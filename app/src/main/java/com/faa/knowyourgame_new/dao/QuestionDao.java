package com.faa.knowyourgame_new.dao;

import androidx.room.Query;

import com.faa.knowyourgame_new.entity.Question;

public interface QuestionDao {
    @Query("SELECT * FROM question WHERE id = :id")
    Question getById(long id);
}
