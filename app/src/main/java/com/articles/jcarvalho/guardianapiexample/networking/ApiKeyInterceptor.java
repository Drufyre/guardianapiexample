package com.articles.jcarvalho.guardianapiexample.networking;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jcarvalho on 8/7/2016.
 */
public class ApiKeyInterceptor implements Interceptor {

    private String apiKey;

    public ApiKeyInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl requestUrl = request.url().newBuilder()
                .addQueryParameter("api-key", apiKey)
                .build();

        Request.Builder modifiedRequest = request.newBuilder().url(requestUrl);

        Response response = chain.proceed(modifiedRequest.build());

        return response;
    }
}
