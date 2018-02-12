package com.ozturkburak.nearp.model.retrofitModels

import android.os.Parcel
import android.os.Parcelable


class Location() : Parcelable {

    var address: String? = null
    var crossStreet: String? = null
    var lat: Double? = null
    var lng: Double? = null
//    var labeledLatLngs: List<LabeledLatLng>? = null
    var distance: Int? = null
    var postalCode: String? = null
    var cc: String? = null
    var city: String? = null
    var state: String? = null
    var country: String? = null
    var formattedAddress: List<String>? = null

    constructor(parcel: Parcel) : this() {
        address = parcel.readString()
        crossStreet = parcel.readString()
        lat = parcel.readValue(Double::class.java.classLoader) as? Double
        lng = parcel.readValue(Double::class.java.classLoader) as? Double
        distance = parcel.readValue(Int::class.java.classLoader) as? Int
        postalCode = parcel.readString()
        cc = parcel.readString()
        city = parcel.readString()
        state = parcel.readString()
        country = parcel.readString()
        formattedAddress = parcel.createStringArrayList()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeString(crossStreet)
        parcel.writeValue(lat)
        parcel.writeValue(lng)
        parcel.writeValue(distance)
        parcel.writeString(postalCode)
        parcel.writeString(cc)
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeString(country)
        parcel.writeStringList(formattedAddress)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<Location?> {
            return arrayOfNulls(size)
        }
    }

}
