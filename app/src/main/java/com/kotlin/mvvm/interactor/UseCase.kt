package com.kotlin.mvvm.interactor

import com.kotlin.mvvm.exception.Failure
import com.kotlin.mvvm.functional.Either
import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> where Type : Any {

    private val netJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + netJob)

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        uiScope.launch {
            onResult(run(params))
        }
    }

    class None
}