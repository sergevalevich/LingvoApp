package com.valevich.lingvoapp.stubmodel;


import com.valevich.lingvoapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Training {

    TRANSLATION_WORD("ПЕРЕВОД СЛОВО",R.drawable.translate_word),
    WORD_TRANSLATION("СЛОВО ПЕРЕВОД", R.drawable.word_translate),
    WORD_PICTURE("СЛОВО КАРТИНКА", R.drawable.word_picture),
    PICTURE_WORD("КАРТИНКА СЛОВО", R.drawable.picture_word),
    SOUND_WORD("ЗВУК СЛОВО", R.drawable.sound_word),
    SOUND_PICTURE("ЗВУК КАРТИНКА", R.drawable.sound_picture),
    WORD_CONSTRUCTOR("КОНСТРУКТОР СЛОВ", R.drawable.moderation_words),
    PHRASE_CONSTRUCTOR("КОНСТРУКТОР ФРАЗ", R.drawable.moderation_phrazes);

    private String mTitle;

    private int mImageResId;

    public int getImageResId() {
        return mImageResId;
    }

    public String getTitle() {
        return mTitle;
    }

    Training(String title, int imageResId) {
        mTitle = title;
        mImageResId = imageResId;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
