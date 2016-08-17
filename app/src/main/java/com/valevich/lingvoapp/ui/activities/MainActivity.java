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
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.valevich.lingvoapp.R;
import com.valevich.lingvoapp.eventbus.EventBus;
import com.valevich.lingvoapp.ui.fragments.AchievementsFragment_;
import com.valevich.lingvoapp.ui.fragments.CardsFragment_;
import com.valevich.lingvoapp.ui.fragments.DictionaryFragment_;
import com.valevich.lingvoapp.ui.fragments.PhraseBookFragment_;
import com.valevich.lingvoapp.ui.fragments.SettingsFragment_;
import com.valevich.lingvoapp.ui.fragments.TrainingsFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;


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

    @Bean
    EventBus mEventBus;

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            replaceFragment(new AchievementsFragment_());
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
        } else if (mFragmentManager.getBackStackEntryCount() == 1) {
            finish();
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

    private void setupNavigationContent() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (mDrawerLayout != null) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.drawer_achievements:
                        replaceFragment(new AchievementsFragment_());
                        break;
                    case R.id.drawer_cards:
                        replaceFragment(new CardsFragment_());
                        break;
                    case R.id.drawer_phrasebook:
                        replaceFragment(new PhraseBookFragment_());
                        break;
                    case R.id.drawer_dictionary:
                        replaceFragment(new DictionaryFragment_());
                        break;
                    case R.id.drawer_trainings:
                        replaceFragment(new TrainingsFragment_());
                        break;
                    case R.id.drawer_settings:
                        replaceFragment(new SettingsFragment_());
                        break;
                    case R.id.drawer_exit:
                        navigateToLogIn();
                        break;
                }
                return true;
            }
        });
    }

    private void changeToolbarTitle(String backStackEntryName) {
        if (backStackEntryName.equals(AchievementsFragment_.class.getName())) {
            setTitle(mAchievementsTitle);
            mNavigationView.setCheckedItem(R.id.drawer_achievements);
        } else if (backStackEntryName.equals(CardsFragment_.class.getName())) {
            setTitle(mCardsTitle);
            mNavigationView.setCheckedItem(R.id.drawer_cards);
        } else if (backStackEntryName.equals(SettingsFragment_.class.getName())) {
            setTitle(mSettingsTitle);
            mNavigationView.setCheckedItem(R.id.drawer_settings);
        } else if (backStackEntryName.equals(DictionaryFragment_.class.getName())){
            setTitle(mDictionaryTitle);
            mNavigationView.setCheckedItem(R.id.drawer_dictionary);
        } else if (backStackEntryName.equals(PhraseBookFragment_.class.getName())){
            setTitle(mPhraseBookTitle);
            mNavigationView.setCheckedItem(R.id.drawer_phrasebook);
        } else if (backStackEntryName.equals(TrainingsFragment_.class.getName())){
            setTitle(mTrainingsTitle);
            mNavigationView.setCheckedItem(R.id.drawer_trainings);
        }
    }

    private void setupDrawerLayout() {
        setupNavigationContent();
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

    private void replaceFragment(Fragment fragment) {
        String backStackName = fragment.getClass().getName();

        boolean isFragmentPopped = mFragmentManager.popBackStackImmediate(backStackName, 0);

        if (!isFragmentPopped && mFragmentManager.findFragmentByTag(backStackName) == null) {

            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.replace(R.id.main_container, fragment, backStackName);
            transaction.addToBackStack(backStackName);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.commit();

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

    private void notifyUser(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_SHORT).show();
    }
}
