package com.faa.knowyourgame_new.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@Entity(tableName = "User")
public class User {

    @PrimaryKey@NonNull
    String login;
    
    @ColumnInfo(name = "password")
    String password; // create hash-func for password

    @ColumnInfo(name = "score")
    Double score;
}
