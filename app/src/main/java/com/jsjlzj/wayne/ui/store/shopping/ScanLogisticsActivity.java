package com.jsjlzj.wayne.ui.store.shopping;


import android.app.Activity;
import android.content.Intent;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

 /**
  *
  * @ClassName:      ScanLogisticsActivity
  * @Description:    物流信息
  * @Author:         曾海强
  * @CreateDate:
  */
public class ScanLogisticsActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    private String logisticsCode;

    public static void go2this(Activity activity,String logisticsCode){
        activity.startActivity(new Intent(activity,ScanLogisticsActivity.class).putExtra("logisticsCode",logisticsCode));
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_scan_logistics;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        logisticsCode= getIntent().getStringExtra("logisticsCode");
    }


}
