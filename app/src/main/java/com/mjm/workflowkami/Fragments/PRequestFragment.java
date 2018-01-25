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
import com.mjm.workflowkami.adapter_classes.PurchaseRequestAdapter;
import com.mjm.workflowkami.adapter_classes.TaskClassAdapter;
import com.mjm.workflowkami.impl_classes.Tasks;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.ProjectTeamClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestClass;
import com.mjm.workflowkami.model_classes.TaskClass;
import com.mjm.workflowkami.service_classes.ProjectTeamService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 26 Nov 2017.
 */

public class PRequestFragment extends ListFragment {

    private String TAG = Tasks.class.getSimpleName();
    private ListView listofPteams;
    private ServiceImpl serviceImpl = new ServiceImpl();
    private ProjectTeamClass prjteam = new ProjectTeamClass();
    private List<ProjectTeamClass> projList = new ArrayList<ProjectTeamClass>();
    private ProjectTeamService projectTeamService = API.getInstance().getProjectTeamService();
    private ProgressDialog progressDialog;
    private ProjectClass projectIntent = new ProjectClass();

    private class ProjPReq extends AsyncTask<String, Void, List<PurchaseRequestClass>> {

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading. Please wait... ");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected List<PurchaseRequestClass> doInBackground(String... strings) {
            Intent intent = getActivity().getIntent();
            projectIntent = (ProjectClass) intent.getSerializableExtra("projects");

            do {

                serviceImpl.GetAllPurchaseRequests();
//                serviceImpl.GetAllProjTeams();
//                if (projectIntent != null) {
//                    serviceImpl.GetTaskByProjId(projectIntent.getProjID());
//                }
                try  {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (serviceImpl.prList == null);
            return serviceImpl.prList;
        }
        @Override
        protected void onPostExecute(List<PurchaseRequestClass> workerClassResponseEntity) {
            progressDialog.dismiss();
            PurchaseRequestAdapter adapter = new PurchaseRequestAdapter(getActivity(), workerClassResponseEntity);
            setListAdapter(adapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_preq, container, false);

        new ProjPReq().execute();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        PurchaseRequestAdapter adapter = new PurchaseRequestAdapter(getActivity(), serviceImpl.prList);
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