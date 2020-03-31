package com.jsjlzj.wayne.ui.store.home.amoy;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.HotSchoolAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.home.CategoryBean;
import com.jsjlzj.wayne.entity.store.home.CategoryPageBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
 /**
  *
  * @ClassName:      HotSchoolActivity
  * @Description:    热门学校或机构
  * @Author:         曾海强
  * @CreateDate:
  */
public class HotSchoolActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, HotSchoolAdapter.OnItemClickListener, XRecyclerView.LoadingListener {

    @BindView(R.id.rv_hot_school)
    CustomXRecyclerView rvHotSchool;

    private HotSchoolAdapter adapter;

     private List<CategoryBean> categoryList = new ArrayList<>();
     private Map<Object,Object> map = new HashMap<>();
     /**
      * 对应类型的categoryId
      */
     private int categoryId;
     private int pageNo;
     private int pageCount;
     private boolean isRefresh;

    public static void go2this(Activity context,int categoryId) {
        Intent intent = new Intent(context, HotSchoolActivity.class);
        intent.putExtra("categoryId",categoryId);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_hot_school;
    }


    @Override
    protected void initViewAndControl() {
        initTitle("热门学校");
        categoryId = getIntent().getIntExtra("categoryId",0);
        if(categoryId == 0){
            finish();
        }
        adapter = new HotSchoolAdapter(this,new ArrayList<>());
        adapter.setListener(this);
        rvHotSchool.setPullRefreshEnabled(true);
        rvHotSchool.setLoadingMoreEnabled(true);
        rvHotSchool.setLoadingListener(this);
        rvHotSchool.setLayoutManager(new LinearLayoutManager(this));
        rvHotSchool.setAdapter(adapter);
        loadData(true);
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    @Override
    public void onItemClick(CategoryBean bean) {
        WebViewContainerActivity.go2this(this,bean.getName(),HttpConstant.WEB_URL_SCHOOL_DETAIL+bean.getId(),
                WebViewContainerFragment.TYPE_SCHOOL_DETAIL);
    }



     private void loadData(boolean isRefresh) {
         this.isRefresh = isRefresh;
         if(isRefresh) {
             pageNo = 1;
         }
         map.clear();
         map.put(HttpConstant.PAGE_NO, pageNo);
         map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
         map.put("categoryId", categoryId);
         presenter.getOrganizationList(map);
     }


     @Override
     public void getCategoryListSuccess(MdlBaseHttpResp<CategoryPageBean> resp) {
         rvHotSchool.refreshComplete();
         rvHotSchool.loadMoreComplete();
         if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp){
             pageNo = resp.getData().getData().getPageNo();
             int totalCount = resp.getData().getData().getTotalCount();
             int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
             if (a == 0) {
                 pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
             } else {
                 pageCount =( totalCount / HttpConstant.PAGE_SIZE_NUMBER)+1;
             }
             List<CategoryBean> list = resp.getData().getData().getResult();
             if (list != null && list.size() > 0) {
                 if (isRefresh) {
                     categoryList.clear();
                 }
                 categoryList.addAll(list);
                 adapter.setData(categoryList);
                 hideEmpty();
             } else if (isRefresh) {
                 // 无数据
                 showEmpty(R.id.rel_empty,0,null);
             }
         }
     }

     @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

     @Override
     public void onRefresh() {
        loadData(true);
     }

     @Override
     public void onLoadMore() {
         if (pageNo < pageCount ) {
             pageNo++;
             loadData(false);
         } else {
             ToastHelper.showToast(this, getString(R.string.has_no_more_data));
         }
     }
 }