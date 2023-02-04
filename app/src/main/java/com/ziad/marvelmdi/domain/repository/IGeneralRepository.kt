package com.ziad.marvelmdi.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface IGeneralRepository {
    suspend fun getCharacters(): Flow<PagingData<com.ziad.marvelmdi.data.remote.model.Character>>
}