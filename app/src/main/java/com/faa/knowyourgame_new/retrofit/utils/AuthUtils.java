package com.faa.knowyourgame_new.retrofit.utils;

import android.util.Log;

import com.faa.knowyourgame_new.dto.RegisterDto;
import com.faa.knowyourgame_new.dto.UserDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.faa.knowyourgame_new.retrofit.RetrofitClient.myService;

public class AuthUtils {

    private static String TAG = "AuthUtils";

    public interface RegistrationCallBack<T>{
        void register(String signUpResponse);
    }

    public interface LoginCallBack<T>{
        void login(String signInResponse);
    }

    public static void signIn(String login,
                              String password,
                              LoginCallBack loginCallBack) {

        myService.signIn(login, password).enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {

                if(response.isSuccessful()) {
                    Log.d(TAG, "Sign in user: " + response.body());

                    loginCallBack.login(response.body().toString());
                }
                else {
                    Log.d(TAG, "Sign in status(code): " + response.code());
                }
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable t) {
                Log.e(TAG, "Error loading from API");
            }
        });
    }

    public static void signUp(String login,
                              String password,
                              RegistrationCallBack registrationCallBack) {

        myService.signUp(login, password).enqueue(new Callback<RegisterDto>() {
            @Override
            public void onResponse(Call<RegisterDto> call, Response<RegisterDto> response) {

                if(response.isSuccessful()) {
                    Log.d(TAG, "Sign up status: " + response.body().getStatus());

                    registrationCallBack.register(response.body().getStatus());
                }
                else {
                    Log.d(TAG, "Sign up status(code): " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RegisterDto> call, Throwable t) {
                Log.e(TAG, "Error loading from API");
            }
        });
    }
}
