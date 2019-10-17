package com.kotlin.mvvm.data.factory

import com.kotlin.mvvm.data.repository.INetRepository

abstract class IDataStoreFactory {

    private val netRepositoryMaps: MutableMap<String, INetRepository<Any>> by lazy {
        createINetRepositoryMaps()
    }

    abstract fun createINetRepositoryMaps(): MutableMap<String, INetRepository<Any>>

    open fun getINetRepository(type: String): INetRepository<Any>? = netRepositoryMaps[type]

}