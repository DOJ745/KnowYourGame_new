package com.faa.knowyourgame_new.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "Logs")
public class Logs {

    @PrimaryKey@NonNull
    String login;

    @ColumnInfo(name = "answer_status")
    int answerStatus;

    @ColumnInfo(name = "points")
    double points;

    @ColumnInfo(name = "date_time")
    String dateTime;
}
