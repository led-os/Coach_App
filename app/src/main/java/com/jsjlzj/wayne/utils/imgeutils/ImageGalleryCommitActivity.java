//package com.jsjlzj.wayne.utils.imgeutils;
//
//import android.annotation.TargetApi;
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.BitmapFactory;
//import android.graphics.Point;
//import android.os.Build;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Display;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.load.model.GlideUrl;
//import com.bumptech.glide.load.model.LazyHeaders;
//import com.bumptech.glide.request.target.Target;
//import com.jsjlzj.wayne.R;
//import com.jsjlzj.wayne.utils.GlidUtils;
//import com.jsjlzj.wayne.utils.SPUtil;
//import com.netease.nim.uikit.common.util.log.LogUtil;
//
//import java.io.File;
//import java.util.concurrent.Future;
//
//import androidx.viewpager.widget.PagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import okhttp3.Call;
//
//
///**
// * Created by nercita on 2016/11/15.
// */
//
//public class ImageGalleryCommitActivity extends PhotoBaseActivity implements ViewPager.OnPageChangeListener {
//
//
//    @BindView(R.id.vp_image)
//    PreviewerViewPager vpImage;
//    @BindView(R.id.tv_index)
//    TextView tvIndex;
//    @BindView(R.id.iv_save)
//    ImageView ivSave;
//    private String mImageUrl;
//
//    private int mCurPosition;
//    private ViewPagerAdapter viewPagerAdapter;
//    private Dialog dialog;
//    private static final String TAG = "ImageGalleryCommit";
//
//    @Override
//    protected int getContentView() {
//        return R.layout.activity_image_gallery1;
//    }
//
//    @Override
//    protected void initWidget() {
//        super.initWidget();
//        vpImage.addOnPageChangeListener(this);
//    }
//
//
//    @Override
//    protected void initData() {
//        super.initData();
//        //初始化页面数据
//        initIntentDate();
//        viewPagerAdapter = new ViewPagerAdapter();
//        vpImage.setAdapter(viewPagerAdapter);
//        vpImage.setCurrentItem(mCurPosition);
//        // First we call to init the TextView
//        onPageSelected(mCurPosition);
//    }
//
//    /**
//     * 初始化上个页面传来的数据
//     */
//    private void initIntentDate() {
//        Intent intent = getIntent();
//        mImageUrl = intent.getStringExtra(ImageUtils.KEY_IMAGEURL);
//        LogUtil.i(TAG, mImageUrl);
//        // /storage/emulated/0/nercita/BTHExtensionBeijing/BTHExtensionBeijing_1503048600277.jpg
//        // /storage/emulated/0/nercita/BTHExtensionBeijing/BTHExtensionBeijing_1503048637623.jpg
//    }
//
//
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//        mCurPosition = position;
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
//
//    private Point mDisplayDimens;
//
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
//    @SuppressWarnings("deprecation")
//    private synchronized Point getDisplayDimens() {
//        if (mDisplayDimens != null) {
//            return mDisplayDimens;
//        }
//        Point displayDimens;
//        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        Display display = windowManager.getDefaultDisplay();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//            displayDimens = new Point();
//            display.getSize(displayDimens);
//        } else {
//            displayDimens = new Point(display.getWidth(), display.getHeight());
//        }
//
//
//        mDisplayDimens = displayDimens;
//        return mDisplayDimens;
//    }
//
//
//    @OnClick({R.id.btn_back})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_back:
//                finish();
//                break;
//            default:
//                break;
//        }
//    }
//
//    /**
//     * 处理修改图片的数据响应
//     *
//     * @param response
//     */
//    private void parseImageData(String response) {
//        if ("1".equals(response)) {
//            //响应成功
//            Intent intent = new Intent();
//            intent.putExtra(ImageUtils.KEY_COMMIT_IMAGE, mImageUrl);//将选择好的图片返回
//            setResult(Activity.RESULT_OK, intent);
//            Toast.makeText(this, "修改成功!", Toast.LENGTH_SHORT).show();
//            finish();
//        } else {
//            //响应失败
//            Intent intent = new Intent();
//            setResult(2, intent);
//            Toast.makeText(this, "修改失败!", Toast.LENGTH_SHORT).show();
//            finish();
//
//        }
//        dialog.dismiss();
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//
//
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//
//    }
//
//
//    private class ViewPagerAdapter extends PagerAdapter implements ImagePreviewView.OnReachBorderListener {
//
//
//        @Override
//        public int getCount() {
//            return 1;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            View view = LayoutInflater.from(container.getContext())
//                    .inflate(R.layout.lay_gallery_page_item_contener, container, false);
//            ImagePreviewView previewView = (ImagePreviewView) view.findViewById(R.id.iv_preview);
//            previewView.setOnReachBorderListener(this);
//            ImageView defaultView = (ImageView) view.findViewById(R.id.iv_default);
//            GlidUtils.setGrid(ImageGalleryCommitActivity.this,list.get);
////
////            loadImage(new File(mImageUrl),
////                    previewView, defaultView);
////
//
//            container.addView(view);
//            return view;
//        }
//
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }
//
//        @Override
//        public void onReachBorder(boolean isReached) {
//            vpImage.isInterceptable(isReached);
//        }
//
//        private <T> void loadImage(final T urlOrPath,
//                                   final ImageView previewView,
//                                   final ImageView defaultView
//                                  ) {
//
//
//        }
//
//        private <T> void loadImageDoDownAndGetOverrideSize(final T urlOrPath, final DoOverrideSizeCallback callback) {
//            // In this save max image size is source
//            final Future<File> future = getImageLoader().load(urlOrPath)
//                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
//
//            AppOperator.runOnThread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        File sourceFile = future.get();
//
//                        BitmapFactory.Options options = PicturesCompressor.createOptions();
//                        // First decode with inJustDecodeBounds=true to check dimensions
//                        options.inJustDecodeBounds = true;
//                        // First decode with inJustDecodeBounds=true to check dimensions
//                        BitmapFactory.decodeFile(sourceFile.getAbsolutePath(), options);
//
//                        int width = options.outWidth;
//                        int height = options.outHeight;
//                        PicturesCompressor.resetOptions(options);
//
//                        if (width > 0 && height > 0) {
//                            // Get Screen
//                            final Point point = getDisplayDimens();
//
//                            // This max size
//                            final int maxLen = Math.min(Math.min(point.y, point.x) * 5, 1366 * 3);
//
//                            // Init override size
//                            final int overrideW, overrideH;
//
//                            if ((width / (float) height) > (point.x / (float) point.y)) {
//                                overrideH = Math.min(height, point.y);
//                                overrideW = Math.min(width, maxLen);
//                            } else {
//                                overrideW = Math.min(width, point.x);
//                                overrideH = Math.min(height, maxLen);
//                            }
//
//                            // Call back on main thread
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    callback.onDone(overrideW, overrideH, true);
//                                }
//                            });
//                        } else {
//                            // Call back on main thread
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    callback.onDone(0, 0, false);
//                                }
//                            });
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//
//                        // Call back on main thread
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                callback.onDone(0, 0, false);
//                            }
//                        });
//                    }
//                }
//            });
//        }
//
//
//    }
//
//    interface DoOverrideSizeCallback {
//        void onDone(int overrideW, int overrideH, boolean isTrue);
//    }
//
//
//    public GlideUrl getGlideUrlByUser(String url) {
//        return new GlideUrl(url,
//                new LazyHeaders
//                        .Builder()
//                        .build());
//    }
//
//
//}
