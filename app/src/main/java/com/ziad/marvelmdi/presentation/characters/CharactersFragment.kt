package com.ziad.marvelmdi.presentation.characters

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.ziad.marvelmdi.databinding.FragmentCharactersBinding
import com.ziad.marvelmdi.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment :
    BaseFragment<FragmentCharactersBinding>(FragmentCharactersBinding::inflate) {
    private val viewModel: CharactersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = binding.rvCharacters
        val controller = CharacterListEpoxyController {
            Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
        }

        rv.setController(controller)
        viewModel.fetchCharacters {
            controller.submitData(it)
        }
    }
}