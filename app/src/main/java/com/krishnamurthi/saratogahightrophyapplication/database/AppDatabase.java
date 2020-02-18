package com.krishnamurthi.saratogahightrophyapplication.database;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.krishnamurthi.saratogahightrophyapplication.database.DAOs.SportDao;
import com.krishnamurthi.saratogahightrophyapplication.database.DAOs.TrophyDao;
import com.krishnamurthi.saratogahightrophyapplication.database.entities.Sport;
import com.krishnamurthi.saratogahightrophyapplication.database.entities.Trophy;

import java.util.concurrent.Executors;

@Database(entities = {Sport.class, Trophy.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    abstract SportDao sportDao();
    abstract TrophyDao trophyDao();

    private static AppDatabase mInstance;

    public synchronized static AppDatabase getInstance(Context context, Status status) {
        if(mInstance == null) mInstance = buildDatabaseStatus(context, status);
        return mInstance;
    }

    private static AppDatabase buildDatabaseStatus(final Context context, Status status) {
        AppDatabase db = null;
        switch (status) {
            case NO_CHANGES:
                db = Room.databaseBuilder(context, AppDatabase.class, "App-Database")
                        .createFromAsset(Environment.getExternalStorageDirectory().toString() + "app.db")
                        .build();
                break;
            case CHANGES:
                db = Room.databaseBuilder(context, AppDatabase.class, "App-Database")
                        .createFromAsset(Environment.getExternalStorageDirectory().toString() + "app.db")
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Executors.newSingleThreadScheduledExecutor().execute(() -> {
                                    //TODO: Change STATUS Enum so it's specific to which table has changes
                                    getInstance(context, status).sportDao().insertAll(DatabaseManager.getSportData(true));
                                    getInstance(context, status).trophyDao().insertAll(DatabaseManager.getTrophyData(true));
                                });
                            }
                        }).build();
                break;
            case NEW:
                db = Room.databaseBuilder(context, AppDatabase.class, "App-Database")
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Executors.newSingleThreadScheduledExecutor().execute(() -> {
                                    getInstance(context, status).sportDao().insertAll(DatabaseManager.getSportData(false));
                                    getInstance(context, status).trophyDao().insertAll(DatabaseManager.getTrophyData(false));
                                });
                            }
                        }).build();
                break;
        }
        return db;
    }
}