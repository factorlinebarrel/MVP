package com.ll.mvp.biz;

import com.ll.mvp.Listener.LoginListener;
import com.ll.mvp.Listener.RegisterListener;

/**
 * 请求类接口
 * Created by LiLei on 2017/7/3.Go.
 */
public interface UserBiz {
    void login(String name, String password, LoginListener listener);

    void register(String name, String password, RegisterListener listener);
}
