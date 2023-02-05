package com.ziad.marvelmdi.data.remote

import com.ziad.marvelmdi.data.remote.model.BaseResponse
import com.ziad.marvelmdi.data.remote.model.CharactersResponse
import com.ziad.marvelmdi.data.remote.model.GenericDetailsResponse
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
    ): BaseResponse<GenericDetailsResponse>

    @GET(EndPoints.GET_CHARACTERS_FIRST_3_EVENTS)
    suspend fun getFirst3EventsByCharacterId(
        @Path("ID") charId: Int,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 3,
    ): BaseResponse<GenericDetailsResponse>

    @GET(EndPoints.GET_CHARACTERS_FIRST_3_SERIES)
    suspend fun getFirst3SeriesByCharacterId(
        @Path("ID") charId: Int,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 3,
    ): BaseResponse<GenericDetailsResponse>

    @GET(EndPoints.GET_CHARACTERS_FIRST_3_STORIES)
    suspend fun getFirst3StoriesByCharacterId(
        @Path("ID") charId: Int,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 3,
    ): BaseResponse<GenericDetailsResponse>
}