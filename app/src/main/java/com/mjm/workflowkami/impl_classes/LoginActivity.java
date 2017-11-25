package com.mjm.workflowkami.impl_classes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Base64;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.LoaderAsync;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.model_classes.UserClass;
import com.mjm.workflowkami.service_classes.UserService;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends LoaderAsync {

    private EditText txtEmail;
    private EditText txtPassword;
//    private SpotsDialog loader;
    Toast t;
    Intent i;
    private UserClass user;
    private UserService userService = API.getInstance().getUserService();

    private class LoginTask extends AsyncTask<String, Void, ResponseEntity<UserClass>> {

        @Override
        protected void onPreExecute() {
            showLoadingDialog();
        }

        @Override
        protected ResponseEntity<UserClass> doInBackground(String... strings) {
            final String url = strings[0];
            RestTemplate restTemplate = new RestTemplate();
             try {

                 restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                 HttpHeaders headers = new HttpHeaders();

                 String auth = txtEmail.getText().toString() + ":" + txtPassword.getText().toString();

                 String encodedAuth = Base64.encodeToString(auth.getBytes(), Base64.DEFAULT);
                 String authHeader = "Basic " + new String(encodedAuth);
                 headers.set("Authorization", authHeader);

                 HttpEntity<String> entity = new HttpEntity<String>(headers);
                 ResponseEntity<UserClass> response = restTemplate.exchange(url, HttpMethod.GET, entity, UserClass.class);
                 return response;


             } catch (Exception eo) {
                String message = eo.getMessage();
                return null;
             }
        }

        @Override
        protected void onPostExecute(ResponseEntity<UserClass> userClassResponseEntity) {
            dismissProgressDialog();
            if (userClassResponseEntity != null) {
                UserClass user = userClassResponseEntity.getBody();
                if (user.getUserstatus() == true) {
                    i = new Intent(LoginActivity.this, Dashboard.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "User is inactive", Toast.LENGTH_LONG);
                }
            } else {
                Toast.makeText(LoginActivity.this, "Invalid email/password!", Toast.LENGTH_LONG);
            }
        }



    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
//        loader = new SpotsDialog(LoginActivity.this);
        // Set up the login form.
    }

    public void singInButtonOnClick (View v) {
//        loader.show();
//        if (!"".equals(txtPassword.getText().toString()) && !"".equals(txtEmail.getText().toString())) {
//            user = new UserClass();
//            user.setEmail(txtEmail.getText().toString().trim());
//            user.setPassword(txtPassword.getText().toString().trim());
//
//            Call<UserClass> viewUser = userService.getUserByEmailPassword(user.getEmail(), user.getPassword());
//            viewUser.enqueue(new Callback<UserClass>() {
//                @Override
//                public void onResponse(Call<UserClass> call, Response<UserClass> response) {
//
//                    if (response.isSuccessful()) {
//                        loader.dismiss();
//                        i = new Intent(LoginActivity.this, Dashboard.class);
//                        startActivity(i);
//                    } else {
//                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
//                        alertDialogBuilder.setMessage("Invalid Username/Password!");
//                        alertDialogBuilder.setCancelable(true);
//                        alertDialogBuilder.show();
//                        loader.dismiss();
//                        Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_LONG).show();
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<UserClass> call, Throwable t) {

//                    Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_LONG).show();
//                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
//                    alertDialogBuilder.setMessage(t.toString());
//                    alertDialogBuilder.setCancelable(true);
//                    alertDialogBuilder.show();
//                    loader.dismiss();
//                }
//
//            });
//        } else {
//
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
//            alertDialogBuilder.setMessage("Invalid Email/Password! Please try again.");
//            alertDialogBuilder.setCancelable(true);
//            alertDialogBuilder.show();
//            loader.dismiss();
//            Toast.makeText(LoginActivity.this, "Invalid Email/Password! Please try again.", Toast.LENGTH_LONG).show();
//        }
//        alertDialog.dismiss();
        final String uri = "http://servicemjm-env.ap-southeast-1.elasticbeanstalk.com/user/signin/"+txtEmail.getText().toString().trim()+"/"+txtPassword.getText().toString().trim();
        new LoginTask().execute(uri);



    }
}

