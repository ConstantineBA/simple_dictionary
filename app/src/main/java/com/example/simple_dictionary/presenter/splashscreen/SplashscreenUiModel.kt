package com.example.simple_dictionary.presenter.splashscreen

import com.example.simple_dictionary.core.base.BaseUiModel

sealed class SplashscreenUiModel : BaseUiModel(){
    object GoToSearch: SplashscreenUiModel()
}