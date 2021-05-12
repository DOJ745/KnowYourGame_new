package com.faa.knowyourgame_new.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.faa.knowyourgame_new.entity.Answer;
import com.faa.knowyourgame_new.entity.Difficulty;
import com.faa.knowyourgame_new.entity.Theme;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DifficultyDao {
    @Query("SELECT * FROM Difficulty WHERE _id = :id")
    Difficulty getById(long id);

    @Query("SELECT * FROM Difficulty")
    List<Difficulty> getAll();

    @Insert
    void insert(Difficulty difficulty);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMany(List<Difficulty> difficulties);

    @Update
    void update(Difficulty difficulty);

    @Update
    void updateMany(List<Difficulty> difficulties);

    @Delete
    void deleteMany(List<Difficulty> difficulties);
}
