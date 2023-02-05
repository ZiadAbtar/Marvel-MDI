package com.ziad.marvelmdi.presentation.character_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.ziad.marvelmdi.R
import com.ziad.marvelmdi.databinding.FragmentCharachterDetailsBinding
import com.ziad.marvelmdi.presentation.core.BaseFragment
import com.ziad.marvelmdi.utils.getUsableUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment :
    BaseFragment<FragmentCharachterDetailsBinding>(FragmentCharachterDetailsBinding::inflate) {

    private val args: CharacterDetailsFragmentArgs by navArgs()
    private val viewModel: CharacterDetailsViewModel by viewModels()

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

        val controller = CharacterDetailsEpoxyController { comicUrl ->
            val webIntent = Intent(Intent.ACTION_VIEW)
            webIntent.data = Uri.parse(comicUrl)
            startActivity(webIntent)
        }
        binding.rvCharacterDetails.setController(controller)

        controller.setData(viewModel.data)

        viewModel.getComics(character.id, {
            controller.setData(viewModel.data)
        }, { errorCode: Int, message: String?, messageId: Int ->
            println("ZIAD, error $errorCode")
        })

        viewModel.getEvents(character.id, {
            controller.setData(viewModel.data)
        }, { errorCode: Int, message: String?, messageId: Int ->
            println("ZIAD, error $errorCode")
        })

        viewModel.getSeries(character.id, {
            controller.setData(viewModel.data)
        }, { errorCode: Int, message: String?, messageId: Int ->
            println("ZIAD, error $errorCode")
        })

        viewModel.getStories(character.id, {
            controller.setData(viewModel.data)
        }, { errorCode: Int, message: String?, messageId: Int ->
            println("ZIAD, error $errorCode")
        })
    }
}