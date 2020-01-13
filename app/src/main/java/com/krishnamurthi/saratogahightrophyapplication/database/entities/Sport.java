package com.krishnamurthi.saratogahightrophyapplication.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

@Fts4
@Entity(tableName = "sport") public class Sport {
    @PrimaryKey @ColumnInfo(name = "rowid") public int id;
    @ColumnInfo(name = "Sports") public String sport_name;
    @ColumnInfo(name = "Image_URL") public String image_url;
}