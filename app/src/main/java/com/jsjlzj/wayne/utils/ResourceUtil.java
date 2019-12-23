package com.jsjlzj.wayne.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.jsjlzj.wayne.ui.MyApp;


/**
 * Created by Administrator on 2018/4/16.
 */

public class ResourceUtil {
    public static String getString(@StringRes int stringId) {
        return MyApp.ME.getString(stringId);
    }

    public static int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(MyApp.ME, resId);
    }

    public static ColorStateList getColorStateList(@ColorRes int resId) {
        return ContextCompat.getColorStateList(MyApp.ME, resId);
    }

    public static Drawable getDrawable(@DrawableRes int id) {
        return ContextCompat.getDrawable(MyApp.ME, id);
    }

    public static int getDimension(@DimenRes int dimenId) {
        return MyApp.ME.getResources().getDimensionPixelOffset(dimenId);
    }

    public static void setCompoundDrawable(TextView tv, @DrawableRes int start,
                                           @DrawableRes int top, @DrawableRes int end, @DrawableRes int bottom) {
        if (tv == null) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tv.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        } else {
            Drawable leftD = null;
            Drawable topD = null;
            Drawable rightD = null;
            Drawable bottomD = null;
            if (start != 0) {
                leftD = getDrawable(start);
                setDrawableBound(leftD);
            }

            if (top != 0) {
                topD = getDrawable(top);
                setDrawableBound(topD);
            }

            if (end != 0) {
                rightD = getDrawable(end);
                setDrawableBound(rightD);
            }

            if (bottom != 0) {
                bottomD = getDrawable(bottom);
                setDrawableBound(bottomD);
            }

            tv.setCompoundDrawables(leftD, topD, rightD, bottomD);
        }
    }

    private static void setDrawableBound(Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }
}
