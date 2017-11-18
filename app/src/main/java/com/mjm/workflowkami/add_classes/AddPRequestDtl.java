package com.mjm.workflowkami.add_classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.adapter_classes.PurchaseRequestItemAdapter;
import com.mjm.workflowkami.impl_classes.Users;
import com.mjm.workflowkami.model_classes.PurchaseRequestClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestItemClass;
import com.mjm.workflowkami.service_classes.PurchaseRequestItemService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ddc on 11/14/17.
 */

public class AddPRequestDtl extends Fragment{
    private Button btnAddPItem;
//    TextInputEditText item, txtqty, amount;
    private EditText txtitem, txtqty, txtjob, txtunit, txtitemid, txtpreqid;
    private ListView container;
    private PurchaseRequestItemService preqItemServ = API.getInstance().getPurchaseRequestItemService();
    private PurchaseRequestItemClass p = new PurchaseRequestItemClass();
    private PurchaseRequestClass preq = new PurchaseRequestClass();
    private ServiceImpl serviceImpl = new ServiceImpl();
//    private PurchaseRequestItemAdapter adapter = new PurchaseRequestItemAdapter();
    public List<PurchaseRequestItemClass> pItemList = new ArrayList<PurchaseRequestItemClass>();
    private PurchaseRequestItemService pItemService = API.getInstance().getPurchaseRequestItemService();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_prequest_item, container, false);

        String[] arr = {"1", "2", "3"};
//        btnAddPItem = (Button) rootView.findViewById(R.id.btnAddPItem);
//        txtitemid = (EditText) rootView.findViewById(R.id.txtpreqitemid);
//        txtitem = (EditText) rootView.findViewById(R.id.txtPReqItem);
//        txtjob = (EditText) rootView.findViewById(R.id.txtPReqJob);
//        txtqty = (EditText) rootView.findViewById(R.id.txtPReqQuantity);
//        txtunit = (EditText) rootView.findViewById(R.id.txtPReqUnit);
//        txtpreqid = (EditText) rootView.findViewById(R.id.txtpreqid);
//        item = (TextInputEditText) rootView.findViewById(R.id.txtPReqItem);
//        txtqty = (TextInputEditText) rootView.findViewById(R.id.txtPReqQuantity);
//        amount = (TextInputEditText) rootView.findViewById(R.id.txtPReqAmount);
        container = (ListView) rootView.findViewById(R.id.container);

//        LayoutTransition transition = new LayoutTransition();
//        container.setLayoutTransition(transition);


//        Intent intent = getActivity().getIntent();
//        preq = (PurchaseRequestClass) intent.getSerializableExtra("preqs");

//        if(preq != null) {
//            txtitemid.setText(preq.getPreqID());
//            serviceImpl.GetAllPReqItemList(preq.getPreqID());
//        }
//        serviceImpl.GetAllPReqItemList(preq.getPreqID());
//        Call<List<PurchaseRequestItemClass>> getitems = pItemService.getItemByPReqId(preq.getPreqID());
//
//        getitems.enqueue(new Callback<List<PurchaseRequestItemClass>>() {
//            @Override
//            public void onResponse(Call<List<PurchaseRequestItemClass>> call, Response<List<PurchaseRequestItemClass>> response) {
//                List<PurchaseRequestItemClass> iList = response.body();
////                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
//
//                try {
//                    for (int i = 0; i < iList.size(); i++) {
//                        pItemList.add(new PurchaseRequestItemClass(iList.get(i).getPreqItemID(),
//                                iList.get(i).getPreqID(),
//                                iList.get(i).getPreqqty(),
//                                iList.get(i).getPrequnit(),
//                                iList.get(i).getPreqdesc(),
//                                iList.get(i).getPreqjob()));
//                    }
//                } catch (final Exception e) { e.printStackTrace(); }
//
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
//                alertDialogBuilder.setMessage(pItemList.toString());
//                alertDialogBuilder.setCancelable(true);
//                alertDialogBuilder.show();
//            }
//
//            @Override
//            public void onFailure(Call<List<PurchaseRequestItemClass>> call, Throwable t) {
//                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
//                alertDialogBuilder.setMessage(t.toString());
//                alertDialogBuilder.setCancelable(true);
//                alertDialogBuilder.show();
//            }
//        });
//        populateRequests(arr);


//        final ViewGroup finalContainer = container;
//        btnAddPItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                final View addView = inflater.inflate(R.layout.list_dialog_remove, null);
//                TextView vItem = (TextView) addView.findViewById(R.id.vItem);
////                TextView vtxtqty = (TextView) addView.findViewById(R.id.vtxtqty);
////                TextView vAmount = (TextView) addView.findViewById(R.id.vAmount);
//                vItem.setText("Item Description: "+txtitem.getText().toString() +
//                        "\nLevel: " + txtjob.getText().toString() +
//                        "\nQuantity: " + txtqty.getText().toString() +
//                        "\nUnit: " + txtunit.getText().toString());
////                vtxtqty.setText(txtqty.getText().toString());
////                vAmount.setText(amount.getText().toString());
//
//
//
//                if (!txtitemid.getText().toString().matches("")) {
//                    p=new PurchaseRequestItemClass(Integer.valueOf(txtitemid.getText().toString()),
//                            Integer.valueOf(txtitemid.getText().toString()),
//                            Integer.valueOf(txtqty.getText().toString()),
//                            txtunit.getText().toString(),
//                            txtitem.getText().toString(),
//                            txtjob.getText().toString());
////                    EditPReqItem(preq.getPreqID(), , p);
//                } else {
//
//                    p=new PurchaseRequestItemClass(preq.getPreqID(),
//                            Integer.valueOf(txtqty.getText().toString()),
//                            txtunit.getText().toString(),
//                            txtitem.getText().toString(),
//                            txtjob.getText().toString());
//                    AddPReqItems(p);
//                }
//
//                txtitem.setText("");
//                txtjob.setText("");
//                txtqty.setText("");
//                txtunit.setText("");
////                Button remove = (Button) addView.findViewById(R.id.btnRemoveItem);
////                remove.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////
////
//////                        ((LinearLayout) addView.getParent()).removeView(addView);
////                    }
////                });
//
////                finalContainer.addView(addView);
//
//
//            }
//        });


        return rootView;
    }

    public void populateRequests(String[] arr1) {
        PurchaseRequestItemAdapter adapter = new PurchaseRequestItemAdapter(getActivity(), serviceImpl.pItemList);
        ArrayAdapter adapter1 = new ArrayAdapter(getActivity(), R.layout.list_dialog_remove, arr1);
        container.setAdapter(adapter1);
    }

    public void AddPReqItems(PurchaseRequestItemClass i) {
        Call<PurchaseRequestItemClass> addPReqItem = preqItemServ.addPReqItem(i);

        addPReqItem.enqueue(new Callback<PurchaseRequestItemClass>() {
            @Override
            public void onResponse(Call<PurchaseRequestItemClass> call, Response<PurchaseRequestItemClass> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "Item Added", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setMessage(response.toString());
                    alertDialogBuilder.setCancelable(true);
                    alertDialogBuilder.show();
                }
            }

            @Override
            public void onFailure(Call<PurchaseRequestItemClass> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setMessage(t.toString());
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.show();
            }
        });
    }

    public void EditPReqItem(int preq, int itemid, PurchaseRequestItemClass i) {
        Call<Void> editPReqItem = preqItemServ.editPReqItem(preq, itemid, i);

        editPReqItem.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(AddUserr.this, "User has been successfully edited!", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setMessage(response.toString());
                    alertDialogBuilder.setCancelable(true);
                    alertDialogBuilder.show();

                    Intent u = new Intent(getContext(), Users.class);
                    startActivity(u);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setMessage(t.toString());
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.show();
            }
        });
    }


}
