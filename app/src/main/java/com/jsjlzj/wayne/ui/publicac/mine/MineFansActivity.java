package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.search.SearchUserAdapter;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.search.ChannelListBean;
import com.jsjlzj.wayne.entity.store.search.ChannelPageBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
  *
  * @ClassName:      MineFansActivity
  * @Description:    我的粉丝/关注
  * @Author:         曾海强
  * @CreateDate:
  */
public class MineFansActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView, XRecyclerView.LoadingListener, SearchUserAdapter.OnSearchUserClickListener {


     @BindView(R.id.rv_fans)
     CustomXRecyclerView rvFans;

    /**
     * 0:我的粉丝  1 ： 我的关注
     */
    private int type;
    private int pageNo = 1,pageCount;
    private boolean isRefresh;
    private Map<Object,Object> map = new HashMap<>();
    private List<ChannelListBean> channelListBeans = new ArrayList<>();
    private SearchUserAdapter adapter;


    public static void go2this(Activity context,int type) {
         Intent intent = new Intent(context, MineFansActivity.class);
         intent.putExtra(ExtraConstant.EXTRA_SHOW_TYPE,type);
         context.startActivity(intent);
     }


     @Override
     protected int getLayoutResId() {
         return R.layout.activity_mine_fans;
     }

     @Override
     protected void initViewAndControl() {
         type = getIntent().getIntExtra(ExtraConstant.EXTRA_SHOW_TYPE,0);
         if(type == 0){
             initTitle("我的粉丝");
         }else {
             initTitle("我的关注");
         }
         rvFans.setPullRefreshEnabled(true);
         rvFans.setLoadingMoreEnabled(true);
         rvFans.setLoadingListener(this);
         rvFans.setLayoutManager(new LinearLayoutManager(this));
         adapter = new SearchUserAdapter(this, channelListBeans);
         adapter.setListener(this);
         rvFans.setAdapter(adapter);
         loadData(true);
     }


     @Override
     protected TalentTabFragmentPresenter createPresenter() {
         return new TalentTabFragmentPresenter(this);
     }

    public void loadData(boolean isRefresh){
        this.isRefresh = isRefresh;
        if (isRefresh) {
            pageNo = 1;
        }
        map.clear();
        map.put(HttpConstant.PAGE_NO, pageNo);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        if(type == 0){
            presenter.getMineFansList(map);
        }else {
            presenter.getMineFollowList(map);
        }
    }

    @Override
    public void getFensListSuccess(MdlBaseHttpResp<ChannelPageBean> resp) {
        rvFans.refreshComplete();
        rvFans.loadMoreComplete();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            pageNo = resp.getData().getData().getPageNo();
            int totalCount = resp.getData().getData().getTotalCount();
            int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
            if (a == 0) {
                pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
            } else {
                pageCount = (totalCount / HttpConstant.PAGE_SIZE_NUMBER) + 1;
            }
            List<ChannelListBean> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    channelListBeans.clear();
                }
                channelListBeans.addAll(list);
                adapter.setData(channelListBeans);
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
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
            LogAndToastUtil.toast(this, getString(R.string.has_no_more_data));
        }
    }

    @Override
    public void onItemClick(ChannelListBean string) {}

    @Override
    public void onFavoriteClick(ChannelListBean bean) {
        Map<Object, Object> map = new HashMap<>();
        map.put("id", bean.getId());
        if (!bean.isFollower()) {
            presenter.cancelFollow(map);
        } else {
            presenter.clickFollow(map);
        }
    }

    @Override
    public void getMessageSuccess(MdlBaseHttpResp<DataBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            LogAndToastUtil.toast(resp.getMsg());
        }
    }
}
