package com.krishnamurthi.saratogahightrophyapplication.database;

import android.os.Environment;

import com.krishnamurthi.saratogahightrophyapplication.database.entities.Sport;
import com.krishnamurthi.saratogahightrophyapplication.database.entities.Trophy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.krishnamurthi.saratogahightrophyapplication.utils.CSVUtils.*;
import static com.krishnamurthi.saratogahightrophyapplication.utils.Constants.*;

class DatabaseManager {
    //boolean is whether there are changes, or all are new

    static Sport[] getSportData(boolean mChanges) {
        List<Sport> sports = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(Environment.getExternalStorageDirectory().toString()
                    + titles[0] + ".csv"));
            Scanner scanner1 = new Scanner(new File(Environment.getExternalStorageDirectory().toString()
                    + "cache/" + titles[0] + ".csv"));
            boolean first = true;
            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());
                if (first) first = false; //Column Headers
                else {
                    if (mChanges && scanner1.hasNext()) {
                        List<String> line2 = parseLine(scanner1.nextLine());
                        if (!new Sport(line2.get(0), line2.get(1)).equals(new Sport(line.get(0), line.get(1)))) {
                            sports.add(new Sport(line.get(0), line.get(1)));
                        }
                        // Check if the sport data line is new or in old database
                    } else sports.add(new Sport(line.get(0), line.get(1)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sports.toArray(new Sport[0]);
    }

    static Trophy[] getTrophyData(boolean mChanges) {
        List<Trophy> trophies = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(Environment.getExternalStorageDirectory().toString()
                    + titles[1] + ".csv"));
            Scanner scanner1 = new Scanner(new File(Environment.getExternalStorageDirectory().toString()
                    + "cache/" + titles[1] + ".csv"));
            boolean first = true;
            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());
                List<String> line2 = parseLine(scanner1.nextLine());
                if (first) first = false;
                else {
                    String[] players = line.get(4).split(",");
                    String[] players1 = line2.get(4).split(",");

                    for (int i = 0; i < players.length; i++) {
                        String player = players[i];
                        String player2 = players1[i];
                        if (mChanges && scanner1.hasNext()) {
                            if (!new Trophy(line2.get(0), Integer.parseInt(line2.get(1)),
                                    line2.get(2), line2.get(3), player2, line2.get(5)).equals(
                                    new Trophy(line.get(0), Integer.parseInt(line.get(1)),
                                            line.get(2), line.get(3), player, line.get(5)))) {
                                trophies.add(new Trophy(line2.get(0), Integer.parseInt(line2.get(1)),
                                        line2.get(2), line2.get(3), player2, line2.get(5)));
                            }
                            // Check if the sport data line is new or in old database
                        } else trophies.add(new Trophy(line.get(0), Integer.parseInt(line.get(1)),
                                line.get(2), line.get(3), player, line.get(5)));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trophies.toArray(new Trophy[0]);
    }
}
