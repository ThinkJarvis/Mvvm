package  com.kotlin.walletservice.model

import android.os.Parcel
import android.os.Parcelable

data class OrderInfo(val name: String?, val price: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderInfo> {
        override fun createFromParcel(parcel: Parcel): OrderInfo {
            return OrderInfo(parcel)
        }

        override fun newArray(size: Int): Array<OrderInfo?> {
            return arrayOfNulls(size)
        }
    }

}