package com.mjm.workflowkami.adapter_classes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mjm.workflowkami.R;
import com.mjm.workflowkami.add_classes.AddTask;
import com.mjm.workflowkami.add_classes.AddWorker;
import com.mjm.workflowkami.model_classes.UserClass;
import com.mjm.workflowkami.model_classes.WorkerClass;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by admin on 21 Nov 2017.
 */

public class WorkerClassAdapter extends ArrayAdapter<WorkerClass>{
    private Context context;
    private List<WorkerClass> workers;

    public WorkerClassAdapter(Context context, List<WorkerClass> workers) {
        super(context, R.layout.list_item_workers, workers);
        this.context = context;
        this.workers = workers;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_workers, parent, false);
//        TextView txtID = (TextView) view.findViewById(R.id.userID);
//        txtID.setText(tasks.get(position).getTaskID().toString());
        TextView txtworkersfirstname = (TextView) view.findViewById(R.id.workerfirstname);
        txtworkersfirstname.setText(workers.get(position).getWorkersfirstname());
        TextView txtworkersrole = (TextView) view.findViewById(R.id.workersrole);
        txtworkersrole.setText(workers.get(position).getWorkersrole());

        Button btnEditWorker = (Button) view.findViewById(R.id.btnEditWorker);
        btnEditWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkerClass workerClass = workers.get(position);

                Intent i = new Intent(context, AddWorker.class);

                i.putExtra("workers", workerClass);
                context.startActivity(i);
            }
        });

        return view;

    }
}
