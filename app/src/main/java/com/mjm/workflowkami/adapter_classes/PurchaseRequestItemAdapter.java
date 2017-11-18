package com.mjm.workflowkami.adapter_classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.mjm.workflowkami.R;
import com.mjm.workflowkami.model_classes.PurchaseRequestItemClass;

import java.util.List;

/**
 * Created by DC on 31/10/2017.
 */

public class PurchaseRequestItemAdapter extends ArrayAdapter<PurchaseRequestItemClass> {

    private Context context;
    private List<PurchaseRequestItemClass> item;
    private LayoutInflater inflater;

    public PurchaseRequestItemAdapter(@NonNull Context context, List<PurchaseRequestItemClass> item) {
        super(context, R.layout.list_dialog_remove, item);
        this.context = context;
        this.item = item;
        inflater = LayoutInflater.from(this.context);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_dialog_remove, parent, false);

        TextView txtPReqID = (TextView) view.findViewById(R.id.vItem);
        txtPReqID.setText(String.valueOf(item.get(position).getPreqID()) + " " +
        item.get(position).getPreqdesc());

        Button btnRemove = (Button) view.findViewById(R.id.btnRemoveItem);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PurchaseRequestItemClass pItem = item.get(position);
                item.remove(position);
//                Intent i = new Intent(context, AddPRequest.class);

//                i.putExtra("item", pItem);
//                context.startActivity(i);
            }
        });
//        TextView txtofficeengr = (TextView) view.findViewById(R.id.firstname);
//        txtofficeengr.setText("SAMPLE1");

        return view;

    }
}
