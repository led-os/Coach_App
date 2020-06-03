package com.jsjlzj.wayne.data.http;

import com.jsjlzj.wayne.data.api.StoreService;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @ClassName: HttpDataHome
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/24 10:49
 */
public class HttpDataHome extends BaseHttpData {
    private static class SingletonHolder {
        private static final HttpDataHome INSTANCE = new HttpDataHome();
    }

    public static HttpDataHome getInstance() {
        return HttpDataHome.SingletonHolder.INSTANCE;
    }

    private StoreService service = create(StoreService.class);

    public void refreshUrl() {
        service = null;
        service = create(StoreService.class);
    }

    public void getSmes(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getSmes(body);
        setSubscribe(observable, observer);
    }


    /**
     * 获取首页推荐数据
     *
     * @param params
     * @param observer
     */
    public void getRecommendData(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestRecommend(body);
        setSubscribe(observable, observer);
    }

    public void getCategoryList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCategoryList(body);
        setSubscribe(observable, observer);
    }

    /**
     * 获取首页淘学数据
     *
     * @param params
     * @param observer
     */
    public void getAmoySchoolData(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestAmoySchool(body);
        setSubscribe(observable, observer);
    }


    /**
     * 获取首页赛事数据
     *
     * @param params
     * @param observer
     */
    public void getMatchData(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestMatch(body);
        setSubscribe(observable, observer);
    }


    /**
     * 获取首页淘学列表数据
     *
     * @param params
     * @param observer
     */
    public void getAmoyList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestAmoyList(body);
        setSubscribe(observable, observer);
    }


    /**
     * 获取首页赛事列表数据
     *
     * @param params
     * @param observer
     */
    public void getMatchList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestMatchList(body);
        setSubscribe(observable, observer);
    }


    /**
     * 获取首页干货数据
     *
     * @param params
     * @param observer
     */
    public void getDriedFoodData(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestDriedFood(body);
        setSubscribe(observable, observer);
    }

    /**
     * 获取首页干货列表数据
     *
     * @param params
     * @param observer
     */
    public void getDriedFoodList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestDriedFoodList(body);
        setSubscribe(observable, observer);
    }

    /**
     * 获取首页资讯数据
     *
     * @param params
     * @param observer
     */
    public void getInformationData(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestInformation(body);
        setSubscribe(observable, observer);
    }

    /**
     * 获取首页资讯列表数据
     *
     * @param params
     * @param observer
     */
    public void getInformationList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestInformationList(body);
        setSubscribe(observable, observer);
    }

    /**
     * 获取首页产品数据
     *
     * @param params
     * @param observer
     */
    public void getProductData(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestProduce(body);
        setSubscribe(observable, observer);
    }

    /**
     * 获取首页产品列表数据
     *
     * @param params
     * @param observer
     */
    public void getProductList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestProductList(body);
        setSubscribe(observable, observer);
    }


    /**
     * 获取所有视频类别数据
     *
     * @param params
     * @param observer
     */
    public void getAllClassicList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestAllClassicList(body);
        setSubscribe(observable, observer);
    }

    /**
     * 获取对应categoryId的热门学校（机构）数据
     *
     * @param params
     * @param observer
     */
    public void getOrganizationList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestOrganizationList(body);
        setSubscribe(observable, observer);
    }

    /**
     * 获取视频列表数据
     *
     * @param params
     * @param observer
     */
    public void getVideoList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestVideoList(body);
        setSubscribe(observable, observer);
    }

    /**
     * 点赞
     *
     * @param params
     * @param observer
     */
    public void clickZan(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestClickZan(body);
        setSubscribe(observable, observer);
    }

    /**
     * 取消点赞
     *
     * @param params
     * @param observer
     */
    public void cancelZan(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCancelZan(body);
        setSubscribe(observable, observer);
    }


    /**
     * 收藏
     *
     * @param params
     * @param observer
     */
    public void clickCollect(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestClickCollect(body);
        setSubscribe(observable, observer);
    }

    /**
     * 取消收藏
     *
     * @param params
     * @param observer
     */
    public void cancelCollect(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCancelCollect(body);
        setSubscribe(observable, observer);
    }


    /**
     * 关注
     *
     * @param params
     * @param observer
     */
    public void clickFollow(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestClickFollow(body);
        setSubscribe(observable, observer);
    }

    /**
     * 取消关注
     *
     * @param params
     * @param observer
     */
    public void cancelFollow(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCancelFollow(body);
        setSubscribe(observable, observer);
    }

    /**
     * 删除动态
     *
     * @param params
     * @param observer
     */
    public void deleteDynamic(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestDeleteDynamic(body);
        setSubscribe(observable, observer);
    }

    /**
     * 获取学习数据
     *
     * @param params
     * @param observer
     */
    public void getLearnData(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestLearnData(body);
        setSubscribe(observable, observer);
    }

    /**
     * 获取章节列表数据
     *
     * @param params
     * @param observer
     */
    public void getChapterList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestChapterList(body);
        setSubscribe(observable, observer);
    }

    /**
     * 获取章节题目列表数据
     *
     * @param params
     * @param observer
     */
    public void getChapterSubjectList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestChapterSubjectList(body);
        setSubscribe(observable, observer);
    }

    /**
     * 保存答题记录
     *
     * @param params
     * @param observer
     */
    public void getSaveAnswerRecord(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestSaveAnswerRecord(body);
        setSubscribe(observable, observer);
    }

    /**
     * 章节错误题目列表
     *
     * @param params
     * @param observer
     */
    public void getWrongSubjectList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestWrongSubjectList(body);
        setSubscribe(observable, observer);
    }


    /**
     * 章节错误题目列表
     *
     * @param params
     * @param observer
     */
    public void getExamSubjectList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestExamSubjectList(body);
        setSubscribe(observable, observer);
    }


    /**
     * 模拟考试-提交答案
     *
     * @param params
     * @param observer
     */
    public void submitExamAnswer(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestSubjectExamAnswer(body);
        setSubscribe(observable, observer);
    }


    /**
     * 模拟考试-提交答案
     *
     * @param params
     * @param observer
     */
    public void doneChapterAnswer(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestDoneChapterAnswer(body);
        setSubscribe(observable, observer);
    }


    /**
     * 模拟考试-错题集
     *
     * @param params
     * @param observer
     */
    public void getTestResult(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestErrorResult(body);
        setSubscribe(observable, observer);
    }

    /**
     * 模拟考试-错题集
     *
     * @param params
     * @param observer
     */
    public void getCurrentSubject(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCurrentSubject(body);
        setSubscribe(observable, observer);
    }

    /**
     * 模拟考试-成绩记录
     *
     * @param params
     * @param observer
     */
    public void getAnswerRecord(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestAnswerRecord(body);
        setSubscribe(observable, observer);
    }

    /**
     * 搜索全部
     *
     * @param params
     * @param observer
     */
    public void getSearchData(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestSearchData(body);
        setSubscribe(observable, observer);
    }


    /**
     * 社区列表
     *
     * @param params
     * @param observer
     */
    public void getDynamicList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestDynamicList(body);
        setSubscribe(observable, observer);
    }


    /**
     * 我的动态列表
     *
     * @param params
     * @param observer
     */
    public void getMineDynamicList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestMineDynamicList(body);
        setSubscribe(observable, observer);
    }

    /**
     * 淘学我的报名
     *
     * @param params
     * @param observer
     */
    public void getAmoySignUp(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestAmoySignUp(body);
        setSubscribe(observable, observer);
    }

    /**
     * 赛事报名
     *
     * @param params
     * @param observer
     */
    public void getMatchSignUp(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestMatchSignUp(body);
        setSubscribe(observable, observer);
    }

    /**
     * 发布动态 图片或视频
     *
     * @param params
     * @param observer
     */
    public void publicDynamic(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestPublicDynamic(body);
        setSubscribe(observable, observer);
    }

    /**
     * 我的收藏 动态列表
     *
     * @param params
     * @param observer
     */
    public void getCollectDynamicList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestDynamicCollectList(body);
        setSubscribe(observable, observer);
    }

    /**
     * 我的收藏 视频列表
     *
     * @param params
     * @param observer
     */
    public void getCollectVideoList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestVideoCollectList(body);
        setSubscribe(observable, observer);
    }

    /**
     * 我的收藏 文章列表
     *
     * @param params
     * @param observer
     */
    public void getCollectInformationList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestInformationCollectList(body);
        setSubscribe(observable, observer);
    }


    /**
     * 优选数据1 (分类导航列表+banner列表+免费体验+每日一学)
     *
     * @param params
     * @param observer
     */
    public void getOptimizationData1(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestOptimizationData1(body);
        setSubscribe(observable, observer);
    }

    /**
     * 首页优选数据2(减脂,会员精选,了解运动,4门课+分类推荐)
     *
     * @param params
     * @param observer
     */
    public void getOptimizationData2(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestOptimizationData2(body);
        setSubscribe(observable, observer);
    }

    /**
     * 提现记录
     *
     * @param params
     * @param observer
     */
    public void getCashOutList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCashOutList(body);
        setSubscribe(observable, observer);
    }

    /**
     * 我的收益
     *
     * @param params
     * @param observer
     */
    public void getMineProfit(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestMineProfit(body);
        setSubscribe(observable, observer);
    }


    public void getRecommendCategoryList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestRecommendCategoryList(body);
        setSubscribe(observable, observer);
    }

    public void getFreeExperCategoryList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestFreeExperCategoryList(body);
        setSubscribe(observable, observer);
    }

    public void getHotCategoryList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestHotCategoryList(body);
        setSubscribe(observable, observer);
    }

    public void getHotListeningCategoryList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestHotListeningCategoryList(body);
        setSubscribe(observable, observer);
    }

    public void getJianzhiCategoryList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestJianzhiCategoryList(body);
        setSubscribe(observable, observer);
    }

    public void getMotionCategoryList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestMotionCategoryList(body);
        setSubscribe(observable, observer);
    }

    public void getFourLessonCategoryList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestFourLessonCategoryList(body);
        setSubscribe(observable, observer);
    }

    public void getJifenList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestJifenList(body);
        setSubscribe(observable, observer);
    }

    public void getSearchCategoryList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestSearchCategoryList(body);
        setSubscribe(observable, observer);
    }

    public void getLocationList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestLocationList(body);
        setSubscribe(observable, observer);
    }

    public void saveLocation(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestSaveLocation(body);
        setSubscribe(observable, observer);
    }

    public void modifyLocation(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestModifyLocation(body);
        setSubscribe(observable, observer);
    }

    public void deleteLocation(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestDeleteLocation(body);
        setSubscribe(observable, observer);
    }

    public void getCurrencyList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCurrencyList(body);
        setSubscribe(observable, observer);
    }

    public void getCurrencyDetailList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCurrencyDetailList(body);
        setSubscribe(observable, observer);
    }

    public void getGroupProductList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestGroupProductList(body);
        setSubscribe(observable, observer);
    }

    public void getSearchProductList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestSearchProductList(body);
        setSubscribe(observable, observer);
    }

    public void getSearchNewProductList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestSearchNewProductList(body);
        setSubscribe(observable, observer);
    }

    public void getSearchHotProductList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestSearchHotProductList(body);
        setSubscribe(observable, observer);
    }

    public void getTimeSkillProductList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestTimeSkillProductList(body);
        setSubscribe(observable, observer);
    }

    public void getTimeSkillHint(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestTimeSkillHint(body);
        setSubscribe(observable, observer);
    }

    public void getShoppingCarList(Observer observer) {
        Observable observable = service.requestShoppingCarList();
        setSubscribe(observable, observer);
    }


    public void commitOrder(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCommitOrder(body);
        setSubscribe(observable, observer);
    }

    public void commitOrder2(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCommitOrder2(body);
        setSubscribe(observable, observer);
    }


    public void getMineCouponList(int useStatus, Observer observer) {
        Observable observable = service.requestMineCouponList(useStatus);
        setSubscribe(observable, observer);
    }

    public void getEnableCouponList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestEnableCouponList(body);
        setSubscribe(observable, observer);
    }

    public void getEnableCoupon(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestEnableCoupon(body);
        setSubscribe(observable, observer);
    }

    public void addShoppingCar(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestAddShoppingCar(body);
        setSubscribe(observable, observer);
    }

    public void deleteCar(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestDeleteCar(body);
        setSubscribe(observable, observer);
    }

    public void toPayOrder(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestPayOrder(body);
        setSubscribe(observable, observer);
    }

    public void getOrderList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestOrderList(body);
        setSubscribe(observable, observer);
    }

    public void getProfitOrderList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestProfitOrderList(body);
        setSubscribe(observable, observer);
    }

    public void getAfterOrderList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestAfterOrderList(body);
        setSubscribe(observable, observer);
    }


    public void getAfterOrderCancel(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestAfterOrderCancel(body);
        setSubscribe(observable, observer);
    }

    public void getOrderDetail(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestOrderDetail(body);
        setSubscribe(observable, observer);
    }

    public void getCancelAfterSale(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCancelAfterSale(body);
        setSubscribe(observable, observer);
    }

    public void saveAfterSale(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestSaveAfterSale(body);
        setSubscribe(observable, observer);
    }

    public void getOrderAfterDetail(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestOrderAfterDetail(body);
        setSubscribe(observable, observer);
    }

    public void getOrderCancel(String orderCode, Observer observer) {
//        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestOrderCancel(orderCode);
        setSubscribe(observable, observer);
    }

    public void searchPayResult(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestSearchPayResult(body);
        setSubscribe(observable, observer);
    }

    public void confirmOrder(String orderCode, Observer observer) {
        Observable observable = service.requestConfirmOrder(orderCode);
        setSubscribe(observable, observer);
    }

    public void commitEvaluateOrder(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestEvaluateOrder(body);
        setSubscribe(observable, observer);
    }

    public void getLogisticsInfo(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestLogisticsInfo(body);
        setSubscribe(observable, observer);
    }

    public void setPayPassword(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestPayPassword(body);
        setSubscribe(observable, observer);
    }

    public void getCourserDetail(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCourserDetail(body);
        setSubscribe(observable, observer);
    }

    public void buyCourserByCurrency(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestBuyCourserByCurrency(body);
        setSubscribe(observable, observer);
    }

    public void commitVipOrder(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCommitVipOrder(body);
        setSubscribe(observable, observer);
    }

    public void getShoppingDetail(int params, Observer observer) {

        Observable observable = service.requestShoppingDetail(params);
        setSubscribe(observable, observer);
    }

    public void updateBynum(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestUpdateBynum(body);
        setSubscribe(observable, observer);
    }

    public void applyLeader(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestApplyLeader(body);
        setSubscribe(observable, observer);
    }

    public void getBankCardList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestBankCardList(body);
        setSubscribe(observable, observer);
    }

    public void deleteBankCard(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestDeleteBankCard(body);
        setSubscribe(observable, observer);
    }

    public void getBankCardInfo(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestBankCardInfo(body);
        setSubscribe(observable, observer);
    }


    public void applyCashout(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestApplyCashout(body);
        setSubscribe(observable, observer);
    }

    public void applySettledIn(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestApplySettledIn(body);
        setSubscribe(observable, observer);
    }

    public void saveBankCard(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestSaveBankCard(body);
        setSubscribe(observable, observer);
    }

    public void upload(String picPath, Observer observer) {
        File file = new File(picPath);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("file", file.getName(), reqFile);//pic为key
        Observable observable = service.upload(photo);
        setSubscribe(observable, observer);
    }

    public void uploadVideo(String videoPath, Observer observer) {
        File file = new File(videoPath);
        RequestBody reqFile = RequestBody.create(MediaType.parse("video/mp4"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("file", file.getName(), reqFile);//pic为key
        Observable observable = service.uploadVideo(photo);
        setSubscribe(observable, observer);
    }

}
