package com.ziad.marvelmdi.presentation.character_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.ziad.marvelmdi.R
import com.ziad.marvelmdi.databinding.FragmentCharachterDetailsBinding
import com.ziad.marvelmdi.presentation.core.BaseFragment
import com.ziad.marvelmdi.utils.Constants
import com.ziad.marvelmdi.utils.getUsableUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment :
    BaseFragment<FragmentCharachterDetailsBinding>(FragmentCharachterDetailsBinding::inflate) {

    private val args: CharacterDetailsFragmentArgs by navArgs()
    private val viewModel: CharacterDetailsViewModel by viewModels()
    private lateinit var controller: CharacterDetailsEpoxyController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val character = args.character

        binding.ivCharacter.apply {
            val characterImage = character.thumbnail.getUsableUrl()
            transitionName = characterImage
            Glide.with(this)
                .load(characterImage)
                .centerInside()
                .into(this)
        }

        binding.tvName.text = character.name
        binding.tvId.text = getString(R.string.id_s, character.id)

        controller = CharacterDetailsEpoxyController({ comicUrl ->
            val webIntent = Intent(Intent.ACTION_VIEW)
            webIntent.data = Uri.parse(comicUrl)
            startActivity(webIntent)
        }, {
            when (it) {
                Constants.COMICS -> {
                    viewModel.getComics(character.id)
                }
                Constants.EVENTS -> {
                    viewModel.getEvents(character.id)
                }
                Constants.STORIES -> {
                    viewModel.getStories(character.id)
                }
                Constants.SERIES -> {
                    viewModel.getSeries(character.id)
                }
            }
        })
        binding.rvCharacterDetails.setController(controller)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.refreshFlow.collect {
                controller.setData(viewModel.data)
            }
        }


        fetchAll(character.id)
    }

    private fun fetchAll(characterId: Int) {

        viewModel.getComics(characterId)

        viewModel.getEvents(characterId)

        viewModel.getSeries(characterId)

        viewModel.getStories(characterId)
    }
}