package com.articles.jcarvalho.guardianapiexample.ui.sections.interactor;

import com.articles.jcarvalho.guardianapiexample.models.SectionResponseObject;
import com.articles.jcarvalho.guardianapiexample.networking.Service;

import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by jcarvalho on 8/10/2016.
 */
public class SectionInteractor implements ISectionInteractor {

    private Service service;

    public SectionInteractor(Service service) {
        this.service = service;
    }

    @Override
    public void getSections(Continuation<SectionResponseObject, Void> continuation) {
        Task.callInBackground(new Callable<SectionResponseObject>() {
            @Override
            public SectionResponseObject call() throws Exception {
                SectionResponseObject responseObject = service.getSections().clone().execute().body();
                return responseObject;
            }
        }).continueWith(continuation, Task.UI_THREAD_EXECUTOR);
    }
}
