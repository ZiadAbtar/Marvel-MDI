package com.ziad.marvelmdi.data.remote

import com.ziad.marvelmdi.data.remote.model.BaseResponse

sealed class Resource<T>(val data: BaseResponse<T>? = null, val message: String? = null) {

    class Success<T>(data: BaseResponse<T>) : Resource<T>(data)

    class Error<T>(responseCode: Int = -1, message: String = "", val stringResource: Int = -1) :
        Resource<T>(message = message)

    class Loading<T> : Resource<T>()
}