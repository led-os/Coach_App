package com.jsjlzj.wayne.ui.mvp.home;

import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BasePresenter;

/**
 * @ClassName: HomePresenter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/1/14 15:58
 */
public class HomePresenter extends BasePresenter<HomeView> {

    private HomeModel model;

    @Override
    protected HomeModel getMode() {
        return model;
    }


    public HomePresenter(HomeView view) {
        this.view = view;
        this.model = new HomeModel();
    }

    @Override
    protected void responseSuccess(int code, MdlBaseHttpResp resp) {

    }
}
