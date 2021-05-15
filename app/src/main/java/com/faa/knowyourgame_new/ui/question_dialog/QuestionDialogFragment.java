package com.faa.knowyourgame_new.ui.question_dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.faa.knowyourgame_new.R;
import com.faa.knowyourgame_new.entity.Answer;
import com.faa.knowyourgame_new.entity.Question;
import com.faa.knowyourgame_new.retrofit.utils.ApiUtils;
import com.faa.knowyourgame_new.retrofit.utils.DownloadImageTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.faa.knowyourgame_new.MainActivity.IMAGE_PATH;
import static com.faa.knowyourgame_new.MainActivity.answerDao;
import static com.faa.knowyourgame_new.MainActivity.themeDao;
import static com.faa.knowyourgame_new.ui.home.HomeFragment.ChosenQuestions;

public class QuestionDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private static int answerResult = 0;

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        View view = getLayoutInflater().inflate(R.layout.question_dialog, null);
        dialog.setView(view);

        TextView themeText = view.findViewById(R.id.theme_text);
        TextView chosenAnswer = view.findViewById(R.id.chosen_answer);
        TextView timerCountdown = view.findViewById(R.id.countdown);

        ImageView questionImage = view.findViewById(R.id.question_image);

        Button answer_var_one = view.findViewById(R.id.btn_answer_var_one);
        Button answer_var_two = view.findViewById(R.id.btn_answer_var_two);
        Button answer_var_three = view.findViewById(R.id.btn_answer_var_three);
        Button answer_on_question = view.findViewById(R.id.btn_answer);

        Dialog questionDialog = dialog.create();

        List<Question> QUESTIONS = ChosenQuestions;
        List<Answer> ANSWERS;
        themeText.setText(themeDao.getNameById(QUESTIONS.get(0).getTheme_id()));

        ANSWERS = answerDao.getAnswersForQuestion(QUESTIONS.get(0).get_id());

        questionImage.setImageDrawable(Drawable.createFromPath(IMAGE_PATH + "/" + QUESTIONS.get(0).getImage()));

        answer_var_one.setText(ANSWERS.get(0).getText());
        answer_var_two.setText(ANSWERS.get(1).getText());
        answer_var_three.setText(ANSWERS.get(2).getText());

        CountDownTimer answerTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerCountdown.setText("Time left: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                timerCountdown.setText("Times up!");
                //wrongUserAnswer();
                questionDialog.dismiss();
            }
        }.start();


        answer_var_one.setOnClickListener(v -> {
            answerResult = ANSWERS.get(0).getTrueness();
            chosenAnswer.setText("Chosen 1 variant");
        });

        answer_var_two.setOnClickListener(v -> {
            answerResult = ANSWERS.get(1).getTrueness();
            chosenAnswer.setText("Chosen 2 variant");
        });

        answer_var_three.setOnClickListener(v -> {
            answerResult = ANSWERS.get(2).getTrueness();
            chosenAnswer.setText("Chosen 3 variant");
        });

        answer_on_question.setOnClickListener(v -> {

            if(answerResult == 0) {
                answerTimer.cancel();
                questionDialog.dismiss();
            }
        });

        return questionDialog;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) { }

    private Bitmap pickImageForView(File storagePath, String imgName) {

        Bitmap foundImage = null;
        boolean success = false;
        try {

            String imagePath = storagePath.getPath() + "/" + imgName;
            InputStream ims = getClass().getResourceAsStream(imagePath);


            foundImage = BitmapFactory.decodeStream(ims);

            if(foundImage != null)
                success = true;
        }
        catch (Exception e) { e.printStackTrace(); }

        if (success) { Log.e("LoginDialogFragment", "Image found!"); }
        else {  Log.e("LoginDialogFragment", "Image NOT found!"); }
        return foundImage;
    }
}
