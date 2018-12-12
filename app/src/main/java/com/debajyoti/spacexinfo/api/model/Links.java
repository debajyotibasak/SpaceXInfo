package com.debajyoti.spacexinfo.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("flickr")
    @Expose
    private String flickr;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("elon_twitter")
    @Expose
    private String elonTwitter;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFlickr() {
        return flickr;
    }

    public void setFlickr(String flickr) {
        this.flickr = flickr;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getElonTwitter() {
        return elonTwitter;
    }

    public void setElonTwitter(String elonTwitter) {
        this.elonTwitter = elonTwitter;
    }
}
