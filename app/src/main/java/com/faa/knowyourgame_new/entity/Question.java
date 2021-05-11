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
@Entity(tableName = "Question")
public class Question {

    @PrimaryKey@NonNull@ColumnInfo(name = "_id")
    int ID;

    @ForeignKey(entity = Difficulty.class, parentColumns = "ID", childColumns = "difficulty_ID")
    int difficulty_id;

    @ForeignKey(
            entity = Theme.class,
            parentColumns = "ID",
            childColumns = "theme_ID",
            onDelete = CASCADE)
    int theme_id;

    @ColumnInfo(name = "text")
    String text;

    @ColumnInfo(name = "image")
    String image; // URL

    @ColumnInfo(name = "cost")
    int cost;
}
