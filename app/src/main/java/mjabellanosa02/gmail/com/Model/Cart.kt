package mjabellanosa02.gmail.com.Model

import android.os.Parcel
import android.os.Parcelable

class Cart() :Parcelable {
    var cart_item_name: String ?= null
    var cart_item_uid: String ?= null
    var cart_uid: String ?= null
    var cart_item_price: Double ?= null
    var cart_item_image_url: String ?= null
    var cart_item_additional_info: ArrayList<String> ?= null
    var cart_item_count: Int ?= null

    constructor(parcel: Parcel) : this() {
        cart_item_name = parcel.readString()
        cart_item_uid = parcel.readString()
        cart_uid = parcel.readString()
        cart_item_price = parcel.readValue(Double::class.java.classLoader) as? Double
        cart_item_image_url = parcel.readString()
        cart_item_count = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cart_item_name)
        parcel.writeString(cart_item_uid)
        parcel.writeString(cart_uid)
        parcel.writeValue(cart_item_price)
        parcel.writeString(cart_item_image_url)
        parcel.writeValue(cart_item_count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cart> {
        override fun createFromParcel(parcel: Parcel): Cart {
            return Cart(parcel)
        }

        override fun newArray(size: Int): Array<Cart?> {
            return arrayOfNulls(size)
        }
    }
}