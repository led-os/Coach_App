package com.jsjlzj.wayne.ui.store.personal.set;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SPUtil;
import com.jsjlzj.wayne.utils.keyboard.KeyboardUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

 /**
  *
  * @ClassName:      ModifyPayPasswordActivity
  * @Description:    设置支付密码界面
  * @Author:         曾海强
  * @CreateDate:
  */
public class ModifyPayPasswordActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.edPwd)
    EditText edPwd;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    private String phone,code;
    private Map<Object,Object> map = new HashMap<>();

    public static void go2this(Activity activity, String phone,String code) {
        activity.startActivity(new Intent(activity, ModifyPayPasswordActivity.class)
                .putExtra("phone", phone)
                .putExtra("code",code));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_modify_pay_password;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("设置支付密码");
        phone = getIntent().getStringExtra("phone");
        code = getIntent().getStringExtra("code");
        tvConfirm.setOnClickListener(clickListener);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);

        if(TextUtils.isEmpty(edPwd.getText().toString())){
            LogAndToastUtil.toast("请输入支付密码");
            return;
        }else if(edPwd.getText().toString().length() != 6){
            LogAndToastUtil.toast("请输入6位数字密码");
            return;
        }

        map.put("code",code);
        map.put("mobile",phone);
        map.put("password",edPwd.getText().toString());
        presenter.setPayPassword(map);
    }


     @Override
     public void setPayPasswordSuccess(MdlBaseHttpResp<MdlUser> resp) {
         if(resp.getStatus() == HttpConstant.R_HTTP_OK){
             KeyboardUtil.closeKeyboard(edPwd,this);
             MdlUser data = resp.getData();
             MyApp.ME.user = data.getData();
             SPUtil.saveToken2SP(data.getData().getToken());
             SPUtil.saveUser2SP(data.getData());
             finish();
         }
     }
 }
