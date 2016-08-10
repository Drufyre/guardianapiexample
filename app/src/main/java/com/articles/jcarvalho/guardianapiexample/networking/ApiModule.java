package com.articles.jcarvalho.guardianapiexample.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jcarvalho on 8/8/2016.
 */
@Module
public class ApiModule {

    String baseUrl;

    private final String apiKey = "f9aeb461-43a7-43ba-8328-08ae6482e03c";

    public ApiModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public OkHttpClient provideClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new ApiKeyInterceptor(apiKey))
                .build();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        // Add any needed customizations
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    public Service provideService(Retrofit retrofit) {
        return retrofit.create(Service.class);
    }

}
