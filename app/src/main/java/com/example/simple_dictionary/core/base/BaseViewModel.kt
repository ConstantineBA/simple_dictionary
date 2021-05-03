package com.example.simple_dictionary.core.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject

abstract class BaseViewModel<EVENT : BaseUiEvent, MODEL : BaseUiModel> : ViewModel() {

    val subject: @NonNull Subject<EVENT> by lazy { PublishSubject.create() }

    val model: Observable<MODEL> = subject
        .filter { it != previewEvent }
        .doOnNext { previewEvent = it }
        .compose(mappingEventToModel())
        .filter { it != previewModel }
        .doOnNext { previewModel = it }


    private var previewEvent: EVENT? = null
    var previewModel: MODEL? = null

    protected abstract fun mappingEventToModel(): ObservableTransformer<EVENT, MODEL>
}