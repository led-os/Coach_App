package com.jsjlzj.wayne.ui.publicac.mine;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.SelectImageUtils;

import butterknife.BindView;

/**
 * @ClassName: FillInInfoActivity
 * @Description: 填写认证信息
 * @Author: 曾海强
 * @CreateDate:
 */
public class FillInInfoActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_authentication)
    TextView edAuthentication;
    @BindView(R.id.ed_code)
    EditText edCode;
    @BindView(R.id.ed_select_direction)
    TextView edSelectDirection;
    @BindView(R.id.img_select)
    ImageView imgSelect;
    @BindView(R.id.rel_zyrz)
    RelativeLayout relZyrz;
    @BindView(R.id.rel_scrx)
    RelativeLayout relScrx;
    @BindView(R.id.rel_scgh)
    RelativeLayout relScgh;
    @BindView(R.id.tv_apply)
    TextView tvApply;
    @BindView(R.id.img_zyrz)
    ImageView imgZyrz;
    @BindView(R.id.img_scrx)
    ImageView imgScrx;
    @BindView(R.id.img_scgh)
    ImageView imgScgh;

    public static void go2this(Activity context) {
        Intent intent = new Intent(context, FillInInfoActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_fill_in_info;
    }

    @Override
    protected void initViewAndControl() {
        initTitle("教练认证");

        edSelectDirection.setOnClickListener(clickListener);
        imgSelect.setOnClickListener(clickListener);
        relScgh.setOnClickListener(clickListener);
        relScrx.setOnClickListener(clickListener);
        relZyrz.setOnClickListener(clickListener);
        tvApply.setOnClickListener(clickListener);
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.ed_select_direction:
            case R.id.img_select:
                break;
            case R.id.rel_zyrz://职业证书材料
                presenter.autoObtainStoragePermission(FillInInfoActivity.this, 0);
                break;
            case R.id.rel_scrx://手持人像
                presenter.autoObtainStoragePermission(FillInInfoActivity.this, 1);
                break;
            case R.id.rel_scgh://手持国徽
                presenter.autoObtainStoragePermission(FillInInfoActivity.this, 2);
                break;
            case R.id.tv_apply://去申请
                break;
        }
    }


    @Override
    public void selectPhoto(int position) {
        SelectImageUtils.selectPhoto(this, getString(R.string.takephoto), false, true, 1);
    }

    @Override
    public void onUploadSuccess(String imgUrl, int position) {
//        presenter.uploadImage(imgUrl);
        if (position == 0) {
            GlidUtils.setRoundGrid(this, imgUrl,imgZyrz,4 );
        } else if(position == 1){
            GlidUtils.setRoundGrid(this, imgUrl,imgScrx,4 );
        } else {
            GlidUtils.setRoundGrid(this, imgUrl,imgScgh,4 );
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(this,requestCode,resultCode,data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
