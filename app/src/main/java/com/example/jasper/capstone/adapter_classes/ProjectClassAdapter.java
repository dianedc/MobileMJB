package com.example.jasper.capstone.adapter_classes;

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
import android.widget.Toast;

import com.example.jasper.capstone.R;
import com.example.jasper.capstone.add_classes.AddProject;
import com.example.jasper.capstone.impl_classes.Tasks;
import com.example.jasper.capstone.model_classes.ProjectClass;

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
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_projects, parent, false);
        TextView txtProjname = (TextView) view.findViewById(R.id.projname);
        txtProjname.setText(projects.get(position).getProjectname());
        TextView txtProjectManager = (TextView) view.findViewById(R.id.projmanager);
        txtProjectManager.setText(projects.get(position).getProjectmanager().getLastname());

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
                Intent i = new Intent(context, Tasks.class);

                i.putExtra("projects", projectClass);
                context.startActivity(i);
            }
        });
        return view;

    }
}

