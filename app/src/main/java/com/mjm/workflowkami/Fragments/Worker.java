package com.mjm.workflowkami.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.adapter_classes.PurchaseRequestItemAdapter;
import com.mjm.workflowkami.adapter_classes.WorkerClassAdapter;
import com.mjm.workflowkami.add_classes.AddPrequestDtlItem;
import com.mjm.workflowkami.impl_classes.Tasks;
import com.mjm.workflowkami.impl_classes.Workers;
import com.mjm.workflowkami.model_classes.PurchaseRequestClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestItemClass;
import com.mjm.workflowkami.model_classes.WorkerClass;
import com.mjm.workflowkami.service_classes.WorkerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 21 Nov 2017.
 */

public class Worker extends ListFragment {

    private String TAG = Tasks.class.getSimpleName();
    private ListView listofWorkers;
    private ServiceImpl serviceImpl = new ServiceImpl();
    private WorkerClass work = new WorkerClass();
    List<WorkerClass> workerList = new ArrayList<WorkerClass>();
    private WorkerService workerService = API.getInstance().getWorkerService();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_worker, container, false);

//        listofWorkers = (ListView) findViewById(R.id.lstWorkers);

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

//        Intent intent = getActivity().getIntent();
//        work = (Workers) intent.getSerializableExtra("workers");
//        int workersID = 0;
//        if (work != null) {
//            workersID = work.getWorkersID();
//        }

        Intent intent = getActivity().getIntent();
        work = (WorkerClass) intent.getSerializableExtra("workers");
//        int workersID = 0;
//        if (work != null) {
//            workersID = work.getWorkersID();
//        }

        serviceImpl.GetAllWorkers();
//        serviceImpl.GetAllWorkers(workersID);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        WorkerClassAdapter adapter = new WorkerClassAdapter(getActivity(), serviceImpl.workerList);
        setListAdapter(adapter);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        WorkerClass worker = (WorkerClass) this.getListAdapter().getItem(position);
        Intent i = new Intent(getContext(), Worker.class);

        i.putExtra("item", worker);
        getContext().startActivity(i);
        super.onListItemClick(l, v, position, id);
    }
}
