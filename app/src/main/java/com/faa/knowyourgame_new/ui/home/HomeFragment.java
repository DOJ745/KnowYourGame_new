package com.faa.knowyourgame_new.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.faa.knowyourgame_new.R;
import com.faa.knowyourgame_new.entity.Difficulty;
import com.faa.knowyourgame_new.retrofit.utils.ApiUtils;
import com.faa.knowyourgame_new.retrofit.utils.DownloadImageTask;
import com.faa.knowyourgame_new.ui.question_dialog.QuestionDialogFragment;

import java.util.ArrayList;
import java.util.List;

import static com.faa.knowyourgame_new.MainActivity.difficultyDao;
import static com.faa.knowyourgame_new.MainActivity.leagueDao;
import static com.faa.knowyourgame_new.MainActivity.userDao;
import static com.faa.knowyourgame_new.ui.login_dialog.LoginDialogFragment.LoginUserName;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private DialogFragment questionDialogFragment = new QuestionDialogFragment();
    private HomeViewModel homeViewModel;
    private static int ChosenDif;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        TextView textUserScore = root.findViewById(R.id.text_home);
        Spinner spinner = root.findViewById(R.id.difficulty_spinner);
        Button playButton = root.findViewById(R.id.btn_play);

        ImageView ratingIcon = root.findViewById(R.id.rating_icon);
        ImageView leagueIcon = root.findViewById(R.id.league_icon);


        if(userDao.getUserScore(LoginUserName) >= 0 && userDao.getUserScore(LoginUserName) <= 100) {

            String leagueImgName = leagueDao.getLeagueImg("Bronze");
            Log.d(TAG, leagueImgName);

            new DownloadImageTask(leagueIcon, leagueImgName).execute(
                    ApiUtils.BASE_SERVER_LEAGUE_IMAGE_DIR + leagueImgName);
        }
        if(userDao.getUserScore(LoginUserName) >= 101 && userDao.getUserScore(LoginUserName) <= 150){
            String leagueImgName = leagueDao.getLeagueImg("Silver");

            new DownloadImageTask(leagueIcon, leagueImgName).execute(
                    ApiUtils.BASE_SERVER_LEAGUE_IMAGE_DIR + leagueImgName);
        }
        if(userDao.getUserScore(LoginUserName) >= 151){
            String leagueImgName = leagueDao.getLeagueImg("Gold");

            new DownloadImageTask(leagueIcon, leagueImgName).execute(
                    ApiUtils.BASE_SERVER_LEAGUE_IMAGE_DIR + leagueImgName);
        }


        ratingIcon.setOnClickListener(listener ->
                questionDialogFragment.show(this.getParentFragmentManager(), "TEST_DIALOG"));

        playButton.setOnClickListener(listener ->
                questionDialogFragment.show(this.getParentFragmentManager(), "QUESTION_DIALOG")
        );

        List<Difficulty> difficultyList = difficultyDao.getAll();
        List<String> difficultyNames = new ArrayList<>();

        for(int i = 0; i < difficultyList.size(); i++){
            difficultyNames.add(difficultyList.get(i).getName()) ;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(),
                android.R.layout.simple_spinner_item, difficultyNames);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                ChosenDif = difficultyDao.getIdByName(item);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

        textUserScore.setText("Your current score - " +
                String.valueOf(userDao.getUserScore(LoginUserName)));

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textUserScore.setText(s);
            }
        });

        return root;
    }
}