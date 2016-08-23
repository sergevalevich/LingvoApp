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

@EFragment(R.layout.fragment_word_picture_training)
public class WordPictureFragment extends TrainingsBaseFragment{
    @ViewById(R.id.title)
    TextView mTitle;

    @ViewById(R.id.hint)
    ImageView mHintImage;

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

    @AfterViews
    void setUpViews() {
        collectOptions(mOptions);
        collectNotifiers(mNotifiers);
        setContinueButton(mContinueButton);
    }

    @Override
    void bindData(List<Word> words) {
        setUpHint();
        setTitle(words.get(0),true);
        setOptions(words);
    }

    private void setUpHint() {
        // TODO: 23.08.2016 show hint
    }

    private void setTitle(Word correctAnswer, boolean areOptionsTranslated) {
        mTitle.setText(areOptionsTranslated
                ? correctAnswer.getNativeText()
                : correctAnswer.getTranslation());
    }

    private void setOptions(List<Word> words) {

        for (int optionIndex = 0; optionIndex < OPTIONS_COUNT; optionIndex++) {
            ImageView optionImage = (ImageView) mOptions.get(optionIndex);
            Word word = getRandomWord(words);
            if(word.equals(words.get(0))) setCorrectAnswerIndex(optionIndex);

            setOptionImage(optionImage, word);
            setOptionClickListener(optionIndex, optionImage);
        }
    }

    private void setOptionImage(ImageView optionImage, Word word) {
        mImageLoader.loadImageByUrl(word.getImageUrl(),optionImage);
    }
}
