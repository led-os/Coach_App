package com.jsjlzj.wayne.ui.basis;

import android.app.Activity;
import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseNoLoginActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;

public class WebViewActivity extends MVPBaseNoLoginActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {
    public static void go2this(Activity context) {
        Intent intent = new Intent(context, WebViewActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_web_view;
    }
    private WebView webView;
    @Override
    protected void initViewAndControl() {
        webView=findView(R.id.webView);
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        webView.loadUrl(HttpConstant.BASE_URL+"/static/h5/agreement/regist_privacy.html");
    }


    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }
}
