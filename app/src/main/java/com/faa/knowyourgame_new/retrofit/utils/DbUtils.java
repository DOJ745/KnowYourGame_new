package com.faa.knowyourgame_new.retrofit.utils;

import android.util.Log;

import com.faa.knowyourgame_new.dto.DbDto;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.faa.knowyourgame_new.retrofit.RetrofitClient.myService;

public class DbUtils {
    private static final String TAG = "DbUtils";

    public interface DbCallBack<T>{
        void dbData(DbDto getDataResponse);
    }

    public static void getData(DbCallBack dbCallBack){
        myService.getData().enqueue(new Callback<DbDto>() {
            @Override
            public void onResponse(
                    @NotNull Call<DbDto> call,
                    @NotNull Response<DbDto> response) {
                Log.d(TAG, "DATA FROM SERVER: " + response.body());
                dbCallBack.dbData(response.body());
            }

            @Override
            public void onFailure(
                    @NotNull Call<DbDto> call,
                    @NotNull Throwable t) {
                Log.e(TAG, "Error loading from API");
            }
        });
    }
}
