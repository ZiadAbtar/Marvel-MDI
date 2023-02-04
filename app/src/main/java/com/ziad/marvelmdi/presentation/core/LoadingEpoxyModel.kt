package com.ziad.marvelmdi.presentation.core

import android.annotation.SuppressLint
import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.ziad.marvelmdi.R

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_loading_vertical)
abstract class LoadingEpoxyModel : EpoxyModelWithHolder<LoadingEpoxyModel.Holder>() {

    override fun bind(holder: Holder) {
        super.bind(holder)
    }
    class Holder : EpoxyHolder() {
        override fun bindView(itemView: View) {

        }
    }
}