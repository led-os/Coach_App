package com.jsjlzj.wayne.ui.publicac.help;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.adapter.recycler.WrapContentLinearLayoutManager;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlQuestion;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

/**
 * 帮助
 */
public class HelpActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, HelpActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_help;
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    private MyRecyclerView<MdlQuestion.DataBean> recyclerView;
    private MyRecyclerAdapter<MdlQuestion.DataBean> recyclerViewAdapter;

    @Override
    protected void initViewAndControl() {
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnRight).setOnClickListener(clickListener);
        initRecycle();
    }

    private void initRecycle() {
        recyclerView = findView(R.id.rvPositionSelect);
        LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL));
        recyclerViewAdapter = new MyRecyclerAdapter<MdlQuestion.DataBean>(this, R.layout.item_questionslist) {
            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, final MdlQuestion.DataBean item, int position) {
                helper.setText(R.id.tvPositionName, TextUtils.isEmpty(item.getTitle())?"":item.getTitle());
                helper.setOnClickListener(R.id.llItem, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HelpProblemInfoActivity.go2this(HelpActivity.this,item);
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
        presenter.faqList(null);
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
                case R.id.btnBack:
                    finish();
                    break;
                case R.id.btnRight:
                    HelpFeedbackProblemActivity.go2this(HelpActivity.this);
                    break;
            }
        }
    }

    @Override
    public void showResult(MdlBaseHttpResp<MdlQuestion> resp){
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            recyclerViewAdapter.setData(resp.getData().getData());
        }
    }

}