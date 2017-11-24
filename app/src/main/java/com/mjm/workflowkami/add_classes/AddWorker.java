package com.mjm.workflowkami.add_classes;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.mjm.workflowkami.model_classes.WorkerClass;
import com.mjm.workflowkami.service_classes.WorkerService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddWorker extends AppCompatActivity {

    private EditText workerID, workerLName, workerFName;
    private Spinner workerRole;
    private Button btnSaveWorker;
    private WorkerClass wor = new WorkerClass();
    private WorkerClass wIntent;
    private WorkerService ws = API.getInstance().getWorkerService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAddWorker);
        toolbar.setTitle("Worker");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        wIntent = (WorkerClass) intent.getSerializableExtra("workers");

        workerID = (EditText) findViewById(R.id.workerID);
        workerFName = (EditText) findViewById(R.id.workerfname);
        workerLName = (EditText) findViewById(R.id.workerlname);
        workerRole = (Spinner) findViewById(R.id.workerrole);
        btnSaveWorker = (Button) findViewById(R.id.btnSaveWorker);

        if (wIntent != null) {
            workerID.setText(String.valueOf(wIntent.getWorkersID()));
            workerFName.setText(wIntent.getWorkersfirstname());
            workerLName.setText(wIntent.getWorkerslastname());
            for (int i = 0; i < workerRole.getCount(); i++) {
                if (workerRole.getItemAtPosition(i).toString().equals(wIntent.getWorkersrole())) {
                    workerRole.setSelection(i);
                    break;
                }
            }
        }

        btnSaveWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!workerID.getText().toString().matches("")) {
                    wor = new WorkerClass(Integer.valueOf(workerID.getText().toString()),
                            workerFName.getText().toString().trim(),
                            workerLName.getText().toString().trim(),
                            workerRole.getSelectedItem().toString());
                    EditWorker(wIntent.getWorkersID(), wor);
                } else {
                    wor = new WorkerClass(workerFName.getText().toString().trim(),
                            workerLName.getText().toString().trim(),
                            workerRole.getSelectedItem().toString());
                    SaveWorker(wor);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_worker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back_worker:
                startActivity(new Intent(this, Worker.class));
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
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddWorker.this);
                    alertDialogBuilder.setMessage("Worker has been successfully added!");
                    alertDialogBuilder.setCancelable(true);
                    alertDialogBuilder.show();

                    Intent u = new Intent(AddWorker.this, Worker.class);
                    startActivity(u);
                }
            }

            @Override
            public void onFailure(Call<WorkerClass> call, Throwable t) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddWorker.this);
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
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddWorker.this);
                    alertDialogBuilder.setMessage("Worker has been successfully edited!");
                    alertDialogBuilder.setCancelable(true);
                    alertDialogBuilder.show();

                    Intent u = new Intent(AddWorker.this, Worker.class);
                    startActivity(u);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddWorker.this);
                alertDialogBuilder.setMessage("An error has been encountered while adding worker");
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.show();
            }
        });

    }

    public void onClickCancel(View v) {
        Intent cancel = new Intent(AddWorker.this, Worker.class);
        startActivity(cancel);

    }
}
