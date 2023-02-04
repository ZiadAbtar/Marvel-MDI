package com.ziad.marvelmdi.domain.use_case

import com.ziad.marvelmdi.data.remote.ApiFlowWrapper
import com.ziad.marvelmdi.data.remote.model.CharactersResponse
import com.ziad.marvelmdi.data.remote.repository.GeneralRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val generalRepository: GeneralRepository) {
//    operator fun invoke(offset: Int, limit: Int) = ApiFlowWrapper<CharactersResponse>().invoke {
//        generalRepository.getCharacters(offset, limit)
//    }
}