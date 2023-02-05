package com.ziad.marvelmdi.data.remote

import com.ziad.marvelmdi.data.remote.model.BaseResponse
import com.ziad.marvelmdi.data.remote.model.CharactersResponse
import com.ziad.marvelmdi.data.remote.model.ComicsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET(EndPoints.GET_CHARACTERS)
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): BaseResponse<CharactersResponse>

    @GET(EndPoints.GET_CHARACTERS_FIRST_3_COMICS)
    suspend fun getFirst3ComicsByCharacterId(
        @Path("ID") charId: Int,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 3,
    ): BaseResponse<ComicsResponse>
}