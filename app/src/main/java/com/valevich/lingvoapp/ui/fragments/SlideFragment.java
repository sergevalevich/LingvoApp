package com.valevich.lingvoapp.ui.fragments;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Starable;
import com.valevich.lingvoapp.stubmodel.Translatable;
import com.valevich.lingvoapp.stubmodel.Viewable;
import com.valevich.lingvoapp.utils.ImageLoader;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.fragment_details)
public class SlideFragment<T extends Translatable & Viewable & Starable & Parcelable> extends Fragment {

    @ViewById(R.id.image)
    ImageView mImage;

    @ViewById(R.id.nativeText)
    TextView mNativeTextLabel;

    @ViewById(R.id.translate)
    TextView mTranslationLabel;

    @ViewById(R.id.left_arrow)
    ImageView mLeftArrow;

    @ViewById(R.id.right_arrow)
    ImageView mRightArrow;

    @ViewById(R.id.star)
    ImageView mStar;

    @ViewById(R.id.dictionary)
    ImageView mDictionaryButton;

    @ViewById(R.id.sound)
    ImageView mPlayButton;

    @ViewById(R.id.share)
    ImageView mShareButton;

    @Bean
    ImageLoader mImageLoader;

    @FragmentArg
    T item;

    @AfterViews
    void setUpViews() {
        if(item != null) bindData();
    }

    private void bindData() {
        setUpImage();
        setUpLabels();
        setUpPlayButton();
        setUpStarButton();
    }

    private void setUpImage() {
        mImageLoader.loadImageByResId(item.getImageResId(), mImage);
    }

    private void setUpLabels() {
        mNativeTextLabel.setText(item.getNativeText());
        mTranslationLabel.setText(item.getTranslation());
    }

    private void setUpStarButton() {

        if(item.isFavorite()) mStar.setImageResource(R.drawable.star_activ_kitty);
        else mStar.setImageResource(R.drawable.star_kitty_);

        mStar.setOnClickListener(view -> {
            if(item.isFavorite()) {
                item.setFavorite(false);
                mStar.setImageResource(R.drawable.star_kitty_);
            } else {
                item.setFavorite(true);
                mStar.setImageResource(R.drawable.star_activ_kitty);
            }
        });
    }

    private void setUpPlayButton() {
        mPlayButton.setOnClickListener(view -> {
            //// TODO: 19.08.2016 play sound
        });
    }

}
