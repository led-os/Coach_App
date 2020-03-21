package com.jsjlzj.wayne.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Desc
 * @Author zenghaiqiang
 * @CreateDate 2019/9/25 16:01
 * @UpdateUser
 * @UpdateDate
 * @UpdateRemark
 * @Version 1.0
 */
public class BitmapUtils {

    /**
     * bitmap 转byte
     */
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) bmp.recycle();
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Bitmap base64ToBitmap(String imgStr) {
//        // Base64解码
//        byte[] b = Base64.decodeBase64(imgStr.replaceFirst("data:image/.*;base64,", ""));
//        return BitmapFactory.decodeByteArray(b, 0, b.length);
        return null;
    }


    public static  void getBitmap(Context context, String uri, final GlideLoadBitmapCallback callback) {
        Glide.with(context)
                .asBitmap()
                .load(uri)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                        callback.getBitmapCallback(bitmap);
                    }

                });
    }

    public interface GlideLoadBitmapCallback{
        void getBitmapCallback(Bitmap bitmap);
    }


    /*
     *    get image from network
     *    @param [String]imageURL
     *    @return [BitMap]image
     */
    public static Bitmap returnBitMap(String url){
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
