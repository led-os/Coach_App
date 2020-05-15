package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.os.Bundle;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import java.util.HashMap;
import java.util.Map;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description 银行卡列表
 * @date: 2020/05/14
 * @author: 曾海强
 */
public class ManagerBandCardActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.rv_data)
    RecyclerView rvData;

    public static void go2this(Activity activity){

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_manager_band_card;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("银行卡管理");
        presenter.getBankCardList();
        Map<Object,Object> map = new HashMap<>();
        map.put("id",12);
        presenter.deleteBankCard(map);
        presenter.getBankCardInfo(map);
    }

}
