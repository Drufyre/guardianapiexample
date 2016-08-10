package com.articles.jcarvalho.guardianapiexample.ui.sections;

import com.articles.jcarvalho.guardianapiexample.networking.Service;
import com.articles.jcarvalho.guardianapiexample.ui.annotations.PerView;
import com.articles.jcarvalho.guardianapiexample.ui.articles.interactor.ArticleListInteractor;
import com.articles.jcarvalho.guardianapiexample.ui.articles.interactor.IArticleListInteractor;
import com.articles.jcarvalho.guardianapiexample.ui.articles.presenter.ArticlePresenter;
import com.articles.jcarvalho.guardianapiexample.ui.articles.presenter.ArticlePresenterImplementation;
import com.articles.jcarvalho.guardianapiexample.ui.sections.interactor.ISectionInteractor;
import com.articles.jcarvalho.guardianapiexample.ui.sections.interactor.SectionInteractor;
import com.articles.jcarvalho.guardianapiexample.ui.sections.presenter.SectionPresenter;
import com.articles.jcarvalho.guardianapiexample.ui.sections.presenter.SectionPresenterImplementation;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jcarvalho on 8/10/2016.
 */
@Module
public final class SectionListModule {
    @Provides
    @PerView
    ISectionInteractor provideInteractor(Service service) {
        return new SectionInteractor(service);
    }

    @Provides
    @PerView
    SectionPresenter providePresenter(ISectionInteractor interactor) {
        return new SectionPresenterImplementation(interactor);
    }
}
