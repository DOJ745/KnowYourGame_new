package com.faa.knowyourgame_new.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.faa.knowyourgame_new.entity.Difficulty;

@Dao
public interface DifficultyDao {
    @Query("SELECT * FROM difficulty WHERE id = :id")
    Difficulty getById(long id);

    @Insert
    void insert(Difficulty difficulty);

    @Update
    void update(Difficulty difficulty);
}
