package com.jsjlzj.wayne.ui.store.attestation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

 /**
  *
  * @ClassName:      编辑俱乐部信息-最新
  * @Description:    java类作用描述
  * @Author:         曾海强
  * @CreateDate:     2020/08/01
  */
public class EditStoreInfoActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_edit_store_info;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {

    }


}
