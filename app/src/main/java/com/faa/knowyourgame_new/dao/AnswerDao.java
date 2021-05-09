package com.faa.knowyourgame_new.dao;

import androidx.room.Query;

import com.faa.knowyourgame_new.entity.Answer;

public interface AnswerDao {
    @Query("SELECT * FROM answers WHERE id = :id")
    Answer getById(long id);
}
