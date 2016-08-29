package com.valevich.lingvoapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.eventbus.EventBus;
import com.valevich.lingvoapp.eventbus.events.ItemSelectedEvent;
import com.valevich.lingvoapp.eventbus.events.PhraseCategorySelectedEvent;
import com.valevich.lingvoapp.eventbus.events.TrainingSelectedEvent;
import com.valevich.lingvoapp.eventbus.events.WordCategorySelectedEvent;
import com.valevich.lingvoapp.ui.fragments.DetailsWrapper_;
import com.valevich.lingvoapp.ui.fragments.PhraseMediaFragment_;
import com.valevich.lingvoapp.ui.fragments.ProfileDetailsFragment_;
import com.valevich.lingvoapp.ui.fragments.WordMediaFragment_;
import com.valevich.lingvoapp.ui.fragments.menusections.AchievementsFragment_;
import com.valevich.lingvoapp.ui.fragments.menusections.DictionaryFragment_;
import com.valevich.lingvoapp.ui.fragments.menusections.PhraseBookFragment_;
import com.valevich.lingvoapp.ui.fragments.menusections.SettingsFragment_;
import com.valevich.lingvoapp.ui.fragments.menusections.TrainingsFragment_;
import com.valevich.lingvoapp.ui.fragments.menusections.WordCategoriesFragment_;
import com.valevich.lingvoapp.ui.fragments.trainings.PhraseConstructionFragment_;
import com.valevich.lingvoapp.ui.fragments.trainings.PictureWordFragment_;
import com.valevich.lingvoapp.ui.fragments.trainings.SoundPictureFragment_;
import com.valevich.lingvoapp.ui.fragments.trainings.SoundWordFragment_;
import com.valevich.lingvoapp.ui.fragments.trainings.TranslateWordFragment_;
import com.valevich.lingvoapp.ui.fragments.trainings.WordPictureFragment_;
import com.valevich.lingvoapp.ui.fragments.trainings.WordsConstructionFragment_;
import com.valevich.lingvoapp.utils.ImageLoader;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

@OptionsMenu(R.menu.menu_main)
@EActivity
public class MainActivity extends AppCompatActivity
        implements FragmentManager.OnBackStackChangedListener {

    @ViewById(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.navigation_view)
    NavigationView mNavigationView;

    @StringRes(R.string.nav_drawer_achievements)
    String mAchievementsTitle;

    @StringRes(R.string.nav_drawer_cards)
    String mCardsTitle;

    @StringRes(R.string.nav_drawer_dictionary)
    String mDictionaryTitle;

    @StringRes(R.string.nav_drawer_phrasebook)
    String mPhraseBookTitle;

    @StringRes(R.string.nav_drawer_trainings)
    String mTrainingsTitle;

    @StringRes(R.string.nav_drawer_settings)
    String mSettingsTitle;

    @StringRes(R.string.profile_details)
    String mProfileDetailsTitle;

    @Bean
    EventBus mEventBus;

    @Bean
    ImageLoader mImageLoader;

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            replaceFragment(new AchievementsFragment_(), false);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        mEventBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mEventBus.unregister(this);
    }

    @AfterViews
    void setupViews() {
        setupActionBar();
        setupDrawerLayout();
        setupFragmentManager();
    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void onBackStackChanged() {

        Fragment f = mFragmentManager
                .findFragmentById(R.id.main_container);

        if (f != null) {
            changeToolbarTitle(f.getClass().getName());
        }

    }

    @Subscribe
    public void onWordCategorySelected(WordCategorySelectedEvent event) {
        replaceFragment(WordMediaFragment_.builder().categoryName(event.getCategoryName()).build(),
                true);
    }

    @Subscribe
    public void onPhraseCategorySelected(PhraseCategorySelectedEvent event) {
        replaceFragment(PhraseMediaFragment_.builder().categoryName(event.getCategoryName()).build(),
                true);
    }

    @Subscribe
    public void onItemSelected(ItemSelectedEvent event) {
        replaceFragment(DetailsWrapper_.builder().items(event.getItems()).position(event.getPosition()).build(),
                true);
    }

    @Subscribe
    public void onTrainingSelected(TrainingSelectedEvent event) {
        switch (event.getTraining()) {
            case TRANSLATION_WORD:
                replaceFragment(TranslateWordFragment_.builder().areOptionsTranslated(true).build(),
                        true);
                break;
            case WORD_TRANSLATION:
                replaceFragment(TranslateWordFragment_.builder().areOptionsTranslated(false).build(),
                        true);
                break;
            case WORD_PICTURE:
                replaceFragment(new WordPictureFragment_(), true);
                break;
            case PICTURE_WORD:
                replaceFragment(new PictureWordFragment_(), true);
                break;
            case SOUND_WORD:
                replaceFragment(new SoundWordFragment_(), true);
                break;
            case SOUND_PICTURE:
                replaceFragment(new SoundPictureFragment_(), true);
                break;
            case WORD_CONSTRUCTOR:
                replaceFragment(new WordsConstructionFragment_(), true);
                break;
            case PHRASE_CONSTRUCTOR:
                replaceFragment(new PhraseConstructionFragment_(), true);
                break;
        }
    }

    private void setupNavigationContent() {
        mNavigationView.setNavigationItemSelectedListener(item -> {
            if (mDrawerLayout != null) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.drawer_achievements:
                    replaceFragment(new AchievementsFragment_(), false);
                    break;
                case R.id.drawer_cards:
                    replaceFragment(new WordCategoriesFragment_(), false);
                    break;
                case R.id.drawer_phrasebook:
                    replaceFragment(new PhraseBookFragment_(), false);
                    break;
                case R.id.drawer_dictionary:
                    replaceFragment(new DictionaryFragment_(), false);
                    break;
                case R.id.drawer_trainings:
                    replaceFragment(new TrainingsFragment_(), false);
                    break;
                case R.id.drawer_settings:
                    replaceFragment(new SettingsFragment_(), false);
                    break;
                case R.id.drawer_exit:
                    navigateToLogIn();
                    break;
            }
            return true;
        });
        mNavigationView.setItemIconTintList(null);

        View headerView = mNavigationView.getHeaderView(0);
        ImageView profileImage = (ImageView) headerView.findViewById(R.id.profile_image);
        TextView nameField = (TextView) headerView.findViewById(R.id.name);
        TextView emailField = (TextView) headerView.findViewById(R.id.email);

        nameField.setText(R.string.default_username);
        emailField.setText(R.string.default_user_email);
        mImageLoader.loadImageByResId(R.drawable.man_main, profileImage);
        // TODO: 21.08.2016 load user image

    }

    private void changeToolbarTitle(String fragmentName) {
        if (fragmentName.equals(AchievementsFragment_.class.getName())) {
            setTitle(mAchievementsTitle);
            mNavigationView.setCheckedItem(R.id.drawer_achievements);
        } else if (fragmentName.equals(WordCategoriesFragment_.class.getName())) {
            setTitle(mCardsTitle);
            mNavigationView.setCheckedItem(R.id.drawer_cards);
        } else if (fragmentName.equals(SettingsFragment_.class.getName())) {
            setTitle(mSettingsTitle);
            mNavigationView.setCheckedItem(R.id.drawer_settings);
        } else if (fragmentName.equals(DictionaryFragment_.class.getName())) {
            setTitle(mDictionaryTitle);
            mNavigationView.setCheckedItem(R.id.drawer_dictionary);
        } else if (fragmentName.equals(PhraseBookFragment_.class.getName())) {
            setTitle(mPhraseBookTitle);
            mNavigationView.setCheckedItem(R.id.drawer_phrasebook);
        } else if (fragmentName.equals(WordMediaFragment_.class.getName())) {
            setTitle(mCardsTitle);
        } else if (fragmentName.equals(PhraseMediaFragment_.class.getName())) {
            setTitle(mPhraseBookTitle);
        } else if (fragmentName.equals(DetailsWrapper_.class.getName())) {
            setTitle("");
        } else if (fragmentName.equals(ProfileDetailsFragment_.class.getName())) {
            setTitle(mProfileDetailsTitle);
        } else {
            setTitle(mTrainingsTitle);
            mNavigationView.setCheckedItem(R.id.drawer_trainings);
        }
    }

    private void setupDrawerLayout() {
        setupNavigationContent();
        setUpProfileImage();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this
                , mDrawerLayout
                , mToolbar
                , R.string.navigation_drawer_open
                , R.string.navigation_drawer_close);
//        toggle.setDrawerIndicatorEnabled(false);
//        toggle.setHomeAsUpIndicator(R.drawable.menu);
//        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
//                    mDrawerLayout.closeDrawer(GravityCompat.START);
//                } else {
//                    mDrawerLayout.openDrawer(GravityCompat.START);
//                }
//            }
//        });
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
    }

    private void setupActionBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setUpProfileImage() {
        View header = mNavigationView.getHeaderView(0);
        ImageView profileImage = (ImageView) header.findViewById(R.id.profile_image);
        profileImage.setOnClickListener(view -> {
            if (mDrawerLayout != null) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
            replaceFragment(new ProfileDetailsFragment_(), true);
        });
    }

    private void replaceFragment(Fragment fragment, boolean neededToRemember) {
        String backStackName = fragment.getClass().getName();

        boolean isFragmentPopped = false;

        if(neededToRemember) {
            isFragmentPopped = mFragmentManager
                    .popBackStackImmediate(backStackName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            mFragmentManager
                    .popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        if(!isFragmentPopped && mFragmentManager.findFragmentByTag(backStackName) == null) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.replace(R.id.main_container, fragment, backStackName);
            if (neededToRemember) transaction.addToBackStack(backStackName);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.commit();

            changeToolbarTitle(backStackName);
        }
    }

    private void setupFragmentManager() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(this);
    }

    private void navigateToLogIn() {
        Intent intent = new Intent(this, LoginActivity_.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void notifyUserWithToast(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_SHORT).show();
    }
}
