package com.krishnamurthi.saratogahightrophyapplication.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.krishnamurthi.saratogahightrophyapplication.database.daos.SportDao;
import com.krishnamurthi.saratogahightrophyapplication.database.daos.TrophyDao;
import com.krishnamurthi.saratogahightrophyapplication.database.entities.Sport;
import com.krishnamurthi.saratogahightrophyapplication.database.entities.Trophy;

import java.util.concurrent.Executors;

@Database(entities = {Sport.class, Trophy.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    abstract SportDao sportDao();
    abstract TrophyDao trophyDao();

    private static AppDatabase mInstance;

    public synchronized static AppDatabase getInstance(Context context) {
        if(mInstance == null) mInstance = buildDatabase(context);
        return mInstance;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "App-Database")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(() -> {
                            getInstance(context).sportDao().insertAll(DatabaseManager.getSportData());
                            getInstance(context).trophyDao().insertAll(DatabaseManager.getTrophyData());
                        });
                    }
                }).build();
    }
}
