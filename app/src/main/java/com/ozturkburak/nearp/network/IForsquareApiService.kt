package com.ozturkburak.nearp.network

import com.ozturkburak.nearp.model.retrofitModels.RawResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Created by burak on 2/10/18.
 */


interface IForsquareApiService {



    @GET("v2/venues/explore?")
    fun exploreNear(@Query("client_id") clientId: String,
                    @Query("client_secret") clientSecret: String,
                    @Query("v") version: String,
                    @Query("ll") latlong: String,
                    @Query("limit") limit: Int): Call<RawResponse>

    @GET("v2/venues/explore?")
    fun exploreNear( @QueryMap  options:Map<String, String>): Call<RawResponse>
}