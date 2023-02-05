package com.ziad.marvelmdi.presentation.character_details

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.ziad.marvelmdi.data.remote.model.Comic
import com.ziad.marvelmdi.presentation.core.LoadingEpoxyModel_
import com.ziad.marvelmdi.utils.Constants

class CharacterDetailsEpoxyController(
    private val onComicClick: (url: String) -> Unit
) :
    EpoxyController() {

    private var data = HashMap<String, List<Any>?>()

    fun setData(data: HashMap<String, List<Any>?>) {
        this.data = data
        requestModelBuild()
    }

    override fun buildModels() {
        data.forEach { type, array ->
            when (type) {
                Constants.COMICS -> {
                    TitleEpoxyModel_()
                        .id(type)
                        .title(type)
                        .addTo(this)

                    if (array == null) {
                        LoadingEpoxyModel_()
                            .id("$type loading")
                            .addTo(this)
                    } else {
                        val comicModels = arrayListOf<ComicItemModel_>()
                        for (item in array) {
                            val comic = item as Comic
                            comicModels.add(
                                ComicItemModel_()
                                    .id(comic.id)
                                    .comic(comic)
                                    .onItemClicked(onComicClick)
                            )
                        }

                        CarouselModel_()
                            .id("$type carousel")
                            .models(comicModels)
                            .addTo(this)
                    }
                }
                Constants.EVENTS -> {

                }
                Constants.STORIES -> {

                }
                Constants.SERIES -> {

                }
            }
        }
    }
}