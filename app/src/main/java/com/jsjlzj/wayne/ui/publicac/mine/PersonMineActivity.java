package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.entity.store.MdlInfo;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.publicac.dialog.ZanFragment;
import com.jsjlzj.wayne.ui.store.home.community.CommunityItemFragment;
import com.jsjlzj.wayne.ui.trainer.personal.set.PersonalInfoSetTrainerActivity;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.GlidUtils;

import butterknife.BindView;

/**
 * @ClassName: PersonMineActivity
 * @Description: 教练端我的个人中心
 * @Author: 曾海强
 * @CreateDate:
 */
public class PersonMineActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.img_level)
    ImageView imgLevel;
    @BindView(R.id.tv_dynamic)
    TextView tvDynamic;
    @BindView(R.id.ll_dynamic)
    LinearLayout llDynamic;
    @BindView(R.id.tv_fen)
    TextView tvFen;
    @BindView(R.id.ll_fen)
    LinearLayout llFen;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.ll_follow)
    LinearLayout llFollow;
    @BindView(R.id.tv_zan)
    TextView tvZan;
    @BindView(R.id.ll_zan)
    LinearLayout llZan;
    @BindView(R.id.tv_favorite)
    TextView tvFavorite;
    @BindView(R.id.ll_favorite)
    LinearLayout llFavorite;
    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.tv_modify_info)
    TextView tvModifyInfo;
    @BindView(R.id.tv_apply)
    TextView tvApply;
    @BindView(R.id.tv_simple)
    TextView tvSimple;

    private MdlInfo.DataBean mdlInfo;

    public static void go2this(Activity context, MdlInfo.DataBean mdlInfo) {
        Intent intent = new Intent(context, PersonMineActivity.class);
        intent.putExtra(ExtraConstant.EXTRA_DATA, mdlInfo);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_person_mine;
    }

    @Override
    protected void initViewAndControl() {
        mdlInfo = (MdlInfo.DataBean) getIntent().getSerializableExtra(ExtraConstant.EXTRA_DATA);
        if (mdlInfo == null) {
            finish();
            return;
        }
        initTitle(mdlInfo.getName());
        initInfo(mdlInfo);

        llDynamic.setOnClickListener(clickListener);
        llFen.setOnClickListener(clickListener);
        llFollow.setOnClickListener(clickListener);
        llZan.setOnClickListener(clickListener);
        llFavorite.setOnClickListener(clickListener);
        tvModifyInfo.setOnClickListener(clickListener);
        tvApply.setOnClickListener(clickListener);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        CommunityItemFragment fragment = CommunityItemFragment.getInstance(4, "");
        fragmentTransaction.add(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }

    private void initInfo(MdlInfo.DataBean mdlInfo) {
        GlidUtils.setCircleGrid(this, mdlInfo.getHeadImg(), imgHead);
        imgLevel.setVisibility(mdlInfo.getLevel() == 2 ? View.VISIBLE : View.GONE);
        tvDynamic.setText(DateUtil.getNumByInteger(mdlInfo.getPublishCount()));
        tvFen.setText(DateUtil.getNumByInteger(mdlInfo.getFensCount()));
        tvFollow.setText(DateUtil.getNumByInteger(mdlInfo.getFollowerCount()));
        tvZan.setText(DateUtil.getNumByInteger(mdlInfo.getLikeCount()));
        tvFavorite.setText(DateUtil.getNumByInteger(mdlInfo.getCollectCount()));
        if (TextUtils.isEmpty(mdlInfo.getContent())) {
            tvSimple.setVisibility(View.GONE);
        } else {
            tvSimple.setVisibility(View.VISIBLE);
            tvSimple.setText("简介："+mdlInfo.getContent());
        }
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.ll_dynamic://动态
                MineDynamicActivity.go2this(this);
                break;
            case R.id.ll_fen://粉丝
                MineFansActivity.go2this(this, 0);
                break;
            case R.id.ll_follow://关注
                MineFansActivity.go2this(this, 1);
                break;
            case R.id.ll_zan://获赞
                ZanFragment.showDialog(getSupportFragmentManager(), mTitleTv.getText().toString(), "共获得" + tvZan.getText().toString() + "个赞");
                break;
            case R.id.ll_favorite://收藏
                MineFavoriteActivity.go2this(this);
                break;
            case R.id.tv_modify_info://修改资料
                PersonalInfoSetTrainerActivity.go2this(this,999);
                break;
            case R.id.tv_apply://我的申请
                PostureAuthenActivity.go2this(this);
//                TrainerAuthenActivity.go2this(this);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == 999){
                String name = data.getStringExtra("name");
                String imgUrl = data.getStringExtra("headImg");
                String content = data.getStringExtra("content");
                if(!TextUtils.isEmpty(imgUrl)){
                    GlidUtils.setCircleGrid(this,imgUrl,imgHead);
                }
                mTitleTv.setText(name);
                if(TextUtils.isEmpty(content)){
                    tvSimple.setVisibility(View.GONE);
                }else {
                    tvSimple.setVisibility(View.VISIBLE);
                    tvSimple.setText("简介："+content);
                }
            }
        }
    }
}
