package com.debajyoti.spacexinfo.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.debajyoti.spacexinfo.api.model.PastLaunch;

import java.util.List;

@Dao
public interface PastLaunchesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPastLaunches(List<PastLaunch> pastLaunches);

    @Query("SELECT * FROM pastLaunches ORDER BY flightNumber DESC")
    LiveData<List<PastLaunch>> getPastLaunchesFromDb();

    @Query("SELECT * FROM pastLaunches WHERE flightNumber =:missionId")
    LiveData<PastLaunch> getPastLaunchById(int missionId);

    @Query("SELECT * FROM pastLaunches ORDER BY flightNumber DESC LIMIT 5")
    LiveData<List<PastLaunch>> getPastfiveLaunchesFromDb();
}
