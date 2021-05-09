package com.faa.knowyourgame_new.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Data
@Entity(tableName = "User")
public class User {

    @PrimaryKey@NonNull
    String login;
    
    @ColumnInfo(name = "Password")
    String password; // create hash-func for password

    @ColumnInfo(name = "Score")
    Double score;
}
