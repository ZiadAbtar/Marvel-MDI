package com.ziad.marvelmdi.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ziad.marvelmdi.data.remote.ApiInterface
import com.ziad.marvelmdi.data.remote.model.BaseResponse
import com.ziad.marvelmdi.data.remote.model.GenericDetailsResponse
import com.ziad.marvelmdi.domain.repository.IGeneralRepository
import com.ziad.marvelmdi.presentation.characters.CharactersPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GeneralRepository @Inject constructor(private val api: ApiInterface) : IGeneralRepository {
    override suspend fun getCharacters(): Flow<PagingData<com.ziad.marvelmdi.data.remote.model.Character>> =
        Pager(PagingConfig(pageSize = 10, prefetchDistance = 1, initialLoadSize = 10),
            pagingSourceFactory = {
                CharactersPagingSource(api)
            }).flow

    override suspend fun getComicsByCharacterId(id: Int): BaseResponse<GenericDetailsResponse> =
        api.getFirst3ComicsByCharacterId(id)

    override suspend fun getEventsByCharacterId(id: Int): BaseResponse<GenericDetailsResponse> =
        api.getFirst3EventsByCharacterId(id)

    override suspend fun getSeriesByCharacterId(id: Int): BaseResponse<GenericDetailsResponse> =
        api.getFirst3SeriesByCharacterId(id)

    override suspend fun getStoriesByCharacterId(id: Int): BaseResponse<GenericDetailsResponse> =
        api.getFirst3StoriesByCharacterId(id)

}