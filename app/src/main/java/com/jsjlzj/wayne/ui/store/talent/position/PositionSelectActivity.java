package com.jsjlzj.wayne.ui.store.talent.position;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

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
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jsjlzj.wayne.constant.HttpConstant.SIZE10;

/**
 * 职位选择
 */
public class PositionSelectActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, PositionSelectActivity.class);
        intent.putExtra("isResult", "");
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_position_select;
    }


    private MyRecyclerView<MdlCV.DataBean.ResultBean> recyclerView;
    private MyRecyclerAdapter<MdlCV.DataBean.ResultBean> recyclerViewAdapter;
    private List<MdlCV.DataBean.ResultBean> listAll;
    private Button btnConfirm;
    private View statusLayout;
    private int currentPage = 1;
    private int status = 0;

    @Override
    protected void initViewAndControl() {
        btnConfirm = findView(R.id.btnConfirm);
        statusLayout = findView(R.id.statusLayout);
        btnConfirm.setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.toolbarTitle).setOnClickListener(clickListener);

        findView(R.id.statusAll).setOnClickListener(clickListener);
        findView(R.id.statusOpen).setOnClickListener(clickListener);
        findView(R.id.statusWait).setOnClickListener(clickListener);
        findView(R.id.statusClose).setOnClickListener(clickListener);
        findView(R.id.statusFail).setOnClickListener(clickListener);
        listAll=new ArrayList<>();
        initRecycle();
    }

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    Map<Object, Object> map;

    private void initRecycle() {
        recyclerView = findView(R.id.rvPositionSelect);
        LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL));
        recyclerViewAdapter = new MyRecyclerAdapter<MdlCV.DataBean.ResultBean>(this, R.layout.item_position_select) {
            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, final MdlCV.DataBean.ResultBean item, int position) {
                helper.setText(R.id.tvPositionName, item.getName());
                helper.setText(R.id.tvSalary, item.getSalaryMin() + "-" + item.getSalaryMax() + "K");
                switch (item.getStatus()) {
                    case 1:
                        helper.setText(R.id.tvStatus, "待开放");
                        helper.setVisible(R.id.tvStatus, true);
                        helper.setTextColor(R.id.tvStatus, getResources().getColor(R.color.text_red));
                        break;
                    case 2:
                        helper.setVisible(R.id.tvStatus, false);
                        break;
                    case 3:
                        helper.setText(R.id.tvStatus, "审核失败");
                        helper.setVisible(R.id.tvStatus, true);
                        helper.setTextColor(R.id.tvStatus, getResources().getColor(R.color.text_red));
                        break;
                    case 4:
                        helper.setVisible(R.id.tvStatus, true);
                        helper.setText(R.id.tvStatus, "已关闭");
                        helper.setTextColor(R.id.tvStatus, getResources().getColor(R.color.login_title));
                        break;
                    default:
                        helper.setVisible(R.id.tvStatus, false);
                        break;
                }
                String education=TextUtils.isEmpty(item.getLowestEducationLevel())?"不限":item.getLowestEducationLevel();
                String workYears=TextUtils.isEmpty(item.getWorkYears())?"不限":item.getWorkYears();
                helper.setText(R.id.tvInfo, item.getProvince() + item.getCity() + "  " + education + " " + workYears);
                helper.setOnClickListener(R.id.llItem, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PositionInfoActivity.go2this(PositionSelectActivity.this,item.getId());
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
        recyclerViewAdapter.setData(listAll);
//        getInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRefresh();
    }

    public void getInfo(){
       if(null==map) map =new HashMap<>();
       map.clear();
       if(currentPage==1){
           listAll.clear();
           recyclerViewAdapter.notifyDataSetChanged();
       }
        map.put("pageNo",currentPage);
        map.put("pageSize",SIZE10);
        map.put("status",status);
        presenter.queryByStatus(map);
    }

    private void loadMore() {
        currentPage++;
        getInfo();
        recyclerView.loadMoreComplete();
    }

    private void loadRefresh() {
        currentPage=1;
        getInfo();
        recyclerView.refreshComplete();
    }
    private boolean isShow=false;
    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    finish();
                    break;
                case R.id.toolbarTitle:
                    isShow=!isShow;
                    statusLayout.setVisibility(isShow ? View.VISIBLE : View.GONE);
                    break;
                case R.id.statusAll:
                    status = 0;
                    isShow=false;
                    statusLayout.setVisibility(View.GONE);
                    loadRefresh();
                    break;
                case R.id.statusOpen:
                    status = 2;
                    isShow=false;
                    statusLayout.setVisibility(View.GONE);
                    loadRefresh();
                    break;
                case R.id.statusWait:
                    status = 1;
                    isShow=false;
                    statusLayout.setVisibility(View.GONE);
                    loadRefresh();
                    break;
                case R.id.statusClose:
                    status = 4;
                    isShow=false;
                    statusLayout.setVisibility(View.GONE);
                    loadRefresh();
                    break;
                case R.id.statusFail:
                    status = 3;
                    isShow=false;
                    statusLayout.setVisibility(View.GONE);
                    loadRefresh();
                    break;
                case R.id.btnConfirm:
                    RecruitActivity.go2this(PositionSelectActivity.this,"");
                    break;
            }
        }
    }

    public void showPositionList(MdlBaseHttpResp<MdlCV> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            if (null != resp.getData().getData().getResult()) {
                listAll.addAll(resp.getData().getData().getResult());
                recyclerViewAdapter.notifyDataSetChanged();
            }
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }
}