package com.debajyoti.spacexinfo.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mass {
    @SerializedName("kg")
    @Expose
    private int kg;
    @SerializedName("lb")
    @Expose
    private int lb;

    public int getKg() {
        return kg;
    }

    public void setKg(int kg) {
        this.kg = kg;
    }

    public int getLb() {
        return lb;
    }

    public void setLb(int lb) {
        this.lb = lb;
    }
}
