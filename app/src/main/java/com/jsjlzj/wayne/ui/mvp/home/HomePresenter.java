package com.jsjlzj.wayne.ui.mvp.home;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BasePresenter;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SelectImageUtils;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * @ClassName: HomePresenter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/1/14 15:58
 */
public class HomePresenter extends BasePresenter<HomeView> {

    private static final int REQ_CODE_RECOMMEND = 1;
    private static final int REQ_CODE_AMOY_SCHOOL = 2;
    private static final int REQ_CODE_MATCH = 3;
    private static final int REQ_CODE_AMOY_LIST = 4;
    private static final int REQ_CODE_MATCH_LIST = 5;
    private static final int REQ_CODE_DRIED_FOOD = 6;
    private static final int REQ_CODE_DRIED_FOOD_LIST = 7;
    private static final int REQ_CODE_INFORMATION = 8;
    private static final int REQ_CODE_INFORMATION_LIST = 9;
    private static final int REQ_CODE_PRODUCT = 10;
    private static final int REQ_CODE_PRODUCT_LIST = 11;
    private static final int REQ_CODE_ALL_CLASSIC = 12;
    private static final int REQ_CODE_ORIGANIZATION_LIST = 13;
    private static final int REQ_CODE_VIDEO_LIST = 14;
    private static final int REQ_CODE_CLICK_ZAN = 15;
    private static final int REQ_CODE_CANCEL_ZAN = 16;
    private static final int REQ_CODE_CLICK_COLLECT = 17;
    private static final int REQ_CODE_CANCEL_COLLECT = 18;
    private static final int REQ_CODE_CLICK_FOLLOW = 19;
    private static final int REQ_CODE_CANCEL_FOLLOW = 20;
    private static final int REQ_CODE_LEARN_DATA = 21;
    private static final int REQ_CODE_CHAPTER_LIST = 22;
    private static final int REQ_CODE_CHAPTER_SUBJECT_LIST = 23;
    private static final int REQ_CODE_SAVE_ANSWER_RECORD = 24;
    private static final int REQ_CODE_WRONG_SUBJECT_LIST = 25;
    private static final int REQ_CODE_EXAM_SUBJECT_LIST = 26;
    private static final int REQ_CODE_SUBMIT_EXAM_ANSWER = 27;
    private static final int REQ_CODE_DONE_CHAPTER_ANSWER = 28;
    private static final int REQ_CODE_TEST_RESULT = 29;
    private static final int REQ_CODE_CURRENT_SUBJECT = 30;
    private static final int REQ_CODE_ANSWER_RECORD = 31;
    private static final int REQ_CODE_SEARCH = 32;
    private static final int REQ_CODE_DYNAMIC_LIST = 33;
    private static final int REQ_CODE_MINE_DYNAMIC_LIST = 34;
    private static final int REQ_CODE_AMOY_SIGN_UP = 35;
    private static final int REQ_CODE_PUBLIC_DYNAMIC = 36;
    private static final int REQ_CODE_UPLOAD_FILE = 37;
    private static final int REQ_CODE_UPLOAD_VIDEO_FILE = 41;
    private static final int REQ_CODE_DYNAMIC_COLLECT = 38;
    private static final int REQ_CODE_VIDEO_COLLECT = 39;
    private static final int REQ_CODE_INFORMATION_COLLECT = 40;
    private static final int REQ_CODE_DELETE_DYNAMIC = 42;
    private static final int REQ_CODE_OPTIMIZATION_DATA1 = 43;
    private static final int REQ_CODE_OPTIMIZATION_DATA2 = 44;
    private static final int REQ_CODE_CASH_OUT_LIST = 45;
    private static final int REQ_CODE_MINE_PROFIT = 46;
    private static final int REQ_CODE_RECOMMEND_CATEGORY_LIST = 47;
    private static final int REQ_CODE_SEARCH_CATEGORY_LIST = 48;
    private static final int REQ_CODE_JIFEN_LIST = 49;
    private static final int REQ_CODE_LOCATION_LIST = 50;
    private static final int REQ_CODE_SAVE_LOCATION = 51;
    private static final int REQ_CODE_CURRENCY_LIST = 52;
    private static final int REQ_CODE_CURRENCY_DETAIL_LIST = 53;
    private static final int REQ_CODE_GROUP_PRODUCT_LIST = 54;
    private static final int REQ_CODE_SHOPPING_CAR_LIST = 55;
    private static final int REQ_CODE_ADD_SHOPPING_CAR = 56;
    private static final int REQ_CODE_UPDATE_SHOPPING_CAR = 57;
    private static final int REQ_CODE_MINE_COUPON_LIST = 58;
    private static final int REQ_CODE_ENABLE_COUPON_LIST = 59;
    private static final int REQ_CODE_APPLY_LEADER = 60;
    private static final int REQ_CODE_BANKCARD_LIST = 61;
    private static final int REQ_CODE_BANKCARD_ITEM = 62;
    private static final int REQ_CODE_COMMIT_ORDER_2 = 63;
    private static final int REQ_CODE_SET_PAY_PASSWORD = 64;
    private static final int REQ_CODE_GET_ORDER_LIST = 65;
    private static final int REQ_CODE_SEARCH_PAY_RESULT = 66;
    private static final int REQ_CODE_CONFIRM_ORDER = 67;
    private static final int REQ_CODE_EVALUATE_ORDER = 68;
    private static final int REQ_CODE_GET_CATEGORY_LIST = 69;
    private static final int REQ_CODE_GET_COURSER_DETAIL = 70;
    private static final int REQ_CODE_BUY_COURSER = 71;
    private static final int REQ_CODE_GET_SHOPPING_DETAIL = 72;
    private static final int REQ_CODE_COMMIT_VIP_ORDER = 73;
    private static final int REQ_CODE_AFTER_ORDER_LIST = 74;
    private static final int REQ_CODE_AFTER_ORDER_CANCEL = 75;
    private static final int REQ_CODE_ORDER_DETAIL = 76;
    private static final int REQ_CODE_ORDER_CANCEL = 77;
    private static final int REQ_CODE_ORDER_AFTER_DETAIL = 78;
    private static final int REQ_CODE_CANCEL_AFTER_SALE = 79;
    private static final int REQ_CODE_LOGISTICS_LIST = 80;
    private static final int REQ_CODE_SKILL_HINT = 81;
    private static final int REQ_CODE_PROFIT_ORDER_LIST = 82;
    private static final int REQ_CODE_SHOPPING_LIST = 83;



    private HomeModel model;

    @Override
    protected HomeModel getMode() {
        return model;
    }


    public void getSmes(Map param) {
        if (model != null) {
            model.getSmes(param, this);
        }
    }


    public HomePresenter(HomeView view) {
        this.view = view;
        this.model = new HomeModel();
    }


    public void getRecommendData() {
        if (model != null) {
            view.showLoading();
            model.getHomeRecommendData(REQ_CODE_RECOMMEND, null, this);
        }
    }


    public void getCategoryList() {
        if (model != null) {
            view.showLoading();
            model.getCategoryList(REQ_CODE_GET_CATEGORY_LIST, null, this);
        }
    }

    public void getAmoySchoolData() {
        if (model != null) {
            view.showLoading();
            model.getAmoySchoolData(REQ_CODE_AMOY_SCHOOL, null, this);
        }
    }

    public void getMatchData() {
        if (model != null) {
            view.showLoading();
            model.getMatchData(REQ_CODE_MATCH, null, this);
        }
    }


    public void getAmoyList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getAmoyList(REQ_CODE_AMOY_LIST, param, this);
        }
    }


    public void getMatchList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getMatchList(REQ_CODE_MATCH_LIST, param, this);
        }
    }

    public void getDriedFoodData() {
        if (model != null) {
            view.showLoading();
            model.getDriedFoodData(REQ_CODE_DRIED_FOOD, null, this);
        }
    }

    public void getDriedFoodList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getDriedFoodList(REQ_CODE_DRIED_FOOD_LIST, param, this);
        }
    }

    public void getInformationData() {
        if (model != null) {
            view.showLoading();
            model.getInformationData(REQ_CODE_INFORMATION, null, this);
        }
    }

    public void getInformationList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getInformationList(REQ_CODE_INFORMATION_LIST, param, this);
        }
    }


    public void getProductData() {
        if (model != null) {
            view.showLoading();
            model.getProductData(REQ_CODE_PRODUCT, null, this);
        }
    }

    public void getProductList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getProductList(REQ_CODE_PRODUCT_LIST, param, this);
        }
    }


    public void getAllClassic() {
        if (model != null) {
            view.showLoading();
            model.getAllClassicList(REQ_CODE_ALL_CLASSIC, null, this);
        }
    }

    public void getOrganizationList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getOrganizationList(REQ_CODE_ORIGANIZATION_LIST, param, this);
        }
    }


    public void getVideoList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getVideoList(REQ_CODE_VIDEO_LIST, param, this);
        }
    }

    public void clickZan(Map param) {
        if (model != null) {
//            view.showLoading();
            model.clickZan(REQ_CODE_CLICK_ZAN, param, this);
        }
    }

    /**
     * "module": "模块编码:干货 GAN_HUO,视频 VIDEO,社区 COMMUNITY,在线课程 ONLINE_LEARN"
     *
     * @param param
     */
    public void cancelZan(Map param) {
        if (model != null) {
//            view.showLoading();
            model.cancelZan(REQ_CODE_CANCEL_ZAN, param, this);
        }
    }

    public void clickCollect(Map param) {
        if (model != null) {
//            view.showLoading();
            model.clickCollect(REQ_CODE_CLICK_COLLECT, param, this);
        }
    }

    /**
     * "module": "模块编码:干货 GAN_HUO,视频 VIDEO,社区 COMMUNITY,在线课程 ONLINE_LEARN"
     *
     * @param param
     */
    public void cancelCollect(Map param) {
        if (model != null) {
//            view.showLoading();
            model.cancelCollect(REQ_CODE_CANCEL_COLLECT, param, this);
        }
    }


    public void clickFollow(Map param) {
        if (model != null) {
//            view.showLoading();
            model.clickFollow(REQ_CODE_CLICK_FOLLOW, param, this);
        }
    }


    public void cancelFollow(Map param) {
        if (model != null) {
            model.cancelFollow(REQ_CODE_CANCEL_FOLLOW, param, this);
        }
    }

    public void deleteDynamic(Map param) {
        if (model != null) {
            model.deleteDynamic(REQ_CODE_DELETE_DYNAMIC, param, this);
        }
    }


    public void getLearnData() {
        if (model != null) {
            view.showLoading();
            model.getLearnData(REQ_CODE_LEARN_DATA, null, this);
        }
    }


    public void getChapterList() {
        if (model != null) {
            view.showLoading();
            model.getChapterList(REQ_CODE_CHAPTER_LIST, null, this);
        }
    }

    public void getChapterSubjectList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getChapterSubjectList(REQ_CODE_CHAPTER_SUBJECT_LIST, param, this);
        }
    }

    public void getSaveAnswerRecord(Map param) {
        if (model != null) {
            model.getSaveAnswerRecord(REQ_CODE_SAVE_ANSWER_RECORD, param, this);
        }
    }

    public void submitExamAnswer(Map param) {
        if (model != null) {
            model.submitExamAnswer(REQ_CODE_SUBMIT_EXAM_ANSWER, param, this);
        }
    }

    public void getWrongSubjectList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getWrongSubjectList(REQ_CODE_WRONG_SUBJECT_LIST, param, this);
        }
    }


    public void getExamSubjectList() {
        if (model != null) {
            view.showLoading();
            model.getExamSubjectList(REQ_CODE_EXAM_SUBJECT_LIST, null, this);
        }
    }


    public void doneChapterAnswer() {
        if (model != null) {
            view.showLoading();
            model.doneChapterAnswer(REQ_CODE_DONE_CHAPTER_ANSWER, null, this);
        }
    }

    public void getTestResult() {
        if (model != null) {
            view.showLoading();
            model.getTestResult(REQ_CODE_TEST_RESULT, null, this);
        }
    }

    public void getCurrentSubject() {
        if (model != null) {
            view.showLoading();
            model.getCurrentSubject(REQ_CODE_CURRENT_SUBJECT, null, this);
        }
    }

    public void getAnswerRecord() {
        if (model != null) {
            view.showLoading();
            model.getAnswerRecord(REQ_CODE_ANSWER_RECORD, null, this);
        }
    }

    public void getSearchData(Map param) {
        if (model != null) {
            view.showLoading();
            model.getSearchData(REQ_CODE_SEARCH, param, this);
        }
    }

    public void getDynamicList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getDynamicList(REQ_CODE_DYNAMIC_LIST, param, this);
        }
    }

    public void getMineDynamicList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getMineDynamicList(REQ_CODE_MINE_DYNAMIC_LIST, param, this);
        }
    }


    public void getAmoySiguUp(Map param) {
        if (model != null) {
            view.showLoading();
            model.getAmoySignUp(REQ_CODE_AMOY_SIGN_UP, param, this);
        }
    }


    public void getMatchSiguUp(Map param) {
        if (model != null) {
            view.showLoading();
            model.getMatchSignUp(REQ_CODE_AMOY_SIGN_UP, param, this);
        }
    }

    public void publicDynamic(Map param) {
        if (model != null) {
            view.showLoading();
            model.publicDynamic(REQ_CODE_PUBLIC_DYNAMIC, param, this);
        }
    }


    public void upload(String path) {
        if (model != null) {
            view.showLoading();
            model.upload(REQ_CODE_UPLOAD_FILE, path, this);
        }
    }

    public void uploadVideo(String path) {
        if (model != null) {
            view.showLoading();
            model.uploadVideo(REQ_CODE_UPLOAD_VIDEO_FILE, path, this);
        }
    }

    public void getCollectDynamicList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getCollectDynamicList(REQ_CODE_DYNAMIC_COLLECT, param, this);
        }
    }

    public void getCollectVideoList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getCollectVideoList(REQ_CODE_VIDEO_COLLECT, param, this);
        }
    }


    public void getCollectInformationList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getCollectInformationList(REQ_CODE_INFORMATION_COLLECT, param, this);
        }
    }

    public void getOptimizationData1() {
        if (model != null) {
            view.showLoading();
            model.getOptimizationData1(REQ_CODE_OPTIMIZATION_DATA1, null, this);
        }
    }

    public void getOptimizationData2() {
        if (model != null) {
            view.showLoading();
            model.getOptimizationData2(REQ_CODE_OPTIMIZATION_DATA2, null, this);
        }
    }

    public void getCashOutList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getCashOutList(REQ_CODE_CASH_OUT_LIST, param, this);
        }
    }

    public void getMineProfit() {
        if (model != null) {
            view.showLoading();
            model.getMineProfit(REQ_CODE_MINE_PROFIT, null, this);
        }
    }

    public void getRecommendCategoryList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getRecommendCategoryList(REQ_CODE_RECOMMEND_CATEGORY_LIST, param, this);
        }
    }

    public void getFreeExperCategoryList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getFreeExperCategoryList(REQ_CODE_RECOMMEND_CATEGORY_LIST, param, this);
        }
    }

    public void getHotCategoryList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getHotCategoryList(REQ_CODE_RECOMMEND_CATEGORY_LIST, param, this);
        }
    }

    public void getHotListeningCategoryList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getHotListeningCategoryList(REQ_CODE_RECOMMEND_CATEGORY_LIST, param, this);
        }
    }

    public void getJianzhiCategoryList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getJianzhiCategoryList(REQ_CODE_RECOMMEND_CATEGORY_LIST, param, this);
        }
    }

    public void getMotionCategoryList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getMotionCategoryList(REQ_CODE_RECOMMEND_CATEGORY_LIST, param, this);
        }
    }

    public void getFourLessonCategoryList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getFourLessonCategoryList(REQ_CODE_RECOMMEND_CATEGORY_LIST, param, this);
        }
    }


    public void getJifenList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getJifenList(REQ_CODE_JIFEN_LIST, param, this);
        }
    }

    public void getSearchCategoryList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getSearchCategoryList(REQ_CODE_SEARCH_CATEGORY_LIST, param, this);
        }
    }

    public void getLocationList() {
        if (model != null) {
            view.showLoading();
            model.getLocationList(REQ_CODE_LOCATION_LIST, null, this);
        }
    }

    public void saveLocation(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveLocation(REQ_CODE_SAVE_LOCATION, param, this);
        }
    }

    public void modifyLocation(Map param) {
        if (model != null) {
            view.showLoading();
            model.modifyLocation(REQ_CODE_SAVE_LOCATION, param, this);
        }
    }

    public void deleteLocation(Map param) {
        if (model != null) {
            view.showLoading();
            model.deleteLocation(REQ_CODE_SAVE_LOCATION, param, this);
        }
    }

    public void getCurrencyList() {
        if (model != null) {
            view.showLoading();
            model.getCurrencyList(REQ_CODE_CURRENCY_LIST, null, this);
        }
    }

    public void getCurrencyDetailList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getCurrencyDetailList(REQ_CODE_CURRENCY_DETAIL_LIST, param, this);
        }
    }

    public void getGroupProductList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getGroupProductList(REQ_CODE_GROUP_PRODUCT_LIST, param, this);
        }
    }

    public void getSearchProductList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getSearchProductList(REQ_CODE_GROUP_PRODUCT_LIST, param, this);
        }
    }

    public void getSearchNewProductList() {
        if (model != null) {
//            view.showLoading();
            model.getSearchNewProductList(REQ_CODE_SHOPPING_LIST, null, this);
        }
    }

    public void getSearchHotProductList() {
        if (model != null) {
//            view.showLoading();
            model.getSearchHotProductList(REQ_CODE_SHOPPING_LIST, null, this);
        }
    }

    public void getTimeSkillProductList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getTimeSkillProductList(REQ_CODE_GROUP_PRODUCT_LIST, param, this);
        }
    }


    public void getTimeSkillHint(Map param) {
        if (model != null) {
            view.showLoading();
            model.getTimeSkillHint(REQ_CODE_SKILL_HINT, param, this);
        }
    }


    public void getShoppingCarList() {
        if (model != null) {
            view.showLoading();
            model.getShoppingCarList(REQ_CODE_SHOPPING_CAR_LIST, this);
        }
    }
    public void commitOrder(Map params) {
        if (model != null) {
            view.showLoading();
            model.commitOrder(params,REQ_CODE_SHOPPING_CAR_LIST, this);
        }
    }
    public void commitOrder2(Map params) {
        if (model != null) {
            view.showLoading();
            model.commitOrder2(params,REQ_CODE_COMMIT_ORDER_2, this);
        }
    }

    public void getMineCouponList(int useStatus) {
        if (model != null) {
            view.showLoading();
            model.getMineCouponList(useStatus,REQ_CODE_MINE_COUPON_LIST, this);
        }
    }

    public void getEnableCouponList() {
        if (model != null) {
//            view.showLoading();
            model.getEnableCouponList(REQ_CODE_ENABLE_COUPON_LIST, null,this);
        }
    }
    public void getEnableCoupon(Map params) {
        if (model != null) {
//            view.showLoading();
            model.getEnableCoupon(REQ_CODE_ENABLE_COUPON_LIST, params,this);
        }
    }


    public void addShoppingCar(Map params) {
        if (model != null) {
            model.addShoppingCar(REQ_CODE_ADD_SHOPPING_CAR, params, this);
        }
    }

    public void deleteCar(Map params) {
        if (model != null) {
            model.deleteCar(REQ_CODE_SHOPPING_CAR_LIST, params, this);
        }
    }

    public void toPayOrder(Map params) {
        if (model != null) {
            view.showLoading();
            model.toPayOrder(REQ_CODE_COMMIT_ORDER_2, params, this);
        }
    }

    public void getOrderList(Map params) {
        if (model != null) {
            view.showLoading();
            model.getOrderList(REQ_CODE_GET_ORDER_LIST, params, this);
        }
    }
    public void getProfitOrderList(Map params) {
        if (model != null) {
            view.showLoading();
            model.getProfitOrderList(REQ_CODE_PROFIT_ORDER_LIST, params, this);
        }
    }

    public void getAfterOrderList(Map params) {
        if (model != null) {
            view.showLoading();
            model.getAfterOrderList(REQ_CODE_AFTER_ORDER_LIST, params, this);
        }
    }

    public void getAfterOrderCancel(Map params) {
        if (model != null) {
            view.showLoading();
            model.getAfterOrderCancel(REQ_CODE_AFTER_ORDER_CANCEL, params, this);
        }
    }

    public void getOrderDetail(Map params) {
        if (model != null) {
            view.showLoading();
            model.getOrderDetail(REQ_CODE_ORDER_DETAIL, params, this);
        }
    }
    public void getCancelAfterSale(Map params) {
        if (model != null) {
            view.showLoading();
            model.getCancelAfterSale(REQ_CODE_CANCEL_AFTER_SALE, params, this);
        }
    }

    public void saveAfterSale(Map params) {
        if (model != null) {
            view.showLoading();
            model.saveAfterSale(REQ_CODE_CANCEL_AFTER_SALE, params, this);
        }
    }
    public void getOrderAfterDetail(Map params) {
        if (model != null) {
            view.showLoading();
            model.getOrderAfterDetail(REQ_CODE_ORDER_AFTER_DETAIL, params, this);
        }
    }

    public void getOrderCancel(String orderCode) {
        if (model != null) {
            view.showLoading();
            model.getOrderCancel(REQ_CODE_ORDER_CANCEL, orderCode, this);
        }
    }

    public void searchPayResult(Map params) {
        if (model != null) {
            model.searchPayResult(REQ_CODE_SEARCH_PAY_RESULT, params, this);
        }
    }

    public void confirmOrder(String orderCode) {
        if (model != null) {
            model.confirmOrder(REQ_CODE_CONFIRM_ORDER, orderCode, this);
        }
    }


    public void commitEvaluateOrder(Map params) {
        if (model != null) {
            view.showLoading();
            model.commitEvaluateOrder(REQ_CODE_EVALUATE_ORDER, params, this);
        }
    }

    public void getLogisticsInfo(Map params) {
        if (model != null) {
            view.showLoading();
            model.getLogisticsInfo(REQ_CODE_LOGISTICS_LIST, params, this);
        }
    }
    public void setPayPassword(Map params) {
        if (model != null) {
            view.showLoading();
            model.setPayPassword(REQ_CODE_SET_PAY_PASSWORD, params, this);
        }
    }
    public void getCourserDetail(Map params) {
        if (model != null) {
            view.showLoading();
            model.getCourserDetail(REQ_CODE_GET_COURSER_DETAIL, params, this);
        }
    }

    public void buyCourserByCurrency(Map params) {
        if (model != null) {
            view.showLoading();
            model.buyCourserByCurrency(REQ_CODE_BUY_COURSER, params, this);
        }
    }

    public void commitVipOrder(Map params) {
        if (model != null) {
            view.showLoading();
            model.commitVipOrder(REQ_CODE_COMMIT_VIP_ORDER, params, this);
        }
    }

    public void getShoppingDetail(int params) {
        if (model != null) {
            view.showLoading();
            model.getShoppingDetail(REQ_CODE_GET_SHOPPING_DETAIL, params, this);
        }
    }

    public void updateShoppingBynum(Map params) {
        if (model != null) {
            model.updateBynum(REQ_CODE_UPDATE_SHOPPING_CAR, params, this);
        }
    }

    public void applyLeader(Map params) {
        if (model != null) {
            model.applyLeader(REQ_CODE_APPLY_LEADER, params, this);
        }
    }

    public void getBankCardList() {
        if (model != null) {
            model.getBankCardList(REQ_CODE_BANKCARD_LIST, null, this);
        }
    }
    public void deleteBankCard(Map params) {
        if (model != null) {
            model.deleteBankCard(REQ_CODE_BANKCARD_LIST, params, this);
        }
    }

    public void getBankCardInfo(Map params) {
        if (model != null) {
            model.getBankCardInfo(REQ_CODE_BANKCARD_ITEM, params, this);
        }
    }

    public void applyCashout(Map params) {
        if (model != null) {
            model.applyCashout(REQ_CODE_APPLY_LEADER, params, this);
        }
    }

    public void applySettledIn(Map params) {
        if (model != null) {
            model.applySettledIn(REQ_CODE_APPLY_LEADER, params, this);
        }
    }

    public void saveBankCard(Map params) {
        if (model != null) {
            model.saveBankCard(REQ_CODE_APPLY_LEADER, params, this);
        }
    }



    @Override
    protected void responseSuccess(int code, MdlBaseHttpResp resp) {
        view.hideLoading();
        switch (code) {
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
            case REQ_CODE_ORIGANIZATION_LIST:
            case REQ_CODE_PRODUCT_LIST:
                view.getCategoryListSuccess(resp);
                break;
            case REQ_CODE_MINE_DYNAMIC_LIST:
            case REQ_CODE_DYNAMIC_LIST:
            case REQ_CODE_VIDEO_LIST:
            case REQ_CODE_INFORMATION_LIST:
            case REQ_CODE_DRIED_FOOD_LIST:
            case REQ_CODE_DYNAMIC_COLLECT:
            case REQ_CODE_INFORMATION_COLLECT:
            case REQ_CODE_VIDEO_COLLECT:
                view.getVideoListSuccess(resp);
                break;
            case REQ_CODE_ALL_CLASSIC:
                view.getAllClassicSuccess(resp);
                break;
            case REQ_CODE_EVALUATE_ORDER:
            case REQ_CODE_CONFIRM_ORDER:
            case REQ_CODE_CANCEL_ZAN:
            case REQ_CODE_CLICK_ZAN:
            case REQ_CODE_CLICK_COLLECT:
            case REQ_CODE_CANCEL_COLLECT:
            case REQ_CODE_CLICK_FOLLOW:
            case REQ_CODE_CANCEL_FOLLOW:
            case REQ_CODE_ADD_SHOPPING_CAR:
            case REQ_CODE_APPLY_LEADER:
            case REQ_CODE_BUY_COURSER:
            case REQ_CODE_AFTER_ORDER_CANCEL:
            case REQ_CODE_ORDER_CANCEL:
            case REQ_CODE_CANCEL_AFTER_SALE:
            case REQ_CODE_SKILL_HINT:
                view.getMessageSuccess(resp);
                break;
            case REQ_CODE_DELETE_DYNAMIC:
                view.deleteDynamicSuccess(resp);
                break;
            case REQ_CODE_LEARN_DATA:
                view.getLearnDataSuccess(resp);
                break;
            case REQ_CODE_CHAPTER_LIST:
                view.getChapterListSuccess(resp);
                break;
            case REQ_CODE_WRONG_SUBJECT_LIST:
            case REQ_CODE_CHAPTER_SUBJECT_LIST:
                view.getChapterSubjectListSuccess(resp);
                break;
            case REQ_CODE_SAVE_ANSWER_RECORD:
                view.saveAnswerSuccess(resp);
                break;
            case REQ_CODE_TEST_RESULT:
            case REQ_CODE_EXAM_SUBJECT_LIST:
                view.getExamSubjectListSuccess(resp);
                break;
            case REQ_CODE_DONE_CHAPTER_ANSWER:
            case REQ_CODE_SUBMIT_EXAM_ANSWER:
                view.submitExamAnswerSuccess(resp);
                break;
            case REQ_CODE_CURRENT_SUBJECT:
                view.getChapterListSuccess(resp);
                break;
            case REQ_CODE_ANSWER_RECORD:
                view.getAnswerRecordListSuccess(resp);
                break;
            case REQ_CODE_SEARCH:
                view.getSearchDataSuccess(resp);
                break;
            case REQ_CODE_AMOY_SIGN_UP:
                view.amoySignUpSuccess(resp);
                break;
            case REQ_CODE_PUBLIC_DYNAMIC:
                view.publicDynamicSuccess(resp);
                break;
            case REQ_CODE_UPLOAD_VIDEO_FILE:
            case REQ_CODE_UPLOAD_FILE:
                view.showUpload(resp);
                break;
            case REQ_CODE_OPTIMIZATION_DATA1:
                view.getOptimizationData1Success(resp);
                break;
            case REQ_CODE_OPTIMIZATION_DATA2:
                view.getOptimizationData2Success(resp);
                break;
            case REQ_CODE_CASH_OUT_LIST:
                view.getCashOutListSuccess(resp);
                break;
            case REQ_CODE_MINE_PROFIT:
                view.getMineProfitSuccess(resp);
                break;
            case REQ_CODE_SEARCH_CATEGORY_LIST:
            case REQ_CODE_RECOMMEND_CATEGORY_LIST:
                view.getRecommendCategoryListSuccess(resp);
                break;
            case REQ_CODE_JIFEN_LIST:
                view.getJifenListSuccess(resp);
                break;
            case REQ_CODE_LOCATION_LIST:
                view.getLocationListSuccess(resp);
                break;
            case REQ_CODE_SAVE_LOCATION:
                view.saveLocationSuccess(resp);
                break;
            case REQ_CODE_CURRENCY_LIST:
                view.getCurrencyListSuccess(resp);
                break;
            case REQ_CODE_CURRENCY_DETAIL_LIST:
                view.getCurrencyDetailListSuccess(resp);
                break;
            case REQ_CODE_GROUP_PRODUCT_LIST:
                view.getShoppingListSuccess(resp);
                break;
            case REQ_CODE_UPDATE_SHOPPING_CAR:
            case REQ_CODE_SHOPPING_CAR_LIST:
                view.getShoppingCarListSuccess(resp);
                break;
            case REQ_CODE_MINE_COUPON_LIST:
                view.getMineCouponListSuccess(resp);
                break;
            case REQ_CODE_ENABLE_COUPON_LIST:
                view.getEnableCouponListSuccess(resp);
                break;
            case REQ_CODE_BANKCARD_LIST:
                view.getBankCardListSuccess(resp);
                break;
            case REQ_CODE_BANKCARD_ITEM:
                view.getBankCardItemSuccess(resp);
                break;
            case REQ_CODE_COMMIT_ORDER_2:
                view.commitOrder2Success(resp);
                break;
            case REQ_CODE_SET_PAY_PASSWORD:
                view.setPayPasswordSuccess(resp);
                break;
            case REQ_CODE_GET_ORDER_LIST:
                view.getOrderListSuccess(resp);
                break;
            case REQ_CODE_SEARCH_PAY_RESULT:
                view.searchPayResultSuccess(resp);
                break;
            case REQ_CODE_GET_CATEGORY_LIST:
                view.getFindCategoryListSuccess(resp);
                break;
            case REQ_CODE_GET_COURSER_DETAIL:
                view.getCourserDetailSuccess(resp);
                break;
            case REQ_CODE_GET_SHOPPING_DETAIL:
                view.getShoppingDetailSuccess(resp);
                break;
            case REQ_CODE_COMMIT_VIP_ORDER:
                view.commitVipOrderSuccess(resp);
                break;
            case REQ_CODE_AFTER_ORDER_LIST:
                view.getAfterOrderListSuccess(resp);
                break;
            case REQ_CODE_ORDER_DETAIL:
                view.getOrderDetailSuccess(resp);
                break;
            case REQ_CODE_ORDER_AFTER_DETAIL:
                view.getOrderAfterDetailSuccess(resp);
                break;
            case REQ_CODE_LOGISTICS_LIST:
                view.getLogisticsListSuccess(resp);
                break;
            case REQ_CODE_PROFIT_ORDER_LIST:
                view.getProfitOrderListSuccess(resp);
                break;
            case REQ_CODE_SHOPPING_LIST:
                view.getNewShoppingListSuccess(resp);
                break;
            default:
                break;
        }
    }


    /**
     * 读写文件请求码
     */
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private int curIndex;

    /**
     * 获取权限
     */
    public void autoObtainStoragePermission(Activity activity, int position) {
        curIndex = position;
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            view.selectPhoto(position);
        }
    }

    public void onRequestPermissionsResult(Activity activity, int requestCode, @NonNull int[] grantResults) {
        //调用系统相册申请Sdcard权限回调
        if (requestCode == STORAGE_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                view.selectPhoto(curIndex);
            } else {
                LogAndToastUtil.toast(activity, activity.getResources().getString(R.string.please_add_storage_permission));
            }
        }
    }

    /**
     * 图片选择及裁剪的回调
     *
     * @param activity    目标activity
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data        数据
     */
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SelectImageUtils.REQUEST_CODE_CHOOSE:
                    List<String> pathList = data.getStringArrayListExtra("result");
                    for (String path : pathList) {
                        cropPhoto(activity, path);
                    }
                    break;
                case UCrop.REQUEST_CROP:
                    Uri output = UCrop.getOutput(data);
                    if (output != null) {
                        File file = new File(output.getPath());
                        if (file.exists()) {
                            view.onUploadSuccess(file.getAbsolutePath(), curIndex);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        if (resultCode == UCrop.RESULT_ERROR) {
            Throwable cropError = UCrop.getError(data);
            if (cropError != null) {
                cropError.printStackTrace();
            }
        }
    }


    /**
     * 裁剪图片，裁剪之后的照片存储到一个新的文件
     */
    private void cropPhoto(Activity activity, String imgPath) {
        File file = new File(imgPath);
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        fileName = System.currentTimeMillis() + "." + suffix;
        File targetFile = new File(activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName);
        UCrop.Options options = new UCrop.Options();
        options.setCircleDimmedLayer(true);
        Uri parse = Uri.fromFile(file);
        UCrop.of(parse, Uri.fromFile(targetFile))
                .withAspectRatio(58, 72)
                .withMaxResultSize(1000, 1000)
                .withOptions(options)
                .start(activity);
    }
}
