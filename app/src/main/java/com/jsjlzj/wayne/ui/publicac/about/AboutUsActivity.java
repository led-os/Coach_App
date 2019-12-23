package com.jsjlzj.wayne.ui.publicac.about;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.MyPermissionConstant;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.Utility;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.jsjlzj.wayne.widgets.dialog.PhoneDialog;

/**
 * 关于
 */
public class AboutUsActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    public static void go2this(Activity context) {//沟通过的达人
        Intent intent = new Intent(context, AboutUsActivity.class);
        intent.putExtra("type", 1);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_aboutus;
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    private TextView tvVersion,btnWechat;

    @Override
    protected void initViewAndControl() {
        tvVersion=findView(R.id.tvVersion);
        btnWechat=findView(R.id.btnWechat);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnEvaluate).setOnClickListener(clickListener);
        findView(R.id.btnService).setOnClickListener(clickListener);
        tvVersion.setText(Utility.getVerName(this)+"");
        if(MyApp.user!=null){
//            btnWechat.setText(MyApp.user.getWxId());
        }
    }


    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    finish();
                    break;
                case R.id.btnEvaluate:
                    break;
                case R.id.btnService:
                    if(!TextUtils.isEmpty(btnWechat.getText().toString())) {
                        copyTextToClipboard(btnWechat.getText().toString());
                    }
//                    showPhoneDialog();
                    break;
            }
        }
    }
    public void copyTextToClipboard(final String text) {
        runOnUiThread(new Runnable() {


            @Override
            public void run() {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("playerId", text);
                clipboardManager.setPrimaryClip(clipData);
                LogAndToastUtil.toast("复制成功");
            }
        });
    }
    private void showPhoneDialog() {
        new PhoneDialog(this, new PhoneDialog.ClickListener() {
            @Override
            public void clickConfirm(String data) {
                clickCallPhone();
            }
        }).show();
    }
    private void clickCallPhone() {
        PermissionUtil.checkPermission(
                this,
                MyPermissionConstant.CALL_PHONE,
                Manifest.permission.CALL_PHONE);
    }

    @Override
    public void permissionSuccess(int permissionReqCode) {
        super.permissionSuccess(permissionReqCode);
        switch (permissionReqCode) {
            case  MyPermissionConstant.CALL_PHONE:
                Intent intent = new Intent();
                //设置拨打电话的动作
                intent.setAction(Intent.ACTION_DIAL);
                //设置拨打电话的号码
                intent.setData(Uri.parse("tel:" + getString(R.string.phone)));
                //开启打电话的意图
                startActivity(intent);
                break;
        }
    }
}
