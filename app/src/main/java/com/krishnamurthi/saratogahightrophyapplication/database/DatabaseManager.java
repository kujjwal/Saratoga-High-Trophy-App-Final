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

    static Sport[] getSportData() {
        List<Sport> sports = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(Environment.getExternalStorageDirectory().toString()
                    + titles[0] + ".csv"));
            boolean first = true;
            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());
                if (first) first = false;
                else {
                    sports.add(new Sport(line.get(0), line.get(1)));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return sports.toArray(new Sport[0]);
    }

    static Trophy[] getTrophyData() {
        List<Trophy> trophies = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(Environment.getExternalStorageDirectory().toString()
                    + titles[0] + ".csv"));
            boolean first = true;
            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());
                if (first) first = false;
                else {
                    String[] players = line.get(4).split(",");
                    for(String player : players) {
                        trophies.add(new Trophy(line.get(0), Integer.parseInt(line.get(1)),
                                line.get(2), line.get(3), player, line.get(5)));
                    }
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return trophies.toArray(new Trophy[0]);
    }
}
