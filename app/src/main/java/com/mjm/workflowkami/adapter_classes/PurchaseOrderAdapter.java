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
import com.mjm.workflowkami.add_classes.AddPurchaseOrder;
import com.mjm.workflowkami.model_classes.PurchaseOrderClass;

import java.util.List;

/**
 * Created by DC on 01/11/2017.
 */

public class PurchaseOrderAdapter extends ArrayAdapter<PurchaseOrderClass> {
    private Context context;
    private List<PurchaseOrderClass> pords;

    public PurchaseOrderAdapter(Context context, List<PurchaseOrderClass> pords) {
        super(context, R.layout.list_item_porder, pords);
        this.context = context;
        this.pords = pords;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_porder, parent, false);
//        TextView txtProjID = (TextView) view.findViewById(R.id.userID);
//        txtProjID.setText(projects.get(position).getProjectID().toString());
        TextView txtPordid = (TextView) view.findViewById(R.id.pord_id);
        txtPordid.setText(String.valueOf(pords.get(position).getPrequestID().getPreqID()));
        TextView txtpord_projnman = (TextView) view.findViewById(R.id.pord_projman);
        txtpord_projnman.setText(pords.get(position).getPordprojman().getFirstname() +
        " " + pords.get(position).getPordprojman().getLastname());

        Button btnEdit = (Button) view.findViewById(R.id.btnEditPord);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PurchaseOrderClass purchaseOrderClass = pords.get(position);

                Intent i = new Intent(context, AddPurchaseOrder.class);

                i.putExtra("pords", purchaseOrderClass);
                context.startActivity(i);
            }
        });

        return view;

    }
}
