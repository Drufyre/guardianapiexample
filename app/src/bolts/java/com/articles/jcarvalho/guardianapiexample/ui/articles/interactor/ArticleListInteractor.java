package com.articles.jcarvalho.guardianapiexample.ui.articles.interactor;

import com.articles.jcarvalho.guardianapiexample.models.SearchResponse;
import com.articles.jcarvalho.guardianapiexample.models.SearchResponseObject;
import com.articles.jcarvalho.guardianapiexample.networking.Service;

import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by jcarvalho on 8/9/2016.
 */
public class ArticleListInteractor implements IArticleListInteractor {

    private final Service service;

    public ArticleListInteractor(Service service) {
        this.service = service;
    }

    @Override
    public void getArticleList(String sectionFilter, long page, Continuation<SearchResponseObject, Void> continuation) {
        if(sectionFilter == null || sectionFilter.equals("")) {
            getUnfilteredArticleList(page, continuation);
        } else {
            getFilteredArticleList(sectionFilter, page, continuation);
        }
    }

    private void getUnfilteredArticleList(final long page, Continuation<SearchResponseObject, Void> continuation) {
        Task.callInBackground(new Callable<SearchResponseObject>() {
            @Override
            public SearchResponseObject call() throws Exception {
                SearchResponseObject responseObject = service.getContent(page).clone().execute().body();
                return responseObject;
            }
        }).continueWith(continuation, Task.UI_THREAD_EXECUTOR);
    }

    private void getFilteredArticleList(final String sectionFilter, final long page, Continuation<SearchResponseObject, Void> continuation) {
        Task.callInBackground(new Callable<SearchResponseObject>() {
            @Override
            public SearchResponseObject call() throws Exception {
                SearchResponseObject responseObject = service.getContent(sectionFilter, page).clone().execute().body();
                return responseObject;
            }
        }).continueWith(continuation, Task.UI_THREAD_EXECUTOR);
    }
}
