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

    public static final int REQ_CODE_RECOMMEND = 1;
    public static final int REQ_CODE_AMOY_SCHOOL = 2;

    private HomeModel model;

    @Override
    protected HomeModel getMode() {
        return model;
    }


    public HomePresenter(HomeView view) {
        this.view = view;
        this.model = new HomeModel();
    }


    public void getRecommendData(){
        if (model != null) {
            view.showLoading();
            model.getHomeRecommendData(REQ_CODE_RECOMMEND, null, this);
        }
    }


    public void getAmoySchoolData(){
        if (model != null) {
            view.showLoading();
            model.getAmoySchoolData(REQ_CODE_AMOY_SCHOOL, null, this);
        }
    }

    @Override
    protected void responseSuccess(int code, MdlBaseHttpResp resp) {
        view.hideLoading();
        switch (code){
            case REQ_CODE_RECOMMEND:
                view.getHomeRecommendSuccess(resp);
                break;
            case REQ_CODE_AMOY_SCHOOL:
                view.getAmoySchoolSuccess(resp);
                break;
        }
    }
}
