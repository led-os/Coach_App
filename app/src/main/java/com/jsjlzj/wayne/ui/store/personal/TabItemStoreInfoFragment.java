package com.jsjlzj.wayne.ui.store.personal;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlInfo;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.publicac.about.AboutUsActivity;
import com.jsjlzj.wayne.ui.publicac.help.HelpActivity;
import com.jsjlzj.wayne.ui.store.personal.manage.ConnectListActivity;
import com.jsjlzj.wayne.ui.store.personal.manage.InterviewListActivity;
import com.jsjlzj.wayne.ui.store.personal.set.PersonalInfoSetActivity;
import com.jsjlzj.wayne.ui.store.personal.set.SetingActivity;
import com.jsjlzj.wayne.ui.store.personal.storeinfo.StoreInfoSetActivity;
import com.jsjlzj.wayne.ui.store.talent.position.PositionSelectActivity;
import com.jsjlzj.wayne.utils.Utility;
import com.jsjlzj.wayne.widgets.dialog.CommonDialog;

/**
 * 门店 ---我的界面
 */
public class TabItemStoreInfoFragment extends MVPBaseFragment<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {

    public static Fragment getInstance() {
        TabItemStoreInfoFragment fragment = new TabItemStoreInfoFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    private TextView tvName,tvStorePosition,tvNun1,tvNun2,tvNun3,tvNun4,
            btnGeren,btnMendian,btnSet,btnAbout,btnHelp;
    private ImageView image;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_talent_tab_store;
    }

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }
    @Override
    protected void initViewAndControl(View view) {
        tvName = findView(R.id.tvName);
        tvStorePosition = findView(R.id.tvStorePosition);
        tvNun1 = findView(R.id.tvNun1);
        tvNun2 = findView(R.id.tvNun2);
        tvNun3 = findView(R.id.tvNun3);
        tvNun4 = findView(R.id.tvNun4);
        btnGeren = findView(R.id.btnGeren);
        btnMendian = findView(R.id.btnMendian);
        btnSet = findView(R.id.btnSet);
        btnAbout = findView(R.id.btnAbout);
        btnHelp = findView(R.id.btnHelp);
        image = findView(R.id.image);


        findView(R.id.btnLogout).setOnClickListener(clickListener);
        findView(R.id.LlNun4).setOnClickListener(clickListener);
        findView(R.id.LlNun3).setOnClickListener(clickListener);
        findView(R.id.LlNun2).setOnClickListener(clickListener);
        findView(R.id.LlNun1).setOnClickListener(clickListener);
        tvName.setOnClickListener(clickListener);
        tvStorePosition.setOnClickListener(clickListener);
        btnGeren.setOnClickListener(clickListener);
        btnMendian.setOnClickListener(clickListener);
        btnSet.setOnClickListener(clickListener);
        btnAbout.setOnClickListener(clickListener);
        btnHelp.setOnClickListener(clickListener);
        image.setOnClickListener(clickListener);
    }

    @Override
    protected void fragment2Front() {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getMyInfo(null);

    }

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.tvName:
                    PersonalInfoSetActivity.go2this(getActivity());//ok
                    break;
                case R.id.tvStorePosition:
                    PersonalInfoSetActivity.go2this(getActivity());//ok
                    break;
                case R.id.image:
                case R.id.btnGeren:
                    PersonalInfoSetActivity.go2this(getActivity());//ok
                    break;
                case R.id.LlNun1://沟通  过的达人
                    ConnectListActivity.go2this(getActivity());
                    break;
                case R.id.LlNun2://面试日程
                    InterviewListActivity.go2this(getActivity());
                    break;
                case R.id.LlNun3://收藏  我感兴趣的达人
                    ConnectListActivity.go2this2(getActivity());
                    break;
                case R.id.LlNun4://职位选择
                    PositionSelectActivity.go2this(getActivity());
                    break;
                case R.id.btnMendian:
                    StoreInfoSetActivity.go2this(getActivity());
                    break;
                case R.id.btnSet:
                    SetingActivity.go2this(getActivity());
                    break;
                case R.id.btnAbout:
                    AboutUsActivity.go2this(getActivity());
                    break;
                case R.id.btnHelp:
                    HelpActivity.go2this(getActivity());
                    break;
                case R.id.btnLogout:
                    clickLogout();
                    break;
            }
        }
    }

    public void clickLogout() {
        CommonDialog dialog = new CommonDialog(getActivity(), "确定退出账号吗？", new CommonDialog.ClickListener() {
            @Override
            public void clickConfirm() {
                Utility.needLogin(getActivity());
            }

            @Override
            public void clickCancel() {

            }
        });
        dialog.show();

    }

    public  void showGetMyInfo(MdlBaseHttpResp<MdlInfo> resp) {
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            MdlInfo.DataBean bean=resp.getData().getData();
            tvNun1.setText(bean.getCommunicatedCount()+"");
            tvNun2.setText(bean.getInterviewedCount()+"");
            tvNun3.setText(bean.getLikeCount()+"");
            tvNun4.setText(bean.getPositionCount()+"");
            tvStorePosition.setText(bean.getStoreUserName()+"·"+bean.getStoreUserPosition());
            tvName.setText(bean.getStoreName());
                setImg(bean.getStoreUserHeadImg(), image);
        }
    }
}