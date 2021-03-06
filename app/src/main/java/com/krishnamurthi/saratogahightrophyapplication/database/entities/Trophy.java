package com.krishnamurthi.saratogahightrophyapplication.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Fts4
@Entity(tableName = "trophy") public class Trophy {
    @Ignore @PrimaryKey @ColumnInfo(name = "rowid") public int id;
    @ColumnInfo(name = "Sport") public String sport_name;
    @ColumnInfo(name = "Year") public int year;
    @ColumnInfo(name = "Trophy_Title") public String tr_title;
    @ColumnInfo(name = "Trophy_Image_URI") public String tr_image_url;
    @ColumnInfo(name = "Player_Name") public String player;
    @ColumnInfo(name = "Category") public String category;

    public Trophy(String sport_name, int year, String tr_title, String tr_image_url, String player, String category) {
        this.sport_name = sport_name;
        this.year = year;
        this.tr_title = tr_title;
        this.tr_image_url = tr_image_url;
        this.player = player;
        this.category = category;
    }
}
