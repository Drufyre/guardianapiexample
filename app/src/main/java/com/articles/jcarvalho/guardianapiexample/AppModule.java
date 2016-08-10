package com.articles.jcarvalho.guardianapiexample;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jcarvalho on 8/8/2016.
 */
@Module
public class AppModule {

    ArticleApplication application;

    public AppModule(ArticleApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }
}
