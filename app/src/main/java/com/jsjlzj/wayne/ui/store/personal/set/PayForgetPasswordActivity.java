package com.jsjlzj.wayne.ui.store.personal.set;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SPUtil;
import com.jsjlzj.wayne.widgets.text.CountDownTextView;
import com.jsjlzj.wayne.widgets.text.VerifyCodeView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * @ClassName: PayForgetPasswordActivity
 * @Description: 设置或修改支付密码
 * @Author: 曾海强
 * @CreateDate:
 */
public class PayForgetPasswordActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.tvContext)
    TextView tvContext;
    @BindView(R.id.edPwd)
    VerifyCodeView edPwd;
    @BindView(R.id.btnGetSmes)
    CountDownTextView btnGetSmes;
    @BindView(R.id.tv_next)
    TextView tvNext;
    /**
     * 0 修改密码   1 ： 忘记密码
     */
    private int type;
    private boolean isFrist = true;
    private Map<Object,Object> map = new HashMap<>();
    private MdlUser.MdlUserBean userBean;

    public static void go2this(Activity activity, int type) {
        activity.startActivity(new Intent(activity, PayForgetPasswordActivity.class).putExtra("type", type));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pay_forget_password;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        type = getIntent().getIntExtra("type", 0);
        userBean = SPUtil.getUserFromSP();
        if(userBean == null || TextUtils.isEmpty(userBean.getMobile())){
            finish();
        }
        if (type == 0) {
            initTitle("修改密码");
        } else {
            initTitle("忘记密码");
        }
        btnGetSmes.setOnClickListener(clickListener);
        tvNext.setOnClickListener(clickListener);
        edPwd.setInputCompleteListener(new VerifyCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                tvNext.setSelected(true);
            }

            @Override
            public void invalidContent() {
                tvNext.setSelected(false);
            }
        });
        btnGetSmes.setNormalText("获取验证码")
                .setCountDownText("重新获取(", ")")
                .setCloseKeepCountDown(true)//关闭页面保持倒计时开关
                .setCountDownClickable(true)//倒计时期间点击事件是否生效开关
                .setShowFormatTime(true)//是否格式化时间
                .setOnCountDownFinishListener(() -> btnGetSmes.setTextColor(getResources().getColor(R.color.login_btn_smes)))
                .setOnClickListener(v -> {
                    if (isFrist) {
                        isFrist = false;
                        btnGetSmes.startCountDown(59);
                        btnGetSmes.setTextColor(getResources().getColor(R.color.gray));
                    } else {
                        LogAndToastUtil.toast("短信已发送", Toast.LENGTH_SHORT);
                        btnGetSmes.startCountDown(59);
                        btnGetSmes.setTextColor(getResources().getColor(R.color.gray));
                    }
                    map.clear();
                    map.put("mobile",userBean.getMobile());
                    map.put("type","PAY_PASSWORD_CODE");
                    presenter.getSmes(map);
                });
        btnGetSmes.performClick();
        tvContext.setText("已向您的手机 " + userBean.getMobile() + " 发送验证码");
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.btnGetSmes:
                map.clear();
                map.put("mobile",userBean.getMobile());
                map.put("type","PAY_PASSWORD_CODE");
                presenter.getSmes(map);
                break;
            case R.id.tv_next:
                if(TextUtils.isEmpty(edPwd.getEditContent())){
                    LogAndToastUtil.toast("请输入验证码");
                    return;
                }
                ModifyPayPasswordActivity.go2this(this,userBean.getMobile(),edPwd.getEditContent().toString());
                finish();
                break;

        }
    }
}
