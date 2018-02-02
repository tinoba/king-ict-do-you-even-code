package eu.tinoba.androidarcitecturetemplate.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.tinoba.androidarcitecturetemplate.application.TemplateApplication;
import eu.tinoba.androidarcitecturetemplate.device.ApplicationInformation;
import eu.tinoba.androidarcitecturetemplate.device.ApplicationInformationImpl;
import eu.tinoba.androidarcitecturetemplate.device.DeviceInformation;
import eu.tinoba.androidarcitecturetemplate.device.DeviceInformationImpl;

@Module
public final class DeviceModule {

    @Provides
    @Singleton
    public DeviceInformation provideDeviceInformation() {
        return new DeviceInformationImpl();
    }

    @Provides
    @Singleton
    public ApplicationInformation provideApplicationInformation(final TemplateApplication application) {
        return new ApplicationInformationImpl(application, application.getPackageManager());
    }
}
