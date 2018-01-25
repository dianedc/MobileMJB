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
import com.mjm.workflowkami.add_classes.AddProject;
import com.mjm.workflowkami.impl_classes.Workflow;
import com.mjm.workflowkami.model_classes.ProjectClass;

import java.util.List;

/**
 * Created by Jasper on 4 Oct 2017.
 */

public class ProjectClassAdapter extends ArrayAdapter<ProjectClass> {
    private Context context;
    private List<ProjectClass> projects;

    public ProjectClassAdapter(Context context, List<ProjectClass> projects) {
        super(context, R.layout.list_item_projects, projects);
        this.context = context;
        this.projects = projects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_projects, parent, false);
        TextView txtProjname = (TextView) view.findViewById(R.id.projname);
        txtProjname.setText(projects.get(position).getProjname());
        TextView txtProjectManager = (TextView) view.findViewById(R.id.projmanager);
        txtProjectManager.setText(projects.get(position).getProjmanager().getLastname());

        Button btnEdit = (Button) view.findViewById(R.id.btnEditProj);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProjectClass projectClass = projects.get(position);
                Intent i = new Intent(context, AddProject.class);

                i.putExtra("projects", projectClass);
                context.startActivity(i);
            }
        });

        Button btnView = (Button) view.findViewById(R.id.btnViewProj);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProjectClass projectClass = projects.get(position);
                Intent i = new Intent(context, Workflow.class);

//                i.setClassName(context, "com.mjm.workflowkami.add_classes.AddTask");
                i.putExtra("projects", projectClass);
                context.startActivity(i);
            }
        });
        return view;

    }
}

