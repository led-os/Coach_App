package com.jsjlzj.wayne.utils;

import android.app.ProgressDialog;
import android.content.Context;
import androidx.annotation.StringRes;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;


import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.publicac.dialog.CustomProgressFragment;
import com.jsjlzj.wayne.ui.publicac.dialog.LoadingFragment;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author zenghaiqiang
 */
public class LogAndToastUtil {
    // 等待加载进度条对话框的 集合
    private static HashMap<Class<?>, Stack<ProgressDialog>> dicWait;

    /**
     * 弹出提示 使用静态成员变量保证toast不延时
     */
    private static Toast toast;


    public static void toast(Context context,String s, Object... args) {
        if (args != null && args.length > 0) {
            s = String.format(s, args);
        }
        if(context==null)context= MyApp.ME.getApplicationContext();
        Toast.makeText(context,s+"",Toast.LENGTH_SHORT).show();
    }

    public static void toastL(Context context,String s, Object... args) {
        if (args != null && args.length > 0) {
            s = String.format(s, args);
        }
        if(context==null)context=MyApp.ME.getApplicationContext();
        Toast.makeText(context,s+"",Toast.LENGTH_LONG).show();
    }

    public static void toast(Context context,int resId, Object... args) {
        toast(context,ResourceUtil.getString(resId), args);
    }



    public static void toast(@StringRes int resId, Object... args) {
        toastShowLengthTime(ResourceUtil.getString(resId), Toast.LENGTH_SHORT, args);
    }

    public static void toast(String s, Object... args) {
        toastShowLengthTime(s, Toast.LENGTH_SHORT, args);
    }
    public static void toastL(String s, Object... args) {
        toastShowLengthTime(s, Toast.LENGTH_LONG, args);
    }








    private static void toastShowLengthTime(String s, int duration, Object... args) {

        if (args != null && args.length > 0) {
            s = String.format(s, args);
        }

        if (toast == null) {
            toast = Toast.makeText(MyApp.ME, "", duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }

        toast.setText(s);
        toast.show();
    }

    public static void clearToast() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }

    }


    /**
     * 打印日志
     */
    public static void log(String s, Object... args) {
        if (!HttpConstant.isDebug) return;
        if (args != null && args.length > 0) {
            s = String.format(s, args);
        }
        Log.d("--LP--", s);
    }

    public static void log(String tag, String msg) {
        if (!HttpConstant.isDebug) return;
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        msg += "";
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.d(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.d(tag, msg);
    }

    public static void logE(String tag, String msg) {
        if (!HttpConstant.isDebug) return;
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        msg += "";
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.d(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.e(tag, msg);
    }



    public static ProgressDialog showWait(Context context, String message) {
        return showWait(context, message, true);
    }

    public static ProgressDialog showWaitNoCanceledOutside(Context context, String message) {
        return showWait(context, message, false);
    }

    public static ProgressDialog showWait(Context context, String message, boolean canceledOutside) {
        if (context == null) {
            return null;
        }
        CustomProgressFragment pd = new CustomProgressFragment(context, R.style.CustomDialog);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCanceledOnTouchOutside(canceledOutside);
        pd.setMessage(message);
        pd.setIndeterminate(true);
        pd.setCancelable(true);
        pd.show();

        if (dicWait == null) {
            dicWait = new HashMap<>();
        }
        Stack<ProgressDialog> stack = dicWait.get(context.getClass());
        if (stack == null) {
            stack = new Stack<>();
            dicWait.put(context.getClass(), stack);
        }
        stack.push(pd);
        return pd;
    }

    public static void clearWait(Context context) {
        if (context == null) {
            dicWait.clear();
        } else {
            Stack<ProgressDialog> stack = dicWait.get(context.getClass());
            if (stack != null) {
                stack.clear();
            }
            dicWait.remove(context.getClass());
        }
    }


    public static void cancelWait(Context context) {
        try {
            if (context == null) {
                return;
            }
            Stack<ProgressDialog> stack = dicWait.get(context.getClass());
            if (stack != null && stack.size() > 0) {
                ProgressDialog pd = stack.pop();
                if (pd.isShowing()) {
                    pd.cancel();
                }
                if (stack.size() == 0) {
                    dicWait.remove(context.getClass());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
