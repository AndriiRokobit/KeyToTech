package com.test.keytotech.di.app;

import com.test.keytotech.BuildConfig;
import com.test.keytotech.data.network.api.BaseApi;
import com.test.keytotech.di.qualifiers.SimpleOkHttp;
import com.test.keytotech.di.qualifiers.SimpleRetrofit;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.test.keytotech.data.network.constant.Endpoints.WEB_API_URL;
import static com.test.keytotech.data.network.constant.Timings.SIMPLE_CONNECT;
import static com.test.keytotech.data.network.constant.Timings.SIMPLE_READ;
import static com.test.keytotech.data.network.constant.Timings.SIMPLE_WRITE;

@Module
public abstract class NetworkModule {

    @Singleton
    @Provides
    public static BaseApi provideApi(@SimpleRetrofit Retrofit retrofit) {
        return retrofit.create(BaseApi.class);
    }

    @Provides
    @Singleton
    @SimpleRetrofit
    public static Retrofit provideSimpleRetrofit(Converter.Factory converterFactory, @SimpleOkHttp OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .baseUrl(WEB_API_URL).build();
    }

    @Provides
    @Singleton
    @SimpleOkHttp
    public static OkHttpClient provideSimpleOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(SIMPLE_READ, TimeUnit.SECONDS)
                .writeTimeout(SIMPLE_WRITE, TimeUnit.SECONDS)
                .connectTimeout(SIMPLE_CONNECT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    public static Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create();
    }
}
