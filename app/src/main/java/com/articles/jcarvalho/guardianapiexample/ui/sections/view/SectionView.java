package com.articles.jcarvalho.guardianapiexample.ui.sections.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.articles.jcarvalho.guardianapiexample.R;
import com.articles.jcarvalho.guardianapiexample.models.Section;
import com.articles.jcarvalho.guardianapiexample.ui.MainActivity;
import com.articles.jcarvalho.guardianapiexample.ui.MainActivityComponent;
import com.articles.jcarvalho.guardianapiexample.ui.sections.DaggerSectionListComponent;
import com.articles.jcarvalho.guardianapiexample.ui.sections.SectionListComponent;
import com.articles.jcarvalho.guardianapiexample.ui.sections.presenter.SectionPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jcarvalho on 8/10/2016.
 */
public class SectionView extends LinearLayout implements ISectionView {

    @Inject SectionPresenter presenter;

    @BindView(R.id.section_header) TextView sectionHeader;
    @BindView(R.id.section_list) ListView sectionListView;
    @BindView(R.id.section_list_loading_view) ProgressBar loadingView;

    private MainActivity mainActivity;
    private SectionListComponent component;
    private AdapterView.OnItemClickListener sectionSelectionClickListener;
    private SectionAdapter adapter;

    public SectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mainActivity = ((MainActivity) context);
        MainActivityComponent mainActivityComponent = mainActivity.getComponent();
        component = DaggerSectionListComponent.builder()
                .mainActivityComponent(mainActivityComponent)
                .build();
        component.inject(this);
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        sectionHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getSections();
            }
        });
        presenter.bindView(this);
        presenter.getSections();
    }

    @Override
    public void onDetachedFromWindow() {
        presenter.unbindView();
        super.onDetachedFromWindow();
    }

    public AdapterView.OnItemClickListener getSectionSelectionClickListener() {
        return sectionSelectionClickListener;
    }

    @Override
    public void displaySections(List<Section> sectionList) {
        sectionListView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.VISIBLE);
        sectionHeader.setText(R.string.sections);
        adapter = new SectionAdapter(sectionList);
        sectionListView.setAdapter(adapter);
        sectionListView.setOnItemClickListener(sectionSelectionClickListener);

    }

    @Override
    public void displayError(String error) {
        sectionHeader.setText(error);
    }

    @Override
    public void displayLoading() {
        sectionListView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItemClickListener(AdapterView.OnItemClickListener listener) {
        this.sectionSelectionClickListener = listener;
    }
}
