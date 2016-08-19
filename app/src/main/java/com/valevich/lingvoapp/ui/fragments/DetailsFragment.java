package com.valevich.lingvoapp.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Word;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_details)
public class DetailsFragment extends Fragment {

    private static final int WORD_LOADER_ID = 0;

    @ViewById(R.id.image)
    ImageView mWordImage;

    @ViewById(R.id.original)
    TextView mOriginalWordLabel;

    @ViewById(R.id.translate)
    TextView mTranslatedWordLabel;

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

    @FragmentArg
    Word word;

    @AfterViews
    void setUpViews() {
        setUpPlayButton();
        setUpStarButton();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadWord();
    }

    private void loadWord() {
        getLoaderManager().restartLoader(WORD_LOADER_ID,
                null,
                new LoaderManager.LoaderCallbacks() {
                    @Override
                    public Loader onCreateLoader(int id, Bundle args) {
                        final AsyncTaskLoader loader = new AsyncTaskLoader(getActivity()) {
                            @Override
                            public Object loadInBackground() {
                                //load word info
                                return null;
                            }
                        };
                        loader.forceLoad();
                        return loader;
                    }

                    @Override
                    public void onLoadFinished(Loader loader, Object data) {
                        //fill views
                    }

                    @Override
                    public void onLoaderReset(Loader loader) {

                    }
                });
    }


    private void setUpStarButton() {
        final boolean isFavorite = word.isFavorite();

        if(isFavorite) mStar.setImageResource(R.drawable.star_activ_kitty);
        else mStar.setImageResource(R.drawable.star_kitty_);

        mStar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(isFavorite) {
                    word.setFavorite(false);
                    mStar.setImageResource(R.drawable.star_kitty_);
                } else {
                    word.setFavorite(true);
                    mStar.setImageResource(R.drawable.star_activ_kitty);
                }
            }
        });
    }

    private void setUpPlayButton() {
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //// TODO: 19.08.2016 play sound
            }
        });
    }

}
