package com.jsjlzj.wayne.ui.mvp.home;

import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BasePresenter;

import java.util.Map;

/**
 * @ClassName: HomePresenter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/1/14 15:58
 */
public class HomePresenter extends BasePresenter<HomeView> {

    public static final int REQ_CODE_RECOMMEND = 1;
    public static final int REQ_CODE_AMOY_SCHOOL = 2;
    public static final int REQ_CODE_MATCH = 3;
    public static final int REQ_CODE_AMOY_LIST = 4;
    public static final int REQ_CODE_MATCH_LIST = 5;
    public static final int REQ_CODE_DRIED_FOOD= 6;
    public static final int REQ_CODE_DRIED_FOOD_LIST = 7;
    public static final int REQ_CODE_INFORMATION= 8;
    public static final int REQ_CODE_INFORMATION_LIST = 9;
    public static final int REQ_CODE_PRODUCT = 10;
    public static final int REQ_CODE_PRODUCT_LIST = 11;

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

    public void getMatchData(){
        if (model != null) {
            view.showLoading();
            model.getMatchData(REQ_CODE_MATCH, null, this);
        }
    }


    public void getAmoyList(Map param){
        if (model != null) {
            view.showLoading();
            model.getAmoyList(REQ_CODE_AMOY_LIST, param, this);
        }
    }


    public void getMatchList(Map param){
        if (model != null) {
            view.showLoading();
            model.getMatchList(REQ_CODE_MATCH_LIST, param, this);
        }
    }

    public void getDriedFoodData(){
        if (model != null) {
            view.showLoading();
            model.getDriedFoodData(REQ_CODE_DRIED_FOOD, null, this);
        }
    }
    public void getDriedFoodList(Map param){
        if (model != null) {
            view.showLoading();
            model.getDriedFoodList(REQ_CODE_DRIED_FOOD_LIST, param, this);
        }
    }

    public void getInformationData(){
        if (model != null) {
            view.showLoading();
            model.getInformationData(REQ_CODE_INFORMATION, null, this);
        }
    }
    public void getInformationList(Map param){
        if (model != null) {
            view.showLoading();
            model.getInformationList(REQ_CODE_INFORMATION_LIST, param, this);
        }
    }


    public void getProductData(){
        if (model != null) {
            view.showLoading();
            model.getProductData(REQ_CODE_PRODUCT, null, this);
        }
    }
    public void getProductList(Map param){
        if (model != null) {
            view.showLoading();
            model.getProductList(REQ_CODE_PRODUCT_LIST, param, this);
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
            case REQ_CODE_MATCH:
                view.getMatchSuccess(resp);
                break;
            case REQ_CODE_AMOY_LIST:
                view.getAmoyListSuccess(resp);
                break;
            case REQ_CODE_MATCH_LIST:
                view.getMatchListSuccess(resp);
                break;
            case REQ_CODE_INFORMATION:
            case REQ_CODE_PRODUCT:
            case REQ_CODE_DRIED_FOOD:
                view.getDriedFoodSuccess(resp);
                break;
            case REQ_CODE_PRODUCT_LIST:
            case REQ_CODE_INFORMATION_LIST:
            case REQ_CODE_DRIED_FOOD_LIST:
                view.getDriedFoodListSuccess(resp);
                break;

        }
    }
}
