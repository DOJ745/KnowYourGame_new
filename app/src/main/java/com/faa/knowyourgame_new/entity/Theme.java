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

    @PrimaryKey@NonNull
    int _id;

    @ColumnInfo(name = "name")
    String name;
}
