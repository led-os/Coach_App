package com.jsjlzj.wayne.ui.store.talent.position.recruit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.donkingliang.labels.LabelsView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlSkillRequired;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.widgets.dialog.EditDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity.FLAG_RECRUIT_RECRUITSKILL;
import static com.jsjlzj.wayne.utils.ListCopyUtil.deepCopy;

/**
 * 招聘  技能要求
 */
public class RecruitSkillActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {

    public static void go2this(Activity context, List<String>list) {
        Intent intent = new Intent(context, RecruitSkillActivity.class);
        intent.putExtra("list", (Serializable) list);
        context.startActivityForResult(intent,FLAG_RECRUIT_RECRUITSKILL);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_recruit_skill;
    }



    private LabelsView labelsView;
    private ArrayList<String> label;
    private ArrayList<String> list;
    private List<Integer> intList;
    private List<String> listAddDefine = null;

    @Override
    protected void initViewAndControl() {
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnKeep).setOnClickListener(clickListener);
        findView(R.id.btnAdd).setOnClickListener(clickListener);

        initRecycle();
    }

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    private void initRecycle() {
        //https://www.jianshu.com/p/c574b763e264
        labelsView = findView(R.id.labRecruitSkill);
        list= (ArrayList<String>) getIntent().getSerializableExtra("list");
        if(null==label) label= new ArrayList<>();
        if(null==list) list= new ArrayList<>();
        intList = new ArrayList<>();
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
        presenter.getSystemSkillRequired(null);
    }

    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnAdd://自定义
                    showEditDialog();
                    break;
                case R.id.btnKeep://保存
                    Intent intent=new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list",list);
                    intent.putExtra("bundle",bundle);
//                    intent.putExtra("list",list);
                    setResult(101,intent);
                    finish();
                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
            }
        }
    }
    public void setSelected() {
        if (null != list && list.size() > 0) {
            for (int j = 0; j < list.size(); j++) {
                if (!label.contains(list.get(j))) {
                    label.add(list.get(j));
                }
                for (int i = 0; i < label.size(); i++) {
                    if (label.get(i).equals(list.get(j)) && !label.contains(i)) {
                        intList.add(i);
                    }

                }
            }
        }
        setLabel(label);
        if (null != intList && intList.size() > 0) {
            labelsView.setSelects(intList);
        }
    }
    public void showSkillRequired(MdlBaseHttpResp<MdlSkillRequired> resp) {
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            for (int i = 0; i < resp.getData().getData().size(); i++) {
            label.add(resp.getData().getData().get(i).getName());
            }
            setSelected();
        }
    }

    public void setLabel(List<String> label){
        labelsView.setLabels(label, new LabelsView.LabelTextProvider<String>() {
            @Override
            public CharSequence getLabelText(TextView label, int position, String data) {
                //根据data和position返回label需要显示的数据。
                return data;
            }
        });
    }

    private void showEditDialog() {
        new EditDialog(this, "请输入技能要求", new EditDialog.ClickListener() {
            @Override
            public void clickConfirm(String s) {
                if (!TextUtils.isEmpty(s)) {
                    label.add(s);
                    list.add(s);
                    listAddDefine=deepCopy(list);
                    setLabel(label);
                    if (null != listAddDefine && listAddDefine.size() > 0) {
                        for (int j = 0; j < listAddDefine.size(); j++) {
                            for (int i = 0; i < label.size(); i++) {
                                if (label.get(i).equals(listAddDefine.get(j)) && !intList.contains(i)) {
                                    intList.add(i);
                                }

                            }
                        }
                    }
                    intList.add(label.size() - 1);

                    labelsView.setSelects(intList);
                }
            }

            @Override
            public void clickCancel() {

            }
        }).show();
    }
}