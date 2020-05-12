package com.jsjlzj.wayne.ui.publicac;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.adapter.recycler.WrapContentLinearLayoutManager;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.address.MalAddressProvince;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityPresenter;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索结果
 */
public class AddressActivity extends MVPBaseActivity<LoginActivityView, LoginActivityPresenter> implements LoginActivityView {
    public static int FLAG = 7;
    private String province;
    private String provinceId;
    private String city;
    private String cityId;
    private String area;
    private String areaId;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, AddressActivity.class);
        intent.putExtra("isResult", "");
        context.startActivityForResult(intent, FLAG);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_address;
    }



    private MyRecyclerView<MalAddressProvince.DataBean.MalAddressProvice> recyclerView1;
    private MyRecyclerView<MalAddressProvince.DataBean.MalAddressProvice.MalAddressCity> recyclerView2;
    private MyRecyclerView<MalAddressProvince.DataBean.MalAddressProvice.MalAddressCity.MalAddressCounty> recyclerView3;
    private MyRecyclerAdapter<MalAddressProvince.DataBean.MalAddressProvice> recyclerViewAdapter1;
    private MyRecyclerAdapter<MalAddressProvince.DataBean.MalAddressProvice.MalAddressCity> recyclerViewAdapter2;
    private MyRecyclerAdapter<MalAddressProvince.DataBean.MalAddressProvice.MalAddressCity.MalAddressCounty> recyclerViewAdapter3;

    private List<MalAddressProvince.DataBean.MalAddressProvice> addresss = new ArrayList<>();

    @Override
    protected void initViewAndControl() {
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnKeep).setOnClickListener(clickListener);
        initAdapter();
    }

    @Override
    protected LoginActivityPresenter createPresenter() {
        return new LoginActivityPresenter(this);
    }

    private void initAdapter() {//搜索结果
        recyclerView1 = findView(R.id.rvAddress1);
        recyclerView2 = findView(R.id.rvAddress2);
        recyclerView3 = findView(R.id.rvAddress3);
        recyclerView1.setPullRefreshEnabled(false);
        recyclerView1.setLoadingMoreEnabled(false);
        recyclerView2.setPullRefreshEnabled(false);
        recyclerView2.setLoadingMoreEnabled(false);
        recyclerView3.setPullRefreshEnabled(false);
        recyclerView3.setLoadingMoreEnabled(false);
        LinearLayoutManager layoutManager1 = new WrapContentLinearLayoutManager(this);
        LinearLayoutManager layoutManager2 = new WrapContentLinearLayoutManager(this);
        LinearLayoutManager layoutManager3 = new WrapContentLinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager3.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView3.setLayoutManager(layoutManager3);
        if(null!=MyApp.provinceList) {
            addresss.addAll(MyApp.provinceList);
        }else{
            presenter.getAllArea(null);

        }
        recyclerViewAdapter1 = new MyRecyclerAdapter<MalAddressProvince.DataBean.MalAddressProvice>(this, R.layout.item_address) {
            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, final MalAddressProvince.DataBean.MalAddressProvice item, int position) {
                if (item != null) {
                    helper.setText(R.id.tvPositionName, item.getShortName() + "");
                    if(position==currentIndexProvince){
                        helper.setSelected(R.id.tvPositionName,true);
                    }else{
                        helper.setSelected(R.id.tvPositionName,false);
                    }
                    helper.setOnClickListener(new OnMultiClickListener() {
                        @Override
                        public void OnMultiClick(View view) {
//                            view.setSelected(true);
//                            view.setBackgroundColor(R.color.colorPrimary);
                            province=item.getShortName();
                            provinceId = String.valueOf(item.getId());
//                            currentIndexCity=0;
//                            currentIndexProvince=0;
                            refreachProvince(position);
                            recyclerViewAdapter1.notifyDataSetChanged();
                        }
                    });
                }
            }
        };
        recyclerViewAdapter2 = new MyRecyclerAdapter<MalAddressProvince.DataBean.MalAddressProvice.MalAddressCity>(this, R.layout.item_address) {
            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, final MalAddressProvince.DataBean.MalAddressProvice.MalAddressCity item, int position) {
                if (item != null) {
                    helper.setText(R.id.tvPositionName, item.getShortName() + "");
                    if(position==currentIndexCity){
                        helper.setSelected(R.id.tvPositionName,true);
                    }else{
                        helper.setSelected(R.id.tvPositionName,false);
                    }
                    helper.setOnClickListener(new OnMultiClickListener() {
                        @Override
                        public void OnMultiClick(View view) {
                            view.setSelected(true);
//                            view.setBackgroundColor(R.color.colorPrimary);
//                            currentIndexCity=0;
                            refreachCity(position);
                            city=item.getShortName();
                            cityId=String.valueOf(item.getId());
                            recyclerViewAdapter2.notifyDataSetChanged();
                        }
                    });
                }
            }
        };
        recyclerViewAdapter3 = new MyRecyclerAdapter<MalAddressProvince.DataBean.MalAddressProvice.MalAddressCity.MalAddressCounty>(this, R.layout.item_address) {
            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, final MalAddressProvince.DataBean.MalAddressProvice.MalAddressCity.MalAddressCounty item, int position) {
                if (item != null) {
                    helper.setText(R.id.tvPositionName, item.getShortName() + "");
                    if(position==currentIndexCounty){
                        helper.setSelected(R.id.tvPositionName,true);
                    }else{
                        helper.setSelected(R.id.tvPositionName,false);
                    }
                    helper.setOnClickListener(new OnMultiClickListener() {
                        @Override
                        public void OnMultiClick(View view) {
                            view.setSelected(true);
                            currentIndexCounty = position;
//                            refreachCounty(position);
//                            view.setBackgroundColor(R.color.colorPrimary);
                            area = item.getShortName();
                            areaId = String.valueOf(item.getId());
                            recyclerViewAdapter3.notifyDataSetChanged();
                        }
                    });
                }
            }
        };
        recyclerView1.setAdapter(recyclerViewAdapter1);
        recyclerView2.setAdapter(recyclerViewAdapter2);
        recyclerView3.setAdapter(recyclerViewAdapter3);
        if(addresss!=null&&addresss.size()!=0) {
            recyclerViewAdapter1.setData(addresss);
        }
    }

    private int currentIndexProvince = -1, currentIndexCity = -1, currentIndexCounty = -1;

    private void refreachProvince(int position) {
        currentIndexProvince = position;
        recyclerViewAdapter2.setData(addresss.get(currentIndexProvince).getCityList());
        recyclerViewAdapter3.setData(null);

    }

    private void refreachCity(int position) {
        if (currentIndexProvince != -1) {
            currentIndexCity = position;
            recyclerViewAdapter3.setData(addresss.get(currentIndexProvince).getCityList().get(currentIndexCity).getAreaList());
        }
    }

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    setIntent(new Intent());
                    finish();
                    break;
                case R.id.btnKeep:
                    if(TextUtils.isEmpty(province)){
                        LogAndToastUtil.toast("请选择省");
                        return;
                    }
                    if(TextUtils.isEmpty(city)){
                        LogAndToastUtil.toast("请选择市");
                        return;
                    }
                    if(TextUtils.isEmpty(area)){
                        LogAndToastUtil.toast("请选择区");
                        return;
                    }
                    Intent intent=new Intent();
                    intent.putExtra("province",province);
                    intent.putExtra("provinceId",provinceId);
                    intent.putExtra("city",city);
                    intent.putExtra("cityId",cityId);
                    intent.putExtra("area",area);
                    intent.putExtra("areaId",areaId);
                    setResult(RESULT_OK,intent);
                    finish();
                    break;
                default:break;
            }
        }
    }

    @Override
    public void showResultAllArea(MdlBaseHttpResp<MalAddressProvince> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MyApp.provinceList=resp.getData().getData().getProvinceList();
            addresss.addAll(MyApp.provinceList);
            recyclerViewAdapter1.notifyDataSetChanged();
            recyclerViewAdapter2.notifyDataSetChanged();
            recyclerViewAdapter3.notifyDataSetChanged();
        }
    }

}