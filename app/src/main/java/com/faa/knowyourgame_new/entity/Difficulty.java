package com.faa.knowyourgame_new.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "Difficulty")
public class Difficulty {

    @PrimaryKey@NonNull
    int ID;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "multiplier")
    Double multiplier;
}
