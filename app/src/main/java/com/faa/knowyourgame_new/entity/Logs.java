package com.faa.knowyourgame_new.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "Logs")
public class Logs {

    @ColumnInfo(name = "Login")
    String login;

    @ColumnInfo(name = "AnswerStatus")
    int answerStatus;

    @ColumnInfo(name = "Points")
    double points;

    @ColumnInfo(name = "DateTime")
    Date dateTime;
}
