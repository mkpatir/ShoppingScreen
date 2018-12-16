package com.mkf.shoppingscreen.extras;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.mkf.shoppingscreen.*;

/**
 * Created by Muhammed Kemal Patır on 23.07.2018.
 */

public class CustomToast extends Toast{
    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public CustomToast(Context context) {
        super(context);
    }

    public void setCustomView(LayoutInflater layoutInflater, ViewGroup viewGroup,String text){
        try{
            View view = null;
            view = layoutInflater.inflate(R.layout.custom_toast_layout,viewGroup);
            TextView textView = view.findViewById(R.id.custom_toast_text);
            textView.setText(text);
            setDuration(LENGTH_LONG);
            setView(view);
            show();
        }
        catch (Exception ex){

        }
    }
}
