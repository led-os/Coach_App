package com.jsjlzj.wayne.utils.test;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//消息通道
public class LiveData<T> {
    //地址
    private HashMap<Integer, Observer<T>> map = new HashMap<>();
    private HashMap<Integer, List<T>> mPeningDelayList = new HashMap<>();

    Handler handler = new Handler(Looper.getMainLooper());

    public void postValue(final T value) {
        synchronized (this) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    setValue(value);
                }
            });
        }
    }

    public void setValue(T value) {
        List<Observer> destoryList = new ArrayList<>();
        for (Map.Entry<Integer, Observer<T>> entry : map.entrySet()) {
            Observer observer = entry.getValue();
            Integer activityCode = entry.getKey();
            if (observer.getState() == Observer.STATE_INIT) {
            }
            if (observer.getState() == Observer.STATE_ACTIVE) {
                observer.onChanged(value);
            }
            if (observer.getState() == Observer.STATE_ONPAUSE || observer.getState() == Observer.STATE_INIT) {
                if (mPeningDelayList.get(activityCode) == null)
                    mPeningDelayList.put(activityCode, new ArrayList<T>());
                if (!mPeningDelayList.get(activityCode).contains(value))
                    mPeningDelayList.get(activityCode).add(value);
            }
            if (observer.getState() == Observer.STATE_DESTORY) {
                destoryList.add(observer);
                map.remove(activityCode);
            }
        }
        for (Observer observer : destoryList) {
            map.remove(observer);
        }
    }

    public void observe(Activity activity, Observer<T> observer) {
        FragmentManager fm = activity.getFragmentManager();
        HolderFragment fragment = (HolderFragment) fm.findFragmentByTag("ss");
        if (fragment == null) {
            fragment = new HolderFragment();
            fm.beginTransaction().add(fragment, "ss");
        }
        fragment.setLifecycleListener(lifecycleListener);
        map.put(activity.hashCode(), observer);
    }

    private LifecycleListener lifecycleListener = new LifecycleListener() {
        @Override
        public void onCreate(int actictyCode) {
            map.get(actictyCode).setState(Observer.STATE_INIT);
        }

        @Override
        public void onStart(int actictyCode) {
            map.get(actictyCode).setState(Observer.STATE_ACTIVE);
            if (mPeningDelayList.get(actictyCode) == null || mPeningDelayList.get(actictyCode).size() == 0)
                return;
            for (T t : mPeningDelayList.get(actictyCode)) {
                map.get(actictyCode).onChanged(t);
            }
            mPeningDelayList.clear();
        }

        @Override
        public void onPause(int actictyCode) {
            map.get(actictyCode).setState(Observer.STATE_ONPAUSE);
        }

        @Override
        public void onDetach(int actictyCode) {
            map.get(actictyCode).setState(Observer.STATE_DESTORY);
        }
    };

}
