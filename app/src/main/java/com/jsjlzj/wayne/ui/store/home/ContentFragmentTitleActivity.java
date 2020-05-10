package com.jsjlzj.wayne.ui.store.home;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.publicac.SearchActivity;

 /**
  *
  * @ClassName:      标题及内容替换
  * @Description:    java类作用描述
  * @Author:         曾海强
  * @CreateDate:
  */
public class ContentFragmentTitleActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {


    public static void go2this(Activity context, int type) {
        Intent intent = new Intent(context, ContentFragmentTitleActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    /**
     * 0 ： 淘学  1 ： 国职  2：商城  3 ： 赛事   4 ：资讯
     */
    private int type ;
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_content_fragment_title;
    }
    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        type = getIntent().getIntExtra("type",0);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (type){
            case 0:
                initRightTitle(getResources().getString(R.string.amoy),R.drawable.ic_search_home);
                mRightBtn.setOnClickListener(clickListener);
                AmoySchoolFragment amoySchoolFragment = AmoySchoolFragment.getInstance();
                transaction.add(R.id.content_fragment,amoySchoolFragment);
                break;
            case 1:
                initTitle(getResources().getString(R.string.state_duty));
                Fragment tabItemStudyFragment = TabItemStudyFragment.getInstance();
                transaction.add(R.id.content_fragment,tabItemStudyFragment);
                break;
            case 2:
                break;
            case 3:
                initRightTitle(getResources().getString(R.string.match),R.drawable.ic_search_home);
                mRightBtn.setOnClickListener(clickListener);
                MatchFragment matchFragment = MatchFragment.getInstance();
                transaction.add(R.id.content_fragment,matchFragment);
                break;
            case 4:
                initRightTitle(getResources().getString(R.string.information),R.drawable.ic_search_home);
                mRightBtn.setOnClickListener(clickListener);
                InformationFragment informationFragment = InformationFragment.getInstance();
                transaction.add(R.id.content_fragment,informationFragment);
                break;

        }
        transaction.commitAllowingStateLoss();

    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.btn_title_right:
                SearchActivity.go2this(this);
                break;
        }
    }
}
