package com.faa.knowyourgame_new.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.faa.knowyourgame_new.entity.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user WHERE login = :login")
    User getByLogin(String login);

    @Insert
    void insert(User user);

    @Update
    void update(User user);
}