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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Phrase;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EFragment(R.layout.fragment_word_construction_training)
public class PhraseConstructionFragment extends Fragment {

//    private static final int PHRASE_LOADER_ID = 0;
//
//    @ViewById(R.id.title)
//    TextView mTitle;
//
//    @ViewById(R.id.hint)
//    ImageView mHintImage;
//
//    @ViewById(R.id.hint_label)
//    TextView mHintLabel;
//
//    @ViewById(R.id.right_answer_notifier)
//    ImageView mAnswerStateNotifier;
//
//    @ViewById(R.id.button_next)
//    Button mContinueButton;
//
//    @ViewById(R.id.row_one)
//    LinearLayout mWordButtonSpaceRowOne;
//
//    @ViewById(R.id.row_two)
//    LinearLayout mWordButtonSpaceRowTwo;
//
//    @ViewById(R.id.letters_answer_space)
//    LinearLayout mWordsAnswerSpace;
//
//    private LayoutInflater mLayoutInflater;
//
//    private List<TextView> mAnswerLabels = new ArrayList<>();
//
//    private List<FrameLayout> mWordButtons = new ArrayList<>();
//
//    private String mAnswer;
//
//    private int mNextEmptyPosition;
//
//    @AfterViews
//    void setUpViews() {
//        setInflater();
//        setUpContinueButton();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (mAnswer == null)
//            loadPhrase();
//    }
//
//    private void setInflater() {
//        mLayoutInflater = LayoutInflater.from(getContext());
//    }
//
//    private void setUpContinueButton() {
//        mContinueButton.setOnClickListener(view -> {
//            mContinueButton.setVisibility(View.INVISIBLE);
//            reset();
//            loadPhrase();
//        });
//    }
//
//    private void reset() {
//        mAnswer = null;
//        mTitle.setText("");
//        mAnswerStateNotifier.setVisibility(View.GONE);
//        mAnswerLabels.clear();
//        mWordButtons.clear();
//        mWordButtonSpaceRowOne.removeAllViews();
//        mWordButtonSpaceRowTwo.removeAllViews();
//        mWordsAnswerSpace.removeAllViews();
//    }
//
//    private void loadPhrase() {
//        getLoaderManager().restartLoader(PHRASE_LOADER_ID,
//                null,
//                new LoaderManager.LoaderCallbacks<Phrase>() {
//                    @Override
//                    public Loader<Phrase> onCreateLoader(int id, Bundle args) {
//                        final AsyncTaskLoader<Phrase> loader = new AsyncTaskLoader<Phrase>(getActivity()) {
//                            @Override
//                            public Phrase loadInBackground() {
//                                return Phrase.getRandom();
//                            }
//                        };
//                        loader.forceLoad();
//                        return loader;
//                    }
//
//                    @Override
//                    public void onLoadFinished(Loader<Phrase> loader, Phrase phrase) {
//                        bindData(phrase);
//                    }
//
//                    @Override
//                    public void onLoaderReset(Loader<Phrase> loader) {
//
//                    }
//                });
//    }
//
//    private void bindData(Phrase phrase) {
//        setCorrectAnswer(phrase.getTranslation());
//        setTitle(phrase);
//        setHint();
//        setUpLettersAndSpaces();
//    }
//
//    private void setCorrectAnswer(String answer) {
//        mAnswer = answer;
//    }
//
//    private void setTitle(Phrase phrase) {
//        mTitle.setText(phrase.getNativeText());
//    }
//
//    private void setHint() {
//        mHintImage.setOnClickListener(view -> showHint());
//    }
//
//    private void showHint() {
//        for (int j = 0; j < mAnswerLabels.size(); j++) {
//            TextView label = mAnswerLabels.get(j);
//            if (label.getText().toString().equals(" ")) {
//
//                if (mAnswer != null) {//user clicked on hint
//                    String letter = mAnswer.replaceAll("[^a-zA-Z ]", "").split(" ")[j];
//                    for (TextView letterButton : mWordButtons) {
//                        if (letterButton.getText().equals(letter)) {
//                            highLightUsedLetter(letterButton); //<-----
//                            SpannableString content = new SpannableString(letter);
//                            content.setSpan(new UnderlineSpan(), 0, letter.length(), 0);
//
//                            label.setText(content);
//                            mButtonLabelToggle.put(letterButton, label);
//                            break;
//                        }
//                    }
//                }
//
//                if (lastLetter(j)) {
//                    showAnswer();
//                }
//                break;
//            }
//        }
//    }
//
//    private void toggleLetter(TextView letterButton) {
//
//        if (!hasUserAnswered()) {
//
//            if (wasLetterSelected(letterButton)) {
//
//                hideLetter(letterButton);
//
//                highlightButton(letterButton, R.drawable.bg_round, R.color.colorTextGray);
//
//            } else {
//
//                showLetter(letterButton);
//
//                highlightButton(letterButton, R.drawable.bg_letter_selected, R.color.colorTextWhite);
//            }
//
//        }
//    }
//
//    private void hideLetter(TextView letterButton) {
//        TextView label = mButtonLabelToggle.remove(letterButton);
//
//        SpannableString content = new SpannableString(" ");
//        content.setSpan(new UnderlineSpan(), 0, 1, 0);
//        label.setText(content);
//    }
//
//    private void showLetter(TextView letterButton) {
//        for (int j = 0; j < mAnswerLabels.size(); j++) {
//            TextView label = mAnswerLabels.get(j);
//            if (label.getText().toString().equals(" ")) {
//
//                String buttonText = letterButton.getText().toString();
//                SpannableString content = new SpannableString(buttonText);
//                content.setSpan(new UnderlineSpan(), 0, buttonText.length(), 0);
//
//                label.setText(content);
//                mButtonLabelToggle.put(letterButton, label);
//
//                if (lastLetter(j)) {
//                    showAnswer();
//                }
//                break;
//            }
//        }
//    }
//
//    private boolean wasLetterSelected(TextView letterButton) {
//        return mButtonLabelToggle.containsKey(letterButton);
//    }
//
//    private void highlightButton(TextView letterButton, int background, int textColor) {
//        letterButton.setBackgroundResource(background);
//        letterButton.setTextColor(ContextCompat.getColor(getContext(), textColor));
//    }
//
//    // TODO: 26.08.2016 deprecated
//    private void highLightUsedLetter(TextView letterButton) {
//        if (letterButton.getCurrentTextColor() == ContextCompat
//                .getColor(getContext(), R.color.colorTextWhite)) {
//
//            letterButton.setBackgroundResource(R.drawable.bg_round);
//            letterButton.setTextColor(ContextCompat.getColor(getContext(), R.color.colorTextGray));
//
//        } else {
//            letterButton.setBackgroundResource(R.drawable.bg_letter_selected);
//            letterButton.setTextColor(ContextCompat.getColor(getContext(), R.color.colorTextWhite));
//        }
//    }
//
//    private void setUpLettersAndSpaces() {
//        LayoutInflater inflater = LayoutInflater.from(getContext());
//        String empty = " ";
//        SpannableString content = new SpannableString(empty);
//        content.setSpan(new UnderlineSpan(), 0, empty.length(), 0);
//
//        if (mAnswer != null) {
//            for (int i = 0; i < mAnswer.replaceAll("[^a-zA-Z ]", "").split(" ").length; i++) {
//                View labelRoot = inflater.inflate(R.layout.word_answer, mLettersAnswerSpace, false);
//                TextView letterLabel = (TextView) labelRoot.findViewById(R.id.letter_space);
//
//                View buttonRoot = inflater.inflate(R.layout.word_button, mWordButtonSpaceRowOne, false);
//                TextView letterButton = (TextView) buttonRoot.findViewById(R.id.letter_button);
//
//                letterLabel.setText(content);
//                letterButton.setText(mAnswer.replaceAll("[^a-zA-Z ]", "").split(" ")[i]);
//
//                letterButton.setOnClickListener(view -> toggleLetter(letterButton));
//
//
//                mLettersAnswerSpace.addView(letterLabel);
//                mAnswerLabels.add(letterLabel);
//                mWordButtons.add(letterButton);
//            }
//            addOddLetter(inflater);
//            shuffleOptions();
//        }
//    }
//
//    private void addOddLetter(LayoutInflater inflater) {
//        View buttonRoot = inflater.inflate(R.layout.word_button, mWordButtonSpaceRowOne, false);
//        TextView oddLetterButton = (TextView) buttonRoot.findViewById(R.id.letter_button);
//        oddLetterButton.setText(getRandomWord());
//        oddLetterButton.setOnClickListener(view -> toggleLetter(oddLetterButton));
//        mWordButtons.add(oddLetterButton);
//    }
//
//    private String getRandomWord() {
//        return "WHO";
//    }
//
//    private void shuffleOptions() {
//        Collections.shuffle(mWordButtons);
//        for (TextView letterButton : mWordButtons) {
//            if(mWordButtonSpaceRowOne.getChildCount() < 3)
//                mWordButtonSpaceRowOne.addView(letterButton);
//            else mLetterButtonSpaceRowTwo.addView(letterButton);
//        }
//    }
//
//    private boolean lastLetter(int emptySpaceIndex) {
//        return emptySpaceIndex == mAnswerLabels.size() - 1;
//    }
//
//    private boolean hasUserAnswered() {
//        return mButtonLabelToggle.size() == mAnswer.replaceAll("[^a-zA-Z ]", "").split(" ").length;
//    }
//
//    private void showAnswer() {
//        mAnswerStateNotifier.setVisibility(View.VISIBLE);
//        if (isAnswerCorrect()) {
//            mAnswerStateNotifier.setImageResource(R.drawable.check);
//        } else {
//            mAnswerStateNotifier.setImageResource(R.drawable.krestik);
//        }
//        mContinueButton.setVisibility(View.VISIBLE);
//    }
//
//    private boolean isAnswerCorrect() {
//        String userAnswer = "";
//        for (TextView label : mAnswerLabels) {
//            userAnswer += label.getText().toString();
//        }
//        String answer = mAnswer.replace(" ", "").replaceAll("[^a-zA-Z ]", "");
//        return userAnswer.equals(answer);
//    }
}
