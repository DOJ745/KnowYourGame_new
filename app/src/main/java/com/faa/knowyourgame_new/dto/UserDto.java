package com.faa.knowyourgame_new.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDto implements Serializable {
    @SerializedName("login") @Expose
    public String login;
    @SerializedName("password") @Expose
    public String password;
    @SerializedName("score") @Expose
    public double score;
}