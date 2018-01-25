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
import com.mjm.workflowkami.model_classes.TaskClass;

import java.util.List;

/**
 * Created by DC on 27/09/2017.
 */

public class CountClassAdapter extends ArrayAdapter<TaskClass> {
    private Context context;
    private List<TaskClass> tasks;

    public CountClassAdapter(Context context, List<TaskClass> tasks) {
        super(context, R.layout.list_item_count, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_count, parent, false);
        if (tasks != null) {
            TextView txtTaskname = (TextView) view.findViewById(R.id.taskCount);
            txtTaskname.setText(tasks.get(position).getCount());

        }

        return view;

    }
}
