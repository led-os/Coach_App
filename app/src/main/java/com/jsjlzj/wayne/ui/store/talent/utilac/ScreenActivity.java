package com.jsjlzj.wayne.ui.store.talent.utilac;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.adapter.recycler.WrapContentLinearLayoutManager;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.trainer.publicac.ScreenPositionResultActivity;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

import java.util.List;

/**
 * 搜索结果
 */
public class ScreenActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    public static void go2this(Activity context, int rcmdOrNew,boolean isStore) {
        Intent intent = new Intent(context, ScreenActivity.class);
        intent.putExtra("rcmdOrNew", rcmdOrNew);
        intent.putExtra("isStore", isStore);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_screen;
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    private MyRecyclerView<Object> recyclerView;
    private MyRecyclerAdapter<String> recyclerViewAdapter2;
    private EditText etSearch;
    private TextView clearSearch,btnAdd;
   private List list;
   private int rcmdOrNew;
   private boolean isStore;

    @Override
    protected void initViewAndControl() {
        clearSearch=findView(R.id.clearSearch);
        clearSearch.setOnClickListener(clickListener);
        recyclerView = findView(R.id.rvScreen);
        btnAdd = findView(R.id.btnAdd);
        etSearch = findView(R.id.etSearch);
        btnAdd.setOnClickListener(clickListener);
        rcmdOrNew=getIntent().getIntExtra("rcmdOrNew",1);
        isStore=getIntent().getBooleanExtra("isStore",false);
        list=MyApp.searchList;
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEND||(event!=null&&event.getKeyCode()==KeyEvent.KEYCODE_ENTER)){
                    if(null!=list){
                        if(!list.contains(etSearch.getText().toString().trim())){
                            list.add(etSearch.getText().toString().trim());
                        }
                    }
                    if(isStore){
                        ScreenResultActivity.go2this(ScreenActivity.this,etSearch.getText().toString().trim(),rcmdOrNew);
                    }else {
                        ScreenPositionResultActivity.go2this(ScreenActivity.this, etSearch.getText().toString().trim(), rcmdOrNew);
                    }
                    return true;
                }
                return false;
            }
        });
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
            initAdapter2();
    }


    private void initAdapter2() {//模糊搜索
        LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter2 = new MyRecyclerAdapter<String>(this, R.layout.item_screen_vague) {
            @Override
            public int getItemCount() {
                return super.getItemCount();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, final String item, int position) {
                if (item != null) {
                    helper.setText(R.id.tvPositionName,item);
                }
                helper.setOnClickListener(new OnMultiClickListener() {
                    @Override
                    public void OnMultiClick(View view) {
                        if(isStore){
                            ScreenResultActivity.go2this(ScreenActivity.this,item,rcmdOrNew);
                        }else {
                            ScreenPositionResultActivity.go2this(ScreenActivity.this, item, rcmdOrNew);
                        }
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
        recyclerViewAdapter2.setData(list);
        recyclerView.setAdapter(recyclerViewAdapter2);
    }

    private void loadMore() {
        recyclerView.loadMoreComplete();
    }

    private void loadRefresh() {
        recyclerView.refreshComplete();
    }


    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.clearSearch:
                    if(null!=MyApp.searchList) {
                        MyApp.searchList.clear();
                        recyclerViewAdapter2.notifyDataSetChanged();
                    }
                    break;
                case R.id.btnAdd:
                    finish();
                    break;

            }
        }
    }
}