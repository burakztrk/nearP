package com.ozturkburak.nearp.model.retrofitModels

import android.os.Parcel
import android.os.Parcelable

class Category() : Parcelable {

//    var id: String? = null
    var name: String? = null
    var pluralName: String? = null
    var shortName: String? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        pluralName = parcel.readString()
        shortName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(pluralName)
        parcel.writeString(shortName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }

}
