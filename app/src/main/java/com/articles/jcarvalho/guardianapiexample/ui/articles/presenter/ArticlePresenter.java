package com.articles.jcarvalho.guardianapiexample.ui.articles.presenter;

import com.articles.jcarvalho.guardianapiexample.ui.BasePresenter;
import com.articles.jcarvalho.guardianapiexample.ui.articles.view.IArticleListView;

/**
 * Created by jcarvalho on 8/9/2016.
 */
public abstract class ArticlePresenter extends BasePresenter<IArticleListView> {
    protected long currentPage = 1;

    public abstract void getArticles();

    public abstract void getArticles(String section);

    public void resetPage() {
        currentPage = 1;
    }
}
