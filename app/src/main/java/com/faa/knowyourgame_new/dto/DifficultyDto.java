package com.faa.knowyourgame_new.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DifficultyDto implements Serializable {
    int ID;
    String name;
    double multiplier;
}
