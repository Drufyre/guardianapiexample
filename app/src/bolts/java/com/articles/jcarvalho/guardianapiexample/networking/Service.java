package com.articles.jcarvalho.guardianapiexample.networking;

import com.articles.jcarvalho.guardianapiexample.models.SearchResponseObject;
import com.articles.jcarvalho.guardianapiexample.models.SectionResponseObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jcarvalho on 8/9/2016.
 */
public interface Service {

    @GET("search?show-fields=thumbnail")
    Call<SearchResponseObject> getContent(@Query("page") long page);

    @GET("search?show-fields=thumbnail")
    Call<SearchResponseObject> getContent(@Query("section") String filter, @Query("page") long page);

    @GET("sections")
    Call<SectionResponseObject> getSections();
}
