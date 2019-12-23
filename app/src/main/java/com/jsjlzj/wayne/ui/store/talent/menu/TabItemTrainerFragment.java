package com.jsjlzj.wayne.ui.store.talent.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.adapter.recycler.WrapContentLinearLayoutManager;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlCV;
import com.jsjlzj.wayne.entity.store.MdlPositionType;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.publicac.AddressActivity;
import com.jsjlzj.wayne.ui.store.talent.position.PositionSelectActivity;
import com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity;
import com.jsjlzj.wayne.ui.store.talent.utilac.ScreenActivity;
import com.jsjlzj.wayne.ui.store.talent.utilac.ScreenLabActivity;
import com.jsjlzj.wayne.ui.trainer.personal.PositionPreviewActivity;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jsjlzj.wayne.constant.HttpConstant.SIZE10;

/**
 * 达人 ---首页
 */
public class TabItemTrainerFragment extends MVPBaseFragment<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {

    private String province;
    private String city;
    private String salaryRequiredCode;
    private ArrayList<String> workStatusCodes;
    private ArrayList<String> workYearsCodes;
    private ArrayList<String> lowestEducationLevelCodes;
    private List<MdlPositionType.DataBean> list;
    private int pageNo = 1;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, TabItemTrainerFragment.class);
        context.startActivityForResult(intent, RecruitActivity.FLAG_RECRUIT_CONTENT);
    }
    public static Fragment getInstance() {
        TabItemTrainerFragment fragment = new TabItemTrainerFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    private TextView btnRecommend, btnNew, btnAddress, btnScreen;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_talent_tab_trainer;
    }


    private MyRecyclerView<MdlCV.DataBean.ResultBean> recyclerView;
    private RecyclerView tabLayout;
    private MyRecyclerAdapter<MdlCV.DataBean.ResultBean> recyclerViewAdapter;
    private MyRecyclerAdapter<MdlPositionType.DataBean> tabViewAdapter;
    private Map<Object, Object> map;
    private String positionType = "";
    private List<MdlCV.DataBean.ResultBean> listAll;
    private int rcmdOrNew = 1;

    @Override
    protected void initViewAndControl(View view) {
        if (null == list) {
            list = new ArrayList<>();
        } else {
            list.clear();
        }
        if (null == listAll) {
            listAll = new ArrayList<>();
        } else {
            listAll.clear();
        }
        btnRecommend = findView(R.id.btnRecommend);
        btnNew = findView(R.id.btnNew);
        btnAddress = findView(R.id.btnAddress);
        btnScreen = findView(R.id.btnScreen);
        tabLayout = findView(R.id.tabLayout);
//        tabLayout.setPullRefreshEnabled(false);
        findView(R.id.btnAdd).setOnClickListener(clickListener);
        findView(R.id.btnSearch).setOnClickListener(clickListener);
        btnRecommend.setOnClickListener(clickListener);
        btnNew.setOnClickListener(clickListener);
        btnAddress.setOnClickListener(clickListener);
        btnScreen.setOnClickListener(clickListener);
        selectTab(btnRecommend);
        initRecycle();
    }

    @Override
    public void onResume() {
        super.onResume();
        pageNo=1;
        selected=0;
        presenter.getPublishPositionTypeList(null);

    }

    public void getInfo() {
        if (null == map) map = new HashMap<>();
        map.clear();
        if (pageNo == 1) {
            listAll.clear();
            recyclerViewAdapter.notifyDataSetChanged();
        }
        map.put("city", city);
        map.put("pageNo", pageNo);
        map.put("pageSize", SIZE10);
        map.put("position", positionType);
        map.put("province", province);
        map.put("rcmdOrNew", rcmdOrNew);//推荐最新
        map.put("salaryRequiredCode", salaryRequiredCode);//薪资要求
        map.put("lowestEducationLevelCodes", lowestEducationLevelCodes);
        map.put("workStatusCodes", workStatusCodes);
        map.put("workYearsCodes", workYearsCodes);
        presenter.searchCV(map);
    }

    @Override
    protected void fragment2Front() {

    }


    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    int selected = 0;

    private void initRecycle() {
        recyclerView = findView(R.id.rvTabTanlent);
        LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager layoutManagerTab = new WrapContentLinearLayoutManager(getContext());
        layoutManagerTab.setOrientation(LinearLayoutManager.HORIZONTAL);
        tabLayout.setLayoutManager(layoutManagerTab);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL));
        recyclerViewAdapter = new MyRecyclerAdapter<MdlCV.DataBean.ResultBean>(getContext(), R.layout.item_tab_talent) {
            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, final MdlCV.DataBean.ResultBean item, int position) {
                if (item != null) setUI(helper, item);
                helper.setOnClickListener(R.id.llItem, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PositionPreviewActivity.go2this(getActivity(), item.getId());
                    }
                });
            }
        };
        tabViewAdapter = new MyRecyclerAdapter<MdlPositionType.DataBean>(getContext(), R.layout.talent_tab_item) {
            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, final MdlPositionType.DataBean item, int position) {
                helper.setText(R.id.tv_tab_item, item.getName());
                if (selected == position) {
                    helper.setTextSize(R.id.tv_tab_item, 17);
                    helper.setTextColor(R.id.tv_tab_item, getResources().getColor(R.color.text_black));
                } else {
                    helper.setTextSize(R.id.tv_tab_item, 14);
                    helper.setTextColor(R.id.tv_tab_item, getResources().getColor(R.color.text_unselected));
                }
                helper.setOnClickListener(R.id.tv_tab_item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selected = position;
                        helper.setTextSize(R.id.tv_tab_item, 17);
                        helper.setTextColor(R.id.tv_tab_item, getResources().getColor(R.color.black));
                        notifyDataSetChanged();
                        positionType = item.getName();
                        MyApp.positionId = item.getId();
                        pageNo = 1;
                        getInfo();
                    }
                });
            }
        };
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                loadRefresh();
            }

            @Override
            public void onLoadMore() {
                loadMore();
            }
        });
        tabViewAdapter.setData(list);
        recyclerViewAdapter.setData(listAll);
        tabLayout.setAdapter(tabViewAdapter);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void setUI(BaseAdapterHelper helper, MdlCV.DataBean.ResultBean item) {
        helper.setText(R.id.tvPositionName, item.getName());
        String education= TextUtils.isEmpty(item.getHighestEducationLevel())?"":item.getHighestEducationLevel();
        helper.setText(R.id.tvInfo, item.getWorkYears() + "年 " + education+ " " + item.getAge() + "岁");
        helper.setText(R.id.tvPositionName, item.getName());
        helper.setText(R.id.tvContent, item.getAdvantage());
        helper.setText(R.id.tvPosition, item.getPosition());
        ImageView iv = helper.getView(R.id.headImg);
        setImg(item.getHeadImg(), iv);
        FlexboxLayout llSkill = helper.getView(R.id.llLabel);
        llSkill.removeAllViews();
        if (null != item.getSkillTags() && item.getSkillTags().size() > 0) {
            for (int i = 0; i < item.getSkillTags().size(); i++) {
                TextView tv = new TextView(getContext());
                tv.setTextSize(13);
                FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 30, 30);
                tv.setLayoutParams(params);
                tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.dr_bg_tv_g));
                tv.setPadding(30, 14, 30, 14);
                tv.setText(item.getSkillTags().get(i));
                llSkill.addView(tv);
            }
        }
//        setImg(item.getHeadImg(),iv);
    }

    private void loadMore() {
        pageNo++;
        getInfo();
        recyclerView.loadMoreComplete();
    }

    private void loadRefresh() {
        pageNo = 1;
        getInfo();
        recyclerView.refreshComplete();
    }


    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnRecommend:
                    selectTab(btnRecommend);
                    rcmdOrNew = 1;
                    pageNo = 1;
                    unselectTab(btnNew);
                    getInfo();
                    break;
                case R.id.btnNew:
                    selectTab(btnNew);
                    rcmdOrNew = 2;
                    pageNo = 1;
                    unselectTab(btnRecommend);
                    getInfo();
                    break;
                case R.id.btnAddress:
                    AddressActivity.go2this(getActivity());
                    break;
                case R.id.btnScreen:
                    ScreenLabActivity.go2this(getActivity());
                    break;
                case R.id.btnAdd:
                    PositionSelectActivity.go2this(getActivity());
                    break;
                case R.id.btnSearch:
                    ScreenActivity.go2this(getActivity(), rcmdOrNew, true);
                    break;
            }
        }
    }

    private void unselectTab(TextView view) {
        if (view == null) return;
        view.setSelected(false);
        TextPaint paint = view.getPaint();
        paint.setFakeBoldText(false);
    }

    private void selectTab(TextView view) {
        if (view == null) return;
        view.setSelected(true);
        TextPaint paint = view.getPaint();
        paint.setFakeBoldText(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && null != data) {
            switch (requestCode) {
                case 7:
                    province = data.getStringExtra("province");
                    city = data.getStringExtra("city");
                    btnAddress.setText(city);
                    pageNo = 1;
                    getInfo();
                    break;
                case 1000:
                    salaryRequiredCode = data.getStringExtra("salaryRequiredCode");
                    workStatusCodes = (ArrayList<String>) data.getSerializableExtra("workStatusCodes");
                    workYearsCodes = (ArrayList<String>) data.getSerializableExtra("workYearsCodes");
                    lowestEducationLevelCodes = (ArrayList<String>) data.getSerializableExtra("lowestEducationLevelCodes");
                    pageNo = 1;
                    getInfo();
                    break;
            }
        }
    }

    public void showCV(MdlBaseHttpResp<MdlCV> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData() && null != resp.getData().getData().getResult()) {
            listAll.addAll(resp.getData().getData().getResult());
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }


    public void showPositionTypePublished(MdlBaseHttpResp<MdlPositionType> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            if (null != resp.getData().getData() && resp.getData().getData().size() > 0) {
                list.clear();
                list.addAll(resp.getData().getData());
                positionType = list.get(0).getName();
                MyApp.positionId = list.get(0).getId();
            }
        }
        tabViewAdapter.notifyDataSetChanged();
        getInfo();

    }
}