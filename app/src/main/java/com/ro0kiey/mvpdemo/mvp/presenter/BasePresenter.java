package com.ro0kiey.mvpdemo.mvp.presenter;

import android.content.Context;

import com.ro0kiey.mvpdemo.mvp.view.IBaseView;

/**
 * Created by Ro0kieY on 2017/7/20.
 */

public abstract class BasePresenter<V extends IBaseView> {

    protected Context context;
    protected V iView;

    public BasePresenter(Context context, V iView) {
        this.context = context;
        this.iView = iView;
    }

    public abstract void detachView();
}
