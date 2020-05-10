package com.jsjlzj.wayne.ui.store.find;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.find.ScanHistoryAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.dialog.CommonDialog;

import java.util.ArrayList;

import butterknife.BindView;

public class PlayHistoryActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.rv_week)
    RecyclerView rvWeek;
    @BindView(R.id.tv_last)
    TextView tvLast;
    @BindView(R.id.rv_last)
    RecyclerView rvLast;


    public static void go2this(Activity context) {
        Intent intent = new Intent(context, PlayHistoryActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_play_history;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initRightTitle("播放历史","清空");
        mRightTv.setOnClickListener(clickListener);
        rvWeek.setLayoutManager(new LinearLayoutManager(this));
        rvWeek.setAdapter(new ScanHistoryAdapter(this,new ArrayList<>()));

        rvLast.setLayoutManager(new LinearLayoutManager(this));
        rvLast.setAdapter(new ScanHistoryAdapter(this,new ArrayList<>()));
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        if(view.getId() == R.id.tv_right_btn){
            CommonDialog dialog = new CommonDialog(this, "即将清空全部播放历史，该操作不可恢复，是否继续？", new CommonDialog.ClickListener() {
                @Override
                public void clickConfirm() {
                    LogAndToastUtil.toast("确定");
                }

                @Override
                public void clickCancel() {}
            });
            dialog.show();
        }
    }
}
