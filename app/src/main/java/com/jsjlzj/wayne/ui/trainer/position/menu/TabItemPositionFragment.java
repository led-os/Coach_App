package com.jsjlzj.wayne.ui.trainer.position.menu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.adapter.recycler.WrapContentLinearLayoutManager;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlPosition;
import com.jsjlzj.wayne.entity.store.MdlPositionList;
import com.jsjlzj.wayne.entity.store.MdlPositionType;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.publicac.AddressActivity;
import com.jsjlzj.wayne.ui.store.talent.utilac.ScreenActivity;
import com.jsjlzj.wayne.ui.store.talent.utilac.ScreenLabActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.JobIntentionActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.PositionInfoStoreActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jsjlzj.wayne.constant.HttpConstant.SIZE10;

/**
 * 职位item page -----教练端
 */
public class TabItemPositionFragment extends MVPBaseFragment<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    public static Fragment getInstance() {
        TabItemPositionFragment fragment = new TabItemPositionFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    private TextView btnRecommend, btnNew, btnAddress, btnScreen;
    private RecyclerView tabLayout;
    private int rcmdOrNew = 1;
    private List<MdlPositionType.DataBean> list;
    private List<MdlPosition.DataBean> listAll;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_talent_tab_trainer;
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    private MyRecyclerView<MdlPosition.DataBean> recyclerView;
    private MyRecyclerAdapter<MdlPosition.DataBean> recyclerViewAdapter;
    private Map<Object, Object> map;
    private int pageNo = 1;
    private String province;
    private String city;
    private String salaryRequiredCode;
    private ArrayList<String> workStatusCodes;
    private ArrayList<String> workYearsCodes;
    private ArrayList<String> lowestEducationLevelCodes;
    private String positionType = "";
    private MyRecyclerAdapter<MdlPositionType.DataBean> tabViewAdapter;

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
        initRecycle();
    }

    @Override
    protected void fragment2Front() {

    }

    @Override
    public void onResume() {
        super.onResume();
        pageNo=1;
        selected=0;
        presenter.getPositionTypeList(null);
        presenter.getIsFinishInfo();
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
        map.put("name", positionType);
        map.put("province", province);
        map.put("rcmdOrNew", rcmdOrNew);//推荐最新
        map.put("salaryRequiredCode", salaryRequiredCode);//薪资要求
        map.put("lowestEducationLevelCodes", lowestEducationLevelCodes);
        map.put("workStatusCodes", workStatusCodes);
        map.put("workYearsCodes", workYearsCodes);
        presenter.searchPosition(map);
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
                        MyApp.workHopeId=item.getId();
                        MyApp.workHopeName=item.getName();
                        pageNo = 1;
                        getInfo();
                    }
                });
            }
        };
        recyclerViewAdapter = new MyRecyclerAdapter<MdlPosition.DataBean>(getContext(), R.layout.item_tab_position) {
            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, final MdlPosition.DataBean item, int position) {
                if (item != null) setUI(helper, item);
                helper.setOnClickListener(R.id.llItem, v ->
                        PositionInfoStoreActivity.go2this(getActivity(), item.getId()));
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

    private void setUI(BaseAdapterHelper helper, MdlPosition.DataBean item) {
        helper.setText(R.id.tvPositionName, item.getName());
        helper.setText(R.id.tvInfo, item.getStoreName());
        helper.setText(R.id.tvSalary, item.getSalaryMin() + "-" + item.getSalaryMax() + "K");
        String area= TextUtils.isEmpty(item.getArea())?"":item.getArea();
        String workYears= TextUtils.isEmpty(item.getWorkYears())?"":item.getWorkYears();
        String education= TextUtils.isEmpty(item.getLowestEducationLevel())?"":item.getLowestEducationLevel();

        helper.setText(R.id.psition_info, item.getProvince() + " " + item.getCity() + " " + area + "  " + workYears + "  " + education);
        helper.setText(R.id.tvContent, item.getStoreUserName() + "  " + item.getStoreUserPosition());
        ImageView iv = helper.getView(R.id.image);
        setImg(item.getStoreUserHeadImg(), iv);
    }

    private void loadMore() {
        pageNo++;
        getInfo();
        recyclerView.loadMoreComplete();
    }

    private void loadRefresh() {
        pageNo = 1;
        if(list==null||list.size()==0){
            presenter.getPositionTypeList(null);
        }
        getInfo();
        recyclerView.refreshComplete();
    }


    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnRecommend:
                    rcmdOrNew = 1;
                    pageNo = 1;
                    getInfo();
                    break;
                case R.id.btnNew:
                    rcmdOrNew = 2;
                    pageNo = 1;
                    getInfo();
                    break;
                case R.id.btnAddress:
                    AddressActivity.go2this(getActivity());
                    break;
                case R.id.btnScreen:
                    ScreenLabActivity.go2this(getActivity());
                    break;
                case R.id.btnAdd:
                    JobIntentionActivity.go2this(getActivity());
//                    PositionSelectActivity.go2this(getActivity());
                    break;
                case R.id.btnSearch:
                    ScreenActivity.go2this(getActivity(), rcmdOrNew, false);
                    break;
            }
        }
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

    public void getPositionTypeList(MdlBaseHttpResp<MdlPositionType> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            if (null != resp.getData().getData() && resp.getData().getData().size() > 0) {
                list.clear();
                list.addAll(resp.getData().getData());
                positionType = list.get(0).getName();
                MyApp.workHopeId=list.get(0).getId();
                MyApp.workHopeName=list.get(0).getName();
            }
        }
        tabViewAdapter.notifyDataSetChanged();
        getInfo();
    }

    public void showSearch(MdlBaseHttpResp<MdlPositionList> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData() && null != resp.getData().getData().getResult()) {
            listAll.addAll(resp.getData().getData().getResult());
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getIsFinishInfoSuccess(MdlBaseHttpResp<DataBean> resp) {
        LogAndToastUtil.log("====="+resp.getStatus());
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            Boolean isFinishInfo = (Boolean) resp.getData().getData();
        }
    }
}