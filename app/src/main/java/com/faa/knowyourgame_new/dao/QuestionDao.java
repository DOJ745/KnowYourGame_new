package com.faa.knowyourgame_new.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.faa.knowyourgame_new.entity.Question;

@Dao
public interface QuestionDao {
    @Query("SELECT * FROM question WHERE id = :id")
    Question getById(long id);

    @Insert
    void insert(Question question);

    @Update
    void update(Question question);
}
