package com.faa.knowyourgame_new.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.faa.knowyourgame_new.entity.Answer;
import com.faa.knowyourgame_new.entity.Difficulty;

import java.util.ArrayList;

@Dao
public interface DifficultyDao {
    @Query("SELECT * FROM difficulty WHERE id = :id")
    Difficulty getById(long id);

    @Insert
    void insert(Difficulty difficulty);

    @Insert
    void insertMany(ArrayList<Difficulty> difficulties);

    @Update
    void update(Difficulty difficulty);

    @Update
    void updateMany(ArrayList<Difficulty> difficulties);

    @Delete
    void deleteMany(ArrayList<Difficulty> difficulties);
}
