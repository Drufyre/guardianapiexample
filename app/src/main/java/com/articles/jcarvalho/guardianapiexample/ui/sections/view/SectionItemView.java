package com.articles.jcarvalho.guardianapiexample.ui.sections.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.articles.jcarvalho.guardianapiexample.R;
import com.articles.jcarvalho.guardianapiexample.models.Section;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jcarvalho on 8/9/2016.
 */
public class SectionItemView extends LinearLayout {

    @BindView(R.id.section_title) TextView sectionTitle;

    public SectionItemView(Context context) {
        super(context);
    }

    public SectionItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SectionItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bindTo(Section section) {
        sectionTitle.setText(section.getWebTitle());
    }
}
