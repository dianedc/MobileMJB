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
import com.mjm.workflowkami.model_classes.ProjectClass;

import java.util.List;

/**
 * Created by DC on 27/09/2017.
 */

public class ProjCountClassAdapter extends ArrayAdapter<ProjectClass> {
    private Context context;
    private List<ProjectClass> tasks;

    public ProjCountClassAdapter(Context context, List<ProjectClass> tasks) {
        super(context, R.layout.list_item_count, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_count, parent, false);
//        TextView txtID = (TextView) view.findViewById(R.id.userID);
//        txtID.setText(tasks.get(position).getTaskID().toString());
        if (tasks != null) {
            TextView txtTaskname = (TextView) view.findViewById(R.id.taskCount);
            txtTaskname.setText(tasks.get(position).getCount());

//            TextView txtTaskDescription = (TextView) view.findViewById(R.id.taskDescription);
//            txtTaskDescription.setText(tasks.get(position).getTaskdesc());
        }

//        Button btnEditTask = (Button) view.findViewById(R.id.btnEditTask);
//        btnEditTask.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TaskClass taskClass = tasks.get(position);
//
//                Intent i = new Intent(context, AddTask.class);
//
//                i.putExtra("tasks", taskClass);
//                context.startActivity(i);
//            }
//        });

        return view;

    }
}
