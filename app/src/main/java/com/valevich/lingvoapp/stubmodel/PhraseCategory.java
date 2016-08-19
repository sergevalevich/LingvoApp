package com.valevich.lingvoapp.stubmodel;


import com.valevich.lingvoapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhraseCategory {

    private String mName;

    private String mExamples;

    private int mImageResId;

    public PhraseCategory(String name,String examples, int imageResId) {
        mName = name;
        mExamples = examples;
        mImageResId = imageResId;
    }

    public String getName() {
        return mName;
    }

    public String getExamples() {
        return mExamples;
    }

    public int getImageResId() {
        return mImageResId;
    }

    public static List<PhraseCategory> getAll() {
        return new ArrayList<>(Arrays.asList(
                new PhraseCategory("ПОВСЕДНЕВНЫЕ ФРАЗЫ","приветствие,комплименты", R.drawable.hello),
                new PhraseCategory("ЗНАКОМСТВО","как начать разговор",R.drawable.meeting),
                new PhraseCategory("В РЕСТОРАНЕ","заказ столика,блюда",R.drawable.restaurant),
                new PhraseCategory("ПУТЕШЕСТВИЯ","заказ билетов,решистрация",R.drawable.travel),
                new PhraseCategory("ГОСТИНИЦЫ","бронирование,обслуживание",R.drawable.hotel),
                new PhraseCategory("РАЗВЛЕЧЕНИЯ","театр,кино,концерты",R.drawable.mask),
                new PhraseCategory("ЗДОРОВЬЕ","симптомы,органы,части тела",R.drawable.health),
                new PhraseCategory("ЭКСКУРСИИ","информация,планы",R.drawable.map),
                new PhraseCategory("ПОКУПКИ","одежда,сувениры,оплата",R.drawable.money)));
    }
}
