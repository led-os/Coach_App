package com.jsjlzj.wayne.adapter.recycler.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: SettledInAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/5/28 22:21
 */
public class SettledInAdapter extends RecyclerView.Adapter<SettledInAdapter.ViewHolder> {



    private Context context;


    public SettledInAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_settled_in, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_money)
        TextView tvMoney;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int pos) {
            switch (pos){
                case 0:
                    tvType.setText("基础版");
                    tvMoney.setText("5000");
                    break;
                case 1:
                    tvType.setText("高级版");
                    tvMoney.setText("10000");
                    break;
                case 2:
                    tvType.setText("尊享版");
                    tvMoney.setText("15000");
                    break;
                case 3:
                    tvType.setText("至尊版");
                    tvMoney.setText("20000");
                    break;
            }
        }
    }

}
