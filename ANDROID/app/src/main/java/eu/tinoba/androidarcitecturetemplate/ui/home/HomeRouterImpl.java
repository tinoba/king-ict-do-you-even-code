package eu.tinoba.androidarcitecturetemplate.ui.home;

import android.app.Activity;

public final class HomeRouterImpl implements HomeRouter {

    private final Activity activity;

    public HomeRouterImpl(final Activity activity) {
        this.activity = activity;
    }
}
