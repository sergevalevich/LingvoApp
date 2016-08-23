package com.valevich.lingvoapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.widget.ImageView;
import android.widget.TextView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Word;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.fragment_details)
public class WordSlideFragment extends Fragment {

    private static final String ARGUMENT_WORD = "WORD";

    private static final int WORD_LOADER_ID = 0;

    @ViewById(R.id.image)
    ImageView mWordImage;

    @ViewById(R.id.nativeText)
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

    private int mWordId;

    public WordSlideFragment() {}

    public static WordSlideFragment newInstance(int wordId) {
        WordSlideFragment pageFragment = new WordSlideFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_WORD,wordId);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mWordId = getArguments().getInt(ARGUMENT_WORD);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadWord();
    }

    private void loadWord() {
        getLoaderManager().restartLoader(WORD_LOADER_ID,
                null,
                new LoaderManager.LoaderCallbacks<Word>() {
                    @Override
                    public Loader<Word> onCreateLoader(int id, Bundle args) {
                        final AsyncTaskLoader<Word> loader = new AsyncTaskLoader<Word>(getActivity()) {
                            @Override
                            public Word loadInBackground() {
                                return Word.get(mWordId);
                            }
                        };
                        loader.forceLoad();
                        return loader;
                    }

                    @Override
                    public void onLoadFinished(Loader<Word> loader, Word word) {
                        setUpPlayButton(word);
                        setUpStarButton(word);
                    }

                    @Override
                    public void onLoaderReset(Loader<Word> loader) {

                    }
                });
    }


    private void setUpStarButton(Word word) {
        final boolean isFavorite = word.isFavorite();

        if(isFavorite) mStar.setImageResource(R.drawable.star_activ_kitty);
        else mStar.setImageResource(R.drawable.star_kitty_);

        mStar.setOnClickListener(view -> {
            if(isFavorite) {
                word.setFavorite(false);
                mStar.setImageResource(R.drawable.star_kitty_);
            } else {
                word.setFavorite(true);
                mStar.setImageResource(R.drawable.star_activ_kitty);
            }
        });
    }

    private void setUpPlayButton(Word word) {
        mPlayButton.setOnClickListener(view -> {
            //// TODO: 19.08.2016 play sound
        });
    }
}
