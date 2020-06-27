package com.jsjlzj.wayne.ui.store.find;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.find.FindImageAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.CommentDetailBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.SelectFenView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: 我的评价页面
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate:
 */
public class FindMyEvaluateActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.rv_pic)
    RecyclerView rvPic;
    @BindView(R.id.sfv_all_store)
    SelectFenView sfvAllStore;
    @BindView(R.id.tv_set_fen)
    TextView tvSetFen;
    @BindView(R.id.tv_eve_fen)
    TextView tvEveFen;
    @BindView(R.id.tv_service_fen)
    TextView tvServiceFen;
    @BindView(R.id.img_pic)
    ImageView imgPic;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.img_star_1)
    ImageView imgStar1;
    @BindView(R.id.img_star_2)
    ImageView imgStar2;
    @BindView(R.id.img_star_3)
    ImageView imgStar3;
    @BindView(R.id.img_star_4)
    ImageView imgStar4;
    @BindView(R.id.img_star_5)
    ImageView imgStar5;
    @BindView(R.id.tv_fen)
    TextView tvFen;
    @BindView(R.id.ll_fen_store)
    LinearLayout llFenStore;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.img_trainer_head)
    ImageView imgTrainerHead;
    @BindView(R.id.tv_trainer_name)
    TextView tvTrainerName;
    @BindView(R.id.tv_trainer)
    TextView tvTrainer;
    @BindView(R.id.sfv_trainer_fen)
    SelectFenView sfvTrainerFen;
    @BindView(R.id.tv_zy_fen)
    TextView tvZyFen;
    @BindView(R.id.tv_att_fen)
    TextView tvAttFen;
    @BindView(R.id.tv_vivid_fen)
    TextView tvVividFen;
    @BindView(R.id.tv_is_new)
    TextView tvIsNew;
    @BindView(R.id.rel_trainer)
    RelativeLayout relTrainer;
    @BindView(R.id.rel_store)
    RelativeLayout relStore;
    private int commentId;
    private CommentDetailBean.DataBean bean;
    public static void go2this(Context context, int commentId) {
        context.startActivity(new Intent(context, FindMyEvaluateActivity.class)
                .putExtra("commentId", commentId));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_find_my_evaluate;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("我的评价");
        commentId = getIntent().getIntExtra("commentId", 0);
        presenter.getCommentDetail(commentId);
        relStore.setOnClickListener(clickListener);
        relTrainer.setOnClickListener(clickListener);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.rel_store:
                WebViewContainerActivity.go2this(this,getResources().getString(R.string.clube_detail),HttpConstant.WEB_URL_CLUB_DETAIL + bean.getStoreInfo().getId(),
                        WebViewContainerFragment.TYPE_CLUBE_DETAIL);
                break;
            case R.id.rel_trainer:
                WebViewContainerActivity.go2this(this,getResources().getString(R.string.clube_detail),HttpConstant.WEB_URL_CLUB_DETAIL + bean.getStoreInfo().getId(),
                        WebViewContainerFragment.TYPE_CLUBE_DETAIL);
                break;
        }
    }

    @Override
    public void getCommentDetailSuccess(MdlBaseHttpResp<CommentDetailBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && resp.getData().getData() != null) {
            bean = resp.getData().getData();
            GlidUtils.setCircleGrid(this,bean.getHeadImg(),imgHead);
            tvName.setText(bean.getName());
            tvTime.setText(bean.getCreateTimeText());
            tvContent.setText(bean.getContent());
            LogAndToastUtil.log("=====image日志"+bean.getImage());
            sfvAllStore.setCurFen(bean.getStoreAllScore());
            tvSetFen.setText(bean.getStoreFacilityScore()+"分");
            tvEveFen.setText(bean.getStoreEnvScore()+"分");
            tvServiceFen.setText(bean.getStoreServiceScore()+"分");
            if(bean.getImage() != null && bean.getImage().length() > 0){
                rvPic.setLayoutManager(new GridLayoutManager(this,3));
                List<String> images = new ArrayList<>();
                images = Arrays.asList(bean.getImage().split(","));
                if(bean.getVideoImg() != null && bean.getVideoImg().length() > 0){
                    images.add(bean.getVideoImg());
                }
                FindImageAdapter findImageAdapter = new FindImageAdapter(this,images);
                rvPic.setAdapter(findImageAdapter);
            }
            if(bean.getStoreInfo() != null){
                relStore.setVisibility(View.VISIBLE);
                GlidUtils.setGrid(this,bean.getStoreInfo().getStoreLogo(),imgPic);
                tvStoreName.setText(bean.getStoreInfo().getStoreName());
                if(bean.getStoreInfo().getNewStoreFlag().equals("1")){
                    tvIsNew.setVisibility(View.VISIBLE);
                }else {
                    tvIsNew.setVisibility(View.GONE);
                }
                if (bean.getStoreInfo().getScore() == 1) {
                    imgStar1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                    imgStar3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                    imgStar4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                    imgStar5.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                }else if(bean.getStoreInfo().getScore() > 1 && bean.getStoreInfo().getScore() < 2){
                    imgStar1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_stare_self));
                    imgStar3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                    imgStar4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                    imgStar5.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                } else if (bean.getStoreInfo().getScore() == 2) {
                    imgStar1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                    imgStar4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                    imgStar5.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                }else if(bean.getStoreInfo().getScore() > 2 && bean.getStoreInfo().getScore() < 3){
                    imgStar1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_stare_self));
                    imgStar4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                    imgStar5.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                } else if (bean.getStoreInfo().getScore() == 3) {
                    imgStar1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                    imgStar5.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                } else if(bean.getStoreInfo().getScore() > 3 && bean.getStoreInfo().getScore() < 4){
                    imgStar1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_stare_self));
                    imgStar5.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                }else if (bean.getStoreInfo().getScore() == 4) {
                    imgStar1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar5.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                }else if(bean.getStoreInfo().getScore() > 4 && bean.getStoreInfo().getScore() < 5){
                    imgStar1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar5.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_stare_self));
                } else if (bean.getStoreInfo().getScore() == 5) {
                    imgStar1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                    imgStar5.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_find_star));
                }else {
                    imgStar1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                    imgStar2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                    imgStar3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                    imgStar4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                    imgStar5.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_evaluate_xing_no));
                }
                if(bean.getStoreInfo().getScore() == 0){
                    tvFen.setText("暂无评分");
                }else {
                    tvFen.setText(bean.getStoreInfo().getScore() + "分");
                }
                tvDes.setText(bean.getStoreInfo().getDetailAddress());
            }else {
                relStore.setVisibility(View.GONE);
            }
            if(bean.getTrainer() != null){
                relTrainer.setVisibility(View.VISIBLE);
                GlidUtils.setCircleGrid(this,bean.getTrainer().getHeadImg(),imgTrainerHead);
                tvTrainerName.setText(bean.getTrainer().getTrainerType());
                tvTrainer.setText(bean.getTrainer().getName());
                sfvTrainerFen.setCurFen(bean.getTrainerAllScore());
                tvZyFen.setText(bean.getTrainerSpecialityScore()+"分");
                tvAttFen.setText(bean.getTrainerServiceScore()+"分");
                tvVividFen.setText(bean.getTrainerImageScore()+"分");
            }else {
                relTrainer.setVisibility(View.GONE);
            }

        }
    }

}
