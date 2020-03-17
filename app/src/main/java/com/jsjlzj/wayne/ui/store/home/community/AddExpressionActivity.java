package com.jsjlzj.wayne.ui.store.home.community;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.AddExpressionAdapter;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.entity.trainer.ExpressionBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import butterknife.BindView;

 /**
  *
  * @ClassName:      AddExpressionActivity
  * @Description:    添加表情
  * @Author:         曾海强
  * @CreateDate:
  */
public class AddExpressionActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, AddExpressionAdapter.OnItemClickListener {

    @BindView(R.id.rv_add)
    RecyclerView rvAddExpression;

    private AddExpressionAdapter adapter;

    public static void go2this(Activity context,int requestCode) {
        Intent intent = new Intent(context, AddExpressionActivity.class);
        context.startActivityForResult(intent,requestCode);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_add_expression;
    }


    @Override
    protected void initViewAndControl() {
        initTitle("添加心情/状态");
        adapter = new AddExpressionAdapter(this);
        adapter.setListener(this);
        rvAddExpression.setLayoutManager(new GridLayoutManager(this,2));
        rvAddExpression.setAdapter(adapter);
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


     @Override
     public void onItemClick(ExpressionBean str) {
        Intent intent = new Intent();
        intent.putExtra(ExtraConstant.EXTRA_DATA,str.getExpressionRes());
        setResult(RESULT_OK,intent);
        finish();
     }
 }