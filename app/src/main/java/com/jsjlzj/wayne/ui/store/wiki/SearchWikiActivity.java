package com.jsjlzj.wayne.ui.store.wiki;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.InformationAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.home.VideoBean;
import com.jsjlzj.wayne.entity.store.home.VideoPageBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.store.find.SearchFindStoreActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.keyboard.KeyboardUtil;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;
import com.jsjlzj.wayne.widgets.SearchBarView;
import com.netease.nim.uikit.common.ToastHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: 健身百科搜索界面
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate:
 */
public class SearchWikiActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, XRecyclerView.LoadingListener, InformationAdapter.OnItemClickListener {

    @BindView(R.id.rv_search_wiki)
    CustomXRecyclerView rvSearchWiki;

    private InformationAdapter adapter ;
    private List<VideoBean> videoList = new ArrayList<>();
    private int pageNo=1;
    private int pageCount;
    private boolean isRefresh;
    private Map<Object,Object> map = new HashMap<>();
    private String searchKey;

    public static void go2this(Context context){
        context.startActivity(new Intent(context,SearchWikiActivity.class));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search_wiki;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initSearchTitle();
        initSearchBar();
        mRightTv.setOnClickListener(clickListener);
        rvSearchWiki.setPullRefreshEnabled(true);
        rvSearchWiki.setLoadingMoreEnabled(true);
        rvSearchWiki.setLoadingListener(this);
        rvSearchWiki.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InformationAdapter(this,videoList);
        rvSearchWiki.setAdapter(adapter);
        adapter.setListener(this);
        loadData(true);
    }



    private void initSearchBar() {
        mSearchBar.setOnEditTextChangeListener(new SearchBarView.EditTextCallback() {
            @Override
            public void onEditTextChange(String str) {}

            @Override
            public void onEditFinish(String str) {}

            @Override
            public void onClickSoftWare(String str) {
                if(TextUtils.isEmpty(str)){
                    LogAndToastUtil.toast("请输入关键词");
                    return;
                }
                mSearchBar.getSearchEditText().setText(str);
                mSearchBar.getSearchEditText().setSelection(str.length());
                KeyboardUtil.closeKeyboard(mRightTv, SearchWikiActivity.this);
                searchKey = str;
                loadData(true);
            }
        });
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.tv_right_btn:
                finish();
                break;
        }
    }

    private void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if (isRefresh) {
            pageNo = 1;
        }
        map.clear();
        map.put(HttpConstant.PAGE_NO, pageNo);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        map.put(HttpConstant.TITLE, searchKey);
        presenter.getV4InformationList(map);
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
            LogAndToastUtil.toast(this,getString(R.string.has_no_more_data));
        }
    }

    @Override
    public void getVideoListSuccess(MdlBaseHttpResp<VideoPageBean> resp) {
        rvSearchWiki.refreshComplete();
        rvSearchWiki.loadMoreComplete();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp) {
            pageNo = resp.getData().getData().getPageNo();
            int totalCount = resp.getData().getData().getTotalCount();
            int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
            if (a == 0) {
                pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
            } else {
                pageCount = (totalCount / HttpConstant.PAGE_SIZE_NUMBER) + 1;
            }
            List<VideoBean> list = resp.getData().getData().getResult();
            for (VideoBean bean: list) {
                bean.setName(bean.getTitle());
            }
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    videoList.clear();
                }
                videoList.addAll(list);
                adapter.setData(videoList);
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
            }
        }
    }


    @Override
    public void onItemClick(VideoBean bean) {
        WebViewContainerActivity.go2this(this,getResources().getString(R.string.detail), HttpConstant.WEB_WORD_ARTICLE_DETAIL+bean.getId(),
                WebViewContainerFragment.TYPE_WORD_ARTICLE_DETAIL);
    }
}
