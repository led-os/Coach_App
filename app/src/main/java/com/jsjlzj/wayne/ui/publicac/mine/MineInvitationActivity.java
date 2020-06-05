package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.MineInvitationAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.trainer.InvitationBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

 /**
  *
  * @ClassName:      MineInvitationActivity
  * @Description:    我的邀请
  * @Author:         曾海强
  * @CreateDate:
  */
public class MineInvitationActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.tv_trainer)
    TextView tvTrainer;
    @BindView(R.id.tv_store)
    TextView tvStore;
    @BindView(R.id.rv_mine_invitation)
    CustomXRecyclerView rvMineInvitation;


    private MineInvitationAdapter adapter;
     /**
      * 0:教练和俱乐部, 1:教练, 2:俱乐部
      */
     private int type;
     private int pageNo = 1;
     private int pageCount;
     private boolean isRefresh;
     private Map<Object, Object> map = new HashMap<>();
     private List<InvitationBean.DataBean.ResultBean> invitationList = new ArrayList<>();

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, MineInvitationActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mine_invitation;
    }

    @Override
    protected void initViewAndControl() {
        initTitle("我的邀请");
        tvAll.setOnClickListener(clickListener);
        tvTrainer.setOnClickListener(clickListener);
        tvStore.setOnClickListener(clickListener);

        rvMineInvitation.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MineInvitationAdapter(this,invitationList);
        rvMineInvitation.setAdapter(adapter);
        loadData(true);
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.tv_all://全部
                type = 0;
                tvAll.setTextColor(ContextCompat.getColor(this,R.color.color_222222));
                tvStore.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvTrainer.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                loadData(true);
                break;
            case R.id.tv_trainer://教练
                type = 1;
                tvAll.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvStore.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvTrainer.setTextColor(ContextCompat.getColor(this,R.color.color_222222));
                loadData(true);
                break;
            case R.id.tv_store://俱乐部
                type = 2;
                tvAll.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                tvStore.setTextColor(ContextCompat.getColor(this,R.color.color_222222));
                tvTrainer.setTextColor(ContextCompat.getColor(this,R.color.color_666666));
                loadData(true);
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
         map.put("type",type);
         presenter.getInvitationList(map);
     }


     @Override
     public void getInvitationListSuccess(MdlBaseHttpResp<InvitationBean> resp) {
         rvMineInvitation.refreshComplete();
         rvMineInvitation.loadMoreComplete();
         if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
             pageNo = resp.getData().getData().getPageNo();
             int totalCount = resp.getData().getData().getTotalCount();
             int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
             if (a == 0) {
                 pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
             } else {
                 pageCount = (totalCount / HttpConstant.PAGE_SIZE_NUMBER) + 1;
             }
             List<InvitationBean.DataBean.ResultBean> list = resp.getData().getData().getResult();
             if (list != null && list.size() > 0) {
                 hideEmpty();
                 if (isRefresh) {
                     this.invitationList.clear();
                 }
                 this.invitationList.addAll(list);
                 adapter.setData(invitationList);
             } else if (isRefresh) {
                 // 无数据
                 showEmpty(R.id.rel_empty, 0, null);
             }
         }
     }
 }
