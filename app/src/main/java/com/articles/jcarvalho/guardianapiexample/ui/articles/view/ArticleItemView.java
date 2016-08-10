package com.articles.jcarvalho.guardianapiexample.ui.articles.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.articles.jcarvalho.guardianapiexample.R;
import com.articles.jcarvalho.guardianapiexample.models.Content;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jcarvalho on 8/8/2016.
 */
public class ArticleItemView extends LinearLayout {

    @BindView(R.id.article_title) TextView articleTitle;
    @BindView(R.id.article_date) TextView articleDate;
    @BindView(R.id.thumbnail) ImageView thumbNail;
    public ArticleItemView(Context context) {
        super(context);
    }

    public ArticleItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ArticleItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bindTo(Content content, Context context) {
        articleTitle.setText(content.getWebTitle());
        articleDate.setText(content.getWebPublicationDate().toString());

        // Load thumbnail:
        if(content.getFields() != null && content.getFields().getThumbnail() != null) {
            Picasso.with(context).load(content.getFields().getThumbnail()).into(thumbNail);
        }
    }


}
