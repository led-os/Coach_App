package com.jsjlzj.wayne.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.jsjlzj.wayne.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName: SelectFenView
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2020/6/23 23:43
 */
public class SelectFenView extends LinearLayout {

    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.img_1)
    ImageView img1;
    @BindView(R.id.img_2)
    ImageView img2;
    @BindView(R.id.img_3)
    ImageView img3;
    @BindView(R.id.img_4)
    ImageView img4;
    @BindView(R.id.img_5)
    ImageView img5;
    private Context context;
    private int curFen;

    public SelectFenView(Context context) {
        this(context, null);
    }

    public SelectFenView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectFenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SelectFenView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        initView();
    }


    public void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.select_fen_view, this);
        ButterKnife.bind(this, view);
    }


    @OnClick({R.id.img_1, R.id.img_2, R.id.img_3, R.id.img_4, R.id.img_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_1:
                img1.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing));
                img2.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing_no));
                img3.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing_no));
                img4.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing_no));
                img5.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing_no));
                listener.onFenClick(1);
                break;
            case R.id.img_2:
                img1.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing));
                img2.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing));
                img3.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing_no));
                img4.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing_no));
                img5.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing_no));
                listener.onFenClick(2);
                break;
            case R.id.img_3:
                img1.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing));
                img2.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing));
                img3.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing));
                img4.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing_no));
                img5.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing_no));
                listener.onFenClick(3);
                break;
            case R.id.img_4:
                img1.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing));
                img2.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing));
                img3.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing));
                img4.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing));
                img5.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing_no));
                listener.onFenClick(4);
                break;
            case R.id.img_5:
                img1.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing));
                img2.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing));
                img3.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing));
                img4.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing));
                img5.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_evaluate_xing));
                if(listener != null){
                    listener.onFenClick(5);
                }
                break;
            default:break;
        }
    }

    public int getCurFen() {
        return curFen;
    }

    public void setFenType(String type,OnFenClickListener listener){
        this.listener = listener;
        tvType.setText(type);
    }

    public OnFenClickListener listener;


    public interface OnFenClickListener{
        void onFenClick(int curFen);
    }
}
