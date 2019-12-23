package com.jsjlzj.wayne.utils.shot;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;


import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

public class ShotUtil {

    /**
     * 获取指定Activity的截屏
     */
    public static Bitmap activityScreenShot(Activity activity) {
        // View是你需要截图的View
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        // 获取状态栏高度
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        // 获取屏幕长和高
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();

        // 去掉标题栏
        Bitmap b = Bitmap.createBitmap(bitmap, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();

        return b;
    }

    /**
     * 获取scrollview的截屏
     */
    public static Bitmap scrollViewScreenShot(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
        }
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h, Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }

    public static Bitmap viewConversionBitmap(View v) {
        int w = v.getWidth();
        int h = v.getHeight();

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);

        c.drawColor(Color.TRANSPARENT);
        /** 如果不设置canvas画布为白色，则生成透明 */

        v.layout(0, 0, w, h);
        v.draw(c);

        return bmp;
    }

    /**
     * 获取listview的截屏
     *
     * @param listview
     * @return
     */
    public static Bitmap shotListView(ListView listview) {
        ListAdapter adapter = listview.getAdapter();
        int itemscount = adapter.getCount();
        int allitemsheight = 0;
        List<Bitmap> bmps = new ArrayList<Bitmap>();

        //循环对listview的item进行截图， 最后拼接在一起
        for (int i = 0; i < itemscount; i++) {
            View childView = adapter.getView(i, null, listview);
            childView.measure(
                    View.MeasureSpec.makeMeasureSpec(listview.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
            childView.setDrawingCacheEnabled(true);
            childView.buildDrawingCache();

            bmps.add(childView.getDrawingCache());
            allitemsheight += childView.getMeasuredHeight();
            //这里可以把listview中单独的item进行保存
//            viewSaveToImage(childView.getDrawingCache());
        }
        int w = listview.getMeasuredWidth();
        Bitmap bigbitmap = Bitmap.createBitmap(w, allitemsheight, Bitmap.Config.ARGB_8888);
        Canvas bigcanvas = new Canvas(bigbitmap);

        Paint paint = new Paint();
        int iHeight = 0;

        for (int i = 0; i < bmps.size(); i++) {
            Bitmap bmp = bmps.get(i);
            bigcanvas.drawBitmap(bmp, 0, iHeight, paint);
            iHeight += bmp.getHeight();

            bmp.recycle();
            bmp = null;
        }
        return bigbitmap;
    }


    /**
     * recycleview截图
     *
     * @param view
     * @return
     */
    public static Bitmap shotRecyclerView(RecyclerView view) {
        RecyclerView.Adapter adapter = view.getAdapter();
        Bitmap bigBitmap = null;
        if (adapter != null) {
            int size = adapter.getItemCount();
            int height = 0;
            Paint paint = new Paint();
            int iHeight = 0;
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

            // Use 1/8th of the available memory for this memory cache.
            final int cacheSize = maxMemory / 8;
            LruCache<String, Bitmap> bitmaCache = new LruCache<>(cacheSize);
            for (int i = 0; i < size; i++) {
                RecyclerView.ViewHolder holder = adapter.createViewHolder(view, adapter.getItemViewType(i));
                adapter.onBindViewHolder(holder, i);
                holder.itemView.measure(
                        View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(),
                        holder.itemView.getMeasuredHeight());
                holder.itemView.setDrawingCacheEnabled(true);
                holder.itemView.buildDrawingCache();
                Bitmap drawingCache = holder.itemView.getDrawingCache();
                if (drawingCache != null) {

                    bitmaCache.put(String.valueOf(i), drawingCache);
                }
                height += holder.itemView.getMeasuredHeight();
            }

            bigBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), height, Bitmap.Config.ARGB_8888);
            Canvas bigCanvas = new Canvas(bigBitmap);
            Drawable lBackground = view.getBackground();
            if (lBackground instanceof ColorDrawable) {
                ColorDrawable lColorDrawable = (ColorDrawable) lBackground;
                int lColor = lColorDrawable.getColor();
                bigCanvas.drawColor(lColor);
            }

            for (int i = 0; i < size; i++) {
                Bitmap bitmap = bitmaCache.get(String.valueOf(i));
                bigCanvas.drawBitmap(bitmap, 0f, iHeight, paint);
                iHeight += bitmap.getHeight();
                bitmap.recycle();
            }
        }
        return bigBitmap;
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof NinePatchDrawable) {
            Bitmap bitmap = Bitmap
                    .createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                    : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        } else {
            return null;
        }
    }

    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }


    /**
     * 计算view的大小
     */
    public static void measureSize(Activity activity, String url) {
        //将布局转化成view对象
        View viewBitmap = new ImageView(activity);//LayoutInflater.from(activity).inflate(R.layout.logoimg_layout, null);

        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;

        //然后View和其内部的子View都具有了实际大小，也就是完成了布局，相当与添加到了界面上。接着就可以创建位图并在上面绘制了：
        layoutView(viewBitmap, width, height, url, activity);
    }

    /**
     * 填充布局内容
     */
    public static void layoutView(final View viewBitmap, int width, int height, String url, Activity activity) {
        // 整个View的大小 参数是左上角 和右下角的坐标
        viewBitmap.layout(0, 0, width, height);
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.AT_MOST);

        viewBitmap.measure(measuredWidth, measuredHeight);
        viewBitmap.layout(0, 0, viewBitmap.getMeasuredWidth(), viewBitmap.getMeasuredHeight());

        TextView tv = new TextView(activity);//viewBitmap.findViewById(R.id.tvLogo);
        tv.setText("这是后台生成图片的标题");
        viewSaveToImage(viewBitmap);

        final ImageView imageView = new ImageView(activity);//viewBitmap.findViewById(R.id.imLogo);

//        //注意加载网络图片时一定要用SimpleTarget回调
//        GlideApp.with(activity).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                imageView.setImageBitmap(resource);
//                viewSaveToImage(viewBitmap);
//            }
//        });
    }

    /**
     * 把view转成图片
     *
     * @param view
     */
    private static void viewSaveToImage(View view) {
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.setDrawingCacheBackgroundColor(Color.WHITE);

        // 把一个View转换成图片
        Bitmap cachebmp = viewConversionBitmap(view);

        if (drawableDoneListener != null) {
            drawableDoneListener.drawableDone(bitmap2Drawable(cachebmp));
        }

        view.destroyDrawingCache();
    }

    private static DrawableDoneListener drawableDoneListener;

    public static void setDrawableDoneListener(DrawableDoneListener drawableDoneListener) {
        ShotUtil.drawableDoneListener = drawableDoneListener;
    }

    public interface DrawableDoneListener {
        void drawableDone(Drawable drawable);
    }

    private static Drawable defaultDrawable;

    public static Drawable getDefaultDrawable(@NonNull Context context) {
        if (context instanceof Activity || context instanceof AppCompatActivity) {
            return getDefaultDrawable((Activity) context);
        }
        return defaultDrawable;
    }

    public static Drawable getDefaultDrawable(@NonNull Fragment fragment) {
        return getDefaultDrawable(fragment.getActivity());
    }

    public static Drawable getDefaultDrawable(@NonNull Activity activity) {
        if (defaultDrawable != null) return defaultDrawable;
        String logoNameFrom = SPUtil.getLogoNameFromSP();
        if (TextUtils.isEmpty(logoNameFrom))
            logoNameFrom = activity.getResources().getString(R.string.app_name);
        else
            logoNameFrom = parseCharSequence(logoNameFrom) + "·" + activity.getResources().getString(R.string.app_name);
        defaultDrawable = view2Drawable(activity, logoNameFrom);
        return defaultDrawable;
    }

    public static void setDefaultDrawable(@NonNull Context context, String logoName) {
        if (context instanceof Activity || context instanceof AppCompatActivity) {
            setDefaultDrawable((Activity) context, logoName);
        }
    }

    public static void setDefaultDrawable(@NonNull Fragment fragment, String logoName) {
        setDefaultDrawable(fragment.getActivity(), logoName);
    }

    public static void setDefaultDrawable(@NonNull Activity activity, String logoName) {
        if (logoName == null) return;
        if (SPUtil.getLogoNameFromSP().equals(logoName)) return;
        SPUtil.saveLogoName(logoName);
        if (TextUtils.isEmpty(logoName))
            logoName = activity.getResources().getString(R.string.app_name);
        else
            logoName = parseCharSequence(logoName) + "·" + activity.getResources().getString(R.string.app_name);
        defaultDrawable = view2Drawable(activity, logoName);
    }


    public static Drawable view2Drawable(Activity activity, CharSequence logoName) {
        //将布局转化成view对象
        Drawable drawable;
        View viewBitmap = new ImageView(activity);//LayoutInflater.from(activity).inflate(R.layout.logoimg_layout2, null);
//        TextView tv = viewBitmap.findViewById(R.id.tvLogo);
//        tv.getPaint().setFakeBoldText(true);
//        tv.setText(logoName);

        int width = 567;
        int height = 160;
        //然后View和其内部的子View都具有了实际大小，也就是完成了布局，相当与添加到了界面上。接着就可以创建位图并在上面绘制了：
        viewBitmap.layout(0, 0, width, height);
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.AT_MOST);

        viewBitmap.measure(measuredWidth, measuredHeight);
        viewBitmap.layout(0, 0, viewBitmap.getMeasuredWidth(), viewBitmap.getMeasuredHeight());
        LogAndToastUtil.log("getDefaultDrawable", "width:" + viewBitmap.getMeasuredWidth() + "   height:" + viewBitmap.getMeasuredHeight());


        viewBitmap.setDrawingCacheEnabled(true);
        viewBitmap.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        viewBitmap.setDrawingCacheBackgroundColor(Color.WHITE);
        // 把一个View转换成图片
        Bitmap cachebmp = viewConversionBitmap(viewBitmap);
        LogAndToastUtil.log("getDefaultDrawable", "cachebmp width:" + cachebmp.getWidth() + "   height:" + cachebmp.getHeight());
        drawable = bitmap2Drawable(cachebmp);
        viewBitmap.destroyDrawingCache();
        return drawable;
    }


    public static CharSequence parseCharSequence(CharSequence sequence) {
        StringBuffer buffer = new StringBuffer();
        int length = 0;
        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);
            if (Character.isDigit(c) || Character.isLowerCase(c) || Character.isUpperCase(c)) {
                length++;
            } else {
                length = length + 2;
            }
            if (length > 8) break;
            buffer.append(c);
        }
        return buffer;
    }
}
