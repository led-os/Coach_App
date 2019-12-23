package com.jsjlzj.wayne.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;

import java.util.List;

/**
 * Describe 教育经历
 * Created by Bby on 2019-09-09
 * Email bby0856@163.com
 */
public class EducationAdapter extends BaseAdapter {
    private List<MdlDetailT.DataBean.EducationExperienceListBean> list;
    private Context mContext;
    private LayoutInflater inflater;

    public EducationAdapter(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<MdlDetailT.DataBean.EducationExperienceListBean> list) {
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
            convertView = inflater.inflate(R.layout.item_position_education, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvEducationSchoolName.setText(list.get(position).getSchoolName());
        viewHolder.tvEducationTime.setText(list.get(position).getMajor());
        viewHolder.tvEducationMajor.setText(list.get(position).getStartDate() + "-" + list.get(position).getEndDate());

        return convertView;
    }

    static class ViewHolder {
        TextView tvEducationSchoolName, tvEducationMajor, tvEducationTime;

        public ViewHolder(View view) {
            tvEducationSchoolName = view.findViewById(R.id.tvEducationSchoolName);
            tvEducationMajor = view.findViewById(R.id.tvEducationMajor);
            tvEducationTime = view.findViewById(R.id.tvEducationTime);
        }
    }
}
