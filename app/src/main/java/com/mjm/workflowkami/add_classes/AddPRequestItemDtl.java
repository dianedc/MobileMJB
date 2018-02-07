package com.mjm.workflowkami.add_classes;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.adapter_classes.PurchaseRequestItemAdapter;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestItemClass;
import com.mjm.workflowkami.model_classes.UserClass;
import com.mjm.workflowkami.service_classes.PurchaseRequestItemService;
import com.mjm.workflowkami.service_classes.PurchaseRequestService;

import java.math.BigDecimal;
import java.util.List;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ddc on 11/14/17.
 */

public class AddPRequestItemDtl extends Fragment {
    private EditText preq_id, preq_dtl_desc, preq_dtl_qty, preq_dtl_unit, preq_dtl_job, preq_dtl_uni_price, preq_id_dtl;
    private TextView preq_dtl_line_tot;
    private Button btnCancel, btnSavePreqDtlItem;
    private BigDecimal lineTot;
    private Spinner preq_dtl_gendesc;

    private PurchaseRequestClass pr = new PurchaseRequestClass();
    private PurchaseRequestItemClass piIntent = new PurchaseRequestItemClass();
    private PurchaseRequestItemClass pItem = new PurchaseRequestItemClass();
    private PurchaseRequestItemService pService = API.getInstance().getPurchaseRequestItemService();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_prequest_dtl_item, container, false);
        preq_id = (EditText) rootView.findViewById(R.id.preq_id);
        preq_id_dtl = (EditText) rootView.findViewById(R.id.preq_id_dtl);
        preq_dtl_gendesc = (Spinner) rootView.findViewById(R.id.preq_dtl_gendesc);
        preq_dtl_desc = (EditText) rootView.findViewById(R.id.preq_dtl_desc);
        preq_dtl_qty = (EditText) rootView.findViewById(R.id.preq_dtl_qty);
        preq_dtl_unit = (EditText) rootView.findViewById(R.id.preq_dtl_unit);
        preq_dtl_job = (EditText) rootView.findViewById(R.id.preq_dtl_job);
        preq_dtl_uni_price = (EditText) rootView.findViewById(R.id.preq_dtl_uni_price);
        preq_dtl_line_tot = (TextView) rootView.findViewById(R.id.preq_dtl_line_tot);
        btnCancel = (Button) rootView.findViewById(R.id.btnCancel);
        btnSavePreqDtlItem = (Button) rootView.findViewById(R.id.btnSavePreqDtlItem);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(getActivity(), AddPRequest.class);
                startActivity(cancel);
            }
        });

        final Intent pIntent = getActivity().getIntent();
        piIntent = (PurchaseRequestItemClass) pIntent.getSerializableExtra("item");
        Intent prIntent = getActivity().getIntent();
        pr = (PurchaseRequestClass) prIntent.getSerializableExtra("preqs");

        if(pr != null) {
            preq_id.setText(String.valueOf(pr.getPreqID()));
        }

        if (piIntent != null) {
            preq_id_dtl.setText(String.valueOf(piIntent.getPreqItemID()));
            for (int i = 0; i < preq_dtl_gendesc.getCount(); i++) {
                if (preq_dtl_gendesc.getItemAtPosition(i).toString().equals(piIntent.getPreqgendesc())) {
                    preq_dtl_gendesc.setSelection(i);
                    break;
                }
            }
            preq_dtl_desc.setText(piIntent.getPreqdesc());
            preq_dtl_qty.setText(String.valueOf(piIntent.getPreqqty()));
            preq_dtl_unit.setText(piIntent.getPrequnit());
            preq_dtl_job.setText(piIntent.getPreqjob());
            preq_dtl_uni_price.setText(piIntent.getPrequnitprice().toString());
            preq_dtl_line_tot.setText("Total: " + piIntent.getPreqlinetotal().toString());
        }

        preq_dtl_uni_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int input = Integer.valueOf(preq_dtl_uni_price.getText().toString());
                int input2 = Integer.valueOf(preq_dtl_qty.getText().toString());
                int res = input * input2;
                preq_dtl_line_tot.setText(String.valueOf(res));
                lineTot = new BigDecimal(preq_dtl_line_tot.getText().toString());
            }
        });


        btnSavePreqDtlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), preq_dtl_gendesc.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                if (!preq_id_dtl.getText().toString().matches("")) {
                    //update
                    pItem = new PurchaseRequestItemClass(Integer.valueOf(preq_id_dtl.getText().toString()),
                            piIntent.getPrequestID(),
                            Integer.valueOf(preq_dtl_qty.getText().toString()),
                            preq_dtl_unit.getText().toString().trim(),
                            preq_dtl_gendesc.getSelectedItem().toString(),
                            preq_dtl_desc.getText().toString().trim(),
                            preq_dtl_job.getText().toString().trim(),
                            Double.valueOf(preq_dtl_uni_price.getText().toString()),
                            lineTot);
                    UpdateItem(piIntent.getPrequestID().getPreqID(), Integer.valueOf(preq_id_dtl.getText().toString()), pItem);
                } else {
                    //add
                    pItem = new PurchaseRequestItemClass(pr,
                            Integer.valueOf(preq_dtl_qty.getText().toString()),
                            preq_dtl_unit.getText().toString().trim(),
                            preq_dtl_gendesc.getSelectedItem().toString(),
                            preq_dtl_desc.getText().toString().trim(),
                            preq_dtl_job.getText().toString().trim(),
                            Double.valueOf(preq_dtl_uni_price.getText().toString()),
                            lineTot);
                    AddItem(pr.getPreqID(), pItem);
                        Toast.makeText(getActivity(), "Saved!", Toast.LENGTH_LONG).show();
                    getActivity().finish();
                }
            }
        });


        return rootView;
    }

    public void AddItem(int preqid, PurchaseRequestItemClass pi) {
        Call<PurchaseRequestItemClass> addItem = pService.addPReqItem(preqid, pi);

        addItem.enqueue(new Callback<PurchaseRequestItemClass>() {
            @Override
            public void onResponse(Call<PurchaseRequestItemClass> call, Response<PurchaseRequestItemClass> response) {
                if(response.isSuccessful()) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setMessage("Purchase item has been successfully added!");
                    alertDialogBuilder.setCancelable(true);
                    alertDialogBuilder.show();

//                    AsyncTask<String, Void, List<PurchaseRequestItemClass>> execute = new AddPRequestDtl.PRequestTask().execute();
                    Intent u = new Intent(getActivity(), AddPRequestDtl.class);
                    startActivity(u);
                }
            }

            @Override
            public void onFailure(Call<PurchaseRequestItemClass> call, Throwable t) {
                Toast.makeText(getActivity(), "An error has been encountered while adding purchase item", Toast.LENGTH_SHORT);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setMessage(t.toString());
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.show();
            }
        });
    }

    public void UpdateItem(int preqid, int item, PurchaseRequestItemClass pi) {
        Call<Void> edtItem = pService.editPReqItem(item, pi);

        edtItem.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(AddUserr.this, "User has been successfully edited!", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setMessage("Purchase item has been successfully edited!");
                    alertDialogBuilder.setCancelable(true);
                    alertDialogBuilder.show();

                    Intent u = new Intent(getActivity(), AddPRequestDtl.class);
                    startActivity(u);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), "An error has been encountered while editing purchase item", Toast.LENGTH_SHORT);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setMessage(t.toString());
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.show();
            }
        });
    }


}
