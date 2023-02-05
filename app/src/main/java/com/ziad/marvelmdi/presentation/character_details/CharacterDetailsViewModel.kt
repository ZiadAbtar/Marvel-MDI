package com.ziad.marvelmdi.presentation.character_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ziad.marvelmdi.data.local.ErrorObject
import com.ziad.marvelmdi.data.remote.Resource
import com.ziad.marvelmdi.domain.use_case.GetComicsUseCase
import com.ziad.marvelmdi.domain.use_case.GetEventsUseCase
import com.ziad.marvelmdi.domain.use_case.GetSeriesUseCase
import com.ziad.marvelmdi.domain.use_case.GetStoriesUseCase
import com.ziad.marvelmdi.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import kotlin.collections.LinkedHashMap

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val comicsUseCase: GetComicsUseCase,
    private val eventsUseCase: GetEventsUseCase,
    private val seriesUseCase: GetSeriesUseCase,
    private val storiesUseCase: GetStoriesUseCase
) :
    ViewModel() {

    private var _data = LinkedHashMap<String, List<Any>?>()
    val data = _data

    init {
        _data[Constants.COMICS] = listOf()
        _data[Constants.EVENTS] = listOf()
        _data[Constants.SERIES] = listOf()
        _data[Constants.STORIES] = listOf()
    }

    fun getComics(
        characterId: Int,
        onRefresh: () -> Unit
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
                            _data[Constants.COMICS] = Collections.singletonList(ErrorObject())
                            onRefresh()
                        }
                    }
                }
            }
        }
    }

    fun getEvents(
        characterId: Int,
        onRefresh: () -> Unit
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
                            _data[Constants.EVENTS] = Collections.singletonList(ErrorObject())
                            onRefresh()
                        }
                    }
                }
            }
        }
    }

    fun getSeries(
        characterId: Int,
        onRefresh: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            seriesUseCase(characterId).collect { resource ->
                withContext(Dispatchers.Main) {
                    when (resource) {
                        is Resource.Loading -> {
                            _data[Constants.SERIES] = null
                            onRefresh()
                        }
                        is Resource.Success -> {
                            _data[Constants.SERIES] = resource.data?.data?.results ?: listOf()
                            onRefresh()
                        }

                        is Resource.Error -> {
                            _data[Constants.SERIES] = Collections.singletonList(ErrorObject())
                            onRefresh()
                        }
                    }
                }
            }
        }
    }

    fun getStories(
        characterId: Int,
        onRefresh: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            storiesUseCase(characterId).collect { resource ->
                withContext(Dispatchers.Main) {
                    when (resource) {
                        is Resource.Loading -> {
                            _data[Constants.STORIES] = null
                            onRefresh()
                        }
                        is Resource.Success -> {
                            _data[Constants.STORIES] = resource.data?.data?.results ?: listOf()
                            onRefresh()
                        }

                        is Resource.Error -> {
                            _data[Constants.STORIES] = Collections.singletonList(ErrorObject())
                            onRefresh()
                        }
                    }
                }
            }
        }
    }
}