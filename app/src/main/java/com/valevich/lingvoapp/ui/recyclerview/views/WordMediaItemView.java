package com.valevich.lingvoapp.ui.recyclerview.views;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Word;
import com.valevich.lingvoapp.ui.recyclerview.utils.ViewBinder;
import com.valevich.lingvoapp.utils.ImageLoader;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.grid_item_word_media)
public class WordMediaItemView extends FrameLayout implements ViewBinder<Word>{

    @ViewById(R.id.star)
    ImageView mStar;

    @ViewById(R.id.sound)
    ImageView mPlayButton;

    @ViewById(R.id.image)
    ImageView mImageView;

    @ViewById(R.id.original)
    TextView mWordLabel;

    @ViewById(R.id.translate)
    TextView mTranslatedWordLabel;

    @Bean
    ImageLoader mImageLoader;

    public WordMediaItemView(Context context) {
        super(context);
    }

    @Override
    public void bindData(Word item) {
        bindImage(item.getImageUrl());
        bindStar(item);
        bindPlayButton(item);
        bindLabels(item);
    }

    private void bindImage(String imageUrl) {
        mImageLoader.loadImageByUrl(imageUrl,mImageView);
    }

    private void bindLabels(Word item) {
        mWordLabel.setText(item.getOriginal());
        mTranslatedWordLabel.setText(item.getTranslation());
    }

    private void bindStar(final Word item) {

        final boolean isFavorite = item.isFavorite();

        if(isFavorite) mStar.setImageResource(R.drawable.star_activ_animals);
        else mStar.setImageResource(R.drawable.star_animals);

        mStar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(isFavorite) {
                    item.setFavorite(false);
                    mStar.setImageResource(R.drawable.star_animals);
                } else {
                    item.setFavorite(true);
                    mStar.setImageResource(R.drawable.star_activ_animals);
                }
            }
        });
    }

    private void bindPlayButton(Word item) {
        mPlayButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 19.08.2016 play sound
            }
        });
    }
}
