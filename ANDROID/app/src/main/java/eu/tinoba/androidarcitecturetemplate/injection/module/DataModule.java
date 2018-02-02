package eu.tinoba.androidarcitecturetemplate.injection.module;

import android.provider.Settings;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.tinoba.androidarcitecturetemplate.application.TemplateApplication;
import eu.tinoba.androidarcitecturetemplate.data.storage.PreferenceRepository;
import eu.tinoba.androidarcitecturetemplate.data.storage.SecureSharedPreferences;
import eu.tinoba.androidarcitecturetemplate.data.storage.TemplatePreferences;

@Module
public final class DataModule {

    private static final String PREFS_NAME = "templateSecureStorage";

    @Singleton
    @Provides
    TemplatePreferences provideTemplatePreferences(final SecureSharedPreferences securePreferences) {
        return TemplatePreferences.create(securePreferences);
    }

    @Provides
    @Singleton
    PreferenceRepository providePreferenceRepository(final TemplatePreferences templatePreferences) {
        return templatePreferences;
    }

    @Provides
    @Singleton
    public SecureSharedPreferences provideSecureSharedPreferences(final TemplateApplication context) {
        final String androidSecret = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return new SecureSharedPreferences(context, context.getSharedPreferences(PREFS_NAME, 0), androidSecret);
    }
}
