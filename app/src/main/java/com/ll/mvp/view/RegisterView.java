package com.ll.mvp.view;


/**
 * 业务View-注册接口
 * Created by LiLei on 2017/7/3.Go.
 */
public interface RegisterView {
    String getName();

    String getPassword();

    void RegisterSuccess();//成功跳转Activity

    void RegisterFail();//失败提示
}
