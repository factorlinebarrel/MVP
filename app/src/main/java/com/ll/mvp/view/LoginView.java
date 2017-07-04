package com.ll.mvp.view;


import com.ll.mvp.bean.User;

/**
 * 业务View-登录接口
 * Created by LiLei on 2017/7/3.Go.
 */
public interface LoginView {
    String getName();

    String getPassword();

    void toMainActivity(User user);//成功跳转Activity

    void showFailedError();//失败提示
}
