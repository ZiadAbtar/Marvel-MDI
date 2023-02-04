package com.ziad.marvelmdi.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ziad.marvelmdi.data.remote.repository.GeneralRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val generalRepository: GeneralRepository) :
    ViewModel() {


    fun fetchCharacters(onSubmit: suspend (pagingData: PagingData<com.ziad.marvelmdi.data.remote.model.Character>) -> Unit) {
        viewModelScope.launch {
            charactersStateFlow.collect {
                onSubmit(it)
            }
        }
    }

    private val charactersStateFlow: StateFlow<PagingData<com.ziad.marvelmdi.data.remote.model.Character>> by lazy {
        runBlocking {
            generalRepository.getCharacters()
                .cachedIn(viewModelScope)
                .stateIn(
                    viewModelScope,
                    SharingStarted.Eagerly,
                    PagingData.empty()
                )
        }
    }
}