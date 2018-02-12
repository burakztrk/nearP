package com.ozturkburak.nearp.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.ozturkburak.nearp.view.MapsActivity;

import java.util.Locale;

/**
 * Created by burak on 2/12/18.
 */

public class MapsUtils
{

    public static void ShowMarkerOnGoogleMap(Context context , double lat , double lng , String title){
        String uri = String.format("geo:%1s,%2s?q=%3s,%4s(%5s)" ,lat , lng , lat , lng , title );
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage(Variables.GOOGLEMAPS_PACKAGE);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void ShowRouteOnGoogleMap(Context context , double lat , double lng , String title){
        String uri = String.format(Locale.ENGLISH,"http://maps.google.com/maps?daddr=%d,%d(%s)",lat , lng , title );
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage(Variables.GOOGLEMAPS_PACKAGE);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void ShowMarkerOnMapActivity(Context context , double currLat , double currLng , double targetLat , double targetLng , String title){
        Intent intent = new Intent(context , MapsActivity.class );
        intent.putExtra("CURRENT_LAT" , currLat);
        intent.putExtra("CURRENT_LNG" , currLng);

        intent.putExtra("TARGET_LAT" , targetLat);
        intent.putExtra("TARGET_LNG" , targetLng);
        intent.putExtra("TARGET_TITLE" , title);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
