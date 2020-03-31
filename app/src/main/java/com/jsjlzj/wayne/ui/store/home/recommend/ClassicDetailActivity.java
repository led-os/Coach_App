package com.jsjlzj.wayne.ui.store.home.recommend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.adapter.recycler.home.ClassDetailAdapter;
import com.jsjlzj.wayne.constant.ExtraConstant;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.DataBean;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.store.home.VideoBean;
import com.jsjlzj.wayne.entity.store.home.VideoPageBean;
import com.jsjlzj.wayne.ui.basis.WebViewContainerActivity;
import com.jsjlzj.wayne.ui.basis.WebViewContainerFragment;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.home.HomePresenter;
import com.jsjlzj.wayne.ui.mvp.home.HomeView;
import com.jsjlzj.wayne.utils.GlidUtils;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.widgets.CustomXRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE;

/**
 * @ClassName: ClassicDetailActivity
 * @Description: 单个类型详情
 * @Author: 曾海强
 * @CreateDate:
 */
public class ClassicDetailActivity extends MVPBaseActivity<HomeView, HomePresenter> implements HomeView, ClassDetailAdapter.OnClassicDetailListener, XRecyclerView.LoadingListener {

    @BindView(R.id.scroll_banner)
    ImageView scrollBanner;
    @BindView(R.id.tv_news)
    TextView tvNews;
    @BindView(R.id.tv_hot)
    TextView tvHot;
    @BindView(R.id.rv_classic_detail)
    CustomXRecyclerView rvClassicDetail;

    private ClassDetailAdapter adapter;
    private int categoryId;
    private List<VideoBean> videoList = new ArrayList<>();
    private Map<Object, Object> map = new HashMap<>();
    /**
     * "status": "状态 0:全部,1:最新,2:热门"
     */
    private int status = 1;
    private int pageNo=1;
    private int pageCount;
    private boolean isRefresh;
    private VideoBean currBean;

    public static void go2this(Activity context, String className, String imgUrl, int categoryId) {
        Intent intent = new Intent(context, ClassicDetailActivity.class);
        intent.putExtra(ExtraConstant.EXTRA_TITLE, className);
        intent.putExtra(ExtraConstant.EXTRA_URL, imgUrl);
        intent.putExtra(ExtraConstant.EXTRA_CATEGORY_ID, categoryId);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_classic_detail;
    }


    @Override
    protected void initViewAndControl() {
        String title = getIntent().getStringExtra(ExtraConstant.EXTRA_TITLE);
        initTitle(title);
        categoryId = getIntent().getIntExtra(ExtraConstant.EXTRA_CATEGORY_ID, 0);
        if (categoryId == 0) {
            finish();
        }
        String imgUrl = getIntent().getStringExtra(ExtraConstant.EXTRA_URL);
        GlidUtils.setRoundGrid(this, imgUrl, scrollBanner, 2);
        adapter = new ClassDetailAdapter(this, videoList);
        adapter.setListener(this);
        rvClassicDetail.setPullRefreshEnabled(true);
        rvClassicDetail.setLoadingMoreEnabled(true);
        rvClassicDetail.setLoadingListener(this);
        rvClassicDetail.setLayoutManager(new LinearLayoutManager(this));
        rvClassicDetail.setAdapter(adapter);
        tvNews.setOnClickListener(clickListener);
        tvHot.setOnClickListener(clickListener);
        loadData(true);
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    private void loadData(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if (isRefresh) {
            pageNo = 1;
        }
        map.clear();
        map.put(HttpConstant.PAGE_NO, pageNo);
        map.put(HttpConstant.PAGE_SIZE, HttpConstant.PAGE_SIZE_NUMBER);
        map.put("categoryId", categoryId);
        map.put("status", status);
        presenter.getVideoList(map);
    }

    @Override
    protected void onMultiClick(View view) {
        super.onMultiClick(view);
        switch (view.getId()) {
            case R.id.tv_news:
                status = 1;
                tvNews.setTextColor(ContextCompat.getColor(this, R.color.color_4F9BFA));
                tvHot.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                loadData(true);
                break;
            case R.id.tv_hot:
                status = 2;
                tvHot.setTextColor(ContextCompat.getColor(this, R.color.color_4F9BFA));
                tvNews.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
                loadData(true);
                break;
        }
    }

    @Override
    public void getVideoListSuccess(MdlBaseHttpResp<VideoPageBean> resp) {
        rvClassicDetail.refreshComplete();
        rvClassicDetail.loadMoreComplete();
        if (resp.getStatus() == HttpConstant.R_HTTP_OK) {
            pageNo = resp.getData().getData().getPageNo();
            int totalCount = resp.getData().getData().getTotalCount();
            int a = totalCount % HttpConstant.PAGE_SIZE_NUMBER;
            if (a == 0) {
                pageCount = totalCount / HttpConstant.PAGE_SIZE_NUMBER;
            } else {
                pageCount = (totalCount / HttpConstant.PAGE_SIZE_NUMBER) + 1;
            }
            List<VideoBean> list = resp.getData().getData().getResult();
            if (list != null && list.size() > 0) {
                if (isRefresh) {
                    videoList.clear();
                }
                videoList.addAll(list);
                adapter.setData(videoList);
                hideEmpty();
            } else if (isRefresh) {
                // 无数据
                showEmpty(R.id.rel_empty, 0, null);
            }
        }
    }

    @Override
    public void onClickHead(VideoBean bean) {
        currBean = bean;
        WebViewContainerActivity.go2this(this,bean.getName(),HttpConstant.WEB_URL_USER_INFO+bean.getChannelId(),
                WebViewContainerFragment.TYPE_USER_INFO);
    }

    @Override
    public void onFavorite(VideoBean bean) {
        currBean = bean;
        Map<Object, Object> map = new HashMap<>();
        map.put("id", bean.getChannelId());
        if (!bean.isFollower()) {
            presenter.cancelFollow(map);
        } else {
            presenter.clickFollow(map);
        }
    }

    @Override
    public void onPlayVideo(VideoBean bean) {
        currBean = bean;
        WebViewContainerActivity.go2this(this,bean.getName(), HttpConstant.WEB_URL_DYNAMIC_DETAIL+bean.getId(),
                WebViewContainerFragment.TYPE_DYNAMIC_DETAIL);
    }

    @Override
    public void onClickZan(VideoBean bean) {
        currBean = bean;
        Map<Object, Object> map = new HashMap<>();
        map.put("id", bean.getId());
        map.put("module", "VIDEO");
        if (!bean.isLike()) {
            presenter.cancelZan(map);
        } else {
            presenter.clickZan(map);
        }
    }

    @Override
    public void onClickMessage(VideoBean bean) {
        currBean = bean;
        WebViewContainerActivity.go2this(this,bean.getName(), HttpConstant.WEB_URL_DYNAMIC_DETAIL+bean.getId(),
                WebViewContainerFragment.TYPE_DYNAMIC_DETAIL);
//        currBean = bean;
//        showPopupWindow(bean.getChannelName());
    }

    @Override
    public void onClickMark(VideoBean bean) {
        currBean = bean;
        Map<Object, Object> map = new HashMap<>();
        map.put("id", bean.getId());
        map.put("module", "VIDEO");
        if (!bean.isCollect()) {
            presenter.cancelCollect(map);
        } else {
            presenter.clickCollect(map);
        }
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void onLoadMore() {
        if (pageNo < pageCount ) {
            pageNo++;
            loadData(false);
        } else {
            LogAndToastUtil.toast(this, getString(R.string.has_no_more_data));
        }
    }


    @Override
    public void getMessageSuccess(MdlBaseHttpResp<DataBean> resp) {
        if(resp.getStatus() == HttpConstant.R_HTTP_OK){
            LogAndToastUtil.toast(resp.getMsg());
        }
    }


    private PopupWindow mPopWindow;
    private boolean isSend;

    private void showPopupWindow(String name) {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.layout_comment_editext, null);
        mPopWindow = new PopupWindow(contentView,
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //防止PopupWindow被软件盘挡住（可能只要下面一句，可能需要这两句）
//        mPopWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //设置软键盘弹出
        InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(1000, InputMethodManager.HIDE_NOT_ALWAYS);//这里给它设置了弹出的时间
        //设置各个控件的点击响应
        final EditText editText = contentView.findViewById(R.id.et_comment);
        final LinearLayout llSend = contentView.findViewById(R.id.ll_send);
        final ImageView imgSend = contentView.findViewById(R.id.img_send);
        editText.setHint("回复"+name+":");
        editText.setInputType(TYPE_TEXT_FLAG_MULTI_LINE);
        editText.setSingleLine(false);
        llSend.setOnClickListener(v -> {
            if(isSend){
                isSend = false;
                imgSend.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.cbx_unselected));
            }else {
                isSend = true;
                imgSend.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.cbx_selected));
            }

        });

        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                //在这里做请求操作
                String inputString = editText.getText().toString();
                Toast.makeText(this, inputString, Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();//让PopupWindow消失
                return true;
            }
            return false;
        });
        //是否具有获取焦点的能力
        mPopWindow.setFocusable(true);
        //显示PopupWindow
        View rootview = LayoutInflater.from(this).inflate(getLayoutResId(), null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

}
