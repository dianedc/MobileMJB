package com.mjm.workflowkami.add_classes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ddc on 11/14/17.
 */

public class AddPRequestDtl extends ListFragment {
    private EditText preq_dtl_id, preq_dtl_desc, preq_dtl_qty, preq_dtl_unit, preq_dtl_job, preq_dtl_uni_price, preq_id_dtl;
    private TextView preq_dtl_line_tot;
    private Button btnCancel, btnSavePreqDtlItem;
    private BigDecimal lineTot;

    private PurchaseRequestClass preq = new PurchaseRequestClass();
    private PurchaseRequestClass preqIntent = new PurchaseRequestClass();
    private PurchaseRequestItemClass pItemDtl = new PurchaseRequestItemClass();
    private PurchaseRequestItemClass pItem = new PurchaseRequestItemClass();
    private ServiceImpl serviceImpl = new ServiceImpl();
    private PullRefreshLayout layout;
    int preq_id;
    public List<PurchaseRequestItemClass> pItemList = new ArrayList<PurchaseRequestItemClass>();

    private PurchaseRequestItemService pItemService = API.getInstance().getPurchaseRequestItemService();
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
            preqIntent = (PurchaseRequestClass) intent.getSerializableExtra("preqs");
            do {
                if (preq != null) {
                    serviceImpl.GetAllPReqItemList(preqIntent.getPreqID());
                }
                try  {
                    Thread.sleep(15000);
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
//            getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    pItem = (PurchaseRequestItemClass) parent.getItemAtPosition(position);
//                    Toast.makeText(getActivity(), String.valueOf(pItem.getPrequestID()), Toast.LENGTH_LONG).show();
//                }
//            });
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_prequest_item, container, false);
        Intent intent = getActivity().getIntent();
        preq = (PurchaseRequestClass) intent.getSerializableExtra("preqs");

//        if (preq != null) {
//            int intid = 0;
//            final String uri = "http://servicemjm-env.ap-southeast-1.elasticbeanstalk.com/prequest/"+preq.getPreqID()+"/item";
            new PRequestTask().execute();
//        }


        layout = (PullRefreshLayout) rootView.findViewById(R.id.refreshprdtl);
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new PRequestTask().execute();
                        layout.setRefreshing(false);

                    }
                }, 3000);
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
        pItem = (PurchaseRequestItemClass) getListView().getItemAtPosition(position);
//        pItem = (PurchaseRequestItemClass) getListView().getAdapter().getItem(position);
        Intent i = new Intent(getActivity(), AddPrequestDtlItem.class);
        i.putExtra("item", pItem);
        getActivity().startActivity(i);

        super.onListItemClick(l, v, position, id);
    }

    public void UpdateItem(int preqid, int item, PurchaseRequestItemClass pi) {
        Call<Void> edtItem = pItemService.editPReqItem(preqid, item, pi);

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
