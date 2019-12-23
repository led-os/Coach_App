package com.jsjlzj.wayne.ui.store.talent.position;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.ItemsBean;
import com.jsjlzj.wayne.entity.store.MdlPositionDetail;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.dialog.ShareDialog;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jsjlzj.wayne.constant.HttpConstant.BASE_URL;
import static com.jsjlzj.wayne.constant.HttpConstant.WXAPPID;
import static com.jsjlzj.wayne.utils.Utility.bmpToByteArray;
import static com.jsjlzj.wayne.utils.Utility.buildTransaction;

/**
 * 职位详情
 */
public class PositionInfoActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {

    public static void go2this(Activity context, String id) {
        Intent intent = new Intent(context, PositionInfoActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_position_info;
    }


    private Button btnConfirm;
    private TextView tvPosition, tvSalary, tvCoordinate, tvEducation, tvExp, tvContent, tvClubName, tvClubContent, tvClubCoordinate, tvReleaseTitle, tvRelease, tvStatus;
    private ImageView imClub, imRelease;
    private LinearLayout llSkill;
    private Map<Object, Object> map;
    private String id;
    private List<ItemsBean> list;
    private IWXAPI iwxapi;
    private String status;

    @Override
    protected void initViewAndControl() {
        iwxapi = WXAPIFactory.createWXAPI(this, WXAPPID, true);
        list = new ArrayList<>();
        btnConfirm = findView(R.id.btnConfirm);
        tvPosition = findView(R.id.tvPosition);
        tvSalary = findView(R.id.tvSalary);
        tvCoordinate = findView(R.id.tvCoordinate);
        tvEducation = findView(R.id.tvEducation);
        tvExp = findView(R.id.tvExp);
        tvContent = findView(R.id.tvContent);
        llSkill = findView(R.id.llSkill);
        tvStatus = findView(R.id.tvStatus);
        imClub = findView(R.id.imClub);
        tvClubName = findView(R.id.tvClubName);
        tvClubContent = findView(R.id.tvClubContent);
        tvClubCoordinate = findView(R.id.tvClubCoordinate);
        imRelease = findView(R.id.imRelease);
        tvReleaseTitle = findView(R.id.tvReleaseTitle);
        tvRelease = findView(R.id.tvRelease);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnShare).setOnClickListener(clickListener);
        findView(R.id.btnEdit).setOnClickListener(clickListener);
        btnConfirm.setOnClickListener(clickListener);
        id = getIntent().getStringExtra("id");
        if (null == map) map = new HashMap<>();

        if (MyApp.mdlDict != null && MyApp.mdlDict.getPosition_tipoff() != null) {
            list.addAll(MyApp.mdlDict.getPosition_tipoff().getItems());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(id)) {
            map.put("id", id);
            presenter.getPositionDetail(map);
        }
    }

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    Map<Object, Object> params = new HashMap<>();
    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    finish();
                    break;
                case R.id.btnConfirm:
                    params.put("id", id);
                    params.put("status", status);
                    presenter.changePositionStatus(params);
                    break;
                case R.id.btnShare:
                    showShareDialog();
                    break;
                case R.id.btnEdit:
                    RecruitActivity.go2this(PositionInfoActivity.this, id);
                    break;
            }
        }
    }

    private void showShareDialog() {
        new ShareDialog(this, new ShareDialog.ClickListener() {
            @Override
            public void clickConfirm(int index) {
                //0:微信好友  1:朋友圈
                share(index);
            }
        }).show();
    }

    private void share(int type) {
        if (!iwxapi.isWXAppInstalled()) {
            LogAndToastUtil.toast("您的设备未安装微信客户端");
        } else {
            WXWebpageObject webpageObject = new WXWebpageObject();
            webpageObject.webpageUrl = BASE_URL + "static/h5/index.html?id=" + id;
            WXMediaMessage msg = new WXMediaMessage(webpageObject);
            msg.title = "健身教练之家";
            msg.description = "健身私教";
            Bitmap thumBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            msg.thumbData = bmpToByteArray(thumBitmap, true);
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("Req");
            req.message = msg;
            switch (type) {
                case 0:
                    req.scene = SendMessageToWX.Req.WXSceneSession;
                    break;
                case 1:
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;
                    break;
            }
            iwxapi.sendReq(req);
            finish();
        }
    }

    @Override
    public void showPositionDetail(MdlBaseHttpResp<MdlPositionDetail> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MdlPositionDetail.DataBean bean = resp.getData().getData();
            if (null != bean.getPosition()) {
                if ("2".equals(bean.getPosition().getStatus())) {
                    btnConfirm.setText("关闭职位");
                    status = "4";
                } else {
                    status = "2";
                    btnConfirm.setText("发布职位");
                }
                switch (bean.getPosition().getStatus()) {
                    case "1":
                        tvStatus.setText("待开放");
                        break;
                    case "2":
                        tvStatus.setText("招聘中");
                        tvStatus.setBackground(getResources().getDrawable(R.drawable.status_position_drawable));
                        break;
                    case "3":
                        tvStatus.setText("审核失败");
                        break;
                    case "4":
                        tvStatus.setText("已关闭");
                        break;
                }
                tvPosition.setText(bean.getPosition().getName());
                tvSalary.setText(bean.getPosition().getSalaryMin() + "-" + bean.getPosition().getSalaryMax() + "K");
                tvCoordinate.setText(bean.getPosition().getProvince() + bean.getPosition().getCity());
                tvEducation.setText(TextUtils.isEmpty(bean.getPosition().getLowestEducationLevel())?"不限":bean.getPosition().getLowestEducationLevel());
                tvExp.setText(TextUtils.isEmpty(bean.getPosition().getWorkYears())?"不限":bean.getPosition().getWorkYears());
                tvContent.setText(bean.getPosition().getContent());
                if (null != bean.getPosition().getSkillRequired() && bean.getPosition().getSkillRequired().size() > 0) {
                    for (int i = 0; i < bean.getPosition().getSkillRequired().size(); i++) {
                        TextView tv = new TextView(PositionInfoActivity.this);
                        tv.setTextSize(13);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(36, 36, 0, 0);
                        tv.setLayoutParams(params);
                        tv.setBackground(getResources().getDrawable(R.drawable.dr_bg_tv_g));
                        tv.setPadding(30, 12, 30, 12);
                        tv.setText(bean.getPosition().getSkillRequired().get(i));
                        llSkill.addView(tv);
                    }
                }
                if (null != bean.getStoreInfo()) {
                    tvClubName.setText(bean.getStoreInfo().getStoreName());
                    tvClubContent.setText(bean.getStoreInfo().getStaffNum());
                    tvClubCoordinate.setText(bean.getStoreInfo().getStoreAddress());
                    setImg(bean.getStoreInfo().getBrandLogo(), imClub);
                }
                if (null != bean.getStoreAdmin()) {
                    tvReleaseTitle.setText(bean.getStoreAdmin().getStoreUserName());
                    tvRelease.setText(bean.getStoreAdmin().getStoreUserPosition());
                    setImg(bean.getStoreAdmin().getStoreUserHeadImg(), imRelease);
                }
            }
        }
    }

    @Override
    public void showChangePositionStatus(MdlBaseHttpResp resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            LogAndToastUtil.toast("操作成功");
            finish();
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }
}