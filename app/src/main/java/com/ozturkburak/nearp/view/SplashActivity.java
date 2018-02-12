package com.ozturkburak.nearp.view;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.CompositePermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener;
import com.ozturkburak.nearp.R;
import com.ozturkburak.nearp.utils.CommonUtils;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

/**
 * Created by burak on 2/10/18.
 */


public class SplashActivity extends AppCompatActivity
{

    private LocationRequest mLocationRequest;
    View snackbarContainer ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        snackbarContainer = findViewById(R.id.splash_container);

        checkGPSState();
        setPermissionListener();
    }


    private void setPermissionListener()
    {

        PermissionListener accessListener = new PermissionListener()
        {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response)
            {
                CommonUtils.ShowSnackbar(snackbarContainer , getResources().getString(R.string.locationChecking),Snackbar.LENGTH_LONG ,null , null);
                startLocationUpdates();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {}

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {}
        };

        View container = findViewById(R.id.splash_container);
        PermissionListener snackbarPermissionSettings =
                SnackbarOnDeniedPermissionListener.Builder.with(container, getResources().getString(R.string.locationDenied))
                        .withOpenSettingsButton(getResources().getString(R.string.settings)).build();

        PermissionListener listener = new CompositePermissionListener(snackbarPermissionSettings, accessListener);

        Dexter.withActivity(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(listener)
                .check();
    }


    @SuppressLint("MissingPermission")
    protected void startLocationUpdates()
    {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setNumUpdates(1);
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(1000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();


        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);

        getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, new LocationCallback()
        {
            @Override
            public void onLocationResult(LocationResult locationResult)
            {
                nextScreen(locationResult.getLastLocation());
            }
        }, Looper.myLooper());
    }


    private void nextScreen(final Location location)
    {
        runOnUiThread(new Runnable()
        {

            @Override
            public void run()
            {
                Intent intent = new Intent(getBaseContext(), ListActivity.class);
                intent.putExtra("LAT", location.getLatitude());
                intent.putExtra("LNG", location.getLongitude());
                startActivity(intent);
                SplashActivity.this.finish();
            }
        });
    }




    private void checkGPSState()
    {
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            CommonUtils.ShowSnackbar(snackbarContainer ,getResources().getString(R.string.enableGPS), Snackbar.LENGTH_LONG , getResources().getString(R.string.settings), new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });

        }
    }
}
