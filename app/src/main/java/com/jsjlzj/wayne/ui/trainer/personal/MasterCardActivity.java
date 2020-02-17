package com.jsjlzj.wayne.ui.trainer.personal;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.EducationAdapter;
import com.jsjlzj.wayne.adapter.ExpAdapter;
import com.jsjlzj.wayne.adapter.JobIntentionAdapter;
import com.jsjlzj.wayne.adapter.recycler.ImageSelectAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;
import com.jsjlzj.wayne.entity.trainer.MdlWorkStatus;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.trainer.personal.set.PersonalInfoSetTrainerActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.AddEducationActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.AddExpActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.AddJobIntentionActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.AdvantageActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.JobIntentionActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.PositionPhotoActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SelectImageUtils;
import com.jsjlzj.wayne.widgets.MyListView;
import com.jsjlzj.wayne.widgets.dialog.PositinStateDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @ClassName: MasterCardActivity
 * @Description: 最新达人卡界面
 * @Author: 曾海强
 * @CreateDate: 2020/02/16
 */
public class MasterCardActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView{
    public final static int FLAG_RECRUIT_CONTENT = 0x001;
    public static final int IMG_SIZE = 3;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_add_pic)
    ImageView imgAddPic;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.tv_photo)
    TextView tvPhoto;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.tv_position_state)
    TextView tvPositionState;
    @BindView(R.id.tv_advantage)
    TextView tvAdvantage;
    @BindView(R.id.btn_job_intention)
    TextView btnJobIntention;
    @BindView(R.id.tv_add_intention)
    TextView tvAddIntention;
    @BindView(R.id.rv_job_intention)
    MyListView rvJobIntention;
    @BindView(R.id.tv_add_experience)
    TextView tvAddExperience;
    @BindView(R.id.rv_exp)
    MyListView rvExp;
    @BindView(R.id.tv_add_education)
    TextView tvAddEducation;
    @BindView(R.id.rv_education)
    MyListView rvEducation;
    @BindView(R.id.rv_authentication_img)
    RecyclerView rvAuthenticationImg;
    @BindView(R.id.rv_scene_img)
    RecyclerView rvSceneImg;
    @BindView(R.id.create_elect_card)
    TextView createElectCard;

    private String workStatusCode;

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, MasterCardActivity.class);
        intent.putExtra("isResult", "");
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //简历详情
        presenter.getDetailT(null);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_master_card;
    }


    private JobIntentionAdapter jobIntentionAdapter;
    private ExpAdapter expAdapter;
    private EducationAdapter educationAdapter;
    private List<MdlDetailT.DataBean.WorkExperienceListBean> workExperienceList = new ArrayList<>();
    private List<MdlDetailT.DataBean.EducationExperienceListBean> educationExperienceList = new ArrayList<>();
    private List<MdlDetailT.DataBean.WorkHopeListBean> workHopeList = new ArrayList<>();
    private MdlDetailT.DataBean data;
    private String advantage = "";

    private List<String> authenticationUrls ,sceneUrls;
    private ImageSelectAdapter authentictionAdapter,sceneAdapter;
    private int authenticationPos,scenePos;
    /**
     * 0 : 认证证书  1 ：教育场景视频
     */
    private int selectImgType;


    @Override
    protected void initViewAndControl() {
        initRightTitle("达人卡", "预览");
        mRightTv.setTextColor(ContextCompat.getColor(this,R.color.color_333333));
        mRightTv.setTextSize(15);
        //求职期望列表
        jobIntentionAdapter = new JobIntentionAdapter(this);
        rvJobIntention.setAdapter(jobIntentionAdapter);
        rvJobIntention.setOnItemClickListener((parent, view, position, id) ->
                AddJobIntentionActivity.go2this(MasterCardActivity.this, workHopeList.get(position)));
        //工作经验列表
        expAdapter = new ExpAdapter(this);
        rvExp.setAdapter(expAdapter);
        rvExp.setOnItemClickListener((parent, view, position, id) ->
                AddExpActivity.go2this(MasterCardActivity.this, workExperienceList.get(position)));
        //教育经历列表
        educationAdapter = new EducationAdapter(this);
        rvEducation.setAdapter(educationAdapter);
        rvEducation.setOnItemClickListener((parent, view, position, id) ->
                AddEducationActivity.go2this(MasterCardActivity.this, educationExperienceList.get(position)));

        rvAuthenticationImg.setLayoutManager(new GridLayoutManager(this, IMG_SIZE));
        authenticationUrls = new ArrayList<>();
        // 默认的一张空白占位图
        authenticationUrls.add("");
        authentictionAdapter = new ImageSelectAdapter(this, authenticationUrls);
        authentictionAdapter.setListener(new ImageSelectAdapter.OnImageClickListener() {
            @Override
            public void onImageClick(int position) {
                presenter.autoObtainStoragePermission(MasterCardActivity.this, position);
            }

            @Override
            public void onRemoveImgClick(int position) {
                // 删除图片
                authenticationUrls.remove(position);
                if (authenticationUrls.size() < IMG_SIZE) {
                    // 如果最后一张已经是空白图的话不操作，否则添加一张空白图
                    if (!authenticationUrls.get(authenticationUrls.size() - 1).equals("")) {
                        authenticationUrls.add("");
                    }
                }
                authentictionAdapter.notifyDataSetChanged();
            }
        });
        rvAuthenticationImg.setAdapter(authentictionAdapter);

        rvSceneImg.setLayoutManager(new GridLayoutManager(this, IMG_SIZE));
        sceneUrls = new ArrayList<>();
        // 默认的一张空白占位图
        sceneUrls.add("");
        sceneAdapter = new ImageSelectAdapter(this, sceneUrls);
        sceneAdapter.setListener(new ImageSelectAdapter.OnImageClickListener() {
            @Override
            public void onImageClick(int position) {
//                presenter.autoObtainStoragePermission(MasterCardActivity.this, position);
            }

            @Override
            public void onRemoveImgClick(int position) {
                // 删除图片
                sceneUrls.remove(position);
                if (sceneUrls.size() < IMG_SIZE) {
                    // 如果最后一张已经是空白图的话不操作，否则添加一张空白图
                    if (!sceneUrls.get(sceneUrls.size() - 1).equals("")) {
                        sceneUrls.add("");
                    }
                }
                sceneAdapter.notifyDataSetChanged();
            }
        });
        rvSceneImg.setAdapter(sceneAdapter);

        mRightTv.setOnClickListener(clickListener);
        imgAddPic.setOnClickListener(clickListener);
        llInfo.setOnClickListener(clickListener);
        tvAdvantage.setOnClickListener(clickListener);
        tvPositionState.setOnClickListener(clickListener);
        tvAddIntention.setOnClickListener(clickListener);
        tvAddEducation.setOnClickListener(clickListener);
        tvAddExperience.setOnClickListener(clickListener);
        createElectCard.setOnClickListener(clickListener);
    }




    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {

            case R.id.img_add_pic:
                if (null != data && !TextUtils.isEmpty(data.getLifePhotos())) {
                    PositionPhotoActivity.go2this(MasterCardActivity.this, data.getLifePhotos());
                } else {
                    PositionPhotoActivity.go2this(MasterCardActivity.this, null);
                }
                break;
            case R.id.tv_right_btn://预览
                PositionPreviewNewActivity.go2this(MasterCardActivity.this, "");
                break;
            case R.id.ll_info://修改个人信息
                PersonalInfoSetTrainerActivity.go2this(MasterCardActivity.this);//ok
                break;
            case R.id.tv_advantage://个人优势
                AdvantageActivity.go2this(MasterCardActivity.this, advantage, "可以简单介绍一下个人优势，个人发展状况等。", "个人优势");
                break;
            case R.id.tv_position_state://求职状态
                showEducationDialog();
                break;
            case R.id.tv_add_intention://求职意向
                JobIntentionActivity.go2this(MasterCardActivity.this);
                break;
//            case R.id.tv_add_experience://添加求职期望
//                AddJobIntentionActivity.go2this(MasterCardActivity.this);
//                break;
            case R.id.tv_add_experience://添加工作经验
                AddExpActivity.go2this(MasterCardActivity.this);
                break;
            case R.id.tv_add_education://添加教育经历
                AddEducationActivity.go2this(MasterCardActivity.this);
                break;
            case R.id.create_elect_card://生成电子名片
                break;
                default:break;
        }
    }


    @Override
    public void selectPhoto(int position) {
        SelectImageUtils.selectPhoto(this, getString(R.string.takephoto), false, true, 1);
    }

    @Override
    public void onUploadSuccess(String imgUrl, int position) {
//        presenter.uploadImage(imgUrl);
        this.authenticationPos = position;
        if (authenticationUrls.size() < position) {
            authenticationUrls.add(imgUrl);
            // 替换图片
        } else if (authenticationUrls.size() >= position) {
            authenticationUrls.set(position, imgUrl);
        }
        if (authenticationUrls.size() < IMG_SIZE) {
            // 如果最后一张已经是空白图的话不操作，否则添加一张空白图
            if (!authenticationUrls.get(authenticationUrls.size() - 1).equals("")) {
                authenticationUrls.add("");
            }
        }
        authentictionAdapter.notifyDataSetChanged();
    }


    //   求职状态
    private void showEducationDialog() {
        PositinStateDialog positinStateDialog = new PositinStateDialog(this, new PositinStateDialog.ClickListener() {
            @Override
            public void clickConfirm(String data, String code) {
                workStatusCode = code;
                Map<Object, Object> param = new HashMap<>();
                param.put("workStatusCode", code);
                param.put("workStatus", data);

                presenter.saveWorkStatusT(param);
            }
        });
        positinStateDialog.show();
        positinStateDialog.setTitle("选择求职状态");
    }

    @Override
    public void saveWorkStatusT(MdlBaseHttpResp<MdlWorkStatus> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MdlWorkStatus.DataBean data = resp.getData().getData();
            tvPositionState.setText(data.getWorkStatus());
            workStatusCode = data.getWorkStatusCode();
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }

    @Override
    public void getDetailT(MdlBaseHttpResp<MdlDetailT> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            data = resp.getData().getData();
//            tvContent1.setText(TextUtils.isEmpty(data.getWorkYears()) ? "经验年限" : data.getWorkYears());
//            tvContent2.setText(TextUtils.isEmpty(data.getAge()) ? "年龄" : data.getAge());
//            tvContent3.setText(data.getHighestEducationLevel());
//            if (!TextUtils.isEmpty(data.getName())) {
//                tvName.setText(data.getName());
//            }
            if (!TextUtils.isEmpty(data.getHeadImg())) {
                Glide.with(this).load(data.getHeadImg()).into(imgHead);
            }
            if (TextUtils.isEmpty(data.getAdvantage())) {
                tvAdvantage.setText("添加个人优势");
            } else {
                advantage = data.getAdvantage();
                tvAdvantage.setText("去修改");
            }
            if (!TextUtils.isEmpty(data.getLifePhotos())) {
                List<String> list = Arrays.asList(data.getLifePhotos().split(","));
                tvPhoto.setText("已添加" + list.size() + "张");
            } else {
                tvPhoto.setText("未添加");
            }
            if (!TextUtils.isEmpty(data.getWorkStatus())) {
                tvPositionState.setText(data.getWorkStatus());
                workStatusCode = data.getWorkStatusCode();
            } else {
                tvPositionState.setText("添加求职状态");
            }
            workHopeList = data.getWorkHopeList();
            workExperienceList = data.getWorkExperienceList();
            educationExperienceList = data.getEducationExperienceList();
            jobIntentionAdapter.setData(workHopeList);
            expAdapter.setData(workExperienceList);
            educationAdapter.setData(educationExperienceList);
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        switch (requestCode) {
            case FLAG_RECRUIT_CONTENT://个人优势
                String content = data.getStringExtra("data");
//                if (content == null) content = "";
//                if (tvAdvantage != null) tvAdvantage.setText(content);
                if (TextUtils.isEmpty(content)) {
                    tvAdvantage.setText("添加个人优势");
                } else {
                    tvAdvantage.setText("去修改");
                }
                break;
        }
    }

}