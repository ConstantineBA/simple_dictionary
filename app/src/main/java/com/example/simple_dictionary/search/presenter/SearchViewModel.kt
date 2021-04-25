package com.example.simple_dictionary.search.presenter

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

    private fun onOpenScreenEvent(): ObservableTransformer<InputSearchTextUiEvent, SearchResultUiModel> {
        return ObservableTransformer {
            it.switchMap { event ->
                searchWordUserCase.execute(event.inputSting)
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map {
                        SearchResultUiModel(results = it)
                    }
                    .startWithItem(SearchResultUiModel().toLoading())
                    .doOnError {
                        it.printStackTrace()
                    }
                    .onErrorReturnItem(SearchResultUiModel().toError())

            }
        }
    }
}