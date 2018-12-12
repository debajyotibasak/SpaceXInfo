package com.debajyoti.spacexinfo.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.debajyoti.spacexinfo.api.model.NextLaunch;

@Dao
public interface NextLaunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNextLaunch(NextLaunch nextLaunch);

    @Query("SELECT * FROM nextLaunch")
    LiveData<NextLaunch> getNexttLaunchFromDb();

    @Query("DELETE FROM nextLaunch")
    void deleteNextLaunch();
}
