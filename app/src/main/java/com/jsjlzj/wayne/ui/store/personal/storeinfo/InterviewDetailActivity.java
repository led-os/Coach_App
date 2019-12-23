package com.jsjlzj.wayne.ui.store.personal.storeinfo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.constant.MyPermissionConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlInterViewDetail;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.store.personal.storeinfo.set.NavigationActivity;
import com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity;
import com.jsjlzj.wayne.ui.trainer.personal.PositionPreviewActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.PositionInfoStoreActivity;
import com.jsjlzj.wayne.ui.yunxin.YunXingUtil;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.jsjlzj.wayne.widgets.dialog.PhoneDialog;

import java.util.HashMap;
import java.util.Map;

//达人以及门店端面试详情页面
public class InterviewDetailActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_interview_detail;
    }

    private TextView tvRemark, tvTrainerName, tvInterviewTime, tvTime, tvWork, tvLocation, tvNavigation, getTrainer, wait_interview, btnConfirm;
    private ImageView headImg;
    private Button btnRefuse;
    private String id;
    private int type;
    private Map<Object, Object> map;
    private MdlInterViewDetail.DataBean bean;
    private boolean isTrainer = MyApp.isTrainer;
    private int isRefuse = 0;

    public static void go2this(Activity context, String id) {
        Intent intent = new Intent(context, InterviewDetailActivity.class);
        intent.putExtra("id", id);
        context.startActivityForResult(intent, RecruitActivity.FLAG_RECRUIT_CONTENT);
    }

    public static void go2this(Activity context, String id, int type) {
        Intent intent = new Intent(context, InterviewDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);//1门店端2 教练端
        context.startActivityForResult(intent, RecruitActivity.FLAG_RECRUIT_CONTENT);
    }

    @Override
    protected void initViewAndControl() {
        id = getIntent().getStringExtra("id");
        type = getIntent().getIntExtra("type", 0);
        if (isTrainer) {
            type = 2;
        } else {
            type = 1;
        }
        tvTrainerName = findView(R.id.tvTrainerName);
        tvInterviewTime = findView(R.id.tvInterviewTime);
        headImg = findView(R.id.headImg);
        getTrainer = findView(R.id.getTrainer);
        tvNavigation = findView(R.id.tvNavigation);
        tvTime = findView(R.id.tvTime);
        tvWork = findView(R.id.tvWork);
        tvLocation = findView(R.id.tvLocation);
        tvRemark = findView(R.id.tvRemark);
        getTrainer.setOnClickListener(clickListener);
        tvNavigation.setOnClickListener(clickListener);
        findView(R.id.tvCall).setOnClickListener(clickListener);
        findView(R.id.tvChat).setOnClickListener(clickListener);
        btnRefuse = findView(R.id.btnRefuse);
        wait_interview = findView(R.id.wait_interview);
        btnRefuse.setOnClickListener(clickListener);
        btnConfirm = findView(R.id.btnConfirm);
        btnConfirm.setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        if (type == 2) {
            getTrainer.setText("职位详情");
            btnRefuse.setVisibility(View.VISIBLE);
            tvNavigation.setVisibility(View.VISIBLE);
            btnConfirm.setText("接受面试");
            isTrainer = true;
            wait_interview.setVisibility(View.GONE);
        } else {
            //门店端
            isTrainer = false;
            btnConfirm.setText("取消面试");
            btnRefuse.setVisibility(View.GONE);
            getTrainer.setText("查看达人卡");
            wait_interview.setVisibility(View.VISIBLE);
            tvNavigation.setVisibility(View.GONE);

        }
        if (null == map) map = new HashMap<>();
        map.put("id", id);
        presenter.getInterViewDetail(map);
    }

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    finish();
                    break;
                case R.id.tvCall://打电话
                    PhoneDialog phoneDialog = new PhoneDialog(InterviewDetailActivity.this, new PhoneDialog.ClickListener() {
                        @Override
                        public void clickConfirm(String data) {
                            clickCallPhone();
                        }
                    });
                    phoneDialog.show();
                    phoneDialog.setPhone(bean.getMobile());
                    break;
                case R.id.tvChat://聊天
                    if (bean != null) {
                        YunXingUtil.toChatRoom(InterviewDetailActivity.this, bean.getYunXinAccount(), bean.getWorkHopeId(),
                                bean.getWorkHopeName(), bean.getPositionId(), MyApp.isTrainer);
                    }
                    break;

                case R.id.btnRefuse://拒绝面试，达人可见
                    map.clear();
                    map.put("id", id);
                    map.put("status", 2);
                    isRefuse = 0;
                    presenter.getInterViewStatus(map);
                    break;
                case R.id.btnConfirm://接受面试/取消面试
                    map.clear();
                    if (type == 2) {
                        map.put("id", id);
                        if (btnConfirm.getText().toString().equals("接受面试")) {
                            map.put("status", 1);
                            isRefuse = 1;
                        } else {
                            isRefuse = 2;
                            map.put("status", 3);
                        }
                        presenter.getInterViewStatus(map);
                    } else {
                        map.put("id", id);
                        presenter.getInterViewCancel(map);
                    }

                    break;
                case R.id.tvNavigation://导航
                    if (null != bean) {
                        NavigationActivity.go2this(InterviewDetailActivity.this, bean.getAddress(), bean.getCoordinate());
                    }
                    break;
                case R.id.getTrainer://查看达人卡/职位信息
                    if (null != bean) {
                        if (type == 2) {
                            PositionInfoStoreActivity.go2this(InterviewDetailActivity.this, bean.getPositionId());
                        } else {
                            PositionPreviewActivity.go2this(InterviewDetailActivity.this, bean.getWorkHopeId());
                        }
                    }
                    break;
            }
        }
    }

    private void clickCallPhone() {
        PermissionUtil.checkPermission(
                this,
                MyPermissionConstant.CALL_PHONE,
                Manifest.permission.CALL_PHONE);
    }

    @Override
    public void permissionSuccess(int permissionReqCode) {
        super.permissionSuccess(permissionReqCode);
        switch (permissionReqCode) {
            case MyPermissionConstant.CALL_PHONE:
                Intent intent = new Intent();
                //设置拨打电话的动作
                intent.setAction(Intent.ACTION_DIAL);//ACTION_DIAL  ACTION_CALL
                //设置拨打电话的号码
                intent.setData(Uri.parse("tel:" + bean.getMobile()));
                //开启打电话的意图
                startActivity(intent);
                break;
        }
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    public void showResultgetInterViewDetail(MdlBaseHttpResp<MdlInterViewDetail> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            bean = resp.getData().getData();
            switch (type) {
                case 1:
                    tvTrainerName.setText(bean.getName());

                    break;
                case 2:
                    tvTrainerName.setText(bean.getName());

                    break;
            }
            setImg(bean.getHeadImg(), headImg);
            tvWork.setText(bean.getPositionName() + " " + bean.getSalaryMin() + "-" + bean.getSalaryMax() + "K");
            tvLocation.setText(bean.getAddress());
            tvRemark.setText(bean.getInterviewMark());
            if (bean.getTrainerStatus().equals("0") && !isTrainer) {
                wait_interview.setVisibility(View.VISIBLE);
            } else {
                wait_interview.setVisibility(View.GONE);
            }
            String[] times = bean.getInterviewTime().split("\\s+");
            if (null != times) {
                tvInterviewTime.setText(times[times.length - 1]);
            }
            tvTime.setText(bean.getInterviewTime());
        }
    }

    public void showResultgetInterViewCancel(MdlBaseHttpResp resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData() != null) {
            LogAndToastUtil.toast("取消面试成功");
            YunXingUtil.sendText(bean.getYunXinAccount(), "取消了面试邀请", bean.getWorkHopeId(), bean.getPositionId(), bean.getWorkHopeName());
            finish();
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

    public void showResultgetInterViewStatus(MdlBaseHttpResp resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData() != null) {
            LogAndToastUtil.toast("操作成功");
            switch (isRefuse) {
                case 1:
                    btnConfirm.setText("取消面试");
                    btnRefuse.setVisibility(View.GONE);
                    map.clear();
                    map.put("id", id);
                    YunXingUtil.sendText(bean.getYunXinAccount(), "接受了面试邀请", bean.getWorkHopeId(), bean.getPositionId(), bean.getWorkHopeName());
                    presenter.getInterViewDetail(map);
                    break;
                case 2:
                    YunXingUtil.sendText(bean.getYunXinAccount(), "取消了面试邀请", bean.getWorkHopeId(), bean.getPositionId(), bean.getWorkHopeName());
                    finish();
                    break;
                default:
                    YunXingUtil.sendText(bean.getYunXinAccount(), "拒绝了面试邀请", bean.getWorkHopeId(), bean.getPositionId(), bean.getWorkHopeName());
                    finish();
                    break;
            }
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }
}
