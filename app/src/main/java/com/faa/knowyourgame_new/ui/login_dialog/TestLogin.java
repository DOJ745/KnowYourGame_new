package com.faa.knowyourgame_new.ui.login_dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.faa.knowyourgame_new.R;

import lombok.Getter;
import lombok.Setter;

import static com.faa.knowyourgame_new.MainActivity.hasConnection;

@Getter
@Setter
public class TestLogin extends DialogFragment implements DialogInterface.OnClickListener {

    //private Dialog loginDialog;
    //private Context activityContext;


    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setCancelable(false);

        View view = getLayoutInflater().inflate(R.layout.login_dialog, null);
        dialog.setView(view);


        EditText entered_login = view.findViewById(R.id.editLogin);
        EditText entered_password = view.findViewById(R.id.editPassword);

        Button sign_in = view.findViewById(R.id.button_sign_in);
        Button sign_up = view.findViewById(R.id.button_sign_up);

        Dialog loginDialog = dialog.create();

        sign_in.setOnClickListener( listener_in -> {

            if(hasConnection(getActivity())) {
                Toast.makeText(getActivity(), "sign in", Toast.LENGTH_SHORT).show();
                loginDialog.cancel();
            }
            else
                Toast.makeText(getActivity(), "no internet connection", Toast.LENGTH_SHORT).show();
        });

        sign_up.setOnClickListener( listener_up -> {
            if(hasConnection(getActivity())) {
                Toast.makeText(getActivity(), "sign up", Toast.LENGTH_SHORT).show();
                loginDialog.cancel();
            }
            else
                Toast.makeText(getActivity(), "no internet connection", Toast.LENGTH_SHORT).show();
        });

        entered_login.setError("Required field! (> 3 symbols!)");
        entered_login.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                if (entered_login.getText().length() != 0 && entered_login.getText().length() > 3) {
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

        return loginDialog;
    }
    /*
    public Dialog createDialog(Context context) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(false);

        View view = activity.getLayoutInflater().inflate(R.layout.login_dialog, null);
        dialog.setView(view);


        EditText entered_login = view.findViewById(R.id.editLogin);
        EditText entered_password = view.findViewById(R.id.editPassword);

        Button sign_in = view.findViewById(R.id.button_sign_in);
        Button sign_up = view.findViewById(R.id.button_sign_up);

        Dialog loginDialog = dialog.create();

        sign_in.setOnClickListener( listener_in -> {

            if(hasConnection(context)) {
                Toast.makeText(context, "sign in", Toast.LENGTH_SHORT).show();
                loginDialog.cancel();
            }
            else
                Toast.makeText(context, "no internet connection", Toast.LENGTH_SHORT).show();
        });

        sign_up.setOnClickListener( listener_up -> {
            if(hasConnection(context)) {
                Toast.makeText(context, "sign up", Toast.LENGTH_SHORT).show();
                loginDialog.cancel();
            }
            else
                Toast.makeText(context, "no internet connection", Toast.LENGTH_SHORT).show();
        });

        entered_login.setError("Required field! (> 3 symbols!)");
        entered_login.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                if (entered_login.getText().length() != 0 && entered_login.getText().length() > 3) {
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

        return loginDialog;
    }*/

    @Override
    public void onClick(DialogInterface dialog, int which) {

        switch (which) {
            case R.id.button_sign_in:
                if(hasConnection(getActivity())) {
                    Toast.makeText(getActivity(), "sign in", Toast.LENGTH_SHORT).show();
                    super.onCancel(dialog);
                }
                else
                    Toast.makeText(getActivity(), "no internet connection", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
