package com.ziad.marvelmdi.presentation.character_details

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.ziad.marvelmdi.R
import com.ziad.marvelmdi.data.remote.model.GenericDetail
import com.ziad.marvelmdi.presentation.core.BaseEpoxyHolder
import com.ziad.marvelmdi.utils.getUsableUrl

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_retry)
abstract class RetryItemModel : EpoxyModelWithHolder<RetryItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var type: String

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onRetryClicked: (type: String) -> Unit

    override fun bind(holder: Holder) {
        super.bind(holder)
        with(holder) {
            btnRetry.setOnClickListener {
                onRetryClicked(type)
            }
        }
    }

    class Holder : BaseEpoxyHolder() {
        val btnRetry: MaterialButton by bind(R.id.btn_retry)
    }
}