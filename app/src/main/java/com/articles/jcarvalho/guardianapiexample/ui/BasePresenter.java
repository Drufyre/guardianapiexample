package com.articles.jcarvalho.guardianapiexample.ui;

/**
 * Created by jcarvalho on 8/9/2016.
 */
public abstract class BasePresenter<T>  {
    protected T view;

    public void bindView(T view) {
        this.view = view;
    }

    public void unbindView() {
        this.view = null;
    }

}
