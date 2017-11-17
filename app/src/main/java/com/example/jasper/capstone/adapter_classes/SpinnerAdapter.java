package com.example.jasper.capstone.adapter_classes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jasper.capstone.R;
import com.example.jasper.capstone.add_classes.AddTask;
import com.example.jasper.capstone.model_classes.UserClass;

import java.util.List;

/**
 * Created by Jasper on 21 Oct 2017.
 */

public class SpinnerAdapter extends ArrayAdapter<UserClass> {
    private Context context;
    private List<UserClass> values;

    public SpinnerAdapter(@NonNull Context context, @LayoutRes int textViewResourceId, List<UserClass> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    @Nullable
    @Override
    public UserClass getItem(int position) {
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

        textView_id.setText(String.valueOf(values.get(position).getUserID()));
        textView_lastname.setText(values.get(position).getLastname());
        return customView;

    }

}
