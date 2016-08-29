package com.valevich.lingvoapp.ui.fragments.trainings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Word;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@EFragment(R.layout.fragment_word_construction_training)
public class WordsConstructionFragment extends Fragment {

    private static final int WORD_LOADER_ID = 0;

    @ViewById(R.id.title)
    TextView mTitle;

    @ViewById(R.id.hint)
    ImageView mHintImage;

    @ViewById(R.id.hint_label)
    TextView mHintLabel;

    @ViewById(R.id.right_answer_notifier)
    ImageView mRightAnswerNotifier;

    @ViewById(R.id.button_next)
    Button mContinueButton;

    @ViewById(R.id.row_one)
    LinearLayout mLetterButtonSpaceRowOne;

    @ViewById(R.id.row_two)
    LinearLayout mLetterButtonSpaceRowTwo;

    @ViewById(R.id.letters_answer_space)
    LinearLayout mLettersAnswerSpace;

    private LayoutInflater mLayoutInflater;

    private List<TextView> mAnswerLabels = new ArrayList<>();

    private List<FrameLayout> mLetterButtons = new ArrayList<>();

    private String mAnswer;

    private int mNextEmptyPosition;

    @AfterViews
    void setUpViews() {
        setInflater();
        setUpContinueButton();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAnswer == null)
            loadWord();
    }

    private void setInflater() {
        mLayoutInflater = LayoutInflater.from(getContext());
    }

    private void setUpContinueButton() {
        mContinueButton.setOnClickListener(view -> {
            reset();
            loadWord();
        });
    }

    private void reset() {
        mAnswer = null;
        mNextEmptyPosition = 0;
        mTitle.setText("");
        mRightAnswerNotifier.setVisibility(View.GONE);
        mContinueButton.setVisibility(View.INVISIBLE);
        mAnswerLabels.clear();
        mLetterButtons.clear();
        mLetterButtonSpaceRowOne.removeAllViews();
        mLetterButtonSpaceRowTwo.removeAllViews();
        mLettersAnswerSpace.removeAllViews();
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
                                return new Word().getRandom();
                            }
                        };
                        loader.forceLoad();
                        return loader;
                    }

                    @Override
                    public void onLoadFinished(Loader<Word> loader, Word word) {
                        bindData(word);
                    }

                    @Override
                    public void onLoaderReset(Loader<Word> loader) {

                    }
                });
    }

    private void bindData(Word word) {
        setCorrectAnswer(word.getTranslation());
        setTitle(word);
        setHint();
        setUpLettersAndSpaces();
    }

    private void setCorrectAnswer(String answer) {
        mAnswer = answer;
    }

    private void setTitle(Word word) {
        mTitle.setText(word.getNativeText());
    }

    private void setHint() {
        mHintImage.setOnClickListener(view -> showHint());
    }

    private void showHint() {
        if (!hasUserAnswered()) {
            mHintLabel.setText(mAnswer.substring(mNextEmptyPosition, mNextEmptyPosition + 1));
            fadeViewInOut(mHintLabel);
        }
    }

    private void checkLetter(FrameLayout letterButton) {
        if (!hasUserAnswered()) {
            TextView letterButtonLabel = getLetterButtonLabel(letterButton);
            if (isLetterCorrect(letterButtonLabel.getText().toString())) {
                highlightButton(letterButton);
                showLetter(letterButtonLabel);
            } else {
                showNotifier(getLetterButtonNotifier(letterButton));
            }
        }
    }

    private boolean hasUserAnswered() {
        return mNextEmptyPosition >= mAnswer.length();
    }

    private boolean isLetterCorrect(String letter) {
        return letter.equals(mAnswer.substring(mNextEmptyPosition, mNextEmptyPosition + 1));
    }

    private void showNotifier(ImageView notifier) {
        fadeViewInOut(notifier);
    }

    private void fadeViewInOut(View view) {
        view.setVisibility(View.VISIBLE);

        Animation bummerIn = AnimationUtils.loadAnimation(getContext(), R.anim.bummer_in);
        Animation bummerOut = AnimationUtils.loadAnimation(getContext(), R.anim.bummer_out);
        bummerIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.startAnimation(bummerOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bummerOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        view.startAnimation(bummerIn);
    }

    private void showLetter(TextView letterButton) {
        TextView answerLabel = mAnswerLabels.get(mNextEmptyPosition);
        SpannableString content = new SpannableString(letterButton.getText().toString());
        content.setSpan(new UnderlineSpan(), 0, 1, 0);
        answerLabel.setText(content);
        if (lastLetter(mNextEmptyPosition++)) finishRound();
    }

    private void highlightButton(FrameLayout letterButton) {
        TextView letterButtonLabel = getLetterButtonLabel(letterButton);
        letterButtonLabel.setBackgroundResource(R.drawable.bg_letter_selected);
        letterButtonLabel.setTextColor(ContextCompat.getColor(getContext(), R.color.colorTextWhite));
        letterButton.setClickable(false);
    }

    private void setUpLettersAndSpaces() {

        String empty = " ";
        SpannableString content = new SpannableString(empty);
        content.setSpan(new UnderlineSpan(), 0, empty.length(), 0);

        if (mAnswer != null) {
            for (int i = 0; i < mAnswer.length(); i++) {

                createLetterLabel(content);
                createLetterButton(mAnswer.substring(i, i + 1));

            }
            createLetterButton(getRandomLetter());
            shuffleOptions();
        }
    }

    private void createLetterLabel(SpannableString text) {
        View labelRoot = mLayoutInflater.inflate(R.layout.letter_answer, mLettersAnswerSpace, false);
        TextView letterLabel = (TextView) labelRoot.findViewById(R.id.letter_space);
        letterLabel.setText(text);
        mAnswerLabels.add(letterLabel);
        mLettersAnswerSpace.addView(letterLabel);
    }

    private void createLetterButton(String text) {
        View buttonRoot = mLayoutInflater.inflate(R.layout.letter_button, mLetterButtonSpaceRowOne, false);
        FrameLayout letterButton = (FrameLayout) buttonRoot.findViewById(R.id.root);
        letterButton.setOnClickListener(view -> checkLetter(letterButton));
        TextView letterButtonLabel = getLetterButtonLabel(letterButton);
        letterButtonLabel.setText(text);
        mLetterButtons.add(letterButton);
    }

    private TextView getLetterButtonLabel(FrameLayout letterButton) {
        return (TextView) letterButton.findViewById(R.id.letter_button);
    }

    private ImageView getLetterButtonNotifier(FrameLayout letterButton) {
        return (ImageView) letterButton.findViewById(R.id.wrong_answer_notifier);
    }

    private String getRandomLetter() {
        return Character.toString((char) (new Random().nextInt(26) + 'A'));
    }

    private void shuffleOptions() {
        Collections.shuffle(mLetterButtons);
        for (FrameLayout option : mLetterButtons) {
            if (mLetterButtonSpaceRowOne.getChildCount() < 5)
                mLetterButtonSpaceRowOne.addView(option);
            else mLetterButtonSpaceRowTwo.addView(option);
        }
    }

    private boolean lastLetter(int emptySpaceIndex) {
        return emptySpaceIndex == mAnswer.length() - 1;
    }

    private void finishRound() {
        showAnswer();
        showContinueButton();
    }

    private void showAnswer() {
        mRightAnswerNotifier.setVisibility(View.VISIBLE);
    }

    private void showContinueButton() {
        mContinueButton.setVisibility(View.VISIBLE);
    }
}
