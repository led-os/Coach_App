package com.jsjlzj.wayne.ui.store.talent.position.recruit;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.LeftAdapter;
import com.jsjlzj.wayne.adapter.RightAdapter;
import com.jsjlzj.wayne.adapter.recycler.WrapContentLinearLayoutManager;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlPositionType;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import java.util.ArrayList;
import java.util.List;

import static com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity.FLAG_RECRUIT_RECRUITTYPE;

/**
 * 招聘  职位类型
 */
public class RecruitTypeActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, RecruitTypeActivity.class);
        intent.putExtra("isResult", "");
        context.startActivityForResult(intent,FLAG_RECRUIT_RECRUITTYPE);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_recruit_type;
    }


    private ListView recyclerView1, recyclerView2;
    private List<String> leftList;
//    private List<MdlPositionType.DataBean> rightList;
    private List<List<MdlPositionType.DataBean.PositionTypeBean>>  rightList;//by
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
//    private MyRecyclerAdapter<String> recyclerViewAdapter1,recyclerViewAdapter2;

    @Override
    protected void initViewAndControl() {
        findView(R.id.btnBack).setOnClickListener(clickListener);
        initRecycle();
    }

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    private void initRecycle() {
        recyclerView1 = findView(R.id.rvRecruitType1);


        recyclerView2 = findView(R.id.rvRecruitType2);
        if (null == leftList) leftList = new ArrayList<>();
        if (null == rightList) rightList = new ArrayList<>();
        LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        leftAdapter = new LeftAdapter(this, leftList);
        recyclerView1.setAdapter(leftAdapter);
        rightAdapter = new RightAdapter(this);
        recyclerView2.setAdapter(rightAdapter);
        presenter.getPositionType(null);




//        recyclerView1.setLayoutManager(layoutManager);
//        recyclerView1.setPullRefreshEnabled(false);
//        recyclerView1.setLoadingMoreEnabled(false);
//        recyclerViewAdapter1 = new MyRecyclerAdapter<String>(this, R.layout.item_recruit_type) {
//            @Override
//            public int getItemCount() {
//                return super.getItemCount()+10;
//            }
//
//            @Override
//            public void onUpdate(BaseAdapterHelper helper, final String item, int position) {
//                helper.setOnClickListener(R.id.tvContent, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        v.setSelected(!v.isSelected());
//                    }
//                });
//            }
//        };
//        recyclerView1.setAdapter(recyclerViewAdapter1);

//        LinearLayoutManager layoutManager2 = new WrapContentLinearLayoutManager(this);
//        layoutManager.setOrientation(layoutManager.VERTICAL);
//        recyclerView2.setLayoutManager(layoutManager2);
//        recyclerView2.setPullRefreshEnabled(false);
//        recyclerView2.setLoadingMoreEnabled(false);
//        recyclerViewAdapter2 = new MyRecyclerAdapter<String>(this, R.layout.item_recruit_type) {
//            @Override
//            public int getItemCount() {
//                return super.getItemCount()+10;
//            }
//
//            @Override
//            public void onUpdate(BaseAdapterHelper helper, final String item, int position) {
//                helper.setOnClickListener(R.id.tvContent, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        v.setSelected(!v.isSelected());
//                    }
//                });
//            }
//        };
//        recyclerView2.setAdapter(recyclerViewAdapter2);


    }

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack://返回
                    finish();
                    break;
            }
        }
    }

    @Override
    public void showPositionType(MdlBaseHttpResp<MdlPositionType> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            for (int i = 0; i < resp.getData().getData().size(); i++) {
                leftList.add(resp.getData().getData().get(i).getName());
//                rightList.add(resp.getData().getData().get(i));
                rightList.add(resp.getData().getData().get(i).getPositionTypeList());//by
            }
            leftAdapter.notifyDataSetChanged();
//            rightAdapter.setList(rightList);
            rightAdapter.setList(rightList.get(0));
            rightAdapter.notifyDataSetChanged();
            recyclerView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    leftAdapter.setSelectItem(position);
//                    recyclerView2.setSelection(position);
//                rightAdapter.notifyDataSetChanged();

                    rightAdapter.setList(rightList.get(position));
                    rightAdapter.setSelectItem(position);
                }
            });
            recyclerView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    rightAdapter.setSelectItem(position);
                    Intent intent=new Intent();
//                    Log.e("bby",""+rightList.get(leftAdapter.getSelectItem()).get(position).getName());
                    intent.putExtra("name",rightList.get(leftAdapter.getSelectItem()).get(position).getName());
                    intent.putExtra("type",leftList.get(leftAdapter.getSelectItem()));
//                    intent.putExtra("type",rightList.get(leftAdapter.getSelectItem()).getPositionTypeList().get(position).getName());
                    intent.putExtra("id",rightList.get(leftAdapter.getSelectItem()).get(position).getId());
                    setResult(100,intent);
                    finish();
                }
            });
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }
}