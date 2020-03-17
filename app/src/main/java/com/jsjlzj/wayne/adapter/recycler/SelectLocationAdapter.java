package com.jsjlzj.wayne.adapter.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: SelectLocationAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/3/3 14:26
 */
public class SelectLocationAdapter extends RecyclerView.Adapter<SelectLocationAdapter.ViewHolder> {



    private Context context;
    private List<String> list = new ArrayList<>();

    public SelectLocationAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.rel_item)
        RelativeLayout relItem;
        private String bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            bean = list.get(pos);
            tvName.setText(bean);
            relItem.setOnClickListener(clickListener);
        }


        public MyViewClickListener clickListener = new ViewHolder.MyViewClickListener();


        private class MyViewClickListener extends OnMultiClickListener {
            @Override
            public void OnMultiClick(View view) {
                if (listener == null) {
                    return;
                }
                switch (view.getId()) {
                    case R.id.rel_item:
                        listener.onItemClick(bean);
                        break;
                    default:
                        break;
                }
            }
        }

    }


    public interface OnItemClickListener {
        void onItemClick(String string);


    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
