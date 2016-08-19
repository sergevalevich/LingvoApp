package com.valevich.lingvoapp.ui.recyclerview.views;


import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.PhraseCategory;
import com.valevich.lingvoapp.ui.recyclerview.utils.ViewBinder;
import com.valevich.lingvoapp.utils.ImageLoader;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.phrase_category_item)
public class PhraseBookItemView extends RelativeLayout implements ViewBinder<PhraseCategory> {
    public PhraseBookItemView(Context context) {
        super(context);
    }

    @ViewById(R.id.phrase_category_icon)
    ImageView mImageView;

    @ViewById(R.id.phrase_category_title)
    TextView mTitle;

    @ViewById(R.id.phrase_category_examples)
    TextView mExamplesLabel;

    @Bean
    ImageLoader mImageLoader;

    @Override
    public void bindData(PhraseCategory item) {
        mImageLoader.loadImageByResId(item.getImageResId(),mImageView);
        mTitle.setText(item.getName());
        mExamplesLabel.setText(item.getExamples());
    }
}
