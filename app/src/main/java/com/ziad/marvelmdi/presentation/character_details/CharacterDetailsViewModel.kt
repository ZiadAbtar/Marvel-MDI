package com.ziad.marvelmdi.presentation.character_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ziad.marvelmdi.data.remote.Resource
import com.ziad.marvelmdi.data.remote.model.Comic
import com.ziad.marvelmdi.domain.use_case.GetComicsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(private val comicsUseCase: GetComicsUseCase) :
    ViewModel() {

    fun getComics(
        characterId: Int,
        onSuccess: (comics: List<Comic>) -> Unit,
        onLoading: () -> Unit,
        onError: (responseCode: Int, message: String?, stringResource: Int) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            comicsUseCase(characterId).collect { resource ->
                withContext(Dispatchers.Main) {
                    when (resource) {
                        is Resource.Loading -> {
                            onLoading()
                        }
                        is Resource.Success -> {
                            onSuccess(resource.data?.data?.results ?: listOf())
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