package com.mkf.shoppingscreen.extras;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by Muhammed Kemal Patır on 13.07.2018.
 */

public class Extra {
    public static int getPxFromDp(float dp,Resources resources){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,resources.getDisplayMetrics());
    }
}
