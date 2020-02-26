package com.jsjlzj.wayne.data.api;


import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
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
import com.jsjlzj.wayne.entity.store.home.CategoryPageBean;
import com.jsjlzj.wayne.entity.store.home.RecommendBean;
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;
import com.jsjlzj.wayne.entity.trainer.MdlWorkStatus;
import com.jsjlzj.wayne.entity.trainer.MdlsaveAdvantage;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

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
    Observable<MdlBaseHttpResp<CategoryPageBean>> requestDriedFoodList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_HOME_INFORMATION)
    Observable<MdlBaseHttpResp<AmoySchoolBean>> requestInformation(@Body RequestBody requestBody);

    @POST(HttpConstant.API_HOME_INFORMATION_LIST)
    Observable<MdlBaseHttpResp<CategoryPageBean>> requestInformationList(@Body RequestBody requestBody);

    @POST(HttpConstant.API_HOME_PRODUCT)
    Observable<MdlBaseHttpResp<AmoySchoolBean>> requestProduce(@Body RequestBody requestBody);

    @POST(HttpConstant.API_HOME_PRODUCT_LIST)
    Observable<MdlBaseHttpResp<CategoryPageBean>> requestProductList(@Body RequestBody requestBody);

}
