package com.mjm.workflowkami.add_classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.Fragments.Worker;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.impl_classes.Workers;
import com.mjm.workflowkami.model_classes.TaskAssignedClass;
import com.mjm.workflowkami.model_classes.WorkerClass;
import com.mjm.workflowkami.service_classes.WorkerService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMoveWorker extends AppCompatActivity {

    private EditText task_assigned_id, task_assigned_pteam_id;
    private Spinner task_assigned_proj, task_assigned_task;
    private Button btnSaveTransfer;
    private TaskAssignedClass taskAssignedClass = new TaskAssignedClass();
    private TaskAssignedClass tIntent;
    private WorkerService ws = API.getInstance().getWorkerService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_move_worker);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add_move_worker);
        toolbar.setTitle("Move Worker");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        tIntent = (TaskAssignedClass) intent.getSerializableExtra("taskassigned");

        task_assigned_id = (EditText) findViewById(R.id.task_assigned_id);
        task_assigned_pteam_id = (EditText) findViewById(R.id.task_assigned_pteam_id);
        task_assigned_proj = (Spinner) findViewById(R.id.task_assigned_proj);
        task_assigned_task = (Spinner) findViewById(R.id.task_assigned_task);
        btnSaveTransfer = (Button) findViewById(R.id.btnSaveTransfer);

        if (tIntent != null) {
            task_assigned_id.setText(String.valueOf(tIntent.getAssignedID()));
            task_assigned_pteam_id.setText(String.valueOf(tIntent.getAssignedID().getProjteamID()));
//            workerLName.setText(wIntent.getWorkerslastname());
            for (int i = 0; i < task_assigned_proj.getCount(); i++) {
                if (task_assigned_proj.getItemAtPosition(i).toString().equals(tIntent.getProjectID().getProjname())) {
                    task_assigned_proj.setSelection(i);
                    break;
                }
            }
            for (int i = 0; i < task_assigned_task.getCount(); i++) {
                if (task_assigned_task.getItemAtPosition(i).toString().equals(tIntent.getTaskID().getTaskID())) {
                    task_assigned_task.setSelection(i);
                    break;
                }
            }
        }

//        btnSaveTransfer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!task_assigned_id.getText().toString().matches("")) {
//                    taskAssignedClass = new TaskAssignedClass(tIntent.getTaskassignedID(),
//                            task_assigned_proj.getSelectedItem(),
//                            task_assigned_task.getSelectedItem(),
//                            tIntent.getAssignedID());
//                    EditWorker(wIntent.getWorkersID(), wor);
//                } else {
//                    wor = new WorkerClass(workerFName.getText().toString().trim(),
//                            workerLName.getText().toString().trim(),
//                            workerRole.getSelectedItem().toString());
//                    SaveWorker(wor);
//                }
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.add_worker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back_worker:
                startActivity(new Intent(this, Workers.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void SaveWorker(WorkerClass w){
        Call<WorkerClass> addWorker = ws.addWorker(w);

        addWorker.enqueue(new Callback<WorkerClass>() {
            @Override
            public void onResponse(Call<WorkerClass> call, Response<WorkerClass> response) {
                if (response.isSuccessful()) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddMoveWorker.this);
                    alertDialogBuilder.setMessage("Worker has been successfully added!");
                    alertDialogBuilder.setCancelable(true);
                    alertDialogBuilder.show();

                    Intent u = new Intent(AddMoveWorker.this, Worker.class);
                    startActivity(u);
                }
            }

            @Override
            public void onFailure(Call<WorkerClass> call, Throwable t) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddMoveWorker.this);
                alertDialogBuilder.setMessage("An error has been encountered while adding worker");
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.show();
            }
        });
    }
    public void EditWorker(int id, WorkerClass w) {
        Call<Void> editWorker = ws.editWork(id, w);
        editWorker.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddMoveWorker.this);
                    alertDialogBuilder.setMessage("Worker has been successfully edited!");
                    alertDialogBuilder.setCancelable(true);
                    alertDialogBuilder.show();

                    Intent u = new Intent(AddMoveWorker.this, Worker.class);
                    startActivity(u);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddMoveWorker.this);
                alertDialogBuilder.setMessage("An error has been encountered while adding worker");
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.show();
            }
        });

    }

    public void onClickCancel(View v) {
        Intent cancel = new Intent(AddMoveWorker.this, Worker.class);
        startActivity(cancel);

    }
}
