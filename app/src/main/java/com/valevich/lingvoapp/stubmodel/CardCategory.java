package com.valevich.lingvoapp.stubmodel;

import com.valevich.lingvoapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//zaglushka
public class CardCategory {
    private int mProgress;

    private String mName;

    private int mImageResId;

    public CardCategory(int progress, String name, int imageResId) {
        mProgress = progress;
        mName = name;
        mImageResId = imageResId;
    }

    public int getProgress() {
        return mProgress;
    }

    public String getName() {
        return mName;
    }

    public int getImageResId() {
        return mImageResId;
    }

    public static List<CardCategory> getAll() {
        return new ArrayList<>(Arrays.asList(
                new CardCategory(0,"СТРАНЫ",R.drawable.city),
                new CardCategory(0,"ЖИВОТНЫЕ",R.drawable.panda),
                new CardCategory(0,"ЦВЕТЫ",R.drawable.flowers),
                new CardCategory(0,"ЦВЕТА",R.drawable.colors),
                new CardCategory(0,"ФРУКТЫ",R.drawable.fruits),
                new CardCategory(0,"ОВОЩИ",R.drawable.vegetables)));
    }

    public void getWords() {

    }
}
