package com.mjm.workflowkami.adapter_classes;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.add_classes.AddMoveWorker;
import com.mjm.workflowkami.add_classes.AddTask;
import com.mjm.workflowkami.impl_classes.TaskAssigned;
import com.mjm.workflowkami.impl_classes.TaskAssigned;
import com.mjm.workflowkami.model_classes.TaskClass;
import com.mjm.workflowkami.service_classes.TaskService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DC on 27/09/2017.
 */

public class TaskClassAdapter extends ArrayAdapter<TaskClass> {
    private Context context;
    private List<TaskClass> tasks;
    private ProgressDialog progressDialog;
    private TaskService taskService = API.getInstance().getTaskService();
    private Button btnCompleteTask, btnCompleteTaskDone, btnStartTask, btnStartTaskDone, btnEditTask, btnAssignWorker;

    public TaskClassAdapter(Context context, List<TaskClass> tasks) {
        super(context, R.layout.list_item_tasks, tasks);
        this.context = context;
        this.tasks = tasks;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_tasks, parent, false);
        btnCompleteTask = (Button) view.findViewById(R.id.btnCompleteTask);
        btnCompleteTaskDone = (Button) view.findViewById(R.id.btnCompleteTaskDone);
        btnStartTask = (Button) view.findViewById(R.id.btnStartTask);
        btnStartTaskDone = (Button) view.findViewById(R.id.btnStartTaskDone);
        btnAssignWorker = (Button) view.findViewById(R.id.btnAssignWorker);
//        initialState();
        try {
            if (tasks != null) {
                TextView txtTaskname = (TextView) view.findViewById(R.id.taskName);
                txtTaskname.setText(tasks.get(position).getTaskname());

                TextView txtTaskDescription = (TextView) view.findViewById(R.id.taskStatus);
                txtTaskDescription.setText(tasks.get(position).getTaskstatus());

                TextView txtTaskPhase = (TextView) view.findViewById(R.id.taskPhase);
                txtTaskPhase.setText(tasks.get(position).getTaskphase());

                if (tasks.get(position).getTaskstatus().equals("Completed")) {
                    btnCompleteTask.setVisibility(View.INVISIBLE);
                    btnCompleteTaskDone.setVisibility(View.VISIBLE);

                    btnStartTask.setVisibility(View.INVISIBLE);
                    btnStartTaskDone.setVisibility(View.VISIBLE);

//                    btnCompleteTask.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Toast.makeText(context, "Task has been completed", Toast.LENGTH_LONG).show();
//                        }
//                    });
//                    btnStartTask.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Toast.makeText(context, "Task has been completed", Toast.LENGTH_LONG).show();
//                        }
//                    });

                }

                if (tasks.get(position).getTaskstatus().equals("Active")) {
                    btnStartTask.setVisibility(View.INVISIBLE);
                    btnStartTaskDone.setVisibility(View.VISIBLE);

//                    btnCompleteTask.setEnabled(true);

//                    btnStartTask.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Toast.makeText(context, "Task is already active", Toast.LENGTH_LONG).show();
//                        }
//                    });

                }

                if (tasks.get(position).getTaskstatus().equals("Waiting")) {
//                    btnStartTask.setEnabled(true);
                    btnCompleteTask.setVisibility(View.INVISIBLE);
                    btnStartTask.setVisibility(View.VISIBLE);



//                    btnCompleteTask.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Toast.makeText(context, "Task should be started", Toast.LENGTH_LONG).show();
//                        }
//                    });

                }
            }

        } catch (Exception eo) {
            eo.printStackTrace();
        }
        btnEditTask = (Button) view.findViewById(R.id.btnEditTask);
        btnEditTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskClass taskClass = tasks.get(position);

                Intent i = new Intent(context, AddTask.class);

                i.putExtra("tasks", taskClass);
                context.startActivity(i);
            }
        });

        btnCompleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                mBuilder.setTitle("Complete Task?");
                mBuilder.setMessage("Set the selected task to a completed state.");
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Call<Void> compTask = taskService.completeTask(tasks.get(position).getTaskID());
                        compTask.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(context, "Task has been successfully completed!", Toast.LENGTH_LONG).show();
                                    btnCompleteTask.setVisibility(View.INVISIBLE);
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                mBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = mBuilder.create();
                alert.show();
            }
        });

        btnStartTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                mBuilder.setTitle("Start Task?");
                mBuilder.setMessage("Set the selected task to an active state.");
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Call<Void> compTask = taskService.startTask(tasks.get(position).getTaskID());
                        compTask.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(context, "Task has been successfully started!", Toast.LENGTH_LONG).show();
                                    btnStartTask.setVisibility(View.INVISIBLE);
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                mBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = mBuilder.create();
                alert.show();
            }
        });

        btnAssignWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskClass taskClass = tasks.get(position);

                Intent i = new Intent(context, TaskAssigned.class);

                i.putExtra("assignworker", taskClass);
                context.startActivity(i);
            }
        });

        return view;

    }

    private void initialState() {

        btnCompleteTask.setEnabled(true);
        btnStartTask.setEnabled(true);
        btnEditTask.setEnabled(true);

    }
}
