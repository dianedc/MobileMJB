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
import com.mjm.workflowkami.adapter_classes.AttendanceClassAdapter;
import com.mjm.workflowkami.adapter_classes.WorkerClassAdapter;
import com.mjm.workflowkami.impl_classes.Tasks;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.ProjectTeamClass;
import com.mjm.workflowkami.model_classes.WorkerClass;
import com.mjm.workflowkami.service_classes.ProjectTeamService;
import com.mjm.workflowkami.service_classes.WorkerService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Attendance extends ListFragment {
    private String TAG = Tasks.class.getSimpleName();
    private ListView listofWorkers;
    private ServiceImpl serviceImpl = new ServiceImpl();
    private WorkerClass work = new WorkerClass();
    List<WorkerClass> workerList = new ArrayList<WorkerClass>();
    private WorkerService workerService = API.getInstance().getWorkerService();
    private ProgressDialog progressDialog;
    private ProjectClass proj = new ProjectClass();
    private ProjectTeamService projectTeamService = API.getInstance().getProjectTeamService();
    private List<ProjectTeamClass> pTeamList;

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
                    Call<List<ProjectTeamClass>> getProjTeams = projectTeamService.getWorkerTeamById(proj.getProjID());

                    getProjTeams.enqueue(new Callback<List<ProjectTeamClass>>() {
                        @Override
                        public void onResponse(Call<List<ProjectTeamClass>> call, Response<List<ProjectTeamClass>> response) {
                            if (response.isSuccessful()) {
                                List<ProjectTeamClass> projectTeamClassList = response.body();
                                Log.d(TAG, response.toString());

                                try {
                                    for (int i = 0; i < projectTeamClassList.size(); i++) {
                                        pTeamList.add(new ProjectTeamClass(projectTeamClassList.get(i).getProjteamID(),
                                                projectTeamClassList.get(i).getProjectsprojID(),
                                                projectTeamClassList.get(i).getUserID(),
                                                projectTeamClassList.get(i).getWorkersworkersID()));
                                    }
                                } catch (final Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<ProjectTeamClass>> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }

                try  {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (pTeamList == null);
            return pTeamList;
        }
        @Override
        protected void onPostExecute(List<ProjectTeamClass> attendanceClassResponseEntity) {
            progressDialog.dismiss();
            AttendanceClassAdapter adapter = new AttendanceClassAdapter(getActivity(), attendanceClassResponseEntity);
            setListAdapter(adapter);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_attendance, container, false);
        pTeamList = new ArrayList<ProjectTeamClass>();
        new WorkerTask().execute();

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        AttendanceClassAdapter adapter = new AttendanceClassAdapter(getActivity(), pTeamList);
        setListAdapter(adapter);
        super.onActivityCreated(savedInstanceState);
    }

}
