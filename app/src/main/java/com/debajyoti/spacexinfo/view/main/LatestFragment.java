package com.debajyoti.spacexinfo.view.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.debajyoti.spacexinfo.R;
import com.debajyoti.spacexinfo.interfaces.RocketClickListener;
import com.debajyoti.spacexinfo.utils.AppUtils;
import com.debajyoti.spacexinfo.view.MainViewModel;
import com.debajyoti.spacexinfo.view.detail.RocketDetailActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


/**
 * A simple {@link Fragment} subclass.
 */
public class LatestFragment extends DaggerFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MainViewModel mainViewModel;

    private TextView txvUpcomingMissionName, txvUpcomingMissionDate, txvPreviousMissionName, txvPreviousMissionDate;

    public LatestFragment() {
        // Required empty public constructor
    }

    public static LatestFragment newInstance() {
        Bundle args = new Bundle();
        LatestFragment fragment = new LatestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initViews(View view) {
        txvUpcomingMissionName = view.findViewById(R.id.txvUpcomingMissionName);
        txvUpcomingMissionDate = view.findViewById(R.id.txvUpcomingMissionDate);
        txvPreviousMissionName = view.findViewById(R.id.txvPreviousMissionName);
        txvPreviousMissionDate = view.findViewById(R.id.txvPreviousMissionDate);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_latest, container, false);

        initViews(view);

        mainViewModel.getNextLaunchFromDb().observe(this, nextLaunch -> {
            if (nextLaunch != null) {
                txvUpcomingMissionName.setText(nextLaunch.getMissionName());
                String ISTDateFromUTC = AppUtils.convertDateFromUTCtoIST(nextLaunch.getLaunchDateUtc());
                txvUpcomingMissionDate.setText(ISTDateFromUTC);
            }
        });

        mainViewModel.getLatestLaunchesFromDb().observe(this, previousLaunch -> {
            if (previousLaunch != null) {
                txvPreviousMissionName.setText(previousLaunch.getMissionName());
                String ISTDateFromUTC = AppUtils.convertDateFromUTCtoIST(previousLaunch.getLaunchDateUtc());
                txvPreviousMissionDate.setText(ISTDateFromUTC);
            }
        });

        return view;
    }
}
