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

    private static final int REQ_CODE_RECOMMEND = 1;
    private static final int REQ_CODE_AMOY_SCHOOL = 2;
    private static final int REQ_CODE_MATCH = 3;
    private static final int REQ_CODE_AMOY_LIST = 4;
    private static final int REQ_CODE_MATCH_LIST = 5;
    private static final int REQ_CODE_DRIED_FOOD= 6;
    private static final int REQ_CODE_DRIED_FOOD_LIST = 7;
    private static final int REQ_CODE_INFORMATION= 8;
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



    public void getAllClassic(){
        if (model != null) {
            view.showLoading();
            model.getAllClassicList(REQ_CODE_ALL_CLASSIC, null, this);
        }
    }

    public void getOrganizationList(Map param){
        if (model != null) {
            view.showLoading();
            model.getOrganizationList(REQ_CODE_ORIGANIZATION_LIST, param, this);
        }
    }


    public void getVideoList(Map param){
        if (model != null) {
            view.showLoading();
            model.getVideoList(REQ_CODE_VIDEO_LIST, param, this);
        }
    }

    public void clickZan(Map param){
        if (model != null) {
//            view.showLoading();
            model.clickZan(REQ_CODE_CLICK_ZAN, param, this);
        }
    }

    /**
     * "module": "模块编码:干货 GAN_HUO,视频 VIDEO,社区 COMMUNITY,在线课程 ONLINE_LEARN"
     * @param param
     */
    public void cancelZan(Map param){
        if (model != null) {
//            view.showLoading();
            model.cancelZan(REQ_CODE_CANCEL_ZAN, param, this);
        }
    }

    public void clickCollect(Map param){
        if (model != null) {
//            view.showLoading();
            model.clickCollect(REQ_CODE_CLICK_ZAN, param, this);
        }
    }

    /**
     * "module": "模块编码:干货 GAN_HUO,视频 VIDEO,社区 COMMUNITY,在线课程 ONLINE_LEARN"
     * @param param
     */
    public void cancelCollect(Map param){
        if (model != null) {
//            view.showLoading();
            model.cancelCollect(REQ_CODE_CANCEL_ZAN, param, this);
        }
    }


    public void clickFollow(Map param){
        if (model != null) {
//            view.showLoading();
            model.clickFollow(REQ_CODE_CLICK_FOLLOW, param, this);
        }
    }


    public void cancelFollow(Map param){
        if (model != null) {
            model.cancelFollow(REQ_CODE_CANCEL_FOLLOW, param, this);
        }
    }


    public void getLearnData(){
        if (model != null) {
            view.showLoading();
            model.getLearnData(REQ_CODE_LEARN_DATA, null, this);
        }
    }


    public void getChapterList(){
        if (model != null) {
            view.showLoading();
            model.getChapterList(REQ_CODE_CHAPTER_LIST, null, this);
        }
    }

    public void getChapterSubjectList(Map param){
        if (model != null) {
            view.showLoading();
            model.getChapterSubjectList(REQ_CODE_CHAPTER_SUBJECT_LIST, param, this);
        }
    }

    public void getSaveAnswerRecord(Map param){
        if (model != null) {
            model.getSaveAnswerRecord(REQ_CODE_SAVE_ANSWER_RECORD, param, this);
        }
    }

    public void submitExamAnswer(Map param){
        if (model != null) {
            model.submitExamAnswer(REQ_CODE_SUBMIT_EXAM_ANSWER, param, this);
        }
    }

    public void getWrongSubjectList(Map param){
        if (model != null) {
            view.showLoading();
            model.getWrongSubjectList(REQ_CODE_WRONG_SUBJECT_LIST, param, this);
        }
    }


   public void getExamSubjectList(){
        if (model != null) {
            view.showLoading();
            model.getExamSubjectList(REQ_CODE_EXAM_SUBJECT_LIST, null, this);
        }
    }


   public void doneChapterAnswer(){
        if (model != null) {
            view.showLoading();
            model.doneChapterAnswer(REQ_CODE_DONE_CHAPTER_ANSWER, null, this);
        }
    }

   public void getTestResult(){
        if (model != null) {
            view.showLoading();
            model.getTestResult(REQ_CODE_TEST_RESULT, null, this);
        }
    }

   public void getCurrentSubject(){
        if (model != null) {
            view.showLoading();
            model.getCurrentSubject(REQ_CODE_CURRENT_SUBJECT, null, this);
        }
    }

   public void getAnswerRecord(){
        if (model != null) {
            view.showLoading();
            model.getAnswerRecord(REQ_CODE_ANSWER_RECORD, null, this);
        }
    }

   public void getSearchData(Map param){
        if (model != null) {
            view.showLoading();
            model.getSearchData(REQ_CODE_SEARCH, param, this);
        }
    }

   public void getDynamicList(Map param){
        if (model != null) {
            view.showLoading();
            model.getDynamicList(REQ_CODE_DYNAMIC_LIST, param, this);
        }
    }

   public void getMineDynamicList(Map param){
        if (model != null) {
            view.showLoading();
            model.getMineDynamicList(REQ_CODE_MINE_DYNAMIC_LIST, param, this);
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
            case REQ_CODE_ORIGANIZATION_LIST:
            case REQ_CODE_PRODUCT_LIST:
                view.getCategoryListSuccess(resp);
                break;
            case REQ_CODE_MINE_DYNAMIC_LIST:
            case REQ_CODE_DYNAMIC_LIST:
            case REQ_CODE_VIDEO_LIST:
            case REQ_CODE_INFORMATION_LIST:
            case REQ_CODE_DRIED_FOOD_LIST:
                view.getVideoListSuccess(resp);
                break;
            case REQ_CODE_ALL_CLASSIC:
                view.getAllClassicSuccess(resp);
                break;
            case REQ_CODE_CLICK_ZAN:
                view.getClickZanSuccess(resp);
                break;
            case REQ_CODE_CANCEL_ZAN:
                view.getCancelZanSuccess(resp);
                break;
            case REQ_CODE_CLICK_COLLECT:
                view.getClickCollectSuccess(resp);
                break;
            case REQ_CODE_CANCEL_COLLECT:
                view.getCancelCollectSuccess(resp);
                break;
            case REQ_CODE_CLICK_FOLLOW:
                view.getClickFollowSuccess(resp);
                break;
            case REQ_CODE_CANCEL_FOLLOW:
                view.getCancelFollowSuccess(resp);
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


        }
    }
}
