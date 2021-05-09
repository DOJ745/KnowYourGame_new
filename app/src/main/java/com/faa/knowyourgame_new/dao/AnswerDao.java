package com.faa.knowyourgame_new.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.faa.knowyourgame_new.entity.Answer;

@Dao
public interface AnswerDao {
    @Query("SELECT * FROM answers WHERE id = :id")
    Answer getById(long id);

    @Insert
    void insert(Answer answer);

    @Update
    void update(Answer answer);
}
