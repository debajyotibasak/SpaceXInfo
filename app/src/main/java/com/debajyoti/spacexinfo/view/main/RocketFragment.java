package com.debajyoti.spacexinfo.view.main;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import com.debajyoti.spacexinfo.interfaces.RocketClickListener;
import com.debajyoti.spacexinfo.view.MainViewModel;
import com.debajyoti.spacexinfo.view.adapter.RocketAdapter;
import com.debajyoti.spacexinfo.view.detail.RocketDetailActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * A simple {@link Fragment} subclass.
 */
public class RocketFragment extends DaggerFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MainViewModel mainViewModel;

    private RocketAdapter rocketAdapter;
    private RecyclerView rvRocket;

    public RocketFragment() {
        // Required empty public constructor
    }

    public static RocketFragment newInstance() {
        Bundle args = new Bundle();
        RocketFragment fragment = new RocketFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initViews(View view) {
        rvRocket = view.findViewById(R.id.rvRocket);
    }

    private void initData() {
        rocketAdapter = new RocketAdapter(getActivity());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rocket, container, false);

        initViews(view);
        initData();

        rvRocket.setAdapter(rocketAdapter);
        rvRocket.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRocket.setHasFixedSize(true);

        mainViewModel.getRocketFromDb().observe(this, rocketList -> {
            if (rocketList != null && !rocketList.isEmpty()) {
                rocketAdapter.addRocketList(rocketList);
            }
        });

        return view;
    }
}
