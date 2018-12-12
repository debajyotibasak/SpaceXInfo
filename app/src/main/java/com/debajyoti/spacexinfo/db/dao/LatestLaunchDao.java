package com.debajyoti.spacexinfo.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.debajyoti.spacexinfo.api.model.LatestLaunch;

@Dao
public interface LatestLaunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLatestLaunch(LatestLaunch latestLaunch);

    @Query("SELECT * FROM latestLaunch")
    LiveData<LatestLaunch> getLatestLaunchFromDb();

    @Query("DELETE FROM latestLaunch")
    void deleteLatestLaunch();
}
