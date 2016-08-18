package com.valevich.lingvoapp.ui.recyclerview.views;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.CardCategory;
import com.valevich.lingvoapp.ui.recyclerview.utils.ViewBinder;
import com.valevich.lingvoapp.utils.ImageLoader;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.grid_item_card)
public class CardCategoryItemView extends FrameLayout implements ViewBinder<CardCategory> {

    @ViewById(R.id.image)
    ImageView mImageView;

    @ViewById(R.id.category_name)
    TextView mCategoryNameLabel;

    @ViewById(R.id.progress)
    ProgressBar mProgressBar;

    @Bean
    ImageLoader mImageLoader;

    public CardCategoryItemView(Context context) {
        super(context);
    }

    @Override
    public void bindData(CardCategory item) {
        mImageLoader.loadImageByUrl(item.getImageUrl(),mImageView);
        mCategoryNameLabel.setText(item.getName());
        mProgressBar.setProgress(item.getProgress());
    }
}
