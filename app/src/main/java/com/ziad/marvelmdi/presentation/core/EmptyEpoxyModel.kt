package com.ziad.marvelmdi.presentation.core

import android.annotation.SuppressLint
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.ziad.marvelmdi.R

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_no_data)
abstract class EmptyEpoxyModel : EpoxyModelWithHolder<EmptyEpoxyModel.Holder>() {

    class Holder : BaseEpoxyHolder()
}