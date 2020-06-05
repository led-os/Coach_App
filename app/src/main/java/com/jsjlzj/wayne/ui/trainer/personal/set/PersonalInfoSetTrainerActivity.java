package com.jsjlzj.wayne.ui.trainer.personal.set;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.ItemsBean;
import com.jsjlzj.wayne.entity.store.MdlDict;
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;
import com.jsjlzj.wayne.entity.trainer.MdlsaveAdvantage;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SelectImageUtils;
import com.jsjlzj.wayne.utils.eventbus.EnumEventBus;
import com.jsjlzj.wayne.utils.eventbus.MdlEventBus;
import com.jsjlzj.wayne.widgets.dialog.DateDialog;
import com.jsjlzj.wayne.widgets.dialog.EditDialog;
import com.jsjlzj.wayne.widgets.dialog.EducationDialog;
import com.jsjlzj.wayne.widgets.dialog.SexDialog;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 编辑个人信息  教练端
 */
public class PersonalInfoSetTrainerActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView, TextWatcher {


    private static final int NAME = 10002;
    private static final int ENGLISH_NAME = 10003;

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.edName)
    TextView edName;
    @BindView(R.id.tv_english)
    TextView tvEnglish;
    @BindView(R.id.tvSex)
    TextView tvSex;
    @BindView(R.id.edWechat)
    TextView edWechat;
    @BindView(R.id.tvBirthday)
    TextView tvBirthday;
    @BindView(R.id.tvEducation)
    TextView tvEducation;
    @BindView(R.id.tvJobTime)
    TextView tvJobTime;
    @BindView(R.id.et_simple)
    EditText etSimple;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.ll_english_name)
    LinearLayout llEnglishName;
    @BindView(R.id.ll_bind_wx)
    LinearLayout llBindWx;


    private String cropHeadPicPath;
    private String[] datas = new String[7];
    private List<ItemsBean> itemsBean;

    public static void go2this(Activity context,int requestCode) {
        Intent intent = new Intent(context, PersonalInfoSetTrainerActivity.class);
        context.startActivityForResult(intent,requestCode);
    }

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, PersonalInfoSetTrainerActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_trainer_personl_info_set;
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("修改资料");
        mRightTv.setTextColor(ContextCompat.getColor(this, R.color.color_222222));
        mRightTv.setTextSize(15);
        mRightTv.setText("保存");
        mRightTv.setVisibility(View.VISIBLE);
        mRightTv.setOnClickListener(clickListener);
        image.setOnClickListener(clickListener);
        llName.setOnClickListener(clickListener);
        llEnglishName.setOnClickListener(clickListener);
        tvSex.setOnClickListener(clickListener);
        tvBirthday.setOnClickListener(clickListener);
        tvEducation.setOnClickListener(clickListener);
        tvJobTime.setOnClickListener(clickListener);
        llBindWx.setOnClickListener(clickListener);
        etSimple.addTextChangedListener(this);
        setInfo();
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.tvSex://性别
                showSexDialog();
                break;
            case R.id.ll_name:
                new EditDialog(this,"", new EditDialog.ClickListener() {
                    @Override
                    public void clickConfirm(String s) {
                        edName.setText(s);
                    }

                    @Override
                    public void clickCancel() {}
                }).show();
//                ChangeUserInfoActivity.go2this(this,0,edName.getText().toString(),NAME);
                break;
            case R.id.ll_english_name:
                new EditDialog(this,"", new EditDialog.ClickListener() {
                    @Override
                    public void clickConfirm(String s) {
                        tvEnglish.setText(s);
                    }

                    @Override
                    public void clickCancel() {

                    }
                }).show();
//                ChangeUserInfoActivity.go2this(this,1,tvEnglish.getText().toString(),ENGLISH_NAME);
                break;
            case R.id.tvEducation://学历
                showEducationDialog();
                break;
            case R.id.tvBirthday://生日
                showDateDialog(tvBirthday);
                break;
            case R.id.tvJobTime://参加工作时间
                showDateDialog(tvJobTime);
                break;
            case R.id.tv_right_btn://保存
                keepInfo();
                break;
            case R.id.ll_bind_wx://绑定微信
                bindWechat();
                break;
            case R.id.image:
                presenter.autoObtainStoragePermission(PersonalInfoSetTrainerActivity.this, 0);
                break;
        }
    }


    private void setInfo() {
        presenter.getDetailT(null);
//        setEditAble(true);
        if (MyApp.mdlDict != null && MyApp.mdlDict.getEducation_level() != null) {
            MdlDict.DataBean.SalaryRequiredBean bean = MyApp.mdlDict.getEducation_level();
            if (bean != null && bean.getItems() != null) {
                itemsBean = MyApp.mdlDict.getEducation_level().getItems();
                for (int i = 0; i < bean.getItems().size(); i++) {
                    datas[i] = bean.getItems().get(i).getName();
                }
            }
        } else {
            presenter.getAll(null);
        }
    }

    private void setEditAble(boolean able) {
        if (able) {
            edName.setFocusableInTouchMode(true);
            edName.setFocusable(true);
            edName.requestFocus();
            edWechat.setFocusableInTouchMode(true);
            edWechat.setFocusable(true);
            edWechat.requestFocus();
            //up-by
            image.setClickable(true);
            tvSex.setClickable(true);
            tvBirthday.setClickable(true);
            tvEducation.setClickable(true);
            tvJobTime.setClickable(true);
        } else {
            edName.setFocusable(false);
            edName.setFocusableInTouchMode(false);
            edWechat.setFocusable(false);
            edWechat.setFocusableInTouchMode(false);
            //up-by
            image.setClickable(false);
            tvSex.setClickable(false);
            tvBirthday.setClickable(false);
            tvEducation.setClickable(false);
            tvJobTime.setClickable(false);
        }
    }



    private Map<Object, Object> map;
    private String birth;
    private String educationLevelCode;
    private String headImg;
    private String joinWorkDate;
    private int sexCode;

    private void keepInfo() {
        if (map == null) map = new HashMap<>();

        String strName = edName.getText().toString();
        String strWechat = edWechat.getText().toString();
        String content = etSimple.getText().toString();
        String englishName = tvEnglish.getText().toString();
        birth = tvBirthday.getText().toString();
        joinWorkDate = tvJobTime.getText().toString();
//        if (TextUtils.isEmpty(headImg)) {
//            LogAndToastUtil.toast("请上传头像");
//            return;
//        }
//        if (TextUtils.isEmpty(strName)) {
//            LogAndToastUtil.toast("请输入名字");
//            return;
//        }
//        if (TextUtils.isEmpty(birth)) {
//            LogAndToastUtil.toast("请选择生日");
//            return;
//        }
//
//        if (TextUtils.isEmpty(educationLevelCode)) {
//            LogAndToastUtil.toast("请选择学历");
//            return;
//        }
//        if (TextUtils.isEmpty(joinWorkDate)) {
//            LogAndToastUtil.toast("请选择参加工作时间");
//            return;
//        }
//        if (sexCode==0) {
//            LogAndToastUtil.toast("请选择性别");
//            return;
//        }
        map.put("name", strName);
        map.put("wxId", strWechat);
        map.put("birth", birth);
        map.put("educationLevelCode", educationLevelCode);
        if(!TextUtils.isEmpty(headImg)){
            map.put("headImg", headImg);
        }
        map.put("joinWorkDate", joinWorkDate);
        map.put("englishName",englishName);
        map.put("sexCode", sexCode);
        map.put("content", content);

        presenter.saveCvBaseInfoT(map);
    }

    @Override
    public void saveCvBaseInfoT(MdlBaseHttpResp<MdlsaveAdvantage> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            LogAndToastUtil.toast("保存成功");
            Intent intent = new Intent();
            intent.putExtra("name",edName.getText().toString());
            intent.putExtra("headImg",headImg);
            intent.putExtra("content",etSimple.getText().toString());
            setResult(RESULT_OK,intent);
            finish();
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

    private void bindWechat() {
        if (!TextUtils.isEmpty(edWechat.getText().toString())) {
            return;
        }
        MyApp.idBindWeChat = 1;
        try {
            if (!MyApp.getApp().getIwxapi().isWXAppInstalled()) {
                LogAndToastUtil.toast("请安装微信");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "spinbook_hzdc_login";
        MyApp.getApp().getIwxapi().sendReq(req);
    }


    //学历
    private void showEducationDialog() {
        new EducationDialog(this, new EducationDialog.ClickListener() {
            @Override
            public void clickConfirm(String data, int position) {
                tvEducation.setText(data);
                educationLevelCode = itemsBean.get(position).getCode();
            }
        }, datas).show();
    }

    //性别
    private void showSexDialog() {
        new SexDialog(this, new SexDialog.ClickListener() {
            @Override
            public void clickConfirm(int isMan) {
                sexCode = isMan;
                tvSex.setText(isMan == 1 ? "男" : "女");
            }
        }).show();
    }

    //生日
    private void showDateDialog(TextView textView) {
        new DateDialog(this, new DateDialog.ClickListener() {
            @Override
            public void clickConfirm(int year, int month, int day) {
                textView.setText(year + "-" + month + "-" + day);
            }
        }).show();
    }


    @Override
    public void showUpload(MdlBaseHttpResp<MdlUpload> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            headImg = resp.getData().getData().getUrl();
            if (!TextUtils.isEmpty(headImg)) {
                GlidUtils.setCircleGrid(this, cropHeadPicPath,image);
//                String[] imgs = headImg.split("/");
//                if (imgs.length != 0) {
//                    headImg = imgs[imgs.length - 1];
//                }
            }
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }


    @Override
    public void showResultgetAll(MdlBaseHttpResp<MdlDict> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MyApp.mdlDict = resp.getData().getData();
            MdlDict.DataBean.SalaryRequiredBean bean = resp.getData().getData().getStaff_num();
            if (null != bean) {
                itemsBean = bean.getItems();
                if (itemsBean != null && itemsBean.size() > 0) {
                    for (int i = 0; i < itemsBean.size(); i++) {
                        datas[i] = itemsBean.get(i).getName();
                    }
                }
            }
        }
    }

    @Override
    public void getDetailT(MdlBaseHttpResp<MdlDetailT> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MdlDetailT.DataBean user = resp.getData().getData();
            edName.setText(TextUtils.isEmpty(user.getName()) ? "" : user.getName());
            edWechat.setText(TextUtils.isEmpty(user.getWxId()) ? "" : user.getWxId());
            headImg = user.getHeadImg();
            GlidUtils.setCircleGrid(this,user.getHeadImg(),image);
            tvSex.setText(user.getSex());
            sexCode = user.getSexCode();
            tvBirthday.setText(user.getBirth());
            tvJobTime.setText(user.getJoinWorkDate());
            tvEducation.setText(user.getHighestEducationLevel());
            educationLevelCode = user.getHighestEducationLevelCode();
            etSimple.setText(user.getContent());
            edWechat.setText(user.getWxName());
            tvEnglish.setText(user.getEnglishName());
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        int leng = s.length();
        if(leng <140){
            tvNum.setText(leng+"/140");
        }else {
            tvNum.setText("140/140");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusMessage(MdlEventBus event) {
        String message = (String) event.data;
        switch (event.eventType) {
            case EnumEventBus.MESSAGE_BIND_WX:
                edWechat.setText(message);
                break;
        }
    }


    @Override
    public void selectPhoto(int position) {
        SelectImageUtils.selectPhoto(this, getString(R.string.takephoto), false, true, 1);
    }

    @Override
    public void onUploadSuccess(String imgUrl, int position) {
        presenter.upload(imgUrl);
        cropHeadPicPath = imgUrl;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(this,requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case NAME:
                    String name = data.getStringExtra(ExtraConstant.EXTRA_NAME);
                    edName.setText(name);
                    break;
                case ENGLISH_NAME:
                    String engName = data.getStringExtra(ExtraConstant.EXTRA_NAME);
                    tvEnglish.setText(engName);
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(this, requestCode, grantResults);
    }

}