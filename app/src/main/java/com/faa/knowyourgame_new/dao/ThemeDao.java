package com.faa.knowyourgame_new.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.faa.knowyourgame_new.entity.Theme;

@Dao
public interface ThemeDao {
    @Query("SELECT * FROM theme WHERE id = :id")
    Theme getById(long id);
}
