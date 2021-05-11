package com.faa.knowyourgame_new.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "Theme")
public class Theme {

    @PrimaryKey@NonNull@ColumnInfo(name = "_id")
    int ID;

    @ColumnInfo(name = "name")
    String name;
}
