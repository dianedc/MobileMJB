package com.mjm.workflowkami.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.adapter_classes.ProjTeamClassAdapter;
import com.mjm.workflowkami.adapter_classes.WorkerTeamClassAdapter;
import com.mjm.workflowkami.impl_classes.Tasks;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.ProjectTeamClass;
import com.mjm.workflowkami.service_classes.ProjectTeamService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 26 Nov 2017.
 */

public class WorkerFragment extends ListFragment {

    private String TAG = Tasks.class.getSimpleName();
    private ListView listofPteams;
    private ServiceImpl serviceImpl = new ServiceImpl();
    private ProjectTeamClass prjteam = new ProjectTeamClass();
    private List<ProjectTeamClass> projList = new ArrayList<ProjectTeamClass>();
    private ProjectTeamService projectTeamService = API.getInstance().getProjectTeamService();
    private ProgressDialog progressDialog;
    private ProjectClass projectIntent = new ProjectClass();

    private class WorkerFragmentTask extends AsyncTask<String, Void, List<ProjectTeamClass>> {

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading. Please wait... ");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected List<ProjectTeamClass> doInBackground(String... strings) {
            Intent intent = getActivity().getIntent();
            projectIntent = (ProjectClass) intent.getSerializableExtra("projects");

            do {

//                serviceImpl.GetAllProjTeams();
                if (projectIntent != null) {
                    serviceImpl.GetWorkersTeamById(projectIntent.getProjID());
//                    Call<List<ProjectTeamClass>> getProjTeams = projectTeamService.getWorkerTeamById(projectIntent.getProjID());
//
//                    getProjTeams.enqueue(new Callback<List<ProjectTeamClass>>() {
//                        @Override
//                        public void onResponse(Call<List<ProjectTeamClass>> call, Response<List<ProjectTeamClass>> response) {
//                            if (response.isSuccessful()) {
//                                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
//                                List<ProjectTeamClass> projectTeamClassList = response.body();
//                                Log.d(TAG, response.toString());
//
//                                try {
//                                    for (int i = 0; i < projectTeamClassList.size(); i++) {
//                                        projList.add(new ProjectTeamClass(projectTeamClassList.get(i).getProjteamID(),
//                                                projectTeamClassList.get(i).getProjectsprojID(),
//                                                projectTeamClassList.get(i).getUserID(),
//                                                projectTeamClassList.get(i).getWorkersworkersID()));
//                                    }
//                                } catch (final Exception e) {
//                                    e.printStackTrace();
//                                }
//                            } else {
//                                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<ProjectTeamClass>> call, Throwable t) {
////                            t.printStackTrace();
//                            Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
//                        }
//                    });
                }
                try  {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (serviceImpl.pTeamWorkerList == null);
            return serviceImpl.pTeamWorkerList;
        }
        @Override
        protected void onPostExecute(List<ProjectTeamClass> workerClassResponseEntity) {
            progressDialog.dismiss();
            WorkerTeamClassAdapter adapter = new WorkerTeamClassAdapter(getActivity(), workerClassResponseEntity);
            setListAdapter(adapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_projteamworker, container, false);

        new WorkerFragmentTask().execute();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        WorkerTeamClassAdapter adapter = new WorkerTeamClassAdapter(getActivity(), serviceImpl.pTeamWorkerList);
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
