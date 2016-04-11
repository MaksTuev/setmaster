package com.two_man.setmaster.ui.base.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;

import com.two_man.setmaster.ui.base.BaseView;
import com.two_man.setmaster.ui.base.HasName;
import com.two_man.setmaster.ui.base.HasPresenter;
import com.two_man.setmaster.util.log.LogServerUtil;

/**
 * базовый класс для вью, основанной на Activity
 */
public abstract class BaseActivityView extends BaseActivity implements BaseView, HasPresenter, HasName {


    private Handler handler = new Handler();
    /**
     * в реализации этого метода необходимо удовлетворить зависимости
     */
    protected abstract void satisfyDependencies();

    @LayoutRes
    protected abstract int getContentView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogServerUtil.logViewCreated(this);
        setContentView(getContentView());
        satisfyDependencies();
        initPresenter();
        if (savedInstanceState != null) {
            handler.post(() -> getPresenter().onRestore(savedInstanceState));
        }
        handler.post(() -> getPresenter().onLoad());
    }

    @Override
    @CallSuper
    public void initPresenter() {
        getPresenter().attachView(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getPresenter().onSave(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void goBack() {
        this.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().onDestroy();
        LogServerUtil.logViewDestroyed(this);
    }

}