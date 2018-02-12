package com.ozturkburak.nearp.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.ozturkburak.nearp.R;

/**
 * Created by burak on 2/11/18.
 */

public class CommonUtils
{

    public static void ShowSnackbar(View container, final String text , int duration  ,final String buttonText , final View.OnClickListener listener)
    {
        if (container == null)
            return;

        Snackbar snackbar = Snackbar.make(container, text, duration);
        if (listener != null){
            snackbar.setAction(buttonText, listener);
            snackbar.setActionTextColor(container.getContext().getResources().getColor(R.color.materialYellow));
        }

        snackbar.show();

    }
}
