package com.faa.knowyourgame_new.dao;

import androidx.room.Query;

import com.faa.knowyourgame_new.entity.Difficulty;

public interface DifficultyDao {
    @Query("SELECT * FROM difficulty WHERE id = :id")
    Difficulty getById(long id);
}
