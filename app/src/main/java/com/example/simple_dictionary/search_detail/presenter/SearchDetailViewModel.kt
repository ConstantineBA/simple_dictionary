package com.example.simple_dictionary.search_detail.presenter

import com.example.simple_dictionary.core.base.BaseViewModel
import com.example.simple_dictionary.search_detail.domain.GetMeaningUseCase
import com.example.simple_dictionary.search_detail.presenter.SearchDetailUiEvent.GetSearchDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchDetailViewModel @Inject constructor(
    private val getMeaningUseCase: GetMeaningUseCase
) :
    BaseViewModel<SearchDetailUiEvent, SearchDetailUiModel>() {
    override fun mappingEventToModel(): ObservableTransformer<SearchDetailUiEvent, SearchDetailUiModel> {
        return ObservableTransformer {
            it.ofType(GetSearchDetails::class.java).compose(onOpenScreenEvent())
        }
    }

    private fun onOpenScreenEvent(): ObservableTransformer<GetSearchDetails, SearchDetailUiModel> {
        return ObservableTransformer {
            it.flatMap { event ->
                getMeaningUseCase.execute(event.id)
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { results ->
                        SearchDetailUiModel(
                            transcription = results.transcription,
                            partOfSpeech = results.partOfSpeech,
                            definition = results.definition,
                            translations = results.translations,
                            examples = results.examples
                        )
                    }
                    .startWithItem(SearchDetailUiModel().toLoading())
                    .onErrorReturnItem(SearchDetailUiModel().toError())
            }
        }
    }
}