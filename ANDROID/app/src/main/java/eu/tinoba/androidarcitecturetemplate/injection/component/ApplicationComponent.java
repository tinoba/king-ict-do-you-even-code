package eu.tinoba.androidarcitecturetemplate.injection.component;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import eu.tinoba.androidarcitecturetemplate.application.TemplateApplication;
import eu.tinoba.androidarcitecturetemplate.data.service.NetworkService;
import eu.tinoba.androidarcitecturetemplate.data.storage.TemplatePreferences;
import eu.tinoba.androidarcitecturetemplate.device.ApplicationInformation;
import eu.tinoba.androidarcitecturetemplate.device.DeviceInformation;
import eu.tinoba.androidarcitecturetemplate.injection.module.ApiModule;
import eu.tinoba.androidarcitecturetemplate.injection.module.ApplicationModule;
import eu.tinoba.androidarcitecturetemplate.injection.module.DataModule;
import eu.tinoba.androidarcitecturetemplate.injection.module.DeviceModule;
import eu.tinoba.androidarcitecturetemplate.injection.module.ManagerModule;
import eu.tinoba.androidarcitecturetemplate.injection.module.ThreadingModule;
import eu.tinoba.androidarcitecturetemplate.injection.module.UseCaseModule;
import eu.tinoba.androidarcitecturetemplate.manager.StringManager;
import io.reactivex.Scheduler;
import okhttp3.OkHttpClient;

import static eu.tinoba.androidarcitecturetemplate.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static eu.tinoba.androidarcitecturetemplate.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                ApiModule.class,
                ManagerModule.class,
                DataModule.class,
                ThreadingModule.class,
                UseCaseModule.class,
                DeviceModule.class
        }
)

public interface ApplicationComponent extends ApplicationComponentInjects {

    final class Initializer {

        private Initializer() {
        }

        public static ApplicationComponent init(final TemplateApplication templateApplication) {
            return DaggerApplicationComponent.builder()
                                             .applicationModule(new ApplicationModule(templateApplication))
                                             .apiModule(new ApiModule())
                                             .build();
        }
    }

    @Named(OBSERVE_SCHEDULER)
    Scheduler getObserveScheduler();

    @Named(SUBSCRIBE_SCHEDULER)
    Scheduler getSubscribeScheduler();

    StringManager getStringManager();

    OkHttpClient getOkHttpClient();

    DeviceInformation getDeviceInformation();

    ApplicationInformation getApplicationInformation();

    TemplatePreferences getTemplatePreferences();

    NetworkService getNetworkService();
}
