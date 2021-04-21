package com.faa.knowyourgame_new.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class LogsDto implements Serializable {
    String login;
    int answer_status;
    double points;
    Date date;
}
