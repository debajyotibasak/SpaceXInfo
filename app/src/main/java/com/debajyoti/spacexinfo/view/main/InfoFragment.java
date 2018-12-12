package com.debajyoti.spacexinfo.view.main;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.debajyoti.spacexinfo.R;
import com.debajyoti.spacexinfo.utils.AppUtils;
import com.debajyoti.spacexinfo.view.MainViewModel;
import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MainViewModel mainViewModel;

    private TextView txvCeoName, txvCtoName, txvCooName, txvCtoNamePropDep,
            txvValuationAmt, txvEmployeesAmt, txvVehiclesAmt, txvDescription;

    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance() {
        Bundle args = new Bundle();
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initViews(View view) {
        txvCeoName = view.findViewById(R.id.txvCeoName);
        txvCtoName = view.findViewById(R.id.txvCtoName);
        txvCooName = view.findViewById(R.id.txvCooName);
        txvCtoNamePropDep = view.findViewById(R.id.txvCtoNamePropDep);
        txvValuationAmt = view.findViewById(R.id.txvValuationAmt);
        txvEmployeesAmt = view.findViewById(R.id.txvEmployeesAmt);
        txvVehiclesAmt = view.findViewById(R.id.txvVehiclesAmt);
        txvDescription = view.findViewById(R.id.txvSpacexDesciption);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);

        initViews(view);

        mainViewModel.getInfoFromDb().observe(this, info -> {
            if (info != null) {
                txvCeoName.setText(info.getCeo());
                txvCtoName.setText(info.getCto());
                txvCooName.setText(info.getCoo());
                txvCtoNamePropDep.setText(info.getCtoPropulsion());
                txvValuationAmt.setText(String.format("$%s", AppUtils.convertCommaSeparatedCost(String.valueOf(info.getValuation()))));
                txvEmployeesAmt.setText(String.valueOf(info.getEmployees()));
                txvVehiclesAmt.setText(String.valueOf(info.getVehicles()));
                txvDescription.setText(info.getSummary());
            }
        });

        return view;
    }

}
