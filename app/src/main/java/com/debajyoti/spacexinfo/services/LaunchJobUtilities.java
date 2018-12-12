package com.debajyoti.spacexinfo.services;

import android.content.Context;
import android.util.Log;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

public class LaunchJobUtilities {

    private static final String JOB_TAG = "SpaceX-Job";
    private static final int REMINDER_INTERVAL_MINUTES = 6;
    private static final int REMINDER_INTERVAL_SECONDS = (int) TimeUnit.HOURS.toSeconds(REMINDER_INTERVAL_MINUTES);
    private static final int SYNC_FLEXTIME_SECONDS = REMINDER_INTERVAL_SECONDS;

    private static boolean sInitialized;

    synchronized public static void scheduleTemplateFetch(Context context) {
        if (sInitialized) return;
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        Job job = dispatcher.newJobBuilder()
                .setService(FetchLaunchesJob.class)
                .setTag(JOB_TAG)
                .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(REMINDER_INTERVAL_SECONDS, REMINDER_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
                .setReplaceCurrent(true)
                .build();
        dispatcher.mustSchedule(job);
        sInitialized = true;
        Log.d("Firebase Job Dispatcher", "executed");
    }
}
