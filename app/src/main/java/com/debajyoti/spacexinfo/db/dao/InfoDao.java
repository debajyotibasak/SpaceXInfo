package com.debajyoti.spacexinfo.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.debajyoti.spacexinfo.api.model.Info;

@Dao
public interface InfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInfo(Info info);

    @Query("SELECT * FROM infoTable")
    LiveData<Info> getInfoFromDb();

    @Query("DELETE FROM infoTable")
    void deleteInfo();
}
