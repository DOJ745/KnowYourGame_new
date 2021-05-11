package com.faa.knowyourgame_new;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.faa.knowyourgame_new.dao.AnswerDao;
import com.faa.knowyourgame_new.dao.DifficultyDao;
import com.faa.knowyourgame_new.dao.LeagueDao;
import com.faa.knowyourgame_new.dao.QuestionDao;
import com.faa.knowyourgame_new.dao.ThemeDao;
import com.faa.knowyourgame_new.dao.UserDao;
import com.faa.knowyourgame_new.db.AppDatabase;
import com.faa.knowyourgame_new.dto.DbDto;
import com.faa.knowyourgame_new.dto.ThemeDto;
import com.faa.knowyourgame_new.entity.Theme;
import com.faa.knowyourgame_new.retrofit.utils.DbUtils;
import com.faa.knowyourgame_new.ui.login_dialog.LoginDialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private DialogFragment loginDialogFragment = new LoginDialogFragment();
    private static final String TAG = "MainActivity";
    private static final String PREFS_FILE = "Config";


    public static AppDatabase db;

    public static ThemeDao themeDao;
    public static DifficultyDao difficultyDao;
    public static QuestionDao questionDao;
    public static AnswerDao answerDao;
    public static LeagueDao leagueDao;
    public static UserDao userDao;

    public static DbDto dbDto;
    public static SharedPreferences configuration;
    public static SharedPreferences.Editor configEditor;

    //private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configuration = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        configEditor = configuration.edit();

        int firstLaunch = configuration.getInt("FIRST_LAUNCH", 0);

        // Initializing database
        /*db =  Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "main_db").build();*/
        db =  Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "main_db")
                .allowMainThreadQueries()
                .build();
        initDao(db);

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

        if(hasConnection(getApplicationContext())) {

            DbUtils.getData((response) ->
                    Toast.makeText(this,
                            response.toString(),
                            Toast.LENGTH_LONG).show());

            dbDto = new DbDto();
            DbUtils.getData((getDataResponse -> {

                dbDto.setThemes(getDataResponse.getThemes());
                dbDto.setDifficulties(getDataResponse.getDifficulties());
                dbDto.setQuestions(getDataResponse.getQuestions());
                dbDto.setAnswers(getDataResponse.getAnswers());
                dbDto.setLeagues(getDataResponse.getLeagues());

                if(configuration.getInt("FIRST_LAUNCH", 0) == 0) {
                    firstLaunch(dbDto);
                }
                else { checkForUpdates(dbDto); }
            }));

            firstLaunch += 1;
            configEditor.remove("FIRST_LAUNCH");
            configEditor.apply();

            configEditor.putInt("FIRST_LAUNCH", firstLaunch);
            configEditor.apply();
        }

        //new DownloadImageTask(imageView).execute(ApiUtils.BASE_SERVER_URL + "images/cat.jpg");
    }

    public static boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void initDao(AppDatabase _db){
        themeDao = _db.themeDao();
        difficultyDao = _db.difficultyDao();
        questionDao = _db.questionDao();
        answerDao = _db.answerDao();
        leagueDao = _db.leagueDao();
        userDao = _db.userDao();
    }

    private void firstLaunch(DbDto dbDto) {

        ModelMapper modelMapper = new ModelMapper();

        List<Theme> serverThemes = new ArrayList<>();
        Theme serverTheme = new Theme();

        for(ThemeDto elem : dbDto.getThemes()){
            serverTheme = modelMapper.map(elem, Theme.class);
            serverThemes.add(serverTheme);
        }

        themeDao.insertMany(serverThemes);
        Log.d(TAG, themeDao.getAll().get(0).toString());
    }

    private void checkForUpdates(DbDto dbDto) {

        ModelMapper modelMapper = new ModelMapper();

        Log.d(TAG, dbDto.toString());

        List<Theme> serverThemes = new ArrayList<>();

        Theme serverTheme = new Theme();
        for(ThemeDto elem : dbDto.getThemes()) {
            serverTheme = modelMapper.map(elem, Theme.class);
            serverThemes.add(serverTheme);
        }

        List<Theme> dbThemes = themeDao.getAll();

        for(Theme servTheme : serverThemes) {
            if(dbThemes.contains(servTheme)) {
                themeDao.update(servTheme);
            }
            else{
                dbThemes.add(servTheme);
                themeDao.insert(servTheme);
            }
        }

        // Removing same themes from temp collection
        if(serverThemes.size() < dbThemes.size()) {
            for(int i = 0; i < serverThemes.size(); i++) {
                dbThemes.remove(serverThemes.get(i));
            }
            // Deleting themes from DB
            themeDao.deleteMany(dbThemes);
        }
    }
}