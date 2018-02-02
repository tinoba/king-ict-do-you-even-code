package eu.tinoba.androidarcitecturetemplate.injection;

import eu.tinoba.androidarcitecturetemplate.application.TemplateApplication;
import eu.tinoba.androidarcitecturetemplate.injection.component.ActivityComponent;
import eu.tinoba.androidarcitecturetemplate.injection.component.ApplicationComponent;
import eu.tinoba.androidarcitecturetemplate.ui.base.activities.BaseActivity;

public final class ComponentFactory {

    private ComponentFactory() { }

    public static ApplicationComponent createApplicationComponent(final TemplateApplication application) {
        return ApplicationComponent.Initializer.init(application);
    }

    public static ActivityComponent createActivityComponent(final TemplateApplication application, final BaseActivity activity) {
        return ActivityComponent.Initializer.init(application.getApplicationComponent(), activity);
    }
}
