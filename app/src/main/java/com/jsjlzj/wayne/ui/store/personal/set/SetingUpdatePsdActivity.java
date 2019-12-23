package com.jsjlzj.wayne.ui.store.personal.set;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityPresenter;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SPUtil;

import java.util.HashMap;
import java.util.Map;

public class SetingUpdatePsdActivity extends MVPBaseActivity<LoginActivityView, LoginActivityPresenter> implements LoginActivityView {
    public static void go2this(Activity context) {
        Intent intent = new Intent(context, SetingUpdatePsdActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_seting_update_psd;
    }
    private TextView edAgain,edPassword,edPrePassword;
    @Override
    protected void initViewAndControl() {
        findView(R.id.btnConfirm).setOnClickListener(onClickListner);
        findView(R.id.btnBack).setOnClickListener(onClickListner);
        edAgain= findView(R.id.edAgain);
        edPassword=findView(R.id.edPassword);
        edPrePassword=findView(R.id.edPrePassword);
    }
    private MyOnClickListner onClickListner = new MyOnClickListner();
    private Map<Object,Object> map;
    private class MyOnClickListner extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    finish();
                    break;
                case R.id.btnConfirm:
                    if(map==null)map=new HashMap<>();
                    String prePsd=edPrePassword.getText().toString().trim();
                    String psd=edPassword.getText().toString().trim();
                    String again=edAgain.getText().toString().trim();
                    if(!TextUtils.isEmpty(prePsd)&&!TextUtils.isEmpty(psd)&&!TextUtils.isEmpty(again)&&prePsd.length()>5&&psd.length()>5&&again.length()>5){
                        if(again.equals(psd)){
                            map.put("oldPassword",prePsd);
                            map.put("password",psd);
                            presenter.updatePsd(map);
                        }else{
                            LogAndToastUtil.toast("两次输入的密码不一致");
                        }
                    }else{
                        LogAndToastUtil.toast("请将密码补充完整,至少输入6位");
                    }
                    break;
            }
        }
    }



    @Override
    protected LoginActivityPresenter createPresenter() {
        return new LoginActivityPresenter(this);
    }

    public void updatePsd(MdlBaseHttpResp<MdlUser> resp) {
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            LogAndToastUtil.toast("修改成功");
            MyApp.user=resp.getData().getData();
            SPUtil.saveToken2SP(resp.getData().getData().getToken());
            SPUtil.saveUser2SP(MyApp.user);
            finish();
        }
    }
}
