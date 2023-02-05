package com.ziad.marvelmdi.domain.use_case

import com.ziad.marvelmdi.data.remote.ApiFlowWrapper
import com.ziad.marvelmdi.data.remote.model.ComicsResponse
import com.ziad.marvelmdi.data.remote.repository.GeneralRepository
import javax.inject.Inject

class GetComicsUseCase @Inject constructor(private val generalRepository: GeneralRepository) {
    operator fun invoke(characterId: Int) = ApiFlowWrapper<ComicsResponse>().invoke {
        generalRepository.getComicsByCharacterId(characterId)
    }
}