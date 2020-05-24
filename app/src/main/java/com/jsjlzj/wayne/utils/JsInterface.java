package com.jsjlzj.wayne.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.publicac.report.ReportTypeActivity;
import com.jsjlzj.wayne.ui.store.find.ConfirmCourserOrderActivity;
import com.jsjlzj.wayne.ui.store.home.amoy.SignUpActivity;
import com.jsjlzj.wayne.ui.store.list.MoreMatchActivity;
import com.jsjlzj.wayne.ui.store.shopping.ShoppingCartActivity;
import com.jsjlzj.wayne.widgets.dialog.ShareDialog;
import com.netease.nim.uikit.common.ToastHelper;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;

import static com.jsjlzj.wayne.utils.Utility.bmpToByteArray;
import static com.jsjlzj.wayne.utils.Utility.buildTransaction;

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
        SignUpActivity.go2this(mContext,schoolId,courseId,0);
    }


    /**
     * 内部跳转界面
     * @param url 地址后缀
     * @param title 标题
     */
    @JavascriptInterface
    public void toPage(String url,String title){
        LogAndToastUtil.log(url+"====JsInterface===="+title);
        if(!url.contains("http:")){
            url = HttpConstant.WEB_URL_BASE + url ;
        }
        WebViewContainerActivity.go2this(mContext,title, url, WebViewContainerFragment.TYPE_INNER_TAB);
    }

    @JavascriptInterface
    public void lookMoreActivity(){
        // TODO: 2020/3/24 跳转到更多赛事
        LogAndToastUtil.toast("全部赛事");
        MoreMatchActivity.go2this(mContext);
    }

    private Bitmap bitmap;

    /**
     * 分享
     * @param url 分享的url
     * @param title 分享的标题
     * @param content 分享的内容
     * @param img 分享的图片
     */
    @JavascriptInterface
    public void share(String url,String title,String content,String img){
        LogAndToastUtil.log(title+"====JsInterface===="+content+"=========="+img);
        BitmapUtils.getBitmap(mContext, img, new BitmapUtils.GlideLoadBitmapCallback() {
            @Override
            public void getBitmapCallback(Bitmap bitmap) {
                LogAndToastUtil.log("====bitmap===="+bitmap+"=========="+img);
                JsInterface.this.bitmap = bitmap;
            }
        });
        new ShareDialog(mContext,index -> {
            if(index == 2){
                ReportTypeActivity.go2this(mContext);
            }else {
                //0:微信好友  1:朋友圈
                share(index,url,title,content);
            }
        }).show();
    }


    private void share(int type,String url,String title,String content) {
        if (!MyApp.getApp().getIwxapi().isWXAppInstalled()) {
            LogAndToastUtil.toast("您的设备未安装微信客户端");
        } else {
            WXWebpageObject wxWebpage = new WXWebpageObject();
            wxWebpage.webpageUrl = url;
            WXMediaMessage msg = new WXMediaMessage(wxWebpage);
            msg.title = title;
            msg.description = content;

            if(bitmap == null){
                bitmap= BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
            }
            Bitmap thumbData = Bitmap.createScaledBitmap(bitmap, 100, 100, true);

            msg.thumbData = bmpToByteArray(thumbData, true);
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("Req");
            req.message = msg;
            switch (type) {
                case 0:
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;
                    MyApp.getApp().getIwxapi().sendReq(req);
                    break;
                case 1:
                    req.scene = SendMessageToWX.Req.WXSceneSession;
                    MyApp.getApp().getIwxapi().sendReq(req);
                    break;
                case 2:
                    break;
            }


        }
    }

    /**
     * 课程报名
     * @param courseId 课程id
     */
    @JavascriptInterface
    public void joinCourse(String courseId){
        SignUpActivity.go2this(mContext,"",courseId,0);
    }

    @JavascriptInterface
    public void buyProduct(String productId,String phone){
        ToastHelper.showToast(mContext,productId+"产品购买"+phone);
    }

    @JavascriptInterface
    public void joinMatch(String matchId){
        SignUpActivity.go2this(mContext,"",matchId,1);
    }

    @JavascriptInterface
    public void joinCourseLearn(String courseId){
        ToastHelper.showToast(mContext,"加入课程学习");
    }

    @JavascriptInterface
    public void reLogin(String url){
        Utility.needLogin(mContext);
    }

    /**
     * 课程购买
     * @param id
     */
    @JavascriptInterface
    public void buyCourse(int id){
        ConfirmCourserOrderActivity.go2this(mContext, id);
    }

    /**
     * 客服
     */
    @JavascriptInterface
    public void toService(){
        LogAndToastUtil.toast("====JsInterface====toService");
    }

    /**
     * 购物车
     */
    @JavascriptInterface
    public void toBuyCar(){
        LogAndToastUtil.toast("====JsInterface====toBuyCar");
        ShoppingCartActivity.go2this(mContext);
    }

    /**
     * 立即购买
     * @param type
     * @param buyNum
     * @param productId
     */
    @JavascriptInterface
    public void buyGoods(int type, int buyNum, int productId){
        LogAndToastUtil.toast("====JsInterface====type:"+type+"buyNum:"+buyNum+"===productId:"+productId);

    }

    @JavascriptInterface
    public void buyVip(String product){
        LogAndToastUtil.toast("====JsInterface====buyVip"+product);
    }

    @JavascriptInterface
    public String getToken(){
        LogAndToastUtil.log("====token===="+SPUtil.getTokenFromSP());
        return SPUtil.getTokenFromSP();
    }

    /**
     * 跳转积分明细
     */
    @JavascriptInterface
    public void toIntegral(){
        LogAndToastUtil.toast("====JsInterface====toIntegral");
    }
}
