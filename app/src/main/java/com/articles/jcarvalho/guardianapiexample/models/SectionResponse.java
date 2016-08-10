package com.articles.jcarvalho.guardianapiexample.models;

import java.util.List;

/**
 * Created by jcarvalho on 8/7/2016.
 */
public class SectionResponse {
    private String status;
    private long total;
    private List<Section> results;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Section> getResults() {
        return results;
    }

    public void setResults(List<Section> results) {
        this.results = results;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
