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
import com.mjm.workflowkami.add_classes.AddPRequest;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestClass;
import java.util.List;
/**
 * Created by DC on 31/10/2017.
 */

public class PurchaseRequestAdapter extends ArrayAdapter<PurchaseRequestClass> {

    private Context context;
    private List<PurchaseRequestClass> preqs;

    public PurchaseRequestAdapter(@NonNull Context context, List<PurchaseRequestClass> preqs) {
        super(context, R.layout.list_item_prequest, preqs);
        this.context = context;
        this.preqs = preqs;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_prequest, parent, false);

        TextView txtProjID = (TextView) view.findViewById(R.id.preqID);
        if(preqs.get(position).getPreqstatus() == true && preqs.get(position).getPreqstatus() != null) {
            txtProjID.setText("Approved");
        } else if (preqs.get(position).getPreqstatus() == null){
            txtProjID.setText("Pending");
        }

        TextView txtProjName = (TextView) view.findViewById(R.id.proj_name);
        txtProjName.setText(String.valueOf(preqs.get(position).getProjectID().getProjname()));

        Button btnEdit = (Button) view.findViewById(R.id.btnEditPreq);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PurchaseRequestClass purchaseRequestClass = preqs.get(position);
                ProjectClass proj = preqs.get(position).getProjectID();

                Intent i = new Intent(context, AddPRequest.class);

                i.putExtra("preqs", purchaseRequestClass);
                i.putExtra("projs", proj);
                context.startActivity(i);
            }
        });
//        TextView txtofficeengr = (TextView) view.findViewById(R.id.firstname);
//        txtofficeengr.setText("SAMPLE1");

        return view;

    }
}
