package com.jsjlzj.wayne.ui.trainer.publicac;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.google.android.flexbox.FlexboxLayout;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.ItemsBean;
import com.jsjlzj.wayne.entity.store.MdlPosition;
import com.jsjlzj.wayne.entity.store.MdlPositionDetail;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.store.personal.storeinfo.set.NavigationActivity;
import com.jsjlzj.wayne.ui.store.personal.storeinfo.set.StoreInfoPreviewActivity;
import com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity;
import com.jsjlzj.wayne.ui.yunxin.YunXingUtil;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.dialog.CommonDialog;
import com.jsjlzj.wayne.widgets.dialog.ReportDialog;
import com.jsjlzj.wayne.widgets.dialog.ShareDialog;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jsjlzj.wayne.constant.HttpConstant.BASE_URL;
import static com.jsjlzj.wayne.constant.HttpConstant.WXAPPID;
import static com.jsjlzj.wayne.utils.Utility.bmpToByteArray;
import static com.jsjlzj.wayne.utils.Utility.buildTransaction;

/**
 * 职位详情  (教练看  门店发布的职位)
 */
public class PositionInfoStoreActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


    public static void go2this(Activity context,String id) {
        Intent intent = new Intent(context, PositionInfoStoreActivity.class);
        intent.putExtra("id",id);
        context.startActivityForResult(intent, RecruitActivity.FLAG_RECRUIT_CONTENT);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_position_info_store;
    }

    private TextView tvPositionName,tvPositionContent,tvSalary,tvPublisherPositon,tvPublisherName,tvPositionContent2,tvStoreName,tvNumber,addressTv;
    private ImageView  image,imageStore,btnShoucang;
    private FlexboxLayout llLabelSkill,llLabelWelfare;
    private MapView baiduMap;
    private Map<Object,Object> map;
    private BaiduMap mBaiduMap;
    private String storeId;
    private String id;
    private boolean isLikeFlag=false;
    private IWXAPI iwxapi;
    private List<ItemsBean> list;
    @Override
    protected void initViewAndControl() {
        list=new ArrayList<>();
        iwxapi= WXAPIFactory.createWXAPI(this,WXAPPID,true);
        id=getIntent().getStringExtra("id");
        tvPositionName=findView(R.id.tvPositionName);//职位名称
        tvPositionContent=findView(R.id.tvPositionContent);//职位描述 地址 年限 文化
        tvSalary=findView(R.id.tvSalary);//薪资范围

        image=findView(R.id.image);//发布者头像
        tvPublisherName=findView(R.id.tvPublisherName);//发布者名称
        tvPublisherPositon=findView(R.id.tvPublisherPositon);//发布者职位

        imageStore=findView(R.id.imageStore);//门店头像
        tvStoreName=findView(R.id.tvStoreName);//门店名字
        tvNumber=findView(R.id.tvNumber);//门店规模
        baiduMap=findView(R.id.baiduMap);//门店地图
        addressTv = findView(R.id.addressTv);

        tvPositionContent2=findView(R.id.tvPositionContent2);//职位描述
        llLabelSkill=findView(R.id.llLabelSkill);//技能要求
        btnShoucang=findView(R.id.btnShoucang);
        llLabelWelfare=findView(R.id.llLabelWelfare);//俱乐部福利
        mBaiduMap=baiduMap.getMap();
        mBaiduMap.getUiSettings().setAllGesturesEnabled(false);
        baiduMap.showZoomControls(false);
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(null!=bean&&bean.getStoreInfo()!=null) {
                    NavigationActivity.go2this(PositionInfoStoreActivity.this, bean.getStoreInfo().getStoreAddress(), bean.getStoreInfo().getCoordinate());
                }else{
                    NavigationActivity.go2this(PositionInfoStoreActivity.this, "", "");
                }
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
        findView(R.id.btnConfirm).setOnClickListener(clickListener);
        findView(R.id.btnFengxiang).setOnClickListener(clickListener);
        findView(R.id.llStoreAddress).setOnClickListener(clickListener);
        findView(R.id.btnJubao).setOnClickListener(clickListener);
        btnShoucang.setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        if(null==map) map=new HashMap<>();
        map.clear();
        if(!TextUtils.isEmpty(id)) {
            map.put("id", id);
            presenter.getTrainerPositionDetail(map);
        }
        if(MyApp.mdlDict!=null&&MyApp.mdlDict.getPosition_tipoff()!=null){
            list.addAll(MyApp.mdlDict.getPosition_tipoff().getItems());
        }
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    private MyViewClickListener clickListener = new MyViewClickListener();
    CommonDialog dialog;
    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnConfirm://立即沟通
                    if(TextUtils.isEmpty(MyApp.workHopeId)){
                        dialog=new CommonDialog(PositionInfoStoreActivity.this, "您还没有发布求职意向,快去发布吧", new CommonDialog.ClickListener() {
                            @Override
                            public void clickConfirm() {
                                dialog.dismiss();
                            }

                            @Override
                            public void clickCancel() {
                                JobIntentionActivity.go2this(PositionInfoStoreActivity.this);
                            }
                        });
                        dialog.show();
                        dialog.setCancel("确定");
                        dialog.setConfirm("下次再说");
                    }else {
                        if (bean != null && bean.getPosition() != null)
                            YunXingUtil.toChatRoom(view.getContext(),
                                    bean.getYunXinAccount(),
                                    MyApp.workHopeId,
                                    MyApp.workHopeName,
                                    bean.getPosition().getId(),
                                    MyApp.isTrainer);
                    }
                    break;
                case R.id.llStoreAddress://公司
                    StoreInfoPreviewActivity.go2this(PositionInfoStoreActivity.this,storeId);
                    break;
                case R.id.btnFengxiang://分享
                    showShareDialog();
                    break;
                case R.id.btnJubao://举报
                    ReportDialog dialog=new ReportDialog(PositionInfoStoreActivity.this, new ReportDialog.ClickListener(){
                        @Override
                        public void clickConfirm(String code) {
                            LogAndToastUtil.toast("举报成功");
                        }
                    },list);
                    dialog.show();
                    break;
                case R.id.btnShoucang://收藏
                    if(isLikeFlag){
                        presenter.cancelPositionLike(map);
                    }else {
                        presenter.savePositionLike(map);
                    }
                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
            }
        }
    }
    private void showShareDialog() {
        new ShareDialog(this, new ShareDialog.ClickListener() {
            @Override
            public void clickConfirm(int index) {
                //0:微信好友  1:朋友圈
                share(index);
            }
        }).show();
    }

    private void share(int type){
        if(!iwxapi.isWXAppInstalled()){
            LogAndToastUtil.toast("您的设备未安装微信客户端");
        }else{
            WXWebpageObject webpageObject=new WXWebpageObject();
            webpageObject.webpageUrl=BASE_URL+"static/h5/index.html?id="+id;
            WXMediaMessage msg=new WXMediaMessage(webpageObject);
            msg.title="健身教练之家";
            msg.description="健身私教";
            Bitmap thumBitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
            msg.thumbData = bmpToByteArray(thumBitmap, true);
            SendMessageToWX.Req req=new SendMessageToWX.Req();
            req.transaction = buildTransaction("Req");
            req.message=msg;
            switch (type){
                case 0:
                    req.scene=SendMessageToWX.Req.WXSceneSession;
                    break;
                case 1:
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;
                    break;
            }
            iwxapi.sendReq(req);
            finish();
        }
    }
    private  MdlPositionDetail.DataBean bean;
    @Override
    public void showPositionDetail(MdlBaseHttpResp<MdlPositionDetail> resp) {
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            bean=resp.getData().getData();
            MdlPosition.DataBean dataBean=bean.getPosition();
            tvPositionName.setText(dataBean.getName());
            tvPositionContent.setText(dataBean.getCity()+dataBean.getArea()+" "+dataBean.getWorkYears()+"  "+dataBean.getLowestEducationLevel());
            tvSalary.setText(dataBean.getSalaryMin()+"-"+dataBean.getSalaryMax()+"K");
            tvPositionContent2.setText(dataBean.getContent());
            MdlPositionDetail.DataBean.StoreAdminBean adminBean=bean.getStoreAdmin();
            tvPublisherName.setText(adminBean.getStoreUserName());
            tvPublisherPositon.setText(adminBean.getStoreUserPosition());
            setImg(adminBean.getStoreUserHeadImg(),image);
            MdlPositionDetail.DataBean.StoreInfoBean storeInfoBean= bean.getStoreInfo();
            tvStoreName.setText(storeInfoBean.getCompanyName());
            tvNumber.setText(storeInfoBean.getStaffNum());
            if(null!=storeInfoBean.getCompanyImages()&&storeInfoBean.getCompanyImages().size()>0) {
                setImg(storeInfoBean.getCompanyImages().get(0).getThumbnail(), imageStore);
            }
            if(dataBean.isLike()){
                isLikeFlag=true;
                btnShoucang.setImageDrawable(getResources().getDrawable(R.drawable.collected));
            }else{
                btnShoucang.setImageDrawable(getResources().getDrawable(R.drawable.uncollected));
            }
            llLabelWelfare.removeAllViews();
            if(null!=storeInfoBean.getCompanyBenefits()&&storeInfoBean.getCompanyBenefits().size()>0){
                for (int i = 0; i < storeInfoBean.getCompanyBenefits().size(); i++) {
                    TextView tv = new TextView(this);
                    FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(0,0,30,30);
                    tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.dr_bg_tv_g));
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                    tv.setPadding(30, 14,30,14);
                    tv.setTextColor(getResources().getColor(R.color.text_gray));
                    tv.setText(storeInfoBean.getCompanyBenefits().get(i));
                    tv.setLayoutParams(params);
                    llLabelWelfare.addView(tv);
                }
            }
        llLabelSkill.removeAllViews();
        if(null!=dataBean.getSkillRequired()&&dataBean.getSkillRequired().size()>0){
                for (int i = 0; i < dataBean.getSkillRequired().size(); i++) {
                    TextView tv = new TextView(this);
                    FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(0,0,30,30);
                    tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.dr_bg_tv_g));
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                    tv.setPadding(30, 14,30,14);
                    tv.setTextColor(getResources().getColor(R.color.text_gray));
                    tv.setText(dataBean.getSkillRequired().get(i));
                    tv.setLayoutParams(params);
                    llLabelSkill.addView(tv);
                }
            }
            storeId=storeInfoBean.getId();
            addressTv.setText(storeInfoBean.getStoreAddress());
            String[] latLngStr=dataBean.getCoordinate().split(",");
            if(latLngStr!=null) {
                LatLng latLng = new LatLng(Double.valueOf(latLngStr[1]), Double.valueOf(latLngStr[0]));
                MapStatus mapStatus=new MapStatus.Builder()
                        .target(latLng)
                        .zoom(18)
                        .build();
                MapStatusUpdate mapStatusUpdate= MapStatusUpdateFactory.newMapStatus(mapStatus);
                mBaiduMap.setMapStatus(mapStatusUpdate);
                BitmapDescriptor bitmapDescriptor= BitmapDescriptorFactory.fromResource(R.drawable.ic_address_pointer);
                MarkerOptions markerOptions=new MarkerOptions().icon(bitmapDescriptor).position(latLng);
                mBaiduMap.addOverlay(markerOptions);

            }
        }

    }

    @Override
    public void  showPostionLike(MdlBaseHttpResp<MdlPositionDetail> resp) {
        if(resp.getStatus()==HttpConstant.R_HTTP_OK&&null!=resp.getData()){
            btnShoucang.setImageDrawable(getResources().getDrawable(R.drawable.collected));
            LogAndToastUtil.toast("收藏成功");
            isLikeFlag=true;
        }else{
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

    public void showCancelPositionLike(MdlBaseHttpResp resp) {
        if(resp.getStatus()==HttpConstant.R_HTTP_OK&&null!=resp.getData()){
            btnShoucang.setImageDrawable(getResources().getDrawable(R.drawable.uncollected));
            LogAndToastUtil.toast("取消成功");
            isLikeFlag=false;
        }else{
            LogAndToastUtil.toast(resp.getMsg());
        }

        }
}