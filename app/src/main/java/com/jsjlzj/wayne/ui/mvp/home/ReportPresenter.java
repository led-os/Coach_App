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
import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseModel;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BasePresenter;
import com.jsjlzj.wayne.utils.SelectImageUtils;
import com.netease.nim.uikit.common.ToastHelper;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

/**
 * @ClassName: ReportPresenter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/12 22:42
 */
public class ReportPresenter extends BasePresenter<ReportView> {

    private ReportModel model;

    public ReportPresenter(ReportView view) {
        this.view = view;
        this.model = new ReportModel();
    }

    @Override
    protected BaseModel getMode() {
        return model;
    }

    @Override
    protected void responseSuccess(int code, MdlBaseHttpResp resp) {

    }


    public void uploadImage(String imgUrl){
        File file = new File(imgUrl);
        RequestBody fileRQ = RequestBody.create(file, MediaType.parse("image/*"));
        MultipartBody.Part  pic1 = MultipartBody.Part.createFormData("file", file.getName(), fileRQ);
//        addSubscription(apiStores.requestUploadFile(pic1), new BaseObserver<BaseApiResponse<ImageBean>>() {
//            @Override
//            public void onError(ResponseException e) {
//                mView.getFloorWithRoomFail();
//            }
//
//            @Override
//            public void onNext(BaseApiResponse<ImageBean> data) {
//                if(data != null && data.getData() != null && data.getCode() == 200){
//                    mView.uploadToServerSuccess(data.getData());
//                }else {
//                    mView.getFloorWithRoomFail();
//                }
//            }
//        });
    }



    /**
     * 读写文件请求码
     */
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private int curIndex;

    /**
     * 获取权限
     */
    public void autoObtainStoragePermission(Activity activity,int position) {
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
                ToastHelper.showToast(activity,activity.getResources().getString(R.string.please_add_storage_permission));
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
