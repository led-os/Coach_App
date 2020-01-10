package com.jsjlzj.wayne.ui.store.talent.utilac;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.ItemsBean;
import com.jsjlzj.wayne.entity.store.MdlDict;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityPresenter;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityView;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索 条件选择
 */
public class ScreenLabActivity extends MVPBaseActivity<LoginActivityView, LoginActivityPresenter> implements LoginActivityView {

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, ScreenLabActivity.class);
        context.startActivityForResult(intent, 1000);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_screen_lab;
    }

    @Override
    protected LoginActivityPresenter createPresenter() {
        return new LoginActivityPresenter(this);
    }

    private MyRecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView4;
    private MyRecyclerAdapter adapter1, adapter2, adapter3, adapter4;
    private List<ItemsBean> datas1, datas2, datas3, datas4;
    private List<String> lowestEducationLevelCodes, workStatusCodes, workYearsCodes;
    private String salaryRequiredCode = "";
    private TextView toolbarLeft;
    @Override
    protected void initViewAndControl() {
        findView(R.id.btnConfirm).setOnClickListener(clickListener);
        recyclerView1 = findView(R.id.rvSreen1);
        recyclerView2 = findView(R.id.rvSreen2);
        recyclerView3 = findView(R.id.rvSreen3);
        recyclerView4 = findView(R.id.rvSreen4);
        toolbarLeft = findView(R.id.toolbarLeft);
        toolbarLeft.setBackground(getDrawable(R.drawable.ic_back_b));
        toolbarLeft.setOnClickListener(clickListener);
        workYearsCodes = new ArrayList<>();
        lowestEducationLevelCodes = new ArrayList<>();
        workStatusCodes = new ArrayList<>();
        MdlDict.DataBean dataBean = MyApp.mdlDict;
        if (null != dataBean) {
            initData(dataBean);
        } else {
            presenter.getAll(null);
        }
    }


    private void initViewPager(MyRecyclerView recyclerView, MyRecyclerAdapter adapter, List<ItemsBean> texts) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);
        adapter = new MyRecyclerAdapter<ItemsBean>(this, R.layout.item_screen_lab, texts) {
            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, ItemsBean item, int position) {
                if (item == null) return;
                helper.getView(R.id.tvScreenItem).setSelected(item.isSelected());
                helper.setText(R.id.tvScreenItem, item.getName() + "");
                helper.setOnClickListener(R.id.tvScreenItem, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setSelected(!v.isSelected());
                        texts.get(position).setSelected(v.isSelected());
                        if (position == 0) {
                            for (int i = 0; i < texts.size(); i++) {
                                texts.get(i).setSelected(false);
                            }
                            texts.get(0).setSelected(true);
                        } else {
                            if (texts.get(0).isSelected()) {
                                texts.get(0).setSelected(false);
                            }
                        }
                        notifyDataSetChanged();
                    }
                });
            }
        };
        //解决数据加载不完的问题
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter);
    }

    int selected = 0;

    private void initCheckPager(MyRecyclerView recyclerView, MyRecyclerAdapter adapter, List<ItemsBean> texts) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);
        adapter = new MyRecyclerAdapter<ItemsBean>(this, R.layout.item_screen_lab, texts) {
            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, ItemsBean item, int position) {
                if (item == null) return;
                if (position != selected) {
                    helper.setSelected(R.id.tvScreenItem, false);
                    item.setSelected(false);
                } else {
                    helper.setSelected(R.id.tvScreenItem, true);
                    item.setSelected(true);
                }
                helper.setText(R.id.tvScreenItem, item.getName() + "");
                helper.setOnClickListener(R.id.tvScreenItem, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selected = position;
                        v.setSelected(true);
                        item.setSelected(true);
                        notifyDataSetChanged();
                    }
                });
            }
        };
        //解决数据加载不完的问题
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter);
    }


    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            Intent intent = new Intent();
            switch (view.getId()) {
                case R.id.btnConfirm:
                    for (int i = 0; i < datas1.size(); i++) {
                        if (datas1.get(i).isSelected() && i != 0) {
                            workYearsCodes.add(datas1.get(i).getCode());
                        }
                    }
                    for (int i = 0; i < datas2.size(); i++) {
                        if (datas2.get(i).isSelected() && i != 0) {
                            lowestEducationLevelCodes.add(datas2.get(i).getCode());
                        }
                    }
                    for (int i = 0; i < datas4.size(); i++) {
                        if (datas4.get(i).isSelected() && i != 0) {
                            workStatusCodes.add(datas4.get(i).getCode());
                        }
                    }
                    for (int i = 0; i < datas3.size(); i++) {
                        if (datas3.get(i).isSelected()) {
                            salaryRequiredCode = datas3.get(i).getCode();
                            break;
                        }
                    }
                    intent.putExtra("salaryRequiredCode", salaryRequiredCode);
                    intent.putExtra("workStatusCodes", (Serializable) workStatusCodes);
                    intent.putExtra("workYearsCodes", (Serializable) workYearsCodes);
                    intent.putExtra("lowestEducationLevelCodes", (Serializable) lowestEducationLevelCodes);
                    setResult(RESULT_OK, intent);
                    finish();
                    break;
                case R.id.toolbarLeft:
                    finish();
                    break;
            }
        }
    }


    @Override
    public void showResultgetAll(MdlBaseHttpResp<MdlDict> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MdlDict.DataBean dataBean = resp.getData().getData();
            MyApp.mdlDict = dataBean;
            initData(dataBean);
        }
    }

    public void initData(MdlDict.DataBean dataBean) {
        datas1 = new ArrayList<>();
        datas2 = new ArrayList<>();
        datas3 = new ArrayList<>();
        datas4 = new ArrayList<>();
        datas1.add(new ItemsBean("", "全部", "", true));
        datas2.add(new ItemsBean("", "全部", "", true));
        datas3.add(new ItemsBean("", "全部", "", true));
        datas4.add(new ItemsBean("", "全部", "", true));
        datas1.addAll(dataBean.getWork_years().getItems());
        for (int i = 1; i < datas1.size(); i++) {
            if (datas1.get(i).isSelected()) {
                datas1.get(0).setSelected(false);
                break;
            }
        }
        datas2.addAll(dataBean.getEducation_level().getItems());
        for (int i = 1; i < datas2.size(); i++) {
            if (datas2.get(i).isSelected()) {
                datas2.get(0).setSelected(false);
                break;
            }
        }
        datas3.addAll(dataBean.getSalary_required().getItems());
        for (int i = 1; i < datas3.size(); i++) {
            if (datas3.get(i).isSelected()) {
                datas3.get(0).setSelected(false);
                selected=i;
                break;
            }
        }
        datas4.addAll(dataBean.getWork_status().getItems());
        for (int i = 1; i < datas4.size(); i++) {
            if (datas4.get(i).isSelected()) {
                datas4.get(0).setSelected(false);
                break;
            }
        }
        initViewPager(recyclerView1, adapter1, datas1);
        initViewPager(recyclerView2, adapter2, datas2);
        initCheckPager(recyclerView3, adapter3, datas3);
        initViewPager(recyclerView4, adapter4, datas4);
    }


}
