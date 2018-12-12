package com.debajyoti.spacexinfo.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaunchLinks {
    @SerializedName("mission_patch_small")
    @Expose
    private String missionPatch;
    @SerializedName("article_link")
    @Expose
    private String articleLink;
    @SerializedName("wikipedia")
    @Expose
    private String wiki;
    @SerializedName("video_link")
    @Expose
    private String videoLink;
    @SerializedName("flickr_images")
    @Expose
    private String[] flickrImages;

    public LaunchLinks(String missionPatch, String articleLink, String wiki, String videoLink, String[] flickrImages) {
        this.missionPatch = missionPatch;
        this.articleLink = articleLink;
        this.wiki = wiki;
        this.videoLink = videoLink;
        this.flickrImages = flickrImages;
    }

    public String getMissionPatch() {
        return missionPatch;
    }

    public String getArticleLink() {
        return articleLink;
    }

    public String getWiki() {
        return wiki;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public String[] getFlickrImages() {
        return flickrImages;
    }
}
