package com.mjm.workflowkami.adapter_classes;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.model_classes.WorkerClass;
import com.mjm.workflowkami.service_classes.TaskService;
import com.mjm.workflowkami.service_classes.WorkerService;

import java.util.List;

/**
 * Created by DC on 01/02/2018.
 */

public class MoveWorkerAdapter extends ArrayAdapter<WorkerClass> {

    private Context context;
    private List<WorkerClass> workers;
    private ProgressDialog progressDialog;
    private WorkerService taskService = API.getInstance().getWorkerService();
    public MoveWorkerAdapter(Context context, List<WorkerClass> workers) {
        super(context, R.layout.list_item_pteams, workers);
        this.context = context;
        this.workers = workers;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_pteams, parent, false);
        TextView workerName = (TextView) view.findViewById(R.id.projectTeam_id);
        workerName.setText(workers.get(position).getWorkersfirstname() + " " +workers.get(position).getWorkersfirstname());


        return view;

    }
}
