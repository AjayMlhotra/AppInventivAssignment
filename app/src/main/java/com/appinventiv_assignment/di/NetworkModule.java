package com.appinventiv_assignment.di;

import androidx.annotation.NonNull;

import com.appinventiv_assignment.BuildConfig;
import com.appinventiv_assignment.api.ApiService;
import com.appinventiv_assignment.utils.network_utils.InternetInterceptor;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {
    @Provides
    public String provideBaseUrl() {
        return BuildConfig.BASE_URL;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(InternetInterceptor internetInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(0, 30, TimeUnit.SECONDS))
                .followSslRedirects(true)
                .addInterceptor(internetInterceptor)
                .addInterceptor(chain -> {
                    Request newRequest = chain.request().newBuilder().build();
                    return chain.proceed(newRequest);
                });
       if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }

    @Provides
    @Singleton
    @NonNull
    public Retrofit provideRetrofit(@NonNull OkHttpClient okHttpClient, @NonNull String BASE_URL) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(@NonNull Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
