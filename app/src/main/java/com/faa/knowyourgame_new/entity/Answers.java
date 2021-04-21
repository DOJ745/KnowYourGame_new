package com.faa.knowyourgame_new.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "Answers")
public class Answers {

    @PrimaryKey(autoGenerate = true)
    int ID;

    @ForeignKey(entity = Question.class, parentColumns = "ID", childColumns = "Question_ID")
    int question_id;

    @ColumnInfo(name = "Text")
    String text;

    @ColumnInfo(name = "Trueness")
    int trueness;
}
