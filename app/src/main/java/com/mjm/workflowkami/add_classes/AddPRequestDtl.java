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
//        Call<List<PurchaseRequestItemClass>> getitems = pItemService.getItemByPReqId(preq_id);
//
//        getitems.enqueue(new Callback<List<PurchaseRequestItemClass>>() {
//            @Override
//            public void onResponse(Call<List<PurchaseRequestItemClass>> call, Response<List<PurchaseRequestItemClass>> response) {
//                List<PurchaseRequestItemClass> iList = response.body();
////                Toast.makeText(getActivity(), response.body().toString(), Toast.LENGTH_LONG).show();
//                try {
//                    for (int i = 0; i < iList.size(); i++) {
//                        pItemList.add(new PurchaseRequestItemClass(iList.get(i).getPreqItemID(),
//                                iList.get(i).getPreqID(),
//                                iList.get(i).getPreqqty(),
//                                iList.get(i).getPrequnit(),
//                                iList.get(i).getPreqdesc(),
//                                iList.get(i).getPreqjob(),
//                                iList.get(i).getPrequnitprice(),
//                                iList.get(i).getPreqlinetotal()));
//                    }
//                } catch (final Exception e) { e.printStackTrace(); }
//            }
//
//            @Override
//            public void onFailure(Call<List<PurchaseRequestItemClass>> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
//
//            }
//        });

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
