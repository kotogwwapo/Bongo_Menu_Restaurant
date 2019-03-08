package mjabellanosa02.gmail.com.Model

import android.os.Parcel
import android.os.Parcelable

class CartDetail() : Parcelable {
    var cart_detail_cost: Float?=null
    var cart_detail_item_count: Int ?= null
    var cart_detail_user_uid: String ?= null

    constructor(parcel: Parcel) : this() {
        cart_detail_cost = parcel.readValue(Float::class.java.classLoader) as? Float
        cart_detail_item_count = parcel.readValue(Int::class.java.classLoader) as? Int
        cart_detail_user_uid = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(cart_detail_cost)
        parcel.writeValue(cart_detail_item_count)
        parcel.writeString(cart_detail_user_uid)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CartDetail> {
        override fun createFromParcel(parcel: Parcel): CartDetail {
            return CartDetail(parcel)
        }

        override fun newArray(size: Int): Array<CartDetail?> {
            return arrayOfNulls(size)
        }
    }


}