package com.jsjlzj.wayne.ui.store.home.mine;


import android.app.Activity;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.mine.CouponAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.MineCouponBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @ClassName: CouponFragment
 * @Description: 优惠券子界面
 * @Author: 曾海强
 * @CreateDate: 2020/05/08
 */
public class CouponFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, CouponAdapter.OnItemClickListener {


    @BindView(R.id.rv_coupon)
    RecyclerView rvCoupon;
    /**
     * 0 未使用  1 ：已使用   2：已过期
     */
    private int type;
    private CouponAdapter adapter;


    public static CouponFragment getInstance(int type){
        CouponFragment  couponFragment = new CouponFragment(type);
        return couponFragment;
    }

    public CouponFragment() {
    }
    public CouponFragment(int type) {
        this.type = type;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_coupon;
    }

    @Override
    protected void fragment2Front() {
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl(View view) {
        rvCoupon.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CouponAdapter(getActivity(),new ArrayList<>());
        adapter.setListener(this);
        rvCoupon.setAdapter(adapter);
        presenter.getMineCouponList(type);
    }


    @Override
    public void getMineCouponListSuccess(MdlBaseHttpResp<MineCouponBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            if(resp.getData().getData() != null && resp.getData().getData().size() > 0){
                adapter.setData(resp.getData().getData());
            }else {
                showEmpty(R.id.rel_empty,0,null);
            }
        }
    }

    @Override
    public void onItemClick(MineCouponBean.DataBean bean) {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }
}
