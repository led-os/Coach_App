package com.jsjlzj.wayne.ui.basis;

import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.BaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityPresenter;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityView;

public class RegisterActivity extends BaseActivity<LoginActivityView, LoginActivityPresenter> implements LoginActivityView{
    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void initViewAndControl() {

    }


    @Override
    public void showResultRegister(MdlBaseHttpResp resp) {

    }
}
