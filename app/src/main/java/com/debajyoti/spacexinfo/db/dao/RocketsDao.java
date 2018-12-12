package com.debajyoti.spacexinfo.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.debajyoti.spacexinfo.api.model.PastLaunch;
import com.debajyoti.spacexinfo.api.model.Rocket;

import java.util.List;

@Dao
public interface RocketsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRocketList(List<Rocket> rocketList);

    @Query("SELECT * FROM rockets")
    LiveData<List<Rocket>> getRocketFromDb();

    @Query("SELECT * FROM rockets WHERE id =:rocketId")
    LiveData<Rocket> getRocketById(int rocketId);

    @Query("SELECT id FROM rockets WHERE rocketId =:rocketId")
    LiveData<Integer> getRocketId(String rocketId);
}
