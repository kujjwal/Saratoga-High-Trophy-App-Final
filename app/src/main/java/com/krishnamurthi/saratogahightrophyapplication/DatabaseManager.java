package com.krishnamurthi.saratogahightrophyapplication;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static com.krishnamurthi.saratogahightrophyapplication.Constants.*;

class DatabaseManager {

    static final String pre_1 = "INSERT INTO " + tableName_1 + " (" + columns_1 + ") values(";
    static final String pre_2 = ");";

    static void createDB() throws FileNotFoundException {
        for(int i = 0 ; i < GIDS.length; i++) { //Should only be two
            FileReader fr = new FileReader(Environment.getExternalStorageDirectory().toString() + titles[i] + ".csv");
            BufferedReader br = new BufferedReader(fr);

        }
    }
}
