package com.articles.jcarvalho.guardianapiexample.networking;

import com.articles.jcarvalho.guardianapiexample.models.SearchResponseObject;
import com.articles.jcarvalho.guardianapiexample.models.SectionResponseObject;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jcarvalho on 8/7/2016.
 */
public interface Service {

    @GET("search?show-fields=thumbnail")
    Observable<SearchResponseObject> getContent(@Query("page") long page);

    @GET("search?show-fields=thumbnail")
    Observable<SearchResponseObject> getContent(@Query("section") String filter, @Query("page") long page);

    @GET("sections")
    Observable<SectionResponseObject> getSections();
}
