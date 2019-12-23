package com.jsjlzj.wayne.utils;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.ClipboardManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.WebSettings;
import android.widget.TextView;

import com.jsjlzj.wayne.constant.PublicConstant;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.basis.LoginByPhoneActivity;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.AuthService;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class Utility {
    public static final long DAY_MS = 86400000;
    public static final long HOUR_8_MS = 28800000;


    public static byte int2byte(int num) {
        short v = (short) num;
        return (byte) (v & 0xFF);
    }

    public static String CONTEN_HIDE = "******";

    public static int byte2Uint(byte num) {
        return num & 0xFF;
    }

    /**
     * 获取手机IMEI号
     * <p>
     * 需要动态权限: android.permission.READ_PHONE_STATE
     */
    public static String getIMEI(Context context) {
        if (!TextUtils.isEmpty(SPUtil.getIMEIFromSP())) return SPUtil.getIMEIFromSP();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "35" +
                    Build.BOARD.length() % 10 +
                    Build.BRAND.length() % 10 +
                    Build.CPU_ABI.length() % 10 +
                    Build.DEVICE.length() % 10 +
                    Build.DISPLAY.length() % 10 +
                    Build.HOST.length() % 10 +
                    Build.ID.length() % 10 +
                    Build.MANUFACTURER.length() % 10 +
                    Build.MODEL.length() % 10 +
                    Build.PRODUCT.length() % 10 +
                    Build.TAGS.length() % 10 +
                    Build.TYPE.length() % 10 +
                    Build.USER.length() % 10; //13 digits;
        }
        String imei = telephonyManager.getDeviceId();
        if (TextUtils.isEmpty(imei)) {
            imei = "35" +
                    Build.BOARD.length() % 10 +
                    Build.BRAND.length() % 10 +
                    Build.CPU_ABI.length() % 10 +
                    Build.DEVICE.length() % 10 +
                    Build.DISPLAY.length() % 10 +
                    Build.HOST.length() % 10 +
                    Build.ID.length() % 10 +
                    Build.MANUFACTURER.length() % 10 +
                    Build.MODEL.length() % 10 +
                    Build.PRODUCT.length() % 10 +
                    Build.TAGS.length() % 10 +
                    Build.TYPE.length() % 10 +
                    Build.USER.length() % 10; //13 digits
        }

        SPUtil.saveIMEI2SP(imei);
        return imei;
    }

    /**
     * 获取版本号 版本名
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     */
    public static boolean isPad(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static boolean isHorizontal(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public static void copy(Context context, String s) {
        // 从API11开始android推荐使用android.content.ClipboardManager
        // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(s);
        LogAndToastUtil.toast(context, "复制成功:" + s);
    }

    /**
     * 跳转到桌面
     */
    public static void goToDesktop(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (context == null) context = MyApp.ME.getApplicationContext();
        context.startActivity(intent);
    }


    /**
     * 线程休眠
     *
     * @param ms 单位：毫秒
     */
    public static void ThreadSleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String byteArray2HexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format(" 0x%02X", b));
        }

        return sb.toString();
    }

    /**
     * 获取进程号对应的进程名
     *
     * @return 进程名
     */
    public static String getProcessName() {
        BufferedReader reader = null;
        try {
            int pid = android.os.Process.myPid();
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public static String getRunInfo() {

        try {
            PackageManager manager = MyApp.ME.getPackageManager();
            PackageInfo info = manager.getPackageInfo(MyApp.ME.getPackageName(), 0);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS", Locale.getDefault());

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Android版本: %s\r\n", Build.VERSION.RELEASE));
            sb.append(String.format("设备型号: %s\r\n", Build.MODEL));
            sb.append(String.format("%s版本: %s\r\n", PublicConstant.APP_NAME, info.versionName));
            sb.append(String.format("Android系统制作 %s\r\n", Build.BRAND));
            sb.append(String.format("时间: %s\r\n", sdf.format(new Date())));

            return sb.toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static long getStringToDate(int y, int m, int d) {
        long ts = 0;
        try {
            ts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(y + "-" + (m + 1) + "-" + d + " 00:00:00").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return ts;
        }
    }

    public static long getDayForMs(Long l) {
        LogAndToastUtil.log("getStatistic24", l + "");
        if (l == null) return 0;
        if ((l + HOUR_8_MS) % DAY_MS == 0) return l;
        return l / DAY_MS * DAY_MS - HOUR_8_MS;
    }


    public static String getUserAgent() {
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                userAgent = WebSettings.getDefaultUserAgent(MyApp.ME);
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static StringBuffer getSubCotent(String... strings) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < strings.length; i++) {
            buffer.append(strings[i]);
            if (i < strings.length - 1) buffer.append("\n");
        }
        return buffer;
    }

    public static StringBuffer getSubCotent(StringBuffer buffer, String... strings) {
        for (int i = 0; i < strings.length; i++) {
            buffer.append(strings[i]);
            if (i < strings.length - 1) buffer.append("\n");
        }
        return buffer;
    }


    public static Spannable getCustomSpan(String content, @DimenRes int fontSize) {
        Spannable span = new SpannableString(content);
        int len1 = content.length();
        span.setSpan(new AbsoluteSizeSpan(ResourceUtil.getDimension(fontSize)), 0, len1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return span;
    }

    public static Spannable getCustomSpan(@StringRes int resId, @DimenRes int fontSize) {
        String content = MyApp.ME.getString(resId);
        return getCustomSpan(content, fontSize);
    }


    public static Spannable getCommon2LinesSpan(String _1_line, String _2_line, @DimenRes int _1_fontSizeId, @DimenRes int _2_fontSizeId, @ColorRes int _1_colorId, @ColorRes int _2_colorId) {
        if (_2_line == null) _2_line = "";
        int len1 = _1_line.length();
        int len2 = len1 + _2_line.length();

        Spannable span = new SpannableString(_1_line + _2_line);
        span.setSpan(new AbsoluteSizeSpan(ResourceUtil.getDimension(_1_fontSizeId)), 0, len1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new AbsoluteSizeSpan(ResourceUtil.getDimension(_2_fontSizeId)), len1, len2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        span.setSpan(new ForegroundColorSpan(ResourceUtil.getColor(_1_colorId)), 0, len1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        span.setSpan(new ForegroundColorSpan(ResourceUtil.getColor(_2_colorId)), len1, len2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return span;
    }

    public static Spannable getCommon2LinesSpan(String _1_line, String _2_line, String _3_line, String _4_line) {
        Spannable span = new SpannableString(_1_line + _2_line + _3_line + _4_line);
        return span;
    }

    public static Spannable getColorString(@ColorRes int colorId, int[] indexs, String... strings) {
        StringBuffer buffer = new StringBuffer();
        int[] indexTemp = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            buffer.append(strings[0]);
            if (i < strings.length - 1) buffer.append("\n");
            indexTemp[i] = buffer.length();
        }
        Spannable span = new SpannableString(buffer);
        span.setSpan(new AbsoluteSizeSpan(12), 0, buffer.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        int temp = 0;
        for (int i = 0; i < indexs.length; i++) {
            if (indexs[i] >= strings.length) continue;
            if (indexs[i] == 0) {
            } else {

            }
        }
        return span;
    }


    public static String getEditTextStr(TextView editText) {
        if (editText == null) {
            throw new NullPointerException("--骚年，先别忙获取内容，你的EditText为null--");
        }
        return editText.getText().toString().trim();
    }

    public static DisplayMetrics getDisplayScreenSize(Activity context) {
        if (context == null) return null;
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    public static void recycleBitmap(Bitmap bmp) {
        if (bmp != null && !bmp.isRecycled()) {
            bmp.recycle();
            bmp = null;
        }
    }

    public static String getPhotoPath() {
        String imagePath = Utility.getDatePath(MyApp.ME.dirPhoto);
        String fileName = new SimpleDateFormat("dd_HHmmssSSS", Locale.getDefault()).format(new Date()) + ".jpg";
        imagePath = imagePath + "/" + fileName; // 相片储存的绝对路径

        return imagePath;
    }

    private static String getDatePath(String basePath) {
        if (basePath == null) {
            return null;
        }
        String path = basePath;
        if (basePath.endsWith("/") == false) {
            basePath += "/";
        }
        Calendar calendar = Calendar.getInstance();
        path = String.format(Locale.US, "%s%d/%02d", basePath, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    public static String getRemotePicUrlFromServer(String url) {
        return url;
        //return HttpConstant.BASE_URL + url;
    }

    public static String appendSet(Set<Long> set) {
        if (set == null || set.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (long id : set) {
            sb.append(id).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    public static void needLogin(Activity activity) {
        SPUtil.saveToken2SP("");
        SPUtil.saveYXAccountSP("");
        SPUtil.saveYXTokenSP("");
        SPUtil.saveUser2SP(null);
        Intent intent = new Intent(activity == null ?
                MyApp.ME.getApplicationContext() : activity,
                LoginByPhoneActivity.class);
        if (activity == null) {
            MyApp.ME.getApplicationContext().startActivity(intent);
        } else {
            activity.startActivity(intent);
            activity.finish();
        }
        NIMClient.getService(AuthService.class).logout();
        stopApp();
    }

    public static void stopApp() {
//        try {
//            TagAliasOperatorHelper.getInstance().delAlias(MyApp.ME, String.valueOf(MyApp.ME..id));
////            android.os.Process.killProcess(android.os.Process.myPid());
////            System.exit(0);
//            EventBusManager.post(new MdlEventBus(EnumEventBus.LOGOUT_OK));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static String myFormat(String format, Object... args) {
        return String.format(Locale.getDefault(), format, args);
    }

    public static Calendar getCalendarYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) - 1);//calendar.get(Calendar.DAY_OF_MONTH)-1
        return calendar;
    }

    public static String getTargetYesterday(Calendar calendar) {
        calendar.add(Calendar.DATE, -1);
        String s = myFormat("%d-%02d-%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        return s;
    }

    public static String getTargetToday(Calendar calendar) {
        String s = myFormat("%d-%02d-%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        return s;
    }

    public static String getTargetNextDay(Calendar calendar) {
        calendar.add(Calendar.DATE, +1);
        String s = myFormat("%d-%02d-%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        return s;
    }

    public static String getTargetYesterday() {
        return getTargetYesterday(Calendar.getInstance());
    }

    public static String getYMD(long mills) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(mills));
    }

    public static int[] getYMDfor24(long mills) {
        String ymd = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(mills));
        String[] s = ymd.split("-");
        return new int[]{Integer.parseInt(s[0]), Integer.parseInt(s[1]) - 1, Integer.parseInt(s[2])};
    }

    public static String getYMDHM(long mills) {
        if (mills <= 0) return "";
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date(mills));
    }

    public static String getYMD2(long mills) {
        if (mills <= 0) return "";
        return new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault()).format(new Date(mills));
    }

    public static String getMDHM(long mills) {
        if (mills <= 0) return "";
        return new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault()).format(new Date(mills));
    }

    public static String getHM(long mills) {
        if (mills <= 0) return "";
        return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(mills));
    }

    public static long date2TimeStamp(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean isOneDay(long time1, long time2) {
        if (time1 <= 0 || time2 <= 0) return true;
        return new SimpleDateFormat("MM-dd", Locale.getDefault()).format(new Date(time1)).equals(
                new SimpleDateFormat("MM-dd", Locale.getDefault()).format(new Date(time2))
        );
    }

    public static String getTimes(long minute) {
        String times = "";
        long m = minute % 60;
        long h = minute / 60 % 24;
        long d = minute / 60 / 24;
        if (d > 0) times += d + "天";
        if (h > 0) times += h + "时";
        if (m > 0) times += m + "分";
        return times.equals("") ? "0" : times;
    }

    public static String getOutputTime(long minute) {
        String times = "";
        long m = minute % 60;
        long h = minute / 60;
        if (h > 0) times += h + "时";
        if (m > 0) times += m + "分";
        return times.equals("") ? "0" : times;
    }

    public static String getHour4M(long minute) {
        String times = minute >= 0 ? "" : "-";
        minute = Math.abs(minute);
        long m = minute % 60;
        long h = minute / 60 % 24;
        if (h > 0) times += h + "时";
        if (m > 0) times += m + "分";
        return times.equals("") ? "0" : times;
    }

    public static String getTimes4Second2(long second) {
        String times = "";
        long s = second % 60;
        long m = second / 60 % 60;
        long h = second / 60 / 60 % 24;
        long d = second / 60 / 60 / 24;
        if (d > 0) times += d + "天";
        if (h > 0) times += h + "时";
        if (m > 0) times += m + "分";
        if (s > 0) times += s + "秒";
        return times.equals("") ? "0" : times;
    }

    public static String getTimes4Second(long second) {
        String times = "";
        long s = second % 60;
        long m = second / 60 % 60;
        long h = second / 60 / 60 % 24;
        long d = second / 60 / 60 / 24;
        if (d > 0) times += d + "天";
        if (h > 0) times += h + "时";
        if (m > 0) times += m + "分";
        if (s > 0) times += s + "秒";
        return times.equals("") ? "0" : times;
    }

    public static boolean isExceptionTime(long startTime) {
        if (startTime <= 0) return false;
        return (Math.abs(startTime - System.currentTimeMillis()) / 1000 / 60) > 5;
    }

    public static String getDistanceTime(long startTime) {
        if (startTime < 0) return "";

        return getDistanceTime(startTime, System.currentTimeMillis());
    }

    public static String getDistanceTime(long time1, long time2) {
        long diff = Math.abs(time2 - time1);
        diff = diff / 1000;
        long min = diff / 60 % 60;
        long hour = diff / 60 / 60 % 24;
        long day = diff / 60 / 60 / 24;
        long y = diff / 60 / 60 / 24 / 365;

        if (y != 0) return "";
        if (day != 0) return day + "天" + hour + "时" + min + "分";
        if (hour != 0) return hour + "时" + min + "分";
        if (min != 0) return min + "分";
        return "<1分";
    }

    public static boolean getDistanceTime5M(long startTime, long endTime) {
        if (startTime == 0 || endTime == 0) return false;
        long diff = Math.abs(endTime - startTime);
        return diff / 1000 / 60 >= 5;
    }

    public static boolean isNow(long startTime) {
        return startTime < System.currentTimeMillis();
    }

    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int getMaxWeekInMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 得到本月第一周在年的索引
     * 如 2018年9月第1周 在2018年是第35周
     */
    public static int getFirstWeekInMonthOfYear(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);

        int totalWeekInMonth = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        int currentWeekInYear = calendar.get(Calendar.WEEK_OF_YEAR);
        int currentWeekInMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        int minWeek = currentWeekInYear - currentWeekInMonth;
        Log.d("getWeekInMonthOfYear", "currentWeekInYear: " + currentWeekInYear + "  currentWeekInMonth:" + currentWeekInMonth);
        return minWeek;
    }


    public static String getYMDHMS(long mills) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(mills));
    }


    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static byte[] getBitmapArray(Context context, int drawable) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawable);
        int bytes = bitmap.getByteCount();
        ByteBuffer buf = ByteBuffer.allocate(bytes);
        bitmap.copyPixelsToBuffer(buf);
        return buf.array();
    }

    public static byte[] getBitmapArray(final Bitmap bmp, final boolean needRecycle) {
        int i;
        int j;
        if (bmp.getHeight() > bmp.getWidth()) {
            i = bmp.getWidth();
            j = bmp.getWidth();
        } else {
            i = bmp.getHeight();
            j = bmp.getHeight();
        }

        Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.RGB_565);
        Canvas localCanvas = new Canvas(localBitmap);

        while (true) {
            localCanvas.drawBitmap(bmp, new Rect(0, 0, i, j), new Rect(0, 0, i, j), null);
            if (needRecycle)
                bmp.recycle();
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            localBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    localByteArrayOutputStream);
            localBitmap.recycle();
            byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
            try {
                localByteArrayOutputStream.close();
                return arrayOfByte;
            } catch (Exception e) {
                // F.out(e);
            }
            i = bmp.getHeight();
            j = bmp.getHeight();
        }
    }

    public static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    /**
     * 微信分享
     */

    /**
     * double to string
     */
    public static DecimalFormat decimalFormat;

    public static String double2String(Double num) {
        if (num == null) return "";
        if (Math.round(num) - num == 0) {
            return String.valueOf((long) (double) num);
        }
        if (decimalFormat == null) decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(num);
    }

    public static String double2String(boolean isBig, Double num) {
        if (num == null) return "";
        if (Math.round(num) - num == 0 || !isBig) {
            return String.valueOf((long) (double) num);
        }
        if (decimalFormat == null) decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(num);
    }

    public static double double2Double(Double num) {
        if (num == null) return 0;
        if (decimalFormat == null) decimalFormat = new DecimalFormat("0.00");
        return Double.parseDouble(decimalFormat.format(num));
    }


    public static String getOutputTime(double machinesTime, int changersTime) {
        return double2String((machinesTime / 60 + changersTime) / 60);
    }

    /**
     * 预计结束时间段
     */


    public static long getendTime(double amount, double singleProcessingAmount,
                                  int processingTime) {
        if (amount <= 0 || singleProcessingAmount <= 0) return System.currentTimeMillis();
        return System.currentTimeMillis() + (long) (Math.ceil(amount / singleProcessingAmount) * processingTime * 1000);
    }

    public static long getendTime(long lastTime, double amount, double singleProcessingAmount,
                                  int processingTime) {
        if (amount <= 0 || singleProcessingAmount <= 0) return lastTime;
        return lastTime + (long) (Math.ceil(amount / singleProcessingAmount) * processingTime * 1000);
    }

    /**
     * 获取assent文件
     */
    public static AssetFileDescriptor descriptor;

    public static AssetFileDescriptor getAssentFile(Context context, String filePath) {
        try {
            if (descriptor != null) return descriptor;
            descriptor = context.getAssets().openFd(filePath);
            return descriptor;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}