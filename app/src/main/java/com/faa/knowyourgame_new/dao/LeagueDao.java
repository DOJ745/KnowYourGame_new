package com.faa.knowyourgame_new.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.faa.knowyourgame_new.entity.Answer;
import com.faa.knowyourgame_new.entity.League;

import java.util.ArrayList;

@Dao
public interface LeagueDao {
    @Query("SELECT * FROM League WHERE name = :name")
    League getByName(String name);

    @Insert
    void insert(League league);

    @Insert
    void insertMany(ArrayList<League> leagues);

    @Update
    void update(League league);

    @Update
    void updateMany(ArrayList<League> leagues);

    @Delete
    void deleteMany(ArrayList<League> leagues);
}
