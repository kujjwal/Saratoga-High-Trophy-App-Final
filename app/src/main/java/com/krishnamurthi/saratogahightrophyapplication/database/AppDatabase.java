package com.krishnamurthi.saratogahightrophyapplication.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.krishnamurthi.saratogahightrophyapplication.database.daos.SportDao;
import com.krishnamurthi.saratogahightrophyapplication.database.daos.TrophyDao;
import com.krishnamurthi.saratogahightrophyapplication.database.entities.Sport;
import com.krishnamurthi.saratogahightrophyapplication.database.entities.Trophy;

@Database(entities = {Sport.class, Trophy.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SportDao sportDao();
    public abstract TrophyDao trophyDao();
}
