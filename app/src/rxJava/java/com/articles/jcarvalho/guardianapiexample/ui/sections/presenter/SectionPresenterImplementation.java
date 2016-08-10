package com.articles.jcarvalho.guardianapiexample.ui.sections.presenter;

import com.articles.jcarvalho.guardianapiexample.models.SectionResponse;
import com.articles.jcarvalho.guardianapiexample.models.SectionResponseObject;
import com.articles.jcarvalho.guardianapiexample.ui.sections.interactor.ISectionInteractor;

import rx.Observer;
import rx.functions.Action1;
import rx.observers.Observers;

/**
 * Created by jcarvalho on 8/10/2016.
 */
public class SectionPresenterImplementation extends SectionPresenter{

    private ISectionInteractor interactor;
    private Observer<SectionResponseObject> subscriber;

    public SectionPresenterImplementation(ISectionInteractor interactor) {
        this.interactor = interactor;
        subscriber = Observers.create(new Action1<SectionResponseObject>() {
            @Override
            public void call(SectionResponseObject sectionResponseObject) {
                SectionResponse response = sectionResponseObject.getResponse();
                if(response.getMessage() != null && !response.getMessage().equals("")) {
                    showError();
                } else if(response.getResults() != null && !response.getResults().isEmpty()) {
                    view.displaySections(response.getResults());
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

    private void showError() {
        view.displayError("There was a problem getting article sections. Please try again later.");
    }

    @Override
    public void getSections() {
        view.displayLoading();
        interactor.getSectionList(subscriber);
    }

    @Override
    public void unbindView() {
        super.unbindView();
        interactor.finish();
    }
}
