package eu.tinoba.androidarcitecturetemplate.application;

import android.app.Application;

import eu.tinoba.androidarcitecturetemplate.injection.ComponentFactory;
import eu.tinoba.androidarcitecturetemplate.injection.component.ApplicationComponent;
import timber.log.Timber;

public final class TemplateApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = ComponentFactory.createApplicationComponent(this);
        applicationComponent.inject(this);
        Timber.plant(new Timber.DebugTree());
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
