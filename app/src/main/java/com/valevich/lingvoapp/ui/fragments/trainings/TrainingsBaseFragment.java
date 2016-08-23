package com.valevich.lingvoapp.ui.fragments.trainings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Word;

import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@EFragment
abstract class TrainingsBaseFragment extends Fragment {
    private static final int WORDS_LOADER_ID = 0;
    static final int OPTIONS_COUNT = 4;

    private List<View> mOptions = new ArrayList<>(OPTIONS_COUNT);
    private List<ImageView> mAnswerStateNotifiers = new ArrayList<>(OPTIONS_COUNT);
    private Button mContinueButton;

    private int mCorrectAnswerIndex = -1;


    @Override
    public void onResume() {
        super.onResume();
        if(mCorrectAnswerIndex == -1)
            loadWords();
    }

    void collectOptions(List<View> options) {
        mOptions.addAll(options);
    }

    void collectNotifiers(List<ImageView> notifiers) {
        mAnswerStateNotifiers.addAll(notifiers);
    }

    void setContinueButton(Button button) {
        mContinueButton = button;
        setUpContinueButton();
    }

    void setCorrectAnswerIndex(int index) {
        mCorrectAnswerIndex = index;
    }

    boolean isAnswerCorrect(int optionIndex) {
        return optionIndex == mCorrectAnswerIndex;
    }

    Word getRandomWord(List<Word> words) {
        int index = new Random().nextInt(words.size());
        return words.remove(index);
    }

    void setOptionClickListener(final int optionIndex, TextView optionLabel) {
        optionLabel.setOnClickListener(view -> {

            //do not allow the user to answer once again
            blockOptions();

            if(isAnswerCorrect(optionIndex)) {
                mAnswerStateNotifiers.get(optionIndex).setImageResource(R.drawable.check);
            } else {
                mAnswerStateNotifiers.get(optionIndex).setImageResource(R.drawable.krestik);
                mAnswerStateNotifiers.get(mCorrectAnswerIndex).setImageResource(R.drawable.check);
            }

            //show button to load next word
            mContinueButton.setVisibility(View.VISIBLE);

            //reset correctIndex
            mCorrectAnswerIndex = -1;

        });
    }

    private void loadWords() {
        getLoaderManager().restartLoader(WORDS_LOADER_ID,
                null,
                new LoaderManager.LoaderCallbacks<List<Word>>() {
                    @Override
                    public Loader<List<Word>> onCreateLoader(int id, Bundle args) {
                        final AsyncTaskLoader<List<Word>> loader = new AsyncTaskLoader<List<Word>>(getActivity()) {
                            @Override
                            public List<Word> loadInBackground() {
                                return Word.getBunch();
                            }
                        };
                        loader.forceLoad();
                        return loader;
                    }

                    @Override
                    public void onLoadFinished(Loader<List<Word>> loader, List<Word> words) {
                        bindData(words);
                        unblockOptions();
                    }

                    @Override
                    public void onLoaderReset(Loader<List<Word>> loader) {

                    }
                });
    }

    private void setUpContinueButton() {
        mContinueButton.setOnClickListener(view -> {
            mContinueButton.setVisibility(View.INVISIBLE);
            loadWords();
        });
    }

    private void blockOptions() {
        for(View option:mOptions) {
            option.setClickable(false);
        }
    }

    private void unblockOptions() {
        for(View option:mOptions) {
            option.setClickable(true);
        }
    }

    abstract void bindData(List<Word> words);
}
