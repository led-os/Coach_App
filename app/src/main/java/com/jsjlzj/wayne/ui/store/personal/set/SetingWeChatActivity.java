package com.jsjlzj.wayne.ui.store.personal.set;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.basis.LoginByPhoneSmesActivity;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.dialog.CommonDialog;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static com.jsjlzj.wayne.constant.HttpConstant.WXAPPID;

/**
 * 修改微信号
 */
public class SetingWeChatActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {
    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    public static void go2this(Activity context, int result, String wecaht,int isBindWeChat) {
        Intent intent = new Intent(context, SetingWeChatActivity.class);
        intent.putExtra("wecaht", wecaht);
        intent.putExtra("isBindWeChat", isBindWeChat);
        context.startActivityForResult(intent, result);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_store_seting_wechat;
    }


    private TextView tvWechat, btnWechat, unBindWechat;
    private String wecaht;
    private IWXAPI api;

    @Override
    protected void initViewAndControl() {
        api= WXAPIFactory.createWXAPI(this,WXAPPID,false);
        wecaht = getIntent().getStringExtra("wecaht");
        int isBindWeChat=getIntent().getIntExtra("isBindWeChat",0);
        tvWechat = findView(R.id.tvWechat);
        btnWechat = findView(R.id.btnWechat);
        btnWechat.setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        unBindWechat = findView(R.id.unBindWechat);
        unBindWechat.setOnClickListener(clickListener);
        if (isBindWeChat==2) {
            tvWechat.setText(wecaht);
            btnWechat.setVisibility(View.GONE);
            unBindWechat.setVisibility(View.VISIBLE);
        } else {
            btnWechat.setVisibility(View.VISIBLE);
            unBindWechat.setVisibility(View.GONE);
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
                case R.id.btnWechat:
                    MyApp.idBindWeChat=1;
                    checkInstalledWX();
                    break;
                case R.id.unBindWechat:
                    CommonDialog commonDialog=new CommonDialog(SetingWeChatActivity.this, "解除微信登录后，将无法使用微信快捷登录，确定要解除吗？", new CommonDialog.ClickListener() {
                        @Override
                        public void clickConfirm() {
                            LoginByPhoneSmesActivity.go2ThisUnBind(SetingWeChatActivity.this, MyApp.user.getMobile(),1);
                        }

                        @Override
                        public void clickCancel() {

                        }
                    });
                    commonDialog.setCancel("点错了");
                    commonDialog.setConfirm("确定解除");
                    commonDialog.show();

                    break;
            }
        }
    }
    public void checkInstalledWX(){
        if(!api.isWXAppInstalled()){
            LogAndToastUtil.toast("您的设备未安装微信客户端");
        }else{
            SendAuth.Req req=new SendAuth.Req();
            req.scope="snsapi_userinfo";
            req.state="wechat_sdk";
            api.sendReq(req);
            finish();
        }
    }
    @Override
    public void finish() {
        super.finish();
        Intent intent = new Intent();
        intent.putExtra("wecaht", wecaht);
        setResult(0, intent);
    }

}