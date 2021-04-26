package com.example.simple_dictionary.splashscreen.presenter

import com.example.simple_dictionary.core.base.BaseUiEvent

sealed class SplashscreenUiEvent: BaseUiEvent() {
    object OpenScreen: SplashscreenUiEvent()
}