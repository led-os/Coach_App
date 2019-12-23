package com.jsjlzj.wayne.ui.store.personal.set;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

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

/**
 * 设置密码
 */
public class SetingPasswordActivity extends MVPBaseActivity<LoginActivityView, LoginActivityPresenter> implements LoginActivityView {

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, SetingPasswordActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_store_seting_password;
    }

    @Override
    protected LoginActivityPresenter createPresenter() {
        return new LoginActivityPresenter(this);
    }

    private EditText edPassword,edAgain;

    @Override
    protected void initViewAndControl() {

        edPassword = findView(R.id.edPassword);
        edAgain = findView(R.id.edAgain);

        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnConfirm).setOnClickListener(clickListener);
    }
    Map<Object,Object> map=null;
    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    finish();
                    break;
                case R.id.btnConfirm:
                    String psd=edPassword.getText().toString().trim();
                    String psdAgin=edAgain.getText().toString().trim();
                    if(null==map)map=new HashMap<>();
                    if(!TextUtils.isEmpty(psd)&&!TextUtils.isEmpty(psdAgin)&&(psd.equals(psdAgin))&&psd.length()>=6&&psd.length()<17){
                        map.put("password",psd);
                        presenter.setPsd(map);
                    }else{
                        LogAndToastUtil.toast("请正确输入密码");
                    }
                    break;
            }
        }
    }

    @Override
    public void setPsd(MdlBaseHttpResp<MdlUser> resp) {
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            LogAndToastUtil.toast("设置成功");
            MyApp.user=resp.getData().getData();
            SPUtil.saveToken2SP(resp.getData().getData().getToken());
            SPUtil.saveUser2SP(MyApp.user);
            finish();
        }else{
            LogAndToastUtil.toast(resp.getMsg());
        }
    }
}