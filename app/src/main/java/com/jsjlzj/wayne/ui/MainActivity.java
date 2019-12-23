package com.jsjlzj.wayne.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.constant.HttpConstant;
import com.jsjlzj.wayne.entity.Login.MdlUser;
import com.jsjlzj.wayne.entity.MdlBaseHttpResp;
import com.jsjlzj.wayne.entity.address.MalAddressProvince;
import com.jsjlzj.wayne.entity.store.MdlDict;
import com.jsjlzj.wayne.entity.trainer.MdlInterviewMessage;
import com.jsjlzj.wayne.ui.basis.JsjlAgreementActivity;
import com.jsjlzj.wayne.ui.basis.LoginByPhoneActivity;
import com.jsjlzj.wayne.ui.mvp.base.MVPBaseActivity;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityPresenter;
import com.jsjlzj.wayne.ui.mvp.relizelogin.LoginActivityView;
import com.jsjlzj.wayne.ui.store.personal.TabItemStoreInfoFragment;
import com.jsjlzj.wayne.ui.store.personal.storeinfo.InterviewDetailActivity;
import com.jsjlzj.wayne.ui.store.talent.menu.TabItemTrainerFragment;
import com.jsjlzj.wayne.ui.trainer.personal.TabItemTrainerInfoFragment;
import com.jsjlzj.wayne.ui.trainer.position.menu.TabItemPositionFragment;
import com.jsjlzj.wayne.ui.yunxin.YunXingUtil;
import com.jsjlzj.wayne.ui.yunxin.fragment.MySessionListFragment;
import com.jsjlzj.wayne.ui.yunxin.reminder.ReminderItem;
import com.jsjlzj.wayne.ui.yunxin.reminder.ReminderManager;
import com.jsjlzj.wayne.utils.LogAndToastUtil;
import com.jsjlzj.wayne.utils.ResourceUtil;
import com.jsjlzj.wayne.utils.SPUtil;
import com.jsjlzj.wayne.utils.Utility;
import com.jsjlzj.wayne.widgets.MyViewPager;
import com.jsjlzj.wayne.widgets.dialog.dataItem.CommonInterviewDialog;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.model.CustomNotification;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 主页  根据身份  “ 门店、教练”  适应菜单
 */
public class MainActivity extends MVPBaseActivity<LoginActivityView, LoginActivityPresenter> implements LoginActivityView, ReminderManager.UnreadNumChangedCallback {

    public static void go2this(Activity context, boolean isStore) {
        if(SPUtil.getFist()&&isFlyme()){//SPUtil.getFist()
            JsjlAgreementActivity.go2this(context,isStore);
            SPUtil.saveFist();
        }else {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("isStore", isStore);
            context.startActivity(intent);
        }
    }
    public static boolean isFlyme() {
        try {
            // Invoke Build.hasSmartBar()
            final Method method = Build.class.getMethod("hasSmartBar");
            return method != null;
        } catch (final Exception e) {
            return false;

        }
    }
    private boolean isStore;

    @Override
    protected int getLayoutResId() {
        return R.layout.main_layout;
    }

    @Override
    protected LoginActivityPresenter createPresenter() {
        return new LoginActivityPresenter(this);
    }

    private int[][] stroeDrawableSelector = {
            {R.drawable.ic_talent_w, R.drawable.ic_talent_y},
            {R.drawable.ic_message_w, R.drawable.ic_message_y},
            {R.drawable.ic_mine_w, R.drawable.ic_mine_y}
    };
    private int[][] trainerDrawableSelector = {
            {R.drawable.ic_talent_w, R.drawable.ic_talent_y},
            {R.drawable.ic_message_w, R.drawable.ic_message_y},
            {R.drawable.ic_mine_w, R.drawable.ic_mine_y}
    };

    private String[] stroeNames = {
            "达人", "消息", "我的"
    };
    private String[] trainerNames = {
            "职位", "消息", "我的"
    };
    private MyViewPager viewPager;
    private List<Fragment> mFragments = new ArrayList<>();
    private String[] nams;
    private int[][] drawableSelector;
    private TabLayout tabLayout;
    private TabItemTrainerFragment tabItemTrainerFragment;
    private MdlUser.MdlUserBean user;

    private TabItemPositionFragment tabItemPositionFragment;

    @Override
    protected void initViewAndControl() {
        MyApp.ME.dm = Utility.getDisplayScreenSize(this);
        MyApp.user = SPUtil.getUserFromSP();
        //登不上去的话 在AndroidManifest 有appkey
        user = MyApp.ME.user;
        if (user != null)
            YunXingUtil.LoginYunxing(new LoginInfo(user.getYunXinAccount(), user.getYunXinToken()), new RequestCallback<LoginInfo>() {
                @Override
                public void onSuccess(LoginInfo param) {
                    LogAndToastUtil.log("云信登录成功" + param.getAccount());
                    NimUIKit.loginSuccess(user.getYunXinAccount());//必要
                    SPUtil.saveYXTokenSP(param.getToken());
                    SPUtil.saveYXAccountSP(param.getAccount());
                    //设置用户信息
//                    YunXingUtil.setUserInfo(user.getName(), user.getHeadImg(), new RequestCallbackWrapper<Void>() {
//                        @Override
//                        public void onResult(int i, Void aVoid, Throwable throwable) {
//
//                        }
//                    });
                }

                @Override
                public void onFailed(int code) {
                    LoginByPhoneActivity.go2This(AppManager.getAppManager().currentActivity(),"","","","","");
                    finish();
                    LogAndToastUtil.log("云信登录成功" + code);
                }

                @Override
                public void onException(Throwable exception) {
                    LoginByPhoneActivity.go2This(AppManager.getAppManager().currentActivity(),"","","","","");
                    finish();
                    LogAndToastUtil.log("云信登录成功" + exception);
                }
            });

        NIMClient.getService(MsgServiceObserve.class).observeCustomNotification(new Observer<CustomNotification>() {
            @Override
            public void onEvent(CustomNotification message) {
                // 在这里处理自定义通知。
                // {"type":  "InterviewNotice", ext:{"sHeadImg":俱乐部头像， "tHeadImg": 教练头像 ， "storeName": 俱乐部名称, "id": 面试ID}}
                String content = message.getContent();
                if (!TextUtils.isEmpty(content)) {
                    try {
                        MdlInterviewMessage mdlInterviewMessage = new Gson().fromJson(content, new TypeToken<MdlInterviewMessage>() {}.getType());
                        if (mdlInterviewMessage != null) {
                            if ("InterviewNotice".equals(mdlInterviewMessage.getType())) {
                                if (null != mdlInterviewMessage.getExt()) {
                                    new CommonInterviewDialog(AppManager.getAppManager().currentActivity(),
                                            mdlInterviewMessage.getExt().getTHeadImg(),
                                            mdlInterviewMessage.getExt().getSHeadImg(),
                                            mdlInterviewMessage.getExt().getStoreName(),
                                            new CommonInterviewDialog.ClickListener() {
                                                @Override
                                                public void clickConfirm() {
                                                    InterviewDetailActivity.go2this(AppManager.getAppManager().currentActivity(), mdlInterviewMessage.getExt().getId() + "");
                                                }
                                            }
                                    ).show();
                                }
                            }
                        }
                    } catch (Exception e) {

                    }
                }
            }
        }, true);


        isStore = getIntent().getBooleanExtra("isStore", true);
        MyApp.isTrainer = !isStore;

        viewPager = findView(R.id.viewpager);
        tabLayout = findView(R.id.tabLayout);
        mFragments.clear();
        if (isStore) {
            nams = stroeNames;
            drawableSelector = stroeDrawableSelector;
            tabItemTrainerFragment = (TabItemTrainerFragment) TabItemTrainerFragment.getInstance();
            mFragments.add(tabItemTrainerFragment);
            mFragments.add(MySessionListFragment.getInstance(false));
            mFragments.add(TabItemStoreInfoFragment.getInstance());
        } else {
            nams = trainerNames;
            drawableSelector = trainerDrawableSelector;
            tabItemPositionFragment = (TabItemPositionFragment) TabItemPositionFragment.getInstance();
            mFragments.add(tabItemPositionFragment);
            mFragments.add(MySessionListFragment.getInstance(true));
            mFragments.add(TabItemTrainerInfoFragment.getInstance());
        }
        initViewPager();
        registerMsgUnreadInfoObserver(true);

    }
    /**
     * 注册未读消息数量观察者
     */
    private void registerMsgUnreadInfoObserver(boolean register) {
        if (register) {
            ReminderManager.getInstance().registerUnreadNumChangedCallback(this);
        } else {
            ReminderManager.getInstance().unregisterUnreadNumChangedCallback(this);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        registerMsgUnreadInfoObserver(false);
    }
    @Override
    public void onUnreadNumChanged(ReminderItem item) {
        setTabTip(item);
    }
    private void initViewPager() {
        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                Fragment fragment = mFragments.get(arg0);
                return fragment;
            }
        };
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(mAdapter.getCount());
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i));
            }
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                selectTab(view);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                unselectTab(view);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewPager.setCurrentItem(0);
        selectTab(tabLayout.getTabAt(0).getCustomView());
        presenter.getAll(null);
        presenter.getAllArea(null);
    }

    private View getTabView(int position) {
        View v = LayoutInflater.from(this).inflate(R.layout.main_tab_item, null);
        TextView tv = v.findViewById(R.id.tv_tab_item);
        tv.setText(nams[position]);
        v.setId(position);
        if (position == 0) {
            ResourceUtil.setCompoundDrawable(tv, 0, drawableSelector[position][1], 0, 0);
        } else {
            ResourceUtil.setCompoundDrawable(tv, 0, drawableSelector[position][0], 0, 0);
        }
        return v;
    }
    private void setTabTip(ReminderItem item){
        if(tabLayout!=null && tabLayout.getTabCount()>1){
            TabLayout.Tab tab = tabLayout.getTabAt(1);
            if (tab != null) {
                View customView = tab.getCustomView();
                if (customView != null) {
                    TextView tvCount  =customView.findViewById(R.id.tv_tab_msg);
                    if (tvCount != null) {
                        if (item == null) {
                            tvCount.setVisibility(View.GONE);
                            return;
                        }
                        int unread = item.unread();
                        tvCount.setVisibility(unread > 0 ? View.VISIBLE : View.GONE);
                        if(unread>0)tvCount.setText(unread+"");
                    }
                }
            }
        }
    }

    private void selectTab(View view) {
        TextView tv = view.findViewById(R.id.tv_tab_item);
        int position = view.getId();
        ResourceUtil.setCompoundDrawable(tv, 0, drawableSelector[position][1], 0, 0);
//        if (position == 1) {
//            view.findViewById(R.id.tv_tab_msg).setVisibility(View.GONE);
//        }
    }

    private void unselectTab(View view) {
        TextView tv = view.findViewById(R.id.tv_tab_item);
        int position = view.getId();
        ResourceUtil.setCompoundDrawable(tv, 0, drawableSelector[position][0], 0, 0);
//        if (position == 1) {
//            view.findViewById(R.id.tv_tab_msg).setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public void showResultgetAll(MdlBaseHttpResp<MdlDict> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MyApp.mdlDict = resp.getData().getData();
        }
    }

    @Override
    public void showResultAllArea(MdlBaseHttpResp<MalAddressProvince> resp) {
        if (resp.getStatus() == HttpConstant.R_HTTP_OK && null != resp.getData() && null != resp.getData().getData()) {
            MyApp.provinceList = resp.getData().getData().getProvinceList();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String type = SPUtil.getUserFromSP().getAccountType();
        if ("STORE".equals(type)) {
            isStore = true;
        } else if ("TRAINER".equals(type)) {
            isStore = false;
        }
        if (isStore) {
            tabItemTrainerFragment.onActivityResult(requestCode, resultCode, data);
        } else {
            tabItemPositionFragment.onActivityResult(requestCode, resultCode, data);
        }
    }


}
