package com.ziad.marvelmdi.presentation.characters

import android.widget.ImageView
import androidx.paging.LoadState
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.ziad.marvelmdi.data.remote.model.Character
import com.ziad.marvelmdi.presentation.core.EmptyEpoxyModel_
import com.ziad.marvelmdi.presentation.core.ErrorEpoxyModel_
import com.ziad.marvelmdi.presentation.core.LoadingEpoxyModel_


class CharacterListEpoxyController(private val onItemClicked: (character: Character, ivCharacter: ImageView) -> Unit) :
    PagingDataEpoxyController<Character>() {


    private var isError = false

    private var isAppend = false

    private var isLoading = false
        set(value) {
            field = value
            requestModelBuild()
        }

    private var errorMessage: String? = ""
        set(value) {
            field = value?.let {
                isError = true
                it
            } ?: run {
                isError = false
                null
            }
            requestModelBuild()
        }


    init {
        addLoadStateListener { loadStates ->
            isLoading = (loadStates.refresh is LoadState.Loading).apply {
                isAppend = false
            } || (loadStates.append is LoadState.Loading).apply {
                isAppend = true
            }
            isError = (loadStates.refresh is LoadState.Error).apply {
                if (this)
                    isAppend = false
            } || (loadStates.append is LoadState.Error).apply {
                if (this)
                    isAppend = true
            }
            val errorThrowable = (if (loadStates.refresh is LoadState.Error)
                loadStates.refresh
            else if (loadStates.append is LoadState.Error)
                loadStates.append
            else
                null) as LoadState.Error?
            errorMessage = errorThrowable?.error?.message
        }
    }


    override fun buildItemModel(
        currentPosition: Int,
        item: Character?
    ): EpoxyModel<*> {
        item?.let {
            return CharacterItemModel_()
                .id("${item.id} $currentPosition")
                .onItemClicked(onItemClicked)
                .character(item)

        } ?: run {
            //Loading View Model
            return LoadingEpoxyModel_()
                .id("loading")
        }
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        super.addModels(
            if (isError)
                models.plus(
                    ErrorEpoxyModel_()
                        .id("Error")
                        .errorStr(errorMessage)
                ).filter { it !is ErrorEpoxyModel_ }
            else if (isLoading) {
                models.plus(
                    LoadingEpoxyModel_()
                        .id("loading")
                ).distinct()
            } else if (models.isEmpty()) {
                models.plus(
                    EmptyEpoxyModel_()
                        .id("empty")
                ).distinct()
            } else
                models.distinct()
        )

    }

    override fun onExceptionSwallowed(exception: RuntimeException) {

    }
}