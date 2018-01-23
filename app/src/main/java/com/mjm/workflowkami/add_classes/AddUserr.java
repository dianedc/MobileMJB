package com.mjm.workflowkami.add_classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.impl_classes.Users;
import com.mjm.workflowkami.model_classes.UserClass;
import com.mjm.workflowkami.service_classes.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserr extends AppCompatActivity {

    private EditText userID;
    private EditText userFName;
    private EditText userLName;
    private EditText userMName;
    private EditText emailadd;
    private EditText cemailAdd;
    private EditText userpassword;
    private EditText cuserpassword;
    private Switch userstatus;
    private UserClass user = new UserClass();
    private UserClass userIntent = new UserClass();
    private UserService userService = API.getInstance().getUserService();
    private Boolean statusSwitch;
    private Button saveUser;
    Toast t;
    Intent i;
    private ServiceImpl serviceImpl = new ServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_userr);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAddUser);
        setSupportActionBar(toolbar);

        userID = (EditText) findViewById(R.id.user_id);
        userFName = (EditText) findViewById(R.id.user_fname);
        userLName = (EditText) findViewById(R.id.user_lname);
        userMName = (EditText) findViewById(R.id.user_mname);
        emailadd = (EditText) findViewById(R.id.user_email);
        userpassword = (EditText) findViewById(R.id.user_pass);
        userstatus = (Switch) findViewById(R.id.userstatus);
        saveUser = (Button) findViewById(R.id.btnSaveUser);


        if (userstatus.isChecked()) {
            statusSwitch = true;
        } else {
            statusSwitch = false;
        }

        Intent intent = getIntent();
        userIntent = (UserClass) intent.getSerializableExtra("users");

        if (userIntent != null) {
            userID.setText(String.valueOf(userIntent.getUserID()));
            userFName.setText(userIntent.getFirstname());
            userLName.setText(userIntent.getLastname());
            userMName.setText(userIntent.getMiddlename());
            emailadd.setText(userIntent.getEmail());
            userpassword.setText(userIntent.getPassword());
            if (userIntent.getPassword() != null) {
                userpassword.setEnabled(false);
            }
            if (userIntent.getUserstatus() == true) {
                userstatus.setChecked(true);
            } else {
                userstatus.setChecked(false);
            }
        }

        saveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userID.getText().toString().matches("")) {
                    //update
                    user = new UserClass(Integer.valueOf(userID.getText().toString()),
                            userFName.getText().toString().trim(),
                            userLName.getText().toString().trim(),
                            userMName.getText().toString().trim(),
                            emailadd.getText().toString().trim(),
                            userpassword.getText().toString().trim(),
                            statusSwitch);
                    UpdateUser(userIntent.getUserID(), user);
                } else {
                    //add
                    user = new UserClass(userFName.getText().toString().trim(),
                            userLName.getText().toString().trim(),
                            userMName.getText().toString().trim(),
                            emailadd.getText().toString().trim(),
                            userpassword.getText().toString().trim(),
                            statusSwitch);
                    AddUser(user);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_userr, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_back:
                startActivity(new Intent(this, Users.class));
                return true;
//            case R.id.action_settings:
//                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void AddUser(UserClass u) {
        Call<UserClass> addUser = userService.addUser(u);

        addUser.enqueue(new Callback<UserClass>() {
            @Override
            public void onResponse(Call<UserClass> call, Response<UserClass> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(AddUserr.this, "User has been successfully added!", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddUserr.this);
                    alertDialogBuilder.setMessage("User has been successfully added!");
                    alertDialogBuilder.setCancelable(true);
                    alertDialogBuilder.show();

                    Intent u = new Intent(AddUserr.this, Users.class);
                    startActivity(u);
                }
            }
            @Override
            public void onFailure(Call<UserClass> call, Throwable t) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddUserr.this);
                alertDialogBuilder.setMessage("An error has been encountered while adding user");
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.show();
//                Toast.makeText(AddUserr.this, "An error has been encountered while adding user", Toast.LENGTH_SHORT);
            }
        });
    }

    public void UpdateUser(int id, UserClass u) {
        Call<Void> editUser = userService.editUser(id, u);

        editUser.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(AddUserr.this, "User has been successfully edited!", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddUserr.this);
                    alertDialogBuilder.setMessage("User has been successfully edited!");
                    alertDialogBuilder.setCancelable(true);
                    alertDialogBuilder.show();

                    Intent u = new Intent(AddUserr.this, Users.class);
                    startActivity(u);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddUserr.this, "An error has been encountered while editing user", Toast.LENGTH_SHORT);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddUserr.this);
                alertDialogBuilder.setMessage(t.toString());
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.show();
            }
        });
    }

    public void onClickCancel(View v) {
        Intent cancel = new Intent(AddUserr.this, Users.class);
        startActivity(cancel);

    }

}
