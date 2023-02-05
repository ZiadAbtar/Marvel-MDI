package com.ziad.marvelmdi.presentation.character_details

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.ziad.marvelmdi.data.remote.model.GenericDetail
import com.ziad.marvelmdi.presentation.core.EmptyEpoxyModel_
import com.ziad.marvelmdi.presentation.core.LoadingEpoxyModel_
import com.ziad.marvelmdi.utils.Constants

class CharacterDetailsEpoxyController(
    private val onItemClicked: (url: String) -> Unit
) :
    EpoxyController() {

    private var data = LinkedHashMap<String, List<Any>?>()

    fun setData(data: LinkedHashMap<String, List<Any>?>) {
        this.data = data
        requestModelBuild()
    }

    override fun buildModels() {
        data.forEach { (type, array) ->
            TitleEpoxyModel_()
                .id(type)
                .title(type)
                .addTo(this)

            if (array == null) {
                LoadingEpoxyModel_()
                    .id("$type loading")
                    .addTo(this)
            } else if (array.isEmpty()) {
                EmptyEpoxyModel_()
                    .id("$type empty")
                    .addTo(this)
            } else {
                val genericModels = arrayListOf<GenericDetailItemModel_>()
                for (item in array) {
                    val genericItem = item as GenericDetail
                    genericModels.add(
                        GenericDetailItemModel_()
                            .id(genericItem.id)
                            .item(genericItem)
                            .onItemClicked(onItemClicked)
                    )
                }

                CarouselModel_()
                    .id("$type carousel")
                    .models(genericModels)
                    .addTo(this)
            }
        }
    }
}