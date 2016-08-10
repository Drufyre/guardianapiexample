package com.articles.jcarvalho.guardianapiexample.ui.sections.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.articles.jcarvalho.guardianapiexample.R;
import com.articles.jcarvalho.guardianapiexample.models.Section;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jcarvalho on 8/8/2016.
 */
public class SectionAdapter extends BaseAdapter {

    private List<Section> sections;

    public SectionAdapter() {
        sections = new ArrayList<>();
    }

    public SectionAdapter(List<Section> sections) {

        setSections(sections);
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public int getCount() {
        return sections.size();
    }

    @Override
    public Section getItem(int position) {
        return sections.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_section_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.sectionTitle.setText(getItem(position).getWebTitle());
        return convertView;
    }

    public static class ViewHolder {
        @BindView(R.id.section_title) public TextView sectionTitle;
        public ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }

    }
}
