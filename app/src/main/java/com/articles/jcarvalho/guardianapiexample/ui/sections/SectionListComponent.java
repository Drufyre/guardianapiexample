package com.articles.jcarvalho.guardianapiexample.ui.sections;

import com.articles.jcarvalho.guardianapiexample.ui.MainActivityComponent;
import com.articles.jcarvalho.guardianapiexample.ui.annotations.PerView;
import com.articles.jcarvalho.guardianapiexample.ui.sections.view.SectionView;

import dagger.Component;

/**
 * Created by jcarvalho on 8/10/2016.
 */
@PerView
@Component (dependencies = MainActivityComponent.class,
            modules = SectionListModule.class)
public interface SectionListComponent {
    void inject(SectionView sectionView);
}
