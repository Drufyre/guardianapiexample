package com.articles.jcarvalho.guardianapiexample.ui.sections.interactor;

import com.articles.jcarvalho.guardianapiexample.models.SectionResponseObject;
import com.articles.jcarvalho.guardianapiexample.networking.Service;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jcarvalho on 8/10/2016.
 */
public class SectionInteractor implements ISectionInteractor {

    private final Service service;
    private CompositeSubscription subscription = new CompositeSubscription();

    public SectionInteractor(Service service) {
        this.service = service;
    }

    @Override
    public void getSectionList(Observer<SectionResponseObject> subscriber) {
        subscription.add(service.getSections()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(subscriber));
    }

    @Override
    public void finish() {
        subscription.clear();
    }
}
