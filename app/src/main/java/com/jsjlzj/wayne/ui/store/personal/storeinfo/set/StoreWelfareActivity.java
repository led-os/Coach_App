package com.jsjlzj.wayne.ui.store.personal.storeinfo.set;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.donkingliang.labels.LabelsView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlBenefits;
import com.jsjlzj.wayne.entity.store.MdlStoreInfo;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.dialog.EditDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jsjlzj.wayne.utils.ListCopyUtil.deepCopy;

/**
 * 公司福利
 */
public class StoreWelfareActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {
    private List<String> list = null;
    private List<String> listAddDefine = null;
    private List<Integer> intList;

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    public static void go2this(Activity context, List<String> list) {
        Intent intent = new Intent(context, StoreWelfareActivity.class);
        intent.putExtra("isResult", (Serializable) list);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_store_info_welfare;
    }

    private LabelsView labelsView;
    private ArrayList<String> labels;

    @Override
    protected void initViewAndControl() {
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnKeep).setOnClickListener(clickListener);
        findView(R.id.btnAdd).setOnClickListener(clickListener);
        initRecycle();
        intList = new ArrayList<>();
    }

    private void initRecycle() {
        //https://www.jianshu.com/p/c574b763e264
        labelsView = findView(R.id.labRecruitSkill);
        labels = new ArrayList<>();
        list = (List<String>) getIntent().getSerializableExtra("isResult");
        if (null == list) list = new ArrayList<>();
        if (null == listAddDefine) listAddDefine = new ArrayList<>();
        //标签的点击监听
        labelsView.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                //label是被点击的标签，data是标签所对应的数据，position是标签的位置。
            }
        });
        //标签的选中监听
        labelsView.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
            @Override
            public void onLabelSelectChange(TextView label, Object data, boolean isSelect, int position) {
                //label是被选中的标签，data是标签所对应的数据，isSelect是是否选中，position是标签的位置。
                if (isSelect) {
                    if (!list.contains(data)) {
                        list.add((String) data);
                    }
                    if (!intList.contains(position)) {
                        intList.add(position);
                    }
                } else {
                    if (list.contains(data)) {
                        list.remove(data);
                    }
                    if (intList.contains(position)) {
                        intList.remove(Integer.valueOf(position));
                    }
                }
            }
        });
        presenter.getSystemCompanyBenefits(null);

    }

    private void setLabel(ArrayList labels) {
        labelsView.setLabels(labels, new LabelsView.LabelTextProvider<String>() {
            @Override
            public CharSequence getLabelText(TextView label, int position, String data) {
                //根据data和position返回label需要显示的数据。
                return data;
            }
        });
    }

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnAdd://自定义
                    showEditDialog();
                    break;
                case R.id.btnKeep://存草稿箱
                    submit();
                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
            }
        }
    }

    Map<Object, Object> map = null;

    private void submit() {
        if (null == map) map = new HashMap<>();
        list=labelsView.getSelectLabelDatas();
        map.put("companyBenefits", list);
        presenter.saveCompanyBenefits(map);
    }

    public void setSelected() {
        if (null != list && list.size() > 0) {
            for (int j = 0; j < list.size(); j++) {
                if (!labels.contains(list.get(j))) {
                    labels.add(list.get(j));
                }
                for (int i = 0; i < labels.size(); i++) {
                    if (labels.get(i).equals(list.get(j)) && !intList.contains(i)) {
                        intList.add(i);
                    }

                }
            }
        }
        setLabel(labels);
        if (null != intList && intList.size() > 0) {
            labelsView.setSelects(intList);
        }
    }

    @Override
    public void showBenefits(MdlBaseHttpResp<MdlBenefits> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData() && null != resp.getData().getData()) {
            for (int i = 0; i < resp.getData().getData().size(); i++) {
                if (null != resp.getData().getData().get(i)) {
                    labels.add(resp.getData().getData().get(i).getName());
                }
            }
            setSelected();

        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }

    @Override
    public void showSaveCompanyBenefits(MdlBaseHttpResp<MdlStoreInfo> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            LogAndToastUtil.toast(this, "保存成功");
            finish();
        } else {
            LogAndToastUtil.toast(this, resp.getMsg());
        }
    }




    private void showEditDialog() {
        new EditDialog(this, "请输入福利", new EditDialog.ClickListener() {
            @Override
            public void clickConfirm(String s) {
                if (!TextUtils.isEmpty(s)) {
                    labels.add(s);
                    list.add(s);
                    listAddDefine=deepCopy(list);
                    setLabel(labels);
                    if (null != listAddDefine && listAddDefine.size() > 0) {
                        for (int j = 0; j < listAddDefine.size(); j++) {
                            for (int i = 0; i < labels.size(); i++) {
                                if (labels.get(i).equals(listAddDefine.get(j)) && !intList.contains(i)) {
                                    intList.add(i);
                                }

                            }
                        }
                    }
                    intList.add(labels.size() - 1);

                    labelsView.setSelects(intList);
                }
            }

            @Override
            public void clickCancel() {

            }
        }).show();
    }


}