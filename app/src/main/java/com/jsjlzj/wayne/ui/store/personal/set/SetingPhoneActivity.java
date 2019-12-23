package com.jsjlzj.wayne.ui.store.personal.set;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.jsjlzj.wayne.utils.RegexUtil;
import com.jsjlzj.wayne.utils.SPUtil;
import com.jsjlzj.wayne.widgets.text.CountDownTextView;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改手机号
 */
public class SetingPhoneActivity extends MVPBaseActivity<LoginActivityView, LoginActivityPresenter> implements LoginActivityView {


    public static void go2this(Activity context,int result,String phone) {
        Intent intent = new Intent(context, SetingPhoneActivity.class);
        intent.putExtra("phone", phone);
        context.startActivityForResult(intent,result);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_store_seting_phone;
    }


    private TextView tvCurrentPhone;
    private EditText edPhone, edSmes;
    private CountDownTextView btnGetSmes;
    private String phone;
    @Override
    protected void initViewAndControl() {
        phone=getIntent().getStringExtra("phone");
        tvCurrentPhone = findView(R.id.tvCurrentPhone);
        if(!TextUtils.isEmpty(phone)&&phone.length()==11){
            tvCurrentPhone.setText(phone.substring(0,3)+"****"+phone.substring(7,phone.length()));
        }else{
        tvCurrentPhone.setText(phone);

        }
        edPhone = findView(R.id.edPhone);
        edSmes = findView(R.id.edSmes);
        btnGetSmes = findView(R.id.btnGetSmes);


        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnConfirm).setOnClickListener(clickListener);


        btnGetSmes.setNormalText("获取验证码")
                .setCountDownText("重新获取", "")
                .setCloseKeepCountDown(true)//关闭页面保持倒计时开关
                .setCountDownClickable(true)//倒计时期间点击事件是否生效开关
                .setShowFormatTime(true)//是否格式化时间
                .setOnCountDownFinishListener(new CountDownTextView.OnCountDownFinishListener() {
                    @Override
                    public void onFinish() {
                        btnGetSmes.setTextColor(getResources().getColor(R.color.login_btn_smes));
                    }
                })
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!RegexUtil.isMobile(edPhone.getText().toString())) {
                            LogAndToastUtil.toast("请输入正确的号码");
                            return;
                        }
                        LogAndToastUtil.toast("短信已发送", Toast.LENGTH_SHORT);
                        btnGetSmes.startCountDown(59);
                        btnGetSmes.setTextColor(getResources().getColor(R.color.gray));
                        if (mapGetSmes == null) mapGetSmes = new HashMap<>();
                        mapGetSmes.put("mobile", edPhone.getText().toString());
                        mapGetSmes.put("type", "CHANGE_MOBILE_CODE");
                        presenter.getSmes(mapGetSmes);
                    }
                });
    }

    @Override
    protected LoginActivityPresenter createPresenter() {
        return new LoginActivityPresenter(this);
    }

    private Map<Object, Object> mapGetSmes, mapConfirm;

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    finish();
                    break;
                case R.id.btnConfirm:
                    if (mapConfirm == null) mapConfirm = new HashMap<>();
                    mapConfirm.put("mobile", edPhone.getText().toString());
                    mapConfirm.put("code", edSmes.getText().toString());
                    presenter.changeMobile(mapConfirm);
                    break;
            }
        }
    }
    @Override
    public void changeMobile(MdlBaseHttpResp<MdlUser> resp) {
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            LogAndToastUtil.toast("修改成功");
            MyApp.user=resp.getData().getData();
            SPUtil.saveToken2SP(MyApp.user.getToken());
            SPUtil.saveUser2SP(MyApp.user);

            finish();
        }else{
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

//    @Override
//    public void showResultResetPwd(MdlBaseHttpResp<MdlUser> resp) {
//        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
//            LogAndToastUtil.toast(this, "修改成功");
//            finish();
//        }
//    }

    @Override
    public void finish() {
        super.finish();
        Intent intent =new Intent();
        intent.putExtra("phone",phone);
        setResult(0,intent);
    }
}