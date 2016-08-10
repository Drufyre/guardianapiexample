package com.articles.jcarvalho.guardianapiexample.ui.articles.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.articles.jcarvalho.guardianapiexample.R;
import com.articles.jcarvalho.guardianapiexample.models.Content;
import com.articles.jcarvalho.guardianapiexample.models.Section;
import com.articles.jcarvalho.guardianapiexample.ui.MainActivity;
import com.articles.jcarvalho.guardianapiexample.ui.MainActivityComponent;
import com.articles.jcarvalho.guardianapiexample.ui.articles.ContentListComponent;
import com.articles.jcarvalho.guardianapiexample.ui.articles.DaggerContentListComponent;
import com.articles.jcarvalho.guardianapiexample.ui.articles.content.ArticleDetailActivity;
import com.articles.jcarvalho.guardianapiexample.ui.articles.presenter.ArticlePresenter;
import com.articles.jcarvalho.guardianapiexample.ui.articles.view.misc.EndlessRecyclerOnScrollListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jcarvalho on 8/9/2016.
 */
public class ArticleListView extends LinearLayout implements IArticleListView, View.OnClickListener {

    @Inject ArticlePresenter presenter;

    @BindView(R.id.content_list) RecyclerView contentList;
    @BindView(R.id.toolbar_title) TextView toolbarTitle;
    @BindView(R.id.article_list_loading_view) ProgressBar loadingView;
    @BindView(R.id.article_list_error_view) TextView errorView;

    private ContentListComponent component;

    private ArticleAdapter adapter;
    private String sectionFilter = "";
    private MainActivity mainActivity;
    private EndlessRecyclerOnScrollListener scrollListener;

    public ArticleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mainActivity = ((MainActivity) context);
        MainActivityComponent mainActivityComponent = mainActivity.getComponent();
        component = DaggerContentListComponent.builder()
                .mainActivityComponent(mainActivityComponent)
                .build();
        component.inject(this);
    }


    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        contentList.setLayoutManager(layoutManager);
        scrollListener = new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                getArticleList();
            }
        };
        contentList.addOnScrollListener(scrollListener);
        errorView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.resetPage();
                getArticleList();
            }
        });
        toolbarTitle.setText(R.string.top_news);
        adapter = new ArticleAdapter(getContext());
        adapter.setItemClickListener(this);
        contentList.setAdapter(adapter);
        presenter.bindView(this);
        presenter.getArticles();
    }

    @Override
    public void onDetachedFromWindow() {
        presenter.unbindView();
        super.onDetachedFromWindow();
    }

    @Override
    public void displayArticles(List<Content> articleContent) {
        contentList.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        adapter = new ArticleAdapter(articleContent, getContext());
        adapter.setItemClickListener(this);
        contentList.setAdapter(adapter);
    }

    @Override
    public void appendArticles(List<Content> articleContent) {
        contentList.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        if(adapter == null || adapter.getItemCount() == 0) {
            displayArticles(articleContent);
        } else {
            adapter.appendArticles(articleContent);
        }
    }

    @Override
    public void displayLoading() {
        contentList.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void displayError(String errorText) {
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(errorText);
    }

    @Override
    public void setSection(Section section) {
        sectionFilter = section.getId();
        toolbarTitle.setText(section.getWebTitle());
        presenter.resetPage();
        scrollListener.resetPageCount();
        getArticleList();
    }

    private void getArticleList() {
        if(sectionFilter.equals("")) {
            presenter.getArticles();
        } else {
            presenter.getArticles(sectionFilter);
        }
    }

    private void launchArticleActivity(Content content) {
        Intent articleIntent = new Intent(mainActivity, ArticleDetailActivity.class);
        articleIntent.putExtra(ArticleDetailActivity.CONTENT_TITLE, content.getWebTitle());
        articleIntent.putExtra(ArticleDetailActivity.CONTENT_URL, content.getWebUrl());
        mainActivity.startActivity(articleIntent);
    }

    @Override
    public void onClick(View view) {
        int itemPosition = contentList.getChildAdapterPosition(view);
        launchArticleActivity(adapter.getArticles().get(itemPosition));
    }
}
