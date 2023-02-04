package com.ziad.marvelmdi.presentation.characters

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.ziad.marvelmdi.R
import com.ziad.marvelmdi.presentation.core.BaseEpoxyHolder
import com.ziad.marvelmdi.utils.getUsableUrl

@EpoxyModelClass(layout = R.layout.item_character)
abstract class CharacterItemModel : EpoxyModelWithHolder<CharacterItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var character: com.ziad.marvelmdi.data.remote.model.Character

    override fun bind(holder: Holder) {
        super.bind(holder)
        with(holder) {
            tvName.text = character.name
            tvDesc.text = character.description

            if (character.description.isNullOrEmpty())
                tvDesc.visibility = View.GONE
            else
                tvDesc.visibility = View.VISIBLE

            Glide
                .with(tvName.context)
                .load(character.thumbnail.getUsableUrl())
                .placeholder(R.color.grey)
                .into(ivCharacter);
        }
    }

    class Holder : BaseEpoxyHolder() {
        val tvName: TextView by bind(R.id.tv_name)
        val ivCharacter: ImageView by bind(R.id.iv_character)
        val tvDesc: TextView by bind(R.id.tv_desc)

    }
}