package com.jsjlzj.wayne.ui.trainer.personal;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.ShoppingNumBean;
import com.jsjlzj.wayne.entity.store.MdlInfo;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseFragment;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.publicac.about.AboutUsActivity;
import com.jsjlzj.wayne.ui.publicac.dialog.ZanFragment;
import com.jsjlzj.wayne.ui.publicac.help.HelpActivity;
import com.jsjlzj.wayne.ui.publicac.mine.InvitationActivity;
import com.jsjlzj.wayne.ui.publicac.mine.MineDynamicActivity;
import com.jsjlzj.wayne.ui.publicac.mine.MineFansActivity;
import com.jsjlzj.wayne.ui.publicac.mine.MineFavoriteActivity;
import com.jsjlzj.wayne.ui.publicac.mine.MineSignUpActivity;
import com.jsjlzj.wayne.ui.publicac.mine.MineStudyActivity;
import com.jsjlzj.wayne.ui.publicac.mine.PersonMineActivity;
import com.jsjlzj.wayne.ui.store.home.mine.CouponActivity;
import com.jsjlzj.wayne.ui.store.home.mine.CurrencyActivity;
import com.jsjlzj.wayne.ui.store.home.mine.LeaderDesActivity;
import com.jsjlzj.wayne.ui.store.home.mine.MessageConnectActivity;
import com.jsjlzj.wayne.ui.store.home.mine.MineOrderActivity;
import com.jsjlzj.wayne.ui.store.home.mine.MineProfitActivity;
import com.jsjlzj.wayne.ui.store.personal.set.SetingActivity;
import com.jsjlzj.wayne.ui.store.shopping.LocationManagerActivity;
import com.jsjlzj.wayne.ui.store.shopping.ShoppingCartActivity;
import com.jsjlzj.wayne.ui.trainer.personal.set.CollectStoresActivity;
import com.jsjlzj.wayne.ui.trainer.personal.set.ConnectPositionListActivity;
import com.jsjlzj.wayne.ui.trainer.personal.set.InterviewPositionListActivity;
import com.jsjlzj.wayne.ui.trainer.publicac.JobIntentionActivity;
import com.jsjlzj.wayne.utils.DateUtil;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import butterknife.BindView;

/**
 * @ClassName: TabItemTrainerMineFragment
 * @Description: 教练端我的
 * @Author: 曾海强
 * @CreateDate: 2020/05/05
 */
public class TabItemTrainerMineFragment extends MVPBaseFragment<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


    @BindView(R.id.img_set)
    ImageView imgSet;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.img_level)
    ImageView imgLevel;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_invitation_code)
    TextView tvInvitationCode;
    @BindView(R.id.tv_copy)
    TextView tvCopy;
    @BindView(R.id.rel_user_info)
    RelativeLayout relUserInfo;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_commit_money)
    TextView tvCommitMoney;
    @BindView(R.id.img_right_money)
    ImageView imgRightMoney;
    @BindView(R.id.rel_vip)
    RelativeLayout relVip;
    @BindView(R.id.rel_sign_in)
    RelativeLayout relSignIn;
    @BindView(R.id.rel_discount)
    RelativeLayout relDiscount;
    @BindView(R.id.rel_integral)
    RelativeLayout relIntegral;
    @BindView(R.id.img_all_order)
    ImageView imgAllOrder;
    @BindView(R.id.tv_all_order)
    TextView tvAllOrder;
    @BindView(R.id.ll_dzf)
    LinearLayout llDzf;
    @BindView(R.id.ll_dfh)
    LinearLayout llDfh;
    @BindView(R.id.ll_dsh)
    LinearLayout llDsh;
    @BindView(R.id.ll_dpj)
    LinearLayout llDpj;
    @BindView(R.id.ll_thsh)
    LinearLayout llThsh;
    @BindView(R.id.ll_gtg)
    LinearLayout llGtg;
    @BindView(R.id.ll_dms)
    LinearLayout llDms;
    @BindView(R.id.ll_drk)
    LinearLayout llDrk;
    @BindView(R.id.ll_qzgj)
    LinearLayout llQzgj;
    @BindView(R.id.ll_zwsc)
    LinearLayout llZwsc;
    @BindView(R.id.ll_kcb)
    LinearLayout llKcb;
    @BindView(R.id.ll_lljl)
    LinearLayout llLljl;
    @BindView(R.id.ll_wdbm)
    LinearLayout llWdbm;
    @BindView(R.id.ll_cwtz)
    LinearLayout llCwtz;
    @BindView(R.id.ll_cjrz)
    LinearLayout llCjrz;
    @BindView(R.id.ll_rwzx)
    LinearLayout llRwzx;

    @BindView(R.id.ll_shdz)
    LinearLayout llShdz;
    @BindView(R.id.ll_gzjlb)
    LinearLayout llGzjlb;
    @BindView(R.id.ll_wdxx)
    LinearLayout llWdxx;
    @BindView(R.id.ll_yqhy)
    LinearLayout llYqhy;
    @BindView(R.id.rel_shopping_cart)
    RelativeLayout relShoppingCart;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_dynamic)
    TextView tvDynamic;
    @BindView(R.id.ll_dynamic)
    LinearLayout llDynamic;
    @BindView(R.id.tv_fen)
    TextView tvFen;
    @BindView(R.id.ll_fen)
    LinearLayout llFen;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.ll_follow)
    LinearLayout llFollow;
    @BindView(R.id.tv_zan)
    TextView tvZan;
    @BindView(R.id.ll_zan)
    LinearLayout llZan;
    @BindView(R.id.tv_favorite)
    TextView tvFavorite;
    @BindView(R.id.ll_favorite)
    LinearLayout llFavorite;
    @BindView(R.id.ll_gywm)
    LinearLayout llGywm;
    @BindView(R.id.ll_bzyfk)
    LinearLayout llBzyfk;
    @BindView(R.id.ll_yhxy)
    LinearLayout llYhxy;
    private MdlInfo.DataBean bean;

    public TabItemTrainerMineFragment() {
    }


    public static Fragment getInstance() {
        TabItemTrainerMineFragment fragment = new TabItemTrainerMineFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_tab_item_trainer_mine;
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.myselfT(null);
        presenter.getShoppingNum();
    }

    @Override
    protected void initViewAndControl(View view) {
        imgSet.setOnClickListener(clickListener);
        imgMessage.setOnClickListener(clickListener);
        relUserInfo.setOnClickListener(clickListener);
        tvCopy.setOnClickListener(clickListener);
        llDynamic.setOnClickListener(clickListener);
        llFen.setOnClickListener(clickListener);
        llFollow.setOnClickListener(clickListener);
        llZan.setOnClickListener(clickListener);
        llFavorite.setOnClickListener(clickListener);
        tvCommitMoney.setOnClickListener(clickListener);
        imgRightMoney.setOnClickListener(clickListener);
        relVip.setOnClickListener(clickListener);
        relSignIn.setOnClickListener(clickListener);
        relDiscount.setOnClickListener(clickListener);
        relIntegral.setOnClickListener(clickListener);
        tvAllOrder.setOnClickListener(clickListener);
        imgAllOrder.setOnClickListener(clickListener);
        llDzf.setOnClickListener(clickListener);
        llDsh.setOnClickListener(clickListener);
        llDfh.setOnClickListener(clickListener);
        llDpj.setOnClickListener(clickListener);
        llThsh.setOnClickListener(clickListener);
        llGtg.setOnClickListener(clickListener);
        llDms.setOnClickListener(clickListener);
        llDrk.setOnClickListener(clickListener);
        llQzgj.setOnClickListener(clickListener);
        llZwsc.setOnClickListener(clickListener);
        llKcb.setOnClickListener(clickListener);
        llLljl.setOnClickListener(clickListener);
        llWdbm.setOnClickListener(clickListener);
        llCwtz.setOnClickListener(clickListener);
        llCjrz.setOnClickListener(clickListener);
        llRwzx.setOnClickListener(clickListener);

        llShdz.setOnClickListener(clickListener);
        llGzjlb.setOnClickListener(clickListener);
        llWdxx.setOnClickListener(clickListener);
        llYqhy.setOnClickListener(clickListener);
        relShoppingCart.setOnClickListener(clickListener);
        llGywm.setOnClickListener(clickListener);
        llBzyfk.setOnClickListener(clickListener);
        llYhxy.setOnClickListener(clickListener);
    }

    @Override
    protected void fragment2Front() {
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.img_set://设置
                SetingActivity.go2this(getActivity());
                break;
            case R.id.img_message:
                //我的消息
                MessageConnectActivity.go2this(getActivity());
                break;
            case R.id.rel_user_info:
                PersonMineActivity.go2this(getActivity(), bean);
                break;
            //复制邀请码到剪切板
            case R.id.tv_copy:
                copyInfo(tvInvitationCode.getText().toString());
                break;
            /**  是团长 提现   如果用户不是团长 ，这里是成为团长， 余额为0*/
            case R.id.tv_commit_money:
            case R.id.img_right_money:
                //不是团长
                if(bean.getRegimentalLevel() == 0){
                    if(bean.getRegimentalAuditStatus() == 0 || bean.getRegimentalAuditStatus() == 2){
                        LeaderDesActivity.go2this(getActivity());
                    }else if(bean.getRegimentalAuditStatus() == 1){
                        LogAndToastUtil.toast("审核中。。。");
                    }else if(bean.getRegimentalAuditStatus() == 3){
                        LogAndToastUtil.toast("您已成为团长了");
                    }
                }else {
                    MineProfitActivity.go2this(getActivity());
                }
                break;
            case R.id.rel_vip://会员中心
                WebViewContainerActivity.go2this(getActivity(),"会员中心",HttpConstant.WEB_URL_NEW_MEMBER_CENTER,
                        WebViewContainerFragment.TYPE_NEW_MEMBER_CENTER);
                break;
            case R.id.rel_sign_in://每日签到
                WebViewContainerActivity.go2this(getActivity(),"每日签到",HttpConstant.WEB_URL_NEW_DAY_SIGN,
                        WebViewContainerFragment.TYPE_NEW_DAY_SIGN);
                break;
            case R.id.rel_discount://优惠券
                CouponActivity.go2this(getActivity(),1111);
                break;
            case R.id.rel_integral://积分商城
                WebViewContainerActivity.go2this(getActivity(),"积分商城",HttpConstant.WEB_URL_NEW_ESHOP,
                        WebViewContainerFragment.TYPE_NEW_DAY_SIGN);
                break;
            case R.id.tv_all_order://全部订单
            case R.id.img_all_order://全部订单
                MineOrderActivity.go2this(getActivity(),0);
                break;
            case R.id.ll_dzf://待支付
                MineOrderActivity.go2this(getActivity(),1);
                break;
            case R.id.ll_dfh://待发货
                MineOrderActivity.go2this(getActivity(),2);
                break;
            case R.id.ll_dsh://待收货
                MineOrderActivity.go2this(getActivity(),3);
                break;
            case R.id.ll_dpj://待评价
                MineOrderActivity.go2this(getActivity(),4);
                break;
            case R.id.ll_thsh://退货售后
                MineOrderActivity.go2this(getActivity(),5);
                break;
            case R.id.ll_gtg://沟通过
                ConnectPositionListActivity.go2this(getActivity());
                break;
            case R.id.ll_dms://待面试
                InterviewPositionListActivity.go2this(getActivity());
                break;
            case R.id.ll_drk://达人卡
                MasterCardActivity.go2this(getActivity());
                break;
            case R.id.ll_qzgj://求职管理
                JobIntentionActivity.go2this(getActivity());
                break;
            case R.id.ll_zwsc://职位收藏
                ConnectPositionListActivity.go2this2(getActivity());
                break;
            case R.id.ll_kcb://蜂隐币
                CurrencyActivity.go2this(getActivity());
                break;
            case R.id.ll_lljl://浏览记录
                break;
            case R.id.ll_wdbm://我的报名
                MineSignUpActivity.go2this(getActivity());
                break;
            case R.id.ll_cwtz://成为团长
                if(bean.getRegimentalAuditStatus() == 0 || bean.getRegimentalAuditStatus() == 2){
                    LeaderDesActivity.go2this(getActivity());
                }else if(bean.getRegimentalAuditStatus() == 1){
                    LogAndToastUtil.toast("审核中。。。");
                }else if(bean.getRegimentalAuditStatus() == 3){
                    LogAndToastUtil.toast("您已成为团长了");
                }
                break;
            case R.id.ll_cjrz://厂家入驻
                break;
            case R.id.ll_rwzx://任务中心
                break;

            case R.id.ll_shdz://收货地址
                LocationManagerActivity.go2this(getActivity(),0,0);
                break;
            case R.id.ll_gzjlb://关注的俱乐部
                CollectStoresActivity.go2this(getActivity());
                break;
            case R.id.ll_wdxx://我的学习
                MineStudyActivity.go2this(getActivity());
                break;
            case R.id.ll_yqhy://邀请好友
                InvitationActivity.go2this(getActivity());
                break;
            case R.id.rel_shopping_cart://购物车
                ShoppingCartActivity.go2this(getActivity());
                break;


            case R.id.ll_dynamic://动态
                MineDynamicActivity.go2this(getActivity());
                break;
            case R.id.ll_fen://粉丝
                MineFansActivity.go2this(getActivity(), 0);
                break;
            case R.id.ll_follow://关注
                MineFansActivity.go2this(getActivity(), 1);
                break;
            case R.id.ll_zan://获赞
                ZanFragment.showDialog(getChildFragmentManager(), tvName.getText().toString(), "共获得" + DateUtil.getNumByInteger(bean.getLikeCount()) + "个赞");
                break;
            case R.id.ll_favorite://收藏
                MineFavoriteActivity.go2this(getActivity());
                break;
            case R.id.ll_gywm://关于我们
                AboutUsActivity.go2this(getActivity());
                break;
            case R.id.ll_yhxy://用户协议
                WebViewContainerActivity.go2this(getActivity(), getString(R.string.user_argument),
                        HttpConstant.WEB_URL_PRIVATE_POLICY, WebViewContainerFragment.TYPE_PRIVATE_POLICY);
                break;
            case R.id.ll_bzyfk://帮助
                HelpActivity.go2this(getActivity());
                break;
            default:
                break;

        }
    }

    private void copyInfo(String copyStr) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", copyStr);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
        LogAndToastUtil.toast("复制成功");
    }

    @Override
    public void myselfT(MdlBaseHttpResp<MdlInfo> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            bean = resp.getData().getData();
            if(bean.getLevel() == 2){
                imgLevel.setVisibility(View.VISIBLE);
            }else {
                imgLevel.setVisibility(View.GONE);
            }
            if(bean.getRegimentalLevel() == 0){
                tvCommitMoney.setText("成为团长");
            }else {
                tvCommitMoney.setText("提现");
            }
            tvInvitationCode.setText(bean.getUserId());
            tvMoney.setText(bean.getWithdrawableAmount());
            tvFavorite.setText(DateUtil.getNumByInteger(bean.getCollectCount()));
            tvName.setText(bean.getName());
            tvDynamic.setText(DateUtil.getNumByInteger(bean.getPublishCount()));
            tvFen.setText(DateUtil.getNumByInteger(bean.getFensCount()));
            tvFollow.setText(DateUtil.getNumByInteger(bean.getFollowerCount()));
            tvZan.setText(DateUtil.getNumByInteger(bean.getLikeCount()));
            GlidUtils.setCircleGrid(getActivity(), bean.getHeadImg(), imgHead);
        }
    }


    @Override
    public void getShoppingNumSuccess(MdlBaseHttpResp<ShoppingNumBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            tvNumber.setText(resp.getData().getData()+"");
        }
    }

}
