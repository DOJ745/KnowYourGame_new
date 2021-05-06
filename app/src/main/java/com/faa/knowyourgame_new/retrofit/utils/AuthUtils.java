package com.faa.knowyourgame_new.retrofit.utils;

import android.util.Log;

import com.faa.knowyourgame_new.dto.RegisterDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.faa.knowyourgame_new.retrofit.RetrofitClient.myService;

public class AuthUtils {

    private static String TAG = "AuthUtils";

    public interface RegistrationCallBack<T>{
        void register(String a);
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
