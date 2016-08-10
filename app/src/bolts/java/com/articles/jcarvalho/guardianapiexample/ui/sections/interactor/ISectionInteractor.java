package com.articles.jcarvalho.guardianapiexample.ui.sections.interactor;

import com.articles.jcarvalho.guardianapiexample.models.SectionResponseObject;

import bolts.Continuation;

/**
 * Created by jcarvalho on 8/10/2016.
 */
public interface ISectionInteractor {
    void getSections(Continuation<SectionResponseObject, Void> continuation);
}
