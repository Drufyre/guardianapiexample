package com.articles.jcarvalho.guardianapiexample.ui.articles;

import com.articles.jcarvalho.guardianapiexample.networking.Service;
import com.articles.jcarvalho.guardianapiexample.ui.annotations.PerView;
import com.articles.jcarvalho.guardianapiexample.ui.articles.interactor.ArticleListInteractor;
import com.articles.jcarvalho.guardianapiexample.ui.articles.interactor.IArticleListInteractor;
import com.articles.jcarvalho.guardianapiexample.ui.articles.presenter.ArticlePresenter;
import com.articles.jcarvalho.guardianapiexample.ui.articles.presenter.ArticlePresenterImplementation;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jcarvalho on 8/9/2016.
 */
@Module
public final class ContentListModule {
    @Provides
    @PerView
    IArticleListInteractor provideInteractor(Service service) {
        return new ArticleListInteractor(service);
    }

    @Provides
    @PerView
    ArticlePresenter providePresenter(IArticleListInteractor interactor) {
        return new ArticlePresenterImplementation(interactor);
    }
}
