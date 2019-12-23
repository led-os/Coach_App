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
 * Describe 求职意向
 * Created by Bby on 2019-09-09
 * Email bby0856@163.com
 */
public class TrainerHopeAdapter extends BaseAdapter {
    private List<MdlDetailT.DataBean.WorkHopeListBean> list;
    private Context mContext;
    private LayoutInflater inflater;

    public TrainerHopeAdapter(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<MdlDetailT.DataBean.WorkHopeListBean> list) {
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
            convertView = inflater.inflate(R.layout.item_position_hope, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MdlDetailT.DataBean.WorkHopeListBean bean=list.get(position);
        viewHolder.tvPositionName.setText(bean.getPosition()+bean.getProvince()+" "+bean.getCity());
        viewHolder.tvSalary.setText(list.get(position).getSalaryMin() + "-" + list.get(position).getSalaryMax() + "k");

        return convertView;
    }

    static class ViewHolder {
        TextView tvPositionName, tvSalary;

        public ViewHolder(View view) {
            tvPositionName = view.findViewById(R.id.tvPositionName);
            tvSalary = view.findViewById(R.id.tvSalary);
        }
    }
}
