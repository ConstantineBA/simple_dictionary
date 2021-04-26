package com.example.simple_dictionary.search.presenter

import com.example.simple_dictionary.core.base.BaseViewModel
import com.example.simple_dictionary.search.domain.SearchWordUserCase
import com.example.simple_dictionary.search.presenter.SearchUiEvent.InputSearchTextUiEvent
import com.example.simple_dictionary.search.presenter.SearchUiEvent.OnItemClickedUiEvent
import com.example.simple_dictionary.search.presenter.SearchUiModel.GoToSearchDetail
import com.example.simple_dictionary.search.presenter.SearchUiModel.SearchResultUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchWordUserCase: SearchWordUserCase
) : BaseViewModel<SearchUiEvent, SearchUiModel>() {

    override fun mappingEventToModel(): ObservableTransformer<SearchUiEvent, SearchUiModel> {
        return ObservableTransformer {
            it.publish { event ->
                Observable.merge(
                    event.ofType(InputSearchTextUiEvent::class.java).compose(onSearchEvent()),
                    event.ofType(OnItemClickedUiEvent::class.java).compose(onItemClicked())
                )
            }
        }
    }

    private fun onSearchEvent(): ObservableTransformer<InputSearchTextUiEvent, SearchResultUiModel> {
        return ObservableTransformer {
            it.switchMap { event ->
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

    private fun onItemClicked(): ObservableTransformer<OnItemClickedUiEvent, GoToSearchDetail> {
        return ObservableTransformer {
            it.map { event -> GoToSearchDetail(id = event.id) }
        }
    }
}