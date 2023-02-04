package com.ziad.marvelmdi.data.remote

import com.ziad.marvelmdi.data.remote.model.BaseResponse
import com.ziad.marvelmdi.data.remote.model.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET(EndPoints.GET_CHARACTERS)
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): BaseResponse<CharactersResponse>
}