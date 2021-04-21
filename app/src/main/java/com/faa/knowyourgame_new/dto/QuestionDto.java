package com.faa.knowyourgame_new.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class QuestionDto implements Serializable {
    int ID;
    int difficulty_id;
    int theme_id;
    String text;
    String image;
    int cost;
}
