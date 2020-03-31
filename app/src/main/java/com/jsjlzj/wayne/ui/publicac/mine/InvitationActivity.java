package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.trainer.InvitationCodeBean;
import com.jsjlzj.wayne.ui.MyApp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.utils.BitmapUtils;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SPUtil;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;

import butterknife.BindView;

import static com.jsjlzj.wayne.utils.Utility.bmpToByteArray;
import static com.jsjlzj.wayne.utils.Utility.buildTransaction;

/**
 * @ClassName: InvitationActivity
 * @Description: 邀请好友界面
 * @Author: 曾海强
 * @CreateDate:
 */
public class InvitationActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    @BindView(R.id.img_code)
    ImageView imgCode;
    @BindView(R.id.tv_invitation)
    TextView tvInvitation;
    private Bitmap bitmap;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, InvitationActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_invitation;
    }

    @Override
    protected void initViewAndControl() {
        initRightTitle("邀请好友", "我的邀请");
        mRightTv.setTextColor(ContextCompat.getColor(this, R.color.color_4F9BFA));
        mRightTv.setTextSize(15);
        mRightTv.setOnClickListener(clickListener);
        tvInvitation.setOnClickListener(clickListener);
        presenter.getInvitationCode();
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.tv_right_btn://我的邀请
                MineInvitationActivity.go2this(this);
                break;
            case R.id.tv_invitation://分享专属海报
                MdlUser.MdlUserBean bean = SPUtil.getUserFromSP();
                share(HttpConstant.WEB_URL_INVITATION_FRIEND + bean.getId(), "邀请好友", "快来加入吧");
                break;
        }
    }


    private void share(String url, String title, String content) {
        if (!MyApp.getApp().getIwxapi().isWXAppInstalled()) {
            LogAndToastUtil.toast("您的设备未安装微信客户端");
        } else {
            WXWebpageObject wxWebpage = new WXWebpageObject();
            wxWebpage.webpageUrl = url;
            WXMediaMessage msg = new WXMediaMessage(wxWebpage);
            msg.title = title;
            msg.description = content;

            if (bitmap == null) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_invitation_code);
            }
            Bitmap thumbData = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            msg.thumbData = bmpToByteArray(thumbData, true);
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("Req");
            req.message = msg;
            req.scene = SendMessageToWX.Req.WXSceneSession;
            MyApp.getApp().getIwxapi().sendReq(req);

        }
    }

    @Override
    public void getInvitationSuccess(MdlBaseHttpResp<InvitationCodeBean> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            String imgUrl =resp.getData().getData().getUrl();
            GlidUtils.setRoundGrid(this,imgUrl,imgCode,4);
            BitmapUtils.getBitmap(this, imgUrl, bitmap -> {
                LogAndToastUtil.log("====bitmap====" + bitmap + "==========" + imgUrl);
                InvitationActivity.this.bitmap = bitmap;
            });
        }

    }
}
