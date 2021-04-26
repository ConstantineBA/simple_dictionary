package com.example.simple_dictionary.splashscreen.presenter

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.simple_dictionary.R
import com.example.simple_dictionary.core.base.BaseFragment
import com.example.simple_dictionary.core.base.BaseUiModel
import com.example.simple_dictionary.databinding.SplashscreenFragmentBinding
import com.example.simple_dictionary.splashscreen.presenter.SplashscreenUiModel.GoToSearch
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashscreenFragment :
    BaseFragment<SplashscreenFragmentBinding, SplashscreenUiEvent, SplashscreenUiModel>
        (SplashscreenFragmentBinding::inflate) {

    override val viewModel: SplashscreenViewModel by viewModels()


    override fun onResume() {
        super.onResume()
        sendUiEvent(SplashscreenUiEvent.OpenScreen)
    }

    override fun onUiStateChange(uiModel: BaseUiModel) {
        when (uiModel) {
            is GoToSearch -> findNavController().navigate(R.id.toSearchFragment)
        }
    }
}