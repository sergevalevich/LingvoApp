package com.valevich.lingvoapp.ui.recyclerview.views;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Word;
import com.valevich.lingvoapp.ui.recyclerview.utils.ViewBinder;
import com.valevich.lingvoapp.utils.WordShareActionProvider;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.word_no_image_list_item)
public class WordNoImageItemView extends LinearLayout implements ViewBinder<Word> {

    @ViewById(R.id.star)
    ImageView mStar;

    @ViewById(R.id.sound)
    ImageView mPlayButton;

    @ViewById(R.id.share)
    ImageView mShareButton;

    @ViewById(R.id.original)
    TextView mWordLabel;

    @ViewById(R.id.translation)
    TextView mTranslatedWordLabel;

    @Bean
    WordShareActionProvider mShareActionProvider;

    public WordNoImageItemView(Context context) {
        super(context);
    }

    @Override
    public void bindData(Word item) {
        bindLabels(item);
        bindStar(item);
        bindPlayButton(item);
        bindShareAction(item);
    }

    private void bindLabels(Word item) {
        mWordLabel.setText(item.getOriginal());
        mTranslatedWordLabel.setText(item.getTranslation());
    }

    private void bindStar(final Word item) {

        final boolean isFavorite = item.isFavorite();

        if(isFavorite) mStar.setImageResource(R.drawable.star_activ_animals);
        else mStar.setImageResource(R.drawable.star_animals_no_visibal_activ);

        mStar.setOnClickListener(view -> {
            if(isFavorite) {
                item.setFavorite(false);
                mStar.setImageResource(R.drawable.star_animals_no_visibal_activ);
            } else {
                item.setFavorite(true);
                mStar.setImageResource(R.drawable.star_activ_animals);
            }
        });
    }

    private void bindShareAction(Word item) {
        mShareButton.setOnClickListener(view -> mShareActionProvider.share(item));
    }

    private void bindPlayButton(Word item) {
        mPlayButton.setOnClickListener(view -> {
            // TODO: 19.08.2016 play sound
        });
    }

}
