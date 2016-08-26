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

@EFragment(R.layout.fragment_picture_word_training)
public class PictureWordFragment extends OptionsBaseFragment {

    @ViewById(R.id.hint)
    ImageView mHintImage;

    @ViewById(R.id.word_picture)
    ImageView mWordPicture;

    @ViewById(R.id.button_next)
    Button mContinueButton;

    @ViewsById({R.id.option_one, R.id.option_two, R.id.option_three, R.id.option_four})
    List<View> mOptions;

    @ViewsById({R.id.option_one_answer_notifier,
            R.id.option_two_answer_notifier,
            R.id.option_three_answer_notifier,
            R.id.option_four_answer_notifier})
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
        setUpPicture(words.get(0));
        setUpHint();
        setOptions(words, true);
    }

    private void setUpPicture(Word correctWord) {
        //mImageLoader.loadImageByUrl(correctWord.getImageUrl(),mWordPicture);
        mImageLoader.loadImageByResId(correctWord.getImageResId(),mWordPicture);
    }

    private void setUpHint() {
        mHintImage.setOnClickListener(view -> toggleHint());
    }

    private void toggleHint() {

    }

    private void setOptions(List<Word> words, boolean isOptionTranslated) {
        Word answer = words.get(0);
        for (int optionIndex = 0; optionIndex < OPTIONS_COUNT; optionIndex++) {
            TextView label = (TextView) mOptions.get(optionIndex);
            Word word = getRandomWord(words);
            if (word.equals(answer)) setCorrectAnswerIndex(optionIndex);

            setOptionText(label, word, isOptionTranslated);
            setOptionClickListener(optionIndex, label);
        }
    }

    private void setOptionText(TextView label, Word word, boolean isOptionTranslated) {
        label.setText(isOptionTranslated ? word.getTranslation() : word.getNativeText());
    }
}
