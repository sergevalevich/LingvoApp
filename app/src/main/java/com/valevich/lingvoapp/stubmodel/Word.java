package com.valevich.lingvoapp.stubmodel;

import com.valevich.lingvoapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Word implements Serializable {

    private String mNativeText;

    private String mTranslation;

    private boolean mIsFavorite;

    private String mImageUrl;

    private int mImageResId;

    private int mId;

    public Word(String nativeText, String translation) {
        mTranslation = translation;
        mNativeText = nativeText;
    }

    public Word(String nativeText, String translation, int imageResId) {
        mNativeText = nativeText;
        mTranslation = translation;
        mImageResId = imageResId;
    }

    public static List<Word> getAll(String filter) {
        return new ArrayList<>();
    }

    public String getNativeText() {
        return mNativeText.toUpperCase();
    }

    public boolean isFavorite() {
        return mIsFavorite;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public int getImageResId() {
        return mImageResId;
    }

    public void setFavorite(boolean isFavorite) {
        mIsFavorite = isFavorite;
    }

    public String getTranslation() {
        return mTranslation.toUpperCase();
    }

    public static List<Word> getAllByCategory(String categoryName) {
        return new ArrayList<>(Arrays.asList(
                new Word("Акула","shark", R.drawable.shark),
                new Word("Енот","raccoon",R.drawable.raccoon),
                new Word("Медведь","bear",R.drawable.bear),
                new Word("Панда","panda",R.drawable.panda)
        ));
    }

    public static Word get(int wordId) {
        return new Word("Енот","Raccoon",R.drawable.raccoon);
    }

    public int getId() {
        return mId;
    }

    public static List<Word> getBunch() {
        return new ArrayList<>(Arrays.asList(
                new Word("Лимон","Lemon", R.drawable.lemon),
                new Word("Яблоко","Apple",R.drawable.apple),
                new Word("Арбуз","Watermelon",R.drawable.arbuz),
                new Word("Апельсин","Orange",R.drawable.apelsin)
        ));
    }

    public static Word getRandom() {
        return new Word("Апельсин","Orange");
    }

    public CardCategory getCategory() {
        return new CardCategory(0,"Животные",R.drawable.panda);
    }
}
