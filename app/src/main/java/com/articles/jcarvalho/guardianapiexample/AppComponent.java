package com.articles.jcarvalho.guardianapiexample;

import android.app.Application;

import com.articles.jcarvalho.guardianapiexample.networking.ApiModule;
import com.articles.jcarvalho.guardianapiexample.networking.Service;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by jcarvalho on 8/9/2016.
 */
@Singleton
@Component(
        modules = {
            AppModule.class,
            ApiModule.class
        }
)
public interface AppComponent {

    void inject(Application application);

    Service service();
    Retrofit retrofit();
}
