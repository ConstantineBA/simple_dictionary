package com.example.simple_dictionary.presenter.splashscreen

import com.example.simple_dictionary.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SplashscreenViewModel @Inject constructor() :
    BaseViewModel<SplashscreenUiEvent, SplashscreenUiModel>() {


    override fun mappingEventToModel(): ObservableTransformer<SplashscreenUiEvent, SplashscreenUiModel> {
        return ObservableTransformer {
            it.ofType(SplashscreenUiEvent.OpenScreen::class.java).compose(onOpenScreenEvent())
        }
    }

    private fun onOpenScreenEvent(): ObservableTransformer<SplashscreenUiEvent, SplashscreenUiModel.GoToSearch> {
        return ObservableTransformer {
            Observable.just(SplashscreenUiModel.GoToSearch).delay(1, TimeUnit.SECONDS)
        }
    }
}