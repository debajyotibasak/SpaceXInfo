package com.debajyoti.spacexinfo.view.widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.debajyoti.spacexinfo.R;
import com.debajyoti.spacexinfo.api.model.PastLaunch;
import com.debajyoti.spacexinfo.repo.PreferencesRepo;
import com.debajyoti.spacexinfo.utils.AppUtils;

import java.util.List;

public class SpacexFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private List<PastLaunch> pastLaunches;
    private PreferencesRepo preferencesRepo;

    SpacexFactory(Context mContext, PreferencesRepo preferencesRepo) {
        this.mContext = mContext;
        this.preferencesRepo = preferencesRepo;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        if (pastLaunches != null) {
            return;
        }
        pastLaunches = preferencesRepo.getLaunchesWidget();
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return (pastLaunches == null ? 0 : pastLaunches.size());
    }

    @Override
    public RemoteViews getViewAt(int position) {
        PastLaunch pastLaunch = pastLaunches.get(position);
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_launch_row);
        rv.setTextViewText(R.id.txv_mission, pastLaunch.getMissionName());
        rv.setTextViewText(R.id.txv_date, AppUtils.convertDateFromUTCtoIST(pastLaunch.getLaunchDateUtc()));
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
