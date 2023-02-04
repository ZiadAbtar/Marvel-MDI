package com.ziad.marvelmdi.presentation.characters

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyRecyclerView
import com.ziad.marvelmdi.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersActivityTest : AppCompatActivity() {

    private val viewModel: CharactersViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val rv: EpoxyRecyclerView = findViewById(R.id.rv_characters)
        val controller = CharacterListEpoxyController()
        rv.setController(controller)
        viewModel.fetchCharacters {
            controller.submitData(it)
        }
    }
}