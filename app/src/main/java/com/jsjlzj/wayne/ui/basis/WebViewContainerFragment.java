package com.jsjlzj.wayne.ui.basis;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.alibaba.fastjson.JSONObject;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.constant.MyPermissionConstant;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.utils.JsInterface;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SPUtil;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;
import static android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE;

/**
  *
  * @ClassName:      WebViewContainerFragment
  * @Description:    web页面
  * @Author:         曾海强
  * @CreateDate:     2020/02/26
  */
public class WebViewContainerFragment extends MVPBaseFragment<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    @BindView(R.id.fragment_webview)
    public WebView mWebView;
    private int type;
    private String firstUrl;

     /** 视频全屏参数 */
     protected static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
     public View customView;
     private FrameLayout fullscreenContainer;
     private WebChromeClient.CustomViewCallback customViewCallback;

    public static final int TYPE_BANNER_LINK_URL = 0;
    public static final int TYPE_COURSE_DETAIL = 1;
    public static final int TYPE_DYNAMIC_DETAIL = 2;
    public static final int TYPE_USER_INFO = 3;
    public static final int TYPE_ARTICLE_DETAIL = 4;
    public static final int TYPE_SCHOOL_DETAIL = 5;
    public static final int TYPE_MATCH_DETAIL = 6;
    public static final int TYPE_PRODUCT_DETAIL = 7;
    public static final int TYPE_COURSE_INTRODUCE = 8;
    public static final int TYPE_PRIVATE_POLICY = 9;
    public static final int TYPE_NEW_COURSE_DETAIL = 10;
    public static final int TYPE_NEW_TEACHER_HOME = 11;
    public static final int TYPE_NEW_SHOPPING_DETAIL = 12;
    public static final int TYPE_NEW_MEMBER_CENTER = 13;
    public static final int TYPE_NEW_DAY_SIGN = 14;
    public static final int TYPE_SCAN_SCORE = 15;
    public static final int TYPE_PROFIT = 17;
    public static final int TYPE_CLUBE_DETAIL = 18;
    public static final int TYPE_TUAN_DETAIL = 19;
    public static final int TYPE_TRAINER_DETAIL = 20;

    public static final int TYPE_PLAY_VIDEO = 16;
     /**
      * 内部跳转
      */
    public static final int TYPE_INNER_TAB = 997;
    private static final int REQUEST_CODE = 998;

    public static WebViewContainerFragment getInstance(Bundle bundle) {
        WebViewContainerFragment fragment = new WebViewContainerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


     @Override
     protected int getLayoutResId() {
         return R.layout.fragment_webview_container;
     }

     @Override
     protected void initViewAndControl(View view) {
         Bundle arguments = getArguments();
         if (arguments != null) {
             firstUrl = arguments.getString(ExtraConstant.EXTRA_WEB_URL);
             type = arguments.getInt(ExtraConstant.EXTRA_WEB_TYPE);
         }
         System.out.println("==========webView"+firstUrl);
         initWebView();
         if (firstUrl != null) {
             // 避免出现在 webview 还没加载完成时就加载网页导致出现js获取的宽高为0的情况
             mWebView.post(() -> mWebView.loadUrl(firstUrl));
         }
     }

     @Override
     protected void fragment2Front() {
        LogAndToastUtil.log("====="+firstUrl+"======"+SPUtil.getWebRefresh());
        if(firstUrl.contains(HttpConstant.WEB_URL_NEW_MEMBER_CENTER ) || firstUrl.contains(HttpConstant.WEB_URL_NEW_COURSE_DETAIL)){

        }
     }

     @Override
     protected TalentTabFragmentPresenter createPresenter() {
         return new TalentTabFragmentPresenter(this);
     }

     @Override
     public void onStop() {
         super.onStop();
         mWebView.reload();
     }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setTextZoom(100);

        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setSupportZoom(false);
        // 解决图片不显示
        webSettings.setBlockNetworkImage(false);
        // support zoom
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDefaultTextEncodingName("UTF-8");
        // 自适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        // 如果是内部网页，设置背景透明
//        if (type != TYPE_OUT_URL) {
//            mWebView.setBackgroundColor(Color.TRANSPARENT);
//        }
        //添加客户端支持
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                hideEmpty();
                hideLoading();
                // 当第一次加载本地文件完成时，显示 mWebView，并执行对应 js
                if (firstUrl.equals(url)) {
                    mWebView.setVisibility(View.VISIBLE);
                    switch (type) {
                        default:
                            setToken();
                            break;
                    }
                }
                super.onPageFinished(view, url);
            }

            @Override
            public void onLoadResource(WebView webView, String s) {
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                showEmpty(R.id.web_container, 2, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mWebView.reload();
//                    }
//                });
                super.onReceivedError(view, request, error);
            }
        });
        mWebView.setWebChromeClient(mWebChromeClient);
        mWebView.addJavascriptInterface(new JsInterface(getActivity()), "AndroidWebView");
    }

    public void reload() {
        if (mWebView != null) {
            mWebView.reload();
        }
    }


    private void setToken() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        mWebView.loadUrl("javascript: setToken('" + SPUtil.getTokenFromSP() + "')");
        LogAndToastUtil.log("====detail_token===="+SPUtil.getTokenFromSP());
    }


    public void toShare() {
        mWebView.loadUrl("javascript: appBridgeShare()");
        LogAndToastUtil.log("====detail_token===="+SPUtil.getTokenFromSP());
    }

    public void endInput(String str){
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl("javascript: endInput('"+ str +"')");
            }
        });

    }

    public static JSONObject getBaseJSONObject() {
        JSONObject json = new JSONObject();
//        json.put("token", DataCacheManager.getToken());
//        json.put("deviceId", AppLike.getSelf().getDeviceId());
//        json.put("version", CommonUtils.getVersionName());
//        json.put("osType", "1");
//        json.put("host", BuildConfig.API_HOST);
//        json.put("dpi", "1");
        return json;
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        switch (config.orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                break;
        }
    }

    // 发送通用字段
    private WebChromeClient mWebChromeClient = new WebChromeClient() {

        /*** 视频播放相关的方法 **/

        @Override
        public View getVideoLoadingProgressView() {
            FrameLayout frameLayout = new FrameLayout(getActivity());
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return frameLayout;
        }

        @SuppressLint("SourceLockedOrientationActivity")
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            showCustomView(view, callback);
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        @SuppressLint("SourceLockedOrientationActivity")
        @Override
        public void onHideCustomView() {
            hideCustomView();
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }


        // 处理javascript中的alert
        @Override
        public boolean onJsAlert(WebView webView, String s, String s1, JsResult jsResult) {
            return super.onJsAlert(webView, s, s1, jsResult);
        }

        // 处理javascript中的confirm
        @Override
        public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

        // 处理javascript中的prompt
        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        // 设置网页加载的进度条
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress > 90) {
                hideLoading();
            }
            super.onProgressChanged(view, newProgress);
        }

        // 设置程序的Title
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    };



     /** 视频播放全屏 **/
     private void showCustomView(View view, WebChromeClient.CustomViewCallback callback) {
         // if a view already exists then immediately terminate the new one
         LogAndToastUtil.log("=========showCustomView");
         if (customView != null) {
             callback.onCustomViewHidden();
             return;
         }

         getActivity().getWindow().getDecorView();

         FrameLayout decor = (FrameLayout) getActivity().getWindow().getDecorView();
         fullscreenContainer = new FullscreenHolder(getActivity());
         fullscreenContainer.addView(view, COVER_SCREEN_PARAMS);
         decor.addView(fullscreenContainer, COVER_SCREEN_PARAMS);
         customView = view;
         setStatusBarVisibility(false);
         customViewCallback = callback;
     }

     /** 隐藏视频全屏 */
     public void hideCustomView() {
         LogAndToastUtil.log("=========hideCustomView");
         if (customView == null) {
             return;
         }

         setStatusBarVisibility(true);
         FrameLayout decor = (FrameLayout)getActivity().getWindow().getDecorView();
         decor.removeView(fullscreenContainer);
         fullscreenContainer = null;
         customView = null;
         customViewCallback.onCustomViewHidden();
         mWebView.setVisibility(View.VISIBLE);
     }

     /** 全屏容器界面 */
     static class FullscreenHolder extends FrameLayout {

         public FullscreenHolder(FragmentActivity ctx) {
             super(ctx);
             setBackgroundColor(ctx.getResources().getColor(android.R.color.black));
         }

         @Override
         public boolean onTouchEvent(MotionEvent evt) {
             return true;
         }
     }

     private void setStatusBarVisibility(boolean visible) {
         int flag = visible ? 0 : WindowManager.LayoutParams.FLAG_FULLSCREEN;
         getActivity().getWindow().setFlags(flag, WindowManager.LayoutParams.FLAG_FULLSCREEN);
     }


    private long lastJumpTime;

    /**
     * 跳转 activity
     */
    private void jumpActivity(JSONObject jsonObject) {

        // 避免重复跳转
        if (System.currentTimeMillis() - lastJumpTime < 500) {
            return;
        }
        lastJumpTime = System.currentTimeMillis();

//        try {
//            String destPage = jsonObject.getString(Constant.JS_ACTION_DEST_PAGE);
//            String title = jsonObject.getString(Constant.TITLE);
//            JSONObject data = jsonObject.getJSONObject("data");
//            MLog.e("====zhq====>aaaa<"+data.getString(Constant.URL));
//            switch (destPage) {
//                case Constant.ACTION_JUMP_TO_ORI_TEXT://跳转原文
//                    String url = data.getString(Constant.URL);
//                    if (url.endsWith("pdf") || url.endsWith("doc") || url.endsWith("docx") || url.endsWith("xls") || url.endsWith("xlsx")) {
//                        Intent intent = WebViewContainerActivity.startActivity(getActivity(), "原文", url, TYPE_PDF_DETAIL, null);
//                        startActivity(intent);
//                    } else if (!TextUtils.isEmpty(url)) {
//                        Intent intent = WebViewContainerActivity.startActivity(getActivity(), "原文", url, TYPE_READ_TEXT, null);
//                        startActivity(intent);
//                    }
//                    break;
//                case Constant.ACTION_JUMP_TO_PDF:
//                    String pdfUrl = data.getString("pdfUrl");
//                    if (!TextUtils.isEmpty(pdfUrl)) {
//                        Intent intent = WebViewContainerActivity.startActivity(getActivity(), "附件", pdfUrl, TYPE_PDF_DETAIL, null);
//                        startActivity(intent);
//                    } else {
//                        String rowkey = data.getString("pdfRowkey");
//                        String fileUrl = Constant.URL_FILE + rowkey;
//                        if (!TextUtils.isEmpty(fileUrl)) {
//                            String type = data.getString("type");
//                            if (!TextUtils.isEmpty(type) && type.contains("/")) {
//                                type = type.substring(type.lastIndexOf("/") + 1);
//                            }
//                            Intent intent = WebViewContainerActivity.startActivity(getActivity(), "附件", fileUrl, TYPE_PDF_DETAIL, type);
//                            startActivity(intent);
//                        }
//                    }
//
//                    break;
//                case Constant.ACTION_JUMP_TO_RELATION_MAP:
//                    String rowkey = data.getString(Constant.ROWKEY);
//                    if (!TextUtils.isEmpty(rowkey)) {
//                        Intent intent = WebViewContainerActivity.startActivity(getActivity(), getString(R.string.relative_map),
//                                Constant.WEB_URL_MAP_RELATION, TYPE_CPY_MAP, rowkey);
//                        startActivity(intent);
//                    }
//                    break;
//                case Constant.ACTION_JUMP_INFORMATION_DETAIL:
//                case "11":
//                    String infoRowkey = data.getString(Constant.ROWKEY);
//                    if (!TextUtils.isEmpty(infoRowkey)) {
//                        new DefaultUriRequest(getActivity(), RouterCons.NEWS_DETAIL)
//                                .putExtra(Constant.ROWKEY, infoRowkey)
//                                .start();
//                    }
//                    break;
//                case Constant.ACTION_SEARCH_BOND_DETAIL:
//                    String bondCode = data.getString(Constant.BOND_CODE);
//                    if (!TextUtils.isEmpty(bondCode)) {
//                        new DefaultUriRequest(getContext(), RouterCons.BOND_DETAIL)
//                                .activityRequestCode(REQUEST_CODE)
//                                .putExtra(Constant.ROWKEY, bondCode)
//                                .start();
//                    }
//                    break;
//                case ACTION_SEARCH_CPY_DETAIL:
//                    String anyCpyNo = data.getString("anyCpyNo");
//                    if (!TextUtils.isEmpty(anyCpyNo)) {
//                        new DefaultUriRequest(getActivity(), RouterCons.CPY_DETAIL)
//                                .activityRequestCode(REQUEST_CODE)
//                                .putExtra(Constant.ROWKEY, anyCpyNo)
//                                .start();
//                    }
//                    break;
//                case ACTION_JUMP_TO_NEVAGATE:
//                    String categoryName = data.getString("categoryName");
//                    String categoryCode = data.getString("categoryCode");
//
//                    MapClickEvent event = new MapClickEvent(categoryCode, categoryName, 0, 0);
//                    EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_CLICK_TREND, event));
//                    break;
//                case ACTION_JUMP_TO_CPY_MAP:
//                    anyCpyNo = data.getString("anyCpyNo");
//                    Intent intent = WebViewContainerActivity.startActivity(getContext(), getString(R.string.cpy_map),
//                            Constant.WEB_URL_MAP_CPY,
//                            WebViewContainerFragment.TYPE_CPY_MAP, anyCpyNo);
//                    startActivity(intent);
//                    break;
//                case ACTION_JUMP_TO_BOND_MAP:
//                    bondCode = data.getString(Constant.BOND_CODE);
//                    if (!TextUtils.isEmpty(bondCode)) {
//                        intent = WebViewContainerActivity.startActivity(getContext(), getString(R.string.bond_map),
//                                Constant.WEB_URL_MAP_BOND,
//                                WebViewContainerFragment.TYPE_BOND_MAP, bondCode);
//                        startActivity(intent);
//                    }
//                    break;
//                case ACTION_JUMP_TP_INFO_MAP:
//                    rowkey = data.getString(Constant.ROWKEY);
//                    intent = WebViewContainerActivity.startActivity(getContext(), getString(R.string.info_map),
//                            Constant.WEB_URL_MAP_INFO, WebViewContainerFragment.TYPE_INFORMATION_DETAIL, rowkey);
//                    startActivity(intent);
//                    break;
//                case "14":// 切换债券估页面
//                    int type = data.getIntValue("type");
//                    EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_CHANGE_VALUATION, type));
//                    break;
//                case "15":// 切换债券估页面
//                    Long releaseDate = data.getLong("releaseDate");
//
//                    event = new MapClickEvent(null, null, releaseDate, 1);
//                    EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_CLICK_TREND, event));
//                    break;
//                default:
//                    break;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
//            getComData();
        }
    }



     @Override
    public void onDestroy() {
        try {
            if (mWebView != null) {
                ViewGroup parent = (ViewGroup) mWebView.getParent();
                if (parent != null) {
                    parent.removeView(mWebView);
                }
                mWebView.removeAllViews();
                mWebView.destroy();
                mWebView = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
