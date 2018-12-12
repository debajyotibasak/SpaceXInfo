package com.debajyoti.spacexinfo.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaunchSite {
    @SerializedName("site_id")
    @Expose
    private String siteId;
    @SerializedName("site_name")
    @Expose
    private String siteName;
    @SerializedName("site_name_long")
    @Expose
    private String siteNameLong;

    public LaunchSite(String siteId, String siteName, String siteNameLong) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.siteNameLong = siteNameLong;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getSiteNameLong() {
        return siteNameLong;
    }
}
