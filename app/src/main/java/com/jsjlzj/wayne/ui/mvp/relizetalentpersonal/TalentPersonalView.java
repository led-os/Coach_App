package com.jsjlzj.wayne.ui.mvp.relizetalentpersonal;


import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.FindCategoryBean;
import com.jsjlzj.wayne.entity.shopping.HomeShoppingDataBean;
import com.jsjlzj.wayne.entity.shopping.ShoppingPageBean;
import com.jsjlzj.wayne.entity.store.MdlBenefits;
import com.jsjlzj.wayne.entity.store.MdlCV;
import com.jsjlzj.wayne.entity.store.MdlDict;
import com.jsjlzj.wayne.entity.store.MdlInfo;
import com.jsjlzj.wayne.entity.store.MdlPosition;
import com.jsjlzj.wayne.entity.store.MdlPositionDetail;
import com.jsjlzj.wayne.entity.store.MdlPositionType;
import com.jsjlzj.wayne.entity.store.MdlSkillRequired;
import com.jsjlzj.wayne.entity.store.MdlStoreInfo;
import com.jsjlzj.wayne.entity.trainer.BannerAll;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseView;


public interface TalentPersonalView extends BaseView {
    //上传文件
    default void showUpload(MdlBaseHttpResp<MdlUpload> resp) {
    }
    /**
     * 门店  用户
     */
    //切换身份
    default void showSwitchIdentity(MdlBaseHttpResp<MdlUser> resp) {
    }
    //保存门店认证信息
    default void showSaveStoreAuth(MdlBaseHttpResp<MdlStoreInfo> resp) {
    }
    //保存门店用户个人信息
    default void showSaveStoreUserInfo(MdlBaseHttpResp<MdlUser> resp) {
    }

    //查询门店用户个人信息
    default void showSelectStoreUserInfo(MdlBaseHttpResp<MdlUser> resp) {
    }
    //查询门店用户个人信息
    default void showGetMyInfo(MdlBaseHttpResp<MdlInfo> resp) {
    }


    //反馈问题
    default void questionBack(MdlBaseHttpResp<MdlInfo> resp) {
    }
    /**
     * 门店  门店模块
     */
    //获取俱乐部信息
    default void showStoreInfo(MdlBaseHttpResp<MdlStoreInfo> resp) {
    }
    //默认的公司福利列表
    default void showBenefits(MdlBaseHttpResp<MdlBenefits> resp) {
    }
    //保存品牌logo
    default void showSaveBrandLogo(MdlBaseHttpResp<MdlStoreInfo> resp) {
    }
    //保存公司福利
    default void showSaveCompanyBenefits(MdlBaseHttpResp<MdlStoreInfo> resp) {
    }
    //保存俱乐部照片
    default void showSaveCompanyImage(MdlBaseHttpResp<MdlStoreInfo> resp) {
    }
    //保存俱乐部介绍
    default void showSaveCompanyProfile(MdlBaseHttpResp<MdlStoreInfo> resp) {
    }
    //保存俱乐部地址
    default void showSaveStoreAddress(MdlBaseHttpResp<MdlStoreInfo> resp) {
    }
    //保存上班时间
    default void showSaveWorkTime(MdlBaseHttpResp<MdlStoreInfo> resp) {
    }
    /**
     * 门店  职位模块
     */
    //更改职位状态(关闭或发布)
    //
    default void showChangePositionStatus(MdlBaseHttpResp resp) {
    }
    //获取职位详细信息
    default void showPositionDetail(MdlBaseHttpResp<MdlPositionDetail> resp) {
    }
    //获取招聘类型和职位类型
    default void showPositionType(MdlBaseHttpResp<MdlPositionType> resp) {
    }
    //已发布的职位类型列表
    default void showPositionTypePublished(MdlBaseHttpResp<MdlPositionType> resp) {
    }
    //获取平台默认的技能要求
    default void showSkillRequired(MdlBaseHttpResp<MdlSkillRequired> resp) {
    }
    //根据状态查询职位列表
    default void showPositionList(MdlBaseHttpResp<MdlCV> resp) {
    }
    //保存职位信息
    default void showPositionSave(MdlBaseHttpResp<MdlPosition> resp) {
    }

    /**
     * 门店  简历模块
     */



    //搜索达人简历
    default void showCV(MdlBaseHttpResp<MdlCV> resp) {
    }

    //沟通过的达人
    default void showCVCommunit(MdlBaseHttpResp<MdlCV> resp) {
    }

    //收藏的达人
    default void showCVLike(MdlBaseHttpResp<MdlCV> resp) {
    }

    //保存达人沟通记录
    default void showCVSaveCommunit(MdlBaseHttpResp<MdlCV> resp) {
    }

    default void getAllBannerSuccess(MdlBaseHttpResp<BannerAll> resp){}

    //获取优选列表成功
    default void getCategoryListSuccess(MdlBaseHttpResp<FindCategoryBean> resp){}

    //获取首页商城列表成功
    default void getHomeShoppingDataSuccess(MdlBaseHttpResp<HomeShoppingDataBean> resp){}


    //获取首页商城分类列表成功
    default void getCategoryTypeListSuccess(MdlBaseHttpResp<ShoppingPageBean> resp){}


    //获取数据字典
    default void showResultgetAll(MdlBaseHttpResp<MdlDict> resp){}

    //获取搜索地址列表
    default void showResultgetAddress(MdlBaseHttpResp<MdlDict> resp){}

    //保存地址搜索
    default void showResultsaveAddress(MdlBaseHttpResp<MdlDict> resp){}

    //获取公司信息
    default void showResultStoreDetail(MdlBaseHttpResp<MdlStoreInfo> resp){}

    //收藏俱乐部
    default void showStorePositionList(MdlBaseHttpResp resp) {}

    //取消收藏俱乐部
    default void showCancelPositionStoreList(MdlBaseHttpResp resp) {}


    default void selectPhoto(int position){}

    default void onUploadSuccess(String imgUrl,int position){}

    default void uploadToServerSuccess(String imgUrl){}
}
