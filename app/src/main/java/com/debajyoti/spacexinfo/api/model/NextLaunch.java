package com.debajyoti.spacexinfo.api.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.debajyoti.spacexinfo.db.convertors.LaunchLinksConvertor;
import com.debajyoti.spacexinfo.db.convertors.LaunchSiteConvertor;
import com.debajyoti.spacexinfo.db.convertors.RocketIdConvertor;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.debajyoti.spacexinfo.utils.AppConstants.NEXT_LAUNCH_TABLE;

@Entity(tableName = NEXT_LAUNCH_TABLE)
public class NextLaunch {
    @PrimaryKey
    @SerializedName("flight_number")
    @Expose
    private int flightNumber;
    @SerializedName("mission_name")
    @Expose
    private String missionName;
    @SerializedName("launch_year")
    @Expose
    private String launchYear;
    @SerializedName("launch_date_unix")
    @Expose
    private long launchDateUnix;
    @SerializedName("launch_date_utc")
    @Expose
    private String launchDateUtc;
    @TypeConverters(RocketIdConvertor.class)
    @SerializedName("rocket")
    @Expose
    private RocketId rocketId;
    @TypeConverters(LaunchSiteConvertor.class)
    @SerializedName("launch_site")
    @Expose
    private LaunchSite launchSite;
    @SerializedName("launch_success")
    @Expose
    private boolean launchSuccess;
    @TypeConverters(LaunchLinksConvertor.class)
    @SerializedName("links")
    @Expose
    private LaunchLinks links;
    @SerializedName("details")
    @Expose
    private String details;

    public NextLaunch(int flightNumber,
                      String missionName,
                      String launchYear,
                      long launchDateUnix,
                      String launchDateUtc,
                      RocketId rocketId,
                      LaunchSite launchSite,
                      boolean launchSuccess,
                      LaunchLinks links,
                      String details) {
        this.flightNumber = flightNumber;
        this.missionName = missionName;
        this.launchYear = launchYear;
        this.launchDateUnix = launchDateUnix;
        this.launchDateUtc = launchDateUtc;
        this.rocketId = rocketId;
        this.launchSite = launchSite;
        this.launchSuccess = launchSuccess;
        this.links = links;
        this.details = details;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public String getMissionName() {
        return missionName;
    }

    public String getLaunchYear() {
        return launchYear;
    }

    public long getLaunchDateUnix() {
        return launchDateUnix;
    }

    public String getLaunchDateUtc() {
        return launchDateUtc;
    }

    public RocketId getRocketId() {
        return rocketId;
    }

    public LaunchSite getLaunchSite() {
        return launchSite;
    }

    public boolean isLaunchSuccess() {
        return launchSuccess;
    }

    public LaunchLinks getLinks() {
        return links;
    }

    public String getDetails() {
        return details;
    }
}
