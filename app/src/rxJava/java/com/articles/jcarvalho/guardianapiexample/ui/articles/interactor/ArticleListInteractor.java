package com.articles.jcarvalho.guardianapiexample.ui.articles.interactor;

import com.articles.jcarvalho.guardianapiexample.models.SearchResponse;
import com.articles.jcarvalho.guardianapiexample.models.SearchResponseObject;
import com.articles.jcarvalho.guardianapiexample.networking.Service;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jcarvalho on 8/9/2016.
 */
public class ArticleListInteractor implements IArticleListInteractor {

    private final Service service;
    private CompositeSubscription subscription = new CompositeSubscription();

    public ArticleListInteractor(Service service) {
        this.service = service;
    }

    @Override
    public void getArticleList(String sectionFilter, long page, Observer<SearchResponseObject> subscriber) {
        if(sectionFilter == null || sectionFilter.equals("")) {
            getUnfilteredArticleList(page, subscriber);
        } else {
            getFilteredArticleList(sectionFilter, page, subscriber);
        }
    }

    private void getUnfilteredArticleList(long page, Observer<SearchResponseObject> subscriber) {
        subscription.add(service.getContent(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(subscriber));
    }

    private void getFilteredArticleList(String sectionFilter, long page, Observer<SearchResponseObject> subscriber) {
        subscription.add(service.getContent(sectionFilter, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(subscriber));
    }

    @Override
    public void finish() {
        subscription.clear();
    }
}
