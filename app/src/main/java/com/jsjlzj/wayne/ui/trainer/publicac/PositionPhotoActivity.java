package com.jsjlzj.wayne.ui.trainer.publicac;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.BaseAdapterHelper;
import com.jsjlzj.wayne.adapter.recycler.MyRecyclerAdapter;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.constant.MyPermissionConstant;
import com.jsjlzj.wayne.data.http.HttpDataBasis;
import com.jsjlzj.wayne.entity.Login.MdlUpload;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.base.listener.OnMultiClickListener;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentPresenter;
import com.jsjlzj.wayne.ui.mvp.relizetalent.TalentTabFragmentView;
import com.jsjlzj.wayne.ui.store.talent.position.RecruitActivity;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.permission.PermissionUtil;
import com.jsjlzj.wayne.widgets.MyRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.iwf.photopicker.PhotoPicker;

public class PositionPhotoActivity extends MVPBaseActivity<TalentTabFragmentView, TalentTabFragmentPresenter> implements TalentTabFragmentView {


    public static void go2this(Activity context, String list) {
        Intent intent = new Intent(context, PositionPhotoActivity.class);
        intent.putExtra("list", list);
        context.startActivityForResult(intent, RecruitActivity.FLAG_RECRUIT_CONTENT);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_store_info_photo;
    }


    private MyRecyclerView gridView;
    private MyRecyclerAdapter<String> adapter;
    private List<String> inPathList;//显示图片

    @Override
    protected void initViewAndControl() {
        findView(R.id.btnBack).setOnClickListener(clickListener);
        findView(R.id.btnKeep).setOnClickListener(clickListener);
        String info = getIntent().getStringExtra("list");
        String[] pics = null;
        if (null == inPathList) inPathList = new ArrayList<>();
        if (!TextUtils.isEmpty(info)) {
            pics = info.split(",");
        }
        if (null != pics && pics.length > 0) {
            for (int i = 0; i < pics.length; i++) {
                inPathList.add(pics[i]);
            }
        }
        initRecycle();
    }

    @Override
    protected TalentTabFragmentPresenter createPresenter() {
        return new TalentTabFragmentPresenter(this);
    }

    Map<Object, Object> map = null;
    private MyViewClickListener clickListener = new MyViewClickListener();

    private class MyViewClickListener extends OnMultiClickListener {
        @Override
        public void OnMultiClick(View view) {
            if (null == map) map = new HashMap<>();
            switch (view.getId()) {
                case R.id.btnKeep://保存
                    submitPics(0);

                    break;
                case R.id.btnBack://返回
                    finish();
                    break;
            }
        }
    }

    public void submitPics(int position) {
        this.showLoading();
        if (position == inPathList.size()) {
            StringBuffer submitList = new StringBuffer();
            for (int i = 0; i < inPathList.size(); i++) {
                submitList.append(inPathList.get(i) + ",");
            }
            if (submitList.length() == 0) {
                LogAndToastUtil.toast("请上传图片");
                return;
            }
            map.put("lifePhotos", submitList.toString().substring(0, submitList.toString().length() - 1));
            presenter.saveLifePhotosT(map);
            return;
        }
        if (inPathList.get(position).startsWith("http://")) {
            submitPics(position + 1);
        } else {
            HttpDataBasis.getInstance().upload(inPathList.get(position), new Observer<MdlBaseHttpResp<MdlUpload>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(MdlBaseHttpResp<MdlUpload> mdlBaseHttpResp) {
                    if (null != mdlBaseHttpResp.getData() && null != mdlBaseHttpResp.getData().getData()) {
                        String[] pics = mdlBaseHttpResp.getData().getData().getUrl().split("/");
                        inPathList.set(position, pics[pics.length - 1]);
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
    }


    private void initRecycle() {
        gridView = findView(R.id.rvPhone);
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
                    for (int i = fromPosition ; i < toPosition ; i++) {
                        Collections.swap(inPathList, i-1, i );
                    }
                } else {
                    for (int i = fromPosition ; i > toPosition ; i--) {
                        Collections.swap(inPathList, i-1, i - 2);
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
        adapter = new MyRecyclerAdapter<String>(this, R.layout.item_photo_list) {
            @Override
            public int getItemCount() {
                if(inPathList==null) {
                    return 1;
                }else {
                    return inPathList.size() < 9 ? inPathList.size()+1 : inPathList.size();
                }
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, String item, int position) {
                ImageView imageView = helper.getView(R.id.image);
                ImageView delete=helper.getView(R.id.btnIcClose);
                if(position==inPathList.size()){
                    imageView.setImageResource(R.drawable.dr_bg_photo_add);
                    delete.setVisibility(View.GONE);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickSelectHeadPic();
                        }
                    });
                }else{
                    delete.setVisibility(View.VISIBLE);
                Glide.with(PositionPhotoActivity.this).load(item).into(imageView);
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
        adapter.setData(inPathList);
        gridView.setAdapter(adapter);
    }

    private void clickSelectHeadPic() {
        PermissionUtil.checkPermission(this, MyPermissionConstant.READ_EXTERNAL_STORAGE + HEAD_PIC, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void permissionSuccess(int permissionReqCode) {
        super.permissionSuccess(permissionReqCode);
        switch (permissionReqCode) {
            case MyPermissionConstant.READ_EXTERNAL_STORAGE + HEAD_PIC:
                PhotoPicker.builder()
                        .setPhotoCount(9 - inPathList.size())
                        .setShowCamera(true)
                        .setShowGif(false)
                        .setPreviewEnabled(true)
                        .start(this, HEAD_PIC);
                break;
        }
    }

    private String  picPath;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode) {
                case HEAD_PIC:
                    if (data != null) {
                        ArrayList<String> photos =
                                data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                        if (null != photos && photos.size() > 0) {
                            for (int i = 0; i < photos.size(); i++) {
                                picPath = photos.get(i);
                                inPathList.add(picPath);
//                                unloadList.add(picPath);
                            }
                            adapter.notifyDataSetChanged();
                        }

                    }
                    break;
            }
        }

    }

    public void saveLifePhotosT(MdlBaseHttpResp resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData()) {
            this.hideLoading();
            LogAndToastUtil.toast("保存成功");
            finish();
        } else {
            this.hideLoading();
            LogAndToastUtil.toast(resp.getMsg());
        }
    }

    private static final int HEAD_PIC = 10000;
    private static final int CROP_HEAD_PIC = 10001;



}
