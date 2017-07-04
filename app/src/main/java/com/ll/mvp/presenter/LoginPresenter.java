package com.ll.mvp.presenter;

import android.os.Handler;
import android.os.Looper;

import com.ll.mvp.Listener.LoginListener;
import com.ll.mvp.base.NewBasePresenter;
import com.ll.mvp.bean.User;
import com.ll.mvp.biz.UserBiz;
import com.ll.mvp.biz.UserBizIml;
import com.ll.mvp.view.LoginView;

/**
 * 登录presenter
 * Created by LiLei on 2017/7/3.Go.
 */
public class LoginPresenter extends NewBasePresenter<LoginView> {
    private UserBiz biz;
    private Handler handler;

    public LoginPresenter() {
        biz = new UserBizIml();
        handler = new Handler(Looper.getMainLooper());
    }

    //实际逻辑实现
    public void login() {

        biz.login(getMvpView().getName(), getMvpView().getPassword(), new LoginListener() {
            @Override
            public void loginSuccess(final User user) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isActivityAlive()) {
                            //只有当isActivityAlive返回true时才可以执行与Activity相关的操作,比如 弹出Dialog、Window、跳转Activity等操作.
                        }
                        getMvpView().toMainActivity(user);
                    }
                });
            }

            @Override
            public void loginFailed() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        getMvpView().showFailedError();
                    }
                });
            }
        });
    }
}
