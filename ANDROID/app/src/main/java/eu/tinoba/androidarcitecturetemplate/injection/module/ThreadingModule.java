package eu.tinoba.androidarcitecturetemplate.injection.module;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public final class ThreadingModule {

    public static final String OBSERVE_SCHEDULER = "SubscribeScheduler";

    public static final String SUBSCRIBE_SCHEDULER = "ObserveScheduler";

    @Provides
    @Singleton
    @Named(OBSERVE_SCHEDULER)
    Scheduler provideAndroidSchedulersMainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named(SUBSCRIBE_SCHEDULER)
    Scheduler provideSchedulersIo() {
        return Schedulers.io();
    }
}
