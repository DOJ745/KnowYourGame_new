package com.faa.knowyourgame_new.dao;

import androidx.room.Query;

import com.faa.knowyourgame_new.entity.League;

public interface LeagueDao {
    @Query("SELECT * FROM league WHERE name = :name")
    League getByName(String name);
}
