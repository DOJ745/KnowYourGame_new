package com.faa.knowyourgame_new;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.faa.knowyourgame_new.dto.UserDto;
import com.faa.knowyourgame_new.retrofit.ServerService;
import com.faa.knowyourgame_new.retrofit.utils.ApiUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ServerService myService;
    private static final String TAG = "MainActivity";

    //private User test_user = new User();
    //private ActivityMainBinding binding;
    //private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myService = ApiUtils.getServerService();
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications).build();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().
                findFragmentById(R.id.nav_host_fragment);

        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(navView, navController);

        showLoginDialog();


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

    private void showLoginDialog() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);

        View view = getLayoutInflater().inflate(R.layout.login_dialog, null);
        dialog.setView(view);

        EditText entered_login = view.findViewById(R.id.editLogin);
        EditText entered_password = view.findViewById(R.id.editPassword);

        Button sign_in = view.findViewById(R.id.button_sign_in);
        Button sign_up = view.findViewById(R.id.button_sign_up);

        sign_in.setOnClickListener( listener_in -> {

            if(hasConnection(this)) {
                Toast.makeText(this, "sign in", Toast.LENGTH_SHORT).show();
                dialog.setCancelable(true);
            }
            else
                Toast.makeText(this, "no internet connection", Toast.LENGTH_SHORT).show();
        });
        sign_up.setOnClickListener( listener_up -> {
            if(hasConnection(this)) {
                Toast.makeText(this, "sign up", Toast.LENGTH_SHORT).show();
                dialog.setCancelable(true);
            }
            else
                Toast.makeText(this, "no internet connection", Toast.LENGTH_SHORT).show();
        });

        entered_login.setError("Required field!");
        entered_login.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                if (entered_login.getText().length() != 0) {
                    entered_login.setError(null);
                }
                else {
                    entered_login.setError("Required!");
                }
            }
        });

        entered_password.setError("Required field! (> 5 symbols!)");
        entered_password.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                if (entered_password.getText().length() != 0 && entered_password.getText().length() > 5) {
                    entered_password.setError(null);
                }
                else {
                    entered_password.setError("Required!");
                }
            }
        });

        dialog.show();
    }

    public void signIn() {

    }

    public void signUp() {
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