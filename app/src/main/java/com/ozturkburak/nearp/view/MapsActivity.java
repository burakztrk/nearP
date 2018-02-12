package com.ozturkburak.nearp.view;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ozturkburak.nearp.R;
import com.ozturkburak.nearp.model.retrofitModels.Location;
import com.ozturkburak.nearp.model.retrofitModels.Venue;
import com.ozturkburak.nearp.utils.Variables;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{

    private GoogleMap mMap;
    private LatLng curLocation;
    private Venue targetLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = this.getIntent();
        if (intent !=null){
            curLocation = new LatLng(
                    intent.getDoubleExtra("CURRENT_LAT",0),
                    intent.getDoubleExtra("CURRENT_LNG",0 ));

            targetLocation = new Venue();
            targetLocation.setName(intent.getStringExtra("TARGET_TITLE"));

            Location location = new Location();
            location.setLat(intent.getDoubleExtra("TARGET_LAT",0));
            location.setLng(intent.getDoubleExtra("TARGET_LNG",0));
            targetLocation.setLocation(location);


        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        if (curLocation == null && targetLocation == null && targetLocation.getLocation() == null)
            this.finish();


        LatLng targetLatLang = new LatLng(targetLocation.getLocation().getLat() , this.targetLocation.getLocation().getLng());
        MarkerOptions targetMarker = new MarkerOptions();
        targetMarker.position(targetLatLang).title(this.targetLocation.getName());

        MarkerOptions currentMarker = new MarkerOptions();
        currentMarker.position(curLocation).title(getResources().getString(R.string.myLocation));

        mMap.addMarker(currentMarker).showInfoWindow();
        mMap.addMarker(targetMarker).showInfoWindow();

        mMap.moveCamera(CameraUpdateFactory.newLatLng(targetLatLang));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(targetLatLang, Variables.ZOOM_LEVEL));


    }
}
