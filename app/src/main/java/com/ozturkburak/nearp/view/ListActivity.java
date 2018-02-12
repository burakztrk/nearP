package com.ozturkburak.nearp.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.maps.model.LatLng;
import com.ozturkburak.nearp.model.FoursquareResponse;
import com.ozturkburak.nearp.R;
import com.ozturkburak.nearp.adapter.LocationsAdapter;
import com.ozturkburak.nearp.model.retrofitModels.RawResponse;
import com.ozturkburak.nearp.model.retrofitModels.Venue;
import com.ozturkburak.nearp.network.FoursquareApi;
import com.ozturkburak.nearp.network.FoursquareApiHelper;
import com.ozturkburak.nearp.utils.CommonUtils;
import com.ozturkburak.nearp.utils.MapsUtils;
import com.ozturkburak.nearp.utils.Variables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by burak on 2/11/18.
 */

public class ListActivity extends AppCompatActivity implements View.OnClickListener
{
    private RecyclerView recyclerView;
    private LocationsAdapter mAdapter;
    private List<Venue> venueList;
    private ProgressBar progressBar;
    private View container;

    LatLng currLocation;
    Venue targetLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        Intent intent = this.getIntent();
        if (intent == null)
            return;

        currLocation = new LatLng(intent.getDoubleExtra("LAT",0) , intent.getDoubleExtra("LNG",0));

        container = this.findViewById(R.id.list_container);
        progressBar = this.findViewById(R.id.list_progressbar);
        recyclerView = this.findViewById(R.id.list_recycler_view);
        venueList = new ArrayList<>();
        mAdapter = new LocationsAdapter( venueList, this);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));


        Map<String , String> opt = new HashMap<>();
        opt.put("ll" , currLocation.latitude +"," + currLocation.longitude);

        FoursquareApi.exploreNear(opt)
                .enqueue(new Callback<RawResponse>()
                {
                    @Override
                    public void onResponse(Call<RawResponse> call, Response<RawResponse> retrofitResponse)
                    {
                        FoursquareResponse fsResponse = FoursquareApiHelper.parseResponse(retrofitResponse.body());
                        if (fsResponse.isOk()){
                            for (Venue venue : fsResponse.getVenueList())
                                venueList.add(venue);
                            mAdapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                        }else
                            CommonUtils.ShowSnackbar(container, getResources().getString(R.string.networkProblem),Snackbar.LENGTH_INDEFINITE, "Retry", new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View view)
                                {
                                    Intent i = getBaseContext().getPackageManager()
                                            .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(i);
                                }
                            });
                    }

                    @Override
                    public void onFailure(Call<RawResponse> call, Throwable t)
                    {
                        CommonUtils.ShowSnackbar(container, getResources().getString(R.string.networkProblem), Snackbar.LENGTH_INDEFINITE, "Retry", new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                Intent i = getBaseContext().getPackageManager()
                                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                            }
                        });
                    }
                });

    }

    @Override
    public void onClick(View view)
    {
        int position = recyclerView.getChildAdapterPosition(view);
        targetLocation = venueList.get(position);

        String targetLabel = targetLocation.getName();
        double targetLat = targetLocation.getLocation().getLat();
        double targetLng = targetLocation.getLocation().getLng();


        try {
            MapsUtils.ShowMarkerOnGoogleMap(getBaseContext() , targetLat , targetLng ,  targetLabel);
        }
        catch(ActivityNotFoundException ex) {
            MapsUtils.ShowMarkerOnMapActivity(getBaseContext() , currLocation.latitude , currLocation.longitude , targetLat , targetLng , targetLabel );
        }
    }
}
