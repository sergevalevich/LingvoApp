package com.valevich.lingvoapp.ui.recyclerview.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Training;
import com.valevich.lingvoapp.ui.recyclerview.utils.ViewBinder;
import com.valevich.lingvoapp.utils.ImageLoader;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.grid_item_trainings)
public class CardTrainingItemView extends RelativeLayout implements ViewBinder<Training> {

    @ViewById(R.id.image)
    ImageView mImageView;

    @ViewById(R.id.title_part_one)
    TextView mTitlePartOne;

    @ViewById(R.id.title_part_two)
    TextView mTitlePartTwo;

    public CardTrainingItemView(Context context) {
        super(context);
    }

    @Override
    public void bindData(Training item) {
        mImageView.setImageResource(item.getImageResId());
        String[] parts = item.getTitle().split("\\s+");
        mTitlePartOne.setText(parts[0]);

        if(parts.length > 1)
        mTitlePartTwo.setText(parts[1]);
    }
}
