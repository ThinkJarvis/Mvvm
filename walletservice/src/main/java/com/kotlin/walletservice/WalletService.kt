package com.kotlin.walletservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class WalletService : Service() {

    private val walletServiceImp: WalletServiceImp by lazy {
        WalletServiceImp()
    }

    override fun onBind(intent: Intent?): IBinder? = walletServiceImp

    override fun onCreate() {
        super.onCreate()
        Log.e("wjq", "WalletService onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("wjq", "WalletService onDestroy")
        walletServiceImp.clearCallbacks()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        Log.e("wjq", "WalletService onStartCommand")
    }
}
