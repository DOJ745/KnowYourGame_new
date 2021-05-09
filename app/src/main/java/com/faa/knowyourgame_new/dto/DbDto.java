package com.faa.knowyourgame_new.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class DbDto implements Serializable {
    List<AnswersDto> answers;
    List<LeagueDto> leagues;
    List<ThemeDto> themes;
    List<DifficultyDto> difficulties;
    List<QuestionDto> questions;
}
