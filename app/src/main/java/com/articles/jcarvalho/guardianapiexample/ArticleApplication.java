package com.articles.jcarvalho.guardianapiexample;

import android.app.Application;

import com.articles.jcarvalho.guardianapiexample.networking.ApiModule;

/**
 * Created by jcarvalho on 8/9/2016.
 */
public class ArticleApplication extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule("http://content.guardianapis.com/"))
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
