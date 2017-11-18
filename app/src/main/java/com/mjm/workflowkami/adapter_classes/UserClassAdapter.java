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
import com.mjm.workflowkami.add_classes.AddUserr;
import com.mjm.workflowkami.model_classes.UserClass;

import java.util.List;

/**
 * Created by DC on 27/09/2017.
 */

public class UserClassAdapter extends ArrayAdapter<UserClass> {

    private Context context;
    private List<UserClass> users;
    public UserClassAdapter(Context context, List<UserClass> users) {
        super(context, R.layout.list_item_user, users);
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_user, parent, false);
        TextView txtLastname = (TextView) view.findViewById(R.id.lastname);
        txtLastname.setText(users.get(position).getLastname());

        TextView txtFirstname = (TextView) view.findViewById(R.id.firstname);
        txtFirstname.setText(users.get(position).getFirstname());

        Button btnEdit = (Button) view.findViewById(R.id.btnEditUser);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserClass userClass = users.get(position);

                Intent i = new Intent(context, AddUserr.class);

                i.putExtra("users", userClass);
                context.startActivity(i);
            }
        });
        return view;
    }
}
