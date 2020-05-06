# MVP
高效的MVP模式使用
基于MVP模式的activity简单封装基类和presenter基类.


一:activity

这是一个activity基类，需要传入一个指定view类型(继承的activity的类型)和一个presenter(继承baseactivity的类型)来实现在activity的生命周期
中实现presenter对view的绑定和解绑。

![Image text](https://github.com/factorlinebarrel/MVP/blob/master/screenshot/1.jpg)


二：presenter

这个presenter基类是将制定view进行绑定和解绑(因为未知要绑定的view类型，这里使用泛型)

对presenter进行简单封装，对传入的view,presenter进行绑定和解绑，但是存在空指针问题，这里就只是讲述逻辑。


![Image text](https://github.com/factorlinebarrel/MVP/blob/master/screenshot/2.jpg)

这是的使用弱引用和动态代理的新的presenter封装类，解决了activity被销毁view空指针的问题。
![Image text](https://github.com/factorlinebarrel/MVP/blob/master/screenshot/3.jpg)

同样这个类里面也是一样要进行presenter对view 的绑定和解绑。
![Image text](https://github.com/factorlinebarrel/MVP/blob/master/screenshot/4.jpg)

三：使用

例如我们要写一个登录的activity，登录的activity就需要一个获取账号密码以及登录成功和失败时的接口（LoginView），
由loginActivity实现.

LoginActivity。他需要进行绑定的presenter和view就是他本身loginview类型关联的LoginView和LoginPresenter(如下)。
 
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
    
LoginPresenter。这是一个用于LoginActivity的presenter，即他需要绑定也是一个loginview类型的view。
    
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
    



此时通过查看BaseActivity和NewBasePresenter知道,BaseActivity和NewBasePresenter通过LoginView进行绑定和解绑,presenter获取view，而BaseActivity在生命周期中实现绑定和解绑。


1.activity(在生命周期中实现presenter的绑定和解绑)

      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          presenter = initPresenter();
      }

      @Override
      protected void onResume() {
          super.onResume();
          //业务逻辑请求必须在绑定之后，否则空指针
          presenter.attach(getApplicationContext(), (V) this);

      }

      @Override
      protected void onDestroy() {
          presenter.detach();
          super.onDestroy();
      }
      
 2.presenter(绑定和解绑view)

       public void attach(Context context, T view) {
        mContextRef = new WeakReference<>(context);
        mViewRef = new WeakReference<>(view);
        if (sAppContext == null && context != null) {
            sAppContext = context.getApplicationContext();
        }
     }
       public void detach() {
           if (mContextRef != null) {
               mContextRef.clear();
           }
           mContextRef = null;
           if (mViewRef != null) {
               mViewRef.clear();
           }
           mViewRef = null;
      }
 
 
指定activity在绑定给指定presenter之后，presenter就可以拿着这个view进行操作。


比如这个LoginActivity是拥有getName和getPassword方法的。
 
 
     public interface LoginView {
     String getName();

     String getPassword();

     void toMainActivity(User user);//成功跳转Activity

     void showFailedError();//失败提示
     }
  
  
presenter持有的view不为null，就可以调用view的一系列方法，比如在presenter中可以通过getMvpView().getName()形式获取view的数据。
  
  
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
  
 这样就可以成功实现presenter对view的绑定和解绑,以及解决presenter对view的持有而导致的内存泄漏问题。 
 
  
