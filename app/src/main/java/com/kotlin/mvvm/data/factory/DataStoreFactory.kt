package com.kotlin.mvvm.data.factory

import com.kotlin.mvvm.data.repository.INetRepository
import com.kotlin.mvvm.data.repository.PostRepository


class DataStoreFactory private constructor() : IDataStoreFactory() {
    override fun createINetRepositoryMaps(): MutableMap<String, INetRepository<Any>> =
        mutableMapOf(PostRepository::class.java.simpleName to PostRepository())

    companion object {
        val instance: DataStoreFactory by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DataStoreFactory()
        }

    }


}