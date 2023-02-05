package com.ziad.marvelmdi.presentation.character_details

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.ziad.marvelmdi.R
import com.ziad.marvelmdi.data.remote.model.GenericDetail
import com.ziad.marvelmdi.presentation.core.BaseEpoxyHolder
import com.ziad.marvelmdi.utils.getUsableUrl

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_generic_detail)
abstract class GenericDetailItemModel : EpoxyModelWithHolder<GenericDetailItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var comic: GenericDetail

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onItemClicked: (url: String) -> Unit

    override fun bind(holder: Holder) {
        super.bind(holder)
        with(holder) {
            tvName.text = comic.title

            Glide
                .with(tvName.context)
                .load(comic.thumbnail.getUsableUrl())
                .placeholder(R.color.grey)
                .centerCrop()
                .into(ivComic)

            root.setOnClickListener {
                comic.urls?.let {
                    onItemClicked(it[0].url)
                }

            }
        }
    }

    class Holder : BaseEpoxyHolder() {
        val root: View by bind(R.id.root)
        val tvName: TextView by bind(R.id.tv_name)
        val ivComic: ImageView by bind(R.id.iv_comic)

    }
}