package com.ziad.marvelmdi.presentation.characters

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.ziad.marvelmdi.R

@EpoxyModelClass(layout = R.layout.item_character)
abstract class MovieItemModel : EpoxyModelWithHolder<MovieItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var character: com.ziad.marvelmdi.data.remote.model.Character

    override fun bind(holder: Holder) {

        holder.titleView?.text = character.name
    }

    class Holder : EpoxyHolder() {
        var titleView: TextView? = null

        override fun bindView(itemView: View) {
            titleView = itemView.findViewById(R.id.tv_hero_name)
        }

    }
}