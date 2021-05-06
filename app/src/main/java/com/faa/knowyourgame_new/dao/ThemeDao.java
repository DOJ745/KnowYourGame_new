package com.faa.knowyourgame_new.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.faa.knowyourgame_new.entity.Theme;

@Dao
public interface ThemeDao {
    @Query("SELECT * FROM theme WHERE id = :id")
    Theme getById(long id);

    @Insert
    void insert(Theme theme);

    @Update
    void update(Theme theme);
}
