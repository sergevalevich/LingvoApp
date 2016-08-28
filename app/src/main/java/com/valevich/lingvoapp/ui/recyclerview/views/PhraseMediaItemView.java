package com.valevich.lingvoapp.ui.recyclerview.views;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Phrase;
import com.valevich.lingvoapp.ui.recyclerview.utils.ViewBinder;
import com.valevich.lingvoapp.utils.ImageLoader;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.grid_item_phrase_media)
public class PhraseMediaItemView extends FrameLayout implements ViewBinder<Phrase> {

    @ViewById(R.id.star)
    ImageView mStar;

    @ViewById(R.id.sound)
    ImageView mPlayButton;

    @ViewById(R.id.image)
    ImageView mImageView;

    @ViewById(R.id.text)
    TextView mPhraseLabel;

    @Bean
    ImageLoader mImageLoader;

    public PhraseMediaItemView(Context context) {
        super(context);
    }

    @Override
    public void bindData(Phrase item) {
        //bindImage(item.getImageUrl());
        bindImage(item.getImageResId());
        bindStar(item);
        bindPlayButton(item);
        bindLabels(item);
    }

    private void bindImage(int imageResId) {
        mImageLoader.loadImageByResId(imageResId,mImageView);
    }

    private void bindLabels(Phrase item) {
        mPhraseLabel.setText(item.getTranslation());
    }

    private void bindStar(final Phrase item) {

        if(item.isFavorite()) mStar.setImageResource(R.drawable.star_activ_animals);
        else mStar.setImageResource(R.drawable.star_animals);

        mStar.setOnClickListener(view -> {
            if(item.isFavorite()) {
                item.setFavorite(false);
                mStar.setImageResource(R.drawable.star_animals);
            } else {
                item.setFavorite(true);
                mStar.setImageResource(R.drawable.star_activ_animals);
            }
        });
    }

    private void bindPlayButton(Phrase item) {
        mPlayButton.setOnClickListener(view -> {
            // TODO: 19.08.2016 play sound
        });
    }
}
