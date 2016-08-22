package com.valevich.lingvoapp.ui.recyclerview.adapters;


import android.content.Context;
import android.view.ViewGroup;

import com.valevich.lingvoapp.stubmodel.Word;
import com.valevich.lingvoapp.ui.recyclerview.ViewWrapper;
import com.valevich.lingvoapp.ui.recyclerview.views.DictionaryItemView;
import com.valevich.lingvoapp.ui.recyclerview.views.DictionaryItemView_;
import com.viethoa.RecyclerViewFastScroller;
import com.viethoa.models.AlphabetItem;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EBean
public class DictionaryAdapter extends RecyclerViewAdapterBase<Word, DictionaryItemView>
        implements RecyclerViewFastScroller.BubbleTextGetter {

    @RootContext
    Context mContext;

    public void init(String filter) {
        mItems = Word.getAll(filter);
    }

    @Override
    protected DictionaryItemView onCreateItemView(ViewGroup parent, int viewType) {
        return DictionaryItemView_.build(mContext);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<DictionaryItemView> holder, int position) {
        DictionaryItemView itemView = holder.getView();
        itemView.bindData(mItems.get(position));
    }

    @Override
    public String getTextToShowInBubble(int position) {
        if (position < 0 || position >= mItems.size())
            return null;

        String text = mItems.get(position).getTranslation();
        if (text == null || text.length() < 1)
            return null;

        return text.substring(0, 1);
    }

    public List<AlphabetItem> getAlphabetItems() {
        ArrayList<AlphabetItem> mAlphabetItems = new ArrayList<>();
        List<String> strAlphabets = new ArrayList<>();
        for (int i = 0; i < mItems.size(); i++) {
            String text = mItems.get(i).getTranslation();
            if (text == null || text.trim().isEmpty())
                continue;

            String word = text.substring(0, 1);
            if (!strAlphabets.contains(word)) {
                strAlphabets.add(word);
                mAlphabetItems.add(new AlphabetItem(i, word, false));
            }
        }

        return mAlphabetItems;
    }
}
