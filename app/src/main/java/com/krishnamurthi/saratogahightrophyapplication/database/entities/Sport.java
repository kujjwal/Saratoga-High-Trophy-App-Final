package com.krishnamurthi.saratogahightrophyapplication.database.entities;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Fts4
@Entity(tableName = "sport") public class Sport {
    @Ignore @PrimaryKey @ColumnInfo(name = "rowid") public int id;
    @ColumnInfo(name = "Sports") public String sport_name;
    @ColumnInfo(name = "Image_URL") public String image_url;

    public Sport(String sport_name, String image_url) {
        this.sport_name = sport_name;
        this.image_url = image_url;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        return super.equals(obj);
    }
}
