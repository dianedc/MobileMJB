package com.mjm.workflowkami.adapter_classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mjm.workflowkami.R;
import com.mjm.workflowkami.model_classes.ProjectTeamClass;

import java.util.List;

/**
 * Created by admin on 21 Nov 2017.
 */

public class ProjTeamClassAdapter extends ArrayAdapter<ProjectTeamClass>{
    private Context context;
    private List<ProjectTeamClass> workers;

    public ProjTeamClassAdapter(Context context, List<ProjectTeamClass> workers) {
        super(context, R.layout.list_item_pteams, workers);
        this.context = context;
        this.workers = workers;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_pteams, parent, false);

//        projid = workers.get(position).getProjectsprojID().getProjID();
//        projteamid = workers.get(position).getProjteamID();
        TextView txtworkersfirstname = (TextView) view.findViewById(R.id.projectTeam_id);
//        txtworkersfirstname.setText("TEXT");
//        Toast.makeText(context, workers.get(position).getUserID().getFirstname(), Toast.LENGTH_LONG);
        txtworkersfirstname.setText(workers.get(position).getUserID().getFirstname() + " " +
        workers.get(position).getUserID().getLastname());
        TextView workersID = (TextView) view.findViewById(R.id.workersID);
        workersID.setVisibility(View.GONE);
//        txtworkersfirstname.setText(String.valueOf(workers.get(position).getProjteamID()));
    
//        TextView txtworkersrole = (TextView) view.findViewById(R.id.workersrolew);
//        txtworkersrole.setText(String.valueOf(workers.get(position).));


//       Button btnTimeIn = (Button) view.findViewById(R.id.btnTimeIn);
//        btnTimeIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, String.valueOf(workers.get(position).getWorkersworkersID().getWorkersID()), Toast.LENGTH_LONG).show();
//
//                Call<Void> timeIn = attendanceService.workerTimeIn(workers.get(position).getProjectsprojID().getProjID(),
//                        workers.get(position).getProjteamID());
//                timeIn.enqueue(new Callback<Void>() {
//                    @Override
//                    public void onResponse(Call<Void> call, Response<Void> response) {
//                        if (response.isSuccessful()) {
//                            Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Void> call, Throwable t) {
//                        Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//
//        });


        return view;

    }

}
