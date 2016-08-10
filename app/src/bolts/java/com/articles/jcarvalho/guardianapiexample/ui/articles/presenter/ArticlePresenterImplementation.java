package com.articles.jcarvalho.guardianapiexample.ui.articles.presenter;

import com.articles.jcarvalho.guardianapiexample.models.SearchResponse;
import com.articles.jcarvalho.guardianapiexample.models.SearchResponseObject;
import com.articles.jcarvalho.guardianapiexample.ui.articles.interactor.IArticleListInteractor;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by jcarvalho on 8/9/2016.
 */
public class ArticlePresenterImplementation extends ArticlePresenter {

    private final IArticleListInteractor interactor;

    private final Continuation<SearchResponseObject, Void> continuation;

    public ArticlePresenterImplementation(IArticleListInteractor interactor) {
        this.interactor = interactor;
        continuation = new Continuation<SearchResponseObject, Void>() {
            @Override
            public Void then(Task<SearchResponseObject> task) throws Exception {
                if(task.isFaulted())
                {
                    view.displayError("We're sorry, there was a problem getting the articles.\nPlease try again later.");
                } else if(!task.isCancelled()) {
                    SearchResponseObject responseObject = task.getResult();
                    SearchResponse response = responseObject.getResponse();
                    if(response.getMessage() != null && !response.getMessage().equals(""))
                    {
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
                return null;
            }
        };
    }

    @Override
    public void getArticles() {
        getArticles("");
    }

    @Override
    public void getArticles(String section) {
        view.displayLoading();
        interactor.getArticleList(section,currentPage,continuation);
    }

    private void showError() {
        view.displayError("We're sorry, there was a problem getting the articles.\nPlease try again later.");
    }
}
