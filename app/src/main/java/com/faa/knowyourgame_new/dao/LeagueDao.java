package com.faa.knowyourgame_new.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.faa.knowyourgame_new.entity.League;

@Dao
public interface LeagueDao {
    @Query("SELECT * FROM league WHERE name = :name")
    League getByName(String name);

    @Insert
    void insert(League league);

    @Update
    void update(League league);
}
