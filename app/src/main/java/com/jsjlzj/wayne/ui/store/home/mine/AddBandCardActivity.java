package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import butterknife.BindView;

/**
 * @ClassName: AddBandCardActivity
 * @Description: 添加银行卡
 * @Author: 曾海强
 * @CreateDate: 2020/05/06
 */
public class AddBandCardActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.et_person)
    EditText etPerson;
    @BindView(R.id.et_bank_card)
    EditText etBankCard;
    @BindView(R.id.et_open)
    EditText etOpen;
    @BindView(R.id.tv_save)
    TextView tvSave;
    /**
     * 0 : 添加   1 :修改
     */
    private int type;


    public static void go2this(Activity activity, int type, int requestCode) {
        Intent intent = new Intent(activity, AddBandCardActivity.class);
        intent.putExtra(ExtraConstant.EXTRA_SHOW_TYPE, type);
        activity.startActivityForResult(intent,requestCode);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_add_band_card;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initViewAndControl() {
        type = getIntent().getIntExtra(ExtraConstant.EXTRA_SHOW_TYPE, 0);
        if (type == 0) {
            initTitle("添加银行卡");
        } else {
            initTitle("修改银行卡");
        }
        tvSave.setOnClickListener(clickListener);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        if(view.getId() == R.id.tv_save){
            if(TextUtils.isEmpty(etPerson.getText().toString())){
                LogAndToastUtil.toast("请输入持卡人姓名");
                return;
            }else if(TextUtils.isEmpty(etBankCard.getText().toString())){
                LogAndToastUtil.toast("请输入银行卡号");
                return;
            }else if(etBankCard.getText().length() < 4){
                LogAndToastUtil.toast("请输入正确的银行卡号");
                return;
            }else if(TextUtils.isEmpty(etOpen.getText().toString())){
                LogAndToastUtil.toast("请输入开户行名称");
                return;
            }

            LogAndToastUtil.toast("保存成功");
            Intent intent = new Intent();
            intent.putExtra("open_name",etOpen.getText().toString());
            intent.putExtra("bank_card",etBankCard.getText().toString());
            setResult(RESULT_OK,intent);
            finish();
        }
    }


}
