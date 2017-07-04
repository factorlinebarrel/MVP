package com.ll.mvp.Listener;

/**
 * 通知注册的状态接口
 * Created by LiLei on 2017/7/3.Go.
 */
public interface RegisterListener {
    //注册成功回调接口
    void RegisterSuccess();

    //注册失败回调接口
    void RegisterFailed();
}