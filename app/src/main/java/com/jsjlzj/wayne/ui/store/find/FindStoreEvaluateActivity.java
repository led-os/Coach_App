package com.jsjlzj.wayne.ui.store.find;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

 /**
  *
  * @ClassName:      FindStoreEvaluateActivity
  * @Description:    俱乐部的评价
  * @Author:         曾海强
  * @CreateDate:
  */
public class FindStoreEvaluateActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_find_store_evaluate;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {

    }


}
