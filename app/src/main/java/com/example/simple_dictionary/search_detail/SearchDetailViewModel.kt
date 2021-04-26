package com.example.simple_dictionary.search_detail

import com.example.simple_dictionary.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.ObservableTransformer
import javax.inject.Inject

@HiltViewModel
class SearchDetailViewModel @Inject constructor() :
    BaseViewModel<SearchDetailUiEvent, SearchDetailUiModel>() {
    override fun mappingEventToModel(): ObservableTransformer<SearchDetailUiEvent, SearchDetailUiModel> {
        TODO("Not yet implemented")
    }
}