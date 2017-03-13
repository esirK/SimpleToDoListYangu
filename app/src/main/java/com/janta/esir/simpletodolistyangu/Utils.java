package com.janta.esir.simpletodolistyangu;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * Created by esir on 13/03/2017.
 */
public class Utils {

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

}
