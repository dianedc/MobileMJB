package com.mjm.workflowkami.add_classes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.mjm.workflowkami.API;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.adapter_classes.PurchaseRequestItemAdapter;
import com.mjm.workflowkami.model_classes.PurchaseRequestClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestItemClass;
import com.mjm.workflowkami.service_classes.PurchaseRequestItemService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ddc on 11/14/17.
 */

public class AddPRequestDtlItems extends ListFragment {

    private PurchaseRequestClass preq = new PurchaseRequestClass();
    private ServiceImpl serviceImpl = new ServiceImpl();
    private PullRefreshLayout layout;
    int preq_id;
    private EditText preq_dtl_id, preq_dtl_desc, preq_dtl_qty, preq_dtl_unit, preq_dtl_job, preq_dtl_uni_price, preq_id_dtl;
    private TextView preq_dtl_line_tot;
    private Button btnCancel, btnSavePreqDtlItem;

    private PurchaseRequestClass pr = new PurchaseRequestClass();
    private PurchaseRequestItemClass pi = new PurchaseRequestItemClass();
    private PurchaseRequestItemService pService = API.getInstance().getPurchaseRequestItemService();

    private ProgressDialog progressDialog;

    private class PRequestTask extends AsyncTask<String, Void, List<PurchaseRequestItemClass>> {


        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading. Please wait... ");
            progressDialog.show();
        }

        @Override
        protected List<PurchaseRequestItemClass> doInBackground(String... strings) {
            Intent intent = getActivity().getIntent();
            preq = (PurchaseRequestClass) intent.getSerializableExtra("preqs");
            do {
                if (preq != null) {
                    serviceImpl.GetAllPReqItemList(preq.getPreqID());
                }
                try  {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (serviceImpl.pItemList == null);
            return serviceImpl.pItemList;
        }
        @Override
        protected void onPostExecute(List<PurchaseRequestItemClass> pqClassResponseEntity) {
            progressDialog.dismiss();
            PurchaseRequestItemAdapter adapter = new PurchaseRequestItemAdapter(getActivity(), pqClassResponseEntity);
            setListAdapter(adapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_add_preq_dtl_item, container, false);
//        container = (ListView) rootView.findViewById(R.id.list_preq_items);

//        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab_add_preq_dtl_item);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent add = new Intent(getActivity(), AddPrequestDtlItem.class);
//                startActivity(add);
//            }
//        });
//        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorLightBlue));

        preq_id_dtl = (EditText) rootView.findViewById(R.id.preq_id_dtl);
        preq_dtl_id = (EditText) rootView.findViewById(R.id.preq_dtl_id);
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

        Intent piIntent = getActivity().getIntent();
        pi = (PurchaseRequestItemClass) piIntent.getSerializableExtra("item");
        Intent prIntent = getActivity().getIntent();
        pr = (PurchaseRequestClass) prIntent.getSerializableExtra("preqs");

//        Toast.makeText(getActivity(), preq.toString(), Toast.LENGTH_LONG).show();
//        Toast.makeText(getActivity(), pi.toString(), Toast.LENGTH_LONG).show();
        if(pr != null) {
            Toast.makeText(getActivity(), pr.toString(), Toast.LENGTH_LONG).show();
            preq_id_dtl.setText(String.valueOf(pr.getPreqID()));
        }

        if (pi != null) {

            preq_dtl_id.setText(String.valueOf(pi.getPreqItemID()));
            preq_dtl_desc.setText(pi.getPreqdesc());
            preq_dtl_qty.setText(String.valueOf(pi.getPreqqty()));
            preq_dtl_unit.setText(pi.getPrequnit());
            preq_dtl_job.setText(pi.getPreqjob());
            preq_dtl_uni_price.setText(pi.getPrequnitprice().toString());
            preq_dtl_line_tot.setText("Total: "+ pi.getPreqlinetotal().toString());
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
                int input1 = Integer.valueOf(preq_dtl_qty.getText().toString());
                int result = input * input1;
                preq_dtl_line_tot.setText(String.valueOf(result));

            }
        });


        btnSavePreqDtlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!preq_dtl_id.getText().toString().matches("")) {
                    //update
                    pi = new PurchaseRequestItemClass(Integer.valueOf(preq_dtl_id.getText().toString()),
                            Integer.valueOf(preq_dtl_id.getText().toString()),
                            Integer.valueOf(preq_dtl_qty.getText().toString()),
                            preq_dtl_unit.getText().toString().trim(),
                            preq_dtl_desc.getText().toString().trim(),
                            preq_dtl_job.getText().toString().trim(),
                            Double.valueOf(preq_dtl_uni_price.getText().toString()),
                            Double.valueOf(preq_dtl_line_tot.getText().toString()));
                    UpdateItem(pi.getPreqID(), Integer.valueOf(preq_dtl_id.getText().toString()), pi);
                } else {
                    //add
                    pi = new PurchaseRequestItemClass(Integer.valueOf(preq_id_dtl.getText().toString()),
                            Integer.valueOf(preq_dtl_qty.getText().toString()),
                            preq_dtl_unit.getText().toString().trim(),
                            preq_dtl_desc.getText().toString().trim(),
                            preq_dtl_job.getText().toString().trim(),
                            Double.valueOf(preq_dtl_uni_price.getText().toString()),
                            Double.valueOf(preq_dtl_line_tot.getText().toString()));
                    AddItem(pi.getPreqID(), pi);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        PurchaseRequestItemAdapter adapter = new PurchaseRequestItemAdapter(getActivity(), serviceImpl.pItemList);
//        setListAdapter(adapter);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        PurchaseRequestItemClass pItem = (PurchaseRequestItemClass) this.getListAdapter().getItem(position);
        Intent i = new Intent(getContext(), AddPrequestDtlItem.class);

        i.putExtra("item", pItem);
        getContext().startActivity(i);
        super.onListItemClick(l, v, position, id);
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
        Call<Void> edtItem = pService.editPReqItem(preqid, item, pi);

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
