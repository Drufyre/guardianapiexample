package com.articles.jcarvalho.guardianapiexample.models;

import java.util.List;

/**
 * Created by jcarvalho on 8/7/2016.
 */
public class SearchResponse {
    private String status;
    private long total;
    private long startIndex;
    private long pageSize;
    private long currentPage;
    private long pages;
    private List<Content> results;
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

    public long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(long startIndex) {
        this.startIndex = startIndex;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public List<Content> getResults() {
        return results;
    }

    public void setResults(List<Content> results) {
        this.results = results;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
