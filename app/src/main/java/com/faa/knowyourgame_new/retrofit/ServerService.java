package com.faa.knowyourgame_new.retrofit;

import com.faa.knowyourgame_new.dto.LogoutDto;
import com.faa.knowyourgame_new.dto.RegisterDto;
import com.faa.knowyourgame_new.dto.UserDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerService {

    @POST("/sign_up")
    Call<RegisterDto> signUp(@Query("login") String login, @Query("password") String password);

    @POST("/sign_in")
    Call<UserDto> signIn(@Query("login") String login, @Query("password") String password);

    @POST("/log_out")
    Call<LogoutDto> logout();

    //@GET("/get_data")

    @GET("/test_request")
    Call<UserDto> getCheckUser();
}
