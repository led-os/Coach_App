package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.MineSignUpAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.trainer.SignUpPageBean;
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
 * @ClassName: MineSignUpActivity
 * @Description: 我的报告
 * @Author: 曾海强
 * @CreateDate:
 */
public class MineSignUpActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView, XRecyclerView.LoadingListener {


    @BindView(R.id.tv_all_sign_up)
    TextView tvAllSignUp;
    @BindView(R.id.tv_match)
    TextView tvMatch;
    @BindView(R.id.tv_course)
    TextView tvCourse;
    @BindView(R.id.rv_sign_up)
    CustomXRecyclerView rvSignUp;

    private MineSignUpAdapter adapter;
    /**
     * 0:全部, 1:赛事, 2:课程
     */
    private int type;
    private int pageNo = 1;
    private int pageCount;
    private String module;
    private boolean isRefresh;
    private Map<Object, Object> map = new HashMap<>();
    private List<SignUpPageBean.DataBean.ResultBean> allList = new ArrayList<>();


    public static void go2this(Activity context) {
        Intent intent = new Intent(context, MineSignUpActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mine_sign_up;
    }

    @Override
    protected void initViewAndControl() {
        initTitle("我的报名");
        rvSignUp.setPullRefreshEnabled(true);
        rvSignUp.setLoadingMoreEnabled(true);
        rvSignUp.setLoadingListener(this);
        tvCourse.setOnClickListener(clickListener);
        tvMatch.setOnClickListener(clickListener);
        tvAllSignUp.setOnClickListener(clickListener);
        rvSignUp.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MineSignUpAdapter(this, allList);
        rvSignUp.setAdapter(adapter);
        loadData(true);
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }


    private void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if (isRefresh) {
            pageNo = 1;
        }
        map.clear();
        map.put(HttpConstant.PAGE_NO, pageNo);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        if(!TextUtils.isEmpty(module)){
            map.put("module", module);
        }
        presenter.getSignUpList(map);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.tv_all_sign_up://全部报名
                module = "";
                tvAllSignUp.setTextColor(ContextCompat.getColor(this, R.color.color_222222));
                tvCourse.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                tvMatch.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                loadData(true);
                break;
            case R.id.tv_match://赛事报名
                module = "sport_event";
                tvAllSignUp.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                tvCourse.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                tvMatch.setTextColor(ContextCompat.getColor(this, R.color.color_222222));
                loadData(true);
                break;
            case R.id.tv_course://课程报名
                module = "tao_learn";
                tvAllSignUp.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                tvCourse.setTextColor(ContextCompat.getColor(this, R.color.color_222222));
                tvMatch.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                loadData(true);
                break;
        }
    }

    @Override
    public void getSignUpListSuccess(MdlBaseHttpResp<SignUpPageBean> resp) {
        rvSignUp.refreshComplete();
        rvSignUp.loadMoreComplete();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp){
            pageNo = resp.getData().getData().getPageNo();
            int totalCount = resp.getData().getData().getTotalCount();
            int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
            if (a == 0) {
                pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
            } else {
                pageCount =( totalCount / HttpConstant.PAGE_SIZE_NUMBER)+1;
            }
            List<SignUpPageBean.DataBean.ResultBean> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    allList.clear();
                }
                allList.addAll(list);
                adapter.setData(allList);
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
            LogAndToastUtil.toast(this, getString(R.string.has_no_more_data));
        }
    }
}
