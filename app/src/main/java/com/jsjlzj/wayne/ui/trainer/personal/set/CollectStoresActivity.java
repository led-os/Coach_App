package com.jsjlzj.wayne.ui.trainer.personal.set;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlCV;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.store.personal.storeinfo.set.StoreInfoPreviewActivity;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jsjlzj.wayne.constant.HttpConstant.SIZE10;

public class CollectStoresActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView  {
    public static void go2this(Activity context) {
        Intent intent = new Intent(context, CollectStoresActivity.class);
        intent.putExtra("isResult", "");
        context.startActivity(intent);
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_collect_stores;
    }

    private MyRecyclerView recyclerView;
    private MyRecyclerAdapter<MdlCV.DataBean.ResultBean> adapter;
    private List<MdlCV.DataBean.ResultBean> list;
    private Map<Object,Object> map;
    private int pageNo=1;
    @Override
    protected void initViewAndControl() {
        recyclerView=findView(R.id.recyclerView);
        if(null==map)map=new HashMap<>();
        if(null==list)list=new ArrayList<>();
        initRecycle();
       getInfo();
    }
    private void getInfo(){
        if (pageNo == 1) {
            list.clear();
            adapter.notifyDataSetChanged();
        }
        map.put("pageNo",pageNo);
        map.put("pageSize",SIZE10);
        presenter.getStoreLikeList(map);
    }

    private void initRecycle() {
        adapter=new MyRecyclerAdapter<MdlCV.DataBean.ResultBean>(this,R.layout.item_collectstore) {
            @Override
            public int getItemCount() {
                return list==null?0:list.size();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, MdlCV.DataBean.ResultBean item, int position) {
                if(item==null)return;
                helper.setText(R.id.tvStoreName,item.getCompanyName());
                helper.setText(R.id.tvStoreAddress,item.getStoreAddress());
                helper.setText(R.id.tvStoreNum,item.getStaffNum());
                ImageView iv=helper.getView(R.id.headImg);
                setImg(item.getBrandLogo(),iv);
                helper.setOnClickListener(new OnMultiClickListener() {
                    @Override
                    public void OnMultiClick(View view) {
                        StoreInfoPreviewActivity.go2this(CollectStoresActivity.this,item.getId());
                    }
                });
            }
        };
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                loadRefresh();
            }

            @Override
            public void onLoadMore() {
                loadMore();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter.setData(list);
        recyclerView.setAdapter(adapter);
    }
    private void loadMore() {
        pageNo++;
        getInfo();
        recyclerView.loadMoreComplete();
    }

    private void loadRefresh() {
        pageNo = 1;
        getInfo();
        recyclerView.refreshComplete();
    }
    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    public void showStoreInfoLikePage(MdlBaseHttpResp<MdlCV> resp) {
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            list.addAll(resp.getData().getData().getResult());
            adapter.notifyDataSetChanged();
        }
    }
}
