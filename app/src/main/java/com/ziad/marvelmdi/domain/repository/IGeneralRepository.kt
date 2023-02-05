package com.ziad.marvelmdi.domain.repository

import com.ziad.marvelmdi.data.remote.model.BaseResponse
import com.ziad.marvelmdi.data.remote.model.ComicsResponse

interface IGeneralRepository {
    suspend fun getComicsByCharacterId(id: Int): BaseResponse<ComicsResponse>
}