package com.jsjlzj.wayne.ui.publicac.report;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.ImageSelectAdapter;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.ReportPresenter;
import com.jsjlzj.wayne.ui.mvp.home.ReportView;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.SelectImageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName: ReportContentActivity
 * @Description: 举报内容
 * @Author: 曾海强
 * @CreateDate:
 */
public class ReportContentActivity extends MVPBaseActivity<ReportView, ReportPresenter> implements ReportView, ImageSelectAdapter.OnImageClickListener {

    public static final int IMG_SIZE = 3;

    @BindView(R.id.tv_reason)
    TextView tvReason;
    @BindView(R.id.et_desc)
    EditText etDesc;
    @BindView(R.id.rv_report_img)
    RecyclerView rvReportImg;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    private List<String> imgUrls;
    private ImageSelectAdapter adapter;
    private int position;
    private List<String> uploadImages = new ArrayList<>();

    public static void go2this(Activity context, String typeStr) {
        Intent intent = new Intent(context, ReportContentActivity.class);
        intent.putExtra(ExtraConstant.EXTRA_NAME, typeStr);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_report_content;
    }

    @Override
    protected void initViewAndControl() {
        String type = getIntent().getStringExtra(ExtraConstant.EXTRA_NAME);
        initTitle("举报内容");
        initRecycler();
        tvReason.setText(type);
        tvCommit.setOnClickListener(clickListener);
    }

    private void initRecycler() {
        rvReportImg.setLayoutManager(new GridLayoutManager(this, IMG_SIZE));
        imgUrls = new ArrayList<>();
        // 默认的一张空白占位图
        imgUrls.add("");
        adapter = new ImageSelectAdapter(this, imgUrls);
        rvReportImg.setAdapter(adapter);
        adapter.setListener(this);
    }

    @Override
    protected ReportPresenter createPresenter() {
        return new ReportPresenter(this);
    }


    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        if(view.getId() == R.id.tv_commit){
            // TODO: 2020/2/12 提交投诉
            if(TextUtils.isEmpty(etDesc.getText().toString())){
                LogAndToastUtil.toast("请输入举报内容");
            }else {
                LogAndToastUtil.toast("举报成功");
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /**
     * 选择图片
     * @param position 点击的角标
     */
    @Override
    public void onImageClick(int position) {
        presenter.autoObtainStoragePermission(this, position);
    }

    @Override
    public void onRemoveImgClick(int position) {
        // 删除图片
        imgUrls.remove(position);
        if (imgUrls.size() < IMG_SIZE) {
            // 如果最后一张已经是空白图的话不操作，否则添加一张空白图
            if (!imgUrls.get(imgUrls.size() - 1).equals("")) {
                imgUrls.add("");
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void selectPhoto(int position) {
        SelectImageUtils.selectPhoto(this, getString(R.string.takephoto), false, true, 1);
    }

    @Override
    public void onUploadSuccess(String imgUrl, int position) {
        presenter.uploadImage(imgUrl);
        this.position = position;
        if (imgUrls.size() < position) {
            imgUrls.add(imgUrl);
            // 替换图片
        } else if (imgUrls.size() >= position) {
            imgUrls.set(position, imgUrl);
        }
        if (imgUrls.size() < IMG_SIZE) {
            // 如果最后一张已经是空白图的话不操作，否则添加一张空白图
            if (!imgUrls.get(imgUrls.size() - 1).equals("")) {
                imgUrls.add("");
            }
        }
        adapter.notifyDataSetChanged();
    }
}
