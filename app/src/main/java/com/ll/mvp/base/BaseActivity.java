package com.ll.mvp.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * view
 * presenter
 * Created by LiLei on 2017/7/3.Go.
 */
public abstract class BaseActivity<V, T extends NewBasePresenter<V>> extends AppCompatActivity {
    public T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //业务逻辑请求必须在绑定之后，否则空指针
        presenter.attach(getApplicationContext(), (V) this);

    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    //实例化presenter
    public abstract T initPresenter();
}
