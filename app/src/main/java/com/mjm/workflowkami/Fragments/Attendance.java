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
    private List<ProjectTeamClass> pTeamList = new ArrayList<ProjectTeamClass>();

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
        protected void onPostExecute(List<ProjectTeamClass> attendanceClassResponseEntity) {
            progressDialog.dismiss();
            AttendanceClassAdapter adapter = new AttendanceClassAdapter(getActivity(), attendanceClassResponseEntity);
            setListAdapter(adapter);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_attendance, container, false);
        Intent pIntent = getActivity().getIntent();
        proj = (ProjectClass) pIntent.getSerializableExtra("projects");
        Intent intent = getActivity().getIntent();
        work = (WorkerClass) intent.getSerializableExtra("workers");
//        final String uri = "http://192.168.2.123:8083/rest/project/"+proj.getProjID()+"/team";
        new WorkerTask().execute();
//        Toast.makeText(getActivity(), proj.toString(), Toast.LENGTH_LONG).show();
//        serviceImpl.GetAllWorkers();


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        AttendanceClassAdapter adapter = new AttendanceClassAdapter(getActivity(), pTeamList);
//        setListAdapter(adapter);
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
