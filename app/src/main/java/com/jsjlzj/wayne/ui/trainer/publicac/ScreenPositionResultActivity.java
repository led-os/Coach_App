package com.jsjlzj.wayne.ui.trainer.publicac;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.adapter.recycler.WrapContentLinearLayoutManager;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlPosition;
import com.jsjlzj.wayne.entity.store.MdlPositionList;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.publicac.AddressActivity;
import com.jsjlzj.wayne.ui.store.talent.utilac.ScreenLabActivity;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jsjlzj.wayne.constant.HttpConstant.SIZE10;

public class ScreenPositionResultActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {
    public static void go2this(Activity context, String searchInfo, int rcmdOrNew) {
        Intent intent = new Intent(context, ScreenPositionResultActivity.class);
        intent.putExtra("searchInfo", searchInfo);
        intent.putExtra("rcmdOrNew", rcmdOrNew);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_screen_result;
    }

    private MyRecyclerView<Object> recyclerView;
    private MyRecyclerAdapter<MdlPosition.DataBean> recyclerViewAdapter;
    private List<MdlPosition.DataBean> listAll;
    private TextView btnAddress;
    private EditText etSearch;
    private Map<Object, Object> map;
    private String province;
    private String city;
    private String salaryRequiredCode;
    private ArrayList<String> workStatusCodes;
    private ArrayList<String> workYearsCodes;
    private ArrayList<String> lowestEducationLevelCodes;
    private int pageNo = 1;
    private int rcmdOrNew;

    @Override
    protected void initViewAndControl() {
        String searchInfo = getIntent().getStringExtra("searchInfo");
        rcmdOrNew=getIntent().getIntExtra("rcmdOrNew",1);
        btnAddress = findView(R.id.btnAddressResult);
        findView(R.id.btnScreenResult).setOnClickListener(clickListener);
        findView(R.id.btnCancel).setOnClickListener(clickListener);
        btnAddress.setOnClickListener(clickListener);
        recyclerView = findView(R.id.rvScreenResult);
        etSearch = findView(R.id.etResultSearch);
        listAll=new ArrayList<>();
        etSearch.setText(searchInfo);
        etSearch.setSelection(searchInfo.length());
        initAdapter1();
        getInfo();


        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    List list = MyApp.searchList;
                    if (null != list) {
                        if (!list.contains(etSearch.getText().toString().trim())) {
                            list.add(etSearch.getText().toString().trim());
                        }
                    }
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    public void getInfo() {
        if (null == map) map = new HashMap<>();
        map.clear();
        if(pageNo==1){
            listAll.clear();
            recyclerViewAdapter.notifyDataSetChanged();
        }
        map.put("city", city);
        map.put("pageNo", pageNo);
        map.put("pageSize", SIZE10);
        map.put("name", etSearch.getText().toString().trim());
        map.put("province", province);
        map.put("rcmdOrNew", rcmdOrNew);//推荐最新
        map.put("salaryRequiredCode", salaryRequiredCode);//薪资要求
        map.put("lowestEducationLevelCodes", lowestEducationLevelCodes);
        map.put("workStatusCodes", workStatusCodes);
        map.put("workYearsCodes", workYearsCodes);
        presenter.searchPosition(map);

    }
    private void loadMore() {
        pageNo++;
        getInfo();
        recyclerView.loadMoreComplete();
    }

    private void loadRefresh() {
        pageNo=1;
        getInfo();
        recyclerView.refreshComplete();
    }
    private void initAdapter1() {//搜索结果
        LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
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
        recyclerViewAdapter = new MyRecyclerAdapter<MdlPosition.DataBean>(this, R.layout.item_tab_position) {
            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, final MdlPosition.DataBean item, int position) {
                if (item != null) setUI(helper, item);
                helper.setOnClickListener(R.id.llItem, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PositionInfoStoreActivity.go2this(ScreenPositionResultActivity.this,item.getId());
                    }
                });
            }
        };
        recyclerViewAdapter.setData(listAll);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    private void setUI(BaseAdapterHelper helper,MdlPosition.DataBean item) {
        helper.setText(R.id.tvPositionName, item.getName());
        helper.setText(R.id.tvInfo, item.getStoreName());

        helper.setText(R.id.tvSalary, item.getSalaryMin()+"-"+item.getSalaryMax()+"K");
        String area= TextUtils.isEmpty(item.getArea())?"":item.getArea();
        String workYears= TextUtils.isEmpty(item.getWorkYears())?"":item.getWorkYears();
        String education= TextUtils.isEmpty(item.getLowestEducationLevel())?"":item.getLowestEducationLevel();
        helper.setText(R.id.psition_info, item.getProvince()+" "+item.getCity()+" "+area+"  "+workYears+"  "+education);
        helper.setText(R.id.tvContent, item.getStoreUserName()+"  "+item.getStoreUserPosition());
        ImageView iv=helper.getView(R.id.image);
        setImg(item.getStoreUserHeadImg(),iv);
    }

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnAddressResult:
                    AddressActivity.go2this(ScreenPositionResultActivity.this);
                    break;
                case R.id.btnScreenResult:
                    ScreenLabActivity.go2this(ScreenPositionResultActivity.this);
                    break;
                case R.id.btnCancel:
                    finish();
                    break;

            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && null != data) {
            switch (requestCode) {
                case 7:
                    province = data.getStringExtra("province");
                    city = data.getStringExtra("city");
                    btnAddress.setText(city);
                    currentPage=1;
                    getInfo();
                    break;
                case 1000:
                    salaryRequiredCode = data.getStringExtra("salaryRequiredCode");
                    workStatusCodes = (ArrayList<String>) data.getSerializableExtra("workStatusCodes");
                    workYearsCodes = (ArrayList<String>) data.getSerializableExtra("workYearsCodes");
                    lowestEducationLevelCodes = (ArrayList<String>) data.getSerializableExtra("lowestEducationLevelCodes");
                    currentPage=1;
                    getInfo();
                    break;
            }
        }
    }

    public void showSearch(MdlBaseHttpResp<MdlPositionList> resp) {
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()&&null!=resp.getData().getData().getResult()){
            listAll.addAll(resp.getData().getData().getResult());
            recyclerViewAdapter.notifyDataSetChanged();
        }    }
}
