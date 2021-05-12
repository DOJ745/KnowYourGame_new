package com.faa.knowyourgame_new.retrofit.utils;

import android.util.Log;

import com.faa.knowyourgame_new.dao.AnswerDao;
import com.faa.knowyourgame_new.dao.DifficultyDao;
import com.faa.knowyourgame_new.dao.LeagueDao;
import com.faa.knowyourgame_new.dao.QuestionDao;
import com.faa.knowyourgame_new.dao.ThemeDao;
import com.faa.knowyourgame_new.dto.AnswersDto;
import com.faa.knowyourgame_new.dto.DbDto;
import com.faa.knowyourgame_new.dto.DifficultyDto;
import com.faa.knowyourgame_new.dto.LeagueDto;
import com.faa.knowyourgame_new.dto.QuestionDto;
import com.faa.knowyourgame_new.dto.ThemeDto;
import com.faa.knowyourgame_new.entity.Answer;
import com.faa.knowyourgame_new.entity.Difficulty;
import com.faa.knowyourgame_new.entity.League;
import com.faa.knowyourgame_new.entity.Question;
import com.faa.knowyourgame_new.entity.Theme;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

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

    public static void firstLaunch(
            DbDto dbDto,
            ThemeDao themeDao,
            DifficultyDao difficultyDao,
            LeagueDao leagueDao,
            QuestionDao questionDao,
            AnswerDao answerDao) {

        ModelMapper modelMapper = new ModelMapper();

        Log.d(TAG + ":FIRST_LAUNCH", dbDto.toString());

        List<Theme> serverThemes = new ArrayList<>();
        List<Difficulty> serverDifficulties = new ArrayList<>();
        List<League> serverLeagues = new ArrayList<>();
        List<Question> serverQuestions = new ArrayList<>();
        List<Answer> serverAnswers = new ArrayList<>();

        Theme serverTheme = new Theme();
        Difficulty serverDifficulty = new Difficulty();
        League serverLeague = new League();
        Question serverQuestion = new Question();
        Answer serverAnswer = new Answer();

        for(ThemeDto elem : dbDto.getThemes()){
            serverTheme = modelMapper.map(elem, Theme.class);
            serverThemes.add(serverTheme);
        }

        for(DifficultyDto elem: dbDto.getDifficulties()){
            serverDifficulty = modelMapper.map(elem, Difficulty.class);
            serverDifficulties.add(serverDifficulty);
        }

        for(LeagueDto elem: dbDto.getLeagues()){
            serverLeague = modelMapper.map(elem, League.class);
            serverLeagues.add(serverLeague);
        }

        for(QuestionDto elem: dbDto.getQuestions()){
            serverQuestion = modelMapper.map(elem, Question.class);
            serverQuestions.add(serverQuestion);
        }

        for(AnswersDto elem: dbDto.getAnswers()){
            serverAnswer = modelMapper.map(elem, Answer.class);
            serverAnswers.add(serverAnswer);
        }

        themeDao.insertMany(serverThemes);
        difficultyDao.insertMany(serverDifficulties);
        leagueDao.insertMany(serverLeagues);
        questionDao.insertMany(serverQuestions);
        answerDao.insertMany(serverAnswers);
    }

    public static void checkForUpdates(
            DbDto dbDto,
            ThemeDao themeDao,
            DifficultyDao difficultyDao,
            LeagueDao leagueDao,
            QuestionDao questionDao,
            AnswerDao answerDao) {

        ModelMapper modelMapper = new ModelMapper();

        Log.d(TAG + ":CHECK_FOR_UPDATES", dbDto.toString());

        List<Theme> serverThemes = new ArrayList<>();
        List<Difficulty> serverDifficulties = new ArrayList<>();
        List<League> serverLeagues = new ArrayList<>();
        List<Question> serverQuestions = new ArrayList<>();
        List<Answer> serverAnswers = new ArrayList<>();

        Theme serverTheme = new Theme();
        Difficulty serverDifficulty = new Difficulty();
        League serverLeague = new League();
        Question serverQuestion = new Question();
        Answer serverAnswer = new Answer();

        for(ThemeDto elem : dbDto.getThemes()){
            serverTheme = modelMapper.map(elem, Theme.class);
            serverThemes.add(serverTheme);
        }

        for(DifficultyDto elem: dbDto.getDifficulties()){
            serverDifficulty = modelMapper.map(elem, Difficulty.class);
            serverDifficulties.add(serverDifficulty);
        }

        for(LeagueDto elem: dbDto.getLeagues()){
            serverLeague = modelMapper.map(elem, League.class);
            serverLeagues.add(serverLeague);
        }

        for(QuestionDto elem: dbDto.getQuestions()){
            serverQuestion = modelMapper.map(elem, Question.class);
            serverQuestions.add(serverQuestion);
        }

        for(AnswersDto elem: dbDto.getAnswers()){
            serverAnswer = modelMapper.map(elem, Answer.class);
            serverAnswers.add(serverAnswer);
        }

        List<Theme> dbThemes = themeDao.getAll();
        List<Difficulty> dbDifficulties = difficultyDao.getAll();

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
