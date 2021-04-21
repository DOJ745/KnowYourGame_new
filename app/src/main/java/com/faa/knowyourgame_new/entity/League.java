package com.faa.knowyourgame_new.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "League")
public class League {

    @ColumnInfo(name = "Name")
    String name;

    @ColumnInfo(name = "Image")
    String image; // URL

    @ColumnInfo(name = "Rating")
    int rating;
}
