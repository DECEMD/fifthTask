package com.example.fifthtask

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Person(
    val viewId: Int,
    val textViewNameId: Int,
    val textViewPhoneId: Int,
    var name: String?,
    var secondName: String?,
    var phone: String?
) :
    Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(viewId)
        parcel.writeInt(textViewNameId)
        parcel.writeInt(textViewPhoneId)
        parcel.writeString(name)
        parcel.writeString(secondName)
        parcel.writeString(phone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }


}

