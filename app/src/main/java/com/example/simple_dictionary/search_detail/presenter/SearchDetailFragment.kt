package com.example.simple_dictionary.search_detail.presenter

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.simple_dictionary.R
import com.example.simple_dictionary.common.util.genericFastItemAdapter
import com.example.simple_dictionary.core.base.BaseFragment
import com.example.simple_dictionary.databinding.SearchDetailFragmentBinding
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDetailFragment :
    BaseFragment<SearchDetailFragmentBinding, SearchDetailUiEvent, SearchDetailUiModel>
        (SearchDetailFragmentBinding::inflate) {

    override val viewModel: SearchDetailViewModel by viewModels()

    private val arguments by navArgs<SearchDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.appbarContainer.toolbar) {
            title = getString(R.string.search_detail_toolbar_label)
            setToolbarBackNavigation()
        }

        binding.setupView()
    }

    override fun onResume() {
        super.onResume()
        sendUiEvent(SearchDetailUiEvent.GetSearchDetails(arguments.searchId))
    }

    override fun onUiStateChange(uiModel: SearchDetailUiModel) {
        with(binding) {
            showLoading(uiModel.isLoading)
            showError(uiModel.isError)
            showContent(uiModel)
        }
    }


    private fun SearchDetailFragmentBinding.showLoading(isLoading: Boolean) {
        progress.isVisible = isLoading
    }

    private fun SearchDetailFragmentBinding.showError(isError: Boolean) {
        errorGroup.isVisible = isError
    }

    private fun SearchDetailFragmentBinding.showContent(uiModel: SearchDetailUiModel) {
        list.isVisible = uiModel.isContent()
        val items = mutableListOf(
            uiModel.definition,
            uiModel.transcription,
            uiModel.partOfSpeech,
        ).apply {
            uiModel.examples?.let { addAll(it) }
            uiModel.translations?.let { addAll(it) }
        }.filterNotNull()
        list.genericFastItemAdapter.set(items as List<GenericItem>)
    }

    private fun SearchDetailFragmentBinding.setupView() {
        list.adapter = GenericFastItemAdapter()
        list.animation = null
    }
}