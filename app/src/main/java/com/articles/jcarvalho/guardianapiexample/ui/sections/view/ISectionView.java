package com.articles.jcarvalho.guardianapiexample.ui.sections.view;

import android.widget.AdapterView;

import com.articles.jcarvalho.guardianapiexample.models.Section;

import java.util.List;

/**
 * Created by jcarvalho on 8/10/2016.
 */
public interface ISectionView {

    void displaySections(List<Section> sectionList);

    void displayError(String error);

    void displayLoading();

    void setItemClickListener(AdapterView.OnItemClickListener listener);
}
