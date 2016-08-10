package com.articles.jcarvalho.guardianapiexample.ui;

import com.articles.jcarvalho.guardianapiexample.AppComponent;
import com.articles.jcarvalho.guardianapiexample.networking.Service;
import com.articles.jcarvalho.guardianapiexample.ui.annotations.PerActivity;
import com.articles.jcarvalho.guardianapiexample.ui.articles.view.ArticleListView;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by jcarvalho on 8/9/2016.
 */
@PerActivity
@Component(
        dependencies = AppComponent.class,
        modules = MainActivityModule.class
)
public interface MainActivityComponent {
    void inject(MainActivity activity);

    Service service();
    Retrofit retrofit();
}
