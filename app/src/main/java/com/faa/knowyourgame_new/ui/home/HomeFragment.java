package com.faa.knowyourgame_new.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.faa.knowyourgame_new.ui.question_dialog.QuestionDialogFragment;

import java.util.ArrayList;
import java.util.List;

import static com.faa.knowyourgame_new.MainActivity.difficultyDao;

public class HomeFragment extends Fragment {

    private DialogFragment questionDialogFragment = new QuestionDialogFragment();
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);
        final Spinner spinner = root.findViewById(R.id.difficulty_spinner);
        final Button playButton = root.findViewById(R.id.btn_play);

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
                textView.setText(item + " -- " + position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}