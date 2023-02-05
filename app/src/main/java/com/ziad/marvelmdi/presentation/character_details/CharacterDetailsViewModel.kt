package com.ziad.marvelmdi.presentation.character_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ziad.marvelmdi.data.remote.Resource
import com.ziad.marvelmdi.domain.use_case.GetComicsUseCase
import com.ziad.marvelmdi.domain.use_case.GetEventsUseCase
import com.ziad.marvelmdi.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val comicsUseCase: GetComicsUseCase,
    private val eventsUseCase: GetEventsUseCase) :
    ViewModel() {

    private var _data = HashMap<String, List<Any>?>()
    val data = _data

    init {
        _data[Constants.COMICS] = listOf()
        _data[Constants.EVENTS] = listOf()
        _data[Constants.SERIES] = listOf()
        _data[Constants.STORIES] = listOf()
    }

    fun getComics(
        characterId: Int,
        onRefresh: () -> Unit,
        onError: (responseCode: Int, message: String?, stringResource: Int) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            comicsUseCase(characterId).collect { resource ->
                withContext(Dispatchers.Main) {
                    when (resource) {
                        is Resource.Loading -> {
                            _data[Constants.COMICS] = null
                            onRefresh()
                        }
                        is Resource.Success -> {
                            _data[Constants.COMICS] = resource.data?.data?.results ?: listOf()
                            onRefresh()
                        }

                        is Resource.Error -> {
                            onError(
                                resource.responseCode,
                                resource.message,
                                resource.stringResource
                            )
                        }
                    }
                }
            }
        }
    }

    fun getEvents(
        characterId: Int,
        onRefresh: () -> Unit,
        onError: (responseCode: Int, message: String?, stringResource: Int) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            eventsUseCase(characterId).collect { resource ->
                withContext(Dispatchers.Main) {
                    when (resource) {
                        is Resource.Loading -> {
                            _data[Constants.EVENTS] = null
                            onRefresh()
                        }
                        is Resource.Success -> {
                            _data[Constants.EVENTS] = resource.data?.data?.results ?: listOf()
                            onRefresh()
                        }

                        is Resource.Error -> {
                            onError(
                                resource.responseCode,
                                resource.message,
                                resource.stringResource
                            )
                        }
                    }
                }
            }
        }
    }
}