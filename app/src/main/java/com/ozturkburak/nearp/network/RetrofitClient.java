package com.ozturkburak.nearp.network;

import android.view.View;

import com.ozturkburak.nearp.model.FoursquareResponse;
import com.ozturkburak.nearp.model.retrofitModels.RawResponse;
import com.ozturkburak.nearp.model.retrofitModels.Venue;
import com.ozturkburak.nearp.utils.Variables;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by burak on 2/10/18.
 */

public class RetrofitClient
{
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl)
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
