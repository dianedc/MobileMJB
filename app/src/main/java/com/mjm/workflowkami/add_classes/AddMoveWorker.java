package com.mjm.workflowkami.add_classes;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.Fragments.Worker;
import com.mjm.workflowkami.LoaderAsync;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.adapter_classes.MoveWorkerAdapter;
import com.mjm.workflowkami.adapter_classes.ProjectSpinnerAdapter;
import com.mjm.workflowkami.adapter_classes.ProjectSpinnerMoveAdapter;
import com.mjm.workflowkami.adapter_classes.TaskSpinnerAdapter;
import com.mjm.workflowkami.impl_classes.TaskAssigned;
import com.mjm.workflowkami.impl_classes.Workers;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.TaskAssignedClass;
import com.mjm.workflowkami.model_classes.TaskClass;
import com.mjm.workflowkami.model_classes.WorkerClass;
import com.mjm.workflowkami.service_classes.TaskAssignedService;
import com.mjm.workflowkami.service_classes.WorkerService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMoveWorker extends LoaderAsync
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText task_assigned_id, task_assigned_pteam_id;
    private Spinner task_assigned_proj, task_assigned_task;
    private Button btnSaveTransfer;
    private TaskAssignedClass taskAssignedClass = new TaskAssignedClass();
    private TaskAssignedClass tIntent;
    private TaskAssignedService taskAssignedService = API.getInstance().getTaskAssignedService();
    private ServiceImpl serviceImpl = new ServiceImpl();
    private ProjectClass projSelected;
    private TaskClass taskSelected;
    private Button btnLoadTasks;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    public class MoveWorkerShowProjects extends AsyncTask<String, Void, List<ProjectClass>> {

        List<ProjectClass> cached;
        @Override
        protected void onPreExecute() {
            if (cached == null) {
                showLoadingDialog();
            } else {
                super.onPostExecute(cached);
            }
        }

        @Override
        protected List<ProjectClass> doInBackground(String... strings) {

            do {
                serviceImpl.GetAllActiveProjects();
                try  {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (serviceImpl.projectsList ==null);

            return serviceImpl.projectsList;
        }

        @Override
        protected void onPostExecute(List<ProjectClass> projectClassResponseEntity) {
            dismissProgressDialog();
            ProjectSpinnerMoveAdapter adapter = new ProjectSpinnerMoveAdapter(AddMoveWorker.this, projectClassResponseEntity);
            task_assigned_proj.setAdapter(adapter);
        }
    }

    public class MoveWorkerShowTask extends AsyncTask<Integer, Void, List<TaskClass>> {

        List<TaskClass> cached;
        @Override
        protected void onPreExecute() {
            if (cached == null) {
                showLoadingDialog();
            } else {
                super.onPostExecute(cached);
            }
        }

        @Override
        protected List<TaskClass> doInBackground(Integer... strings) {



            do {
                if (projSelected != null) {
                    serviceImpl.GetTaskByProjId(strings[0]);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } while (serviceImpl.tasksList ==null);

            return serviceImpl.tasksList;
        }

        @Override
        protected void onPostExecute(List<TaskClass> taskClassResponseEntity) {
            dismissProgressDialog();
            task_assigned_task.setAdapter(new TaskSpinnerAdapter(AddMoveWorker.this, taskClassResponseEntity));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_move_worker);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add_move_worker);
        toolbar.setTitle("Move Worker");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        projSelected = new ProjectClass();

        Intent intent = getIntent();
        tIntent = (TaskAssignedClass) intent.getSerializableExtra("taskassigned");

        task_assigned_id = (EditText) findViewById(R.id.task_assigned_id);
        task_assigned_pteam_id = (EditText) findViewById(R.id.task_assigned_pteam_id);
        task_assigned_proj = (Spinner) findViewById(R.id.task_assigned_proj);
        task_assigned_task = (Spinner) findViewById(R.id.task_assigned_task);
        btnLoadTasks = (Button) findViewById(R.id.btnLoadTasks);
        btnSaveTransfer = (Button) findViewById(R.id.btnSaveTransfer);
        new MoveWorkerShowProjects().execute();

        if (tIntent != null) {
            task_assigned_id.setText(String.valueOf(tIntent.getAssignedID()));
            task_assigned_pteam_id.setText(String.valueOf(tIntent.getAssignedID().getProjteamID()));
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

        projSelected = (ProjectClass) task_assigned_proj.getSelectedItem();
//        final int projID = projSelected.getProjID();

        btnLoadTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(AddMoveWorker.this, String.valueOf(projID), Toast.LENGTH_LONG).show();
                new MoveWorkerShowTask().execute(projSelected.getProjID());
            }
        });
//        new MoveWorkerShowTask().execute();

        btnSaveTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddMoveWorker.this, projSelected.getProjname(), Toast.LENGTH_LONG).show();
//                if (!task_assigned_id.getText().toString().matches("")) {
////                    taskAssignedClass = new TaskAssignedClass(tIntent.getTaskassignedID(),
////                            (ProjectClass) task_assigned_proj.getSelectedItem(),
////                            (TaskClass) task_assigned_task.getSelectedItem(),
////                            tIntent.getAssignedID());
////                    EditWorker(tIntent.getTaskassignedID(), taskAssignedClass);
//
//                    Toast.makeText(AddMoveWorker.this, "Content Unavailable", Toast.LENGTH_LONG).show();
//                } else {
//                    taskAssignedClass = new TaskAssignedClass(tIntent.getTaskassignedID(),
//                            projSelected,
//                            (TaskClass) task_assigned_task.getSelectedItem(),
//                            tIntent.getAssignedID());
//                    SaveMoveWorker(tIntent.getProjectID().getProjID(), tIntent.getTaskID().getTaskID(), taskAssignedClass);
//                }
//                taskSelected = (TaskClass) task_assigned_task.getSelectedItem();



//                Toast.makeText(AddMoveWorker.this, String.valueOf(taskSelected.getTaskID()), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.add_worker, menu);
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

    public void SaveMoveWorker(int projid, int taskid, TaskAssignedClass ta){
        Call<TaskAssignedClass> addMoveWorker = taskAssignedService.addTaskAssigned(projid, taskid, ta);

        addMoveWorker.enqueue(new Callback<TaskAssignedClass>() {
            @Override
            public void onResponse(Call<TaskAssignedClass> call, Response<TaskAssignedClass> response) {
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
            public void onFailure(Call<TaskAssignedClass> call, Throwable t) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddMoveWorker.this);
                alertDialogBuilder.setMessage("An error has been encountered while moving worker");
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.show();
            }
        });
    }
//    public void EditWorker(int id, WorkerClass w) {
//        Call<Void> editWorker = ws.editWork(id, w);
//        editWorker.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (response.isSuccessful()) {
//                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddMoveWorker.this);
//                    alertDialogBuilder.setMessage("Worker has been successfully moved!");
//                    alertDialogBuilder.setCancelable(true);
//                    alertDialogBuilder.show();
//
//                    Intent u = new Intent(AddMoveWorker.this, Worker.class);
//                    startActivity(u);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddMoveWorker.this);
//                alertDialogBuilder.setMessage("An error has been encountered while moving worker");
//                alertDialogBuilder.setCancelable(true);
//                alertDialogBuilder.show();
//            }
//        });
//
//    }

    public void onClickCancel(View v) {
        AddMoveWorker.this.finish();

    }
}
