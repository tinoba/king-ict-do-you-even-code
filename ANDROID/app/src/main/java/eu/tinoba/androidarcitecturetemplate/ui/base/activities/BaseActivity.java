package eu.tinoba.androidarcitecturetemplate.ui.base.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import eu.tinoba.androidarcitecturetemplate.application.TemplateApplication;
import eu.tinoba.androidarcitecturetemplate.injection.ComponentFactory;
import eu.tinoba.androidarcitecturetemplate.injection.component.ActivityComponent;

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TemplateApplication templateApplication = (TemplateApplication) getApplication();
        initActivityComponent(templateApplication);
        inject(activityComponent);
    }

    public final ActivityComponent getActivityComponent(final TemplateApplication templateApplication) {
        if (activityComponent == null) {
            initActivityComponent(templateApplication);
        }
        return activityComponent;
    }

    private void initActivityComponent(final TemplateApplication templateApplication) {
        activityComponent = ComponentFactory.createActivityComponent(templateApplication, this);
    }

    protected abstract void inject(ActivityComponent activityComponent);
}
