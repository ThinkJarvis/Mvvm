// IWalletInterface.aidl
package com.kotlin.walletservice;

// Declare any non-default types here with import statements
import  com.kotlin.walletservice.model.OrderInfo;
import  com.kotlin.walletservice.IWalletResponseInterface;

interface IWalletInterface {

     List addListData(in OrderInfo orderInfo);

     void setWalletReponse(in IWalletResponseInterface walletResponseInterface);
}
