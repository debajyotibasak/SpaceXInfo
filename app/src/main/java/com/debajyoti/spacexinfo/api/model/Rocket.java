package com.debajyoti.spacexinfo.api.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.debajyoti.spacexinfo.db.convertors.DiameterConvertor;
import com.debajyoti.spacexinfo.db.convertors.FirstStageConvertor;
import com.debajyoti.spacexinfo.db.convertors.FlickrImageListConvertor;
import com.debajyoti.spacexinfo.db.convertors.HeightConvertor;
import com.debajyoti.spacexinfo.db.convertors.MassConvertor;
import com.debajyoti.spacexinfo.db.convertors.PayloadWeightListConvertor;
import com.debajyoti.spacexinfo.db.convertors.SecondStageConvertor;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import static com.debajyoti.spacexinfo.utils.AppConstants.ROCKETS_TABLE;

@Entity(tableName = ROCKETS_TABLE)
public class Rocket {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("active")
    @Expose
    private boolean active;
    @SerializedName("stages")
    @Expose
    private int stages;
    @SerializedName("boosters")
    @Expose
    private int boosters;
    @SerializedName("cost_per_launch")
    @Expose
    private long costPerLaunch;
    @SerializedName("success_rate_pct")
    @Expose
    private int successRatePct;
    @SerializedName("first_flight")
    @Expose
    private String firstFlight;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("company")
    @Expose
    private String company;
    @TypeConverters(HeightConvertor.class)
    @SerializedName("height")
    @Expose
    private Height height;
    @TypeConverters(DiameterConvertor.class)
    @SerializedName("diameter")
    @Expose
    private Diameter diameter;
    @TypeConverters(MassConvertor.class)
    @SerializedName("mass")
    @Expose
    private Mass mass;
    @TypeConverters(PayloadWeightListConvertor.class)
    @SerializedName("payload_weights")
    @Expose
    private List<PayLoadWeight> payloadWeights = null;
    @TypeConverters(FirstStageConvertor.class)
    @SerializedName("first_stage")
    @Expose
    private FirstStage firstStage;
    @TypeConverters(SecondStageConvertor.class)
    @SerializedName("second_stage")
    @Expose
    private SecondStage secondStage;
    @TypeConverters(FlickrImageListConvertor.class)
    @SerializedName("flickr_images")
    @Expose
    private List<String> flickrImages = null;
    @SerializedName("wikipedia")
    @Expose
    private String wikipedia;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("rocket_id")
    @Expose
    private String rocketId;
    @SerializedName("rocket_name")
    @Expose
    private String rocketName;
    @SerializedName("rocket_type")
    @Expose
    private String rocketType;

    public Rocket(int id,
                  boolean active,
                  int stages,
                  int boosters,
                  long costPerLaunch,
                  int successRatePct,
                  String firstFlight,
                  String country,
                  String company,
                  Height height,
                  Diameter diameter,
                  Mass mass,
                  List<PayLoadWeight> payloadWeights,
                  FirstStage firstStage,
                  SecondStage secondStage,
                  List<String> flickrImages,
                  String wikipedia,
                  String description,
                  String rocketId,
                  String rocketName,
                  String rocketType) {
        this.id = id;
        this.active = active;
        this.stages = stages;
        this.boosters = boosters;
        this.costPerLaunch = costPerLaunch;
        this.successRatePct = successRatePct;
        this.firstFlight = firstFlight;
        this.country = country;
        this.company = company;
        this.height = height;
        this.diameter = diameter;
        this.mass = mass;
        this.payloadWeights = payloadWeights;
        this.firstStage = firstStage;
        this.secondStage = secondStage;
        this.flickrImages = flickrImages;
        this.wikipedia = wikipedia;
        this.description = description;
        this.rocketId = rocketId;
        this.rocketName = rocketName;
        this.rocketType = rocketType;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public int getStages() {
        return stages;
    }

    public int getBoosters() {
        return boosters;
    }

    public long getCostPerLaunch() {
        return costPerLaunch;
    }

    public int getSuccessRatePct() {
        return successRatePct;
    }

    public String getFirstFlight() {
        return firstFlight;
    }

    public String getCountry() {
        return country;
    }

    public String getCompany() {
        return company;
    }

    public Height getHeight() {
        return height;
    }

    public Diameter getDiameter() {
        return diameter;
    }

    public Mass getMass() {
        return mass;
    }

    public List<PayLoadWeight> getPayloadWeights() {
        return payloadWeights;
    }

    public FirstStage getFirstStage() {
        return firstStage;
    }

    public SecondStage getSecondStage() {
        return secondStage;
    }

    public List<String> getFlickrImages() {
        return flickrImages;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public String getDescription() {
        return description;
    }

    public String getRocketId() {
        return rocketId;
    }

    public String getRocketName() {
        return rocketName;
    }

    public String getRocketType() {
        return rocketType;
    }
}
