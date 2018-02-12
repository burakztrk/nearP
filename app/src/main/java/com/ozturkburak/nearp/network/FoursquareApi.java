package com.ozturkburak.nearp.network;

import com.ozturkburak.nearp.model.retrofitModels.RawResponse;
import com.ozturkburak.nearp.utils.Variables;

import static com.ozturkburak.nearp.utils.Variables.*;
import java.util.Map;

import retrofit2.Call;



/**
 * Created by burak on 2/10/18.
 */

public class FoursquareApi
{

    private static IForsquareApiService forsquareService(){
        return  RetrofitClient.getClient(FOURSQUARE_API_URL).create(IForsquareApiService.class);
    }


    public static Call<RawResponse> exploreNear(Map<String , String> options){

        options.put("client_id", FORSQUAREAPI_CLIENTID);
        options.put("client_secret" , FORSQUAREAPI_CLIENTSECRET);
        options.put("limit" ,String.valueOf(FOURSQUARE_API_LIMIT));
        options.put("v" , FORSQUAREAPI_VERSION);
        return forsquareService().exploreNear(options);
    }


    public static Call<RawResponse> exploreNear(double lat , double lng){
        String location = String.format("%d,%d" , lat , lng);
        return forsquareService().exploreNear(FORSQUAREAPI_CLIENTID , FORSQUAREAPI_CLIENTSECRET , FORSQUAREAPI_VERSION , location,FOURSQUARE_API_LIMIT);
    }
}
