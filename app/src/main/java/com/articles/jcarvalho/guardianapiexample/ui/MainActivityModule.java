package com.articles.jcarvalho.guardianapiexample.ui;

import com.articles.jcarvalho.guardianapiexample.ui.annotations.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jcarvalho on 8/9/2016.
 */
@Module
public class MainActivityModule {

    private final MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    MainActivity provideActivity() {
        return activity;
    }
}
