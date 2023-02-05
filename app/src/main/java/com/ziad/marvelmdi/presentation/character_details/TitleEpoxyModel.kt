package com.ziad.marvelmdi.presentation.character_details

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.ziad.marvelmdi.R
import com.ziad.marvelmdi.presentation.core.BaseEpoxyHolder

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_title)
abstract class TitleEpoxyModel : EpoxyModelWithHolder<TitleEpoxyModel.Holder>() {
    @EpoxyAttribute
    var title: String? = null

    override fun bind(holder: Holder) {
        holder.tvTitle.text = title
    }

    class Holder : BaseEpoxyHolder() {
        val tvTitle by bind<TextView>(R.id.tv_title)
    }
}