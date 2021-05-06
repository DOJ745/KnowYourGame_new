package com.faa.knowyourgame_new.ui.login_dialog;

import android.app.Dialog;
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
import com.faa.knowyourgame_new.retrofit.utils.AuthUtils;

import static com.faa.knowyourgame_new.MainActivity.hasConnection;

public class LoginDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

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

        sign_in.setOnClickListener(listener_in -> {

            if(hasConnection(getActivity())) {

                AuthUtils.signIn(
                        entered_login.getText().toString(),
                        entered_password.getText().toString(),
                        (response) ->
                                Toast.makeText(getActivity(),
                                        response,
                                        Toast.LENGTH_SHORT).show());
                loginDialog.cancel();
            }
            else
                Toast.makeText(getActivity(),
                        "No internet connection!",
                        Toast.LENGTH_SHORT).show();
        });

        sign_up.setOnClickListener(listener_up -> {

            if(hasConnection(getActivity())) {

                AuthUtils.signUp(
                        entered_login.getText().toString(),
                        entered_password.getText().toString(),
                        (response) ->
                                Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show());
            }
            else
                Toast.makeText(getActivity(),
                        "No internet connection!",
                        Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(DialogInterface dialog, int which) { }
}
