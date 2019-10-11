package com.kotlin.mvvm

import android.app.Application
import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

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

        initLogger()
    }


    private fun initLogger() {
        Logger.addLogAdapter(object : AndroidLogAdapter(getStrategy()) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }


    private fun getStrategy(): PrettyFormatStrategy =
        PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)  // 是否显示线程信息，默认为ture
            .methodCount(1)         // 显示的方法行数
            .methodOffset(0)        // 隐藏内部方法调用到偏移量
            .tag("tag")
            .build()

}