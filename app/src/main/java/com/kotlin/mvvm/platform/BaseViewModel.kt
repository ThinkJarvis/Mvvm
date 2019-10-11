package com.kotlin.mvvm.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.mvvm.exception.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

open class CoroutineViewModel : ViewModel(), CoroutineScope {

    var failure = MutableLiveData<Failure>()

    private val viewModelJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    open fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}