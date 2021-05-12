package com.faa.knowyourgame_new.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static androidx.room.ForeignKey.CASCADE;

@EqualsAndHashCode
@Getter
@Setter
@Entity(tableName = "Answers")
public class Answer {

    @PrimaryKey@NonNull
    int _id;

    @ForeignKey(
            entity = Question.class,
            parentColumns = "_id",
            childColumns = "question_id",
            onDelete = CASCADE)
    int question_id;

    @ColumnInfo(name = "text")
    String text;

    @ColumnInfo(name = "trueness")
    int trueness;
}
