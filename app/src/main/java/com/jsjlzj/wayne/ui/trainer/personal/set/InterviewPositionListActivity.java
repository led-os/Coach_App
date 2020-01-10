package com.jsjlzj.wayne.ui.trainer.personal.set;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.adapter.recycler.WrapContentLinearLayoutManager;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlInterView;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.store.personal.storeinfo.InterviewDetailActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jsjlzj.wayne.constant.HttpConstant.SIZE10;

/**
 * 面试日程
 */
public class InterviewPositionListActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, InterviewPositionListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_interviewlist;
    }

    private MyRecyclerView recyclerView;
    private MyRecyclerAdapter<MdlInterView.DataBean.ResultBean> recyclerViewAdapter;
    private List<MdlInterView.DataBean.ResultBean> list;
    private Map<Object,Object> map;
    private int pageNo=1;
    @Override
    protected void initViewAndControl() {
        findView(R.id.btnBack).setOnClickListener(clickListener);
        if(list==null)list=new ArrayList<>();
        initRecycle();
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);    }

    private void getInfo(){
        if(map==null)map=new HashMap<>();
        if(pageNo==1){
            list.clear();
            recyclerViewAdapter.notifyDataSetChanged();
        }
        map.put("pageNo",pageNo);
        map.put("pageSize",SIZE10);
        presenter.getInterViewTrainer(map);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRefresh();
    }

    private void initRecycle() {
        recyclerView = findView(R.id.rvPositionSelect);
        LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL));
        recyclerViewAdapter = new MyRecyclerAdapter<MdlInterView.DataBean.ResultBean>(this, R.layout.item_interview_position_list) {
            @Override
            public int getItemViewType(int position) {
                return R.layout.item_interview_position_list;
            }

            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, final MdlInterView.DataBean.ResultBean item, int position) {
                if(null==item)return;
                String[] times=item.getInterviewTime().split("\\s+");
                helper.setText(R.id.tvPositionName,item.getStoreName());
                helper.setText(R.id.tvPosition,item.getPositionName());
                helper.setText(R.id.tvSalary,item.getSalaryMin()+"-"+item.getSalaryMax()+"K");
                if(times!=null&&times.length==2) {
                    helper.setText(R.id.tvDate, times[0]);
                    helper.setText(R.id.tvTime, times[1]);
                }
                ImageView iv=helper.getView(R.id.image);
                setImg(item.getHeadImg(),iv);
                if(item.getTrainerStatus().equals("0")){
                    helper.setText(R.id.tvStatus,"等待面试");
                    helper.setVisible(R.id.tvStatus,true);
                }else{
                    helper.setVisible(R.id.tvStatus,false);
                }
                helper.setOnClickListener(R.id.llItem, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        InterviewDetailActivity.go2this(InterviewPositionListActivity.this,item.getId(),2);
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
        recyclerViewAdapter.setData(list);
        recyclerView.setAdapter(recyclerViewAdapter);
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

    public void showResultgetInterViewTrainer(MdlBaseHttpResp<MdlInterView> resp){
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            list.addAll(resp.getData().getData().getResult());
            recyclerViewAdapter.notifyDataSetChanged();
        }else{
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

}