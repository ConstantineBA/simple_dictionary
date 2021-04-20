package com.example.simple_dictionary.core.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.subjects.PublishSubject

abstract class BaseViewModel<EVENT : BaseUiEvent, MODEL : BaseUiModel> : ViewModel() {

    val subject: @NonNull PublishSubject<EVENT> by lazy { PublishSubject.create() }

    val model: Observable<MODEL> = subject
        .doOnEach { previewEvent = it.value }
        .filter { it != previewEvent }
        .compose(mappingEventToModel())
        .filter { it != previewModel }
        .doOnNext { previewModel = it }


    private var previewEvent: BaseUiEvent? = null
    private var previewModel: BaseUiModel? = null

    protected abstract fun mappingEventToModel(): ObservableTransformer<EVENT, MODEL>
}