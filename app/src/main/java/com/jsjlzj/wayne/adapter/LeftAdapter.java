package com.jsjlzj.wayne.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jsjlzj.wayne.R;

import java.util.List;

public class LeftAdapter extends BaseAdapter {
    private List<String> list;
    private Context mContext;
    private LayoutInflater inflater;
    private int selectItem=0;

    public LeftAdapter(Context mContext,List<String> list) {
        this.mContext = mContext;
        inflater=LayoutInflater.from(mContext);
        this.list=list;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
        notifyDataSetChanged();
    }

    public int getSelectItem() {
        return selectItem;
    }

    @Override
    public int getCount() {
        return null==list?0:list.size();
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
        ViewHolder viewHolder=null;
        if(null==convertView){
            convertView=inflater.inflate(R.layout.item_recruit_type,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tvContent.setText(list.get(position));
        if(selectItem==position){
            viewHolder.tvContent.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            viewHolder.tvContent.setTextColor(mContext.getResources().getColor(R.color.login_btn_l));
        }else{
            viewHolder.tvContent.setBackgroundColor(mContext.getResources().getColor(R.color.bg_color));
            viewHolder.tvContent.setTextColor(mContext.getResources().getColor(R.color.text_gray));
        }
        return convertView;
    }

    static class ViewHolder{
        TextView tvContent;

        public ViewHolder(View view) {
            tvContent=view.findViewById(R.id.tvContent);
        }
    }
}
