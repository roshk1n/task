package com.home.test.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected val composite: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        composite.dispose()
    }
}