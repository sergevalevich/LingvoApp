package com.valevich.lingvoapp.ui.recyclerview.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.valevich.lingvoapp.eventbus.EventBus;
import com.valevich.lingvoapp.eventbus.events.TrainingSelectedEvent;
import com.valevich.lingvoapp.stubmodel.Training;
import com.valevich.lingvoapp.ui.recyclerview.ViewWrapper;
import com.valevich.lingvoapp.ui.recyclerview.views.CardTrainingItemView;
import com.valevich.lingvoapp.ui.recyclerview.views.CardTrainingItemView_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.Arrays;

@EBean
public class TrainingsAdapter extends RecyclerViewAdapterBase<Training, CardTrainingItemView> {

    @RootContext
    Context mContext;

    @Bean
    EventBus mEventBus;

    public void init() {
        mItems = Arrays.asList(Training.values());
    }

    @Override
    protected CardTrainingItemView onCreateItemView(ViewGroup parent, int viewType) {
        return CardTrainingItemView_.build(mContext);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<CardTrainingItemView> holder, int position) {
        CardTrainingItemView itemView = holder.getView();
        itemView.bindData(mItems.get(position));

        itemView.setOnClickListener(view ->
                mEventBus.post(new TrainingSelectedEvent(mItems.get(position))));
    }
}
