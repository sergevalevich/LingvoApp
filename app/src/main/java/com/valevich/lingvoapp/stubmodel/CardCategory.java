package com.valevich.lingvoapp.stubmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//zaglushka
public class CardCategory {
    private int mProgress;

    private String mName;

    private String mImageUrl;

    public CardCategory(int progress, String name, String imageUrl) {
        mProgress = progress;
        mName = name;
        mImageUrl = imageUrl;
    }

    public int getProgress() {
        return mProgress;
    }

    public String getName() {
        return mName;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public static List<CardCategory> getAll() {//// TODO: 17.08.2016 there will be db implementation
        return new ArrayList<>(Arrays.asList(
                new CardCategory(20,"Животные",""),new CardCategory(30,"Страны",""),
                new CardCategory(10,"Цветы",""),new CardCategory(80,"Цвета","")));
    }
}
