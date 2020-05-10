package com.jsjlzj.wayne.ui.store.home;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.AuthenticationAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.home.CategoryBean;
import com.jsjlzj.wayne.entity.store.home.CategoryPageBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.store.home.amoy.HotSchoolActivity;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @ClassName: AuthenticationFragment
 * @Description: 授权类型
 * @Author: 曾海强
 * @CreateDate:
 */
public class AuthenticationFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, AuthenticationAdapter.OnItemClickListener, XRecyclerView.LoadingListener {


    @BindView(R.id.rv_authentication)
    CustomXRecyclerView rvAuthentication;

    private AuthenticationAdapter authenticationAdapter;
    private Map<Object, Object> map = new HashMap<>();
    private List<CategoryBean> categoryList = new ArrayList<>();
    /**
     * 0 : 认证类   1： 技能类    2 ：管理类
     * 对应类型的id
     */
    private int typeId;
    private int pageNo;
    private int pageCount;
    private boolean isRefresh;


    public AuthenticationFragment() {
    }

    public AuthenticationFragment(int id) {
        this();
        this.typeId = id;
    }


    public static AuthenticationFragment getInstance(int type) {
        AuthenticationFragment fragment = new AuthenticationFragment(type);
        return fragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_authentication;
    }

    @Override
    protected void initViewAndControl(View view) {
        rvAuthentication.setHasFixedSize(true);
        rvAuthentication.setNestedScrollingEnabled(false);
        rvAuthentication.setPullRefreshEnabled(true);
        rvAuthentication.setLoadingMoreEnabled(true);
        authenticationAdapter = new AuthenticationAdapter(getActivity(),categoryList);
        authenticationAdapter.setListener(this);
        rvAuthentication.setLoadingListener(this);
        rvAuthentication.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAuthentication.setAdapter(authenticationAdapter);
        loadData(true);

    }

    private void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if(isRefresh) {
            pageNo = 1;
        }
        map.clear();
        map.put(HttpConstant.PAGE_NO, pageNo);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        map.put("parentId", typeId);
        presenter.getAmoyList(map);
    }

    @Override
    protected void fragment2Front() {

    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    @Override
    public void onItemClick(CategoryBean bean) {
        HotSchoolActivity.go2this(getActivity(),bean.getId());
    }


    @Override
    public void getAmoyListSuccess(MdlBaseHttpResp<CategoryPageBean> resp) {
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
                    categoryList.clear();
                }
                categoryList.addAll(list);
                authenticationAdapter.setData(categoryList);
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
        if (pageNo < pageCount) {
            pageNo++;
            loadData(false);
        } else {
            ToastHelper.showToast(getContext(), getString(R.string.has_no_more_data));
        }
    }
}
