package com.articles.jcarvalho.guardianapiexample.ui.sections.presenter;

import com.articles.jcarvalho.guardianapiexample.ui.BasePresenter;
import com.articles.jcarvalho.guardianapiexample.ui.sections.view.ISectionView;

/**
 * Created by jcarvalho on 8/10/2016.
 */
public abstract class SectionPresenter extends BasePresenter<ISectionView> {
    public abstract void getSections();
}
