package com.valevich.lingvoapp.ui.recyclerview.views;


import android.content.Context;
import android.widget.RelativeLayout;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.model.PhraseCategory;
import com.valevich.lingvoapp.ui.recyclerview.utils.ViewBinder;

import org.androidannotations.annotations.EViewGroup;

@EViewGroup(R.layout.phrase_category_item)
public class PhraseBookItemView extends RelativeLayout implements ViewBinder<PhraseCategory> {
    public PhraseBookItemView(Context context) {
        super(context);
    }

    @Override
    public void bindData(PhraseCategory item) {

    }
}
