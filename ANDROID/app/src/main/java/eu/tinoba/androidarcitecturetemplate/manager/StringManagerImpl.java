package eu.tinoba.androidarcitecturetemplate.manager;

import android.content.res.Resources;
import android.support.annotation.StringRes;

public final class StringManagerImpl implements StringManager {

    private final Resources resources;

    public StringManagerImpl(final Resources resources) {
        this.resources = resources;
    }

    @Override
    public String getString(@StringRes int resourceId) {
        return resources.getString(resourceId);
    }
}
