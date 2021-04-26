package com.example.simple_dictionary.search_detail

import androidx.fragment.app.viewModels
import com.example.simple_dictionary.core.base.BaseFragment
import com.example.simple_dictionary.databinding.SearchFragmentBinding

class SearchDetailFragment :
    BaseFragment<SearchFragmentBinding, SearchDetailUiEvent, SearchDetailUiModel>
        (SearchFragmentBinding::inflate) {

    override val viewModel: SearchDetailViewModel by viewModels()
}