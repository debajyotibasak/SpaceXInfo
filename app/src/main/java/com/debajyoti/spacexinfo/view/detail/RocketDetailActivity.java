package com.debajyoti.spacexinfo.view.detail;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.Drawable;
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
import com.debajyoti.spacexinfo.repo.SpacexRepo;
import com.debajyoti.spacexinfo.utils.AppConstants;
import com.debajyoti.spacexinfo.utils.AppUtils;
import com.debajyoti.spacexinfo.view.RocketDetailViewModel;
import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class RocketDetailActivity extends DaggerAppCompatActivity {

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private RocketDetailViewModel rocketDetailViewModel;
    private Handler handler = new Handler();

    private Toolbar mToolbar;
    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView mImvToolbarRocket;
    private TextView mTxvToolbarRocket, mTxvRocketDescription, mTxvRocketName, mTxvFirstFlightDate, mTxvCostPerLaunch,
            mTxvRocketStatus;

    // Technical Specifications
    private TextView mTxvRocketHeight, mTxvRocketDiameter, mTxvRocketMass, mTxvRocketStages;

    // First Stage
    private TextView mTxvFirstStageEngines, mTxvFirstStageFuelAmt, mTxvFirstStageReusable,
            mTxvFirstStageBurnTime;

    // Second Stage
    private TextView mTxvSecondStageEngines, mTxvSecondStageFuelAmt, mTxvSecondStageReusable,
            mTxvSecondStageBurnTime;


    private int rocketId = 0;

    private void initViews() {
        mToolbar = findViewById(R.id.toolbar);
        mAppBarLayout = findViewById(R.id.appBar);
        mCollapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        mImvToolbarRocket = findViewById(R.id.imvToolbarRocketImage);
        mTxvToolbarRocket = findViewById(R.id.txvToolbarRocketName);

        mTxvRocketDescription = findViewById(R.id.txvDescription);
        mTxvRocketName = findViewById(R.id.txvRocketName);
        mTxvFirstFlightDate = findViewById(R.id.txvFirstFlightDate);
        mTxvCostPerLaunch = findViewById(R.id.txvCostPerLaunchAmt);
        mTxvRocketStatus = findViewById(R.id.txvStatus);

        mTxvRocketHeight = findViewById(R.id.txvHeight);
        mTxvRocketDiameter = findViewById(R.id.txvDiameter);
        mTxvRocketMass = findViewById(R.id.txvMass);
        mTxvRocketStages = findViewById(R.id.txvStages);

        mTxvFirstStageEngines = findViewById(R.id.txvEngines);
        mTxvFirstStageFuelAmt = findViewById(R.id.txvFirstStageFuelAmt);
        mTxvFirstStageReusable = findViewById(R.id.txvReusable);
        mTxvFirstStageBurnTime = findViewById(R.id.txvBurnTime);

        mTxvSecondStageEngines = findViewById(R.id.txvSecondStageEngines);
        mTxvSecondStageFuelAmt = findViewById(R.id.txvSecondStageFuelAmt);
        mTxvSecondStageReusable = findViewById(R.id.txvSecondStageReusable);
        mTxvSecondStageBurnTime = findViewById(R.id.txvSecondStageBurnTime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rocket_detail);

        rocketDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(RocketDetailViewModel.class);

        initViews();
        processIntents();
        setupToolbar();
        observeRocketFromDb();
        logRocketNameInAnalytics();
    }

    private void processIntents() {
        if (getIntent().getExtras() != null) {
            if (getIntent().hasExtra("rocketId")) {
                rocketId = getIntent().getIntExtra("rocketId", 0);
            }
        }
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.AppBarCollapsed);
    }

    private void observeRocketFromDb() {
        rocketDetailViewModel.getRocketById().observe(this, rocket -> {
            if (rocket != null) {
                setUpToolbarTitle(rocket.getRocketName());
                loadToolbarImage(rocket.getFlickrImages().get(0));

                mTxvRocketDescription.setText(rocket.getDescription());
                mTxvRocketName.setText(rocket.getRocketName());
                mTxvFirstFlightDate.setText(AppUtils.convertDate(rocket.getFirstFlight(), AppConstants.DF1, AppConstants.DF2));
                mTxvCostPerLaunch.setText(String.format("$%s", AppUtils.convertCommaSeparatedCost(String.valueOf(rocket.getCostPerLaunch()))));
                mTxvRocketStatus.setText(rocket.isActive() ? "Active" : "Inactive");

                mTxvRocketHeight.setText(String.format("%s ft.", String.valueOf(rocket.getHeight().getFeet())));
                mTxvRocketDiameter.setText(String.format("%s ft.", String.valueOf(rocket.getDiameter().getFeet())));
                mTxvRocketMass.setText(String.format("%s Kgs", String.valueOf(rocket.getMass().getKg())));
                mTxvRocketStages.setText(String.valueOf(rocket.getStages()));

                mTxvFirstStageEngines.setText(String.valueOf(rocket.getFirstStage().getEngines()));
                mTxvFirstStageFuelAmt.setText(String.format("%s tons", String.valueOf(rocket.getFirstStage().getFuelAmountTons())));
                mTxvFirstStageReusable.setText(String.valueOf(rocket.getFirstStage().isReusable()));
                mTxvFirstStageBurnTime.setText(String.format("%s secs", String.valueOf(rocket.getFirstStage().getBurnTimeSec())));

                mTxvSecondStageEngines.setText(String.valueOf(rocket.getSecondStage().getEngines()));
                mTxvSecondStageFuelAmt.setText(String.format("%s tons", String.valueOf(rocket.getSecondStage().getFuelAmountTons())));
                mTxvSecondStageReusable.setText(String.valueOf(rocket.getSecondStage().isReusable()));
                mTxvSecondStageBurnTime.setText(String.format("%s secs", String.valueOf(rocket.getSecondStage().getBurnTimeSec())));
            }
        });
    }

    private void logRocketNameInAnalytics() {
        rocketDetailViewModel.getRocketById().observe(this, rocket -> {
            if (rocket != null) {
                Bundle rocketClickBundle = new Bundle();
                rocketClickBundle.putString(AppConstants.EVENT_ROCKET_NAME, rocket.getRocketName());
                firebaseAnalytics.logEvent(AppConstants.EVENT_ROCKET_CLICK, rocketClickBundle);
            }
        });
    }

    private void setUpToolbarTitle(String rocketName) {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                mTxvToolbarRocket.setText(rocketName);
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mCollapsingToolbarLayout.setTitle(" ");
                    mTxvToolbarRocket.setVisibility(View.VISIBLE);
                    isShow = true;
                } else if (isShow) {
                    mCollapsingToolbarLayout.setTitle(" ");
                    mTxvToolbarRocket.setVisibility(View.GONE);
                    isShow = false;
                }
            }
        });
    }

    private void loadToolbarImage(String url) {
        Glide.with(mImvToolbarRocket.getContext())
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
                .into(mImvToolbarRocket);
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
