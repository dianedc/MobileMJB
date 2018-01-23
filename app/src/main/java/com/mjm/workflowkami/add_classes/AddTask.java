package com.mjm.workflowkami.add_classes;

import android.content.Intent;
import android.os.Bundle;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.text.InputType;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.impl_classes.Forms;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.adapter_classes.SpinnerAdapter;
import com.mjm.workflowkami.impl_classes.Dashboard;
import com.mjm.workflowkami.impl_classes.Files;

import com.mjm.workflowkami.impl_classes.Projects;
import com.mjm.workflowkami.impl_classes.PurchaseOrder;
import com.mjm.workflowkami.impl_classes.Reports;
import com.mjm.workflowkami.impl_classes.Tasks;
import com.mjm.workflowkami.impl_classes.Users;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.TaskClass;
import com.mjm.workflowkami.model_classes.UserClass;
import com.mjm.workflowkami.service_classes.TaskService;
import com.mjm.workflowkami.service_classes.UserService;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTask extends AppCompatActivity implements OnClickListener {

    private EditText taskID, taskprojID, taskName, taskDescription, fromDate, toDate, taskHeader, dateComp, taskDuration, taskBudget;
    private Spinner taskStatus, taskPhase;
    private Button saveTask;
    private DatePickerDialog fromDatePickerDialog, toDatePickerDialog, compDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private ServiceImpl serviceImpl = new ServiceImpl();
    private TaskClass taskIntent = new TaskClass();
    private ProjectClass projIntent = new ProjectClass();
    private TaskClass task;
    private TaskService taskService = API.getInstance().getTaskService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewsById();
        setDateTimeField();
        serviceImpl.GetAllUserId();
        serviceImpl.GetAllUsers();

        taskID = (EditText) findViewById(R.id.task_id);
        taskprojID = (EditText) findViewById(R.id.task_projID);
        taskName = (EditText) findViewById(R.id.task_name);
        taskDescription = (EditText) findViewById(R.id.task_desc);
        taskPhase = (Spinner) findViewById(R.id.task_phase);
        taskHeader = (EditText) findViewById(R.id.task_header) ;
        fromDate = (EditText) findViewById(R.id.task_start_date);
        toDate = (EditText) findViewById(R.id.task_end_date);
        dateComp = (EditText) findViewById(R.id.task_date_completed);
        taskStatus = (Spinner) findViewById(R.id.task_status);
        taskDuration = (EditText) findViewById(R.id.task_duration);
        saveTask = (Button) findViewById(R.id.btnSaveTask);
        dateFormatter = new SimpleDateFormat("yyyy-dd-MM", Locale.US);
        taskBudget = (EditText) findViewById(R.id.task_budget);

        taskDuration.setEnabled(false);
        taskStatus.setEnabled(false);
        dateComp.setEnabled(false);

        Intent tIntent = getIntent();
        taskIntent = (TaskClass) tIntent.getSerializableExtra("tasks");
        Intent pIntent = getIntent();
        projIntent = (ProjectClass) pIntent.getSerializableExtra("projects");

        if (taskIntent != null) {

            taskID.setText(String.valueOf(taskIntent.getTaskID()));
            taskprojID.setText(String.valueOf(taskIntent.getProjectID()));
            taskName.setText(taskIntent.getTaskname());
            taskDescription.setText(taskIntent.getTaskdesc());

            for (int i = 0; i < taskPhase.getCount(); i++) {
                if (taskPhase.getItemAtPosition(i).toString().equals(taskIntent.getTaskphase())) {
                    taskPhase.setSelection(i);
                    break;
                }
            }
            taskBudget.setText(String.valueOf(taskIntent.getTaskbudget()));
            taskHeader.setText(taskIntent.getTaskheader());
            fromDate.setText(taskIntent.getTaskstartdate());
            toDate.setText(taskIntent.getTaskenddate());
            dateComp.setText(taskIntent.getTaskdatecompleted());
            for (int i = 0; i < taskStatus.getCount(); i++) {
                if (taskStatus.getItemAtPosition(i).toString().equals(taskIntent.getTaskstatus())) {
                    taskStatus.setSelection(i);
                    break;
                }
            }
            taskDuration.setText(taskIntent.getTaskduration());
        }
        final BigDecimal budget = new BigDecimal(taskBudget.getText().toString());

        saveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!taskID.getText().toString().matches("")) {
                    //update
                    Toast.makeText(AddTask.this, String.valueOf(taskIntent.getProjectID().getProjID()), Toast.LENGTH_LONG).show();
                    Toast.makeText(AddTask.this, taskID.getText().toString(), Toast.LENGTH_LONG).show();
                    task = new TaskClass(Integer.valueOf(taskID.getText().toString()),
                            taskIntent.getProjectID(),
                            taskName.getText().toString().trim(),
                            taskDescription.getText().toString().trim(),
                            taskPhase.getSelectedItem().toString().trim(),
                            taskHeader.getText().toString().trim(),
                            budget,
                            fromDate.getText().toString().trim(),
                            toDate.getText().toString().trim(),
                            dateComp.getText().toString().trim(),
                            taskStatus.getSelectedItem().toString().trim(),
                            taskDuration.getText().toString().trim());
                    UpdateTask(taskIntent.getProjectID().getProjID(), Integer.valueOf(taskID.getText().toString()), task);
                } else {
                    //add
                    Toast.makeText(AddTask.this, String.valueOf(projIntent.getProjID()), Toast.LENGTH_LONG).show();
                    task = new TaskClass(taskIntent.getProjectID(),
                            taskName.getText().toString().trim(),
                            taskDescription.getText().toString().trim(),
                            taskPhase.getSelectedItem().toString().trim(),
                            taskHeader.getText().toString().trim(),
                            budget,
                            fromDate.getText().toString().trim(),
                            toDate.getText().toString().trim(),
                            dateComp.getText().toString().trim(),
                            taskStatus.getSelectedItem().toString().trim(),
                            taskDuration.getText().toString().trim());
                    SaveTask(taskIntent.getProjectID().getProjID(), task);
                }
            }
        });

    }

    private void findViewsById() {
            fromDate = (EditText) findViewById(R.id.task_start_date);
            fromDate.setInputType(InputType.TYPE_NULL);

//            fromDate.requestFocus();
            toDate = (EditText) findViewById(R.id.task_end_date);
            toDate.setInputType(InputType.TYPE_NULL);

            dateComp = (EditText) findViewById(R.id.task_date_completed);
            dateComp.setInputType(InputType.TYPE_NULL);

            taskName = (EditText) findViewById(R.id.task_name);
            taskName.setInputType(InputType.TYPE_NULL);
            taskName.requestFocus();
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

        compDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateComp.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
    @Override
    public void onClick(View view) {
        if(view == fromDate) {
            fromDatePickerDialog.show();
        } else if(view == toDate) {
            toDatePickerDialog.show();
        } else if (view == dateComp) {
            compDatePickerDialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_task, menu);
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_back_task) {
            startActivity(new Intent(this, Tasks.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickCancel(View v) {
        Intent cancel = new Intent(AddTask.this, Tasks.class);
        startActivity(cancel);
    }

    public void SaveTask(int projID, TaskClass t) {

        Call<TaskClass> addTask = taskService.addTask(projID, t);

        addTask.enqueue(new Callback<TaskClass>() {
            @Override
            public void onResponse(Call<TaskClass> call, Response<TaskClass> response) {
                Toast.makeText(AddTask.this, response.toString(), Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    Toast.makeText(AddTask.this, "Task has been successfully added!", Toast.LENGTH_SHORT).show();

                    Intent u = new Intent(AddTask.this, Tasks.class);
                    startActivity(u);
                }
            }
            @Override
            public void onFailure(Call<TaskClass> call, Throwable t) {
                Toast.makeText(AddTask.this, "An error has been encountered while adding task", Toast.LENGTH_SHORT);
            }
        });
    }


    public void UpdateTask(int pid, int tid, TaskClass t) {
        Call<Void> addTask = taskService.editTask(pid, tid, t);

        addTask.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddTask.this, response.toString(), Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    Toast.makeText(AddTask.this, "Task has been successfully edited!", Toast.LENGTH_SHORT).show();

                    Intent u = new Intent(AddTask.this, Users.class);
                    startActivity(u);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(AddTask.this, "An error has been encountered while editing task", Toast.LENGTH_SHORT);
            }
        });
    }

}
