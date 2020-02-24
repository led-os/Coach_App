package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.search.SearchAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.publicac.dialog.ZanFragment;
import com.jsjlzj.wayne.ui.trainer.personal.set.PersonalInfoSetTrainerActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @ClassName: PersonMineActivity
 * @Description: 我的个人中心
 * @Author: 曾海强
 * @CreateDate:
 */
public class PersonMineActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


    @BindView(R.id.img_head)
    ImageView imgHead;
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
    @BindView(R.id.rv_dynamic)
    RecyclerView rvDynamic;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, PersonMineActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_person_mine;
    }

    @Override
    protected void initViewAndControl() {
        initTitle("我的昵称");
        rvDynamic.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) );
        SearchAdapter adapter = new SearchAdapter(this,new ArrayList<>());
        rvDynamic.setAdapter(adapter);

        llDynamic.setOnClickListener(clickListener);
        llFen.setOnClickListener(clickListener);
        llFollow.setOnClickListener(clickListener);
        llZan.setOnClickListener(clickListener);
        llFavorite.setOnClickListener(clickListener);
        tvModifyInfo.setOnClickListener(clickListener);
        tvApply.setOnClickListener(clickListener);

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
                MineFansActivity.go2this(this,0);
                break;
            case R.id.ll_follow://关注
                MineFansActivity.go2this(this,1);
                break;
            case R.id.ll_zan://获赞
                ZanFragment.showDialog(getSupportFragmentManager(),"我的昵称","共获得0个赞");
                break;
            case R.id.ll_favorite://收藏
                MineFavoriteActivity.go2this(this);
                break;
            case R.id.tv_modify_info://修改资料
                PersonalInfoSetTrainerActivity.go2this(this);
                break;
            case R.id.tv_apply://我的申请
                PostureAuthenActivity.go2this(this);
//                TrainerAuthenActivity.go2this(this);
                break;
        }
    }


}
