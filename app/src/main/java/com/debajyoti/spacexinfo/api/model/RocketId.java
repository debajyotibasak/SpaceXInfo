package com.debajyoti.spacexinfo.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RocketId {
    @SerializedName("rocket_id")
    @Expose
    private String rocketId;
    @SerializedName("rocket_name")
    @Expose
    private String rocketName;

    public RocketId(String rocketId, String rocketName) {
        this.rocketId = rocketId;
        this.rocketName = rocketName;
    }

    public String getRocketId() {
        return rocketId;
    }

    public String getRocketName() {
        return rocketName;
    }
}
