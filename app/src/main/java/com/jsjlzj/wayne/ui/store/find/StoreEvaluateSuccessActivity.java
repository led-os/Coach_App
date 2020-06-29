package com.jsjlzj.wayne.ui.store.find;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.find.FindStoreAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.find.FindStoreBean;
import com.jsjlzj.wayne.entity.find.FindStoreRecommendBean;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.ui.publicac.report.ReportTypeActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.PositionInfoStoreActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.dialog.ShareDialog;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jsjlzj.wayne.constant.HttpConstant.BASE_URL;
import static com.jsjlzj.wayne.utils.Utility.bmpToByteArray;
import static com.jsjlzj.wayne.utils.Utility.buildTransaction;

/**
 * @ClassName: 评价成功
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate:
 */
public class StoreEvaluateSuccessActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.tv_scan_detail)
    TextView tvScanDetail;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;
    private int evaluateId;
    private FindStoreAdapter findStoreAdapter;
    private List<FindStoreBean> list = new ArrayList<>();
    private int storeId;

    public static void go2this(Activity activity, int evaluateId,int storeId) {
        activity.startActivity(new Intent(activity, StoreEvaluateSuccessActivity.class)
                .putExtra("evaluateId", evaluateId)
                .putExtra("storeId", storeId));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_store_evaluste_success;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        initTitle("评价结果");
        evaluateId = getIntent().getIntExtra("evaluateId",0);
        storeId = getIntent().getIntExtra("storeId",0);
        tvScanDetail.setOnClickListener(clickListener);
        tvShare.setOnClickListener(clickListener);
        findStoreAdapter = new FindStoreAdapter(this, list);
        findStoreAdapter.setType(1);
        rvRecommend.setLayoutManager(new LinearLayoutManager(this));
        rvRecommend.setAdapter(findStoreAdapter);
        presenter.getFindStoreRecommendList(evaluateId);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()){
            case R.id.tv_scan_detail:
                FindMyEvaluateActivity.go2this(this,evaluateId);
                break;
            case R.id.tv_share:
                showShareDialog();
                break;
                default:break;
        }
    }

    private void showShareDialog() {
        new ShareDialog(this, new ShareDialog.ClickListener() {
            @Override
            public void clickConfirm(int index) {
                if(index == 2){
                    ReportTypeActivity.go2this(StoreEvaluateSuccessActivity.this);
                }else {
                    //0:朋友圈  1:微信好友
                    share(index);
                }
            }
        }).show();
    }

    private void share(int type){
        if(!MyApp.getApp().getIwxapi().isWXAppInstalled()){
            LogAndToastUtil.toast("您的设备未安装微信客户端");
        }else{
            WXWebpageObject webpageObject=new WXWebpageObject();
            webpageObject.webpageUrl=BASE_URL+"#/clubDetail?id="+storeId;
            WXMediaMessage msg=new WXMediaMessage(webpageObject);
            msg.title="蜂隐运动";
            msg.description="俱乐部详情";
            Bitmap thumBitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
            msg.thumbData = bmpToByteArray(thumBitmap, true);
            SendMessageToWX.Req req=new SendMessageToWX.Req();
            req.transaction = buildTransaction("Req");
            req.message=msg;
            switch (type){
                case 0:
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;
                    break;
                case 1:
                    req.scene=SendMessageToWX.Req.WXSceneSession;
                    break;
            }
            MyApp.getApp().getIwxapi().sendReq(req);
            finish();
        }
    }

    @Override
    public void getFindStoreRecommendListSuccess(MdlBaseHttpResp<FindStoreRecommendBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            findStoreAdapter.setData(resp.getData().getData());
        }
    }
}
