package com.ll.mvp.biz;

import com.ll.mvp.Listener.LoginListener;
import com.ll.mvp.Listener.RegisterListener;
import com.ll.mvp.bean.User;

/**
 * 请求接口实现，网络请求业务逻辑类
 * Created by LiLei on 2017/7/3.Go.
 */
public class UserBizIml implements UserBiz {

    @Override
    public void login(String name, String password, LoginListener listener) {
        //模拟联网操作,具体的网络请求操作就写在这里
        if ("name".equals(name) && "password".equals(password)) {
            //这里其实就是我们通过网络访问获取数据之后传回具体实现了这个监听者的presenter，之后再传出activity
            listener.loginSuccess(new User());
        } else {
            listener.loginFailed();
        }
    }

    @Override
    public void register(String name, String password, RegisterListener listener) {
        if ("name".equals(name) && "password".equals(password)) {
            listener.RegisterSuccess();
        } else {
            listener.RegisterFailed();
        }
    }


}
