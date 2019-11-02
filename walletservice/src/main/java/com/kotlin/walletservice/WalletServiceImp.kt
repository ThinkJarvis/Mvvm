package com.kotlin.walletservice

import android.util.Log
import com.kotlin.walletservice.model.OrderInfo


class WalletServiceImp : IWalletInterface.Stub() {
    private val walletResponseCallbacks = mutableListOf<IWalletResponseInterface>()


    override fun setWalletReponse(walletResponseInterface: IWalletResponseInterface?) {
        if (walletResponseInterface != null)
            walletResponseCallbacks.add(walletResponseInterface)
    }


    override fun addListData(orderInfo: OrderInfo?): MutableList<OrderInfo> {
        Log.e("wjq", "${orderInfo?.name}  ${orderInfo?.price}")

        var tempList = mutableListOf(OrderInfo("0", "0"), OrderInfo("1", "1"))


        walletResponseCallbacks.forEach {
            it.onFailureResponse("onFailureResponse")
        }

        return tempList
    }


    open fun clearCallbacks() {
        walletResponseCallbacks.clear()
    }

}