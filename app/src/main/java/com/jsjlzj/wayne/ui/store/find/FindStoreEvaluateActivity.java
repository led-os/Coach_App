package com.jsjlzj.wayne.ui.store.find;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.widgets.SelectFenView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: FindStoreEvaluateActivity
 * @Description: 俱乐部的评价
 * @Author: 曾海强
 * @CreateDate:
 */
public class FindStoreEvaluateActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {

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
    @BindView(R.id.img_1_major)
    ImageView img1Major;
    @BindView(R.id.img_2_major)
    ImageView img2Major;
    @BindView(R.id.img_3_major)
    ImageView img3Major;
    @BindView(R.id.img_4_major)
    ImageView img4Major;
    @BindView(R.id.img_5_major)
    ImageView img5Major;
    @BindView(R.id.img_1_attitude)
    ImageView img1Attitude;
    @BindView(R.id.img_2_attitude)
    ImageView img2Attitude;
    @BindView(R.id.img_3_attitude)
    ImageView img3Attitude;
    @BindView(R.id.img_4_attitude)
    ImageView img4Attitude;
    @BindView(R.id.img_5_attitude)
    ImageView img5Attitude;
    @BindView(R.id.img_1_vivid)
    ImageView img1Vivid;
    @BindView(R.id.img_2_vivid)
    ImageView img2Vivid;
    @BindView(R.id.img_3_vivid)
    ImageView img3Vivid;
    @BindView(R.id.img_4_vivid)
    ImageView img4Vivid;
    @BindView(R.id.img_5_vivid)
    ImageView img5Vivid;
    @BindView(R.id.tv_fen_store)
    TextView tvFenStore;
    @BindView(R.id.img_nick)
    ImageView imgNick;
    @BindView(R.id.ll_nick)
    LinearLayout llNick;
    @BindView(R.id.tv_public)
    TextView tvPublic;

    public static void go2this(Activity activity){
        activity.startActivity(new Intent(activity,FindStoreEvaluateActivity.class));
    }

    private int setFen,evaFen,serviceFen;
    private float totalFen;



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
        sfvSet.setFenType("设施", curFen -> {
            setFen = curFen;
            totalFen = (setFen + evaFen + serviceFen ) * 1.0f/ 3;
            tvFen.setText(""+totalFen);
        });
        sfvEnvironment.setFenType("环境", curFen -> {
            evaFen = curFen;
            totalFen = (setFen + evaFen + serviceFen ) * 1.0f/ 3;
            tvFen.setText(""+totalFen);
        });
        sfvEnvironment.setFenType("环境", curFen -> {
            evaFen = curFen;
            totalFen = (setFen + evaFen + serviceFen ) * 1.0f/ 3;
            tvFen.setText(""+totalFen);
        });
        tvSelectPic.setOnClickListener(clickListener);
        imgSelectPic.setOnClickListener(clickListener);

    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){

            case R.id.img_select_pic:
            case R.id.tv_select_pic:
                // TODO: 2020/6/23 选择教练评价
                break;

        }
    }
}
