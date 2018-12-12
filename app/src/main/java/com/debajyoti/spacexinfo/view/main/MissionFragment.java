package com.debajyoti.spacexinfo.view.main;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.debajyoti.spacexinfo.R;
import com.debajyoti.spacexinfo.view.MainViewModel;
import com.debajyoti.spacexinfo.view.adapter.MissionAdapter;
import com.debajyoti.spacexinfo.view.adapter.RocketAdapter;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MissionFragment extends DaggerFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MainViewModel mainViewModel;

    private MissionAdapter missionAdapter;
    private RecyclerView rvMission;

    public MissionFragment() {
        // Required empty public constructor
    }

    public static MissionFragment newInstance() {
        Bundle args = new Bundle();
        MissionFragment fragment = new MissionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initViews(View view) {
        rvMission = view.findViewById(R.id.rvMissions);
    }

    private void initData() {
        missionAdapter = new MissionAdapter(getActivity());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mission, container, false);

        initViews(view);
        initData();

        rvMission.setAdapter(missionAdapter);
        rvMission.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMission.setHasFixedSize(true);

        mainViewModel.getPastLaunchesFromDb().observe(this, missionList -> {
            if (missionList != null && !missionList.isEmpty()) {
                missionAdapter.addMissionList(missionList);
            }
        });

        return view;
    }

}
