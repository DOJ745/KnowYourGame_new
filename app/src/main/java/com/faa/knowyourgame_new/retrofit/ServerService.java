package com.faa.knowyourgame_new.retrofit;

import com.faa.knowyourgame_new.dto.UserDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerService {
    @POST("/register_user")
    Call<UserDto> postNewUser(@Query("login") String login, @Query("password") String password);

    @GET("/check_user")
    Call<UserDto> getCheckUser();
}
