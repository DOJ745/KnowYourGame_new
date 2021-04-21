package com.faa.knowyourgame_new.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "Theme")
public class Theme {

    @PrimaryKey(autoGenerate = true)
    int ID;

    @ColumnInfo(name = "Name")
    String name;
}
