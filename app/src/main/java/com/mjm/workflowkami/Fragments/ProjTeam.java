package com.mjm.workflowkami.Fragments;

import android.content.Intent;
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
import com.mjm.workflowkami.adapter_classes.ProjectTeamAdapter;
import com.mjm.workflowkami.impl_classes.Tasks;
import com.mjm.workflowkami.model_classes.ProjectTeamClass;
import com.mjm.workflowkami.service_classes.ProjectTeamService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 26 Nov 2017.
 */

public class ProjTeam extends ListFragment {

    private String TAG = Tasks.class.getSimpleName();
    private ListView listofPteams;
    private ServiceImpl serviceImpl = new ServiceImpl();
    private ProjectTeamClass prjteam = new ProjectTeamClass();
    List<ProjectTeamClass> projList = new ArrayList<ProjectTeamClass>();
    private ProjectTeamService projectTeamService = API.getInstance().getProjectTeamService();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_projteam, container, false);



        Intent intent = getActivity().getIntent();
        prjteam = (ProjectTeamClass) intent.getSerializableExtra("projTeam");
//        int workersID = 0;
//        if (work != null) {
//            workersID = work.getWorkersID();
//        }

        serviceImpl.GetAllTeams();
//        serviceImpl.GetAllWorkers(workersID);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ProjectTeamAdapter adapter = new ProjectTeamAdapter(getActivity(), serviceImpl.pTeamList);
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
