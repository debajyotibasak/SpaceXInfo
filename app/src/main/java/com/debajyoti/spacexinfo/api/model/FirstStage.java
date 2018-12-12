package com.debajyoti.spacexinfo.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FirstStage {
    @SerializedName("reusable")
    @Expose
    private boolean reusable;
    @SerializedName("engines")
    @Expose
    private int engines;
    @SerializedName("fuel_amount_tons")
    @Expose
    private double fuelAmountTons;
    @SerializedName("burn_time_sec")
    @Expose
    private int burnTimeSec;

    public boolean isReusable() {
        return reusable;
    }

    public void setReusable(boolean reusable) {
        this.reusable = reusable;
    }

    public int getEngines() {
        return engines;
    }

    public void setEngines(int engines) {
        this.engines = engines;
    }

    public double getFuelAmountTons() {
        return fuelAmountTons;
    }

    public void setFuelAmountTons(int fuelAmountTons) {
        this.fuelAmountTons = fuelAmountTons;
    }

    public int getBurnTimeSec() {
        return burnTimeSec;
    }

    public void setBurnTimeSec(int burnTimeSec) {
        this.burnTimeSec = burnTimeSec;
    }
}
