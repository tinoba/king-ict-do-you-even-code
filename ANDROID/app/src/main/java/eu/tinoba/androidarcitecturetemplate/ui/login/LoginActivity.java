package eu.tinoba.androidarcitecturetemplate.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;

import org.json.JSONException;
import org.json.JSONObject;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.tinoba.androidarcitecturetemplate.R;
import eu.tinoba.androidarcitecturetemplate.data.api.models.request.UserInformation;
import eu.tinoba.androidarcitecturetemplate.data.api.models.response.LoginResponse;
import eu.tinoba.androidarcitecturetemplate.injection.component.ActivityComponent;
import eu.tinoba.androidarcitecturetemplate.ui.base.activities.BaseActivity;
import eu.tinoba.androidarcitecturetemplate.ui.details.DetailsActivity;

public class LoginActivity extends BaseActivity implements LoginView {

    @Inject LoginPresenter presenter;

    @BindView(R.id.login_activity_teamname)
    TextInputEditText teamName;
    @BindView(R.id.login_activity_pass)
    TextInputEditText pass;
    @BindView(R.id.login_activity_spin_kit)
    SpinKitView spin;
    @BindView(R.id.button_login)
    Button loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Login");
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);

    }

    @OnClick(R.id.button_login)
    public void buttonLoginClikc(){
        spin.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.GONE);
        presenter.login(new UserInformation(teamName.getText().toString(), pass.getText().toString()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public void goToNextScreen(LoginResponse loginResponse) throws JSONException {
        JSONObject jsonObject = new JSONObject(loginResponse.Result);
        String id = jsonObject.getString("TeamId");
        startActivity(new Intent(LoginActivity.this, DetailsActivity.class).putExtra("teamId", id));
        spin.setVisibility(View.GONE);
        loginButton.setVisibility(View.VISIBLE);

    }

    @Override
    public void failed() {
        Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        spin.setVisibility(View.GONE);
        loginButton.setVisibility(View.VISIBLE);
    }
}
