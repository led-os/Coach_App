package com.jsjlzj.wayne.ui.mvp.relizetalent;


import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.Login.MdlQuestion;
import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.ShoppingNumBean;
import com.jsjlzj.wayne.entity.store.MdlCV;
import com.jsjlzj.wayne.entity.store.MdlDict;
import com.jsjlzj.wayne.entity.store.MdlInfo;
import com.jsjlzj.wayne.entity.store.MdlInterView;
import com.jsjlzj.wayne.entity.store.MdlInterViewDetail;
import com.jsjlzj.wayne.entity.store.MdlPositionDetail;
import com.jsjlzj.wayne.entity.store.MdlPositionList;
import com.jsjlzj.wayne.entity.store.MdlPositionType;
import com.jsjlzj.wayne.entity.store.home.VideoPageBean;
import com.jsjlzj.wayne.entity.store.search.ChannelPageBean;
import com.jsjlzj.wayne.entity.trainer.BannerAll;
import com.jsjlzj.wayne.entity.trainer.InvitationBean;
import com.jsjlzj.wayne.entity.trainer.InvitationCodeBean;
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;
import com.jsjlzj.wayne.entity.trainer.MdlWorkStatus;
import com.jsjlzj.wayne.entity.trainer.MdlsaveAdvantage;
import com.jsjlzj.wayne.entity.trainer.MineStudyBean;
import com.jsjlzj.wayne.entity.trainer.SignUpPageBean;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseView;


public interface TalentTabFragmentView extends BaseView {


    default void showResult(MdlBaseHttpResp<MdlQuestion> resp){}

    /**
     * 教练端-简历模块
     * 接口:创建达人简历,简历详情,职位类型列表,达人-我的
     */
    default void deleteEducationExperienceT(MdlBaseHttpResp<MdlsaveAdvantage> resp) {}
    default void deleteWorkExperienceT(MdlBaseHttpResp<MdlsaveAdvantage> resp) {}
    default void deleteWorkHopeT(MdlBaseHttpResp<MdlsaveAdvantage> resp) {}
    default void getDetailT(MdlBaseHttpResp<MdlDetailT> resp) {}
    default void getPositionTypeT(MdlBaseHttpResp<MdlsaveAdvantage> resp) {}
    default void getWorkHopeListT(MdlBaseHttpResp<MdlsaveAdvantage> resp) {}
    default void myselfT(MdlBaseHttpResp<MdlInfo> resp) {}
    default void saveAdvantage(MdlBaseHttpResp<MdlsaveAdvantage> resp) {}
    default void saveCertificatePhotosT(MdlBaseHttpResp<MdlsaveAdvantage> resp) {}
    default void saveCvBaseInfoT(MdlBaseHttpResp<MdlsaveAdvantage> resp) {}
    default void saveEducationExperienceT(MdlBaseHttpResp<MdlsaveAdvantage> resp) {}
    default void saveLifePhotosT(MdlBaseHttpResp resp) {}
    default void saveWorkExperienceT(MdlBaseHttpResp<MdlsaveAdvantage> resp) {}
    default void saveWorkHopeT(MdlBaseHttpResp<MdlsaveAdvantage> resp) {}
    default void saveWorkStatusT(MdlBaseHttpResp<MdlWorkStatus> resp) {}
    default void getPositionTypeList(MdlBaseHttpResp<MdlPositionType> resp) {}
    default void showCancelPositionLike(MdlBaseHttpResp resp) {}
    default void showPositionDetail(MdlBaseHttpResp<MdlPositionDetail> resp) {}
    default void showPositionCommList(MdlBaseHttpResp<MdlPositionList> resp) {}
    default void showPositionLikeList(MdlBaseHttpResp<MdlPositionList> resp) {}
    default void showStoreDetail(MdlBaseHttpResp<MdlsaveAdvantage> resp) {}
    default void showPostionLike(MdlBaseHttpResp<MdlPositionDetail> resp) {}
    default void showSearch(MdlBaseHttpResp<MdlPositionList> resp) {}
    default void showStoreInfoLikePage(MdlBaseHttpResp<MdlCV> resp) {}
    //收藏达人
    default void showCVSaveLike(MdlBaseHttpResp resp) {
    }

    //取消收藏达人
    default void showCVCancelLike(MdlBaseHttpResp resp) {
    }
    //达人简历详情
    default void showDetailCV(MdlBaseHttpResp<MdlDetailT> resp) {
    }
    //上传文件
    default void showUpload(MdlBaseHttpResp<MdlUpload> resp) {
    }
    //获取数据字典
    default void showResultgetAll(MdlBaseHttpResp<MdlDict> resp){}
    default void showResultgetInterViewStore(MdlBaseHttpResp<MdlInterView> resp){}
    default void showResultgetInterViewTrainer(MdlBaseHttpResp<MdlInterView> resp){}
    default void showResultgetInterViewDetail(MdlBaseHttpResp<MdlInterViewDetail> resp){}
    default void showResultgetInterViewInfo(MdlBaseHttpResp<MdlInterViewDetail> resp){}
    default void showResultgetInterViewCancel(MdlBaseHttpResp resp){}
    default void showResultgetInterViewStatus(MdlBaseHttpResp resp){}
    default void showResultSendInterView(MdlBaseHttpResp resp){}
    default void getAllBannerSuccess(MdlBaseHttpResp<BannerAll> resp){}
    default void getMineDynamicSuccess(MdlBaseHttpResp<VideoPageBean> resp){}

    default void getFensListSuccess(MdlBaseHttpResp<ChannelPageBean> resp){}
    default void getMessageSuccess(MdlBaseHttpResp<DataBean> resp){}
    default void getStudyListSuccess(MdlBaseHttpResp<MineStudyBean> resp){}

    default void selectPhoto(int pos){}
    default void onUploadSuccess(String filePath,int currPos){}
    default void onChangeSucceed(){}
    default void onChangeFailed(String filePath){}
    default void getInvitationSuccess(MdlBaseHttpResp<InvitationCodeBean> resp){}

    default void getInvitationListSuccess(MdlBaseHttpResp<InvitationBean> resp){}

    default void getSignUpListSuccess(MdlBaseHttpResp<SignUpPageBean> resp){}

    default void getShoppingNumSuccess(MdlBaseHttpResp<ShoppingNumBean> resp){}

    default void getIsFinishInfoSuccess(MdlBaseHttpResp<DataBean> resp){}
}
