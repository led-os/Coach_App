package com.jsjlzj.wayne.ui.mvp.relizetalentpersonal;

import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseModel;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BasePresenter;

import java.util.Map;


public class TalentPersonalPresenter extends BasePresenter<TalentPersonalView> {
    private static final int REQ_CODE_1= 1;
    private static final int REQ_CODE_2= 2;
    private static final int REQ_CODE_3= 3;
    private static final int REQ_CODE_4= 4;
    private static final int REQ_CODE_5= 5;
    private static final int REQ_CODE_6= 6;
    private static final int REQ_CODE_7= 7;
    private static final int REQ_CODE_8= 8;
    private static final int REQ_CODE_9= 9;
    private static final int REQ_CODE_10= 10;
    private static final int REQ_CODE_11= 11;
    private static final int REQ_CODE_12= 12;
    private static final int REQ_CODE_13= 13;
    private static final int REQ_CODE_14= 14;
    private static final int REQ_CODE_15= 15;
    private static final int REQ_CODE_16= 16;
    private static final int REQ_CODE_17= 17;
    private static final int REQ_CODE_18= 18;
    private static final int REQ_CODE_19= 19;
    private static final int REQ_CODE_20= 20;
    private static final int REQ_CODE_21= 21;
    private static final int REQ_CODE_22= 22;
    private static final int REQ_CODE_23= 23;
    private static final int REQ_CODE_24= 24;
    private static final int REQ_CODE_25= 25;
    private static final int REQ_CODE_26= 26;
    private static final int REQ_CODE_27= 27;
    private static final int REQ_CODE_28= 28;
    private static final int REQ_CODE_29= 29;
    private static final int REQ_CODE_30= 30;
    private static final int REQ_CODE_31= 31;
    private static final int REQ_CODE_32= 32;
    private static final int REQ_CODE_33= 33;
    private static final int REQ_CODE_34= 34;
    private static final int REQ_CODE_35= 35;



    private TalentPersonalModel model;

    public TalentPersonalPresenter(TalentPersonalView view) {
        this.view = view;
        this.model = new TalentPersonalModel();
    }
//    public void upload(@EnumUploadPic.PicType String type, String path) {
//        if (model != null) {
//            view.showLoading();
//            model.upload(REQ_CODE_21, type, path, this);
//        }
//    }
    public void upload( String path) {
        if (model != null) {
            view.showLoading();
            model.upload(REQ_CODE_21, path, this);
        }
    }
    /**
     * 门店 用户
     */
    public void switchIdentity(Map param) {
        if (model != null) {
            view.showLoading();
            model.switchIdentity(REQ_CODE_18, param, this);
        }
    }
    public void getAll(Map param) {
        if (model != null) {
            view.showLoading();
            model.getAll(REQ_CODE_33, param, this);
        }
    }

    public void getStoreDetail(Map param) {
        if (model != null) {
            view.showLoading();
            model.getStoreDetail(REQ_CODE_27, param, this);
        }
    }
    /**
     * @param param 保存门店认证信息
     */
    public void saveStoreAuth(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveStoreAuth(REQ_CODE_19, param, this);
        }
    }

    /**
     * @param param 保存门店个人信息
     */
    public void saveStoreUserInfo(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveStoreUserInfo(REQ_CODE_20, param, this);
        }
    }

    /**
     * @param param  查询门店个人信息
     */
    public void selectStoreUserInfo(Map param) {
        if (model != null) {
            view.showLoading();
            model.selectStoreUserInfo(REQ_CODE_22, param, this);
        }
    }

    /**
     * @param param  保存搜索地址
     */
    public void saveAddressSearch(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveAddressSearch(REQ_CODE_24, param, this);
        }
    }

    /**
     * @param param  查询地址历史搜索
     */
    public void getAddressSearch(Map param) {
        if (model != null) {
            view.showLoading();
            model.getAddressSearch(REQ_CODE_25, param, this);
        }
    }

    /**
     * 门店  门店模块
     */
    //获取俱乐部信息
    public void getStoreInfo(Map param) {
        if (model != null) {
            view.showLoading();
            model.getStoreInfo(REQ_CODE_1, param, this);
        }
    }
    //默认的公司福利列表
    public void getSystemCompanyBenefits(Map param) {
        if (model != null) {
            view.showLoading();
            model.getSystemCompanyBenefits(REQ_CODE_2, param, this);
        }
    }
    //保存品牌logo
    public void saveBrandLogo(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveBrandLogo(REQ_CODE_3, param, this);
        }
    }

    //查询个人信息
    public void getMyInfo(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveMyInfo(REQ_CODE_23, param, this);
        }
    }
    //保存公司福利
    public void saveCompanyBenefits(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveCompanyBenefits(REQ_CODE_4, param, this);
        }
    }
    //保存俱乐部照片
    public void saveCompanyImage(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveCompanyImage(REQ_CODE_5, param, this);
        }
    }
    //保存俱乐部介绍
    public void saveCompanyProfile(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveCompanyProfile(REQ_CODE_6, param, this);
        }
    }
    //保存俱乐部地址
    public void saveStoreAddress(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveStoreAddress(REQ_CODE_7, param, this);
        }
    }
    //保存上班时间
    public void saveWorkTime(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveWorkTime(REQ_CODE_8, param, this);
        }
    }



//问题反馈
    public void questionBack(Map param) {
        if (model != null) {
            view.showLoading();
            model.questionBack(REQ_CODE_26, param, this);
        }
    }
    public void tipOff(Map param) {
        if (model != null) {
            view.showLoading();
            model.tipOff(REQ_CODE_26, param, this);
        }
    }
    /**
     * 门店  职位模块
     */
    //更改职位状态(关闭或发布)
    public void changePositionStatus(Map param) {
        if (model != null) {
            view.showLoading();
            model.changePositionStatus(REQ_CODE_9, param, this);
        }
    }
    //获取职位详细信息
    public void getPositionDetail(Map param) {
        if (model != null) {
            view.showLoading();
            model.getPositionDetail(REQ_CODE_10, param, this);
        }
    }
    //获取招聘类型和职位类型
    public void getPositionType(Map param) {
        if (model != null) {
            view.showLoading();
            model.getPositionType(REQ_CODE_11, param, this);
        }
    }
    //已发布的职位类型列表
    public void getPublishPositionTypeList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getPublishPositionTypeList(REQ_CODE_12, param, this);
        }
    }
    //获取平台默认的技能要求
    public void getSystemSkillRequired(Map param) {
        if (model != null) {
            view.showLoading();
            model.getSystemSkillRequired(REQ_CODE_13, param, this);
        }
    }
    //根据状态查询职位列表
    public void queryByStatus(Map param) {
        if (model != null) {
            view.showLoading();
            model.queryByStatus(REQ_CODE_14, param, this);
        }
    }
    //保存职位信息
    public void savePosition(Map param) {
        if (model != null) {
            view.showLoading();
            model.savePosition(REQ_CODE_15, param, this);
        }
    }
    /**
     * 门店  简历模块
     */

    //搜索达人简历
    public void searchCV(Map param) {
        if (model != null) {
            view.showLoading();
            model.searchCV(REQ_CODE_17, param, this);
        }
    }

    //沟通过达人列表
    public void getCommunitList(Map param) {
        if (model != null) {
            view.showLoading();
            model.commCV(REQ_CODE_28, param, this);
        }
    }
    //收藏俱乐部
    public void saveStoreInfoLike(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveStoreInfoLike(REQ_CODE_34, param, this);
        }
    }
    //取消收藏俱乐部
    public void cancelStoreLike(Map param) {
        if (model != null) {
            view.showLoading();
            model.cancelStoreLike(REQ_CODE_35, param, this);
        }
    }


    //收藏达人列表
    public void getCVLike(Map param) {
        if (model != null) {
            view.showLoading();
            model.getCVLike(REQ_CODE_29, param, this);
        }
    }

    //保存达人沟通记录
    public void saveCVCommunit(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveCommCV(REQ_CODE_32, param, this);
        }
    }






    @Override
    protected BaseModel getMode() {
        return model;
    }

    @Override
    protected void responseSuccess(int code, MdlBaseHttpResp resp) {
        view.hideLoading();
        switch (code) {
            case REQ_CODE_1:
                view.showStoreInfo(resp);
                break;
            case REQ_CODE_2:
                view.showBenefits(resp);
                break;
            case REQ_CODE_3:
                view.showSaveBrandLogo(resp);
                break;
            case REQ_CODE_4:
                view.showSaveCompanyBenefits(resp);
                break;
            case REQ_CODE_5:
                view.showSaveCompanyImage(resp);
                break;
            case REQ_CODE_6:
                view.showSaveCompanyProfile(resp);
                break;
            case REQ_CODE_7:
                view.showSaveStoreAddress(resp);
                break;
            case REQ_CODE_8:
                view.showSaveWorkTime(resp);
                break;
            case REQ_CODE_9:
                view.showChangePositionStatus(resp);
                break;
            case REQ_CODE_10:
                view.showPositionDetail(resp);
                break;
            case REQ_CODE_11:
                view.showPositionType(resp);
                break;
            case REQ_CODE_12:
                view.showPositionTypePublished(resp);
                break;
            case REQ_CODE_13:
                view.showSkillRequired(resp);
                break;
            case REQ_CODE_14:
                view.showPositionList(resp);
                break;
            case REQ_CODE_15:
                view.showPositionSave(resp);
                break;
            case REQ_CODE_17:
                view.showCV(resp);
                break;
            case REQ_CODE_18:
                view.showSwitchIdentity(resp);
                break;
            case REQ_CODE_19:
                view.showSaveStoreAuth(resp);
                break;
            case REQ_CODE_20:
                view.showSaveStoreUserInfo(resp);
                break;
            case REQ_CODE_21:
                view.showUpload(resp);
                break;
            case REQ_CODE_22:
                view.showSelectStoreUserInfo(resp);
                break;
            case REQ_CODE_23:
                view.showGetMyInfo(resp);
                break;
            case REQ_CODE_24:
                view.showResultsaveAddress(resp);
                break;
            case REQ_CODE_25:
                view.showResultgetAddress(resp);
                break;
            case REQ_CODE_26:
                view.questionBack(resp);
                break;
            case REQ_CODE_27:
                view.showResultStoreDetail(resp);
                break;
            case REQ_CODE_28:
                view.showCVCommunit(resp);
                break;
            case REQ_CODE_29:
                view.showCVLike(resp);
                break;

            case REQ_CODE_32:
                view.showCVSaveCommunit(resp);
                break;
            case REQ_CODE_33:
                view.showResultgetAll(resp);
                break;
            case REQ_CODE_34:
                view.showStorePositionList(resp);
                break;
            case REQ_CODE_35:
                view.showCancelPositionStoreList(resp);
                break;
        }
    }
}
