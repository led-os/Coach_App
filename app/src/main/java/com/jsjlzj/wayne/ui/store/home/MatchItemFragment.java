package com.jsjlzj.wayne.ui.store.home;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.MatchAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.home.CategoryBean;
import com.jsjlzj.wayne.entity.store.home.CategoryPageBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
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
  * @ClassName:      赛事列表
  * @Description:    java类作用描述
  * @Author:         曾海强
  * @CreateDate:      
  */
public class MatchItemFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, XRecyclerView.LoadingListener {


    @BindView(R.id.rv_authentication)
    CustomXRecyclerView rvAuthentication;

    private MatchAdapter matchAdapter;
    /**
     * 0 : 全体赛事   1： 进行中    2 ：已结束
     */
     private int typeId;
     private int pageNo;
     private int pageCount;
     private boolean isRefresh;
     private Map<Object, Object> map = new HashMap<>();
     private List<CategoryBean> categoryBeans = new ArrayList<>();

    public MatchItemFragment() {
    }

    public MatchItemFragment(int type) {
        this();
        this.typeId = type;
    }


    public static MatchItemFragment getInstance(int type) {
        MatchItemFragment fragment = new MatchItemFragment(type);
        return fragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_authentication;
    }

    @Override
    protected void initViewAndControl(View view) {
        rvAuthentication.setPullRefreshEnabled(true);
        rvAuthentication.setLoadingMoreEnabled(true);
        matchAdapter = new MatchAdapter(getActivity(),categoryBeans);
        rvAuthentication.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAuthentication.setLoadingListener(this);
        rvAuthentication.setAdapter(matchAdapter);
        loadData(true);
    }

    @Override
    protected void fragment2Front() {

    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


     private void loadData(boolean isRefresh) {
         this.isRefresh = isRefresh;
         if(isRefresh) {
             pageNo = 0;
         }
         map.clear();
         map.put(HttpConstant.PAGE_NO, pageNo);
         map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
         map.put("categoryId", typeId);
         presenter.getMatchList(map);
     }

     @Override
     public void getMatchListSuccess(MdlBaseHttpResp<CategoryPageBean> resp) {
         rvAuthentication.refreshComplete();
         rvAuthentication.loadMoreComplete();
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
                     categoryBeans.clear();
                 }
                 categoryBeans.addAll(list);
                 matchAdapter.setData(categoryBeans);
                 hideEmpty();
             } else if (isRefresh) {
                 // 无数据
                 showEmpty(R.id.rel_empty,0,null);
             }
         }

     }

     @Override
     public void onRefresh() {
        loadData(true);
     }

     @Override
     public void onLoadMore() {
         if (pageNo < pageCount -1) {
             pageNo++;
             loadData(false);
         } else {
             ToastHelper.showToast(getContext(), getString(R.string.has_no_more_data));
         }
     }
 }
