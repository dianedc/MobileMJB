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
import android.widget.Toast;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.model_classes.ProjectTeamClass;
import com.mjm.workflowkami.model_classes.UserClass;
import com.mjm.workflowkami.model_classes.WorkerClass;
import com.mjm.workflowkami.service_classes.AttendanceService;
import com.mjm.workflowkami.service_classes.ProjectService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 21 Nov 2017.
 */

public class WorkerClassAdapter extends ArrayAdapter<ProjectTeamClass>{
    private Context context;
    private List<ProjectTeamClass> workers;
    private ServiceImpl serviceImpl = new ServiceImpl();
    private String TAG = ServiceImpl.class.getSimpleName();
    private ProjectTeamClass projteamIntent = new ProjectTeamClass();
    public List<ProjectTeamClass> pTeamList = new ArrayList<ProjectTeamClass>();
    private ProjectService attendanceService = API.getInstance().getProjectService();
    private WorkerClass w = new WorkerClass();
    private int projid, projteamid;

    public WorkerClassAdapter(Context context, List<ProjectTeamClass> workers) {
        super(context, R.layout.list_item_workers, workers);
        this.context = context;
        this.workers = workers;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_workers, parent, false);

        projid = workers.get(position).getProjectsprojID().getProjID();
        projteamid = workers.get(position).getProjteamID();
        TextView txtworkersfirstname = (TextView) view.findViewById(R.id.workerfirstnamew);
        txtworkersfirstname.setText(workers.get(position).getWorkersworkersID().getWorkersfirstname()
                + " " + workers.get(position).getWorkersworkersID().getWorkerslastname());
//        txtworkersfirstname.setText(workers.get(position).getWorkersworkersID().getWorkersrole());

        TextView txtworkersrole = (TextView) view.findViewById(R.id.workersrolew);
        txtworkersrole.setText(String.valueOf(workers.get(position).getWorkersworkersID().getWorkersrole()));


       Button btnTimeIn = (Button) view.findViewById(R.id.btnTimeIn);
        btnTimeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, String.valueOf(workers.get(position).getWorkersworkersID().getWorkersID()), Toast.LENGTH_LONG).show();

                Call<Void> timeIn = attendanceService.workerTimeIn(workers.get(position).getProjectsprojID().getProjID(),
                        workers.get(position).getProjteamID());
                timeIn.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }

        });


        return view;

    }

}
