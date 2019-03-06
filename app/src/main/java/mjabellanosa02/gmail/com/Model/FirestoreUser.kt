package mjabellanosa02.gmail.com.Model

import android.os.Parcel
import android.os.Parcelable

class FirestoreUser() : Parcelable {
    var user_email: String?= null
    var user_display_name: String ?= null
    var user_user_name: String ?= null
    var user_uid: String ?= null

    constructor(parcel: Parcel) : this() {
        user_email = parcel.readString()
        user_display_name = parcel.readString()
        user_user_name = parcel.readString()
        user_uid = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(user_email)
        parcel.writeString(user_display_name)
        parcel.writeString(user_user_name)
        parcel.writeString(user_uid)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirestoreUser> {
        override fun createFromParcel(parcel: Parcel): FirestoreUser {
            return FirestoreUser(parcel)
        }

        override fun newArray(size: Int): Array<FirestoreUser?> {
            return arrayOfNulls(size)
        }
    }
}