package com.ziad.marvelmdi.data.remote

import android.util.Log
import com.google.gson.Gson
import com.ziad.marvelmdi.R
import com.ziad.marvelmdi.data.remote.model.BaseResponse
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException

class ApiFlowWrapper<T> {

    operator fun invoke(apiCallBack: suspend () -> BaseResponse<T>): Flow<Resource<T>> {
        val networkFlow = flow {
            try {
                emit(Resource.Loading())
                val resultObject: BaseResponse<T> = apiCallBack()
                Log.wtf("ApiWrapper ---->", resultObject.data.toString())

                if (resultObject.code != 200) {
                    emit(Resource.Error(resultObject.code))
                } else
                    emit(Resource.Success(resultObject))
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()
                if (errorBody != null) {
                    val httpCode = e.response()!!.code()
                    val resp =
                        Gson().fromJson(errorBody.byteString().utf8(), BaseResponse::class.java)

                    Log.wtf("ApiWrapper ---> $httpCode", resp.message)
                    emit(Resource.Error(httpCode, message = resp.message ?: ""))
                } else {
                    emit(Resource.Error(500, stringResource = R.string.something_went_wrong))
                }
            } catch (e: IOException) {
                emit(Resource.Error(stringResource = R.string.check_server))
            } catch (unknown: Exception) {
                if (unknown.message != null) {
                    emit(Resource.Error(message = unknown.message!!))
                } else {
                    emit(Resource.Error(stringResource = R.string.something_went_wrong))
                }
            }
        }.catch { cause ->
            Log.wtf("Flow Error -->", cause.cause?.message)
        }
        return networkFlow
    }

}