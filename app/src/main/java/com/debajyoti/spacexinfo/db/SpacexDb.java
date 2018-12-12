package com.debajyoti.spacexinfo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.debajyoti.spacexinfo.api.model.Info;
import com.debajyoti.spacexinfo.api.model.LatestLaunch;
import com.debajyoti.spacexinfo.api.model.NextLaunch;
import com.debajyoti.spacexinfo.api.model.PastLaunch;
import com.debajyoti.spacexinfo.api.model.Rocket;
import com.debajyoti.spacexinfo.db.dao.InfoDao;
import com.debajyoti.spacexinfo.db.dao.LatestLaunchDao;
import com.debajyoti.spacexinfo.db.dao.NextLaunchDao;
import com.debajyoti.spacexinfo.db.dao.PastLaunchesDao;
import com.debajyoti.spacexinfo.db.dao.RocketsDao;

@Database(entities = {
        Info.class,
        Rocket.class,
        PastLaunch.class,
        LatestLaunch.class,
        NextLaunch.class}, version = 1, exportSchema = false)
public abstract class SpacexDb extends RoomDatabase {

    public abstract InfoDao infoDao();

    public abstract RocketsDao rocketsDao();

    public abstract PastLaunchesDao pastLaunchesDao();

    public abstract LatestLaunchDao latestLaunchDao();

    public abstract NextLaunchDao nextLaunchDao();
}
