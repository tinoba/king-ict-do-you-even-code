package eu.tinoba.androidarcitecturetemplate.ui.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import eu.tinoba.androidarcitecturetemplate.R;
import eu.tinoba.androidarcitecturetemplate.injection.component.ActivityComponent;
import eu.tinoba.androidarcitecturetemplate.ui.base.activities.BaseActivity;

public class RegisterActivity extends BaseActivity implements RegisterView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");

    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);

    }
}
