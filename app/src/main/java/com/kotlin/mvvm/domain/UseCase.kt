package com.kotlin.mvvm.domain


import com.kotlin.walletservice.model.Either
import com.kotlin.walletservice.model.Failure
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class UseCase<out Type, in Params> : CoroutineScope where Type : Any, Params : Any {

    private val netJob = SupervisorJob()

    private val viewModelJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {

        launch {
            val job = async(Dispatchers.IO) { run(params) }
            onResult(job.await())
        }

    }

    class None

    open fun cancelJob() {
        viewModelJob.cancel()
    }
}