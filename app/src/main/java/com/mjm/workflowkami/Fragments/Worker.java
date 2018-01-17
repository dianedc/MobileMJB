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
import com.mjm.workflowkami.adapter_classes.WorkerClassAdapter;
import com.mjm.workflowkami.impl_classes.Projects;
import com.mjm.workflowkami.impl_classes.Tasks;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.ProjectTeamClass;
import com.mjm.workflowkami.model_classes.WorkerClass;
import com.mjm.workflowkami.service_classes.WorkerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 21 Nov 2017.
 */

public class Worker extends ListFragment {

    private ServiceImpl serviceImpl = new ServiceImpl();
    private WorkerClass work = new WorkerClass();
    private ProgressDialog progressDialog;
    private ProjectClass proj = new ProjectClass();

    private class WorkerTask extends AsyncTask<String, Void, List<ProjectTeamClass>> {


        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading. Please wait... ");
            progressDialog.show();
        }

        @Override
        protected List<ProjectTeamClass> doInBackground(String... strings) {
            do {
                Intent pIntent = getActivity().getIntent();
                proj = (ProjectClass) pIntent.getSerializableExtra("projects");
                if (proj != null) {
                    serviceImpl.GetTeamById(proj.getProjID());
                }
                try  {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (serviceImpl.pTeamList == null);
            return serviceImpl.pTeamList;
        }
        @Override
        protected void onPostExecute(List<ProjectTeamClass> workerClassResponseEntity) {
            progressDialog.dismiss();
            WorkerClassAdapter adapter = new WorkerClassAdapter(getActivity(), workerClassResponseEntity);
            setListAdapter(adapter);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_worker, container, false);
        new WorkerTask().execute();
        Intent intent = getActivity().getIntent();
        work = (WorkerClass) intent.getSerializableExtra("workers");

        serviceImpl.GetAllWorkers();

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        WorkerClassAdapter adapter = new WorkerClassAdapter(getActivity(), serviceImpl.pTeamList);
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

