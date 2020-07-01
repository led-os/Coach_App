package com.jsjlzj.wayne.utils.imagewatcher;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 保存图片的asynctask
 * Created by hongzhou on 2018/6/6.
 */

public class GlideSaveBitmapAsyncTask extends AsyncTask<Void, Integer, Void> {
    public static final String FOLDER_NAME = "/ibitimages";
    Context mContext;
    String mImgUrl;
    TextView mPercentBtn;
    String fileName;
    public GlideSaveBitmapAsyncTask(Context context, String url/*, TextView percentBtn*/, String fileName) {
        mContext = context;
        mImgUrl = url;
        this.fileName = fileName;
//        mPercentBtn = percentBtn;
    }

    @Override
    protected Void doInBackground(Void... params) {
        checkFolder();
        String path = getImagePath(mImgUrl);
        copyFile(path, getStorePath(mImgUrl));
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.e("ZH","onProgressUpdate--->"+values[0]);
//        mPercentBtn.setText(values[0] +"%");
        if (values[0] == 100) {
//            mPercentBtn.setText("已保存");
//            ToastUtils.showToastCenter(App.getApp().getApplicationContext(), mContext.getString(R.string.save_to_sdcard)+ Environment.getExternalStorageDirectory().getAbsolutePath()+FOLDER_NAME+"/"+getImgName(mImgUrl));
        }
    }

    private void checkFolder() {
        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+FOLDER_NAME);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    private String getImgName(String url) {
        return fileName;
    }

    private String getStorePath(String url) {
        return Environment.getExternalStorageDirectory().getAbsolutePath()+FOLDER_NAME+"/"+getImgName(url);
    }

    /**
     * Glide 获得图片缓存路径
     */
    private String getImagePath(String imgUrl) {
        String path = null;
        FutureTarget<File> future = Glide.with(mContext)
                .load(imgUrl)
                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
        try {
            File cacheFile = future.get();
            path = cacheFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ZZZZZ",e.getMessage());
        }
        return path;
    }

    public void copyFile(String oldPath, String newPath) {
        InputStream inStream = null;
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            File newFile = new File(newPath);
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            if (oldfile.exists()) { //文件存在时
                inStream = new FileInputStream(oldPath); //读入原文件
                float fileSize = inStream.available();
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
//                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                    publishProgress((int)(bytesum / fileSize * 100));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inStream.close();
                AndroidUtilities.addMediaToGallery(newPath);
            } catch (Exception e) {
            }
        }
    }
}
