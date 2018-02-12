package com.ozturkburak.nearp.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ozturkburak.nearp.R;
import com.ozturkburak.nearp.model.retrofitModels.Venue;

import java.util.List;

/**
 * Created by burak on 2/11/18.
 */

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.LocationViewHolder>
{

    private List<Venue> locationList;
    private View.OnClickListener listener;

    public LocationsAdapter(List<Venue> locationList , View.OnClickListener listener){
        this.locationList = locationList;
        this.listener = listener;
    }


    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new LocationViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final LocationViewHolder holder, int position)
    {
        final Venue venue = locationList.get(position);
        holder.titleTextView.setText(venue.getName());

        if (venue.getCategories() != null && !venue.getCategories().isEmpty()){
            String category = venue.getCategories().get(0).getName();
            holder.categoryTextView.setText(category ==null ? "" : category);
        }

        if (venue.getLocation() !=null)
            holder.descTextView.setText(venue.getLocation().getAddress());
    }

    @Override
    public int getItemCount()
    {
        return this.locationList.size();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }



    public class LocationViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView , categoryTextView , descTextView;
        ImageView imageView;

        public LocationViewHolder(View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_imageView);
            titleTextView = itemView.findViewById(R.id.item_title);
            categoryTextView = itemView.findViewById(R.id.item_category);
            descTextView = itemView.findViewById(R.id.item_desc);
            itemView.setOnClickListener(listener);
        }

    }
}
