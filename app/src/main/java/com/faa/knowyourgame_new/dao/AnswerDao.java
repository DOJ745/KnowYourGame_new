package com.faa.knowyourgame_new.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.faa.knowyourgame_new.entity.Answer;

import java.util.ArrayList;


@Dao
public interface AnswerDao {
    @Query("SELECT * FROM Answers WHERE ID = :id")
    Answer getById(long id);

    @Insert
    void insert(Answer answer);

    @Insert
    void insertMany(ArrayList<Answer> answers);

    @Update
    void update(Answer answer);

    @Update
    void updateMany(ArrayList<Answer> answers);

    @Delete
    void deleteMany(ArrayList<Answer> answers);
}
