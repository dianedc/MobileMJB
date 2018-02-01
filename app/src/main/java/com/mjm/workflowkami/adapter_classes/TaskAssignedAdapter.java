package com.mjm.workflowkami.adapter_classes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.mjm.workflowkami.R;
import com.mjm.workflowkami.add_classes.AddMoveWorker;
import com.mjm.workflowkami.add_classes.AddProject;
import com.mjm.workflowkami.impl_classes.Workflow;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.TaskAssignedClass;

import java.util.List;

/**
 * Created by Jasper on 4 Oct 2017.
 */

public class TaskAssignedAdapter extends ArrayAdapter<TaskAssignedClass> {
    private Context context;
    private List<TaskAssignedClass> workers;

    public TaskAssignedAdapter(Context context, List<TaskAssignedClass> workers) {
        super(context, R.layout.list_item_task_assigned, workers);
        this.context = context;
        this.workers = workers;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_task_assigned, parent, false);
        TextView txtProjname = (TextView) view.findViewById(R.id.task_worker_name);
        txtProjname.setText(workers.get(position).getAssignedID().getWorkersworkersID().getWorkersfirstname() +" "+
                            workers.get(position).getAssignedID().getWorkersworkersID().getWorkerslastname());
        TextView txtProjectManager = (TextView) view.findViewById(R.id.task_assigned_role);
        txtProjectManager.setText(workers.get(position).getAssignedID().getWorkersworkersID().getWorkersrole());

        Button btnMoveWorker = (Button) view.findViewById(R.id.btnMoveWorker);
        btnMoveWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskAssignedClass taskAssignedClass = workers.get(position);
                Intent i = new Intent(context, AddMoveWorker.class);

                i.putExtra("taskassigned", taskAssignedClass);
                context.startActivity(i);
            }
        });

        return view;

    }
}

