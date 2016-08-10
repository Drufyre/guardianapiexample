package com.articles.jcarvalho.guardianapiexample.ui.articles.view;

import android.support.annotation.StringRes;

import com.articles.jcarvalho.guardianapiexample.models.Content;
import com.articles.jcarvalho.guardianapiexample.models.Section;

import java.util.List;

/**
 * Created by jcarvalho on 8/9/2016.
 */
public interface IArticleListView {

    void displayArticles(List<Content> articleContent);

    void appendArticles(List<Content> articleContent);

    void displayLoading();

    void displayError(String errorText);

    void setSection(Section section);
}
