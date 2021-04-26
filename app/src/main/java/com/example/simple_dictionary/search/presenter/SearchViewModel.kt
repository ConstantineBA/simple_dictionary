package com.example.simple_dictionary.search.presenter

import android.util.Log
import com.example.simple_dictionary.core.base.BaseViewModel
import com.example.simple_dictionary.search.domain.SearchWordUserCase
import com.example.simple_dictionary.search.presenter.SearchUiEvent.InputSearchTextUiEvent
import com.example.simple_dictionary.search.presenter.SearchUiModel.SearchResultUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchWordUserCase: SearchWordUserCase
) : BaseViewModel<SearchUiEvent, SearchUiModel>() {

    override fun mappingEventToModel(): ObservableTransformer<SearchUiEvent, SearchUiModel> {
        return ObservableTransformer {
            it.ofType(InputSearchTextUiEvent::class.java).compose(onOpenScreenEvent())
        }
    }

    fun onButtonItemClicked(id: Int) {
        Log.d("test ------->", "onButtonItemClicked: $id")
    }

    private fun onOpenScreenEvent(): ObservableTransformer<InputSearchTextUiEvent, SearchResultUiModel> {
        return ObservableTransformer {
            it.flatMap { event ->
                searchWordUserCase.execute(event.inputSting)
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { results -> SearchResultUiModel(results = results) }
                    .startWithItem(SearchResultUiModel().toLoading())
                    .onErrorReturnItem(SearchResultUiModel().toError())
            }
        }
    }
}