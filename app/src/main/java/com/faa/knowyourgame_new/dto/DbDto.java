package com.faa.knowyourgame_new.dto;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Data;

@Data
public class DbDto implements Serializable {
    ArrayList<AnswersDto> answers;
    ArrayList<LeagueDto> leagues;
    ArrayList<ThemeDto> themes;
    ArrayList<DifficultyDto> difficulties;
    ArrayList<QuestionDto> questions;
}
