package com.jsjlzj.wayne.ui.basis;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.constant.MyPermissionConstant;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.utils.StatusBarCompatUtil;
import com.jsjlzj.wayne.utils.keyboard.KeyboardUtil;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;

import static android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE;
import static com.jsjlzj.wayne.ui.basis.WebViewContainerFragment.TYPE_ARTICLE_DETAIL;
import static com.jsjlzj.wayne.ui.basis.WebViewContainerFragment.TYPE_BANNER_LINK_URL;
import static com.jsjlzj.wayne.ui.basis.WebViewContainerFragment.TYPE_DYNAMIC_DETAIL;
import static com.jsjlzj.wayne.ui.basis.WebViewContainerFragment.TYPE_NEW_DAY_SIGN;
import static com.jsjlzj.wayne.ui.basis.WebViewContainerFragment.TYPE_NEW_MEMBER_CENTER;
import static com.jsjlzj.wayne.ui.basis.WebViewContainerFragment.TYPE_PRIVATE_POLICY;


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
        if(type == TYPE_BANNER_LINK_URL){
            finish();
            return;
        }
        String title = intent.getStringExtra(ExtraConstant.EXTRA_WEB_TITLE);
        String url = intent.getStringExtra(ExtraConstant.EXTRA_WEB_URL);
        String rowkey = intent.getStringExtra(ExtraConstant.EXTRA_WEB_DATA);
        if (!TextUtils.isEmpty(title)) {
            if(url.contains("/comment") || type == TYPE_BANNER_LINK_URL || type == TYPE_PRIVATE_POLICY || type == TYPE_NEW_MEMBER_CENTER || title.equals("积分明细") || type == TYPE_NEW_DAY_SIGN){
                if(type == TYPE_ARTICLE_DETAIL){
                    title = "详情";
                }
                initTitle(title);
            }else {
                initRightTitle(title,R.drawable.ic_share2);
                mRightBtn.setOnClickListener(clickListener);
            }
        }else {
            initTitle("");
        }
        addWebFragment(url, rowkey, type);
        if(type == TYPE_DYNAMIC_DETAIL){
//            AndroidBug5497Workaround.assistActivity(this);
        }

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
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                /** 回退键 事件处理 优先级:视频播放全屏-网页回退-关闭页面 */
                if (webFragment.customView != null) {
                    webFragment.hideCustomView();
                } else if (webFragment.mWebView.canGoBack()) {
                    webFragment.mWebView.goBack();
                } else {
//                    finish();
                }
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }


    public void clickCallPhone() {
        if (TextUtils.isEmpty(getResources().getString(R.string.link_phone))) {
            return;
        }
        PermissionUtil.checkPermission(
                this,
                MyPermissionConstant.CALL_PHONE,
                Manifest.permission.CALL_PHONE);
    }

    @Override
    public void permissionSuccess(int permissionReqCode) {
        super.permissionSuccess(permissionReqCode);
        switch (permissionReqCode) {
            case MyPermissionConstant.CALL_PHONE:
                Intent intent = new Intent();
                //设置拨打电话的动作
                intent.setAction(Intent.ACTION_DIAL);//ACTION_DIAL  ACTION_CALL
                //设置拨打电话的号码
                intent.setData(Uri.parse("tel:" + getResources().getString(R.string.link_phone)));
                //开启打电话的意图
                startActivity(intent);
                break;
            default:break;
        }
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


    private PopupWindow mPopWindow;


    public void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.layout_comment_editext, null);
        mPopWindow = new PopupWindow(contentView,
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //防止PopupWindow被软件盘挡住（可能只要下面一句，可能需要这两句）
//        mPopWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //设置软键盘弹出
        InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(1000, InputMethodManager.HIDE_NOT_ALWAYS);//这里给它设置了弹出的时间
        //设置各个控件的点击响应
        final EditText editText = contentView.findViewById(R.id.et_comment);
        final TextView tvSend = contentView.findViewById(R.id.tv_send);
        editText.setHint("写下你的评论吧");
        editText.setInputType(TYPE_TEXT_FLAG_MULTI_LINE);
        editText.setSingleLine(false);
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                //在这里做请求操作
                String inputString = editText.getText().toString();
                webFragment.endInput(inputString);
                mPopWindow.dismiss();//让PopupWindow消失
                KeyboardUtil.openKeyboard(editText,this);
                return true;
            }
            return false;
        });
        tvSend.setOnClickListener(v -> {
            String inputString = editText.getText().toString();
            webFragment.endInput(inputString);
            mPopWindow.dismiss();//让PopupWindow消失
        });
        //是否具有获取焦点的能力
        mPopWindow.setFocusable(true);
        //显示PopupWindow
        View rootview = LayoutInflater.from(this).inflate(getLayoutResId(), null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        KeyboardUtil.openKeyboard(editText,this);
    }

}
