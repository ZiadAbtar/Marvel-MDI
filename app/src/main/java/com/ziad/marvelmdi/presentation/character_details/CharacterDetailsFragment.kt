package com.ziad.marvelmdi.presentation.character_details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.ziad.marvelmdi.databinding.FragmentCharachterDetailsBinding
import com.ziad.marvelmdi.presentation.core.BaseFragment
import com.ziad.marvelmdi.utils.getUsableUrl

class CharacterDetailsFragment :
    BaseFragment<FragmentCharachterDetailsBinding>(FragmentCharachterDetailsBinding::inflate) {

    private val args: CharacterDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivCharacter.apply {
            val characterImage = args.character.thumbnail.getUsableUrl()
            transitionName = characterImage
            Glide.with(this)
                .load(characterImage)
                .into(this)
        }
    }
}