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
 * Created by Jasper on 21 Oct 2017.
 */

public class ProjectSpinnerMoveAdapter extends ArrayAdapter<ProjectClass> {
    private Context context;
    private List<ProjectClass> values;

    public ProjectSpinnerMoveAdapter(@NonNull Context context, List<ProjectClass> values) {
        super(context, R.layout.spinner_list, values);
        this.context = context;
        this.values = values;
    }

    @Nullable
    @Override
    public ProjectClass getItem(int position) {
        return values.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customSpinnerView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customSpinnerView(position, convertView, parent);
    }

    public View customSpinnerView(final int position, @Nullable View myView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.spinner_list, parent, false);
        TextView textView_id = (TextView) customView.findViewById(R.id.spinner_item_id);
        TextView textView_lastname = (TextView) customView.findViewById(R.id.spinner_item_lastname);

        textView_id.setText(String.valueOf(values.get(position).getProjID()));
        textView_lastname.setText(values.get(position).getProjname());
        return customView;

    }

}
