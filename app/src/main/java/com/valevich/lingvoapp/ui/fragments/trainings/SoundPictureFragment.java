package com.valevich.lingvoapp.ui.fragments.trainings;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.stubmodel.Word;
import com.valevich.lingvoapp.utils.ImageLoader;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.util.List;

@EFragment(R.layout.fragment_sound_picture_training)
public class SoundPictureFragment extends OptionsBaseFragment {
    @ViewById(R.id.title)
    TextView mTitle;

    @ViewById(R.id.hint)
    ImageView mHintImage;

    @ViewById(R.id.play_button)
    ImageView mPlayButton;

    @ViewById(R.id.button_next)
    Button mContinueButton;

    @ViewsById({R.id.image_option_one,R.id.image_option_two,R.id.image_option_three,R.id.image_option_four})
    List<View> mOptions;

    @ViewsById({R.id.image_option_one_answer_notifier,
            R.id.image_option_two_answer_notifier,
            R.id.image_option_three_answer_notifier,
            R.id.image_option_four_answer_notifier})
    List<ImageView> mNotifiers;

    @Bean
    ImageLoader mImageLoader;

    private String mCorrectAnswer = "";

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
        setCorrectAnswer(words.get(0).getTranslation());
        setOptions(words);
    }

    @Override
    void hideHints() {
        hideHint();
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
        if(mPlayButton.getVisibility() == View.VISIBLE) showHint();
        else hideHint();
    }

    private void showHint() {
        mPlayButton.setVisibility(View.GONE);
        mTitle.setText(mCorrectAnswer);
    }

    private void hideHint() {
        mPlayButton.setVisibility(View.VISIBLE);
        mTitle.setText("");
    }

    private void setCorrectAnswer(String correctAnswer) {
        mCorrectAnswer = correctAnswer;
    }

    private void setOptions(List<Word> words) {
        Word answer = words.get(0);
        for (int optionIndex = 0; optionIndex < OPTIONS_COUNT; optionIndex++) {
            ImageView optionImage = (ImageView) mOptions.get(optionIndex);
            Word word = getRandomWord(words);
            if(word.equals(answer)) setCorrectAnswerIndex(optionIndex);

            setOptionImage(optionImage, word);
            setOptionClickListener(optionIndex, optionImage);
        }
    }

    private void setOptionImage(ImageView optionImage, Word word) {
        //mImageLoader.loadImageByUrl(word.getImageUrl(),optionImage);
        mImageLoader.loadImageByResId(word.getImageResId(),optionImage);
    }
}
