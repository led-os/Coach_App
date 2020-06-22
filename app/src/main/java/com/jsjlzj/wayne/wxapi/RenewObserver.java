package com.jsjlzj.wayne.wxapi;

import com.jsjlzj.wayne.ui.store.shopping.PaymentActivity;

import java.util.ArrayList;

/**
 * description 微信的回调监听
 * date: 2019/09/05
 * @author: 曾海强
 */
public class RenewObserver {

    private ArrayList<OnWeiXinListener> onWeiXinListeners = new ArrayList<>();

    public static RenewObserver getInstance() {
        return Holder.instance;
    }

    public void registerWeiXinListener(OnWeiXinListener listener) {
        onWeiXinListeners.add(listener);
    }

    public void unRegisterWeiXinListener(OnWeiXinListener listener) {
        if (listener != null && onWeiXinListeners.size() > 0) {
            onWeiXinListeners.remove(listener);
        }
    }

    public void onPay(int errorCode) {
        if (onWeiXinListeners.size() > 0) {
            int size = onWeiXinListeners.size();
            for (int i = 0; i < size; i++) {
                onWeiXinListeners.get(i).onPay(errorCode);
            }
        }
    }



    public interface OnWeiXinListener {
        /**
         * 支付
         */
        void onPay(int errorCode);

    }

    private static class Holder {
        private static final RenewObserver instance = new RenewObserver();
    }
}
