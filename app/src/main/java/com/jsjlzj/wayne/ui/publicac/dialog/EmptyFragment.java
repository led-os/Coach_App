package com.jsjlzj.wayne.ui.publicac.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.jsjlzj.wayne.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * description: java类作用描述
 * @author: 曾海强
 * CreateDate: 2019/2/20 16:53
 */

public class EmptyFragment extends DialogFragment {

    @BindView(R.id.img_default)
    ImageView imgDefault;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.tv_add_link)
    TextView tvAddLink;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    /**
     * 0:快速搜索的空界面   1：购物车空界面
     */
    private int type;
    private View.OnClickListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changeUI();
    }

    public void showEmpty() {
        showEmpty(0, null);
    }

    public void showEmpty(int type) {
        showEmpty(type, null);
    }

    public void showEmpty(int type, View.OnClickListener clickListener) {
        this.type = type;
        this.listener = clickListener;
        if (isAdded()) {
            changeUI();
        }
    }

    /**
     * 改变UI
     */
    private void changeUI() {
        if(listener != null){
            tvAddLink.setVisibility(View.VISIBLE);
        }else {
            tvAddLink.setVisibility(View.GONE);
        }
        switch (type) {
            case 0:
                imgDefault.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.icon_seek));
                tvNoData.setText("没有找到相关内容");
                break;
            case 1:
                imgDefault.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.ic_blank_gwc));
                tvNoData.setText("您的购物车还没有商品哦~");
                break;
            case 2:

                break;


            default:
                break;
        }
    }

    @OnClick({R.id.tv_add_link})
    public void onViewClicked(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }
}
