package com.krishnamurthi.saratogahightrophyapplication;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.krishnamurthi.saratogahightrophyapplication.database.AppDatabase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.ref.WeakReference;

import static com.krishnamurthi.saratogahightrophyapplication.Constants.*;

class DatabaseManager {
    private static WeakReference<Context> contextWeakReference;
    static final String pre_1 = "INSERT INTO " + tableName_1 + " (" + columns_1 + ") values(";
    static final String pre_2 = ");";

    static void createDB(Context context) throws FileNotFoundException {
        contextWeakReference = new WeakReference<>(context);
        for(int i = 0 ; i < GIDS.length; i++) { //Should only be two
            FileReader fr = new FileReader(Environment.getExternalStorageDirectory().toString() + titles[i] + ".csv");
            BufferedReader br = new BufferedReader(fr);
        }
    }

    static AppDatabase dbCreator() {
        return Room.databaseBuilder(contextWeakReference.get(), AppDatabase.class, "App-Database").build();
    }
}
