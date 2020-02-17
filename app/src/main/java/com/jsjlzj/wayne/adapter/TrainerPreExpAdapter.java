package com.jsjlzj.wayne.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;

import java.util.List;

/**
 * Describe 工作经验
 * Created by Bby on 2019-09-09
 * Email bby0856@163.com
 */
public class TrainerPreExpAdapter extends BaseAdapter {
    private List<MdlDetailT.DataBean.WorkExperienceListBean> list;
    private Context mContext;
    private LayoutInflater inflater;

    public TrainerPreExpAdapter(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<MdlDetailT.DataBean.WorkExperienceListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return null == list ? 0 : list.size();
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
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_position_exppre, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MdlDetailT.DataBean.WorkExperienceListBean bean=list.get(position);
        viewHolder.tvExpStoreName.setText(bean.getCompanyName()+bean.getPositionType());
        viewHolder.tvExpPosition.removeAllViews();

        if(null!=bean.getSkillTags()&&bean.getSkillTags().size()>0){
            for (int i = 0; i < bean.getSkillTags().size(); i++) {
                TextView tv = new TextView(mContext);
                FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,0,30,30);
                tv.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.dr_bg_tv_g));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                tv.setPadding(20, 10,20,10);
                tv.setTextColor(mContext.getResources().getColor(R.color.color_999999));
                tv.setText(bean.getSkillTags().get(i));
                tv.setLayoutParams(params);
                viewHolder.tvExpPosition.addView(tv);
            }
        }
        viewHolder.tvSalary.setText(list.get(position).getStartDate() + "-" + list.get(position).getEndDate());
        viewHolder.tvExpContent.setText(list.get(position).getWorkContent());

        return convertView;
    }

    static class ViewHolder {
        TextView tvExpStoreName, tvSalary, tvExpContent;
        FlexboxLayout tvExpPosition;

        public ViewHolder(View view) {
            tvExpStoreName = view.findViewById(R.id.tvExpStoreName);
            tvExpPosition = view.findViewById(R.id.tvExpPosition);
            tvSalary = view.findViewById(R.id.tvSalary);
            tvExpContent = view.findViewById(R.id.tvExpContent);
        }
    }
}
