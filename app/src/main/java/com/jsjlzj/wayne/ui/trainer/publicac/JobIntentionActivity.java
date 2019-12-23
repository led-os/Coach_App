package com.jsjlzj.wayne.ui.trainer.publicac;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.adapter.recycler.WrapContentLinearLayoutManager;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;
import com.jsjlzj.wayne.entity.trainer.MdlWorkStatus;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.Utility;
import com.jsjlzj.wayne.widgets.MyRecyclerView;
import com.jsjlzj.wayne.widgets.dialog.PositinStateDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 求职期望
 */
public class JobIntentionActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {
    public final static int FLAG_RECRUIT_CONTENT = 0x001;
    private List<MdlDetailT.DataBean.WorkHopeListBean> workHopeList;
    private String workStatusCode;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, JobIntentionActivity.class);
        intent.putExtra("isResult", "");
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //简历详情
        workHopeList.clear();
        presenter.getDetailT(new HashMap<>());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_jobintention;
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    private TextView tvState;
    private EditText edIntention;
    private MyRecyclerView<MdlDetailT.DataBean.WorkHopeListBean> recyclerView;
    private MyRecyclerAdapter<MdlDetailT.DataBean.WorkHopeListBean> recyclerViewAdapter;

    @Override
    protected void initViewAndControl() {
        MyApp.ME.dm= Utility.getDisplayScreenSize(this);
        tvState = findView(R.id.tvState);
        edIntention = findView(R.id.edIntention);
        findView(R.id.llState).setOnClickListener(clickListener);
        findView(R.id.btnConfirm).setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        if(null==workHopeList)workHopeList=new ArrayList<>();
        initRecycle();
    }

    private void initRecycle() {
        recyclerView = findView(R.id.rvJobintention);
        LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);

//        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL));
        recyclerViewAdapter = new MyRecyclerAdapter<MdlDetailT.DataBean.WorkHopeListBean>(this, R.layout.item_position_jobintention2) {
            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, final MdlDetailT.DataBean.WorkHopeListBean item, int position) {
                if (item != null) setUI(helper, item);
                helper.setOnClickListener(R.id.llItem, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddJobIntentionActivity.go2this(JobIntentionActivity.this, workHopeList.get(position));
                    }
                });
            }
        };
        recyclerViewAdapter.setData(workHopeList);
        recyclerView.setAdapter(recyclerViewAdapter);

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
    }



    private void setUI(BaseAdapterHelper helper,MdlDetailT.DataBean.WorkHopeListBean item) {
        helper.setText(R.id.tvPositionName, item.getPosition());
        helper.setText(R.id.tvInfo, item.getProvince()+" "+item.getCity());
        helper.setText(R.id.tvSalary, item.getSalaryMin()+"-"+item.getSalaryMax()+"K");
//        helper.setText(R.id.psition_info, item.getProvince()+" "+item.getCity()+" "+item.getArea()+"  "+item.getWorkYears()+"  "+item.getLowestEducationLevel());
//        helper.setText(R.id.tvContent, item.getStoreUserName()+"  "+item.getStoreUserPosition());
//        ImageView iv=helper.getView(R.id.headImg);
//        setImg(item.getStoreUserHeadImg(),iv);
    }

    private void loadMore() {
        recyclerView.loadMoreComplete();
    }

    private void loadRefresh() {
        recyclerView.refreshComplete();
    }


    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.llState://求职状态
                    showEducationDialog();
                    break;
                case R.id.btnConfirm://添加期望
                    AddJobIntentionActivity.go2this(JobIntentionActivity.this);
                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
            }
        }
    }
    private void showEducationDialog() {
        PositinStateDialog dialog= new PositinStateDialog(this, new PositinStateDialog.ClickListener() {
            @Override
            public void clickConfirm(String data,String code) {
                Log.e("bby",data+code);
                workStatusCode = code;
                Map<Object, Object> param = new HashMap<>();
                param.put("workStatusCode", code);
                param.put("workStatus", data);
                presenter.saveWorkStatusT(param);
            }
        });
        dialog.show();
        dialog.setTitle("请选择求职状态");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null)return;
        switch (requestCode) {
            case 0:
                break;
        }
    }



    @Override
    public void getDetailT(MdlBaseHttpResp<MdlDetailT> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MdlDetailT.DataBean data = resp.getData().getData();
            tvState.setText(data.getWorkStatus());
            workStatusCode = data.getWorkStatusCode();
            if(null!=data.getWorkHopeList()&&data.getWorkHopeList().size()>0) {
                workHopeList.addAll(data.getWorkHopeList());
                recyclerViewAdapter.notifyDataSetChanged();
            }

        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }

    @Override
    public void saveWorkStatusT(MdlBaseHttpResp<MdlWorkStatus> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MdlWorkStatus.DataBean data = resp.getData().getData();
            tvState.setText(data.getWorkStatus());
            workStatusCode = data.getWorkStatusCode();
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }

}