package com.ll.mvp.presenter;

import android.os.Handler;
import android.os.Looper;

import com.ll.mvp.Listener.LoginListener;
import com.ll.mvp.base.NewBasePresenter;
import com.ll.mvp.bean.User;
import com.ll.mvp.biz.UserBiz;
import com.ll.mvp.biz.UserBizIml;
import com.ll.mvp.view.LoginView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
                /*handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isActivityAlive()) {
                            //只有当isActivityAlive返回true时才可以执行与Activity相关的操作,比如 弹出Dialog、Window、跳转Activity等操作.
                        }
                        getMvpView().toMainActivity(user);
                    }
                });*/
                Observable.create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                        //可以发出的三种类型事件,代码控制onComplete和onError必须唯一并且互斥
                        emitter.onNext(user);
                        emitter.onComplete();
                       /* emitter.onError();*/
                    }
                }).subscribe(new Observer<User>() {
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(User user) {
                        System.out.println("Activity的情况:" + isActivityAlive());
                        if (isActivityAlive()) {
                            //只有当isActivityAlive返回true时才可以执行与Activity相关的操作,比如 弹出Dialog、Window、跳转Activity等操作.
                            System.out.println("Activity的情况:" + isActivityAlive());
                        }
                        getMvpView().toMainActivity(user);
                        //解除订阅
                        disposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

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
