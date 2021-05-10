package com.faa.knowyourgame_new.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.faa.knowyourgame_new.entity.Answer;
import com.faa.knowyourgame_new.entity.Question;

import java.util.ArrayList;

@Dao
public interface QuestionDao {
    @Query("SELECT * FROM question WHERE id = :id")
    Question getById(long id);

    @Insert
    void insert(Question question);

    @Insert
    void insertMany(ArrayList<Question> questions);

    @Update
    void update(Question question);

    @Update
    void updateMany(ArrayList<Question> questions);

    @Delete
    void deleteMany(ArrayList<Question> questions);
}
