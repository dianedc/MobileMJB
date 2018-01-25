package com.mjm.workflowkami.add_classes;

import android.content.Intent;
import android.os.Bundle;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.UserClass;
import com.mjm.workflowkami.service_classes.ProjectService;
import com.mjm.workflowkami.impl_classes.Projects;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.service_classes.UserService;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddProject extends AppCompatActivity
        implements OnClickListener {



    private EditText projProgress, projName, projID, fromDate, toDate, projClient, projDesc, dateComp, projCBudget, projTBudget, projDuration, projStatus, projType;
    private TextView projMan;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private ProjectClass projectIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        projID = (EditText) findViewById(R.id.project_id);
        projName = (EditText) findViewById(R.id.project_name);
        projStatus = (EditText) findViewById(R.id.project_status);
        projClient = (EditText) findViewById(R.id.proj_client);
        projDesc = (EditText) findViewById(R.id.proj_desc);
        dateComp = (EditText) findViewById(R.id.proj_date_comp);
        projCBudget = (EditText) findViewById(R.id.proj_c_budget);
        projTBudget = (EditText) findViewById(R.id.proj_t_budget);
        projDuration = (EditText) findViewById(R.id.proj_duration);
        projType = (EditText) findViewById(R.id.proj_type);
        projProgress = (EditText) findViewById(R.id.project_progress);
        fromDate = (EditText) findViewById(R.id.project_start_date);
        toDate = (EditText) findViewById(R.id.project_end_date);
        projMan = (TextView) findViewById(R.id.viewProjMan);
        dateFormatter = new SimpleDateFormat("yyyy-dd-MM", Locale.US);


        projProgress.setEnabled(false);
        projName.setEnabled(false);
        projID.setEnabled(false);
        fromDate.setEnabled(false);
        toDate.setEnabled(false);
        projClient.setEnabled(false);
        projDesc.setEnabled(false);
        dateComp.setEnabled(false);
        projCBudget.setEnabled(false);
        projTBudget.setEnabled(false);
        projDuration.setEnabled(false);
        projStatus.setEnabled(false);
        projType.setEnabled(false);

        Intent intent = getIntent();
        projectIntent = (ProjectClass) intent.getSerializableExtra("projects");


        if (projectIntent != null) {
            projID.setText(String.valueOf(projectIntent.getProjID()));
            projName.setText(projectIntent.getProjname());
            projClient.setText(projectIntent.getProjclient());
            projDesc.setText(projectIntent.getProjdesc());
            dateComp.setText(projectIntent.getProjdatecompleted());

            fromDate.setText(projectIntent.getProjstartdate());
            toDate.setText(projectIntent.getProjenddate());

            projStatus.setText(projectIntent.getProjstatus());
            projType.setText(projectIntent.getProjtype());
            projMan.setText(projectIntent.getProjmanager().getLastname() + ", " + projectIntent.getProjmanager().getFirstname());
            projProgress.setText(projectIntent.getProjprogress().toString());
            projCBudget.setText(String.valueOf(projectIntent.getProjcontractbudget()));
            projTBudget.setText(String.valueOf(projectIntent.getProjtargetbudget()));
            projDuration.setText(projectIntent.getProjduration());
        }

        findViewsById();
        setDateTimeField();

    }
    private void findViewsById() {
        fromDate = (EditText) findViewById(R.id.project_start_date);
        fromDate.setInputType(InputType.TYPE_NULL);

        toDate = (EditText) findViewById(R.id.project_end_date);
        toDate.setInputType(InputType.TYPE_NULL);

        projName = (EditText) findViewById(R.id.project_name);
        projName.setInputType(InputType.TYPE_NULL);

    }

    private void setDateTimeField() {
        fromDate.setOnClickListener(this);
        toDate.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View view) {
        if(view == fromDate) {
            fromDatePickerDialog.show();
        } else if(view == toDate) {
            toDatePickerDialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_project, menu);
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickCancel(View v) {
        Intent cancel = new Intent(AddProject.this, Projects.class);
        startActivity(cancel);
    }
}
