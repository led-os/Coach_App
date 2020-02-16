package com.jsjlzj.wayne.ui.mvp.home;

import com.jsjlzj.wayne.ui.mvp.base.mvp.BaseView;

/**
 * @ClassName: ReportView
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/2/12 22:39
 */
public interface ReportView extends BaseView {

    default void selectPhoto(int position){}

    default void onUploadSuccess(String imgUrl,int position){}

    default void uploadToServerSuccess(String imgUrl){}
}
