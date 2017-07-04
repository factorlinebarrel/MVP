package com.ll.mvp.Listener;

import com.ll.mvp.bean.User;

/**
 * 通知登录的状态接口
 * Created by LiLei on 2017/7/3.Go.
 */
public interface LoginListener {
    //个人登录成功回调接口
    void loginSuccess(User user);

    //登录失败回调接口
    void loginFailed();
}