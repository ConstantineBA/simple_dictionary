package com.example.simple_dictionary.presenter.splashscreen

import com.example.simple_dictionary.core.base.BaseUiEvent

sealed class SplashscreenUiEvent: BaseUiEvent() {
    object OpenScreen: SplashscreenUiEvent()
}