package com.kotlin.mvvm.domain

import com.kotlin.mvvm.data.model.Failure
import com.kotlin.mvvm.data.model.Either
import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> where Type : Any, Params : Any {

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