package com.valevich.lingvoapp.ui.recyclerview.views;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Word;
import com.valevich.lingvoapp.ui.recyclerview.utils.ViewBinder;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.dictionary_list_item)
public class DictionaryItemView extends RelativeLayout implements ViewBinder<Word> {

    @ViewById(R.id.word)
    TextView mWordLabel;

    public DictionaryItemView(Context context) {
        super(context);
    }

    @Override
    public void bindData(Word item) {
        mWordLabel.setText(item.getTranslation());
    }
}
