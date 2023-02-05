package com.ziad.marvelmdi.domain.repository

import androidx.paging.PagingData
import com.ziad.marvelmdi.data.remote.model.BaseResponse
import kotlinx.coroutines.flow.Flow
import com.ziad.marvelmdi.data.remote.model.Character
import com.ziad.marvelmdi.data.remote.model.ComicsResponse

interface IGeneralRepository {
    suspend fun getCharacters(): Flow<PagingData<Character>>
    suspend fun getComicsByCharacterId(id: Int): BaseResponse<ComicsResponse>
}