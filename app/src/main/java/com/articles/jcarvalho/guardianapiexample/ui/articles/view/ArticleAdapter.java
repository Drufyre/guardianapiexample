package com.articles.jcarvalho.guardianapiexample.ui.articles.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.articles.jcarvalho.guardianapiexample.R;
import com.articles.jcarvalho.guardianapiexample.models.Content;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jcarvalho on 8/8/2016.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<Content> articles;
    private Context context;
    private View.OnClickListener itemClickListener;

    public ArticleAdapter(Context context) {
        articles = new ArrayList<>();
        this.context = context;
    }

    public ArticleAdapter(List<Content> articles, Context context) {
        setArticles(articles);
        this.context = context;
    }

    public List<Content> getArticles() {
        return articles;
    }

    public void setArticles(List<Content> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    public void appendArticles(List<Content> articles) {
        this.articles.addAll(articles);
        notifyDataSetChanged();
    }

    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_article_item, parent, false);
        itemView.setOnClickListener(itemClickListener);
        return new ArticleAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArticleAdapter.ViewHolder holder, int position) {
        holder.bindTo(articles.get(position), context);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public View.OnClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(View.OnClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ArticleItemView itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = (ArticleItemView)itemView;
        }

        public void bindTo(Content content, Context context) {
            itemView.bindTo(content, context);
        }
    }
}
