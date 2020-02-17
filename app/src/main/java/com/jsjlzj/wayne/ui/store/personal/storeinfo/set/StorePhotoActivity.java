package com.jsjlzj.wayne.ui.store.personal.storeinfo.set;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.constant.MyPermissionConstant;
import com.jsjlzj.wayne.data.http.HttpDataBasis;
import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.MdlStoreInfo;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalentpersonal.TalentPersonalView;
import com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 照片
 */
public class StorePhotoActivity extends MVPBaseActivity<TalentPersonalView, TalentPersonalPresenter> implements TalentPersonalView {

    @Override
    protected TalentPersonalPresenter createPresenter() {
        return new TalentPersonalPresenter(this);
    }

    public static void go2this(Activity context, List<MdlStoreInfo.DataBean.CompanyImagesBean> list) {
        Intent intent = new Intent(context, StorePhotoActivity.class);
        intent.putExtra("list", (Serializable) list);
        context.startActivityForResult(intent, RecruitActivity.FLAG_RECRUIT_CONTENT);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_store_info_photo;
    }


    private MyRecyclerView<MdlStoreInfo.DataBean.CompanyImagesBean> gridView;
    private MyRecyclerAdapter<MdlStoreInfo.DataBean.CompanyImagesBean> adapter;
    private List<MdlStoreInfo.DataBean.CompanyImagesBean> inPathList;//显示list
    private List<MdlStoreInfo.DataBean.CompanyImagesBean> list;//组合
    private List<String> uploadList;
    private List<String> uploadListThumbnail;
    private List<String> uploadListTotal;


    @Override
    protected void initViewAndControl() {
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnKeep).setOnClickListener(clickListener);
        gridView = findView(R.id.rvPhone);
        uploadList = new ArrayList<>();
        uploadListThumbnail = new ArrayList<>();
        uploadListTotal = new ArrayList<>();
        list = new ArrayList<>();
        initRecycle();
    }

    Map<Object, Object> map = null;
    private MyViewClickListener clickListener = new MyViewClickListener();
    private MdlStoreInfo.DataBean.CompanyImagesBean bean;

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            if (null == map) map = new HashMap<>();
            switch (view.getId()) {
                case R.id.btnKeep://保存
                    uploadListTotal.clear();
                    for (int i = 0; i < inPathList.size(); i++) {
                        bean = inPathList.get(i);
                        if (!bean.getOriginal().startsWith("http://") && !bean.getThumbnail().startsWith("http://")) {
                            uploadList.add(bean.getOriginal());
                            uploadListThumbnail.add(bean.getThumbnail());
                        }
                    }
                    uploadListTotal.addAll(uploadListThumbnail);
                    uploadListTotal.addAll(uploadList);
                    submitPics(0);

                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
            }
        }
    }
    int flag=0;
    public void submitPics(int position) {
        this.showLoading();
        if (position == uploadListTotal.size()) {
            if (uploadList.size() == uploadListThumbnail.size()) {
                for (int i = 0; i < uploadListThumbnail.size(); i++) {
                    mdlPic = new MdlStoreInfo.DataBean.CompanyImagesBean();
                    mdlPic.setOriginal(uploadList.get(i));
                    mdlPic.setThumbnail(uploadListThumbnail.get(i));
                    list.add(mdlPic);
                }
            }
            for (int i = 0; i < inPathList.size(); i++) {
                bean = inPathList.get(i);
                if (!bean.getOriginal().startsWith("http://") && !bean.getThumbnail().startsWith("http://")) {
                    inPathList.set(i,list.get(flag));
                    flag++;
                }
            }
            map.put("companyImages", inPathList);
            presenter.saveCompanyImage(map);
            return;
        }
        HttpDataBasis.getInstance().upload(uploadListTotal.get(position), new Observer<MdlBaseHttpResp<MdlUpload>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MdlBaseHttpResp<MdlUpload> mdlBaseHttpResp) {
                if (null != mdlBaseHttpResp.getData() && null != mdlBaseHttpResp.getData().getData()) {
                    String[] pics = mdlBaseHttpResp.getData().getData().getUrl().split("/");
                    if (position > (uploadListTotal.size() / 2 - 1)) {
                        uploadList.set(position - uploadListTotal.size() / 2, pics[pics.length - 1]);
                    } else {
                        uploadListThumbnail.set(position, pics[pics.length - 1]);
                    }
                    submitPics(position + 1);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void initRecycle() {
        inPathList = (List<MdlStoreInfo.DataBean.CompanyImagesBean>) getIntent().getSerializableExtra("list");
        if (null == inPathList) inPathList = new ArrayList<>();
        gridView.setLoadingMoreEnabled(false);
        gridView.setPullRefreshEnabled(false);
        gridView.setLayoutManager(new GridLayoutManager(this, 3));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                int swipeFlags = 0;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(inPathList, i - 1, i);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(inPathList, i - 1, i - 2);
                    }
                }
                adapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        itemTouchHelper.attachToRecyclerView(gridView);
        adapter = new MyRecyclerAdapter<MdlStoreInfo.DataBean.CompanyImagesBean>(this, R.layout.item_photo_list) {

            @Override
            public int getItemCount() {
                if (inPathList == null) {
                    return 1;
                } else {
                    return inPathList.size() < 9 ? inPathList.size() + 1 : inPathList.size();
                }
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, MdlStoreInfo.DataBean.CompanyImagesBean item, int position) {
                ImageView imageView = helper.getView(R.id.image);
                ImageView delete = helper.getView(R.id.btnIcClose);
                if (position == inPathList.size()) {
                    imageView.setImageResource(R.drawable.dr_bg_photo_add);
                    delete.setVisibility(View.GONE);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickSelectHeadPic();
                        }
                    });
                } else {
                    delete.setVisibility(View.VISIBLE);
                    Glide.with(StorePhotoActivity.this).load(item.getThumbnail()).into(imageView);
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            inPathList.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        };
        gridView.setAdapter(adapter);
        adapter.setData(inPathList);
        refreshAdapter();
    }

    private void clickSelectHeadPic() {
        PermissionUtil.checkPermission(this, MyPermissionConstant.READ_EXTERNAL_STORAGE + HEAD_PIC, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }


    @Override
    public void permissionSuccess(int permissionReqCode) {
        super.permissionSuccess(permissionReqCode);
        switch (permissionReqCode) {
            case MyPermissionConstant.READ_EXTERNAL_STORAGE + HEAD_PIC:
//                PictureSelector.create(StorePhotoActivity.this)
//                        .openGallery(PictureMimeType.ofImage())
//                        .maxSelectNum(9 - inPathList.size())
//                        .previewImage(true)
//                        .compress(true)
//                        .enableCrop(false)
//                        .forResult(HEAD_PIC);
//                break;
        }
    }

    MdlStoreInfo.DataBean.CompanyImagesBean mdlPic = new MdlStoreInfo.DataBean.CompanyImagesBean();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

//            switch (requestCode) {
//                case HEAD_PIC:
//                    if (data != null) {
//                        List<LocalMedia> list = PictureSelector.obtainMultipleResult(data);
//                        if (list != null && list.size() > 0) {
//                            for (int i = 0; i < list.size(); i++) {
//                                mdlPic = new MdlStoreInfo.DataBean.CompanyImagesBean();
//                                mdlPic.setThumbnail(list.get(i).getCompressPath());
//                                mdlPic.setOriginal(list.get(i).getPath());
//                                inPathList.add(mdlPic);
//                            }
//                            adapter.notifyDataSetChanged();
//                        }
//                    }
//                    break;
//            }
        }

    }

    public void showSaveCompanyImage(MdlBaseHttpResp<MdlStoreInfo> resp) {
        this.hideLoading();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            LogAndToastUtil.toast("保存成功");
            finish();
        } else {
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

    private static final int HEAD_PIC = 10000;
    private static final int CROP_HEAD_PIC = 10001;

    private void refreshAdapter() {
        if (inPathList == null) {
            inPathList = new ArrayList<>();
        }
        if (adapter == null) {
        }
        adapter.notifyDataSetChanged();
    }


}