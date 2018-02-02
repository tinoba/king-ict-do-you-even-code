package eu.tinoba.androidarcitecturetemplate.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.tinoba.androidarcitecturetemplate.application.TemplateApplication;
import eu.tinoba.androidarcitecturetemplate.manager.StringManager;
import eu.tinoba.androidarcitecturetemplate.manager.StringManagerImpl;

@Module
public final class ManagerModule {

    @Provides
    @Singleton
    StringManager provideStringManager(final TemplateApplication application) {
        return new StringManagerImpl(application.getResources());
    }
}
