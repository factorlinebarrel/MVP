package com.ll.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ll.mvp.base.BaseActivity;
import com.ll.mvp.bean.User;
import com.ll.mvp.presenter.LoginPresenter;
import com.ll.mvp.view.LoginView;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {
    private EditText name_et, password_et;
    private String name, password;
    private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        name_et = (EditText) findViewById(R.id.et_name);
        password_et = (EditText) findViewById(R.id.et_password);
        login_btn = (Button) findViewById(R.id.btn_login);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = name_et.getText().toString().trim();
                password = password_et.getText().toString().trim();
                presenter.login();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public LoginPresenter initPresenter() {
        //在此声明是这个activity的presenter
        return new LoginPresenter();
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }
}
