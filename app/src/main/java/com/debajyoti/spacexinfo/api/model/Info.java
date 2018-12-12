package com.debajyoti.spacexinfo.api.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.debajyoti.spacexinfo.db.convertors.HeadquarterConvertor;
import com.debajyoti.spacexinfo.db.convertors.LinkConvertor;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.debajyoti.spacexinfo.utils.AppConstants.INFO_TABLE;

@Entity(tableName = INFO_TABLE)
public class Info {
    @PrimaryKey(autoGenerate = true)
    private Integer _id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("founder")
    @Expose
    private String founder;
    @SerializedName("founded")
    @Expose
    private int founded;
    @SerializedName("employees")
    @Expose
    private int employees;
    @SerializedName("vehicles")
    @Expose
    private int vehicles;
    @SerializedName("launch_sites")
    @Expose
    private int launchSites;
    @SerializedName("test_sites")
    @Expose
    private int testSites;
    @SerializedName("ceo")
    @Expose
    private String ceo;
    @SerializedName("cto")
    @Expose
    private String cto;
    @SerializedName("coo")
    @Expose
    private String coo;
    @SerializedName("cto_propulsion")
    @Expose
    private String ctoPropulsion;
    @SerializedName("valuation")
    @Expose
    private long valuation;
    @SerializedName("summary")
    @Expose
    private String summary;
    @TypeConverters(HeadquarterConvertor.class)
    @SerializedName("headquarters")
    @Expose
    private Headquarters headquarters;
    @TypeConverters(LinkConvertor.class)
    @SerializedName("links")
    @Expose
    private Links links;

    public Info(Integer _id,
                String name,
                String founder,
                int founded,
                int employees,
                int vehicles,
                int launchSites,
                int testSites,
                String ceo,
                String cto,
                String coo,
                String ctoPropulsion,
                long valuation,
                String summary,
                Headquarters headquarters,
                Links links) {
        this._id = _id;
        this.name = name;
        this.founder = founder;
        this.founded = founded;
        this.employees = employees;
        this.vehicles = vehicles;
        this.launchSites = launchSites;
        this.testSites = testSites;
        this.ceo = ceo;
        this.cto = cto;
        this.coo = coo;
        this.ctoPropulsion = ctoPropulsion;
        this.valuation = valuation;
        this.summary = summary;
        this.headquarters = headquarters;
        this.links = links;
    }

    public Integer get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getFounder() {
        return founder;
    }

    public int getFounded() {
        return founded;
    }

    public int getEmployees() {
        return employees;
    }

    public int getVehicles() {
        return vehicles;
    }

    public int getLaunchSites() {
        return launchSites;
    }

    public int getTestSites() {
        return testSites;
    }

    public String getCeo() {
        return ceo;
    }

    public String getCto() {
        return cto;
    }

    public String getCoo() {
        return coo;
    }

    public String getCtoPropulsion() {
        return ctoPropulsion;
    }

    public long getValuation() {
        return valuation;
    }

    public String getSummary() {
        return summary;
    }

    public Headquarters getHeadquarters() {
        return headquarters;
    }

    public Links getLinks() {
        return links;
    }
}
