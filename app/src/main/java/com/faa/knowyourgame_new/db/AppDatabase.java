package com.faa.knowyourgame_new.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.faa.knowyourgame_new.dao.ThemeDao;
import com.faa.knowyourgame_new.dao.UserDao;
import com.faa.knowyourgame_new.entity.Answer;
import com.faa.knowyourgame_new.entity.Difficulty;
import com.faa.knowyourgame_new.entity.League;
import com.faa.knowyourgame_new.entity.Logs;
import com.faa.knowyourgame_new.entity.Question;
import com.faa.knowyourgame_new.entity.Theme;
import com.faa.knowyourgame_new.entity.User;

@Database(entities = {
        User.class,
        Theme.class,
        Question.class,
        Difficulty.class,
        Answer.class,
        Logs.class,
        League.class,
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract ThemeDao themeDao();
}
