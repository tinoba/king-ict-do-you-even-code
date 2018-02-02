package eu.tinoba.androidarcitecturetemplate.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.tinoba.androidarcitecturetemplate.R;
import eu.tinoba.androidarcitecturetemplate.data.api.models.request.RegisterInformation;
import eu.tinoba.androidarcitecturetemplate.injection.component.ActivityComponent;
import eu.tinoba.androidarcitecturetemplate.ui.base.activities.BaseActivity;
import eu.tinoba.androidarcitecturetemplate.ui.login.LoginActivity;

public class RegisterActivity extends BaseActivity implements RegisterView {

    @Inject
    RegisterPresenter presenter;

    @BindView(R.id.login_activity_teamname)
    TextInputEditText teamname;

    @BindView(R.id.login_activity_password)
    TextInputEditText password;

    @BindView(R.id.login_activity_member1name)
    TextInputEditText member1Name;

    @BindView(R.id.login_activity_member1surname)
    TextInputEditText member1Surname;

    @BindView(R.id.login_activity_member1mail)
    TextInputEditText member1Mail;

    @BindView(R.id.login_activity_member2name)
    TextInputEditText member2Name;

    @BindView(R.id.login_activity_member2surname)
    TextInputEditText member2Surname;

    @BindView(R.id.login_activity_member2mail)
    TextInputEditText member2Mail;
    @BindView(R.id.login_activity_member3name)
    TextInputEditText member3Name;

    @BindView(R.id.login_activity_member3surname)
    TextInputEditText member3Surname;

    @BindView(R.id.login_activity_member3mail)
    TextInputEditText member3Mail;
    @BindView(R.id.login_activity_member4name)
    TextInputEditText member4Name;

    @BindView(R.id.login_activity_member4surname)
    TextInputEditText member4Surname;

    @BindView(R.id.login_activity_member4mail)
    TextInputEditText member4Mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public void goToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @OnClick(R.id.registerButton)
    public void clicked() {
        List<RegisterInformation.Members> lista = new ArrayList<>();
        lista.add(new RegisterInformation.Members(member1Name.getText().toString(), member1Surname.getText().toString(),
                                                  member1Mail.getText().toString()));
        lista.add(new RegisterInformation.Members(member2Name.getText().toString(), member2Surname.getText().toString(),
                                                  member2Mail.getText().toString()));
        lista.add(new RegisterInformation.Members(member3Name.getText().toString(), member3Surname.getText().toString(),
                                                  member3Mail.getText().toString()));
        lista.add(new RegisterInformation.Members(member4Name.getText().toString(), member4Surname.getText().toString(),
                                                  member4Mail.getText().toString()));
        presenter.register(new RegisterInformation(teamname.getText().toString(), password.getText().toString(), lista));
    }
}
