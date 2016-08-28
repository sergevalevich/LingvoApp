package com.valevich.lingvoapp.ui.recyclerview.views;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Word;
import com.valevich.lingvoapp.ui.recyclerview.utils.ViewBinder;
import com.valevich.lingvoapp.utils.ImageLoader;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.dictionary_list_item)
public class DictionaryItemView extends RelativeLayout implements ViewBinder<Word> {

    @ViewById(R.id.word)
    TextView mWordLabel;

    @ViewById(R.id.word_picture)
    ImageView mWordPicture;

    @ViewById(R.id.play_button)
    ImageView mPlayButton;

    @Bean
    ImageLoader mImageLoader;

    public DictionaryItemView(Context context) {
        super(context);
    }

    @Override
    public void bindData(Word item) {
        bindImage(item.getImageResId());
        bindLabel(item.getTranslation());
        bindPlayButton();
    }

    private void bindLabel(String text) {
        mWordLabel.setText(text);
    }

    private void bindPlayButton() {
        mPlayButton.setOnClickListener(view -> {});
    }

    private void bindImage(int imageResId) {
        mImageLoader.loadImageByResId(imageResId,mWordPicture);
    }
}
