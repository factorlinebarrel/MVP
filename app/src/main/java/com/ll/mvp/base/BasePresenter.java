package com.ll.mvp.base;

/**
 * presenter类基类用于解决activity的view解绑绑定
 * 存在空指针情况
 * Created by LiLei on 2017/7/3.Go.
 */
@Deprecated
public abstract class BasePresenter<T> {
    public T view;

    public void attach(T view) {
        this.view = view;
    }

    public void dettach() {
        this.view = null;
    }
}
