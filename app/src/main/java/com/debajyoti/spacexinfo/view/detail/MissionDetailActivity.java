package com.debajyoti.spacexinfo.view.detail;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.debajyoti.spacexinfo.R;
import com.debajyoti.spacexinfo.utils.AppConstants;
import com.debajyoti.spacexinfo.utils.AppUtils;
import com.debajyoti.spacexinfo.utils.SharedPreferenceHelper;
import com.debajyoti.spacexinfo.view.MissionDetailViewModel;
import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MissionDetailActivity extends DaggerAppCompatActivity {

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private MissionDetailViewModel missionDetailViewModel;
    private Handler handler = new Handler();

    private Toolbar mToolbar;
    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView mImvToolbarMission;

    private TextView mTxvToolbarMissionName, mTxvMissionName, mTxvMissionDescription, mTxvLaunchDate, mTxvLaunchYear, mTxvLaunchSite,
            mTxvRocketName, mTxvRocketDetails, mTxvWikiLink, mTxvSpacexLink;

    private void initViews() {
        mToolbar = findViewById(R.id.toolbar);
        mAppBarLayout = findViewById(R.id.appBar);
        mCollapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        mTxvToolbarMissionName = findViewById(R.id.txvToolbarMissionName);
        mImvToolbarMission = findViewById(R.id.imvToolbarMissionPatchImage);

        mTxvMissionName = findViewById(R.id.txvMissionName);
        mTxvMissionDescription = findViewById(R.id.txvMissionDescription);
        mTxvLaunchDate = findViewById(R.id.txvLaunchDate);
        mTxvLaunchSite = findViewById(R.id.txvLaunchSite);
        mTxvLaunchYear = findViewById(R.id.txvLaunchYear);

        mTxvRocketName = findViewById(R.id.txvRocket);
        mTxvRocketDetails = findViewById(R.id.txvRocketDetails);

        mTxvWikiLink = findViewById(R.id.txvWikiLink);
        mTxvSpacexLink = findViewById(R.id.txvSpacexLink);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_detail);

        missionDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(MissionDetailViewModel.class);

        initViews();
        setupToolbar();
        observeMissionFromDb();
        logMissionNameInAnalytics();
        setupRocketDetails();
        setupWikiLinkIntent();
        setupArticleLinkIntent();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.AppBarCollapsed);
    }

    private void observeMissionFromDb() {
        missionDetailViewModel.getMissionById().observe(this, mission -> {
            if (mission != null) {
                setUpToolbarTitle(mission.getMissionName());
                loadToolbarImage(mission.getLinks().getMissionPatch());

                mTxvMissionName.setText(mission.getMissionName());
                mTxvMissionDescription.setText(mission.getDetails());
                mTxvLaunchDate.setText(AppUtils.convertDateFromUTCtoISTDetails(mission.getLaunchDateUtc()));
                mTxvLaunchSite.setText(mission.getLaunchSite().getSiteNameLong());
                mTxvLaunchYear.setText(mission.getLaunchYear());
                mTxvRocketName.setText(mission.getRocketId().getRocketName());
                mTxvWikiLink.setText(mission.getLinks().getWiki());
                mTxvSpacexLink.setText(mission.getLinks().getArticleLink());
            }
        });
    }

    private void logMissionNameInAnalytics() {
        missionDetailViewModel.getMissionById().observe(this, mission -> {
            if (mission != null) {
                Bundle missionClickBundle = new Bundle();
                missionClickBundle.putString(AppConstants.EVENT_MISSION_NAME, mission.getMissionName());
                firebaseAnalytics.logEvent(AppConstants.EVENT_MISSION_CLICK, missionClickBundle);
            }
        });
    }

    private void setUpToolbarTitle(String missionName) {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                mTxvToolbarMissionName.setText(missionName);
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mCollapsingToolbarLayout.setTitle(" ");
                    mTxvToolbarMissionName.setVisibility(View.VISIBLE);
                    isShow = true;
                } else if (isShow) {
                    mCollapsingToolbarLayout.setTitle(" ");
                    mTxvToolbarMissionName.setVisibility(View.GONE);
                    isShow = false;
                }
            }
        });
    }

    private void loadToolbarImage(String url) {
        Glide.with(mImvToolbarMission.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_placeholder))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        handler.post(() -> loadToolbarImage(url));
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(mImvToolbarMission);
    }

    private void setupRocketDetails() {
        mTxvRocketDetails.setOnClickListener(v -> {
            missionDetailViewModel.getMissionById().observe(this, mission -> {
                if (mission != null) {
                    startRocketActivity(mission.getRocketId().getRocketId());
                }
            });
        });
    }

    private void startRocketActivity(String rocketId) {
        missionDetailViewModel.getRocketId(rocketId).observe(this, rocketIntId -> {
            if (rocketIntId != null) {
                SharedPreferenceHelper.setSharedPreferenceInt("rId", rocketIntId);
                Intent intent = new Intent(MissionDetailActivity.this, RocketDetailActivity.class);
                intent.putExtra("rocketId", rocketIntId);
                startActivity(intent);
            }
        });
    }

    private void setupWikiLinkIntent() {
        mTxvWikiLink.setOnClickListener(v -> {
            missionDetailViewModel.getMissionById().observe(this, mission -> {
                if (mission != null) {
                    openUrlInBrowser(mission.getLinks().getWiki());
                    logWikiLinkClickedInAnalytics(mission.getLinks().getWiki());
                }
            });
        });
    }

    private void setupArticleLinkIntent() {
        mTxvSpacexLink.setOnClickListener(v -> {
            missionDetailViewModel.getMissionById().observe(this, mission -> {
                if (mission != null) {
                    openUrlInBrowser(mission.getLinks().getArticleLink());
                    logArticleLinkClickedInAnalytics(mission.getLinks().getArticleLink());
                }
            });
        });
    }

    private void logWikiLinkClickedInAnalytics(String wikiLink) {
        Bundle wikiLinkClickBundle = new Bundle();
        wikiLinkClickBundle.putString(AppConstants.EVENT_WIKI_LINK, wikiLink);
        firebaseAnalytics.logEvent(AppConstants.EVENT_WIKI_LINK_CLICK, wikiLinkClickBundle);
    }

    private void logArticleLinkClickedInAnalytics(String articleLink) {
        Bundle articleLinkClickBundle = new Bundle();
        articleLinkClickBundle.putString(AppConstants.EVENT_ARTICLE_LINK, articleLink);
        firebaseAnalytics.logEvent(AppConstants.EVENT_ARTICLE_LINK_CLICK, articleLinkClickBundle);
    }

    private void openUrlInBrowser(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
