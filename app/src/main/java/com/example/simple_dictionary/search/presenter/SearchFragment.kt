package com.example.simple_dictionary.search.presenter

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.example.simple_dictionary.core.base.BaseFragment
import com.example.simple_dictionary.core.base.BaseUiModel
import com.example.simple_dictionary.databinding.SearchFragmentBinding
import com.example.simple_dictionary.search.presenter.SearchUiEvent.InputSearchTextUiEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<SearchFragmentBinding, SearchUiEvent, SearchUiModel>
        (SearchFragmentBinding::inflate) {

    override val viewModel: SearchViewModel by viewModels()

    override fun onUiStateChange(uiModel: BaseUiModel) {
        when (uiModel) {
            is SearchUiModel.SearchResultUiModel -> Log.d("test", "result: ${uiModel.results} ")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setupView()
    }

    private fun SearchFragmentBinding.setupView() {
        searchEdit.addTextChangedListener { inputText ->
            if (inputText != null && inputText.isNotBlank()) {
                sendUiEvent(InputSearchTextUiEvent(inputText.toString()))
            }
        }
    }
}