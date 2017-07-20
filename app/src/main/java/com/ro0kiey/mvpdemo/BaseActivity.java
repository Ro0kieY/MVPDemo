package com.ro0kiey.mvpdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ro0kiey.mvpdemo.mvp.presenter.BasePresenter;

/**
 * Created by Ro0kieY on 2017/7/20.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initPresenter();
    }

    protected abstract void initPresenter();

    protected abstract int getLayoutResId();
}
