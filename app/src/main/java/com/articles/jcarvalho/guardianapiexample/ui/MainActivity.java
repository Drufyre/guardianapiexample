package com.articles.jcarvalho.guardianapiexample.ui;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import com.articles.jcarvalho.guardianapiexample.AppComponent;
import com.articles.jcarvalho.guardianapiexample.ArticleApplication;
import com.articles.jcarvalho.guardianapiexample.R;
import com.articles.jcarvalho.guardianapiexample.models.Section;
import com.articles.jcarvalho.guardianapiexample.ui.articles.view.ArticleListView;
import com.articles.jcarvalho.guardianapiexample.ui.sections.view.SectionView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jcarvalho on 8/9/2016.
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.toolbar_layout) Toolbar toolbar;
    @BindView(R.id.section_navigation_drawer) SectionView navigationDrawer;
    @BindView(R.id.article_list_content) ArticleListView articleListView;

    MainActivityComponent component;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppComponent appComponent = ((ArticleApplication) getApplication()).getComponent();
        component = DaggerMainActivityComponent.builder().appComponent(appComponent).build();
        component.inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigationDrawer.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Section sectionListing = ((Section) adapterView.getAdapter().getItem(i));
                articleListView.setSection(sectionListing);
                drawerLayout.closeDrawers();
            }
        });
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    public MainActivityComponent getComponent() {
        return component;
    }
}
