package eu.tinoba.androidarcitecturetemplate.injection.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import eu.tinoba.androidarcitecturetemplate.injection.scope.ForActivity;
import eu.tinoba.androidarcitecturetemplate.ui.base.activities.BaseActivity;

@Module
public final class ActivityModule {

    private final BaseActivity baseActivity;

    public ActivityModule(final BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @ForActivity
    @Provides
    public Activity provideActivity() {
        return baseActivity;
    }
}
