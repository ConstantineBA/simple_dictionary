package com.example.simple_dictionary.search_detail.presenter

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.simple_dictionary.R
import com.example.simple_dictionary.common.util.genericFastItemAdapter
import com.example.simple_dictionary.core.base.BaseFragment
import com.example.simple_dictionary.databinding.CommonClickArrowItemBinding
import com.example.simple_dictionary.databinding.SearchDetailFragmentBinding
import com.example.simple_dictionary.search_detail.presenter.model.TranslationItem
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.mikepenz.fastadapter.binding.listeners.addClickListener
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

        val items = mutableListOf<AbstractBindingItem<*>?>()
        if (resources.configuration.orientation == ORIENTATION_LANDSCAPE) {
            transcriptionInclude?.root?.isVisible = uiModel.isContent()
            definitionInclude?.root?.isVisible = uiModel.isContent()
            partOfSpeechInclude?.root?.isVisible = uiModel.isContent()

            transcriptionInclude?.let { uiModel.transcription?.bindView(it, emptyList()) }
            definitionInclude?.let { uiModel.definition?.bindView(it, emptyList()) }
            partOfSpeechInclude?.let { uiModel.partOfSpeech?.bindView(it, emptyList()) }
        } else {
            items.add(uiModel.transcription)
            items.add(uiModel.definition)
            items.add(uiModel.partOfSpeech)
        }
        uiModel.examples?.let { items.addAll(it) }
        uiModel.translations?.let { items.addAll(it) }

        list.genericFastItemAdapter.set(items.filterNotNull() as List<GenericItem>)
    }

    private fun SearchDetailFragmentBinding.setupView() {
        list.adapter = GenericFastItemAdapter().apply {
            addClickListener(
                resolveView = CommonClickArrowItemBinding::getRoot,
                onClick = { _, _, _, item ->
                    if (item is TranslationItem) {
                        sendUiEvent(SearchDetailUiEvent.GetSearchDetails(item.id))
                    }
                }
            )
        }
        list.animation = null

    }
}