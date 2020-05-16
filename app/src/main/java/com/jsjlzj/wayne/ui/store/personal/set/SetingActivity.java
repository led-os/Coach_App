package com.jsjlzj.wayne.ui.store.personal.set;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.ui.basis.LoginRoleSelectActivity;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.utils.SPUtil;
import com.jsjlzj.wayne.utils.Utility;
import com.jsjlzj.wayne.widgets.dialog.CommonDialog;

/**
 * 设置页面
 */
public class SetingActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {
    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, SetingActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_store_seting;
    }


    private TextView tvPhone, tvPassword, tvWechat,tvPayPassword;
    public MdlUser.MdlUserBean user;
    private String strPhone, strWx;

    @Override
    protected void initViewAndControl() {
        user = SPUtil.getUserFromSP();

        tvPhone = findView(R.id.tvPhone);
        tvPassword = findView(R.id.tvPassword);
        tvWechat = findView(R.id.tvWechat);
        tvPayPassword = findView(R.id.tv_pay_password);


        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.tvSwitch).setOnClickListener(clickListener);
        findView(R.id.btnLogout).setOnClickListener(clickListener);
        tvPayPassword.setOnClickListener(clickListener);
        tvPhone.setOnClickListener(clickListener);
        tvPassword.setOnClickListener(clickListener);
        tvWechat.setOnClickListener(clickListener);

        if (null != user) {
            if(!TextUtils.isEmpty(user.getMobile())&&user.getMobile().length()==11){
                tvPhone.setText(user.getMobile().substring(0,3)+"****"+user.getMobile().substring(7,user.getMobile().length()));
            }
                if(user.getIsBindWeChat()==2){
                tvWechat.setText(TextUtils.isEmpty(user.getWxName())?"已绑定":user.getWxName());
                }else{
                    tvWechat.setText("未绑定");
                }
                if(user.getIsSetPwd()==1){//未设置
                    tvPassword.setText("设置密码");
                }else{
                    tvPassword.setText("修改密码");
                }
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
                case R.id.tvPhone:
                    SetingPhoneActivity.go2this(SetingActivity.this, RESULT_PHONE, tvPhone.getText().toString());
                    break;
                case R.id.tvPassword:
                    if(user.getIsSetPwd()==1) {//未设置
                        SetingPasswordActivity.go2this(SetingActivity.this);
                    }else{
                        SetingUpdatePsdActivity.go2this(SetingActivity.this);
                    }
                    break;
                case R.id.tv_pay_password://设置支付密码
                    PayForgetPasswordActivity.go2this(SetingActivity.this,0);
                    break;
                case R.id.tvWechat:
                    SetingWeChatActivity.go2this(SetingActivity.this, RESULT_WECHAT, user.getWxName(),user.getIsBindWeChat());
                    break;
                case R.id.tvSwitch:
                    LoginRoleSelectActivity.go2This(SetingActivity.this, false);
                    break;
                case R.id.btnLogout:
                    clickLogout();
                    break;
            }
        }
    }

    public static final int RESULT_PHONE = 0X001;
    public static final int RESULT_WECHAT = 0X003;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null == data) return;
        switch (requestCode) {
            case RESULT_PHONE:
                strPhone = data.getStringExtra("phone");
                if (!TextUtils.isEmpty(strPhone))
                    tvPhone.setText(strPhone);
                break;
            case RESULT_WECHAT:
                strWx = data.getStringExtra("wecaht");
                if (!TextUtils.isEmpty(strWx))
                    tvWechat.setText(strWx);
                else tvWechat.setText("未绑定");
                break;
        }
    }

    private void clickLogout() {
        CommonDialog dialog = new CommonDialog(this, "确定退出账号吗？", new CommonDialog.ClickListener() {
            @Override
            public void clickConfirm() {
                Utility.needLogin(SetingActivity.this);
            }

            @Override
            public void clickCancel() {}
        });
        dialog.show();

    }
}