package com.debajyoti.spacexinfo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.debajyoti.spacexinfo.SpacexApp;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class AppUtils {

    public static boolean isNetworkAvailable() {
        ConnectivityManager cn = (ConnectivityManager) SpacexApp.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = Objects.requireNonNull(cn).getActiveNetworkInfo();
        return nf != null && nf.isConnected();
    }

    public static String convertDate(String date, String df1, String df2) {
        SimpleDateFormat df = new SimpleDateFormat(df1, Locale.US);
        Date newDate = null;
        try {
            newDate = df.parse(date);
            df = new SimpleDateFormat(df2, Locale.US);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df.format(newDate);
    }

    public static String convertDateFromUTCtoIST(String date) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.US);
        outputFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

        Date newDate = null;
        try {
            newDate = inputFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputFormat.format(newDate);
    }

    public static String convertDateFromUTCtoISTDetails(String date) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM dd", Locale.US);
        outputFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

        Date newDate = null;
        try {
            newDate = inputFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputFormat.format(newDate);
    }

    public static void setSnackBar(View view, String msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
        View view1 = snackbar.getView();
        TextView txv = view1.findViewById(android.support.design.R.id.snackbar_text);
        txv.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public static String convertCommaSeparatedCost(String value) {
        long valueInLong = Long.parseLong(value);
        DecimalFormat formatter = new DecimalFormat("###,###");
        return String.valueOf(formatter.format(valueInLong));
    }

    public static boolean shouldFetchData(long currentTime, long timeInPref) {
        return (currentTime - timeInPref) > TimeUnit.MINUTES.toMillis(AppConstants.FRESH_TIMEOUT_IN_MINUTES);
    }
}
