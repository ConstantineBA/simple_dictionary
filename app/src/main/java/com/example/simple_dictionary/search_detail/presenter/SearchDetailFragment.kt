package com.example.simple_dictionary.search_detail.presenter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
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
        binding.setupView()
    }

    override fun onResume() {
        super.onResume()
        sendUiEvent(SearchDetailUiEvent.GetSearchDetails(arguments.searchId))
    }

    override fun onUiStateChange(uiModel: SearchDetailUiModel) {
        val items = mutableListOf(
            uiModel.definition,
            uiModel.transcription,
            uiModel.partOfSpeech,
        ).apply {
            uiModel.examples?.let { addAll(it) }
            uiModel.translations?.let { addAll(it) }
        }.filterNotNull()
        binding.list.genericFastItemAdapter.set(items as List<GenericItem>)
    }

    private fun SearchDetailFragmentBinding.setupView() {
        list.adapter = GenericFastItemAdapter().apply {

        }
        list.animation = null
    }

}