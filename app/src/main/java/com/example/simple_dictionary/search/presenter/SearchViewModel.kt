package com.example.simple_dictionary.search.presenter

import com.example.simple_dictionary.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : BaseViewModel<SearchUiEvent, SearchUiModel>() {
    override fun mappingEventToModel(): ObservableTransformer<SearchUiEvent, SearchUiModel> {
        return ObservableTransformer {
            it.ofType(SearchUiEvent::class.java).compose(onOpenScreenEvent())
        }
    }

    private fun onOpenScreenEvent(): ObservableTransformer<SearchUiEvent, SearchUiModel> {
        return ObservableTransformer {
            Observable.empty()
        }
    }
}