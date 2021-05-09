package com.faa.knowyourgame_new.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "League")
public class League {

    @PrimaryKey@NonNull
    String name;

    @ColumnInfo(name = "Image")
    String image; // URL

    @ColumnInfo(name = "Rating")
    int rating;
}
