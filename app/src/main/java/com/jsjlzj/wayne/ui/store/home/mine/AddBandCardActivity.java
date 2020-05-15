package com.jsjlzj.wayne.ui.store.home.mine;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.shopping.BankCardBean;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
    private BankCardBean bean;
    private Map<Object,Object> map = new HashMap<>();


    public static void go2this(Activity activity, BankCardBean bankCardBean, int requestCode) {
        Intent intent = new Intent(activity, AddBandCardActivity.class);
        intent.putExtra("bankCardBean",bankCardBean);
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
        bean = (BankCardBean) getIntent().getSerializableExtra("bankCardBean");
        if (bean == null) {
            initTitle("添加银行卡");
        } else {
            initTitle("修改银行卡");
            if(!TextUtils.isEmpty(bean.getUserName())){
                etPerson.setText(bean.getUserName());
                etPerson.setSelection(bean.getUserName().length());
            }
            if(!TextUtils.isEmpty(bean.getCardNo())){
                etBankCard.setText(bean.getCardNo());
            }
            if(!TextUtils.isEmpty(bean.getBankName())){
                etOpen.setText(bean.getBankName());
            }
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
            }else if(etBankCard.getText().length() <= 11){
                LogAndToastUtil.toast("请输入正确的银行卡号");
                return;
            }else if(TextUtils.isEmpty(etOpen.getText().toString())){
                LogAndToastUtil.toast("请输入开户行名称");
                return;
            }

            map.clear();
            if(bean != null){
                map.put("id",bean.getId());
            }
            map.put("userName",etPerson.getText().toString());
            map.put("cardNo",etBankCard.getText().toString());
            map.put("bankName",etOpen.getText().toString());
            presenter.saveBankCard(map);
        }
    }

    @Override
    public void getMessageSuccess(MdlBaseHttpResp<DataBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            LogAndToastUtil.toast("保存成功");
            Intent intent = new Intent();
            intent.putExtra("open_name",etOpen.getText().toString());
            intent.putExtra("bank_card",etBankCard.getText().toString());
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
