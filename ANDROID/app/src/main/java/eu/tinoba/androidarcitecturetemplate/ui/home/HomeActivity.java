package eu.tinoba.androidarcitecturetemplate.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.tinoba.androidarcitecturetemplate.R;
import eu.tinoba.androidarcitecturetemplate.injection.component.ActivityComponent;
import eu.tinoba.androidarcitecturetemplate.ui.base.activities.BaseActivity;
import eu.tinoba.androidarcitecturetemplate.ui.login.LoginActivity;
import eu.tinoba.androidarcitecturetemplate.ui.register.RegisterActivity;

public class HomeActivity extends BaseActivity implements HomeView {

    @BindView(R.id.home_btn_login)
    Button btnLogin;


    @BindView(R.id.home_btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.home_btn_login)
    public void clickLogin(){
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }


    @OnClick(R.id.home_btn_register)
    public void clickRegister(){
        startActivity(new Intent(HomeActivity.this, RegisterActivity.class));
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }


}
