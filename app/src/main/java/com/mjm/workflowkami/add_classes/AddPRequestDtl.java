package com.mjm.workflowkami.add_classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
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

public class AddPRequestDtl extends ListFragment {

    private PurchaseRequestClass preq = new PurchaseRequestClass();
    private ServiceImpl serviceImpl = new ServiceImpl();
    private PullRefreshLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_prequest_item, container, false);
//        container = (ListView) rootView.findViewById(R.id.list_preq_items);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab_add_preq_dtl_item);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(getActivity(), AddPrequestDtlItem.class);
                startActivity(add);
            }
        });
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorLightBlue));

        Intent intent = getActivity().getIntent();
        preq = (PurchaseRequestClass) intent.getSerializableExtra("preqs");
        int preq_id = 0;
        if (preq != null) {
            preq_id = preq.getPreqID();
        }
        serviceImpl.GetAllPReqItemList(preq_id);

        layout = (PullRefreshLayout) rootView.findViewById(R.id.refreshprdtl);
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layout.setRefreshing(false);

                    }
                }, 3000);
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        PurchaseRequestItemAdapter adapter = new PurchaseRequestItemAdapter(getActivity(), serviceImpl.pItemList);
        setListAdapter(adapter);
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
}
