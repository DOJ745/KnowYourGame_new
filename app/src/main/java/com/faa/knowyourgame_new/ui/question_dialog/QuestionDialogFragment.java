package com.faa.knowyourgame_new.ui.question_dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.faa.knowyourgame_new.R;
import com.faa.knowyourgame_new.entity.Question;

import java.util.List;

import static com.faa.knowyourgame_new.MainActivity.themeDao;
import static com.faa.knowyourgame_new.ui.home.HomeFragment.ChosenQuestions;

public class QuestionDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        View view = getLayoutInflater().inflate(R.layout.question_dialog, null);
        dialog.setView(view);

        TextView themeText = view.findViewById(R.id.theme_text);
        TextView chosenAnswer = view.findViewById(R.id.chosen_answer);

        ImageView questionImage = view.findViewById(R.id.question_image);

        Button answer_var_one = view.findViewById(R.id.btn_answer_var_one);
        Button answer_var_two = view.findViewById(R.id.btn_answer_var_two);
        Button answer_var_three = view.findViewById(R.id.btn_answer_var_three);

        Dialog questionDialog = dialog.create();

        List<Question> QUESTIONS = ChosenQuestions;
        themeText.setText(themeDao.getNameById(QUESTIONS.get(0).getTheme_id()));

        return questionDialog;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) { }
}
