package com.ziad.marvelmdi.domain.use_case

import com.ziad.marvelmdi.data.remote.ApiFlowWrapper
import com.ziad.marvelmdi.data.remote.model.GenericDetailsResponse
import com.ziad.marvelmdi.data.remote.repository.GeneralRepository
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(private val generalRepository: GeneralRepository) {
    operator fun invoke(characterId: Int) = ApiFlowWrapper<GenericDetailsResponse>().invoke {
        generalRepository.getEventsByCharacterId(characterId)
    }
}