package com.faa.knowyourgame_new.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AnswersDto implements Serializable {
    int _id;
    int question_id;
    String text;
    int trueness;
}
