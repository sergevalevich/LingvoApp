//package com.valevich.lingvoapp.ui.fragments.trainings;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.LoaderManager;
//import android.support.v4.content.AsyncTaskLoader;
//import android.support.v4.content.ContextCompat;
//import android.support.v4.content.Loader;
//import android.text.SpannableString;
//import android.text.style.UnderlineSpan;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.Button;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.valevich.lingvoapp.R;
//import com.valevich.lingvoapp.stubmodel.Randomized;
//import com.valevich.lingvoapp.stubmodel.Translatable;
//import com.valevich.lingvoapp.stubmodel.Word;
//
//import org.androidannotations.annotations.AfterViews;
//import org.androidannotations.annotations.EFragment;
//import org.androidannotations.annotations.ViewById;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Random;
//
//@EFragment(R.layout.fragment_word_construction_training)
//public class ConstructionBaseFragment<T extends Translatable & Randomized> extends Fragment {
//    private static final int LOADER_ID = 0;
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
//    ImageView mRightAnswerNotifier;
//
//    @ViewById(R.id.button_next)
//    Button mContinueButton;
//
//    @ViewById(R.id.row_one)
//    LinearLayout mButtonSpaceRowOne;
//
//    @ViewById(R.id.row_two)
//    LinearLayout mButtonSpaceRowTwo;
//
//    @ViewById(R.id.letters_answer_space)
//    LinearLayout mAnswerSpace;
//
//    private LayoutInflater mLayoutInflater;
//
//    private List<TextView> mAnswerLabels = new ArrayList<>();
//
//    private List<FrameLayout> mButtons = new ArrayList<>();
//
//    private String mAnswer;
//
//    private int mNextEmptyPosition;
//
//    private T mItem;
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
//            loadItem();
//    }
//
//    private void setInflater() {
//        mLayoutInflater = LayoutInflater.from(getContext());
//    }
//
//    private void setUpContinueButton() {
//        mContinueButton.setOnClickListener(view -> {
//            reset();
//            loadItem();
//        });
//    }
//
//    private void reset() {
//        mAnswer = null;
//        mNextEmptyPosition = 0;
//        mTitle.setText("");
//        mRightAnswerNotifier.setVisibility(View.GONE);
//        mContinueButton.setVisibility(View.INVISIBLE);
//        mAnswerLabels.clear();
//        mButtons.clear();
//        mButtonSpaceRowOne.removeAllViews();
//        mButtonSpaceRowTwo.removeAllViews();
//        mAnswerSpace.removeAllViews();
//    }
//
//    private void loadItem() {
//        getLoaderManager().restartLoader(LOADER_ID,
//                null,
//                new LoaderManager.LoaderCallbacks<T>() {
//                    @Override
//                    public Loader<T> onCreateLoader(int id, Bundle args) {
//                        final AsyncTaskLoader<T> loader = new AsyncTaskLoader<T>(getActivity()) {
//                            @Override
//                            public T loadInBackground() {
//                                return mItem.getRandom();
//                            }
//                        };
//                        loader.forceLoad();
//                        return loader;
//                    }
//
//                    @Override
//                    public void onLoadFinished(Loader<T> loader, T item) {
//                        bindData(item);
//                    }
//
//                    @Override
//                    public void onLoaderReset(Loader<T> loader) {
//
//                    }
//                });
//    }
//
//    private void bindData(Word word) {
//        setCorrectAnswer(word.getTranslation());
//        setTitle(word);
//        setHint();
//        setUpLettersAndSpaces();
//    }
//
//    private void setCorrectAnswer(String answer) {
//        mAnswer = answer;
//    }
//
//    private void setTitle(Word word) {
//        mTitle.setText(word.getNativeText());
//    }
//
//    private void setHint() {
//        mHintImage.setOnClickListener(view -> showHint());
//    }
//
//    private void showHint() {
//        if (!hasUserAnswered()) {
//            mHintLabel.setText(mAnswer.substring(mNextEmptyPosition, mNextEmptyPosition + 1));
//            fadeViewInOut(mHintLabel);
//        }
//    }
//
//    private void checkLetter(FrameLayout letterButton) {
//        if (!hasUserAnswered()) {
//            TextView letterButtonLabel = getLetterButtonLabel(letterButton);
//            if (isLetterCorrect(letterButtonLabel.getText().toString())) {
//                highlightButton(letterButton);
//                showLetter(letterButtonLabel);
//            } else {
//                showNotifier(getLetterButtonNotifier(letterButton));
//            }
//        }
//    }
//
//    private boolean hasUserAnswered() {
//        return mNextEmptyPosition >= mAnswer.length();
//    }
//
//    private boolean isLetterCorrect(String letter) {
//        return letter.equals(mAnswer.substring(mNextEmptyPosition, mNextEmptyPosition + 1));
//    }
//
//    private void showNotifier(ImageView notifier) {
//        fadeViewInOut(notifier);
//    }
//
//    private void fadeViewInOut(View view) {
//        view.setVisibility(View.VISIBLE);
//
//        Animation bummerIn = AnimationUtils.loadAnimation(getContext(), R.anim.bummer_in);
//        Animation bummerOut = AnimationUtils.loadAnimation(getContext(), R.anim.bummer_out);
//        bummerIn.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                view.startAnimation(bummerOut);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        bummerOut.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                view.setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//
//
//        view.startAnimation(bummerIn);
//    }
//
//    private void showLetter(TextView letterButton) {
//        TextView answerLabel = mAnswerLabels.get(mNextEmptyPosition);
//        SpannableString content = new SpannableString(letterButton.getText().toString());
//        content.setSpan(new UnderlineSpan(), 0, 1, 0);
//        answerLabel.setText(content);
//        if (lastLetter(mNextEmptyPosition++)) finishRound();
//    }
//
//    private void highlightButton(FrameLayout letterButton) {
//        TextView letterButtonLabel = getLetterButtonLabel(letterButton);
//        letterButtonLabel.setBackgroundResource(R.drawable.bg_letter_selected);
//        letterButtonLabel.setTextColor(ContextCompat.getColor(getContext(), R.color.colorTextWhite));
//        letterButton.setClickable(false);
//    }
//
//    private void setUpLettersAndSpaces() {
//
//        String empty = " ";
//        SpannableString content = new SpannableString(empty);
//        content.setSpan(new UnderlineSpan(), 0, empty.length(), 0);
//
//        if (mAnswer != null) {
//            for (int i = 0; i < mAnswer.length(); i++) {
//
//                createLetterLabel(content);
//                createLetterButton(mAnswer.substring(i, i + 1));
//
//            }
//            createLetterButton(getRandomLetter());
//            shuffleOptions();
//        }
//    }
//
//    private void createLetterLabel(SpannableString text) {
//        View labelRoot = mLayoutInflater.inflate(R.layout.letter_answer, mLettersAnswerSpace, false);
//        TextView letterLabel = (TextView) labelRoot.findViewById(R.id.letter_space);
//        letterLabel.setText(text);
//        mAnswerLabels.add(letterLabel);
//        mLettersAnswerSpace.addView(letterLabel);
//    }
//
//    private void createLetterButton(String text) {
//        View buttonRoot = mLayoutInflater.inflate(R.layout.letter_button, mButtonSpaceRowOne, false);
//        FrameLayout letterButton = (FrameLayout) buttonRoot.findViewById(R.id.root);
//        letterButton.setOnClickListener(view -> checkLetter(letterButton));
//        TextView letterButtonLabel = getLetterButtonLabel(letterButton);
//        letterButtonLabel.setText(text);
//        mButtons.add(letterButton);
//    }
//
//    private TextView getLetterButtonLabel(FrameLayout letterButton) {
//        return (TextView) letterButton.findViewById(R.id.letter_button);
//    }
//
//    private ImageView getLetterButtonNotifier(FrameLayout letterButton) {
//        return (ImageView) letterButton.findViewById(R.id.wrong_answer_notifier);
//    }
//
//    private String getRandomLetter() {
//        return Character.toString((char) (new Random().nextInt(26) + 'A'));
//    }
//
//    private void shuffleOptions() {
//        Collections.shuffle(mButtons);
//        for (FrameLayout option : mButtons) {
//            if (mButtonSpaceRowOne.getChildCount() < 5)
//                mButtonSpaceRowOne.addView(option);
//            else mLetterButtonSpaceRowTwo.addView(option);
//        }
//    }
//
//    private boolean lastLetter(int emptySpaceIndex) {
//        return emptySpaceIndex == mAnswer.length() - 1;
//    }
//
//    private void finishRound() {
//        showAnswer();
//        showContinueButton();
//    }
//
//    private void showAnswer() {
//        mRightAnswerNotifier.setVisibility(View.VISIBLE);
//    }
//
//    private void showContinueButton() {
//        mContinueButton.setVisibility(View.VISIBLE);
//    }
//}
