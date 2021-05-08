package com.faa.knowyourgame_new.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class DbDto implements Serializable {
    List<AnswersDto> answersDtoList;
    List<LeagueDto> leagueDtoList;
    List<ThemeDto> themeDtoList;
    List<DifficultyDto> difficultyDtoList;
    List<QuestionDto> questionDtoList;
}
