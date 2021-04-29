package com.faa.knowyourgame_new;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.faa.knowyourgame_new.dto.UserDto;
import com.faa.knowyourgame_new.retrofit.ServerService;
import com.faa.knowyourgame_new.retrofit.utils.ApiUtils;
import com.faa.knowyourgame_new.ui.login_dialog.LoginDialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static ServerService myService = ApiUtils.getServerService();;
    private DialogFragment loginDialogFragment = new LoginDialogFragment();
    private static final String TAG = "MainActivity";

    //private User test_user = new User();
    //private ActivityMainBinding binding;
    //private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //myService = ApiUtils.getServerService();
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications).build();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().
                findFragmentById(R.id.nav_host_fragment);

        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(navView, navController);

        loginDialogFragment.show(getSupportFragmentManager(), "LOGIN_DIALOG");


        /*binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        imageView = findViewById(R.id.image_view);

        test_user.setLogin("login");
        test_user.setPassword("password");
        test_user.setScore(0.0);

        binding.setUser(test_user);*/

        //new DownloadImageTask(imageView).execute(ApiUtils.BASE_SERVER_URL + "images/cat.jpg");

        //registerUser();
    }

    public static boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void signIn() {

    }

    public static void signUp() {
        myService.postNewUser("login", "password").enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {

                if(response.isSuccessful()) {
                    Log.d(TAG, response.body().getLogin() + " --- " +
                            response.body().getPassword());
                }
                else {
                    Log.d(TAG, String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable t) {
                Log.e(TAG, "Error loading from API");
            }
        });
    }
}