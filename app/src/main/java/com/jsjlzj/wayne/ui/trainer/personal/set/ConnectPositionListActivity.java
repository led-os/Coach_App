package com.jsjlzj.wayne.ui.trainer.personal.set;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.adapter.recycler.WrapContentLinearLayoutManager;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlPosition;
import com.jsjlzj.wayne.entity.store.MdlPositionList;
import com.jsjlzj.wayne.entity.trainer.MdlsaveAdvantage;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.trainer.publicac.PositionInfoStoreActivity;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jsjlzj.wayne.constant.HttpConstant.SIZE10;

/**
 * 1沟通过的职位   /   2感兴趣的职位
 */
public class ConnectPositionListActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    public static void go2this(Activity context) {//沟通过的职位
        Intent intent = new Intent(context, ConnectPositionListActivity.class);
        intent.putExtra("type", 1);
        context.startActivity(intent);
    }

    public static void go2this2(Activity context) {//感兴趣的职位
        Intent intent = new Intent(context, ConnectPositionListActivity.class);
        intent.putExtra("type", 2);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_connectlist;
    }

    private MyRecyclerView<Object> recyclerView;
    private MyRecyclerAdapter<MdlPosition.DataBean> recyclerViewAdapter;
    private List<MdlPosition.DataBean> list;
    private TextView toolbarTitle;
    private int type = 1;
    private Map<Object, Object> map;
    private int pageNo = 1;

    @Override
    protected void initViewAndControl() {
        type = getIntent().getIntExtra("type", 1);
        toolbarTitle = findView(R.id.toolbarTitle);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        if (type == 1) toolbarTitle.setText("沟通过的职位");
        else toolbarTitle.setText("感兴趣的职位");
        if(null==list)list=new ArrayList<>();
        getInfo();
        initRecycle();
    }

    public void getInfo() {
        if (null == map) map = new HashMap<>();
        map.clear();
        if(pageNo==1){
            list.clear();
        }
        map.put("pageNo", pageNo);
        map.put("pageSize", SIZE10);
        switch (type) {
            case 1:
                presenter.getPositionCommList(map);
                break;
            case 2:
                presenter.getPositionLikeList(map);
                break;
        }
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    private void initRecycle() {
        recyclerView = findView(R.id.rvPositionSelect);
        LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL));
        recyclerViewAdapter = new MyRecyclerAdapter<MdlPosition.DataBean>(this, R.layout.item_tab_position) {
            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, final MdlPosition.DataBean item, int position) {
                helper.setText(R.id.tvPositionName, item.getName());
                helper.setText(R.id.tvInfo, item.getStoreName());
                String area= TextUtils.isEmpty(item.getArea())?"":item.getArea();
                String workYears= TextUtils.isEmpty(item.getWorkYears())?"":item.getWorkYears();
                String education= TextUtils.isEmpty(item.getLowestEducationLevel())?"":item.getLowestEducationLevel();
                helper.setText(R.id.psition_info, item.getCity() + " " + area + " " +  workYears+ " " + education);
                helper.setText(R.id.tvSalary, item.getSalaryMin() + "-" + item.getSalaryMax() + "K");
                helper.setText(R.id.tvContent, item.getStoreUserName() + item.getStoreUserPosition());
                ImageView iv = helper.getView(R.id.image);
                setImg(item.getStoreUserHeadImg(), iv);

                helper.setOnClickListener(R.id.llItem, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PositionInfoStoreActivity.go2this(ConnectPositionListActivity.this,item.getId());
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
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setData(list);

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

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    finish();
                    break;
            }
        }
    }

    public void showPositionCommList(MdlBaseHttpResp<MdlPositionList> resp) {
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            list.addAll(resp.getData().getData().getResult());
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    public void showPositionLikeList(MdlBaseHttpResp<MdlPositionList> resp) {
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            list.addAll(resp.getData().getData().getResult());
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }
}