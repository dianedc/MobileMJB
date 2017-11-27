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
import com.mjm.workflowkami.add_classes.AddTask;
import com.mjm.workflowkami.model_classes.TaskClass;
import com.mjm.workflowkami.service_classes.TaskService;

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

//    private class CompleteTask extends AsyncTask<String, String, String> {
//
//        @Override
//        protected void onPreExecute() {
//            progressDialog = new ProgressDialog(context);
//            progressDialog.setMessage("Loading. Please wait... ");
//            progressDialog.show();
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            Call<Void> compTask = taskService.completeTask())
//            return "Updated";
//        }
//        @Override
//        protected void onPostExecute(String workerClassResponseEntity) {
//            progressDialog.dismiss();
//        }
//    }
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
//        TextView txtID = (TextView) view.findViewById(R.id.userID);
//        txtID.setText(tasks.get(position).getTaskID().toString());
        try {
            if (tasks != null) {
                TextView txtTaskname = (TextView) view.findViewById(R.id.taskName);
                txtTaskname.setText(tasks.get(position).getTaskname());

                TextView txtTaskDescription = (TextView) view.findViewById(R.id.taskStatus);
                txtTaskDescription.setText(tasks.get(position).getTaskstatus());
            }
            if (tasks.get(position).getTaskstatus() == "Completed") {
                Button btnCompleteTask = (Button) view.findViewById(R.id.btnCompleteTask);
                btnCompleteTask.setVisibility(View.INVISIBLE);
            }
        } catch (Exception eo) {
            eo.printStackTrace();
        }
        Button btnEditTask = (Button) view.findViewById(R.id.btnEditTask);
        btnEditTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskClass taskClass = tasks.get(position);

                Intent i = new Intent(context, AddTask.class);

                i.putExtra("tasks", taskClass);
                context.startActivity(i);
            }
        });
//        final String uri = "http://servicemjm-env.ap-southeast-1.elasticbeanstalk.com/{proj_id}/task/complete/{task_id}";
        final Button btnCompleteTask = (Button) view.findViewById(R.id.btnCompleteTask);
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
//                        Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
                        Call<Void> compTask = taskService.completeTask(tasks.get(position).getProjectID().getProjID(),
                                tasks.get(position).getTaskID());
                        compTask.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()){
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
//                        Toast.makeText(context, "CANCEL", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alert = mBuilder.create();
//                alert.setTitle("Complete Task?");
                alert.show();
            }
        });

        return view;

    }
}
