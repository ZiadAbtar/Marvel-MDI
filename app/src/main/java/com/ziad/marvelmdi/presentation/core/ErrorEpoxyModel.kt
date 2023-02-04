package com.ziad.marvelmdi.presentation.core

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.ziad.marvelmdi.R

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_error)
abstract class ErrorEpoxyModel : EpoxyModelWithHolder<ErrorEpoxyModel.Holder>() {

    @EpoxyAttribute
    var errorStr: String? = null

    override fun bind(holder: Holder) {
        holder.errorTextView.text = errorStr
    }

    class Holder : BaseEpoxyHolder() {
        val errorTextView by bind<TextView>(R.id.tv_error)
    }
}