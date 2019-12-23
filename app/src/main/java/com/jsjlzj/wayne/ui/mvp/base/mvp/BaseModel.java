package com.jsjlzj.wayne.ui.mvp.base.mvp;

import io.reactivex.disposables.Disposable;

public class BaseModel {
    protected Disposable disposable;

    public void detachModel(){
        if(disposable != null && !disposable.isDisposed()){
            disposable.dispose();
            disposable = null;
        }
    }
}
