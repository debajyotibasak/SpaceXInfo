package com.debajyoti.spacexinfo.view.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViewsService;

import com.debajyoti.spacexinfo.repo.PreferencesRepo;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SpacexService extends RemoteViewsService {

    @Inject
    PreferencesRepo preferencesRepo;

    public static Intent createIntent(Context context, int appWidgetId) {
        Intent intent = new Intent(context, SpacexService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        return intent;
    }

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new SpacexFactory(this.getApplicationContext(), preferencesRepo);
    }
}
