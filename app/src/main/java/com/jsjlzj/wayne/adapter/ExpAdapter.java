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
 * Describe 工作经验
 * Created by Bby on 2019-09-09
 * Email bby0856@163.com
 */
public class ExpAdapter extends BaseAdapter {
    private List<MdlDetailT.DataBean.WorkExperienceListBean> list;
    private Context mContext;
    private LayoutInflater inflater;

    public ExpAdapter(Context mContext) {
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
            convertView = inflater.inflate(R.layout.item_position_exp_new, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvExpStoreName.setText(list.get(position).getCompanyName());
//        viewHolder.tvExpPosition.setText(list.get(position).getPositionType());
//        viewHolder.tvSalary.setText(list.get(position).getStartDate() + "-" + list.get(position).getEndDate());
//        viewHolder.tvExpContent.setText(list.get(position).getWorkContent());

        return convertView;
    }

    static class ViewHolder {
        TextView tvExpStoreName,tvExpPosition, tvSalary, tvExpContent;

        public ViewHolder(View view) {
            tvExpStoreName = view.findViewById(R.id.tvExpStoreName);
//            tvExpPosition = view.findViewById(R.id.tvExpPosition);
//            tvSalary = view.findViewById(R.id.tvSalary);
//            tvExpContent = view.findViewById(R.id.tvExpContent);
        }
    }
}
