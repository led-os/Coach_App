package com.jsjlzj.wayne.ui.mvp.relizetalent;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseModel;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BasePresenter;
import com.jsjlzj.wayne.ui.trainer.personal.MasterCardActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SelectImageUtils;
import com.netease.nim.uikit.common.ToastHelper;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


public class TalentTabFragmentPresenter extends BasePresenter<TalentTabFragmentView> {
    private static final int REQ_CODE_30 = 30;
    private static final int REQ_ALL_DICT = 4;
    private static final int REQ_RESET_UNLOAD = 5;
    private static final int REQ_RESET_UNLOAD_VIDEO = 6;

    private static final int REQ_FAQ = 1;
    private static final int REQ_CODE_31 = 31;

    //up-by
    private static final int REQ_CODE_100 = 100;
    private static final int REQ_CODE_101 = 101;
    private static final int REQ_CODE_102 = 102;
    private static final int REQ_CODE_103 = 103;
    private static final int REQ_CODE_104 = 104;
    private static final int REQ_CODE_105 = 105;
    private static final int REQ_CODE_106 = 106;
    private static final int REQ_CODE_107 = 107;
    private static final int REQ_CODE_108 = 108;
    private static final int REQ_CODE_109 = 109;
    private static final int REQ_CODE_110 = 110;
    private static final int REQ_CODE_111 = 111;
    private static final int REQ_CODE_112 = 112;
    private static final int REQ_CODE_113 = 113;
    private static final int REQ_CODE_114 = 114;
    private static final int REQ_CODE_115 = 115;
    private static final int REQ_CODE_116 = 116;
    private static final int REQ_CODE_117 = 117;
    private static final int REQ_CODE_118 = 118;
    private static final int REQ_CODE_119 = 119;
    private static final int REQ_CODE_120 = 120;
    private static final int REQ_CODE_121 = 121;
    private static final int REQ_CODE_122 = 122;
    private static final int REQ_CODE_123 = 123;
    private static final int REQ_CODE_124 = 124;
    private static final int REQ_CODE_125 = 125;
    private static final int REQ_CODE_126 = 126;
    private static final int REQ_CODE_127 = 127;
    private static final int REQ_CODE_128 = 128;
    private static final int REQ_CODE_129 = 129;
    private static final int REQ_CODE_130 = 130;
    private static final int REQ_CODE_131 = 131;
    private static final int REQ_CODE_132 = 132;
    private static final int REQ_CODE_133 = 133;
    private static final int REQ_CODE_134 = 134;
    private static final int REQ_CODE_CLICK_COLLECT = 1317;
    private static final int REQ_CODE_CANCEL_COLLECT = 1318;
    private static final int REQ_CODE_CLICK_FOLLOW = 1319;
    private static final int REQ_CODE_CANCEL_FOLLOW = 1320;
    private static final int REQ_CODE_LEARN_LIST = 1321;
    private static final int REQ_CODE_INVITATION_CODE = 1322;
    private static final int REQ_CODE_INVITATION_LIST = 1323;
    private static final int REQ_CODE_SIGNUP_LIST = 1324;
    private static final int REQ_CODE_CERTIFICATE_PHOTO = 1325;
    private static final int REQ_CODE_SHOPPING_NUM = 1326;
    private static final int REQ_CODE_IS_FINFISH_INFO = 1327;


    private TalentTabFragmentModel model;

    public TalentTabFragmentPresenter(TalentTabFragmentView view) {
        this.view = view;
        this.model = new TalentTabFragmentModel();
    }

    public void faqList(Map param) {
        if (model != null) {
            view.showLoading();
            model.faqList(REQ_FAQ, param, this);
        }
    }

    public void upload(String path) {
        if (model != null) {
            view.showLoading();
            model.upload(REQ_RESET_UNLOAD, path, this);
        }
    }

    public void uploadVideo(String path) {
        if (model != null) {
            view.showLoading();
            model.uploadVideo(REQ_RESET_UNLOAD_VIDEO, path, this);
        }
    }


    //收藏达人
    public void saveCVLike(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveCVLike(REQ_CODE_30, param, this);
        }
    }

    //取消收藏达人
    public void cancelCVLike(Map param) {
        if (model != null) {
            view.showLoading();
            model.cancelCV(REQ_CODE_31, param, this);
        }
    }

    //达人简历详情
    public void detailCV(Map param) {
        if (model != null) {
            view.showLoading();
            model.detailCV(REQ_CODE_120, param, this);
        }
    }

    /**
     * 教练端-简历模块
     * 接口:创建达人简历,简历详情,职位类型列表,达人-我的
     */
    public void deleteEducationExperienceT(Map param) {
        if (model != null) {
            view.showLoading();
            model.deleteEducationExperienceT(REQ_CODE_100, param, this);
        }
    }

    public void getPositionLikeList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getPositionLikeList(REQ_CODE_118, param, this);
        }
    }

    public void getPositionCommList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getPositionCommList(REQ_CODE_117, param, this);
        }
    }


    public void deleteWorkExperienceT(Map param) {
        if (model != null) {
            view.showLoading();
            model.deleteWorkExperienceT(REQ_CODE_101, param, this);
        }
    }

    public void savePositionLike(Map param) {
        if (model != null) {
            view.showLoading();
            model.savePositionLike(REQ_CODE_122, param, this);
        }
    }


    public void getPositionTypeList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getPositionTypeList(REQ_CODE_115, param, this);
        }
    }


    public void getIsFinishInfo() {
        if (model != null) {
            model.getIsFinishInfo(REQ_CODE_IS_FINFISH_INFO, null, this);
        }
    }


    public void deleteWorkHopeT(Map param) {
        if (model != null) {
            view.showLoading();
            model.deleteWorkHopeT(REQ_CODE_102, param, this);
        }
    }


    public void getDetailT(Map param) {
        if (model != null) {
            view.showLoading();
            model.getDetailT(REQ_CODE_103, param, this);
        }
    }

    public void getPositionTypeT(Map param) {
        if (model != null) {
            view.showLoading();
            model.getPositionTypeT(REQ_CODE_104, param, this);
        }
    }

    public void getWorkHopeListT(Map param) {
        if (model != null) {
            view.showLoading();
            model.getWorkHopeListT(REQ_CODE_105, param, this);
        }
    }

    public void myselfT(Map param) {
        if (model != null) {
            view.showLoading();
            model.myselfT(REQ_CODE_106, param, this);
        }
    }

    public void saveAdvantage(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveAdvantage(REQ_CODE_107, param, this);
        }
    }

    public void saveCertificatePhotosT(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveCertificatePhotosT(REQ_CODE_108, param, this);
        }
    }

    public void saveCvBaseInfoT(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveCvBaseInfoT(REQ_CODE_109, param, this);
        }
    }

    public void saveEducationExperienceT(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveEducationExperienceT(REQ_CODE_110, param, this);
        }
    }

    public void saveLifePhotosT(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveLifePhotosT(REQ_CODE_111, param, this);
        }
    }

    public void saveTeachVideo(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveTeachVideo(REQ_CODE_111, param, this);
        }
    }

    public void saveWorkExperienceT(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveWorkExperienceT(REQ_CODE_112, param, this);
        }
    }

    public void getTrainerPositionDetail(Map param) {
        if (model != null) {
            view.showLoading();
            model.getTrainerPositionDetail(REQ_CODE_119, param, this);
        }
    }


    public void saveWorkHopeT(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveWorkHopeT(REQ_CODE_113, param, this);
        }
    }

    public void saveWorkStatusT(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveWorkStatusT(REQ_CODE_114, param, this);
        }
    }

    public void searchPosition(Map param) {
        if (model != null) {
            view.showLoading();
            model.searchPosition(REQ_CODE_116, param, this);
        }
    }

    public void getAll(Map param) {
        if (model != null) {
            view.showLoading();
            model.getAll(REQ_ALL_DICT, param, this);
        }
    }

    public void getStoreLikeList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getStoreLikeList(REQ_CODE_121, param, this);
        }
    }

    public void cancelPositionLike(Map param) {
        if (model != null) {
            view.showLoading();
            model.cancelPositionLike(REQ_CODE_123, param, this);
        }
    }

    public void getInterViewTrainer(Map param) {
        if (model != null) {
            view.showLoading();
            model.getInterViewTrainer(REQ_CODE_124, param, this);
        }
    }

    public void getInterViewStore(Map param) {
        if (model != null) {
            view.showLoading();
            model.getInterViewStore(REQ_CODE_125, param, this);
        }
    }

    public void getInterViewDetail(Map param) {
        if (model != null) {
            view.showLoading();
            model.getInterViewDetail(REQ_CODE_126, param, this);
        }
    }

    public void getInterViewInfo(Map param) {
        if (model != null) {
            view.showLoading();
            model.getInterViewInfo(REQ_CODE_127, param, this);
        }
    }

    public void getInterViewCancel(Map param) {
        if (model != null) {
            view.showLoading();
            model.getInterViewCancel(REQ_CODE_128, param, this);
        }
    }

    public void getInterViewStatus(Map param) {
        if (model != null) {
            view.showLoading();
            model.getInterViewStatus(REQ_CODE_129, param, this);
        }
    }

    public void sendInterView(Map param) {
        if (model != null) {
            view.showLoading();
            model.sendInterView(REQ_CODE_130, param, this);
        }
    }


    public void getRecommendPic() {
        if (model != null) {
            view.showLoading();
            model.getRecommendPic(REQ_CODE_131, null, this);
        }
    }

    public void getMineDynamicList() {
        if (model != null) {
            view.showLoading();
            model.getMineDynamicList(REQ_CODE_132, null, this);
        }
    }

    public void getMineFansList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getMineFansList(REQ_CODE_133, param, this);
        }
    }

    public void getMineFollowList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getMineFollowList(REQ_CODE_134, param, this);
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

    public void getStudyList() {
        if (model != null) {
            model.getLearnList(REQ_CODE_LEARN_LIST, null, this);
        }
    }


    public void getInvitationCode() {
        if (model != null) {
            view.showLoading();
            model.getInvitationCode(REQ_CODE_INVITATION_CODE, null, this);
        }
    }

    public void getInvitationList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getInvitationList(REQ_CODE_INVITATION_LIST, param, this);
        }
    }


    public void getSignUpList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getSignUpList(REQ_CODE_SIGNUP_LIST, param, this);
        }
    }

    public void getShoppingNum() {
        if (model != null) {
//            view.showLoading();
            model.getShoppingNum(REQ_CODE_SHOPPING_NUM, null, this);
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
            case REQ_ALL_DICT:
                view.showResultgetAll(resp);
                break;
            case REQ_RESET_UNLOAD_VIDEO:
            case REQ_RESET_UNLOAD:
                view.showUpload(resp);
                break;
            case REQ_FAQ:
                view.showResult(resp);
                break;
            case REQ_CODE_100:
                view.deleteEducationExperienceT(resp);
                break;
            case REQ_CODE_101:
                view.deleteWorkExperienceT(resp);
                break;
            case REQ_CODE_102:
                view.deleteWorkHopeT(resp);
                break;
            case REQ_CODE_103:
                view.getDetailT(resp);
                break;
            case REQ_CODE_104:
                view.getPositionTypeT(resp);
                break;
            case REQ_CODE_105:
                view.getWorkHopeListT(resp);
                break;
            case REQ_CODE_106:
                view.myselfT(resp);
                break;
            case REQ_CODE_107:
                view.saveAdvantage(resp);
                break;
            case REQ_CODE_108:
                view.saveCertificatePhotosT(resp);
                break;
            case REQ_CODE_109:
                view.saveCvBaseInfoT(resp);
                break;
            case REQ_CODE_110:
                view.saveEducationExperienceT(resp);
                break;
            case REQ_CODE_111:
                view.saveLifePhotosT(resp);
                break;
            case REQ_CODE_112:
                view.saveWorkExperienceT(resp);
                break;
            case REQ_CODE_113:
                view.saveWorkHopeT(resp);
                break;
            case REQ_CODE_114:
                view.saveWorkStatusT(resp);
                break;
            case REQ_CODE_115:
                view.getPositionTypeList(resp);
                break;
            case REQ_CODE_116:
                view.showSearch(resp);
                break;
            case REQ_CODE_117:
                view.showPositionCommList(resp);
                break;
            case REQ_CODE_118:
                view.showPositionLikeList(resp);
                break;
            case REQ_CODE_119:
                view.showPositionDetail(resp);
                break;
            case REQ_CODE_120:
                view.showDetailCV(resp);
                break;
            case REQ_CODE_121:
                view.showStoreInfoLikePage(resp);
                break;
            case REQ_CODE_122:
                view.showPostionLike(resp);
                break;
            case REQ_CODE_123:
                view.showCancelPositionLike(resp);
                break;
            case REQ_CODE_30:
                view.showCVSaveLike(resp);
                break;
            case REQ_CODE_31:
                view.showCVCancelLike(resp);
                break;
            case REQ_CODE_124:
                view.showResultgetInterViewTrainer(resp);
                break;
            case REQ_CODE_125:
                view.showResultgetInterViewStore(resp);
                break;
            case REQ_CODE_126:
                view.showResultgetInterViewDetail(resp);
                break;
            case REQ_CODE_127:
                view.showResultgetInterViewInfo(resp);
                break;
            case REQ_CODE_128:
                view.showResultgetInterViewCancel(resp);
                break;
            case REQ_CODE_129:
                view.showResultgetInterViewStatus(resp);
                break;
            case REQ_CODE_130:
                view.showResultSendInterView(resp);
                break;
            case REQ_CODE_131:
                view.getAllBannerSuccess(resp);
                break;
            case REQ_CODE_132:
                view.getMineDynamicSuccess(resp);
                break;
            case REQ_CODE_134:
            case REQ_CODE_133:
                view.getFensListSuccess(resp);
                break;
            case REQ_CODE_CLICK_COLLECT:
            case REQ_CODE_CANCEL_COLLECT:
            case REQ_CODE_CLICK_FOLLOW:
            case REQ_CODE_CANCEL_FOLLOW:
                view.getMessageSuccess(resp);
            case REQ_CODE_LEARN_LIST:
                view.getStudyListSuccess(resp);
                break;
            case REQ_CODE_INVITATION_CODE:
                view.getInvitationSuccess(resp);
                break;
            case REQ_CODE_INVITATION_LIST:
                view.getInvitationListSuccess(resp);
                break;
            case REQ_CODE_SIGNUP_LIST:
                view.getSignUpListSuccess(resp);
                break;
            case REQ_CODE_SHOPPING_NUM:
                view.getShoppingNumSuccess(resp);
                break;
            case REQ_CODE_IS_FINFISH_INFO:
                view.getIsFinishInfoSuccess(resp);
                break;

        }
    }


    /*****************************************上传照片*************************************************************/

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
                ToastHelper.showToast(activity, activity.getResources().getString(R.string.please_add_storage_permission));
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
                case MasterCardActivity.REQUEST_CODE_SELECT_VIDEO:
                    Uri uri = data.getData();
                    ContentResolver cr = activity.getContentResolver();
                    String[] proj = {MediaStore.Video.Media.DATA};
                    LogAndToastUtil.toast("proj==="+proj);
                    Cursor cursor = activity.getContentResolver().query(uri, null, null, null, null);
//                    if(cursor.moveToFirst()){
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
                    String path = cursor.getString(column_index);
                    LogAndToastUtil.toast("proj==="+path);
                    view.onUploadSuccess(path, curIndex);
//                    }
                    cursor.close();

//                        Uri selectedVideo = data.getData();
//                        String[] filePathColumn = {MediaStore.Video.Media.DATA};
//                        cursor = activity.getContentResolver().query(selectedVideo,
//                                filePathColumn, null, null, null);
//                        cursor.moveToFirst();
//
//                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                        String path = cursor.getString(columnIndex);
//
//                        File file = new File(path);
//                        if (file.exists()) {
//                            if (file.length() > 100 * 1024 * 1024) {
//                                LogAndToastUtil.toast(activity, "文件大于100M");
//                                break;
//                            }
//                            view.onUploadSuccess(file.getAbsolutePath(), curIndex);
//                        }


//                        Uri output1 = UCrop.getOutput(data);
//                        if (output1 != null) {
//                            File file = new File(output1.getPath());
//                            if (file.exists()) {
//                                if (file.length() > 100 * 1024 * 1024) {
//                                    LogAndToastUtil.toast(activity, "文件大于100M");
//                                    break;
//                                }
//                                view.onUploadSuccess(file.getAbsolutePath(), curIndex);
//                            }
//                        }
//                    } catch (OutOfMemoryError e) {
//                    } finally {
//                        if(cursor != null){
//                            cursor.close();
//                        }
//                    }
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
