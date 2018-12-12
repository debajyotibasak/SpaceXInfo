package com.debajyoti.spacexinfo.view.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.debajyoti.spacexinfo.R;
import com.debajyoti.spacexinfo.interfaces.MissionClickListener;
import com.debajyoti.spacexinfo.interfaces.RocketClickListener;
import com.debajyoti.spacexinfo.utils.AppConstants;
import com.debajyoti.spacexinfo.utils.BottomNavigationViewHelper;
import com.debajyoti.spacexinfo.utils.SharedPreferenceHelper;
import com.debajyoti.spacexinfo.view.MainViewModel;
import com.debajyoti.spacexinfo.view.detail.MissionDetailActivity;
import com.debajyoti.spacexinfo.view.detail.RocketDetailActivity;
import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends DaggerAppCompatActivity implements RocketClickListener, MissionClickListener {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private MainViewModel mainViewModel;

    private Toolbar toolbar;
    private TextView txvToolbar;
    private ImageView imvToolbar;
    private BottomNavigationView bottomNavigationView;

    final Fragment fragment1 = LatestFragment.newInstance();
    final Fragment fragment2 = RocketFragment.newInstance();
    final Fragment fragment3 = MissionFragment.newInstance();
    final Fragment fragment4 = InfoFragment.newInstance();

    private Fragment active = fragment1;

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        txvToolbar = findViewById(R.id.txvToolbar);
        imvToolbar = findViewById(R.id.imvToolbar);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);

        initViews();
        addFragments();
        setupBottomNavigationClickListener();
    }

    private void hideToolbarSpacexLogo() {
        if (imvToolbar.getVisibility() == View.VISIBLE) imvToolbar.setVisibility(View.GONE);
        if (imvToolbar.getVisibility() == View.GONE) txvToolbar.setVisibility(View.VISIBLE);
    }

    private void hideToolbarTextView() {
        if (imvToolbar.getVisibility() == View.GONE) imvToolbar.setVisibility(View.VISIBLE);
        if (imvToolbar.getVisibility() == View.VISIBLE) txvToolbar.setVisibility(View.GONE);
    }

    private void hideToolbar() {
        if (toolbar.getVisibility() == View.VISIBLE) toolbar.setVisibility(View.GONE);
    }

    private void showToolbar() {
        if (toolbar.getVisibility() == View.GONE) toolbar.setVisibility(View.VISIBLE);
    }

    private void setupBottomNavigationClickListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void addFragments() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment1, "1").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(fragment2).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment3, "3").hide(fragment3).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment4, "4").hide(fragment4).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {

            case R.id.navigation_latest:
                showToolbar();
                hideToolbarTextView();
                getSupportFragmentManager().beginTransaction().hide(active).show(fragment1).commit();
                active = fragment1;
                return true;

            case R.id.navigation_info:
                hideToolbar();
                hideToolbarSpacexLogo();
                txvToolbar.setText("Info");
                getSupportFragmentManager().beginTransaction().hide(active).show(fragment4).commit();
                active = fragment4;
                return true;

            case R.id.navigation_mission:
                showToolbar();
                hideToolbarSpacexLogo();
                txvToolbar.setText("Mission");
                getSupportFragmentManager().beginTransaction().hide(active).show(fragment3).commit();
                active = fragment3;
                return true;

            case R.id.navigation_rockets:
                showToolbar();
                hideToolbarSpacexLogo();
                txvToolbar.setText("Rockets");
                getSupportFragmentManager().beginTransaction().hide(active).show(fragment2).commit();
                active = fragment2;
                return true;
        }
        return false;
    };

    @Override
    public void onRocketItemClick(int rocketId) {
        SharedPreferenceHelper.setSharedPreferenceInt("rId", rocketId);
        Intent intent = new Intent(MainActivity.this, RocketDetailActivity.class);
        intent.putExtra("rocketId", rocketId);
        startActivity(intent);
    }

    @Override
    public void onMissionItemClick(int missionId) {
        SharedPreferenceHelper.setSharedPreferenceInt("mId", missionId);
        Intent intent = new Intent(MainActivity.this, MissionDetailActivity.class);
        intent.putExtra("missionId", missionId);
        startActivity(intent);
    }
}
