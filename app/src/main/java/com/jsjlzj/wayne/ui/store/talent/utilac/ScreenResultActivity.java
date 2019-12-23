package com.jsjlzj.wayne.ui.store.talent.utilac;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.publicac.AddressActivity;
import com.jsjlzj.wayne.ui.store.talent.position.PositionInfoActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jsjlzj.wayne.constant.HttpConstant.SIZE10;

public class ScreenResultActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {
    public static void go2this(Activity context, String searchInfo,int rcmdOrNew) {
        Intent intent = new Intent(context, ScreenResultActivity.class);
        intent.putExtra("searchInfo", searchInfo);
        intent.putExtra("rcmdOrNew", rcmdOrNew);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_screen_result;
    }

    private MyRecyclerView<Object> recyclerView;
    private MyRecyclerAdapter<MdlCV.DataBean.ResultBean> recyclerViewAdapter;
    private List<MdlCV.DataBean.ResultBean> listAll;
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
        map.put("position", etSearch.getText().toString().trim());
        map.put("province", province);
        map.put("rcmdOrNew", rcmdOrNew);//推荐最新
        map.put("salaryRequiredCode", salaryRequiredCode);//薪资要求
        map.put("lowestEducationLevelCodes", lowestEducationLevelCodes);
        map.put("workStatusCodes", workStatusCodes);
        map.put("workYearsCodes", workYearsCodes);
        presenter.searchCV(map);

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
        recyclerViewAdapter = new MyRecyclerAdapter<MdlCV.DataBean.ResultBean>(this, R.layout.item_tab_talent) {
            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, final MdlCV.DataBean.ResultBean item, int position) {
                if (item != null) {
                    setUI(helper,item);
                }
                helper.setOnClickListener(R.id.llItem, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PositionInfoActivity.go2this(ScreenResultActivity.this, item.getId());
                    }
                });
            }
        };
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void setUI(BaseAdapterHelper helper, MdlCV.DataBean.ResultBean item) {
        helper.setText(R.id.tvPositionName, item.getName());
        String education= TextUtils.isEmpty(item.getHighestEducationLevel())?"":item.getHighestEducationLevel();
        helper.setText(R.id.tvInfo, item.getWorkYears()+"年 "+education+" "+item.getAge()+"岁");
        helper.setText(R.id.tvPositionName, item.getName());
        helper.setText(R.id.tvContent, item.getAdvantage());
        helper.setText(R.id.tvPosition, item.getPosition());
        ImageView iv=helper.getView(R.id.headImg);
        setImg(item.getHeadImg(),iv);
        FlexboxLayout llSkill=helper.getView(R.id.llLabel);
        if(null!=item.getSkillTags()&&item.getSkillTags().size()>0){
            for (int i = 0; i < item.getSkillTags().size(); i++) {
                TextView tv=new TextView(this);
                tv.setTextSize(13);
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(36,36,0,0);
                tv.setLayoutParams(params);
                tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.dr_bg_tv_g));
                tv.setPadding(30,12,30,12);
                tv.setText(item.getSkillTags().get(i));
                llSkill.addView(tv);
            }
        }
    }

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnAddressResult:
                    AddressActivity.go2this(ScreenResultActivity.this);
                    break;
                case R.id.btnScreenResult:
                    ScreenLabActivity.go2this(ScreenResultActivity.this);
                    break;
                case R.id.btnCancel:
                    finish();
                    break;

            }
        }
    }

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
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

    public void showCV(MdlBaseHttpResp<MdlCV> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            listAll.addAll(resp.getData().getData().getResult());
            recyclerViewAdapter.notifyDataSetChanged();
        }else{
            LogAndToastUtil.toast(resp.getMsg());
        }
    }
}
