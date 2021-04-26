package com.example.simple_dictionary.search.presenter

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.example.simple_dictionary.R
import com.example.simple_dictionary.common.util.genericFastItemAdapter
import com.example.simple_dictionary.core.base.BaseFragment
import com.example.simple_dictionary.core.base.BaseUiModel
import com.example.simple_dictionary.databinding.SearchFragmentBinding
import com.example.simple_dictionary.databinding.SearchResultItemBinding
import com.example.simple_dictionary.search.presenter.SearchUiEvent.InputSearchTextUiEvent
import com.example.simple_dictionary.search.presenter.model.SearchResultItem
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import com.mikepenz.fastadapter.binding.listeners.addClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<SearchFragmentBinding, SearchUiEvent, SearchUiModel>
        (SearchFragmentBinding::inflate) {

    override val viewModel: SearchViewModel by viewModels()

    override fun onUiStateChange(uiModel: BaseUiModel) {
        when (uiModel) {
            is SearchUiModel.SearchResultUiModel -> {
                binding.showLoading()
                binding.showError(uiModel.isError)
                binding.showContent(uiModel)
            }
        }
    }


    private fun SearchFragmentBinding.showLoading() {
        statusText.text = getString(R.string.search_progress_label)
    }

    private fun SearchFragmentBinding.showError(isError: Boolean) {
        messageText.isVisible = isError
        if (isError) {
            statusText.text = getString(R.string.splashscreen_error_title)
            messageText.text = getText(R.string.splashscreen_error_something_wrong_description)
        }
    }

    private fun SearchFragmentBinding.showContent(uiModel: SearchUiModel.SearchResultUiModel) {
        list.isVisible = uiModel.isContent()
        if (uiModel.isContent()) {
            statusText.text = getString(R.string.search_result_label)
            val isEmptyState = uiModel.results.isEmpty()
            list.isGone = isEmptyState
            if (isEmptyState) {
                messageText.isVisible = isEmptyState
                messageText.text = getString(R.string.search_not_found_label)
            }
            list.genericFastItemAdapter.set(uiModel.results)
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
        list.adapter = GenericFastItemAdapter().apply {
            addClickListener(
                resolveView = SearchResultItemBinding::getRoot,
                onClick = { _, _, _, item ->
                    if (item is SearchResultItem) {
                        viewModel.onButtonItemClicked(item.id)
                    }
                }
            )
        }
        list.animation = null
    }
}