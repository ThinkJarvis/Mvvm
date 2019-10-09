package com.kotlin.mvvm

import android.app.Application
import android.content.Context

class KtApplication : Application() {

    companion object {

        private lateinit var instance: KtApplication


        fun getContext(): Context {
            return instance
        }

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


}