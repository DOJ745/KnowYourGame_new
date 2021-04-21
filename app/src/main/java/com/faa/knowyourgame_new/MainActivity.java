package com.faa.knowyourgame_new;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    //private ServerService myService;
    //private User test_user = new User();
    //private ActivityMainBinding binding;
    //private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(navView, navController);


                /*binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        imageView = findViewById(R.id.image_view);

        myService = ApiUtils.getServerService();

        test_user.setLogin("login");
        test_user.setPassword("password");
        test_user.setScore(0.0);

        binding.setUser(test_user);*/

        //new DownloadImageTask(imageView).execute(ApiUtils.BASE_SERVER_URL + "images/cat.jpg");

        //registerUser();
    }

        /*public void registerUser() {
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
    }*/

}