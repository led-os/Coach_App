package com.jsjlzj.wayne.ui.store.find;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.find.SelectTrainAdapter;
import com.jsjlzj.wayne.adapter.recycler.mine.SelectPicOrVideoAdapter;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.CommentBean;
import com.jsjlzj.wayne.entity.find.FindLessonBean;
import com.jsjlzj.wayne.entity.find.FindTrainerBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.store.home.mine.AfterSaleApplyActivity;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SelectImageUtils;
import com.jsjlzj.wayne.widgets.SelectFenView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @ClassName: FindStoreEvaluateActivity
 * @Description: 俱乐部的评价
 * @Author: 曾海强
 * @CreateDate:
 */
public class FindStoreEvaluateActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, SelectPicOrVideoAdapter.OnImageClickListener, SelectTrainAdapter.OnItemClickListener {

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.sfv_set)
    SelectFenView sfvSet;//设施评分
    @BindView(R.id.sfv_environment)
    SelectFenView sfvEnvironment;//环境评分
    @BindView(R.id.sfv_server)
    SelectFenView sfvServer;//服务评分
    @BindView(R.id.tv_fen)
    TextView tvFen;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.rv_pic)
    RecyclerView rvPic;
    @BindView(R.id.tv_select_pic)
    TextView tvSelectPic;
    @BindView(R.id.img_select_pic)
    ImageView imgSelectPic;
    @BindView(R.id.rv_trainer)
    RecyclerView rvTrainer;
    @BindView(R.id.sfv_zy)
    SelectFenView sfvZy;//专业评分
    @BindView(R.id.sfv_attitude)
    SelectFenView sfvAttitude;//态度评分
    @BindView(R.id.sfv_vivid)
    SelectFenView sfvVivid;//形象评分

    @BindView(R.id.tv_fen_store)
    TextView tvFenStore;
    @BindView(R.id.img_nick)
    ImageView imgNick;
    @BindView(R.id.ll_nick)
    LinearLayout llNick;
    @BindView(R.id.tv_public)
    TextView tvPublic;
    private static int IMG_SIZE = 9;

    public static void go2this(Context activity, int storeId,String storeName){
        activity.startActivity(new Intent(activity,FindStoreEvaluateActivity.class)
                .putExtra("storeId",storeId)
                .putExtra("storeName",storeName));
    }

    private int setFen,evaFen,serviceFen;
    private int zyFen,attFen,vividFen;
    private float totalFen,traninFen;
    private SelectPicOrVideoAdapter selectPicOrVideoAdapter;
    private List<String> imgVideoList = new ArrayList<>();
    private SelectTrainAdapter trainAdapter;
    private int curSelectPos;
    private boolean isNick;
    private int storeId;//俱乐部id
    private int selectTrainId;//选择的教练id
    private Map<Object,Object> map = new HashMap<>();



    @Override
    protected int getLayoutResId() {
        return R.layout.activity_find_store_evaluate;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("评价");
        sfvSet.setFenType("设施", curFen -> {
            setFen = curFen;
            totalFen = (setFen + evaFen + serviceFen ) * 1.0f/ 3;
            tvFen.setText(DateUtil.getTwoDotByFloatOne(totalFen));
        });
        sfvEnvironment.setFenType("环境", curFen -> {
            evaFen = curFen;
            totalFen = (setFen + evaFen + serviceFen ) * 1.0f/ 3;
            tvFen.setText(DateUtil.getTwoDotByFloatOne(totalFen));
        });
        sfvServer.setFenType("服务", curFen -> {
            serviceFen = curFen;
            totalFen = (setFen + evaFen + serviceFen ) * 1.0f/ 3;
            tvFen.setText(DateUtil.getTwoDotByFloatOne(totalFen));
        });
        sfvZy.setFenType("专业", curFen -> {
            zyFen = curFen;
            traninFen = (zyFen + attFen + vividFen ) * 1.0f/ 3;
            tvFenStore.setText(DateUtil.getTwoDotByFloatOne(traninFen));
        });
        sfvAttitude.setFenType("态度", curFen -> {
            attFen = curFen;
            traninFen = (zyFen + attFen + vividFen ) * 1.0f/ 3;
            tvFenStore.setText(DateUtil.getTwoDotByFloatOne(traninFen));
        });
        sfvVivid.setFenType("形象", curFen -> {
            vividFen = curFen;
            traninFen = (zyFen + attFen + vividFen ) * 1.0f/ 3;
            tvFenStore.setText(DateUtil.getTwoDotByFloatOne(traninFen));
        });
        storeId = getIntent().getIntExtra("storeId",1);
        String storeName = getIntent().getStringExtra("storeName");
        tvName.setText(storeName);
        rvPic.setLayoutManager(new GridLayoutManager(this,3));
        imgVideoList.add("图片");
        imgVideoList.add("视频");
        selectPicOrVideoAdapter = new SelectPicOrVideoAdapter(this,imgVideoList);
        selectPicOrVideoAdapter.setListener(this);
        rvPic.setAdapter(selectPicOrVideoAdapter);
        rvTrainer.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        trainAdapter = new SelectTrainAdapter(this,new ArrayList<>());
        trainAdapter.setListener(this);
        rvTrainer.setAdapter(trainAdapter);
        tvSelectPic.setOnClickListener(clickListener);
        imgSelectPic.setOnClickListener(clickListener);
        llNick.setOnClickListener(clickListener);
        tvPublic.setOnClickListener(clickListener);
        presenter.getFindTrainerList(storeId);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.img_select_pic:
            case R.id.tv_select_pic:
                SelectTrainActivity.go2this(this,storeId,selectTrainId,SelectTrainActivity.REQUEST_CODE);
                break;
            case R.id.ll_nick://是否匿名
                if(isNick){
                    isNick = false;
                    imgNick.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.cbx_unselect));
                }else {
                    isNick = true;
                    imgNick.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.cbx_select));
                }
                break;
            case R.id.tv_public://发布
                commitComment();
                break;
            default:break;
        }
    }

    private void commitComment() {
        if(TextUtils.isEmpty(etContent.getText())){
            LogAndToastUtil.toast("请输入评论内容");
            return;
        }
        StringBuilder submitList = new StringBuilder();
        for (int i = 0; i < imgVideoList.size(); i++) {
            if (!TextUtils.isEmpty(imgVideoList.get(i)) && !imgVideoList.get(i).equals("图片") && !imgVideoList.get(i).equals("视频") ) {
                submitList.append(imgVideoList.get(i) + ",");
            }
        }
        if (submitList.length() == 0) {
            LogAndToastUtil.toast("请上传图片");
            return;
        }
        map.clear();
        map.put("content",etContent.getText().toString());
        map.put("image",submitList.toString().substring(0, submitList.toString().length() - 1));
        map.put("isAnonymous",isNick ? 1 : 0);
        map.put("storeAllScore",tvFen.getText().toString());
        map.put("storeEnvScore",evaFen);
        map.put("storeFacilityScore",setFen);
        map.put("storeId",storeId);
        map.put("storeServiceScore",serviceFen);
        if(selectTrainId != 0){
            map.put("trainerAllScore",tvFenStore.getText().toString());
            map.put("trainerId",selectTrainId);
            map.put("trainerImageScore",vividFen);
            map.put("trainerServiceScore",attFen);
            map.put("trainerSpecialityScore",zyFen);
        }
//        map.put("video","");
//        map.put("videoImg","");
        presenter.getCommitComment(map);
    }


    @Override
    public void getCommentSuccess(MdlBaseHttpResp<CommentBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            StoreEvaluateSuccessActivity.go2this(this,  resp.getData().getData());
            finish();
        }
    }

    @Override
    public void selectPhoto(int position) {
        if(position == imgVideoList.size() - 1){
            // TODO: 2020/6/24 选择视频文件 
        }else {
            SelectImageUtils.selectPhoto(this, getString(R.string.takephoto), false, true, 1);
        }
    }

    @Override
    public void onUploadSuccess(String path, int position) {
        this.curSelectPos = position;
        presenter.upload(path);
    }


    @Override
    public void showUpload(MdlBaseHttpResp<MdlUpload> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData() != null) {
            MdlUpload.DataBean bean = resp.getData().getData();
            if (curSelectPos ==  imgVideoList.size() - 2) {
                imgVideoList.add(0,bean.getUrl());
            } else if(curSelectPos < imgVideoList.size() - 2){ // 替换图片
                imgVideoList.set(curSelectPos, bean.getUrl());
            }
            if (imgVideoList.size() < IMG_SIZE) {
                // 如果最后一张已经是空白图的话不操作，否则添加一张空白图
                if(imgVideoList.size() > 2){
                    if (!imgVideoList.get(imgVideoList.size() - 2).equals("图片")) {
                        imgVideoList.add("图片");
                    }
                }
                if (!imgVideoList.get(imgVideoList.size() - 1).equals("视频")) {
                    imgVideoList.add("视频");
                }
            }
            selectPicOrVideoAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        presenter.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(this, requestCode, resultCode, data);
        if(requestCode == SelectTrainActivity.REQUEST_CODE && resultCode == Activity.RESULT_OK){
            selectTrainId = data.getIntExtra("selectTrainId",0);
            trainAdapter.setSelectTrainId(selectTrainId);
        }
    }


    @Override
    public void onImageClick(int position) {
        presenter.autoObtainStoragePermission(this, position);
    }

    @Override
    public void onRemoveImgClick(int position) {
        // 删除图片
        imgVideoList.remove(position);
        if (imgVideoList.size() < IMG_SIZE) {
            // 如果最后一张已经是空白图的话不操作，否则添加一张空白图
            if(imgVideoList.size() > 2){
                if (!imgVideoList.get(imgVideoList.size() - 2).equals("图片")) {
                    imgVideoList.add("图片");
                }
            }
            if (!imgVideoList.get(imgVideoList.size() - 1).equals("视频")) {
                imgVideoList.add("视频");
            }
        }
        selectPicOrVideoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(FindTrainerBean.DataBean bean) {
        selectTrainId = bean.getId();
    }


    @Override
    public void getFindStoreTrainerListSuccess(MdlBaseHttpResp<FindTrainerBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            trainAdapter.setData(resp.getData().getData());
        }
    }
}
