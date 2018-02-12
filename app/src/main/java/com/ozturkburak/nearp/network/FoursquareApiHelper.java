package com.ozturkburak.nearp.network;

import com.ozturkburak.nearp.model.FoursquareResponse;
import com.ozturkburak.nearp.model.retrofitModels.Item;
import com.ozturkburak.nearp.model.retrofitModels.RawResponse;

/**
 * Created by burak on 2/11/18.
 */

public class FoursquareApiHelper
{

    public static FoursquareResponse parseResponse(RawResponse rawResponse)
    {
        FoursquareResponse result = new FoursquareResponse();
        try
        {
            if (rawResponse != null && rawResponse.getResponse() != null)
            {
                result.setLocationHeader(rawResponse.getResponse().getHeaderFullLocation());

                if (rawResponse.getResponse().getGroups() != null &&
                        !rawResponse.getResponse().getGroups().isEmpty() &&
                        rawResponse.getResponse().getGroups().get(0).getItems() != null &&
                        !rawResponse.getResponse().getGroups().get(0).getItems().isEmpty())
                {
                    for (Item item : rawResponse.getResponse().getGroups().get(0).getItems())
                    {
                        if (item.getVenue() != null)
                            result.getVenueList().add(item.getVenue());
                    }

                } else
                    return result;
            }
            else
                return result;

        } catch (Throwable t)
        {
            t.printStackTrace();
            result.setErrorMessage(t.getMessage());
        }
        result.setOk(true);
        return result;
    }
}
