package com.jsjlzj.wayne.ui.store.personal.manage;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.adapter.recycler.WrapContentLinearLayoutManager;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlCV;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.trainer.personal.PositionPreviewActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jsjlzj.wayne.constant.HttpConstant.SIZE10;

/**
 * 我感兴趣的达人   沟通过的达人
 */
public class ConnectListActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {
    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    public static void go2this(Activity context) {//沟通过的达人
        Intent intent = new Intent(context, ConnectListActivity.class);
        intent.putExtra("type", 1);
        context.startActivity(intent);
    }

    public static void go2this2(Activity context) {//我感兴趣的达人
        Intent intent = new Intent(context, ConnectListActivity.class);
        intent.putExtra("type", 2);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_connectlist;
    }

    private MyRecyclerView<MdlCV.DataBean.ResultBean> recyclerView;
    private MyRecyclerAdapter<MdlCV.DataBean.ResultBean> recyclerViewAdapter;
    private List<MdlCV.DataBean.ResultBean> listAll;
    private TextView toolbarTitle;
    private int type = 1;
    private int pageNo=1;
    private Map<Object, Object> map;

    @Override
    protected void initViewAndControl() {

        type = getIntent().getIntExtra("type", 1);
        toolbarTitle = findView(R.id.toolbarTitle);
        listAll=new ArrayList<>();
        findView(R.id.btnBack).setOnClickListener(clickListener);
        if (type == 1) {
            toolbarTitle.setText("沟通过的达人");
        } else {
            toolbarTitle.setText("我感兴趣的达人");
        }
        initRecycle();
        getInfo();
    }

    public void getInfo(){
        if (null == map) map = new HashMap<>();
        map.clear();
        if(pageNo==1&&null!=listAll){
            listAll.clear();
            recyclerViewAdapter.notifyDataSetChanged();
        }
        map.put("pageNo",pageNo);
        map.put("pageSize",SIZE10);
        if (type == 1) {
            presenter.getCommunitList(map);
        } else {
            presenter.getCVLike(map);
        }
    }

    private void initRecycle() {
        recyclerView = findView(R.id.rvPositionSelect);
        LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new MyRecyclerAdapter<MdlCV.DataBean.ResultBean>(this, R.layout.item_connectlist) {
            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, final MdlCV.DataBean.ResultBean item, int position) {
                if(item!=null)setUI(helper,item);
                helper.setOnClickListener(R.id.llItem, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PositionPreviewActivity.go2this(ConnectListActivity.this, item.getId());
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
        recyclerViewAdapter.setData(listAll);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    private void setUI(BaseAdapterHelper helper, MdlCV.DataBean.ResultBean item) {
        helper.setText(R.id.tvPositionName, item.getName());
        String education= TextUtils.isEmpty(item.getHighestEducationLevel())?"":item.getHighestEducationLevel();
        helper.setText(R.id.tvInfo, item.getWorkYears()+"年 "+education+" "+item.getAge()+"岁");
        helper.setText(R.id.tvPositionName, item.getName());
        helper.setText(R.id.tvContent, item.getAdvantage());
        helper.setText(R.id.tvTime, item.getCreateTime());
        helper.setText(R.id.tvClub, item.getPosition());
        ImageView iv=helper.getView(R.id.headImg);
//        if(item.getHeadImg())
        setImg(item.getHeadImg(),iv);
        FlexboxLayout llSkill=helper.getView(R.id.llLabel);
        llSkill.removeAllViews();
        if(null!=item.getSkillTags()&&item.getSkillTags().size()>0){
            for (int i = 0; i < item.getSkillTags().size(); i++) {
                TextView tv=new TextView(this);
                tv.setTextSize(13);
                FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,0,30,30);
                tv.setLayoutParams(params);
                tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.dr_bg_tv_g));
                tv.setPadding(30, 14,30,14);
                tv.setText(item.getSkillTags().get(i));
                llSkill.addView(tv);
            }
        }
//        setImg(item.getHeadImg(),iv);
    }

    private void loadMore() {
        pageNo++;
        getInfo();
        recyclerView.loadMoreComplete();
    }

    private void loadRefresh() {
        pageNo=1;
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

    @Override
    public void showCVCommunit(MdlBaseHttpResp<MdlCV> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()&&null!=resp.getData().getData().getResult()) {
            listAll.addAll(resp.getData().getData().getResult());
            recyclerViewAdapter.notifyDataSetChanged();
        }else{
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

    @Override
    public void showCVLike(MdlBaseHttpResp<MdlCV> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()&&null!=resp.getData().getData().getResult()) {
            listAll.addAll(resp.getData().getData().getResult());
            recyclerViewAdapter.notifyDataSetChanged();
        }else{
            LogAndToastUtil.toast(resp.getMsg());
        }
    }
}