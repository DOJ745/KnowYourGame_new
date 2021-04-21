package com.faa.knowyourgame_new.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class LeagueDto implements Serializable {
    String name;
    String image;
    int rating;
}
