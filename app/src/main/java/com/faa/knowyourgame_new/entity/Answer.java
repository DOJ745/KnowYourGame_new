package com.faa.knowyourgame_new.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

import static androidx.room.ForeignKey.CASCADE;

@Getter
@Setter
@Entity(tableName = "Answers")
public class Answer {

    @PrimaryKey@NonNull
    int ID;

    @ForeignKey(
            entity = Question.class,
            parentColumns = "ID",
            childColumns = "question_ID",
            onDelete = CASCADE)
    int question_id;

    @ColumnInfo(name = "text")
    String text;

    @ColumnInfo(name = "trueness")
    int trueness;
}
