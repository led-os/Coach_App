package com.jsjlzj.wayne.data.http;

import android.support.annotation.NonNull;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class BaseHttpData {

    public <T> T create(final Class<T> service) {
        return HttpManager.getInstance().getRetrofit().create(service);
    }

    protected RequestBody GenJsonParamRequestBody(Map param) {
        return HttpUtil.GenJsonParamRequestBody(param);
    }

    protected <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    protected <T> Disposable setSubscribeTimer(final Observable<T> observable, final Observer<T> observer) {
        Disposable timer =Flowable
                .interval(3, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(observer);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                    }
                });
        return timer;
    }

    protected <T1,T2> void setSubscribe(Observable<T1> observable,Function<T1, T2> function, Observer<T2> observer) {
        observable.subscribeOn(Schedulers.io())
                .map(function)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
