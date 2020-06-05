package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.back_img)
    ImageView imgBack;
    @BindView(R.id.rel_friend)
    RelativeLayout relFriend;
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
        mRightTv.setTextColor(ContextCompat.getColor(this, R.color.color_222222));
        mRightTv.setTextSize(15);
        mRightTv.setOnClickListener(clickListener);
        tvInvitation.setOnClickListener(clickListener);
        tvSave.setOnClickListener(clickListener);
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
            case R.id.tv_save://保存专项海报
                saveBitmap(relFriend,"fyyd_invitation");
                break;
            default:break;
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
            String url =resp.getData().getData().getUrl();
            String imgUrl = resp.getData().getData().getImgUrl();
            GlidUtils.setGrid(this,imgUrl,imgBack);
            GlidUtils.setRoundGrid(this,url,imgCode,4);
//            bitmap = Bitmap.createBitmap(relFriend.getWidth(), relFriend.getHeight(),
//                    Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(bitmap);
//            relFriend.draw(canvas);
            BitmapUtils.getBitmap(this, url, bitmap -> {
                LogAndToastUtil.log("====bitmap====" + bitmap + "==========" + imgUrl);
                InvitationActivity.this.bitmap = bitmap;
            });
        }
    }


    public void saveBitmap(View v, String name) {
        String fileName = name + ".png";
        Bitmap bitmap = Bitmap.createBitmap(relFriend.getWidth(), relFriend.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        String TAG = "TIKTOK";
        LogAndToastUtil.log(TAG, "保存图片");
        File f = new File( Environment.getExternalStorageDirectory().getAbsolutePath() , fileName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            LogAndToastUtil.toast("图片保存至"+ Environment.getExternalStorageDirectory().getAbsolutePath() + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
