package com.faa.knowyourgame_new.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.faa.knowyourgame_new.dto.ThemeDto;
import com.faa.knowyourgame_new.entity.Answer;
import com.faa.knowyourgame_new.entity.Theme;

import java.util.ArrayList;

@Dao
public interface ThemeDao {
    @Query("SELECT * FROM theme WHERE id = :id")
    Theme getById(long id);

    @Insert
    void insert(Theme theme);

    @Insert
    void insertMany(ArrayList<Theme> themes);

    @Insert
    void insertManyDto(ArrayList<ThemeDto> themes);

    @Update
    void update(Theme theme);

    @Update
    void updateMany(ArrayList<Theme> themes);

    @Delete
    void deleteMany(ArrayList<Theme> themes);
}
