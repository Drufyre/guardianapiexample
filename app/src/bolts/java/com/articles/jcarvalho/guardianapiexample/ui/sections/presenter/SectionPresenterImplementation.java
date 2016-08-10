package com.articles.jcarvalho.guardianapiexample.ui.sections.presenter;

import com.articles.jcarvalho.guardianapiexample.models.SectionResponse;
import com.articles.jcarvalho.guardianapiexample.models.SectionResponseObject;
import com.articles.jcarvalho.guardianapiexample.ui.sections.interactor.ISectionInteractor;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by jcarvalho on 8/10/2016.
 */
public class SectionPresenterImplementation extends SectionPresenter {

    private final ISectionInteractor interactor;
    private final Continuation<SectionResponseObject, Void> continuation;

    public SectionPresenterImplementation(ISectionInteractor interactor) {
        this.interactor = interactor;
        continuation = new Continuation<SectionResponseObject, Void>() {
            @Override
            public Void then(Task<SectionResponseObject> task) throws Exception {
                if(task.isFaulted()) {

                } else if(!task.isCancelled()) {
                    SectionResponseObject sectionResponseObject = task.getResult();
                    SectionResponse response = sectionResponseObject.getResponse();
                    if(response.getMessage() != null && !response.getMessage().equals("")) {
                        showError();
                    } else if(response.getResults() != null && !response.getResults().isEmpty()) {
                        view.displaySections(response.getResults());
                    } else {
                        showError();
                    }
                }
                return null;
            }
        };
    }

    private void showError() {
        view.displayError("There was a problem getting article sections. Please try again later.");
    }

    @Override
    public void getSections() {
        view.displayLoading();
        interactor.getSections(continuation);
    }
}
