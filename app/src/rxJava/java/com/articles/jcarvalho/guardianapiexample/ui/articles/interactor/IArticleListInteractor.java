package com.articles.jcarvalho.guardianapiexample.ui.articles.interactor;

import com.articles.jcarvalho.guardianapiexample.models.SearchResponse;
import com.articles.jcarvalho.guardianapiexample.models.SearchResponseObject;

import rx.Observer;

/**
 * Created by jcarvalho on 8/9/2016.
 */
public interface IArticleListInteractor {
    void getArticleList(String sectionFilter, long page, Observer<SearchResponseObject> subscriber);
    void finish();
}
