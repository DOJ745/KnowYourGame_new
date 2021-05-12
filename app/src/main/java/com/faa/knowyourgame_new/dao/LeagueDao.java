package com.faa.knowyourgame_new.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.faa.knowyourgame_new.entity.League;

import java.util.List;

@Dao
public interface LeagueDao {
    @Query("SELECT * FROM League WHERE name = :name")
    League getByName(String name);

    @Insert
    void insert(League league);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMany(List<League> leagues);

    @Update
    void update(League league);

    @Update
    void updateMany(List<League> leagues);

    @Delete
    void deleteMany(List<League> leagues);
}
