package com.articles.jcarvalho.guardianapiexample.ui.articles.content;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.articles.jcarvalho.guardianapiexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleDetailActivity extends AppCompatActivity {

    public static final String CONTENT_URL = "content_url";
    public static final String CONTENT_TITLE = "content_title";

    @BindView(R.id.article_content_view) WebView articleContent;
    @BindView(R.id.toolbar_title) TextView articleTitle;
    @BindView(R.id.toolbar_layout) Toolbar toolbar;

    private String contentUrl;
    private String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        ButterKnife.bind(this);
        contentUrl = getIntent().getStringExtra(CONTENT_URL);
        title = getIntent().getStringExtra(CONTENT_TITLE);

        articleTitle.setText(title);
        articleContent.loadUrl(contentUrl);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
