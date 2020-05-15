package com.jsjlzj.wayne.ui.store.shopping;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.shopping.LocationAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.LocationListBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

 /**
  *
  * @ClassName:      地址管理
  * @Description:    java类作用描述
  * @Author:         曾海强
  * @CreateDate:
  */
public class LocationManagerActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, LocationAdapter.OnItemClickListener {

    private static final int REQUEST_CODE = 1000;
    @BindView(R.id.rv_location)
    RecyclerView rvLocation;
    private LocationAdapter adapter;
     /**
      * 0 ： 来致我的界面  1 ： 来至确定订单界面
      */
    private int fromType;
    private List<LocationListBean.DataBean> locationList = new ArrayList<>();


    public static void go2this(Activity activity, int requestCode,int fromType) {
        activity.startActivityForResult(new Intent(activity, LocationManagerActivity.class).putExtra("from_type",fromType), requestCode);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_location_manager;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        fromType = getIntent().getIntExtra("from_type",0);
        initRightTitle("地址管理", "新增");
        mRightTv.setOnClickListener(clickListener);
        rvLocation.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LocationAdapter(this,locationList,fromType);
        adapter.setListener(this);
        rvLocation.setAdapter(adapter);
        presenter.getLocationList();
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.tv_right_btn:
                EditLocationActivity.go2this(this, REQUEST_CODE,null);
                break;
            default:break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            presenter.getLocationList();
        }
    }



    @Override
    public void onItemClick(LocationListBean.DataBean bean) {
        Intent intent = new Intent();
        intent.putExtra("location",bean);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onEditClick(LocationListBean.DataBean bean) {
        EditLocationActivity.go2this(this,REQUEST_CODE,bean);
    }


     @Override
     public void getLocationListSuccess(MdlBaseHttpResp<LocationListBean> resp) {
         if(resp.getStatus() == HttpConstant.R_HTTP_OK){
             if(resp.getData().getData() != null && resp.getData().getData().size() > 0){
                 this.locationList.clear();
                 this.locationList.addAll(resp.getData().getData());
                 adapter.setData(locationList);
             }else {
                 showEmpty(R.id.rel_empty,0,null);
             }
         }
     }
 }
