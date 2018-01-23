package com.mjm.workflowkami.adapter_classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mjm.workflowkami.R;
import com.mjm.workflowkami.model_classes.ProjectTeamClass;

import java.util.List;

/**
 * Created by admin on 13 Nov 2017.
 */

public class ProjectTeamAdapter extends ArrayAdapter<ProjectTeamClass> {
    private Context context;
    private List<ProjectTeamClass> pteams;

    public ProjectTeamAdapter(Context context, List<ProjectTeamClass> pteams) {
        super(context, R.layout.list_item_pteams, pteams);
        this.context = context;
        this.pteams = pteams;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_pteams, parent, false);
//        TextView txtProjID = (TextView) view.findViewById(R.id.userID);
//        txtProjID.setText(projects.get(position).getProjectID().toString());
        TextView txtProjteamID = (TextView) view.findViewById(R.id.projectTeam_id);
        txtProjteamID.setText(String.valueOf(pteams.get(position).getProjteamID()));
        TextView txtProjUserRole = (TextView) view.findViewById(R.id.workersID);
        txtProjUserRole.setText(pteams.get(position).getWorkersworkersID().getWorkersfirstname());

//        Button btnEdit = (Button) view.findViewById(R.id.btnEditPteam);
//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ProjectTeamClass projectTeamClass = pteams.get(position);
//
//                Intent i = new Intent(context, AddProjectTeam.class);
//
//                i.putExtra("pteams", projectTeamClass);
//                context.startActivity(i);
//            }
//        });

        return view;

    }
}
