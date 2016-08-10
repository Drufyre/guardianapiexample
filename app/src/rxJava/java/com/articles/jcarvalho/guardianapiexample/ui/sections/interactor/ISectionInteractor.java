package com.articles.jcarvalho.guardianapiexample.ui.sections.interactor;

import com.articles.jcarvalho.guardianapiexample.models.SectionResponseObject;

import rx.Observer;

/**
 * Created by jcarvalho on 8/10/2016.
 */
public interface ISectionInteractor {
    void getSectionList(Observer<SectionResponseObject> subscriber);
    void finish();
}
