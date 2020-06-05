package com.jsjlzj.wayne.data.http;


import com.jsjlzj.wayne.data.api.StoreService;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/4/17.
 */

public class HttpDataStroe extends BaseHttpData {
    private static class SingletonHolder {
        private static final HttpDataStroe INSTANCE = new HttpDataStroe();
    }

    public static HttpDataStroe getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private StoreService service = create(StoreService.class);

    public void refreshUrl() {
        service = null;
        service = create(StoreService.class);
    }

    public void getStoreInfo(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getStoreInfo(body);
        setSubscribe(observable, observer);
    }

    public void getSystemCompanyBenefits(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getSystemCompanyBenefits(body);
        setSubscribe(observable, observer);
    }
    public void saveStoreUserInfo(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveStoreUserInfo(body);
        setSubscribe(observable, observer);
    }

    public void selectStoreUserInfo(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.selectStoreUserInfo(body);
        setSubscribe(observable, observer);
    }

    public void selectAddressSearch(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getAddressSearch(body);
        setSubscribe(observable, observer);
    }

    public void saveAddressSearch(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveAddressSearch(body);
        setSubscribe(observable, observer);
    }

    public void saveStoreAuth(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveStoreAuth(body);
        setSubscribe(observable, observer);
    }

    public void saveBrandLogo(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveBrandLogo(body);
        setSubscribe(observable, observer);
    }

    public void saveCompanyBenefits(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveCompanyBenefits(body);
        setSubscribe(observable, observer);
    }

    public void saveCompanyImage(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveCompanyImage(body);
        setSubscribe(observable, observer);
    }

    public void saveCompanyProfile(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveCompanyProfile(body);
        setSubscribe(observable, observer);
    }

    public void saveStoreAddress(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveStoreAddress(body);
        setSubscribe(observable, observer);
    }

    public void saveWorkTime(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveWorkTime(body);
        setSubscribe(observable, observer);
    }

    public void changePositionStatus(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.changePositionStatus(body);
        setSubscribe(observable, observer);
    }

    public void getMyInfo(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getMyInfo(body);
        setSubscribe(observable, observer);
    }

    public void getPositionDetail(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getPositionDetail(body);
        setSubscribe(observable, observer);
    }

    public void getPositionType(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getPositionType(body);
        setSubscribe(observable, observer);
    }

    public void getPublishPositionTypeList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getPublishPositionTypeList(body);
        setSubscribe(observable, observer);
    }

    public void getSystemSkillRequired(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getSystemSkillRequired(body);
        setSubscribe(observable, observer);
    }

    public void queryByStatus(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.queryByStatus(body);
        setSubscribe(observable, observer);
    }

    public void savePosition(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.savePosition(body);
        setSubscribe(observable, observer);
    }

    public void detailCV(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.detailCV(body);
        setSubscribe(observable, observer);
    }

    public void searchCV(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.searchCV(body);
        setSubscribe(observable, observer);
    }

    public void isFinishStoreInfo(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.isFinishStoreInfo(body);
        setSubscribe(observable, observer);
    }

    public void commCV(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.commCV(body);
        setSubscribe(observable, observer);
    }

    public void getCVLike(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getCVLIKE(body);
        setSubscribe(observable, observer);
    }

    public void saveCommCV(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveCommCV(body);
        setSubscribe(observable, observer);
    }

    public void saveCVLike(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveCVLike(body);
        setSubscribe(observable, observer);
    }

    public void cancelCV(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.cancelCV(body);
        setSubscribe(observable, observer);
    }

    /**
     * 教练端-简历模块
     * 接口:创建达人简历,简历详情,职位类型列表,达人-我的
     */
    public void deleteEducationExperienceT(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.deleteEducationExperienceT(body);
        setSubscribe(observable, observer);
    }
    public void deleteWorkExperienceT(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.deleteWorkExperienceT(body);
        setSubscribe(observable, observer);
    }
    public void deleteWorkHopeT(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.deleteWorkHopeT(body);
        setSubscribe(observable, observer);
    }

    public void getDetailT(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getDetailT(body);
        setSubscribe(observable, observer);
    }
    public void getPositionTypeT(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getPositionTypeT(body);
        setSubscribe(observable, observer);
    }
    public void getWorkHopeListT(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getWorkHopeListT(body);
        setSubscribe(observable, observer);
    }

    public void myselfT(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.myselfT(body);
        setSubscribe(observable, observer);
    }

    public void saveAdvantage(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveAdvantage(body);
        setSubscribe(observable, observer);
    }

    public void saveCertificatePhotosT(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveCertificatePhotosT(body);
        setSubscribe(observable, observer);
    }

    public void saveCvBaseInfoT(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveCvBaseInfoT(body);
        setSubscribe(observable, observer);
    }

    public void saveEducationExperienceT(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveEducationExperienceT(body);
        setSubscribe(observable, observer);
    }

    public void saveLifePhotosT(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveLifePhotosT(body);
        setSubscribe(observable, observer);
    }


    public void saveTeachVideo(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveTeachVideo(body);
        setSubscribe(observable, observer);
    }

    public void saveWorkExperienceT(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveWorkExperienceT(body);
        setSubscribe(observable, observer);
    }

    public void saveWorkHopeT(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveWorkHopeT(body);
        setSubscribe(observable, observer);
    }

    public void saveWorkStatusT(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveWorkStatusT(body);
        setSubscribe(observable, observer);
    }

    public void getPositionTypeList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getPositionTypeList(body);
        setSubscribe(observable, observer);
    }
    public void getIsFinishInfo(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getIsFinishInfo(body);
        setSubscribe(observable, observer);
    }
    public void cancelPositionLike(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.cancelPositionLike(body);
        setSubscribe(observable, observer);
    }
    public void cancelStoreLike(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.cancelStoreLike(body);
        setSubscribe(observable, observer);
    }
    public void getTrainerPositionDetail(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getTrainerPositionDetail(body);
        setSubscribe(observable, observer);
    }
    public void getPositionCommList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getPositionCommList(body);
        setSubscribe(observable, observer);
    }
    public void getPositionLikeList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getPositionLikeList(body);
        setSubscribe(observable, observer);
    }
    public void getStoreDetail(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getStoreDetail(body);
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
    public void getStorePositionList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getStorePositionList(body);
        setSubscribe(observable, observer);
    }
    public void savePositionLike(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.savePositionLike(body);
        setSubscribe(observable, observer);
    }
    public void saveStoreInfoLike(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.saveStoreInfoLike(body);
        setSubscribe(observable, observer);
    }
    public void searchPosition(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.searchPosition(body);
        setSubscribe(observable, observer);
    }
    public void getStoreLikeList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getStoreLikeList(body);
        setSubscribe(observable, observer);
    }
    public void tipOff(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.tipOff(body);
        setSubscribe(observable, observer);
    }
    public void getInterViewTrainer(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getInterViewTrainer(body);
        setSubscribe(observable, observer);
    }
    public void getInterViewStore(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getInterViewStore(body);
        setSubscribe(observable, observer);
    }

    public void getInterViewDetail(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getInterViewDetail(body);
        setSubscribe(observable, observer);
    }

    public void getInterViewInfo(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getInterViewInfo(body);
        setSubscribe(observable, observer);
    }

    public void getInterViewCancel(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getInterViewCancel(body);
        setSubscribe(observable, observer);
    }

    public void getInterViewStatus(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.getInterViewStatus(body);
        setSubscribe(observable, observer);
    }

    public void sendInterView(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.sendInterView(body);
        setSubscribe(observable, observer);
    }
    public void getRecommendPic(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestRecommendPic(body);
        setSubscribe(observable, observer);
    }
    public void getCategoryList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCategoryList(body);
        setSubscribe(observable, observer);
    }
    public void getHomeShoppingData(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestHomeShoppingData(body);
        setSubscribe(observable, observer);
    }

    public void getMineDynamicList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestMineDynamicList(body);
        setSubscribe(observable, observer);
    }
    public void getMineFansList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestMineFensList(body);
        setSubscribe(observable, observer);
    }
    public void getMineFollowList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestMineFollowList(body);
        setSubscribe(observable, observer);
    }

    /**
     * 收藏
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
     * @param params
     * @param observer
     */
    public void cancelFollow(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestCancelFollow(body);
        setSubscribe(observable, observer);
    }

    /**
     * 我的学习列表
     * @param params
     * @param observer
     */
    public void getLearnList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestLearnList(body);
        setSubscribe(observable, observer);
    }


    /**
     * 获取邀请码
     * @param params
     * @param observer
     */
    public void getInvitationCode(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestInvitationCode(body);
        setSubscribe(observable, observer);
    }

    /**
     * 获取邀请列表
     * @param params
     * @param observer
     */
    public void getInvitationList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestInvitationList(body);
        setSubscribe(observable, observer);
    }
    /**
     * 获取邀请列表
     * @param params
     * @param observer
     */
    public void getSignUpList(Map params, Observer observer) {
        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestSignUpList(body);
        setSubscribe(observable, observer);
    }


  public void getShoppingNum(Map params, Observer observer) {
//        RequestBody body = GenJsonParamRequestBody(params);
        Observable observable = service.requestShoppingNum();
        setSubscribe(observable, observer);
    }


}
