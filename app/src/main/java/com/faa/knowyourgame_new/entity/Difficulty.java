package com.faa.knowyourgame_new.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "Difficulty")
public class Difficulty {

    @PrimaryKey(autoGenerate = true)
    int ID;

    @ColumnInfo(name = "Name")
    String name;

    @ColumnInfo(name = "Multiplier")
    Double multiplier;
}
