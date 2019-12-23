package com.jsjlzj.wayne.utils.test;


import android.app.Activity;
import android.app.Fragment;

public class HolderFragment extends Fragment {
    private int activityCode;
    private LifecycleListener lifecycleListener;

    public void setLifecycleListener(LifecycleListener lifecycleListener) {
        this.lifecycleListener = lifecycleListener;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityCode=activity.hashCode();
        if(lifecycleListener!=null){
            lifecycleListener.onCreate(activityCode);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(lifecycleListener!=null){
            lifecycleListener.onStart(activityCode);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(lifecycleListener!=null){
            lifecycleListener.onDetach(activityCode);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if(lifecycleListener!=null){
            lifecycleListener.onPause(activityCode);
        }
    }



}
