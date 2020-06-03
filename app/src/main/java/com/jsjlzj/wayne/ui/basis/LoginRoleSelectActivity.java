package com.jsjlzj.wayne.ui.basis;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.MainActivity;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.store.attestation.AttestationActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SPUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 身份选择   门店、教练
 */
public class LoginRoleSelectActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {
    private boolean isBack;

    public static void go2This(Activity context, boolean isBack) {
        Intent intent = new Intent(context, LoginRoleSelectActivity.class);
        intent.putExtra("isBack", isBack);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.actvity_login_roleselect;
    }

    @Override
    protected void initViewAndControl() {
        findView(R.id.btnTrainer).setOnClickListener(onClickListner);
        findView(R.id.btnStore).setOnClickListener(onClickListner);
        isBack = getIntent().getBooleanExtra("isBack", true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!isBack && keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    private Map<Object, Object> params;
    private MyOnClickListner onClickListner = new MyOnClickListner();

    private class MyOnClickListner extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            String type = "";
            switch (view.getId()) {
                case R.id.btnTrainer://下一步
                    type = "TRAINER";
                    break;
                case R.id.btnStore://下一步
//                    LogAndToastUtil.toast(LoginRoleSelectActivity.this,"暂未开放,敬请期待");
                    type = "STORE";
                    break;
                default:
                    break;
            }
            if (null == params) params = new HashMap<>();
            params.put("type", type);
            presenter.switchIdentity(params);

        }
    }

    @Override
    public void showSwitchIdentity(MdlBaseHttpResp<MdlUser> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MdlUser.MdlUserBean bean = resp.getData().getData();
            MyApp.ME.user = bean;
            MyApp.isTrainer = bean.getStatus() == 2;
            SPUtil.saveUser2SP(MyApp.user);
            SPUtil.saveToken2SP(bean.getToken());
            switch (bean.getAccountType()) {
                case "NONE":
                    LogAndToastUtil.toast(this, "请选择身份");
                    break;
                case "STORE":
                    if (bean.getStoreStatus() != 1 && bean.getStoreStatus() != 3) {
                        AttestationActivity.go2this(LoginRoleSelectActivity.this);
                    } else if (bean.getStoreStatus() == 3) {
                        MainActivity.go2this(LoginRoleSelectActivity.this, true);
                    } else if (bean.getStoreStatus() == 1) {
                        LogAndToastUtil.toast("审核中。。。");
                    }
                    break;
                case "TRAINER":
                    MainActivity.go2this(LoginRoleSelectActivity.this, false);
                    break;
                default:
                    break;
            }
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }
}
