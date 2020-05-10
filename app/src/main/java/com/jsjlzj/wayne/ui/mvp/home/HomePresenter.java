package com.jsjlzj.wayne.ui.mvp.home;

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
import com.jsjlzj.wayne.ui.mvp.base.mvp.BasePresenter;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SelectImageUtils;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

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
    private static final int REQ_CODE_DRIED_FOOD = 6;
    private static final int REQ_CODE_DRIED_FOOD_LIST = 7;
    private static final int REQ_CODE_INFORMATION = 8;
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
    private static final int REQ_CODE_AMOY_SIGN_UP = 35;
    private static final int REQ_CODE_PUBLIC_DYNAMIC = 36;
    private static final int REQ_CODE_UPLOAD_FILE = 37;
    private static final int REQ_CODE_UPLOAD_VIDEO_FILE = 41;
    private static final int REQ_CODE_DYNAMIC_COLLECT = 38;
    private static final int REQ_CODE_VIDEO_COLLECT = 39;
    private static final int REQ_CODE_INFORMATION_COLLECT = 40;
    private static final int REQ_CODE_DELETE_DYNAMIC = 42;
    private static final int REQ_CODE_OPTIMIZATION_DATA1 = 43;
    private static final int REQ_CODE_OPTIMIZATION_DATA2 = 44;
    private static final int REQ_CODE_CASH_OUT_LIST = 45;
    private static final int REQ_CODE_MINE_PROFIT = 46;
    private static final int REQ_CODE_RECOMMEND_CATEGORY_LIST = 47;
    private static final int REQ_CODE_SEARCH_CATEGORY_LIST = 48;
    private static final int REQ_CODE_JIFEN_LIST = 49;
    private static final int REQ_CODE_LOCATION_LIST = 50;
    private static final int REQ_CODE_SAVE_LOCATION = 51;
    private static final int REQ_CODE_CURRENCY_LIST = 52;
    private static final int REQ_CODE_CURRENCY_DETAIL_LIST = 53;


    private HomeModel model;

    @Override
    protected HomeModel getMode() {
        return model;
    }


    public HomePresenter(HomeView view) {
        this.view = view;
        this.model = new HomeModel();
    }


    public void getRecommendData() {
        if (model != null) {
            view.showLoading();
            model.getHomeRecommendData(REQ_CODE_RECOMMEND, null, this);
        }
    }


    public void getAmoySchoolData() {
        if (model != null) {
            view.showLoading();
            model.getAmoySchoolData(REQ_CODE_AMOY_SCHOOL, null, this);
        }
    }

    public void getMatchData() {
        if (model != null) {
            view.showLoading();
            model.getMatchData(REQ_CODE_MATCH, null, this);
        }
    }


    public void getAmoyList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getAmoyList(REQ_CODE_AMOY_LIST, param, this);
        }
    }


    public void getMatchList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getMatchList(REQ_CODE_MATCH_LIST, param, this);
        }
    }

    public void getDriedFoodData() {
        if (model != null) {
            view.showLoading();
            model.getDriedFoodData(REQ_CODE_DRIED_FOOD, null, this);
        }
    }

    public void getDriedFoodList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getDriedFoodList(REQ_CODE_DRIED_FOOD_LIST, param, this);
        }
    }

    public void getInformationData() {
        if (model != null) {
            view.showLoading();
            model.getInformationData(REQ_CODE_INFORMATION, null, this);
        }
    }

    public void getInformationList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getInformationList(REQ_CODE_INFORMATION_LIST, param, this);
        }
    }


    public void getProductData() {
        if (model != null) {
            view.showLoading();
            model.getProductData(REQ_CODE_PRODUCT, null, this);
        }
    }

    public void getProductList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getProductList(REQ_CODE_PRODUCT_LIST, param, this);
        }
    }


    public void getAllClassic() {
        if (model != null) {
            view.showLoading();
            model.getAllClassicList(REQ_CODE_ALL_CLASSIC, null, this);
        }
    }

    public void getOrganizationList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getOrganizationList(REQ_CODE_ORIGANIZATION_LIST, param, this);
        }
    }


    public void getVideoList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getVideoList(REQ_CODE_VIDEO_LIST, param, this);
        }
    }

    public void clickZan(Map param) {
        if (model != null) {
//            view.showLoading();
            model.clickZan(REQ_CODE_CLICK_ZAN, param, this);
        }
    }

    /**
     * "module": "模块编码:干货 GAN_HUO,视频 VIDEO,社区 COMMUNITY,在线课程 ONLINE_LEARN"
     *
     * @param param
     */
    public void cancelZan(Map param) {
        if (model != null) {
//            view.showLoading();
            model.cancelZan(REQ_CODE_CANCEL_ZAN, param, this);
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

    public void deleteDynamic(Map param) {
        if (model != null) {
            model.deleteDynamic(REQ_CODE_DELETE_DYNAMIC, param, this);
        }
    }


    public void getLearnData() {
        if (model != null) {
            view.showLoading();
            model.getLearnData(REQ_CODE_LEARN_DATA, null, this);
        }
    }


    public void getChapterList() {
        if (model != null) {
            view.showLoading();
            model.getChapterList(REQ_CODE_CHAPTER_LIST, null, this);
        }
    }

    public void getChapterSubjectList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getChapterSubjectList(REQ_CODE_CHAPTER_SUBJECT_LIST, param, this);
        }
    }

    public void getSaveAnswerRecord(Map param) {
        if (model != null) {
            model.getSaveAnswerRecord(REQ_CODE_SAVE_ANSWER_RECORD, param, this);
        }
    }

    public void submitExamAnswer(Map param) {
        if (model != null) {
            model.submitExamAnswer(REQ_CODE_SUBMIT_EXAM_ANSWER, param, this);
        }
    }

    public void getWrongSubjectList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getWrongSubjectList(REQ_CODE_WRONG_SUBJECT_LIST, param, this);
        }
    }


    public void getExamSubjectList() {
        if (model != null) {
            view.showLoading();
            model.getExamSubjectList(REQ_CODE_EXAM_SUBJECT_LIST, null, this);
        }
    }


    public void doneChapterAnswer() {
        if (model != null) {
            view.showLoading();
            model.doneChapterAnswer(REQ_CODE_DONE_CHAPTER_ANSWER, null, this);
        }
    }

    public void getTestResult() {
        if (model != null) {
            view.showLoading();
            model.getTestResult(REQ_CODE_TEST_RESULT, null, this);
        }
    }

    public void getCurrentSubject() {
        if (model != null) {
            view.showLoading();
            model.getCurrentSubject(REQ_CODE_CURRENT_SUBJECT, null, this);
        }
    }

    public void getAnswerRecord() {
        if (model != null) {
            view.showLoading();
            model.getAnswerRecord(REQ_CODE_ANSWER_RECORD, null, this);
        }
    }

    public void getSearchData(Map param) {
        if (model != null) {
            view.showLoading();
            model.getSearchData(REQ_CODE_SEARCH, param, this);
        }
    }

    public void getDynamicList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getDynamicList(REQ_CODE_DYNAMIC_LIST, param, this);
        }
    }

    public void getMineDynamicList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getMineDynamicList(REQ_CODE_MINE_DYNAMIC_LIST, param, this);
        }
    }


    public void getAmoySiguUp(Map param) {
        if (model != null) {
            view.showLoading();
            model.getAmoySignUp(REQ_CODE_AMOY_SIGN_UP, param, this);
        }
    }


    public void getMatchSiguUp(Map param) {
        if (model != null) {
            view.showLoading();
            model.getMatchSignUp(REQ_CODE_AMOY_SIGN_UP, param, this);
        }
    }

    public void publicDynamic(Map param) {
        if (model != null) {
            view.showLoading();
            model.publicDynamic(REQ_CODE_PUBLIC_DYNAMIC, param, this);
        }
    }


    public void upload(String path) {
        if (model != null) {
            view.showLoading();
            model.upload(REQ_CODE_UPLOAD_FILE, path, this);
        }
    }

    public void uploadVideo(String path) {
        if (model != null) {
            view.showLoading();
            model.uploadVideo(REQ_CODE_UPLOAD_VIDEO_FILE, path, this);
        }
    }

    public void getCollectDynamicList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getCollectDynamicList(REQ_CODE_DYNAMIC_COLLECT, param, this);
        }
    }

    public void getCollectVideoList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getCollectVideoList(REQ_CODE_VIDEO_COLLECT, param, this);
        }
    }


    public void getCollectInformationList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getCollectInformationList(REQ_CODE_INFORMATION_COLLECT, param, this);
        }
    }

    public void getOptimizationData1() {
        if (model != null) {
            view.showLoading();
            model.getOptimizationData1(REQ_CODE_OPTIMIZATION_DATA1, null, this);
        }
    }

    public void getOptimizationData2() {
        if (model != null) {
            view.showLoading();
            model.getOptimizationData2(REQ_CODE_OPTIMIZATION_DATA2, null, this);
        }
    }

    public void getCashOutList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getCashOutList(REQ_CODE_CASH_OUT_LIST, param, this);
        }
    }

    public void getMineProfit() {
        if (model != null) {
            view.showLoading();
            model.getMineProfit(REQ_CODE_MINE_PROFIT, null, this);
        }
    }
   public void getRecommendCategoryList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getRecommendCategoryList(REQ_CODE_RECOMMEND_CATEGORY_LIST, param, this);
        }
    }

    public void getFreeExperCategoryList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getFreeExperCategoryList(REQ_CODE_RECOMMEND_CATEGORY_LIST, param, this);
        }
    }
    public void getHotCategoryList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getHotCategoryList(REQ_CODE_RECOMMEND_CATEGORY_LIST, param, this);
        }
    }
    public void getHotListeningCategoryList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getHotListeningCategoryList(REQ_CODE_RECOMMEND_CATEGORY_LIST, param, this);
        }
    }
    public void getJianzhiCategoryList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getJianzhiCategoryList(REQ_CODE_RECOMMEND_CATEGORY_LIST, param, this);
        }
    }

    public void getMotionCategoryList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getMotionCategoryList(REQ_CODE_RECOMMEND_CATEGORY_LIST, param, this);
        }
    }
    public void getFourLessonCategoryList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getFourLessonCategoryList(REQ_CODE_RECOMMEND_CATEGORY_LIST, param, this);
        }
    }


    public void getJifenList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getJifenList(REQ_CODE_JIFEN_LIST, param, this);
        }
    }

   public void getSearchCategoryList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getSearchCategoryList(REQ_CODE_SEARCH_CATEGORY_LIST, param, this);
        }
    }

   public void getLocationList() {
        if (model != null) {
            view.showLoading();
            model.getLocationList(REQ_CODE_LOCATION_LIST, null, this);
        }
    }

   public void saveLocation(Map param) {
        if (model != null) {
            view.showLoading();
            model.saveLocation(REQ_CODE_SAVE_LOCATION, param, this);
        }
    }
  public void getCurrencyList() {
        if (model != null) {
            view.showLoading();
            model.getCurrencyList(REQ_CODE_CURRENCY_LIST, null, this);
        }
    }

  public void getCurrencyDetailList(Map param) {
        if (model != null) {
            view.showLoading();
            model.getCurrencyDetailList(REQ_CODE_CURRENCY_DETAIL_LIST, param, this);
        }
    }


    @Override
    protected void responseSuccess(int code, MdlBaseHttpResp resp) {
        view.hideLoading();
        switch (code) {
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
            case REQ_CODE_DYNAMIC_COLLECT:
            case REQ_CODE_INFORMATION_COLLECT:
            case REQ_CODE_VIDEO_COLLECT:
                view.getVideoListSuccess(resp);
                break;
            case REQ_CODE_ALL_CLASSIC:
                view.getAllClassicSuccess(resp);
                break;
            case REQ_CODE_CANCEL_ZAN:
            case REQ_CODE_CLICK_ZAN:
            case REQ_CODE_CLICK_COLLECT:
            case REQ_CODE_CANCEL_COLLECT:
            case REQ_CODE_CLICK_FOLLOW:
            case REQ_CODE_CANCEL_FOLLOW:
                view.getMessageSuccess(resp);
                break;
            case REQ_CODE_DELETE_DYNAMIC:
                view.deleteDynamicSuccess(resp);
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
            case REQ_CODE_AMOY_SIGN_UP:
                view.amoySignUpSuccess(resp);
                break;
            case REQ_CODE_PUBLIC_DYNAMIC:
                view.publicDynamicSuccess(resp);
                break;
            case REQ_CODE_UPLOAD_VIDEO_FILE:
            case REQ_CODE_UPLOAD_FILE:
                view.showUpload(resp);
                break;
            case REQ_CODE_OPTIMIZATION_DATA1:
                view.getOptimizationData1Success(resp);
                break;
            case REQ_CODE_OPTIMIZATION_DATA2:
                view.getOptimizationData2Success(resp);
                break;
            case REQ_CODE_CASH_OUT_LIST:
                view.getCashOutListSuccess(resp);
                break;
            case REQ_CODE_MINE_PROFIT:
                view.getMineProfitSuccess(resp);
                break;
            case REQ_CODE_SEARCH_CATEGORY_LIST:
            case REQ_CODE_RECOMMEND_CATEGORY_LIST:
                view.getRecommendCategoryListSuccess(resp);
                break;
            case REQ_CODE_JIFEN_LIST:
                view.getJifenListSuccess(resp);
                break;
            case REQ_CODE_LOCATION_LIST:
                view.getLocationListSuccess(resp);
                break;
            case REQ_CODE_SAVE_LOCATION:
                view.saveLocationSuccess(resp);
                break;
            case REQ_CODE_CURRENCY_LIST:
                view.getCurrencyListSuccess(resp);
                break;
            case REQ_CODE_CURRENCY_DETAIL_LIST:
                view.getCurrencyDetailListSuccess(resp);
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
