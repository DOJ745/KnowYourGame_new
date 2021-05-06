package com.faa.knowyourgame_new.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.faa.knowyourgame_new.entity.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user WHERE login = :login")
    User getByLogin(String login);
}
