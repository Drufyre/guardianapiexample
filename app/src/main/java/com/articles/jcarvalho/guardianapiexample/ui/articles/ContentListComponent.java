package com.articles.jcarvalho.guardianapiexample.ui.articles;

import com.articles.jcarvalho.guardianapiexample.AppComponent;
import com.articles.jcarvalho.guardianapiexample.ui.MainActivityComponent;
import com.articles.jcarvalho.guardianapiexample.ui.annotations.PerView;
import com.articles.jcarvalho.guardianapiexample.ui.articles.view.ArticleListView;

import dagger.Component;

/**
 * Created by jcarvalho on 8/9/2016.
 */
@PerView
@Component(
        dependencies = MainActivityComponent.class,
        modules = ContentListModule.class
)
public interface ContentListComponent {
    void inject(ArticleListView view);
}
