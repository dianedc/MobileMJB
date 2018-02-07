package com.mjm.workflowkami.impl_classes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.util.Base64;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mjm.workflowkami.API;
import com.mjm.workflowkami.LoaderAsync;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.auth_classes.SpringBootClient;
import com.mjm.workflowkami.auth_classes.SpringIdentity;
import com.mjm.workflowkami.model_classes.UserClass;
import com.mjm.workflowkami.service_classes.UserService;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends LoaderAsync {

    protected static final String TAG = LoginActivity.class.getSimpleName();
    //    private SpotsDialog loader;
    Toast t;
    Intent i;
    private UserClass user;
    private UserService userService = API.getInstance().getUserService();
    private WebView webView;
    private String LOGIN_URL = "http://192.168.2.147:8080/caps/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("http://192.168.2.147:8080/caps/login");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


//        loader = new SpotsDialog(LoginActivity.this);
        // Set up the login form.
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            if(url.equals("http://192.168.2.147:8080/caps/")){
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

//    public void singInButtonOnClick(View v) {
////        final String uri = "http://servicemjm-env.ap-southeast-1.elasticbeanstalk.com/user/signin/"+txtEmail.getText().toString().trim()+"/"+txtPassword.getText().toString().trim();
//        new LoginTask().execute();
//
//    }

    private void displayResponse(UserClass response) {
        Toast.makeText(this, (CharSequence) response, Toast.LENGTH_LONG).show();
    }
}

//    private class LoginTask extends AsyncTask<Void, Void, UserClass> {
//
//        private String email, password;
//
//        @Override
//        protected void onPreExecute() {
//            showLoadingDialog();
//            EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
//            email = txtEmail.getText().toString();
//
//            EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
//            password = txtPassword.getText().toString();
//
//        }
//
//        @Override
//        protected UserClass doInBackground(Void... voids) {
//            final String uri = "http:///signin/"+email+"/"+password;
//
//            // Populate the HTTP Basic Authentitcation header with the username and password
//            HttpAuthentication authHeader = new HttpBasicAuthentication(email, password);
//            HttpHeaders requestHeaders = new HttpHeaders();
//            requestHeaders.setAuthorization(authHeader);
//            requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//            RestTemplate restTemplate = new RestTemplate();
//            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
//            try {
//
////                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
////                HttpHeaders headers = new HttpHeaders();
////
////                String auth = txtEmail.getText().toString() + ":" + txtPassword.getText().toString();
////
////                String encodedAuth = Base64.encodeToString(auth.getBytes(), Base64.DEFAULT);
////                String authHeader = "Basic " + new String(encodedAuth);
////                headers.set("Authorization", authHeader);
////
////                HttpEntity<String> entity = new HttpEntity<String>(headers);
////                ResponseEntity<UserClass> response = restTemplate.exchange(url, HttpMethod.GET, entity, UserClass.class);
////                return response;
//
//                ResponseEntity<UserClass> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), UserClass.class);
//                UserClass u = response.getBody();
//                return u;
//
//
//            } catch (HttpClientErrorException e) {
//                Log.e(TAG, e.getLocalizedMessage(), e);
////                AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
////                mBuilder.setTitle("Error!");
////                mBuilder.setMessage(e.getMessage());
////                AlertDialog a = mBuilder.create();
////                a.show();
//
//                return null;
//            } catch (ResourceAccessException e) {
//                Log.e(TAG, e.getLocalizedMessage(), e);
////                return new ResponseEntity<UserClass>(0, e.getClass().getSimpleName(), e.getLocalizedMessage());
////                AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
////                mBuilder.setTitle("Error!");
////                mBuilder.setMessage(e.getMessage());
////                AlertDialog a = mBuilder.create();
////                a.show();
//                return null;
//            }
//        }
//
//
//        @Override
//        protected void onPostExecute(UserClass userClassResponseEntity) {
//            dismissProgressDialog();
//            displayResponse(userClassResponseEntity);
////            if (userClassResponseEntity != null) {
////                UserClass user = userClassResponseEntity.getBody();
////                if (user.getUserstatus() == true) {
////                    i = new Intent(LoginActivity.this, Dashboard.class);
////                    startActivity(i);
////                } else {
////                    Toast.makeText(LoginActivity.this, "User is inactive", Toast.LENGTH_LONG);
////                }
////            } else {
////                Toast.makeText(LoginActivity.this, "Invalid email/password!", Toast.LENGTH_LONG);
////            }
//        }
//    }
//}






