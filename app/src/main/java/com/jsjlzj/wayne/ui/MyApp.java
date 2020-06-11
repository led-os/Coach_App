package com.jsjlzj.wayne.ui;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.bumptech.glide.Glide;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.PublicConstant;
import com.jsjlzj.wayne.data.db.DBManager;
import com.jsjlzj.wayne.data.http.HttpManager;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.address.MalAddressProvince;
import com.jsjlzj.wayne.entity.store.MdlDict;
import com.jsjlzj.wayne.ui.mvp.address.BDAbsLocationListener;
import com.jsjlzj.wayne.ui.yunxin.MyNimSDKOptionConfig;
import com.jsjlzj.wayne.ui.yunxin.location.NimDemoLocationProvider;
import com.jsjlzj.wayne.ui.yunxin.preference.DemoCache;
import com.jsjlzj.wayne.ui.yunxin.preference.SessionHelper;
import com.jsjlzj.wayne.ui.yunxin.register.IMListener;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.Utility;
import com.lib.zxing.activity.ZXingLibrary;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.api.UIKitOptions;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.mixpush.MixPushConfig;
import com.netease.nimlib.sdk.util.NIMUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.core.content.ContextCompat;

import static com.jsjlzj.wayne.constant.HttpConstant.WXAPPID;


public class MyApp extends Application {
    public static MyApp ME;

    public static MdlUser.MdlUserBean user;//个人信息
    public static int idBindWeChat;//个人信息
    public static String positionId;//职位id
    public static String workHopeId;//求职意向id
    public static String workHopeName;//求职意向名称
    public static boolean isTrainer;
    public static MdlDict.DataBean mdlDict;//数据字典
    public static List<MalAddressProvince.DataBean.MalAddressProvice> provinceList;//地区
    public String dirPhoto;
    /**
     * 当前纬度
     */
    private float latitude;
    /**
     * 当前经度
     */
    private float longitude;
    /**
     * 当前城市
     */
    private String currentProvince;
    // 当前位置
    private String curPosition;
    private IWXAPI iwxapi;

    public static List<String> searchList = new ArrayList<>();
    public DisplayMetrics dm;


    public static MyApp getApp() {
        return ME;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getCurrentProvince() {
        return currentProvince;
    }

    public String getCurPosition() {
        return curPosition;
    }

    public IWXAPI getIwxapi() {
        return iwxapi;
    }

    // 百度地图定位
    private LocationClient locationClient;
    /**
     * 百度地图定位监听器
     */
    private BDAbsLocationListener bdListener = new BDAbsLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            latitude = (float) bdLocation.getLatitude();    //获取纬度信息
            longitude = (float) bdLocation.getLongitude();
            currentProvince = bdLocation.getCity();  // 获取当前城市
            curPosition = bdLocation.getAddrStr();
            LogAndToastUtil.log("latitude:" + latitude + "longitude:" + longitude + "currentProvince:" + currentProvince + "curPosition:" + curPosition);
            super.onReceiveLocation(bdLocation);
        }
    };

    //初始化百度配置信息
    public void initBdConfigure() {
        locationClient = new LocationClient(getApp()); //百度地圖聲明
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("gcj02");
        option.setScanSpan(0);
        option.setOpenGps(false);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(true);
        option.setWifiCacheTimeOut(5 * 60 * 1000);
        option.setEnableSimulateGps(false);
        option.setIsNeedAddress(true);
        locationClient.setLocOption(option);
        locationClient.registerLocationListener(bdListener);//注册百度监听函数
        locationClient.start();
    }

    public static MdlUser.MdlUserBean getUser() {
        return user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        long startTime = System.currentTimeMillis();
        initYunxing();
        //使用多进程时，只初始化一次
        if (TextUtils.equals(getPackageName(), Utility.getProcessName())) {
            ME = this;
            initBaiduMap();
            prepareSD();
            initBdConfigure();
            initWeixin();
//            user = SPUtil.getUserFromSP();
            initISNav();
            initDB();
            initRetrofit();
            initSmartRefreshLayout();
            initZXingDisplayOpinion();
            forGlobalException();
        }
    }


    private void initSmartRefreshLayout() {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColors(ContextCompat.getColor(context,R.color.color_999999), ContextCompat.getColor(context,R.color.color_333333));
            return new ClassicsHeader(context);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    private void initWeixin() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        iwxapi = WXAPIFactory.createWXAPI(this, WXAPPID, true);
        // 将应用的appId注册到微信
        iwxapi.registerApp(WXAPPID);
    }


    /**
     * 初始化图片选择器
     */
    private void initISNav() {
        ISNav.getInstance().init((ImageLoader) (context, path, imageView) -> Glide.with(context).load(path).into(imageView));
    }

    private void initYunxing() {
        // 4.6.0 开始，第三方推送配置入口改为 SDKOption#mixPushConfig，旧版配置方式依旧支持。
        NIMClient.init(this, loginInfo(), options());
        if (NIMUtil.isMainProcess(this)) {
//            //判断是否在主线程。
            IMListener.getInstance().init(this);
//            // 初始化
            NimUIKit.init(this);
            SessionHelper.init();
            NimUIKit.setLocationProvider(new NimDemoLocationProvider());

        }

    }


    private UIKitOptions buildUIKitOptions() {

        UIKitOptions options = new UIKitOptions();

        // 设置app图片/音频/日志等缓存目录

        options.appCacheDir = MyNimSDKOptionConfig.getAppCacheDir(this) + "/app";

        return options;

    }


    private void initBaiduMap() {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    private void initDB() {
        DBManager.getInstance().initDBManager(this);
    }

    private void initZXingDisplayOpinion() {
        ZXingLibrary.initDisplayOpinion(this);
    }

    private void initRetrofit() {
        HttpManager.getInstance().init();
    }

    private void forGlobalException() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {// 给主线程设置处理运行时异常的handler
            private int errCount = 0;

            @Override
            public void uncaughtException(Thread thread, final Throwable ex) {
                if (errCount > 0) {
                    stop();
                    return;
                }
                errCount++;
                ex.printStackTrace();
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                pw.close();
                StringBuilder sb = new StringBuilder();
                sb.append(Utility.getRunInfo());
                sb.append(sw.toString());
                // CallParameter<Integer> call = new
                // CallParameter<Integer>(null) {
                // @Override
                // public void onComplete(Integer result, Exception exception) {
                // if (exception != null) {
                // MyApp.log("写远程日志出�? \r\n%s", exception);
                // }
                // stop();
                // }
                // };
                try {
                    String from = "";
                    StackTraceElement[] arr = ex.getStackTrace();
                    for (StackTraceElement item : arr) {
                        String className = item.getClassName();
                        if (className.indexOf("mvp") != -1) {
                            int i = className.lastIndexOf(".");
                            if (i != -1) {
                                className = className.substring(i + 1);
                            }
                            from = String.format(Locale.US, "%s:%d %s.%s", item.getFileName(), item.getLineNumber(), className, item.getMethodName());
                            break;
                        }
                    }
                    String title = ex.getClass().getSimpleName() + ": " + ex.getMessage();

                    // RemoteThread.WriteLog(call, from, title, sb.toString());//
                    // 写入远程日志
                    stop();
                } catch (Exception e) {
                }
            }
        });
    }


    private void stop() {
        try {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    private void prepareSD() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String sdCardPath = Environment.getExternalStorageDirectory().toString();
            String basePath = PublicConstant.APP_NAME;
            basePath = sdCardPath + "/" + basePath;
            basePath = basePath.replace("//", "/");
            if (basePath.endsWith("/")) {
                basePath = basePath.substring(0, basePath.length() - 1);
            }
            dirPhoto = basePath + "/photo";
            File dir = new File(dirPhoto);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
    }


    // 如果已经存在用户登录信息，返回LoginInfo，否则返回null即可
    private LoginInfo loginInfo() {
//        String account = Preferences.getUserAccount();
//        String token = Preferences.getUserToken();
//
//        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(token)) {
//            DemoCache.setAccount(account.toLowerCase());
//            return new LoginInfo(account, token);
//        } else {
//            return null;
//        }
        return null;
    }

    /**
     * 配置 APP 保存图片/语音/文件/log等数据的目录
     * 这里示例用SD卡的应用扩展存储目录
     */
    static String getAppCacheDir(Context context) {
        String storageRootPath = null;
        try {
            // SD卡应用扩展存储区(APP卸载后，该目录下被清除，用户也可以在设置界面中手动清除)，请根据APP对数据缓存的重要性及生命周期来决定是否采用此缓存目录.
            // 该存储区在API 19以上不需要写权限，即可配置 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="18"/>
            if (context.getExternalCacheDir() != null) {
                storageRootPath = context.getExternalCacheDir().getCanonicalPath();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(storageRootPath)) {
            // SD卡应用公共存储区(APP卸载后，该目录不会被清除，下载安装APP后，缓存数据依然可以被加载。SDK默认使用此目录)，该存储区域需要写权限!
            storageRootPath = Environment.getExternalStorageDirectory() + "/" + DemoCache.getContext().getPackageName();
        }

        return storageRootPath;
    }

    // 如果返回值为 null，则全部使用默认参数。
    private SDKOptions options() {
        SDKOptions options = new SDKOptions();

        // 如果将新消息通知提醒托管给 SDK 完成，需要添加以下配置。否则无需设置。
        StatusBarNotificationConfig config = new StatusBarNotificationConfig();
        config.notificationEntrance = MainActivity.class; // 点击通知栏跳转到该Activity
        config.notificationSmallIconId = R.drawable.ic_stat_notify_msg;
        // 呼吸灯配置
        config.ledARGB = Color.GREEN;
        config.ledOnMs = 1000;
        config.ledOffMs = 1500;
        // 通知铃声的uri字符串
        config.notificationSound = "android.resource://com.netease.nim.demo/raw/msg";
        options.statusBarNotificationConfig = config;

        // 配置保存图片，文件，log 等数据的目录
        // 如果 options 中没有设置这个值，SDK 会使用采用默认路径作为 SDK 的数据目录。
        // 该目录目前包含 log, file, image, audio, video, thumb 这6个目录。
        String sdkPath = getAppCacheDir(this) + "/nim"; // 可以不设置，那么将采用默认路径
        // 如果第三方 APP 需要缓存清理功能， 清理这个目录下面个子目录的内容即可。
        options.sdkStorageRootPath = sdkPath;

        // 配置是否需要预下载附件缩略图，默认为 true
        options.preloadAttach = true;

        // 配置附件缩略图的尺寸大小。表示向服务器请求缩略图文件的大小
        // 该值一般应根据屏幕尺寸来确定， 默认值为 Screen.width / 2
        options.thumbnailSize = 480;
        options.mixPushConfig = buildMixPushConfig();

        // 用户资料提供者, 目前主要用于提供用户资料，用于新消息通知栏中显示消息来源的头像和昵称
//        options.userInfoProvider = new UserInfoProvider() {
//            @Override
//            public UserInfo getUserInfo(String account) {
//                return null;
//            }
//
//            @Override
//            public String getDisplayNameForMessageNotifier(String account, String sessionId,
//                                                           SessionTypeEnum sessionType) {
//                return null;
//            }
//
//            @Override
//            public Bitmap getAvatarForMessageNotifier(SessionTypeEnum sessionTypeEnum, String s) {
//                return null;
//            }
//        };
        return options;
    }

    private static MixPushConfig buildMixPushConfig() {
        // 第三方推送配置
        MixPushConfig config = new MixPushConfig();
        // 小米推送
        config.xmAppId = "101122327";
        config.xmAppKey = "b81faf3193fd9912399bc1efa128449c6c62399e1006d6e4323fe5660d4fa67e";
        config.xmCertificateName = "xiaomi";


        // 华为推送
        config.hwCertificateName = "huawei";


        // 魅族推送
        config.mzAppId = "3267623";
        config.mzAppKey = "q9wQUIr7zdny6VgmEaOPNE09CnbUeMPp";
        config.mzCertificateName = "meizu";

        // fcm 推送，适用于海外用户，不使用fcm请不要配置
//        config.fcmCertificateName = "DEMO_FCM_PUSH";


        // vivo推送
        config.vivoCertificateName = "vivo";

        // oppo推送
        config.oppoAppId = "30193053";
        config.oppoAppKey = "b886f6be2a4f46ed9f2a678c4004a448";
        config.oppoAppSercet = "8cf7ac4d05964bf3b3569764c3f3df00";
        config.oppoCertificateName = "oppo";
        return config;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ME = null;
        LogAndToastUtil.clearToast();
    }

    private DisplayMetrics getDm() {
        return dm;
    }
}
