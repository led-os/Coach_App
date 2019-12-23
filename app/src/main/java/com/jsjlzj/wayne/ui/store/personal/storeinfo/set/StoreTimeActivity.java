package com.jsjlzj.wayne.ui.store.personal.storeinfo.set;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.ItemsBean;
import com.jsjlzj.wayne.entity.store.MdlDict;
import com.jsjlzj.wayne.entity.store.MdlStoreInfo;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.dialog.WorkTimeDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 俱乐部上班时间
 */
public class StoreTimeActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {
    private String workTimeStart;
    private String workTimeEnd;
    private String restTimeCode;
    private String workOvertimeCode;

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    public static void go2this(Activity context, String workTimeStart, String workTimeEnd, String restTimeCode, String workOvertimeCode) {
        Intent intent = new Intent(context, StoreTimeActivity.class);
        intent.putExtra("workOvertimeCode", workOvertimeCode);
        intent.putExtra("workTimeEnd", workTimeEnd);
        intent.putExtra("restTimeCode", restTimeCode);
        intent.putExtra("workTimeStart", workTimeStart);
        context.startActivityForResult(intent, RecruitActivity.FLAG_RECRUIT_CONTENT);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_store_info_time;
    }

    private List<TextView> tvRestTimeList, tvOverimeList;
    private TextView tvStoreTime;
    private List<ItemsBean> resetList,overList;

    @Override
    protected void initViewAndControl() {
        tvStoreTime = findView(R.id.tvStoreTime);
        resetList=new ArrayList<>();
        overList=new ArrayList<>();
        tvRestTimeList = new ArrayList<>();
        tvOverimeList = new ArrayList<>();
        tvRestTimeList.add(findView(R.id.tvRestTime1));
        tvRestTimeList.add(findView(R.id.tvRestTime2));
        tvRestTimeList.add(findView(R.id.tvRestTime3));
//        tvRestTimeList.add(findView(R.id.tvRestTime4));

        tvOverimeList.add(findView(R.id.tvOverTime1));
        tvOverimeList.add(findView(R.id.tvOverTime2));
        tvOverimeList.add(findView(R.id.tvOverTime3));
        workOvertimeCode = getIntent().getStringExtra("workOvertimeCode");
        workTimeEnd = getIntent().getStringExtra("workTimeEnd");
        restTimeCode = getIntent().getStringExtra("restTimeCode");
        workTimeStart = getIntent().getStringExtra("workTimeStart");
        MdlDict.DataBean bean=MyApp.mdlDict;
        if(null!=bean&&null!=bean.getRest_time()&&null!=bean.getWork_overtime()){
            resetList.addAll(bean.getRest_time().getItems());
            overList.addAll(bean.getWork_overtime().getItems());
        }else{
            presenter.getAll(null);
        }
        selectedRestTime();
        selectedOverTime();
        if (!TextUtils.isEmpty(workTimeStart) && !TextUtils.isEmpty(workTimeEnd)) {
            tvStoreTime.setText(workTimeStart + "-" + workTimeEnd);
        }
        for (int i = 0; i < tvRestTimeList.size(); i++) {
            tvRestTimeList.get(i).setOnClickListener(clickListener);
        }
        for (int i = 0; i < tvOverimeList.size(); i++) {
            tvOverimeList.get(i).setOnClickListener(clickListener);
        }

        findView(R.id.llStoreTime).setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnKeep).setOnClickListener(clickListener);
    }

    private Map<Object, Object> map = null;
    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            if (null == map) map = new HashMap<>();
            switch (view.getId()) {
                case R.id.btnKeep://保存
                    submit();
                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
                case R.id.llStoreTime:
                    showWorkTimeDialog();
                    break;
                case R.id.tvRestTime1:
                case R.id.tvRestTime2:
                case R.id.tvRestTime3:
                    clickRestTime(view);
                    break;
                case R.id.tvOverTime1:
                case R.id.tvOverTime2:
                case R.id.tvOverTime3:
                    clickOverTime(view);
                    break;
            }
        }
    }

    public void submit() {
        for (int i = 0; i < tvOverimeList.size(); i++) {
            if (tvOverimeList.get(i).isSelected()) {
                workOvertimeCode = overList.get(i).getCode();
            }
        }
        for (int i = 0; i < tvRestTimeList.size(); i++) {
            if (tvRestTimeList.get(i).isSelected()) {
                restTimeCode = resetList.get(i).getCode();
            }
        }
        if (!TextUtils.isEmpty(workTimeStart) && !TextUtils.isEmpty(workTimeEnd)) {

            map.put("restTimeCode", restTimeCode);
            map.put("workOvertimeCode", workOvertimeCode);
            map.put("workTimeStart", workTimeStart);
            map.put("workTimeEnd", workTimeEnd);
            presenter.saveWorkTime(map);
        } else {
            LogAndToastUtil.toast(StoreTimeActivity.this, "请选择工作时间");
        }
    }

    private void clickRestTime(View view) {
        for (int i = 0; i < tvRestTimeList.size(); i++) {
            tvRestTimeList.get(i).setSelected(false);
        }
        view.setSelected(true);

    }


    private void selectedRestTime() {
        for (int i = 0; i < tvRestTimeList.size(); i++) {
            if (resetList.get(i).getCode().equals(restTimeCode)) {
                tvRestTimeList.get(i).setSelected(true);
            } else {
                tvRestTimeList.get(i).setSelected(false);
            }
        }
    }

    private void selectedOverTime() {
        for (int i = 0; i < tvOverimeList.size(); i++) {
            if (overList.get(i).getCode().equals(workOvertimeCode)) {
                tvOverimeList.get(i).setSelected(true);

            } else {
                tvOverimeList.get(i).setSelected(false);
            }
        }
    }

    private void clickOverTime(View view) {
        for (int i = 0; i < tvOverimeList.size(); i++) {
            tvOverimeList.get(i).setSelected(false);
        }
        view.setSelected(true);
    }

    public void showSaveWorkTime(MdlBaseHttpResp<MdlStoreInfo> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            LogAndToastUtil.toast(this, "保存成功");
            finish();
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }

    private void showWorkTimeDialog() {
        new WorkTimeDialog(this, new WorkTimeDialog.ClickListener() {
            @Override
            public void clickConfirm(String startTime, String endTime) {
                if (!TextUtils.isEmpty(startTime) && !TextUtils.isEmpty(endTime)) {
                    tvStoreTime.setText(startTime + "-" + endTime);
                    workTimeStart = startTime;
                    workTimeEnd = endTime;
                }
            }
        }).show();
    }

    @Override
    public void showResultgetAll(MdlBaseHttpResp<MdlDict> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MdlDict.DataBean dataBean = resp.getData().getData();
            MyApp.mdlDict = dataBean;
            resetList.addAll(dataBean.getRest_time().getItems());
            overList.addAll(dataBean.getWork_overtime().getItems());        }
    }
}