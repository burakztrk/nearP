package com.ozturkburak.nearp.model.retrofitModels

import android.os.Parcel
import android.os.Parcelable


class Venue() : Parcelable {

    var id: String? = null
    var name: String? = null
    //var contact: Contact? = null
    var location: Location? = null
    var categories: List<Category>? = null
//    var verified: Boolean? = null
//    var stats: Stats? = null
    var url: String? = null
//    var price: Price? = null
    var rating: Float? = null
    var ratingColor: String? = null
//    var ratingSignals: Int? = null

     constructor(parcel: Parcel) : this() {
         id = parcel.readString()
         name = parcel.readString()
         url = parcel.readString()
         rating = parcel.readValue(Float::class.java.classLoader) as? Float
         ratingColor = parcel.readString()
     }
//    var allowMenuUrlEdit: Boolean? = null
//    var beenHere: BeenHere? = null
//    var hours: Hours? = null
//    var photos: Photos? = null
//    var hereNow: HereNow? = null
     override fun writeToParcel(parcel: Parcel, flags: Int) {
         parcel.writeString(id)
         parcel.writeString(name)
         parcel.writeString(url)
         parcel.writeValue(rating)
         parcel.writeString(ratingColor)
     }

     override fun describeContents(): Int {
         return 0
     }

     companion object CREATOR : Parcelable.Creator<Venue> {
         override fun createFromParcel(parcel: Parcel): Venue {
             return Venue(parcel)
         }

         override fun newArray(size: Int): Array<Venue?> {
             return arrayOfNulls(size)
         }
     }

 }
