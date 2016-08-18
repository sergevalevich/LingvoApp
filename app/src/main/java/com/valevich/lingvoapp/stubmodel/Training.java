package com.valevich.lingvoapp.stubmodel;


import com.valevich.lingvoapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Training {

    private String mTitle;

    private int mImageResId;

    public Training(String title, int resId) {
        mTitle = title;
        mImageResId = resId;
    }

    public int getImageResId() {
        return mImageResId;
    }

    public String getTitle() {
        return mTitle;
    }

    public static List<Training> getAll() {
        return new ArrayList<>(Arrays.asList(
                new Training("ПЕРЕВОД СЛОВО", R.drawable.translate_word),
                new Training("СЛОВО ПЕРЕВОД", R.drawable.word_translate),
                new Training("СЛОВО КАРТИНКА", R.drawable.word_picture),
                new Training("КАРТИНКА СЛОВО", R.drawable.picture_word),
                new Training("ЗВУК СЛОВО", R.drawable.sound_word),
                new Training("ЗВУК КАРТИНКА", R.drawable.sound_picture),
                new Training("КОНСТРУКТОР СЛОВ", R.drawable.moderation_words),
                new Training("КОНСТРУКТОР ФРАЗ", R.drawable.moderation_phrazes)));
    }
}
