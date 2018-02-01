package com.mjm.workflowkami.impl_classes;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.baoyz.widget.PullRefreshLayout;
import com.mjm.workflowkami.API;
import com.mjm.workflowkami.LoaderAsync;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.adapter_classes.ProjectClassAdapter;
import com.mjm.workflowkami.adapter_classes.TaskAssignedAdapter;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.TaskAssignedClass;
import com.mjm.workflowkami.model_classes.TaskClass;
import com.mjm.workflowkami.service_classes.ProjectService;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;


public class TaskAssigned extends LoaderAsync
        implements NavigationView.OnNavigationItemSelectedListener {

    private PullRefreshLayout layout;
    private SwipeRefreshLayout refreshLayout;
    private String TAG = TaskAssigned.class.getSimpleName();
    private ListView listOfTaskAssigned;
//    private SpotsDialog loader;
    private ProjectService projectService = API.getInstance().getProjectService();
    List<ProjectClass> pl;
    private ServiceImpl serviceImpl = new ServiceImpl();
    private List<String> strings = new ArrayList<String>();
    private ProjectClassAdapter adapter;
    private Toolbar supportActionBar;
    private TaskClass taskIntent = new TaskClass();

    private class ProjectTask extends AsyncTask<String, Void, List<TaskAssignedClass>> {

        @Override
        protected void onPreExecute() {
            showLoadingDialog();
        }

        @Override
        protected List<TaskAssignedClass> doInBackground(String... strings) {
            Intent intent = getIntent();
            taskIntent = (TaskClass) intent.getSerializableExtra("assignworker");
            do {
                serviceImpl.GetAllWorkersAssigned(taskIntent.getProjectID().getProjID(), taskIntent.getTaskID());
                try  {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (serviceImpl.taskAssignedList == null);
            return serviceImpl.taskAssignedList;
        }
        @Override
        protected void onPostExecute(List<TaskAssignedClass> taskAssignedClassResponseEntity) {
            dismissProgressDialog();
            listOfTaskAssigned.setAdapter(new TaskAssignedAdapter(TaskAssigned.this, taskAssignedClassResponseEntity));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_assigned);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_task_assigned);
        setTitle("Assigned Workers");
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        loader = new SpotsDialog(TaskAssigned.this);
        listOfTaskAssigned = (ListView) findViewById(R.id.lstTaskAssigned);

        new ProjectTask().execute();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_task_assigned);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_task_assigned);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_task_assigned);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.projects, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_dashboard:
//                loader.show();
                Intent d = new Intent(TaskAssigned.this, Dashboard.class);
                startActivity(d);
                break;
            case R.id.nav_project:
                break;

            case R.id.nav_team:
//                loader.show();
                Intent x = new Intent(TaskAssigned.this, AttendanceNav.class);
                startActivity(x);
                break;
//            case R.id.nav_purchaseRequest:
////                loader.show();
//                Intent f = new Intent(Projects.this, Forms.class);
//                startActivity(f);
//                break;
//            case R.id.nav_purchaseOrder:
////                loader.show();
//                Intent e = new Intent(Projects.this, PurchaseOrder.class);
//                startActivity(e);
//                break;
//            case R.id.nav_files:
////                loader.show();
//                Intent fi = new Intent(Projects.this, Files.class);
//                startActivity(fi);
//                break;
//            case R.id.nav_reports:
////                loader.show();
//                Intent r = new Intent(Projects.this, Reports.class);
//                startActivity(r);
//                break;
            case R.id.nav_users:
//                loader.show();
                Intent u = new Intent(TaskAssigned.this, Users.class);
                startActivity(u);
                break;

//            case R.id.nav_workers:
////                loader.show();
//                Intent x = new Intent(Projects.this, Workers.class);
//                startActivity(x);
//                break;

//            case R.id.nav_settings:
////                loader.show();
//                Intent s = new Intent(Projects.this, Settings.class);
//                startActivity(s);
//                break;

            case R.id.nav_logout:
//                loader.show();
                Intent l = new Intent(TaskAssigned.this, LoginActivity.class);
                startActivity(l);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_task_assigned);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setSupportActionBar(Toolbar supportActionBar) {
        this.supportActionBar = supportActionBar;
    }


}
