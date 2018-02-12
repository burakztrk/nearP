package com.ozturkburak.nearp.model;

import com.ozturkburak.nearp.model.retrofitModels.Venue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by burak on 2/11/18.
 */

public class FoursquareResponse
{
    private boolean isOk;
    private String errorMessage;
    private  String locationHeader;
    private List<Venue> venueList;

    public FoursquareResponse()
    {
        this.isOk = false;
        this.venueList = new ArrayList<Venue>();
    }

    public boolean isOk()
    {
        return isOk;
    }

    public void setOk(boolean ok)
    {
        isOk = ok;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getLocationHeader()
    {
        return locationHeader;
    }

    public void setLocationHeader(String locationHeader)
    {
        this.locationHeader = locationHeader;
    }

    public List<Venue> getVenueList()
    {
        return venueList;
    }

    public void setVenueList(List<Venue> venueList)
    {
        this.venueList = venueList;
    }
}
