package com.mjm.workflowkami.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.adapter_classes.PurchaseOrderAdapter;
import com.mjm.workflowkami.adapter_classes.TaskClassAdapter;
import com.mjm.workflowkami.impl_classes.Tasks;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.ProjectTeamClass;
import com.mjm.workflowkami.model_classes.PurchaseOrderClass;
import com.mjm.workflowkami.model_classes.TaskClass;
import com.mjm.workflowkami.service_classes.ProjectTeamService;
import com.mjm.workflowkami.service_classes.PurchaseOrderService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 26 Nov 2017.
 */

public class POrderFragment extends ListFragment {

    private String TAG = Tasks.class.getSimpleName();
    private ListView listofPteams;
    private ServiceImpl serviceImpl = new ServiceImpl();
    private ProjectTeamClass prjteam = new ProjectTeamClass();
    private List<ProjectTeamClass> projList = new ArrayList<ProjectTeamClass>();
    private ProjectTeamService projectTeamService = API.getInstance().getProjectTeamService();
    private ProgressDialog progressDialog;
    private ProjectClass projectIntent = new ProjectClass();

    private class ProjTask extends AsyncTask<String, Void, List<PurchaseOrderClass>> {

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading. Please wait... ");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected List<PurchaseOrderClass> doInBackground(String... strings) {
            Intent intent = getActivity().getIntent();
            projectIntent = (ProjectClass) intent.getSerializableExtra("projects");

            do {

                serviceImpl.GetAllPurchaseOrder();
//                serviceImpl.GetAllProjTeams();
//                if (projectIntent != null) {
//                    serviceImpl.GetTaskByProjId(projectIntent.getProjID());
//                }
                try  {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (serviceImpl.pordList == null);
            return serviceImpl.pordList;
        }
        @Override
        protected void onPostExecute(List<PurchaseOrderClass> workerClassResponseEntity) {
            progressDialog.dismiss();
            PurchaseOrderAdapter adapter = new PurchaseOrderAdapter(getActivity(), workerClassResponseEntity);
            setListAdapter(adapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_porder, container, false);

        new ProjTask().execute();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        PurchaseOrderAdapter adapter = new PurchaseOrderAdapter(getActivity(), serviceImpl.pordList);
        setListAdapter(adapter);
        super.onActivityCreated(savedInstanceState);
    }

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        WorkerClass worker = (WorkerClass) this.getListAdapter().getItem(position);
//        Intent i = new Intent(getContext(), Worker.class);
//
//        i.putExtra("item", worker);
//        getContext().startActivity(i);
//        super.onListItemClick(l, v, position, id);
//    }
}
