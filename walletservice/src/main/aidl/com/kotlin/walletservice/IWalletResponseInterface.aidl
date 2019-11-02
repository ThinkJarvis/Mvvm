// IWalletResponseInterface.aidl
package com.kotlin.walletservice;

import  com.kotlin.walletservice.model.OrderInfo;

interface IWalletResponseInterface {
        void onSuccessResponse(in OrderInfo orderInfo);

        void onFailureResponse(in String error);
}
