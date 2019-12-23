package com.jsjlzj.wayne.ui.store.personal.storeinfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.CalendarBean;
import com.jsjlzj.wayne.entity.store.MdlInterViewDetail;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity;
import com.jsjlzj.wayne.ui.yunxin.YunXingUtil;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.MyGridView;
import com.jsjlzj.wayne.widgets.dialog.InterViewDialog;
import com.jsjlzj.wayne.widgets.dialog.TimeDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//门店发送面试邀请页面
public class InterviewActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {

    private MyGridView gridView;
    private ImageView headImg;
    private TextView tvName;
    private TextView tvAddress,tvPosition;
    private EditText ed_content;
    private TimeDialog dialog;
    private int currentPosition=-1;
    private String interviewTime;
    private String intenviewID = "";//  求职意向id
    private String positionID = "";//  职位ID
    private String intenviewName = "";// 意向职位名称
    private String account = "";// 云信account
    private Map<Object,Object>map;
    public static void go2this(Activity context,  String intenviewID, String intenviewName, String positionID,String account) {
        Intent intent = new Intent(context, InterviewActivity.class);
        intent.putExtra("intenviewID", intenviewID);
        intent.putExtra("intenviewName", intenviewName);
        intent.putExtra("positionID", positionID);
        intent.putExtra("account", account);
        context.startActivity(intent);
    }
    public static void go2this(Activity context) {
        Intent intent = new Intent(context, InterviewActivity.class);
        context.startActivityForResult(intent, RecruitActivity.FLAG_RECRUIT_CONTENT);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_interview;
    }

    //    https://github.com/prolificinteractive/material-calendarview
    @Override
    protected void initViewAndControl() {
        intenviewID = getIntent().getStringExtra("intenviewID");
        positionID = getIntent().getStringExtra("positionID");
        intenviewName = getIntent().getStringExtra("intenviewName");
        account = getIntent().getStringExtra("account");
        gridView = findView(R.id.calendarView);
        headImg = findView(R.id.headImg);
        tvName = findView(R.id.tvName);
        tvAddress = findView(R.id.tvAddress);
        ed_content = findView(R.id.ed_content);
        tvPosition = findView(R.id.tvPosition);
        findView(R.id.btnConfirm).setOnClickListener(clickListener);
        findView(R.id.btnBack).setOnClickListener(clickListener);
        List<CalendarBean> list = new ArrayList<>();
        MyGridViewAdapter adapter = new MyGridViewAdapter(list, this);
        gridView.setAdapter(adapter);
        if(null==map)map=new HashMap<>();
        map.put("positionId",positionID);
        map.put("workHopeId",intenviewID);
        presenter.getInterViewInfo(map);
        Calendar c = Calendar.getInstance();
        //判断当天是星期几
        int weekIndex = c.get(Calendar.DAY_OF_WEEK);
        CalendarBean bean;
        //在当前天填充空数据为了当前天在对应的星期上显示
        for (int i = 1; i < weekIndex; i++) {
            bean = new CalendarBean("", "", false, false);
//                bean.setInterviewDate("");
            list.add(bean);
        }
        //添加当天日期
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        bean = new CalendarBean(sf.format(c.getTime()), "", true, false);
        //获取当前的月份
        int month = c.get(Calendar.MONTH) + 1;
        list.add(bean);
        boolean isNextMonth = false;
        //取当天后的若干天日期
        for (int i = 1; i < 29; i++) {
            c.add(Calendar.DAY_OF_MONTH, 1);
            int monthEnd = c.get(Calendar.MONTH) + 1;
            //如果日期与当前月份不同，则认为是下个月
            if (monthEnd != month) {
                isNextMonth = true;
            } else {
                isNextMonth = false;
            }
            bean = new CalendarBean(sf.format(c.getTime()), "", false, isNextMonth);
            list.add(bean);
        }
        adapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!TextUtils.isEmpty(list.get(position).getInterviewDate())) {
                    dialog = new TimeDialog(InterviewActivity.this, new TimeDialog.ClickListener() {
                        @Override
                        public void clickConfirm(String data) {
                            list.get(position).setInterviewTime(data);
                            list.get(position).setShowTextColor(true);
                            interviewTime=list.get(position).getInterviewDate()+" "+data;
                            if (currentPosition != -1) {
                                list.get(currentPosition).setInterviewTime("");
                                list.get(currentPosition).setShowTextColor(false);
                            }
                            currentPosition = position;
                            adapter.notifyDataSetChanged();
                        }
                    });
                    dialog.show();
                }
            }
        });
    }
    private MyViewClickListener clickListener = new MyViewClickListener();



    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    finish();
                    break;
                case R.id.btnConfirm://发送面试
                    new InterViewDialog(InterviewActivity.this, new InterViewDialog.ClickListener() {
                        @Override
                        public void clickConfirm() {
                            map.clear();
                            map.put("interviewMark",ed_content.getText().toString());
                            map.put("interviewTime",interviewTime);
                            map.put("positionId",positionID);
                            map.put("workHopeId",intenviewID);
                            presenter.sendInterView(map);
                        }
                    }).show();

                    break;
            }
        }
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }
private MdlInterViewDetail.DataBean bean;
    public void showResultgetInterViewInfo(MdlBaseHttpResp<MdlInterViewDetail> resp){
        if(resp.getStatus()== HttpConstant.R_HTTP_OK&&null!=resp.getData()&&null!=resp.getData().getData()){
            bean=resp.getData().getData();
            tvName.setText(bean.getTrainerName());
            setImg(bean.getHeadImg(),headImg);
            tvAddress.setText(bean.getAddress());
            tvPosition.setText(bean.getPositionName());
        }
    }

    class MyGridViewAdapter extends BaseAdapter {
        private List<CalendarBean> list;
        private Context mContext;
        private LayoutInflater inflater;

        public MyGridViewAdapter(List<CalendarBean> list, Context mContext) {
            this.list = list;
            this.mContext = mContext;
            inflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_calendar, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.tvPositionName = convertView.findViewById(R.id.tvPositionName);
                viewHolder.tvPositionTime = convertView.findViewById(R.id.tvPositionTime);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            CalendarBean bean = list.get(position);
            String[] strs = bean.getInterviewDate().split("-");
            viewHolder.tvPositionName.setText(strs[strs.length - 1]);
            if (bean.isToDay()||bean.isNextMonthFlag()||bean.isShowTextColor()) {
                viewHolder.tvPositionTime.setVisibility(View.VISIBLE);
                if(bean.isShowTextColor()) {
                    viewHolder.tvPositionTime.setText(bean.getInterviewTime());
                    viewHolder.tvPositionTime.setTextColor(Color.parseColor("#FECB0E"));
                    viewHolder.tvPositionName.setTextColor(Color.parseColor("#FECB0E"));
                }else if(bean.isNextMonthFlag()) {
                    if (position != 0 && !list.get(position - 1).isNextMonthFlag()) {
                        viewHolder.tvPositionTime.setText(strs[strs.length - 2] + "月");
                        viewHolder.tvPositionName.setTextColor(mContext.getResources().getColor(R.color.text_black));
                        viewHolder.tvPositionTime.setTextColor(mContext.getResources().getColor(R.color.text_black));
                    } else {
                        viewHolder.tvPositionTime.setVisibility(View.INVISIBLE);
                        viewHolder.tvPositionName.setTextColor(mContext.getResources().getColor(R.color.text_black));
                    }
                }else if(bean.isToDay()){
                    viewHolder.tvPositionTime.setText("今天");
                    viewHolder.tvPositionName.setTextColor(mContext.getResources().getColor(R.color.text_black));
                    viewHolder.tvPositionTime.setTextColor(mContext.getResources().getColor(R.color.text_black));

                }
            } else {
                viewHolder.tvPositionTime.setVisibility(View.INVISIBLE);
                viewHolder.tvPositionName.setTextColor(mContext.getResources().getColor(R.color.text_black));
            }
            return convertView;
        }

        class ViewHolder {
            TextView tvPositionName;
            TextView tvPositionTime;
        }
    }

    public void showResultSendInterView(MdlBaseHttpResp resp){
        if(resp.getStatus()==HttpConstant.R_HTTP_OK&&resp.getData()!=null){
            YunXingUtil.sendText(account,"发送了面试邀请",bean.getWorkHopeId(),bean.getPositionId(),bean.getWorkHopeName());
            LogAndToastUtil.toast("面试邀请发送成功");
        }else{
            LogAndToastUtil.toast(resp.getMsg());
        }
    }
}
