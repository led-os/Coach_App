package com.jsjlzj.wayne.utils;

import android.app.Activity;
import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.store.home.amoy.SignUpActivity;
import com.netease.nim.uikit.common.ToastHelper;

/**
 * @ClassName: JsInterface
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/3/17 0:31
 */
public class JsInterface {

    private Activity mContext;

    public JsInterface(Activity mContext) {
        this.mContext = mContext;
    }

    @JavascriptInterface
    public void handlerJsMessage(String jsonMessage) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(jsonMessage);
            LogAndToastUtil.log("====JsInterface===="+jsonMessage);
            int action = jsonObject.getIntValue(ExtraConstant.EXTRA_JS_ACTION);
            switch (action) {
                //跳转页面
//                    case Constant.ACTION_JUMP:
//                        jumpActivity(jsonObject);
//                        break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到我要报名界面
     * @param schoolId 学校id
     * @param courseId 课程id
     */
    @JavascriptInterface
    public void joinSchool(String schoolId,String courseId){
        LogAndToastUtil.log(schoolId+"====JsInterface===="+courseId);
        SignUpActivity.go2this(mContext,schoolId,courseId);
    }


    /**
     * 内部跳转界面
     * @param url 地址后缀
     * @param title 标题
     */
    @JavascriptInterface
    public void toPage(String url,String title){
        LogAndToastUtil.log(url+"====JsInterface===="+title);
        WebViewContainerActivity.go2this(mContext,title, HttpConstant.WEB_URL_BASE+url, WebViewContainerFragment.TYPE_INNER_TAB);
    }

    /**
     * 分享
     * @param url 分享的url
     * @param title 分享的标题
     * @param content 分享的内容
     * @param img 分享的图片
     */
    @JavascriptInterface
    public void share(String url,String title,String content,String img){
        ToastHelper.showToast(mContext,"分享");
    }

    /**
     * 课程报名
     * @param courseId 课程id
     */
    @JavascriptInterface
    public void joinCourse(String courseId){
        ToastHelper.showToast(mContext,"课程报名");
    }

    @JavascriptInterface
    public void buyProduct(String productId){
        ToastHelper.showToast(mContext,"产品购买");
    }

    @JavascriptInterface
    public void joinMatch(String matchId){
        ToastHelper.showToast(mContext,"赛事报名");
    }

    @JavascriptInterface
    public void joinCourseLearn(String courseId){
        ToastHelper.showToast(mContext,"加入课程学习");
    }

    @JavascriptInterface
    public void reLogin(String url){
        ToastHelper.showToast(mContext,"token过期");
    }
}
