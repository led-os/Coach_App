package com.jsjlzj.wayne.utils.imagewatcher;

import android.app.Activity;
import android.net.Uri;
import android.os.Environment;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hzdc.picture.shopping.App;
import com.hzdc.picture.shopping.R;
import com.hzdc.picture.shopping.utils.ToastUtils;

import java.io.File;
import java.util.List;

import static com.hzdc.picture.shopping.view.imagewatcher.GlideSaveBitmapAsyncTask.FOLDER_NAME;

/**
 * api 图片详情加载帮助类
 */
public class ImageWatcherHelper implements ImageWatcher.backSaveCallback {
    private static final int VIEW_IMAGE_WATCHER_ID = R.id.view_image_watcher;
    private final Activity holder;
    private final ViewGroup activityDecorView;
    private ImageWatcher mImageWatcher;
    private ImageWatcher.Loader loader;
    private Integer statusBarHeight;
    private Integer resErrorImage;
    private ImageWatcher.OnPictureLongPressListener listener;
    private ImageWatcher.IndexProvider indexProvider;
    private ImageWatcher.LoadingUIProvider loadingUIProvider;
    private ImageWatcher.OnStateChangedListener onStateChangedListener;
    private String TAG = "ImageWatcherHelper";
    public String fileName;

    private ImageWatcherHelper(Activity activity) {
        holder = activity;
        activityDecorView = (ViewGroup) activity.getWindow().getDecorView();
    }



    public static ImageWatcherHelper with(Activity activity, ImageWatcher.Loader l) { // attach
        if (activity == null) throw new NullPointerException("activity is null");
        if (l == null) throw new NullPointerException("loader is null");
        ImageWatcherHelper iwh = new ImageWatcherHelper(activity);
        iwh.loader = l;
        return iwh;
    }

    public ImageWatcherHelper setTranslucentStatus(int statusBarHeight) {
        this.statusBarHeight = statusBarHeight;
        return this;
    }

    public ImageWatcherHelper setErrorImageRes(int resErrorImage) {
        this.resErrorImage = resErrorImage;
        return this;
    }

    public ImageWatcherHelper setOnPictureLongPressListener(ImageWatcher.OnPictureLongPressListener listener) {
        this.listener = listener;
        return this;
    }

    public ImageWatcherHelper setIndexProvider(ImageWatcher.IndexProvider ip) {
        indexProvider = ip;
        return this;
    }

    public ImageWatcherHelper setLoadingUIProvider(ImageWatcher.LoadingUIProvider lp) {
        loadingUIProvider = lp;
        return this;
    }

    public ImageWatcherHelper setOnStateChangedListener(ImageWatcher.OnStateChangedListener listener) {
        onStateChangedListener = listener;
        return this;
    }

    public void show(ImageView i, SparseArray<ImageView> imageGroupList, List<Uri> urlList, String fileName) {
        init();
        this.fileName = fileName;
        mImageWatcher.show(i, imageGroupList, urlList);

    }

    public void show(List<Uri> urlList, int initPos) {
        init();
        mImageWatcher.show(urlList, initPos);
    }

    private void init() {
        mImageWatcher = new ImageWatcher(holder);
        mImageWatcher.setId(VIEW_IMAGE_WATCHER_ID);
        mImageWatcher.setLoader(loader);
        mImageWatcher.setBackSaveCallback(this);
        mImageWatcher.setDetachAffirmative(); // helper
        if (statusBarHeight != null) mImageWatcher.setTranslucentStatus(statusBarHeight);
        if (resErrorImage != null) mImageWatcher.setErrorImageRes(resErrorImage);
        if (listener != null) mImageWatcher.setOnPictureLongPressListener(listener);
        if (indexProvider != null) mImageWatcher.setIndexProvider(indexProvider);
        if (loadingUIProvider != null) mImageWatcher.setLoadingUIProvider(loadingUIProvider);
        if (onStateChangedListener != null)
            mImageWatcher.setOnStateChangedListener(onStateChangedListener);

        removeExistingOverlayInView(activityDecorView); // 理论上是无意义的操作。在ImageWatcher 'dismiss' 时会移除自身。但检查一下不错
        activityDecorView.addView(mImageWatcher);
    }

    @Override
    public void save() {
        String url = mImageWatcher.getDisplayingUri().toString();
        if (checkFileExist(url)) {
            ToastUtils.showToastCenter(App.getApp().getApplicationContext(), holder.getString(R.string.save_to_sdcard) + Environment.getExternalStorageDirectory().getAbsolutePath() + FOLDER_NAME + "/" + getImgName(url));
        } else {
            GlideSaveBitmapAsyncTask asyncTask = new GlideSaveBitmapAsyncTask(holder, mImageWatcher.getDisplayingUri().toString(),fileName);
            asyncTask.execute();
        }
    }

    @Override
    public void back() {
        handleBackPressed();
    }

    private boolean handleBackPressed() {
        return mImageWatcher != null && mImageWatcher.handleBackPressed();
    }

    private void removeExistingOverlayInView(ViewGroup parent) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            if (child.getId() == VIEW_IMAGE_WATCHER_ID) {
                parent.removeView(child);
            }
            if (child instanceof ViewGroup) {
                removeExistingOverlayInView((ViewGroup) child);
            }
        }
    }

    public interface Provider {
        ImageWatcherHelper iwHelper();
    }


    private boolean checkFileExist(String url) {
        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + FOLDER_NAME);
        if (!folder.exists()) {
            //文件夹不存在
            folder.mkdir();
            return false;
        }
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + FOLDER_NAME + "/" + getImgName(url));
        if (file.exists()) {
            return true;
        }
        return false;
    }

    private String getImgName(String url) {
        return fileName;
    }
}
