package com.valevich.lingvoapp.ui.fragments.trainings;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Word;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.util.List;

@EFragment(R.layout.fragment_sound_word_training)
public class SoundWordFragment extends TrainingsBaseFragment {

    @ViewById(R.id.title)
    TextView mTitle;

    @ViewById(R.id.hint)
    ImageView mHintImage;

    @ViewById(R.id.play_button)
    ImageView mPlayButton;

    @ViewById(R.id.button_next)
    Button mContinueButton;

    @ViewsById({R.id.option_one,R.id.option_two,R.id.option_three,R.id.option_four})
    List<View> mOptions;

    @ViewsById({R.id.option_one_answer_notifier,
            R.id.option_two_answer_notifier,
            R.id.option_three_answer_notifier,
            R.id.option_four_answer_notifier})
    List<ImageView> mNotifiers;

    @AfterViews
    void setUpViews() {
        collectOptions(mOptions);
        collectNotifiers(mNotifiers);
        setContinueButton(mContinueButton);
    }

    @Override
    void bindData(List<Word> words) {
        setUpPlayButton(words.get(0));
        setUpHint();
        setTitle(words.get(0),true);
        setOptions(words,true);
    }

    private void setUpPlayButton(Word correctAnswer) {
        mPlayButton.setOnClickListener(view -> {
            //play sound
        });
    }

    private void setUpHint() {
        mHintImage.setOnClickListener(view -> toggleHint());
    }

    private void toggleHint() {
        if(mPlayButton.getVisibility() == View.VISIBLE) {
            mPlayButton.setVisibility(View.GONE);
            mTitle.setVisibility(View.VISIBLE);
        } else {
            mPlayButton.setVisibility(View.VISIBLE);
            mTitle.setVisibility(View.GONE);
        }
    }

    private void setTitle(Word correctAnswer, boolean areOptionsTranslated) {
        mTitle.setText(areOptionsTranslated
                ? correctAnswer.getNativeText()
                : correctAnswer.getTranslation());
    }

    private void setOptions(List<Word> words, boolean isOptionTranslated) {

        for (int optionIndex = 0; optionIndex < OPTIONS_COUNT; optionIndex++) {
            TextView label = (TextView) mOptions.get(optionIndex);
            Word word = getRandomWord(words);
            if(word.equals(words.get(0))) setCorrectAnswerIndex(optionIndex);

            setOptionText(label, word, isOptionTranslated);
            setOptionClickListener(optionIndex, label);
        }
    }

    private void setOptionText(TextView label, Word word, boolean isOptionTranslated) {
        label.setText(isOptionTranslated ? word.getTranslation() : word.getNativeText());
    }
}
