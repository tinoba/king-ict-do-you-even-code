package eu.tinoba.androidarcitecturetemplate.device;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class ApplicationInformationImpl implements ApplicationInformation {

    private static final int NO_FLAG = 0;

    private final Application application;

    private final PackageManager packageManager;

    public ApplicationInformationImpl(final Application application, final PackageManager packageManager) {
        this.application = application;
        this.packageManager = packageManager;
    }

    @Override
    public String getVersionName() {
        final PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(application.getPackageName(), NO_FLAG);
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }

        return packageInfo.versionName;
    }
}
