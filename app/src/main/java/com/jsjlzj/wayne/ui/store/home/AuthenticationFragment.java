package com.jsjlzj.wayne.ui.store.home;


import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.AuthenticationAdapter;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.store.home.amoy.HotSchoolActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthenticationFragment extends MVPBaseFragment<HomeView, HomePresenter> implements HomeView, AuthenticationAdapter.OnItemClickListener {


    @BindView(R.id.rv_authentication)
    RecyclerView rvAuthentication;

    private AuthenticationAdapter authenticationAdapter;
    /**
     * 0 : 认证类   1： 技能类    2 ：管理类
     */
    private int type;


    public AuthenticationFragment() {
    }

    public AuthenticationFragment(int type) {
        this();
        this.type = type;
    }


    public static AuthenticationFragment getInstance(int type) {
        AuthenticationFragment fragment = new AuthenticationFragment(type);
        return fragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_authentication;
    }

    @Override
    protected void initViewAndControl(View view) {
        rvAuthentication.setHasFixedSize(true);
        rvAuthentication.setNestedScrollingEnabled(false);
        authenticationAdapter = new AuthenticationAdapter(getActivity(),new ArrayList<>());
        authenticationAdapter.setListener(this);
        rvAuthentication.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAuthentication.setAdapter(authenticationAdapter);
    }

    @Override
    protected void fragment2Front() {

    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    @Override
    public void onItemClick(String str) {
        HotSchoolActivity.go2this(getActivity());
    }
}
