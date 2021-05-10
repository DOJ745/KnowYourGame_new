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

    @PrimaryKey@NonNull
    int ID;

    @ForeignKey(entity = Difficulty.class, parentColumns = "ID", childColumns = "Difficulty_ID")
    int difficulty_id;

    @ForeignKey(
            entity = Theme.class,
            parentColumns = "ID",
            childColumns = "Theme_ID",
            onDelete = CASCADE)
    int theme_id;

    @ColumnInfo(name = "Text")
    String text;

    @ColumnInfo(name = "Image")
    String image; // URL

    @ColumnInfo(name = "Cost")
    int cost;
}
