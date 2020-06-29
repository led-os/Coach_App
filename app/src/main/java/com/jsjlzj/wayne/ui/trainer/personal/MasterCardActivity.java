package com.jsjlzj.wayne.ui.trainer.personal;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.cmcy.medialib.utils.MediaSelector;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.EducationAdapter;
import com.jsjlzj.wayne.adapter.ExpAdapter;
import com.jsjlzj.wayne.adapter.JobIntentionAdapter;
import com.jsjlzj.wayne.adapter.recycler.ImageSelectAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.VideoBodyBean;
import com.jsjlzj.wayne.entity.store.learn.VideoTeacherBean;
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;
import com.jsjlzj.wayne.entity.trainer.MdlWorkStatus;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.publicac.mine.InvitationActivity;
import com.jsjlzj.wayne.ui.store.find.FindStoreEvaluateActivity;
import com.jsjlzj.wayne.ui.trainer.personal.set.PersonalInfoSetTrainerActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.AddEducationActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.AddExpActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.AddJobIntentionActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.AdvantageActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.JobIntentionActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.PositionPhotoActivity;
import com.jsjlzj.wayne.utils.BitmapUtils;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SelectImageUtils;
import com.jsjlzj.wayne.widgets.MyListView;
import com.jsjlzj.wayne.widgets.dialog.PositinStateDialog;

import java.io.File;
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
public class MasterCardActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {
    public final static int FLAG_RECRUIT_CONTENT = 0x001;
    public static final int REQUEST_CODE_SELECT_VIDEO = 1000;
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
    @BindView(R.id.tv_add_intention_two)
    TextView tvAddIntentionTwo;
    @BindView(R.id.rv_job_intention)
    MyListView rvJobIntention;
    @BindView(R.id.tv_add_experience)
    TextView tvAddExperience;
    @BindView(R.id.tv_add_experience_two)
    TextView tvAddExperienceTwo;
    @BindView(R.id.rv_exp)
    MyListView rvExp;
    @BindView(R.id.tv_add_education)
    TextView tvAddEducation;
    @BindView(R.id.tv_add_education_two)
    TextView tvAddEducationTwo;
    @BindView(R.id.rv_education)
    MyListView rvEducation;
    @BindView(R.id.rv_authentication_img)
    RecyclerView rvAuthenticationImg;
    @BindView(R.id.rv_scene_img)
    RecyclerView rvSceneImg;
    @BindView(R.id.create_elect_card)
    TextView createElectCard;

    private String workStatusCode;
    private Map<Object, Object> map = new HashMap<>();
    private List<VideoTeacherBean> listVideo = new ArrayList<>();

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

    private List<String> authenticationUrls, sceneUrls;
    private ImageSelectAdapter authentictionAdapter, sceneAdapter;
    /**
     * 0:选择证书  1 ：选择视频
     */
    private int type;
    private int authenticationPos, scenePos;
    private String authenticationUrl, sceneUrl;
    /**
     * 0 : 认证证书  1 ：教育场景视频
     */
    private int selectImgType;
    private boolean isVideoImage = false;

    @Override
    protected void initViewAndControl() {
        initRightTitle("达人卡", "预览");
        mRightTv.setTextColor(ContextCompat.getColor(this, R.color.color_333333));
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
                type = 0;
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
                submitAuthentiction();
            }
        });
        rvAuthenticationImg.setAdapter(authentictionAdapter);

        rvSceneImg.setLayoutManager(new GridLayoutManager(this, IMG_SIZE));
        sceneUrls = new ArrayList<>();
        // 默认的一张空白占位图
        sceneUrls.add("");
        sceneAdapter = new ImageSelectAdapter(this, sceneUrls);
        sceneAdapter.setType(1);
        sceneAdapter.setListener(new ImageSelectAdapter.OnImageClickListener() {
            @Override
            public void onImageClick(int position) {
                type = 1;
                presenter.autoObtainStoragePermission(MasterCardActivity.this, position);
            }

            @Override
            public void onRemoveImgClick(int position) {
                // 删除图片
                sceneUrls.remove(position);
                listVideo.remove(position);
                if (sceneUrls.size() < IMG_SIZE) {
                    // 如果最后一张已经是空白图的话不操作，否则添加一张空白图
                    if (!sceneUrls.get(sceneUrls.size() - 1).equals("")) {
                        sceneUrls.add("");
                    }
                }
                sceneAdapter.notifyDataSetChanged();
                submitScene();
            }
        });
        rvSceneImg.setAdapter(sceneAdapter);

        mRightTv.setOnClickListener(clickListener);
        imgAddPic.setOnClickListener(clickListener);
        llInfo.setOnClickListener(clickListener);
        tvAdvantage.setOnClickListener(clickListener);
        tvPositionState.setOnClickListener(clickListener);
        tvAddIntention.setOnClickListener(clickListener);
        tvAddIntentionTwo.setOnClickListener(clickListener);
        tvAddEducation.setOnClickListener(clickListener);
        tvAddEducationTwo.setOnClickListener(clickListener);
        tvAddExperience.setOnClickListener(clickListener);
        tvAddExperienceTwo.setOnClickListener(clickListener);
        createElectCard.setOnClickListener(clickListener);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.img_add_pic:
                if (null != data && !TextUtils.isEmpty(data.getLifePhotos())) {
                    PositionPhotoActivity.go2this(MasterCardActivity.this, data.getLifePhotos(), 1);
                } else {
                    PositionPhotoActivity.go2this(MasterCardActivity.this, null, 1);
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
            case R.id.tv_add_intention_two:
            case R.id.tv_add_intention://求职意向
                JobIntentionActivity.go2this(MasterCardActivity.this);
                break;
//            case R.id.tv_add_experience://添加求职期望
//                AddJobIntentionActivity.go2this(MasterCardActivity.this);
//                break;
            case R.id.tv_add_experience_two:
            case R.id.tv_add_experience://添加工作经验
                AddExpActivity.go2this(MasterCardActivity.this);
                break;
            case R.id.tv_add_education_two:
            case R.id.tv_add_education://添加教育经历
                AddEducationActivity.go2this(MasterCardActivity.this);
                break;
            case R.id.create_elect_card://生成电子名片
//                InvitationActivity.go2this(this);
                break;
            default:
                break;
        }
    }


    @Override
    public void selectPhoto(int position) {
        if(type == 0){
            SelectImageUtils.selectPhoto(this, getString(R.string.takephoto), false, true, 1);
        }else {
            MediaSelector.get()
                    .showCamera(true)//默认显示，可以不用设置
                    .setSelectMode(MediaSelector.MODE_SINGLE)//默认多选
                    .setMaxCount(20)//默认最多选择5张，设置单选后此设置无效
                    .setMediaType(MediaSelector.VIDEO)//默认选择图片
                    .setListener(new MediaSelector.MediaSelectorListener(){
                        @Override
                        public void onMediaResult(List<String> resultList) {
                            LogAndToastUtil.log("====="+ JSONObject.toJSONString(resultList));
                            if(resultList != null && resultList.size() > 0){
                                MediaMetadataRetriever media = new MediaMetadataRetriever();
                                media.setDataSource(resultList.get(0));
                                String strDuration = media.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                                videoDuration = Integer.parseInt(strDuration) / 1000;
                                BitmapUtils.saveImageToGallery(MasterCardActivity.this,media.getFrameAtTime());
                                presenter.uploadVideo(resultList.get(0));
                            }
                        }
                    })//选择完成的回调, （可以设置回调或者用onActivityResult方式接收）
                    .jump(this);
        }
    }

    @Override
    public void onUploadSuccess(String path, int position) {
        if (type == 0) {
            presenter.upload(path);
            this.authenticationPos = position;
            this.authenticationUrl = path;
        } else {
            presenter.uploadVideo(path);
            this.scenePos = position;
            this.sceneUrl = path;
        }

    }


    private String videoImage,videoUrl;
    private int videoDuration;
    @Override
    public void showUpload(MdlBaseHttpResp<MdlUpload> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData() != null) {
            MdlUpload.DataBean bean = resp.getData().getData();
            if (type == 0) {
                if (authenticationUrls.size() < authenticationPos) {
                    authenticationUrls.add(bean.getUrl());
                    // 替换图片
                } else {
                    authenticationUrls.set(authenticationPos, bean.getUrl());
                }
                if (authenticationUrls.size() < IMG_SIZE) {
                    // 如果最后一张已经是空白图的话不操作，否则添加一张空白图
                    if (!authenticationUrls.get(authenticationUrls.size() - 1).equals("")) {
                        authenticationUrls.add("");
                    }
                }
                authentictionAdapter.notifyDataSetChanged();
                submitAuthentiction();
            } else {
                if(isVideoImage){
                    isVideoImage = false;
                    videoImage = bean.getUrl();
                    listVideo.add(new VideoTeacherBean(String.valueOf(videoDuration),videoUrl,videoImage));
                    if(scenePos == IMG_SIZE -1 && sceneUrls.get(sceneUrls.size() - 1).isEmpty()){
                        sceneUrls.set(scenePos,bean.getUrl());
                    }else if (scenePos == sceneUrls.size() - 1) {
                        sceneUrls.add(bean.getUrl());// 替换图片
                    } else {
                        sceneUrls.set(scenePos, bean.getUrl());
                    }
                    if (sceneUrls.size() < IMG_SIZE) {
                        // 如果最后一张已经是空白图的话不操作，否则添加一张空白图
                        if (!sceneUrls.get(sceneUrls.size() - 1).equals("")) {
                            sceneUrls.add("");
                        }
                    }
                    sceneAdapter.notifyDataSetChanged();
                    submitScene();
                }else {
                    videoUrl = bean.getUrl();
                    String page = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "coach"+File.separator + "videoImage.jpg";
                    isVideoImage = true;
                    presenter.upload(page);
                }

            }

        }
    }


    private void submitAuthentiction() {
        map.clear();
        StringBuilder submitList = new StringBuilder();
        for (int i = 0; i < authenticationUrls.size(); i++) {
            if (!TextUtils.isEmpty(authenticationUrls.get(i))) {
                submitList.append(authenticationUrls.get(i) + ",");
            }
        }
//        if (submitList.length() == 0) {
//            LogAndToastUtil.toast("请上传图片");
//            return;
//        }
        if(submitList.length() > 1){
            map.put("certificatePhotos", submitList.toString().substring(0, submitList.toString().length() - 1));
        }else {
            map.put("certificatePhotos","");
        }
        presenter.saveCertificatePhotosT(map);
    }

    private void submitScene() {
        map.clear();
        if(listVideo.size() > 0){
            map.put("teachVideos", listVideo);
            presenter.saveTeachVideo(map);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        presenter.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
                if (list != null && list.size() > 0) {
                    GlidUtils.setGrid(MasterCardActivity.this, list.get(0), imgBack);
                }
            }
            tvPhoto.setText("去修改");
            if (!TextUtils.isEmpty(data.getWorkStatus())) {
                tvPositionState.setText(data.getWorkStatus());
                workStatusCode = data.getWorkStatusCode();
            } else {
                tvPositionState.setText("添加求职状态");
            }
            workHopeList = data.getWorkHopeList();
            workExperienceList = data.getWorkExperienceList();
            educationExperienceList = data.getEducationExperienceList();
            if (workHopeList != null && workHopeList.size() > 0) {
                tvAddIntentionTwo.setVisibility(View.GONE);
                tvAddIntention.setVisibility(View.VISIBLE);
                jobIntentionAdapter.setData(workHopeList);
            } else {
                tvAddIntentionTwo.setVisibility(View.VISIBLE);
                tvAddIntention.setVisibility(View.GONE);
            }
            if (workHopeList != null && workHopeList.size() > 0) {
                tvAddIntentionTwo.setVisibility(View.GONE);
                tvAddIntention.setVisibility(View.VISIBLE);
                rvJobIntention.setVisibility(View.VISIBLE);
                jobIntentionAdapter.setData(workHopeList);
            } else {
                tvAddIntentionTwo.setVisibility(View.VISIBLE);
                rvJobIntention.setVisibility(View.GONE);
                tvAddIntention.setVisibility(View.GONE);
            }
            if (workExperienceList != null && workExperienceList.size() > 0) {
                tvAddExperience.setVisibility(View.VISIBLE);
                tvAddExperienceTwo.setVisibility(View.GONE);
                rvEducation.setVisibility(View.VISIBLE);
                expAdapter.setData(workExperienceList);
            } else {
                tvAddExperience.setVisibility(View.GONE);
                tvAddExperienceTwo.setVisibility(View.VISIBLE);
                rvEducation.setVisibility(View.GONE);
            }
            if (educationExperienceList != null && educationExperienceList.size() > 0) {
                tvAddEducation.setVisibility(View.VISIBLE);
                tvAddEducationTwo.setVisibility(View.GONE);
                rvEducation.setVisibility(View.VISIBLE);
                educationAdapter.setData(educationExperienceList);
            } else {
                tvAddEducation.setVisibility(View.GONE);
                tvAddEducationTwo.setVisibility(View.VISIBLE);
                rvEducation.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(data.getCertificatePhotos())) {
                List<String> list = Arrays.asList(data.getCertificatePhotos().split(","));
                if (list != null && list.size() > 0) {
                    authenticationUrls.clear();
                    authenticationUrls.addAll(list);
                    if (authenticationUrls.size() < IMG_SIZE) {
                        authenticationUrls.add("");
                    }
                    authentictionAdapter.notifyDataSetChanged();
                }
            }
            if(data.getTeachVideos() != null){
                sceneUrls.clear();
                int size = Math.min(data.getTeachVideos().size(),3);
                for (int i = 0; i < size; i++) {
                    VideoTeacherBean bean = new VideoTeacherBean(String.valueOf(data.getTeachVideos().get(i).getVideoDuration()),data.getTeachVideos().get(i).getVideoUrl(),data.getTeachVideos().get(i).getCoverImg());
                    sceneUrls.add(bean.getCoverImg());
                    listVideo.add(bean);
                }
                if (sceneUrls.size() < IMG_SIZE) {
                    sceneUrls.add("");
                }
                sceneAdapter.notifyDataSetChanged();
            }


        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(this, requestCode, resultCode, data);
        if (data == null) return;
        switch (requestCode) {
            case FLAG_RECRUIT_CONTENT://个人优势
                String content = data.getStringExtra("data");
                if (TextUtils.isEmpty(content)) {
                    tvAdvantage.setText("添加个人优势");
                } else {
                    tvAdvantage.setText("去修改");
                }
                break;
        }
    }

}