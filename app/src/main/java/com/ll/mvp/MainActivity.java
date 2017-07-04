package com.ll.mvp;

import android.os.Bundle;
import android.widget.Toast;

import com.ll.mvp.base.BaseActivity;
import com.ll.mvp.presenter.RegisterPresenter;
import com.ll.mvp.view.RegisterView;

public class MainActivity extends BaseActivity<RegisterView, RegisterPresenter> implements RegisterView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       /* presenter.register();*/
    }

    @Override
    public RegisterPresenter initPresenter() {
        return new RegisterPresenter();
    }

    @Override
    public String getName() {
        return "name";
    }

    @Override
    public String getPassword() {
        return "password";
    }

    @Override
    public void RegisterSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void RegisterFail() {
        Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
    }
}
