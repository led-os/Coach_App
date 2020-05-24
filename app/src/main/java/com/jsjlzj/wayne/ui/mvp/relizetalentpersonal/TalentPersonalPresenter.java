package com.jsjlzj.wayne.ui.mvp.relizetalentpersonal;

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
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseModel;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BasePresenter;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SelectImageUtils;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


public class TalentPersonalPresenter extends BasePresenter<TalentPersonalView> {
    private static final int REQ_CODE_1 = 1;
    private static final int REQ_CODE_2 = 2;
    private static final int REQ_CODE_3 = 3;
    private static final int REQ_CODE_4 = 4;
    private static final int REQ_CODE_5 = 5;
    private static final int REQ_CODE_6 = 6;
    private static final int REQ_CODE_7 = 7;
    private static final int REQ_CODE_8 = 8;
    private static final int REQ_CODE_9 = 9;
    private static final int REQ_CODE_10 = 10;
    private static final int REQ_CODE_11 = 11;
    private static final int REQ_CODE_12 = 12;
    private static final int REQ_CODE_13 = 13;
    private static final int REQ_CODE_14 = 14;
    private static final int REQ_CODE_15 = 15;
    private static final int REQ_CODE_16 = 16;
    private static final int REQ_CODE_17 = 17;
    private static final int REQ_CODE_18 = 18;
    private static final int REQ_CODE_19 = 19;
    private static final int REQ_CODE_20 = 20;
    private static final int REQ_CODE_21 = 21;
    private static final int REQ_CODE_22 = 22;
    private static final int REQ_CODE_23 = 23;
    private static final int REQ_CODE_24 = 24;
    private static final int REQ_CODE_25 = 25;
    private static final int REQ_CODE_26 = 26;
    private static final int REQ_CODE_27 = 27;
    private static final int REQ_CODE_28 = 28;
    private static final int REQ_CODE_29 = 29;
    private static final int REQ_CODE_30 = 30;
    private static final int REQ_CODE_31 = 31;
    private static final int REQ_CODE_32 = 32;
    private static final int REQ_CODE_33 = 33;
    private static final int REQ_CODE_34 = 34;
    private static final int REQ_CODE_35 = 35;
    private static final int REQ_CODE_131 = 131;
    private static final int REQ_CODE_132 = 132;
    private static final int REQ_CODE_133 = 133;
    private static final int REQ_CODE_134 = 134;
    private static final int REQ_CODE_135 = 135;


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
    public void upload(String path) {
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
     * @param param 查询门店个人信息
     */
    public void selectStoreUserInfo(Map param) {
        if (model != null) {
            view.showLoading();
            model.selectStoreUserInfo(REQ_CODE_22, param, this);
        }
    }

    /**
     * @param param 保存搜索地址
     */
    public void saveAddressSearch(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveAddressSearch(REQ_CODE_24, param, this);
        }
    }

    /**
     * @param param 查询地址历史搜索
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


    public void getRecommendPic() {
        if (model != null) {
            view.showLoading();
            model.getRecommendPic(REQ_CODE_131, null, this);
        }
    }

    public void getCategoryList() {
        if (model != null) {
            view.showLoading();
            model.getCategoryList(REQ_CODE_132, null, this);
        }
    }

    public void getHomeShoppingData() {
        if (model != null) {
            view.showLoading();
            model.getHomeShoppingData(REQ_CODE_133, null, this);
        }
    }

    public void getSearchProductList(Map param) {
        if (model != null) {
//            view.showLoading();
            model.getSearchProductList(REQ_CODE_134, param, this);
        }
    }

    public void getShoppingNum() {
        if (model != null) {
//            view.showLoading();
            model.getShoppingNum(REQ_CODE_135, null, this);
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
            case REQ_CODE_131:
                view.getAllBannerSuccess(resp);
                break;
            case REQ_CODE_132:
                view.getCategoryListSuccess(resp);
                break;
            case REQ_CODE_133:
                view.getHomeShoppingDataSuccess(resp);
                break;
            case REQ_CODE_134:
                view.getCategoryTypeListSuccess(resp);
                break;
            case REQ_CODE_135:
                view.getShoppingNumSuccess(resp);
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
