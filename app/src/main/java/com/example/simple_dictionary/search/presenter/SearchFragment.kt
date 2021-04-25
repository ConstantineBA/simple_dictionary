package com.example.simple_dictionary.search.presenter

import androidx.fragment.app.viewModels
import com.example.simple_dictionary.core.base.BaseFragment
import com.example.simple_dictionary.databinding.SearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<SearchFragmentBinding, SearchUiEvent, SearchUiModel>
        (SearchFragmentBinding::inflate) {

    override val viewModel: SearchViewModel by viewModels()
}