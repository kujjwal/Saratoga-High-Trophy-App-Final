package com.krishnamurthi.saratogahightrophyapplication.database.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.krishnamurthi.saratogahightrophyapplication.database.entities.Trophy;

import java.util.List;

@Dao public interface TrophyDao {
    @Query("SELECT * FROM trophy") List<Trophy> getAll();
    @Query("SELECT * FROM trophy WHERE rowid IN (:userIds)") List<Trophy> loadAllByIds(int[] userIds);
    @Insert void insertAll(Trophy... trophies);
    @Delete void delete(Trophy trophy);

    // User-defined Queries
    @Query("SELECT * FROM trophy WHERE Trophy_Title LIKE :tr_title")
        Trophy findByTrophyTitle(String tr_title);
    @Query("SELECT * FROM trophy WHERE Player_Name LIKE :player") //TODO: Normalize DB Structure (IF NECESSARY)
        Trophy findByName(String player);
    @Query("SELECT * FROM trophy WHERE Year LIKE :year")
        Trophy findByYear(int year);
    @Query("SELECT * FROM trophy WHERE Sport LIKE :sport_name")
        Trophy findBySport(String sport_name);
    @Query("SELECT * FROM trophy WHERE Category LIKE :category")
        Trophy findByCategory(String category);
}
