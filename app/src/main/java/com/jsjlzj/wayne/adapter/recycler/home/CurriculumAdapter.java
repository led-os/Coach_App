package com.jsjlzj.wayne.adapter.recycler.home;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

/**
 * @ClassName: CurriculumAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/1/16 17:08
 */
public class CurriculumAdapter extends RecyclerView.Adapter<CurriculumAdapter.OneViewHolder> {


    @NonNull
    @Override
    public CurriculumAdapter.OneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull CurriculumAdapter.OneViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class OneViewHolder extends RecyclerView.ViewHolder {

        public OneViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
