package eu.tinoba.androidarcitecturetemplate.data.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class NetworkInterceptor implements Interceptor {

    private static final String HEADER_PLATFORM = "X-Mobile-OS";

    private static final String PLATFORM_ANDROID = "Android";

    private static final String HEADER_OS_VERSION = "X-Mobile-OS-Version";

    private static final String HEADER_APP_VERSION = "X-Mobile-App-Build";

    private final int osVersionCode;

    private final String appVersionName;

    public NetworkInterceptor(final int osVersionCode, final String appVersionName) {
        this.osVersionCode = osVersionCode;
        this.appVersionName = appVersionName;
    }

    @Override
    public Response intercept(final Chain chain) throws IOException {
        final Request request = chain.request();

        final Request.Builder builder = request.newBuilder();
        builder.addHeader(HEADER_PLATFORM, PLATFORM_ANDROID)
               .addHeader(HEADER_OS_VERSION, String.valueOf(osVersionCode))
               .addHeader(HEADER_APP_VERSION, appVersionName);

        return chain.proceed(builder.build());
    }
}
