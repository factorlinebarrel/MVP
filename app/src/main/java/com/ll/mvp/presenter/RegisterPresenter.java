package com.ll.mvp.presenter;

import android.os.Handler;
import android.os.Looper;

import com.ll.mvp.Listener.RegisterListener;
import com.ll.mvp.base.NewBasePresenter;
import com.ll.mvp.biz.UserBiz;
import com.ll.mvp.biz.UserBizIml;
import com.ll.mvp.view.RegisterView;

/**
 * 注册presenter
 * Created by LiLei on 2017/7/3.Go.
 */
public class RegisterPresenter extends NewBasePresenter<RegisterView> {
    private UserBiz biz;
    private Handler handler;

    public RegisterPresenter() {
        biz = new UserBizIml();
        handler = new Handler(Looper.getMainLooper());
    }

    //实际逻辑实现
    public void register() {

        biz.register(getMvpView().getName(), getMvpView().getPassword(), new RegisterListener() {
            @Override
            public void RegisterSuccess() {
                //异步
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        getMvpView().RegisterSuccess();
                    }
                });
            }

            @Override
            public void RegisterFailed() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        getMvpView().RegisterFail();
                    }
                });
            }
        });
    }
}
