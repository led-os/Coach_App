package com.jsjlzj.wayne.utils;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.jsjlzj.wayne.R;

/**
 *
 * @author zenghaiqiang
 * @date 2019/1/21
 * 图片加载工具类
 */
public class GlidUtils {


    //普通的图片
    public static void setGrid(Context context, String url, ImageView view) {

        Glide
                .with(context)
                .load(url)
                .placeholder(R.drawable.ic_default_image)
                .error(R.drawable.ic_default_image)
                .into(view);
    }


    //普通的图片
    public static void setGrid(Context context, int url, ImageView view) {
        Glide
                .with(context)
                .load(url)
                .placeholder(R.drawable.ic_default_image)
                .error(R.drawable.ic_default_image)
                .into(view);
    }

    //普通的图片
    public static void setGrid(Context context, String url, ImageView view,int errorRes) {
        Glide
                .with(context)
                .load(url)
                .placeholder(errorRes)
                .error(errorRes)
                .into(view);
    }

    /**
     * 设置圆角图片
     * @param context
     * @param url
     * @param view
     * @param round dp
     */
    public static void setRoundGrid(Context context, String url, ImageView view,int round) {
        Glide
                .with(context)
                .load(url)
                .error(R.drawable.ic_default_image)
                .apply(new RequestOptions().transform(new GlideRoundTransform(round)))
                .into(view);
    }

    //圆型图片
    public static void setCircleGrid(Context context, String url, ImageView view) {
        Glide
                .with(context)
                .load(url)
                .error(R.drawable.ic_avatars)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(view);
    }


}


