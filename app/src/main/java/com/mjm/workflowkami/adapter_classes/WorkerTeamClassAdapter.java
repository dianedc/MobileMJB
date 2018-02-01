package com.mjm.workflowkami.adapter_classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.mjm.workflowkami.R;
import com.mjm.workflowkami.model_classes.ProjectTeamClass;

import java.util.List;

/**
 * Created by admin on 21 Nov 2017.
 */

public class WorkerTeamClassAdapter extends ArrayAdapter<ProjectTeamClass>{
    private Context context;
    private List<ProjectTeamClass> workers;

    public WorkerTeamClassAdapter(Context context, List<ProjectTeamClass> workers) {
        super(context, R.layout.list_item_pteamworker, workers);
        this.context = context;
        this.workers = workers;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_pteamworker, parent, false);

        TextView txtworkersfirstname = (TextView) view.findViewById(R.id.worker_team_id);
        TextView workersID = (TextView) view.findViewById(R.id.workersT_id);
        txtworkersfirstname.setText(workers.get(position).getWorkersworkersID().getWorkersfirstname() + " " +
                                    workers.get(position).getWorkersworkersID().getWorkerslastname());
        workersID.setText(workers.get(position).getWorkersworkersID().getWorkersrole());


        return view;

    }

}
