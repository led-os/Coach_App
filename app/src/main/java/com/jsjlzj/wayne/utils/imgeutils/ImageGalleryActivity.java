//package com.jsjlzj.wayne.utils.imgeutils;
//
//import android.Manifest;
//import android.annotation.TargetApi;
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Point;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.text.TextUtils;
//import android.view.Display;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.load.model.GlideUrl;
//import com.bumptech.glide.load.model.LazyHeaders;
//import com.bumptech.glide.request.target.Target;
//import com.jsjlzj.wayne.R;
//import com.yuyh.library.imgsel.common.Constant;
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//import java.util.concurrent.Future;
//
//import androidx.annotation.NonNull;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.viewpager.widget.PagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//
///**
// * Created by nercita on 2016/11/15.
// */
//
//public class ImageGalleryActivity extends PhotoBaseActivity implements ViewPager.OnPageChangeListener {
//
//
//    @BindView(R.id.tv_title)
//    TextView tvTitle;
//    @BindView(R.id.btn_back)
//    ImageView btnBack;
//    @BindView(R.id.vp_image)
//    PreviewerViewPager vpImage;
//    private String[] mImageSources;
//    private int mCurPosition;
//    //保存照片的路径
//    private String protraitPath;
//    private File protraitFile;
//    private Uri origUri;
//    private Uri cropUri;
//    boolean isString = false;
//    private final static int CROP = 400;
//    private final static String FILE_SAVEPATH = Environment
//            .getExternalStorageDirectory().getAbsolutePath()
//            + "/nercita/BTHExtensionBeijing/";
//    private ViewPagerAdapter viewPagerAdapter;
//    private String  imageUrl;
//    private ImagePreviewView previewView;
//    private static String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };
//    private static final int REQUEST_EXTERNAL_STORAGE = 1;
//    private ImageView defaultView;
//    private static final String TAG = "ImageGalleryActivity";
//    private Bitmap bitmap;
//    private String path;
//    private String savePath;
//
//
//    @Override
//    protected int getContentView() {
//        return R.layout.activity_image_gallery;
//    }
//
//    @Override
//    protected void initWidget() {
//        super.initWidget();
//        requestPermission();
//        //初始化界面
//        initUI();
//        vpImage.addOnPageChangeListener(this);
//
//    }
//
//    /**
//     * 初始化界面
//     */
//    private void initUI() {
//        //显示菜单按钮,隐藏确定按钮
//        tvTitle.setText("照片详情");
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    @Override
//    protected void initData() {
//        super.initData();
//        //初始化页面数据
//        initIntentDate();
//
//        int len = mImageSources.length;
//        if (mCurPosition < 0 || mCurPosition >= len)
//            mCurPosition = 0;
//
//        // If only one, we not need the text to show
//
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
//        mImageSources = intent.getStringArrayExtra(ImageUtils.KEY_IMAGE);
//        SharePreferenceUtil.putString(Constant.SP_KEY_USER_PIC,mImageSources[0]);
//    }
//
//    /**
//     * 将图片保存
//     */
//    private void saveToFile() {
//        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            Toast.makeText(this, "路径不存在", Toast.LENGTH_SHORT).show();
//             MLog.i(TAG,"return" );
//            return;
//        }
//
//        final String path = "http://pic.4j4j.cn/upload/pic/20130617/55695c3c95.jpg";
//        final String savePath = Environment.getExternalStorageDirectory().getPath() + "/IACampusPic";
//
//        Object urlOrPath;
//        // Do load
//
//            urlOrPath = getGlideUrlByUser(path);
//
//
//        // In this save max image size is source
//        final Future<File> future = getImageLoader()
//                .load(urlOrPath)
//                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
//
//        AppOperator.runOnThread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    File sourceFile = future.get();
//                    if (sourceFile == null || !sourceFile.exists())
//                        return;
//                    String extension = PicturesCompressor.getExtension(sourceFile.getAbsolutePath());
////                    String extDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
////                            .getAbsolutePath() + File.separator + "FarmNurse";
//                    File extDirFile = new File(savePath);
//                    if (!extDirFile.exists()) {
//                        if (!extDirFile.mkdirs()) {
//                            // If mk dir error
//                            callSaveStatus(false, null);
//                            return;
//                        }
//                    }
//                    final File saveFile = new File(extDirFile, String.format("IMG_%s.%s", System.currentTimeMillis(), extension));
//                    final boolean isSuccess = StreamUtils.copyFile(sourceFile, saveFile);
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            callSaveStatus(isSuccess, saveFile);
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    private void requestPermission() {
//
//        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(
//                    this,
//                    PERMISSIONS_STORAGE,
//                    REQUEST_EXTERNAL_STORAGE
//            );
//        }
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED) {
//            //
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        doNext(requestCode, grantResults);
//    }
//    private void doNext(int requestCode, int[] grantResults) {
//        if (requestCode == 1) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission Granted
//
//            } else {
//                // Permission Denied
//                Toast.makeText(this, "请在应用管理中打开“相机”访问权限！", Toast.LENGTH_LONG).show();
//                finish();
//            }
//        }
//    }
//
//
//    private void callSaveStatus(boolean success, File savePath) {
//        if (success) {
//            Uri uri = Uri.fromFile(savePath);
//            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
//            Toast.makeText(ImageGalleryActivity.this, "保存图片成功" + uri, Toast.LENGTH_SHORT).show();
//             MLog.i(TAG,"picsave" + uri);
//        } else {
//            Toast.makeText(ImageGalleryActivity.this, "保存图片失败", Toast.LENGTH_SHORT).show();
//        }
//    }
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
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }
//
////    @OnClick({R.id.tv_back, R.id.iamge_commit, R.id.tv_commit,})
////    public void onClick(View view) {
////        switch (view.getId()) {
////            case R.id.tv_back:
////                //将本页面的数据传递到上一个页面
////                Intent intent = new Intent();
////                intent.putExtra(ImageUtils.KEY_COMMITDRAWER_IMAGE,imageUrl);
////                setResult(Activity.RESULT_OK,intent);
////                finish();
////                break;
////            case R.id.iamge_commit:
////                photoSelect();//进行选择图片的操作
////                break;
////        }
////    }
//
//    /**
//     * 弹出dialog选择处理方式
//     */
//    private void photoSelect() {
////        new ActionSheetDialog(this)
////                .builder()
////                .setCancelable(true)
////                .setCanceledOnTouchOutside(true)
////                .addSheetItem(getString(R.string.take_photo), ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() {
////                    @Override
////                    public void onClick(int which) {
////                        takephoto();
////                    }
////                }).addSheetItem(getString(R.string.select_camera_photo), ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() {
////            @Override
////            public void onClick(int which) {
////                startImagePick();
////            }
////        }).addSheetItem(getString(R.string.save_image), ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() {
////            @Override
////            public void onClick(int which) {
////                saveToFile();
////            }
////        }).show();
//    }
//
//    /**
//     * 选择相册中的图片
//     */
//    private void startImagePick() {
//        Intent albumIntent = new Intent(Intent.ACTION_PICK);//打开系统的相册
//        albumIntent.setType("image/*");
//        startActivityForResult(albumIntent, ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP);
//    }
//
//    /**
//     * 拍照
//     */
//    public void takephoto() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, this.getCameraTempFile());
//        startActivityForResult(intent,
//                ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA);
//    }
//
//
//    // 拍照保存的绝对路径
//    private Uri getCameraTempFile() {
//        String storageState = Environment.getExternalStorageState();
//        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
//            File savedir = new File(FILE_SAVEPATH);
//            if (!savedir.exists()) {
//                savedir.mkdirs();
//            }
//        } else {
//            Toast.makeText(this,"无法保存",Toast.LENGTH_SHORT).show();
//            return null;
//        }
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
//        //根据当前时间生成图片的名称
//        String timestamp = "/"+formatter.format(new Date())+".jpg";
//        // 照片命名
//        String cropFileName = "BTHExtensionBeijing_" + timestamp + ".jpeg";
//        // 裁剪头像的绝对路径
//        protraitPath = FILE_SAVEPATH + cropFileName;
//        protraitFile = new File(protraitPath);
//        cropUri = Uri.fromFile(protraitFile);
//        this.origUri = this.cropUri;
//        return this.cropUri;
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//
//            case ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA:
//                if (resultCode == -1) {
//                    startActionCrop(origUri);// 拍照后裁剪
//                }
//                break;
//            case ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP:
//                if (data != null && data.getData() != null) {
//                    startActionCrop(data.getData());// 选图后裁剪
//                }
//                break;
//            //选择图片后跳转到确认图片的界面,进行最后的再确认
//            case ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD:
//                //当用户没有剪裁图片就返回的时候,路径为原先的路径
//
//                Intent intent = new Intent(ImageGalleryActivity.this, ImageGalleryCommitActivity.class);
//                intent.putExtra(ImageUtils.KEY_IMAGEURL, protraitPath);
//                startActivityForResult(intent, ImageUtils.REQUEST_CODE_FRIST_TO_TWOIMAGE);
//                break;
//            //从上个页面拿回数据
//            case ImageUtils.REQUEST_CODE_FRIST_TO_TWOIMAGE:
//                if (resultCode == Activity.RESULT_OK) {
//                    //接受到上个页面传来本地图片的地址
//                    imageUrl = data.getStringExtra(ImageUtils.KEY_COMMIT_IMAGE);
//                    //更新页面
//                    viewPagerAdapter.loadImage(new File(imageUrl),previewView,defaultView,loading);
//                }else if (resultCode == 2){
//                //若果失败,则使用原先的图片
//                viewPagerAdapter.loadImage(ConstantsUrl.GET_PIC+ mImageSources[0],previewView,defaultView,loading);
//            }
//
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//
//    }
//
//    /**
//     * 拍照后裁剪
//     *
//     * @param data 原始图片
//     *             裁剪后图片
//     */
//    private void startActionCrop(Uri data) {
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(data, "image/*");
//        intent.putExtra("output", this.getUploadTempFile(data));
//        intent.putExtra("crop", "true");
//        intent.putExtra("aspectX", 1);// 裁剪框比例
//        intent.putExtra("aspectY", 1);
//        intent.putExtra("outputX", CROP);// 输出图片大小
//        intent.putExtra("outputY", CROP);
//        intent.putExtra("scale", true);// 去黑边
//        intent.putExtra("scaleUpIfNeeded", true);// 去黑边
//        startActivityForResult(intent,
//                ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD);
//    }
//
//
//    // 裁剪头像的绝对路径
//    private Uri getUploadTempFile(Uri uri) {
//        String storageState = Environment.getExternalStorageState();
//        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
//            File savedir = new File(FILE_SAVEPATH);
//            if (!savedir.exists()) {
//                savedir.mkdirs();
//            }
//        } else {
//            Toast.makeText(this,"保存图片并检查SD卡路径",Toast.LENGTH_SHORT).show();
//            return null;
//        }
//        String timeStamp = System.currentTimeMillis() + "";
//        String thePath = ImageUtils.getAbsolutePathFromNoStandardUri(uri);
//
//        // 如果是标准Uri
//        if (TextUtils.isEmpty(thePath)) {
//            thePath = ImageUtils.getAbsoluteImagePath(this, uri);
//        }
//        String ext = CommonUtils.getFileFormat(thePath);
//        ext = TextUtils.isEmpty(ext) ? "jpeg" : ext;
//        // 照片命名
//        String cropFileName = "BTHExtensionBeijing_" + timeStamp + "." + ext;
//        // 裁剪头像的绝对路径
//        protraitPath = FILE_SAVEPATH + cropFileName;
//        protraitFile = new File(protraitPath);
//
//        cropUri = Uri.fromFile(protraitFile);
//        return this.cropUri;
//    }
//
//    private class ViewPagerAdapter extends PagerAdapter implements ImagePreviewView.OnReachBorderListener {
//
//        private View.OnClickListener mFinishClickListener;
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
//            previewView = (ImagePreviewView) view.findViewById(R.id.iv_preview);
//            previewView.setOnReachBorderListener(this);
//            defaultView = (ImageView) view.findViewById(R.id.iv_default);
////                if (isString) {
////                    loadImage(new File(imageUrl),
////                            previewView, defaultView, loading);
////                }else {
////                    loadImage(ConstantsUrl.GET_PIC + mImageSources[position],
////                            previewView, defaultView, loading);
////                }
//
//
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
//                                   final ImageView defaultView,
//                                   final Loading loading) {
//
//            loadImageDoDownAndGetOverrideSize(urlOrPath, new DoOverrideSizeCallback() {
//                @Override
//                public void onDone(int overrideW, int overrideH, boolean isTrue) {
//                    DrawableRequestBuilder builder = getImageLoader()
//                            .load(urlOrPath)
//                            .listener(new RequestListener<T, GlideDrawable>() {
//                                @Override
//                                public boolean onException(Exception e,
//                                                           T model,
//                                                           Target<GlideDrawable> target,
//                                                           boolean isFirstResource) {
//                                    if (e != null)
//                                        e.printStackTrace();
//                                    loading.stop();
//                                    loading.setVisibility(View.GONE);
//                                    defaultView.setVisibility(View.VISIBLE);
//                                    return false;
//                                }
//
//                                @Override
//                                public boolean onResourceReady(GlideDrawable resource,
//                                                               T model,
//                                                               Target<GlideDrawable> target,
//                                                               boolean isFromMemoryCache,
//                                                               boolean isFirstResource) {
//                                    loading.stop();
//                                    loading.setVisibility(View.GONE);
//                                    return false;
//                                }
//                            }).diskCacheStrategy(DiskCacheStrategy.SOURCE);
//
//                    // If download or get option error we not set override
//                    if (isTrue && overrideW > 0 && overrideH > 0) {
//                        builder = builder.override(overrideW, overrideH);
//                    }
//
//                    builder.into(previewView);
//                }
//            });
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
//    public GlideUrl getGlideUrlByUser(String url) {
//        return new GlideUrl(url,
//                new LazyHeaders
//                        .Builder()
//                        .build());
//    }
//}
