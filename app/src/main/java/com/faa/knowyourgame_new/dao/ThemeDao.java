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
import java.util.List;

@Dao
public interface ThemeDao {
    @Query("SELECT * FROM Theme WHERE ID = :id")
    Theme getById(long id);

    /*@Query("SELECT * FROM Theme")
    ArrayList<Theme> getAll();*/

    @Query("SELECT * FROM Theme")
    List<ThemeDto> getAllDto();

    @Insert
    void insert(Theme theme);

    @Insert
    void insertMany(ArrayList<Theme> themes);

    @Insert
    void insertManyDto(List<ThemeDto> themes);

    @Update
    void update(Theme theme);

    @Update
    void updateMany(ArrayList<Theme> themes);

    @Delete
    void deleteMany(ArrayList<Theme> themes);
}
