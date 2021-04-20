package com.example.simple_dictionary.presenter.splashscreen

import androidx.fragment.app.viewModels
import com.example.simple_dictionary.core.base.BaseFragment
import com.example.simple_dictionary.core.base.BaseUiModel
import com.example.simple_dictionary.databinding.SplashscreenFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashscreenFragment :
    BaseFragment<SplashscreenFragmentBinding, SplashscreenUiEvent, SplashscreenUiModel>(
        SplashscreenFragmentBinding::inflate
    ) {


    override val viewModel: SplashscreenViewModel by viewModels()


    override fun onResume() {
        super.onResume()
        sendUiEvent(SplashscreenUiEvent.OpenScreen)
    }

    override fun onUiStateChange(uiModel: BaseUiModel) {

    }
}