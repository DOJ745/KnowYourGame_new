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

    @ColumnInfo(name = "AnswerStatus")
    int answerStatus;

    @ColumnInfo(name = "Points")
    double points;

    @ColumnInfo(name = "DateTime")
    String dateTime;
}
