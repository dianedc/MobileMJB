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
import com.mjm.workflowkami.model_classes.ProjectTeamClass;
import com.mjm.workflowkami.model_classes.WorkerClass;
import com.mjm.workflowkami.service_classes.ProjectService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 26 Nov 2017.
 */

public class AttendanceClassAdapter extends ArrayAdapter<ProjectTeamClass> {
    private Context context;
    private List<ProjectTeamClass> attendance;

    private ProjectService attendanceService = API.getInstance().getProjectService();
    private WorkerClass w = new WorkerClass();
    private int projid, projteamid;

    public AttendanceClassAdapter(Context context, List<ProjectTeamClass> attendance) {
        super(context, R.layout.list_item_attendance, attendance);
        this.context = context;
        this.attendance = attendance;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_attendance, parent, false);
        projid = attendance.get(position).getProjectsprojID().getProjID();
        projteamid = attendance.get(position).getProjteamID();
        TextView txtworkersfirstname = (TextView) view.findViewById(R.id.workerfirstnamea);
         txtworkersfirstname.setText(attendance.get(position).getWorkersworkersID().getWorkersfirstname()
                + " " + attendance.get(position).getWorkersworkersID().getWorkerslastname());

//        txtworkersfirstname.setText(String.valueOf(projteamid));
        TextView txtworkersrole = (TextView) view.findViewById(R.id.workersrolea);
        txtworkersrole.setText(attendance.get(position).getWorkersworkersID().getWorkersrole());

        final Button btnTimeOut = (Button) view.findViewById(R.id.btnTimeOut);
        btnTimeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<Void> timeOut = attendanceService.workerTimeOut(attendance.get(position).getProjectsprojID().getProjID(),
                        attendance.get(position).getProjteamID());
                timeOut.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
//                            Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String format = s.format(new Date());

                            Toast.makeText(context, "Attendance Out at: " + format, Toast.LENGTH_LONG).show();
                            btnTimeOut.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
//                        Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
                        t.printStackTrace();
                    }
                });
            }
        });

        return view;

    }
}
