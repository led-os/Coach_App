package com.jsjlzj.wayne.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.entity.trainer.MdlDetailT;
import com.jsjlzj.wayne.ui.store.personal.storeinfo.ImageViewActivity;

import java.util.List;

/**
 * Describe 工作经验
 * Created by Bby on 2019-09-09
 * Email bby0856@163.com
 */
public class TrainerPrePicAdapter extends BaseAdapter {
    private List<String> list;
    private Context mContext;
    private LayoutInflater inflater;

    public TrainerPrePicAdapter(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<String> list) {
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
            convertView = inflater.inflate(R.layout.item_position_prepic, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageViewActivity.go2this((Activity) mContext,position,list);
            }
        });
        Glide.with(mContext).load(list.get(position)).into(viewHolder.iv);
        return convertView;
    }

    static class ViewHolder {
        ImageView iv;

        public ViewHolder(View view) {
            iv = view.findViewById(R.id.iv);
        }
    }
}
