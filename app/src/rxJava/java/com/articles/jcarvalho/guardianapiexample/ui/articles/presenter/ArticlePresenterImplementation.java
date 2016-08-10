package com.articles.jcarvalho.guardianapiexample.ui.articles.presenter;

import com.articles.jcarvalho.guardianapiexample.models.SearchResponse;
import com.articles.jcarvalho.guardianapiexample.models.SearchResponseObject;
import com.articles.jcarvalho.guardianapiexample.ui.articles.interactor.IArticleListInteractor;

import rx.Observer;
import rx.functions.Action1;
import rx.observers.Observers;

/**
 * Created by jcarvalho on 8/9/2016.
 */
public class ArticlePresenterImplementation extends ArticlePresenter {

    private final IArticleListInteractor interactor;

    private final Observer<SearchResponseObject> searchContentObserver;

    public ArticlePresenterImplementation(IArticleListInteractor interactor) {
        this.interactor = interactor;
        searchContentObserver = Observers.create(new Action1<SearchResponseObject>() {
            @Override
            public void call(SearchResponseObject searchResponseObject) {
                SearchResponse response = searchResponseObject.getResponse();
                if(response.getMessage() != null && !response.getMessage().equals("")) {
                    showError();
                } else if(response.getResults() != null && !response.getResults().isEmpty()) {
                    if(currentPage < 2) {
                        view.displayArticles(response.getResults());
                    } else {
                        view.appendArticles(response.getResults());
                    }
                    currentPage = response.getCurrentPage() + 1;
                } else {
                    showError();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                showError();
            }
        });
    }

    @Override
    public void getArticles() {
        getArticles("");
    }

    @Override
    public void getArticles(String section) {
        if(currentPage < 2) {
            view.displayLoading();
        }
        interactor.getArticleList(section, currentPage, searchContentObserver);
    }

    private void showError() {
        view.displayError("We're sorry, there was a problem getting the articles.\nPlease try again later.");
    }

    @Override
    public void unbindView() {
        super.unbindView();
        interactor.finish();
    }
}
