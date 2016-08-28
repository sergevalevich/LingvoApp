package com.valevich.lingvoapp.stubmodel;

import com.valevich.lingvoapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//zaglushka
public class WordCategory {
    private int mProgress;

    private String mName;

    private int mImageResId;

    public WordCategory(int progress, String name, int imageResId) {
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

    public static List<WordCategory> getAll() {
        return new ArrayList<>(Arrays.asList(
                new WordCategory(0,"СТРАНЫ",R.drawable.city),
                new WordCategory(0,"ЖИВОТНЫЕ",R.drawable.panda),
                new WordCategory(0,"ЦВЕТЫ",R.drawable.flowers),
                new WordCategory(0,"ЦВЕТА",R.drawable.colors),
                new WordCategory(0,"ФРУКТЫ",R.drawable.fruits),
                new WordCategory(0,"ОВОЩИ",R.drawable.vegetables)));
    }

}
