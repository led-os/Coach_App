package com.jsjlzj.wayne.data.api;


import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.CashOutPageBean;
import com.jsjlzj.wayne.entity.find.CurrencyBean;
import com.jsjlzj.wayne.entity.find.CurrencyDetailPageBean;
import com.jsjlzj.wayne.entity.find.FindCategoryBean;
import com.jsjlzj.wayne.entity.find.FindLessonPageBean;
import com.jsjlzj.wayne.entity.find.JiFenPageBean;
import com.jsjlzj.wayne.entity.find.MineProfitBean;
import com.jsjlzj.wayne.entity.find.OptimizationData1Bean;
import com.jsjlzj.wayne.entity.find.OptimizationData2Bean;
import com.jsjlzj.wayne.entity.shopping.HomeShoppingDataBean;
import com.jsjlzj.wayne.entity.shopping.LocationListBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingCarBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingPageBean;
import com.jsjlzj.wayne.entity.store.MdlBenefits;
import com.jsjlzj.wayne.entity.store.MdlCV;
import com.jsjlzj.wayne.entity.store.MdlInfo;
import com.jsjlzj.wayne.entity.store.MdlInterView;
import com.jsjlzj.wayne.entity.store.MdlInterViewDetail;
import com.jsjlzj.wayne.entity.store.MdlPosition;
import com.jsjlzj.wayne.entity.store.MdlPositionDetail;
import com.jsjlzj.wayne.entity.store.MdlPositionList;
import com.jsjlzj.wayne.entity.store.MdlPositionType;
import com.jsjlzj.wayne.entity.store.MdlSkillRequired;
import com.jsjlzj.wayne.entity.store.MdlStoreInfo;
import com.jsjlzj.wayne.entity.store.home.AmoySchoolBean;
import com.jsjlzj.wayne.entity.store.home.CategoryListBean;
import com.jsjlzj.wayne.entity.store.home.CategoryPageBean;
import com.jsjlzj.wayne.entity.store.home.RecommendBean;
import com.jsjlzj.wayne.entity.store.home.VideoPageBean;
import com.jsjlzj.wayne.entity.store.learn.AnswerRecordBean;
import com.jsjlzj.wayne.entity.store.learn.ChapterListBean;
import com.jsjlzj.wayne.entity.store.learn.ChapterSubjectListBean;
import com.jsjlzj.wayne.entity.store.learn.DoneChapterBean;
import com.jsjlzj.wayne.entity.store.learn.ExamSubjectListBean;
import com.jsjlzj.wayne.entity.store.learn.LearnBean;
import com.jsjlzj.wayne.entity.store.search.ChannelPageBean;
import com.jsjlzj.wayne.entity.store.search.SearchBean;
import com.jsjlzj.wayne.entity.trainer.BannerAll;
import com.jsjlzj.wayne.entity.trainer.InvitationBean;
import com.jsjlzj.wayne.entity.trainer.InvitationCodeBean;
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;
import com.jsjlzj.wayne.entity.trainer.MdlWorkStatus;
import com.jsjlzj.wayne.entity.trainer.MdlsaveAdvantage;
import com.jsjlzj.wayne.entity.trainer.MineStudyBean;
import com.jsjlzj.wayne.entity.trainer.SignUpPageBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Administrator on 2018/6/11.
 */

public interface StoreService {
    /**
     * 门店  门店模块
     */
    @POST(HttpConstant.API_GETSTOREINFO)   //获取俱乐部信息
    Observable<MdlBaseHttpResp<MdlStoreInfo>> getStoreInfo(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETSYSTEMCOMPANYBENEFITS)//默认的公司福利列表
    Observable<MdlBaseHttpResp<MdlBenefits>> getSystemCompanyBenefits(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVEBRANDLOGO) //保存品牌logo
    Observable<MdlBaseHttpResp<MdlStoreInfo>> saveBrandLogo(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVECOMPANYBENEFITS)//保存公司福利
    Observable<MdlBaseHttpResp<MdlStoreInfo>> saveCompanyBenefits(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVECOMPANYIMAGE)//保存俱乐部照片
    Observable<MdlBaseHttpResp<MdlStoreInfo>> saveCompanyImage(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVECOMPANYPROFILE)//保存俱乐部介绍
    Observable<MdlBaseHttpResp<MdlStoreInfo>> saveCompanyProfile(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVESTOREADDRESS)    //保存俱乐部地址
    Observable<MdlBaseHttpResp<MdlStoreInfo>> saveStoreAddress(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVEWORKTIME)     //保存上班时间
    Observable<MdlBaseHttpResp<MdlStoreInfo>> saveWorkTime(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETMYINFO)     //查询我的页面
    Observable<MdlBaseHttpResp<MdlInfo>> getMyInfo(@Body RequestBody requestBody);

    /**
     * 门店  职位模块
     */
    @POST(HttpConstant.API_CHANGEPOSITIONSTATUS)//更改职位状态(关闭或发布)
    Observable<MdlBaseHttpResp> changePositionStatus(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETPOSITIONDETAIL)//获取职位详细信息
    Observable<MdlBaseHttpResp<MdlPositionDetail>> getPositionDetail(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETPOSITIONTYPE)//获取招聘类型和职位类型
    Observable<MdlBaseHttpResp<MdlPositionType>> getPositionType(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETPUBLISHPOSITIONTYPELIST)//已发布的职位类型列表
    Observable<MdlBaseHttpResp<MdlPositionType>> getPublishPositionTypeList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETSYSTEMSKILLREQUIRED)//获取平台默认的技能要求
    Observable<MdlBaseHttpResp<MdlSkillRequired>> getSystemSkillRequired(@Body RequestBody requestBody);

    @POST(HttpConstant.API_QUERYBYSTATUS)//根据状态查询职位列表
    Observable<MdlBaseHttpResp<MdlCV>> queryByStatus(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVEPOSITION)//保存职位信息
    Observable<MdlBaseHttpResp<MdlPosition>> savePosition(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVESTOREUSERINFO)//保存门店个人信息
    Observable<MdlBaseHttpResp<MdlUser>> saveStoreUserInfo(@Body RequestBody requestBody);

    @POST(HttpConstant.API_ACCOUNTINFO)//查询门店个人信息
    Observable<MdlBaseHttpResp<MdlUser>> selectStoreUserInfo(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVESTOREAUTH)//保存俱乐部信息
    Observable<MdlBaseHttpResp<MdlStoreInfo>> saveStoreAuth(@Body RequestBody requestBody);

    /**
     * 门店  简历模块
     */

    @POST(HttpConstant.API_DETAILCV)//达人简历详情
    Observable<MdlBaseHttpResp<MdlDetailT>> detailCV(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SEARCHCV)//搜索达人简历
    Observable<MdlBaseHttpResp<MdlCV>> searchCV(@Body RequestBody requestBody);

    @POST(HttpConstant.API_COMMCVC)//沟通过列表
    Observable<MdlBaseHttpResp<MdlCV>> commCV(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETCVCLIKE)//收藏达人列表
    Observable<MdlBaseHttpResp<MdlCV>> getCVLIKE(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVECOMMCVC)//保存达人沟通记录
    Observable<MdlBaseHttpResp<MdlCV>> saveCommCV(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVECVCLIKE)//收藏达人
    Observable<MdlBaseHttpResp> saveCVLike(@Body RequestBody requestBody);

    @POST(HttpConstant.API_CANCELCVC)//取消收藏达人
    Observable<MdlBaseHttpResp> cancelCV(@Body RequestBody requestBody);

    /**
     * 教练端-简历模块
     * 接口:创建达人简历,简历详情,职位类型列表,达人-我的
     */

    @POST(HttpConstant.API_DELETEEDUCATIONEXPERIENCET)//删除教育经历
    Observable<MdlBaseHttpResp<MdlsaveAdvantage>> deleteEducationExperienceT(@Body RequestBody requestBody);

    @POST(HttpConstant.API_DELETEWORKEXPERIENCET)//删除工作经历
    Observable<MdlBaseHttpResp<MdlsaveAdvantage>> deleteWorkExperienceT(@Body RequestBody requestBody);

    @POST(HttpConstant.API_DELETEWORKHOPET)//删除求职期望
    Observable<MdlBaseHttpResp<MdlsaveAdvantage>> deleteWorkHopeT(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETDETAILT)//简历详情
    Observable<MdlBaseHttpResp<MdlDetailT>> getDetailT(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETPOSITIONTYPET)//获取招聘类型和职位类型
    Observable<MdlBaseHttpResp<MdlsaveAdvantage>> getPositionTypeT(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETWORKHOPELISTT)//求职意向
    Observable<MdlBaseHttpResp<MdlsaveAdvantage>> getWorkHopeListT(@Body RequestBody requestBody);

    @POST(HttpConstant.API_MYSELFT)//达人-我的
    Observable<MdlBaseHttpResp<MdlInfo>> myselfT(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVEADBANTAGE)//保存个人优势
    Observable<MdlBaseHttpResp<MdlsaveAdvantage>> saveAdvantage(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVECERTIFICATEPHOTOST)//保存证书照片
    Observable<MdlBaseHttpResp<MdlsaveAdvantage>> saveCertificatePhotosT(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVECVBASEINFOT)//保存简历个人信息
    Observable<MdlBaseHttpResp<MdlsaveAdvantage>> saveCvBaseInfoT(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVEEDUCATIONEXPERIENCET)//保存教育经历
    Observable<MdlBaseHttpResp<MdlsaveAdvantage>> saveEducationExperienceT(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVELIFEPHOTOST)//保存生活照片
    Observable<MdlBaseHttpResp> saveLifePhotosT(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVE_VIDEO_TEACH)//保存教学视频
    Observable<MdlBaseHttpResp> saveTeachVideo(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVEWORKEXPERIENCET)//保存工作经历
    Observable<MdlBaseHttpResp<MdlsaveAdvantage>> saveWorkExperienceT(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVEWORKHOPET)//保存求职期望
    Observable<MdlBaseHttpResp<MdlsaveAdvantage>> saveWorkHopeT(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVEWORKSATUST)//保存求职状态
    Observable<MdlBaseHttpResp<MdlWorkStatus>> saveWorkStatusT(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVESEARCH)//保存地址搜索记录
    Observable<MdlBaseHttpResp<MdlCV>> saveAddressSearch(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETSEARCH)//获取地址搜索记录
    Observable<MdlBaseHttpResp<MdlCV>> getAddressSearch(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETPOSITIONTYPELIST)//已添加的求职期望类型列表
    Observable<MdlBaseHttpResp<MdlPositionType>> getPositionTypeList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_CANCELPOSITIONLIKE)//取消职位收藏
    Observable<MdlBaseHttpResp> cancelPositionLike(@Body RequestBody requestBody);

    @POST(HttpConstant.API_CANCELSTORELIKE)//取消收藏俱乐部
    Observable<MdlBaseHttpResp> cancelStoreLike(@Body RequestBody requestBody);

    @POST(HttpConstant.API_POSITIONDETAIL)//职位详情信息
    Observable<MdlBaseHttpResp<MdlPositionDetail>> getTrainerPositionDetail(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETPOSITIONCOMMLIST)//沟通过的职位列表
    Observable<MdlBaseHttpResp<MdlPositionList>> getPositionCommList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETPOSITIONLIKELIST)//收藏过的职位列表
    Observable<MdlBaseHttpResp<MdlPositionList>> getPositionLikeList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETSTOREDETAIL)//公司详情信息
    Observable<MdlBaseHttpResp<MdlStoreInfo>> getStoreDetail(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETSTOREPOSITIONLIST)//获取公司职位信息
    Observable<MdlBaseHttpResp<MdlCV>> getStorePositionList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVEPOSITIONLIKE)//收藏职位
    Observable<MdlBaseHttpResp<MdlPositionDetail>> savePositionLike(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVESTOREINFOLIKE)//收藏俱乐部
    Observable<MdlBaseHttpResp> saveStoreInfoLike(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SEARCH)//搜索职位信息
    Observable<MdlBaseHttpResp<MdlPositionList>> searchPosition(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GETSTORELIKELIST)//收藏俱乐部列表
    Observable<MdlBaseHttpResp<MdlCV>> getStoreLikeList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_TIPOFF)//职位举报
    Observable<MdlBaseHttpResp> tipOff(@Body RequestBody requestBody);

    @POST(HttpConstant.API_INTERVIEWTRAINER)//面试（教练）
    Observable<MdlBaseHttpResp<MdlInterView>> getInterViewTrainer(@Body RequestBody requestBody);

    @POST(HttpConstant.API_INTERVIEWSTORE)//面试（门店）
    Observable<MdlBaseHttpResp<MdlInterView>> getInterViewStore(@Body RequestBody requestBody);

    @POST(HttpConstant.API_INTERVIEWDETAIL)//面试详情
    Observable<MdlBaseHttpResp<MdlInterViewDetail>> getInterViewDetail(@Body RequestBody requestBody);

    //获取面试邀请界面信息
    @POST(HttpConstant.API_MESSAGE_INTERVIEWINFO)
    Observable<MdlBaseHttpResp<MdlInterViewDetail>> getInterViewInfo(@Body RequestBody requestBody);

    //门店取消面试
    @POST(HttpConstant.API_INTERVIEWCANCEL)
    Observable<MdlBaseHttpResp> getInterViewCancel(@Body RequestBody requestBody);

    //教练修改面试状态
    @POST(HttpConstant.API_INTERVIEWSTATUS)
    Observable<MdlBaseHttpResp> getInterViewStatus(@Body RequestBody requestBody);

    //发送面试邀请
    @POST(HttpConstant.API_INTERVIEWInvite)
    Observable<MdlBaseHttpResp> sendInterView(@Body RequestBody requestBody);


    //获取我的广告图片
    @POST(HttpConstant.API_GET_ALL_PIC)
    Observable<MdlBaseHttpResp<BannerAll>> requestRecommendPic(@Body RequestBody requestBody);


    //获取我的广告图片
    @POST(HttpConstant.API_GET_CATEGORY_LIST)
    Observable<MdlBaseHttpResp<FindCategoryBean>> requestCategoryList(@Body RequestBody requestBody);

    //获取商城首页数据
    @POST(HttpConstant.API_GET_HOME_SHOPPING)
    Observable<MdlBaseHttpResp<HomeShoppingDataBean>> requestHomeShoppingData(@Body RequestBody requestBody);



    /******************************************v2 新接口*****************************************************/

    @POST(HttpConstant.API_HOME_RECOMMEND)
    Observable<MdlBaseHttpResp<RecommendBean>> requestRecommend(@Body RequestBody requestBody);


    @POST(HttpConstant.API_HOME_AMOY_SCHOOL)
    Observable<MdlBaseHttpResp<AmoySchoolBean>> requestAmoySchool(@Body RequestBody requestBody);

    @POST(HttpConstant.API_HOME_MATCH)
    Observable<MdlBaseHttpResp<AmoySchoolBean>> requestMatch(@Body RequestBody requestBody);


    @POST(HttpConstant.API_HOME_AMOY_LIST)
    Observable<MdlBaseHttpResp<CategoryPageBean>> requestAmoyList(@Body RequestBody requestBody);


    @POST(HttpConstant.API_HOME_MATCH_LIST)
    Observable<MdlBaseHttpResp<CategoryPageBean>> requestMatchList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_HOME_DRIED_FOOD)
    Observable<MdlBaseHttpResp<AmoySchoolBean>> requestDriedFood(@Body RequestBody requestBody);


    @POST(HttpConstant.API_HOME_DRIED_FOOD_LIST)
    Observable<MdlBaseHttpResp<VideoPageBean>> requestDriedFoodList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_HOME_INFORMATION)
    Observable<MdlBaseHttpResp<AmoySchoolBean>> requestInformation(@Body RequestBody requestBody);

    @POST(HttpConstant.API_HOME_INFORMATION_LIST)
    Observable<MdlBaseHttpResp<VideoPageBean>> requestInformationList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_HOME_PRODUCT)
    Observable<MdlBaseHttpResp<AmoySchoolBean>> requestProduce(@Body RequestBody requestBody);

    @POST(HttpConstant.API_HOME_PRODUCT_LIST)
    Observable<MdlBaseHttpResp<CategoryPageBean>> requestProductList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_HOME_ALL_CLASSIC)
    Observable<MdlBaseHttpResp<CategoryListBean>> requestAllClassicList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_HOME_ORGANIZATION_LIST)
    Observable<MdlBaseHttpResp<CategoryPageBean>> requestOrganizationList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_HOME_VIDEO_LIST)
    Observable<MdlBaseHttpResp<VideoPageBean>> requestVideoList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_ADD_ZAN)
    Observable<MdlBaseHttpResp<DataBean>> requestClickZan(@Body RequestBody requestBody);

    @POST(HttpConstant.API_CANCEL_ZAN)
    Observable<MdlBaseHttpResp<DataBean>> requestCancelZan(@Body RequestBody requestBody);

    @POST(HttpConstant.API_ADD_COLLECT)
    Observable<MdlBaseHttpResp<DataBean>> requestClickCollect(@Body RequestBody requestBody);

    @POST(HttpConstant.API_CANCEL_COLLECT)
    Observable<MdlBaseHttpResp<DataBean>> requestCancelCollect(@Body RequestBody requestBody);

    @POST(HttpConstant.API_ADD_FOLLOW)
    Observable<MdlBaseHttpResp<DataBean>> requestClickFollow(@Body RequestBody requestBody);

    @POST(HttpConstant.API_CANCEL_FOLLOW)
    Observable<MdlBaseHttpResp<DataBean>> requestCancelFollow(@Body RequestBody requestBody);

    @POST(HttpConstant.API_DELETE_DYNAMIC)
    Observable<MdlBaseHttpResp<DataBean>> requestDeleteDynamic(@Body RequestBody requestBody);

    @POST(HttpConstant.API_LEARN_LIST)
    Observable<MdlBaseHttpResp<MineStudyBean>> requestLearnList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_INVITATION_CODE)
    Observable<MdlBaseHttpResp<InvitationCodeBean>> requestInvitationCode(@Body RequestBody requestBody);

    @POST(HttpConstant.API_INVITATION_LIST)
    Observable<MdlBaseHttpResp<InvitationBean>> requestInvitationList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SIGN_UP_LIST)
    Observable<MdlBaseHttpResp<SignUpPageBean>> requestSignUpList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_LEARN_DATA)
    Observable<MdlBaseHttpResp<LearnBean>> requestLearnData(@Body RequestBody requestBody);

    @POST(HttpConstant.API_CHAPTER_LIST)
    Observable<MdlBaseHttpResp<ChapterListBean>> requestChapterList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_CHAPTER_SUBJECT_LIST)
    Observable<MdlBaseHttpResp<ChapterSubjectListBean>> requestChapterSubjectList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVE_ANSWER_RECORD)
    Observable<MdlBaseHttpResp<String>> requestSaveAnswerRecord(@Body RequestBody requestBody);

    @POST(HttpConstant.API_WRONG_SUBJECT_LIST)
    Observable<MdlBaseHttpResp<ChapterSubjectListBean>> requestWrongSubjectList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_EXAM_SUBJECT_LIST)
    Observable<MdlBaseHttpResp<ExamSubjectListBean>> requestExamSubjectList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SUBJECT_EXAM_ANSWER)
    Observable<MdlBaseHttpResp<DoneChapterBean>> requestSubjectExamAnswer(@Body RequestBody requestBody);

    @POST(HttpConstant.API_DONE_ANSWER)
    Observable<MdlBaseHttpResp<DoneChapterBean>> requestDoneChapterAnswer(@Body RequestBody requestBody);

    @POST(HttpConstant.API_ERROR_RESULT)
    Observable<MdlBaseHttpResp<ExamSubjectListBean>> requestErrorResult(@Body RequestBody requestBody);

    @POST(HttpConstant.API_CURRENT_SUBJECT)
    Observable<MdlBaseHttpResp<ChapterListBean>> requestCurrentSubject(@Body RequestBody requestBody);

    @POST(HttpConstant.API_ANSWER_RECORD)
    Observable<MdlBaseHttpResp<AnswerRecordBean>> requestAnswerRecord(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SEARCH_ALL)
    Observable<MdlBaseHttpResp<SearchBean>> requestSearchData(@Body RequestBody requestBody);

    @POST(HttpConstant.API_DYNAMIC_LIST)
    Observable<MdlBaseHttpResp<VideoPageBean>> requestDynamicList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_MINE_DYNAMIC_LIST)
    Observable<MdlBaseHttpResp<VideoPageBean>> requestMineDynamicList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_CHANNEL_FENSLIST)
    Observable<MdlBaseHttpResp<ChannelPageBean>> requestMineFensList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_CHANNEL_FOLLOW_LIST)
    Observable<MdlBaseHttpResp<ChannelPageBean>> requestMineFollowList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_AMOY_SIGN_UP)
    Observable<MdlBaseHttpResp<DataBean>> requestAmoySignUp(@Body RequestBody requestBody);

    @POST(HttpConstant.API_MATCH_SIGN_UP)
    Observable<MdlBaseHttpResp<DataBean>> requestMatchSignUp(@Body RequestBody requestBody);

    @POST(HttpConstant.API_PUBLIC_DYNAMIC)
    Observable<MdlBaseHttpResp<DataBean>> requestPublicDynamic(@Body RequestBody requestBody);

    @POST(HttpConstant.API_DYNAMIC_COLLECT_LIST)
    Observable<MdlBaseHttpResp<VideoPageBean>> requestDynamicCollectList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_VIDEO_COLLECT_LIST)
    Observable<MdlBaseHttpResp<VideoPageBean>> requestVideoCollectList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_INFORMATION_COLLECT_LIST)
    Observable<MdlBaseHttpResp<VideoPageBean>> requestInformationCollectList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_FIND_OPTIMIZATION_DATA1)
    Observable<MdlBaseHttpResp<OptimizationData1Bean>> requestOptimizationData1(@Body RequestBody requestBody);

    @POST(HttpConstant.API_FIND_OPTIMIZATION_DATA2)
    Observable<MdlBaseHttpResp<OptimizationData2Bean>> requestOptimizationData2(@Body RequestBody requestBody);

    @POST(HttpConstant.API_MINE_CASH_OUT_RECORD)
    Observable<MdlBaseHttpResp<CashOutPageBean>> requestCashOutList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_MINE_PROFIT)
    Observable<MdlBaseHttpResp<MineProfitBean>> requestMineProfit(@Body RequestBody requestBody);

    @POST(HttpConstant.API_RECOMMEND_CATEGORY_MORE)
    Observable<MdlBaseHttpResp<FindLessonPageBean>> requestRecommendCategoryList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_FREE_EXPER_CATEGORY_MORE)
    Observable<MdlBaseHttpResp<FindLessonPageBean>> requestFreeExperCategoryList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_HOT_CATEGORY_MORE)
    Observable<MdlBaseHttpResp<FindLessonPageBean>> requestHotCategoryList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_HOT_LISTENING_CATEGORY_MORE)
    Observable<MdlBaseHttpResp<FindLessonPageBean>> requestHotListeningCategoryList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_JIANZHI_CATEGORY_MORE)
    Observable<MdlBaseHttpResp<FindLessonPageBean>> requestJianzhiCategoryList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_MOTION_CATEGORY_MORE)
    Observable<MdlBaseHttpResp<FindLessonPageBean>> requestMotionCategoryList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_FOUR_LESSON_CATEGORY_MORE)
    Observable<MdlBaseHttpResp<FindLessonPageBean>> requestFourLessonCategoryList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_JIFEN_DETAIL_LIST)
    Observable<MdlBaseHttpResp<JiFenPageBean>> requestJifenList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SEARCH_CATEGORY_MORE)
    Observable<MdlBaseHttpResp<FindLessonPageBean>> requestSearchCategoryList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_LOCATION_MANAGER_LIST)
    Observable<MdlBaseHttpResp<LocationListBean>> requestLocationList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SAVE_LOCATION)
    Observable<MdlBaseHttpResp<DataBean>> requestSaveLocation(@Body RequestBody requestBody);

    @POST(HttpConstant.API_CURRENCY_LIST)
    Observable<MdlBaseHttpResp<CurrencyBean>> requestCurrencyList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_CURRENCY_DETAIL)
    Observable<MdlBaseHttpResp<CurrencyDetailPageBean>> requestCurrencyDetailList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_GROUP_PRODUCT)
    Observable<MdlBaseHttpResp<ShoppingPageBean>> requestGroupProductList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_SHOPPING_CAR_LIST)
    Observable<MdlBaseHttpResp<ShoppingCarBean>> requestShoppingCarList(@Body RequestBody requestBody);

    @Multipart
    @POST(HttpConstant.API_UPLOAD)//上次文件
//    Observable<MdlBaseHttpResp<MdlUpload>> upload(@Part("type") RequestBody type, @Part MultipartBody.Part file);
    Observable<MdlBaseHttpResp<MdlUpload>> upload(@Part MultipartBody.Part file);

    @Multipart
    @POST(HttpConstant.API_UPLOAD_VIDEO)//上次视频文件
    Observable<MdlBaseHttpResp<MdlUpload>> uploadVideo(@Part MultipartBody.Part file);
}
