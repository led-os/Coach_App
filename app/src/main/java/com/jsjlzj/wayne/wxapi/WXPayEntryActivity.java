package com.jsjlzj.wayne.wxapi;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.mvp.BasePresenter;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * @ClassName: WXPayEntryActivity
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/29 22:58
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        System.out.println("=========================================================="+baseResp.getType());
        LogAndToastUtil.log(baseResp.getType() + "=====111111" + JSONObject.toJSONString(baseResp));
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            RenewObserver.getInstance().onPay(baseResp.errCode);
            finish();
//            if (baseResp.errCode == BaseResp.ErrCode.ERR_OK) {
//            } else if (baseResp.errCode == BaseResp.ErrCode.ERR_USER_CANCEL) {
//                LogAndToastUtil.toast("取消支付");
//                finish();
//            }
            return;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp.getApp().getIwxapi().handleIntent(getIntent(),this);
    }
}
