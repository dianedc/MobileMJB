package com.example.jasper.capstone.impl_classes;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jasper.capstone.API;
import com.example.jasper.capstone.R;
import com.example.jasper.capstone.model_classes.UserClass;
import com.example.jasper.capstone.service_classes.UserService;

import dmax.dialog.ProgressLayout;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
//    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
//    private static final String[] DUMMY_CREDENTIALS = new String[]{
//            "foo@example.com:hello", "bar@example.com:world"
//    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private EditText mPasswordView, mEmailView;
    private EditText txtEmail;
    private EditText txtPassword;
    private View mProgressView;
    private View mLoginFormView;
    private SpotsDialog loader;
    Toast t;
    Intent i;
    private UserClass user;
    private UserService userService = API.getInstance().getUserService();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        loader = new SpotsDialog(LoginActivity.this);
        // Set up the login form.
    }

    public void singInButtonOnClick (View v) {
        loader.show();
        if (!"".equals(txtPassword.getText().toString()) && !"".equals(txtEmail.getText().toString())) {
            user = new UserClass();
            user.setEmail(txtEmail.getText().toString().trim());
            user.setPassword(txtPassword.getText().toString().trim());

            Call<UserClass> viewUser = userService.getUserByEmailPassword(user.getEmail(), user.getPassword());
            viewUser.enqueue(new Callback<UserClass>() {
                @Override
                public void onResponse(Call<UserClass> call, Response<UserClass> response) {

                    if (response.isSuccessful()) {
                        loader.dismiss();
                        i = new Intent(LoginActivity.this, Dashboard.class);
                        startActivity(i);
                    } else {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                        alertDialogBuilder.setMessage("Invalid Username/Password!");
                        alertDialogBuilder.setCancelable(true);
                        alertDialogBuilder.show();
                        loader.dismiss();
//                        Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<UserClass> call, Throwable t) {

//                    Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                    alertDialogBuilder.setMessage(t.toString());
                    alertDialogBuilder.setCancelable(true);
                    alertDialogBuilder.show();
                    loader.dismiss();
                }

            });
        } else {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
            alertDialogBuilder.setMessage("Invalid Email/Password! Please try again.");
            alertDialogBuilder.setCancelable(true);
            alertDialogBuilder.show();
            loader.dismiss();
//            Toast.makeText(LoginActivity.this, "Invalid Email/Password! Please try again.", Toast.LENGTH_LONG).show();
        }
//        alertDialog.dismiss();
    }
}

