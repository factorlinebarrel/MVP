# MVP
高效的MVP模式使用
基于MVP模式的activity简单封装基类和presenter基类.


一:activity

这是一个activity基类，需要传入一个指定view类型(继承的activity的类型)和一个presenter(继承baseactivity的类型)
![Image text](https://github.com/factorlinebarrel/MVP/blob/master/screenshot/1.jpg)


二：presenter

这个presenter基类是将制定view进行绑定和解绑(因为未知要绑定的view类型，这里使用泛型)

对presenter进行简单封装，对传入的view,presenter进行绑定和解绑，但是存在空指针问题，这里就只是讲述逻辑。


![Image text](https://github.com/factorlinebarrel/MVP/blob/master/screenshot/2.jpg)

这是一个simple哥写的使用弱引用和动态代理的新的presenter封装类，解决了activity被销毁view空指针的问题。
![Image text](https://github.com/factorlinebarrel/MVP/blob/master/screenshot/3.jpg)

同样这个类里面也是一样要进行presenter对view 的绑定和解绑。
![Image text](https://github.com/factorlinebarrel/MVP/blob/master/screenshot/4.jpg)

三：使用
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
