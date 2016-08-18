package com.valevich.lingvoapp.ui.recyclerview.views;

import android.content.Context;
import android.widget.RelativeLayout;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Word;
import com.valevich.lingvoapp.ui.recyclerview.utils.ViewBinder;

import org.androidannotations.annotations.EViewGroup;

@EViewGroup(R.layout.dictionary_list_item)
public class DictionaryItemView extends RelativeLayout implements ViewBinder<Word> {

    public DictionaryItemView(Context context) {
        super(context);
    }

    @Override
    public void bindData(Word item) {

    }
}
