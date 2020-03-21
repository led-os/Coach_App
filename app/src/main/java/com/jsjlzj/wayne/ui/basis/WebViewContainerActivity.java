package com.jsjlzj.wayne.ui.basis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;

import androidx.fragment.app.FragmentTransaction;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.widgets.AndroidBug5497Workaround;

import static com.jsjlzj.wayne.ui.basis.WebViewContainerFragment.TYPE_ARTICLE_DETAIL;
import static com.jsjlzj.wayne.ui.basis.WebViewContainerFragment.TYPE_BANNER_LINK_URL;


/**
 *
 * @author 曾海强
 * @date 2020/02/26
 * 描述：WebView Activity
 */
public class WebViewContainerActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView  {


    public static final String TAG_FRAGMENT = "webview";
    private WebViewContainerFragment webFragment;

    public static void go2this(Context context, String title, String url, int type, String data) {
        Intent intent = new Intent(context, WebViewContainerActivity.class);
        intent.putExtra(ExtraConstant.EXTRA_WEB_TITLE, title);
        intent.putExtra(ExtraConstant.EXTRA_WEB_URL, url);
        intent.putExtra(ExtraConstant.EXTRA_WEB_TYPE, type);
        intent.putExtra(ExtraConstant.EXTRA_WEB_DATA, data);
        context.startActivity(intent);
    }

    public static void go2this(Context context, String title, String url, int type) {
        Intent intent = new Intent(context, WebViewContainerActivity.class);
        intent.putExtra(ExtraConstant.EXTRA_WEB_TITLE, title);
        intent.putExtra(ExtraConstant.EXTRA_WEB_URL, url);
        intent.putExtra(ExtraConstant.EXTRA_WEB_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_webview_container;
    }

    @Override
    protected void initViewAndControl() {
        Intent intent = getIntent();
        int type = intent.getIntExtra(ExtraConstant.EXTRA_WEB_TYPE, TYPE_BANNER_LINK_URL);
        String title = intent.getStringExtra(ExtraConstant.EXTRA_WEB_TITLE);
        String url = intent.getStringExtra(ExtraConstant.EXTRA_WEB_URL);
        String rowkey = intent.getStringExtra(ExtraConstant.EXTRA_WEB_DATA);
        if (!TextUtils.isEmpty(title)) {
            if(url.contains("/comment") || type == TYPE_BANNER_LINK_URL){
                if(type == TYPE_ARTICLE_DETAIL){
                    title = "详情";
                }
                initTitle(title);
            }else {
                initRightTitle(title,R.drawable.ic_share2);
                mRightBtn.setOnClickListener(clickListener);
            }
        }

        addWebFragment(url, rowkey, type);
        AndroidBug5497Workaround.assistActivity(this);
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }


    @Override
    protected void onMultiClick(View view) {
        if(R.id.btn_title_right == view.getId()){
            if(webFragment != null){
                webFragment.toShare();
            }
        }
    }

    /**
     * 加载 webfragment
     */
    private void addWebFragment(String url, String rowkey, int type) {
        Bundle bundle = new Bundle();
        bundle.putString(ExtraConstant.EXTRA_WEB_URL, url);
        bundle.putString(ExtraConstant.EXTRA_WEB_DATA, rowkey);
        bundle.putInt(ExtraConstant.EXTRA_WEB_TYPE, type);

        webFragment = WebViewContainerFragment.getInstance(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.web_view_container, webFragment, TAG_FRAGMENT);
        transaction.commit();
    }

//    @Override
//    public void onBackPressed() {
//        if (webFragment != null) {
////            if (!webFragment.onBackPressed()) {
////                super.onBackPressed();
////            }
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // try catch 来捕获 viewpager 多点触控导致的 exception
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // try catch 来捕获 viewpager 多点触控导致的 exception
        try {
            return super.dispatchTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
