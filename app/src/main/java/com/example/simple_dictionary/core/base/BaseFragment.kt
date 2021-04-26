package com.example.simple_dictionary.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.simple_dictionary.core.HiltApplication
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VIEW_BINDING : ViewBinding, EVENT : BaseUiEvent, MODEL : BaseUiModel>(
    private val inflate: Inflate<VIEW_BINDING>
) : Fragment() {


    protected abstract val viewModel: BaseViewModel<EVENT, MODEL>

    protected lateinit var binding: VIEW_BINDING

    private val disposable by lazy { CompositeDisposable() }

    private var lastUiEvent: BaseUiEvent? = null
    private var lastUiModel: BaseUiModel? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        disposable.add(
            viewModel.model
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ uiModel ->
                    onUiStateChange(uiModel)
                }, ::onErrorUiState)
        )
    }

    override fun onDestroy() {
        disposable.dispose()
        viewModel.subject.distinct()
        super.onDestroy()
    }

    protected open fun onUiStateChange(uiModel: BaseUiModel) {}

    protected open fun onErrorUiState(it: Throwable) {
        it.printStackTrace()
    }

    protected fun sendUiEvent(event: EVENT) {
        viewModel.subject.onNext(event)
    }

    private fun getHilApplication(): HiltApplication {
        return (requireActivity().application as HiltApplication)
    }
}