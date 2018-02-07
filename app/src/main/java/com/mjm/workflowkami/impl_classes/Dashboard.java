package com.mjm.workflowkami.impl_classes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.mjm.workflowkami.API;
import com.mjm.workflowkami.LoaderAsync;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.adapter_classes.CountClassAdapter;
import com.mjm.workflowkami.adapter_classes.ProjCountClassAdapter;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.TaskClass;
import com.mjm.workflowkami.service_classes.ProjectService;
import com.mjm.workflowkami.service_classes.TaskService;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends LoaderAsync
        implements NavigationView.OnNavigationItemSelectedListener{

    private TaskService taskService = API.getInstance().getTaskService();
    private ProjectService projService = API.getInstance().getProjectService();
    TextView txtprojects, txttasks, txttasks2, txttasks3;
    private ProgressDialog pdialog;
//    private ListView listCount;
    private TaskClass taskIntent = new TaskClass();
    List<TaskClass> atasksList = new ArrayList<TaskClass>();
    List<TaskClass> ctasksList = new ArrayList<TaskClass>();
    List<TaskClass> ptasksList = new ArrayList<TaskClass>();
    List<ProjectClass> projList = new ArrayList<ProjectClass>();
    private SpotsDialog loader;
    private String TAG = Dashboard.class.getSimpleName();
    private ServiceImpl serviceImpl = new ServiceImpl();
    private Toolbar supportActionBar;
    private CountClassAdapter adapter;
    private ProjCountClassAdapter projAdapter;
    private PullRefreshLayout layout;

    private ListView mListView, taskListView, pListView, cListView;

    private class ProjCountTask extends AsyncTask<String, Void, List<ProjectClass>> {

        @Override
        protected void onPreExecute() {
            showLoadingDialog();

        }

        @Override
        protected List<ProjectClass> doInBackground(String... strings) {
            do {
                Call<List<ProjectClass>> projactive = projService.getCountActiveProjects();
                projactive.enqueue(new Callback<List<ProjectClass>>() {
                    @Override
                    public void onResponse(Call<List<ProjectClass>> call, Response<List<ProjectClass>> response) {
                        if (response.isSuccessful()) {
                            List<ProjectClass> projectClassList = response.body();
                            try {
                                for (int i = 0; i < projectClassList.size(); i++) {
                                    projList.add(new ProjectClass(projectClassList.get(i).getCount()));
                                }
                            } catch (final Exception e) { e.printStackTrace(); }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProjectClass>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
                try {
                    Thread.sleep(1000);

                } catch (Exception eo) {
                    eo.printStackTrace();
                }
            }while (projList == null);
            return projList;
        }


        @Override
        protected void onPostExecute(List<ProjectClass> taskClassResponseEntity) {
            dismissProgressDialog();
//
            ProjCountClassAdapter adapter = new ProjCountClassAdapter(Dashboard.this, taskClassResponseEntity);
            mListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    private class TaskActive extends AsyncTask<String, Void, List<TaskClass>> {

        @Override
        protected void onPreExecute() {
            showLoadingDialog();

        }

        @Override
        protected List<TaskClass> doInBackground(String... strings) {
            do {
                Call<List<TaskClass>> active = taskService.getAllActive();
                active.enqueue(new Callback<List<TaskClass>>() {
                    @Override
                    public void onResponse(Call<List<TaskClass>> call, Response<List<TaskClass>> response) {
                        if (response.isSuccessful()) {
                            List<TaskClass> taskClassList = response.body();
                            try {
                                for (int i = 0; i < taskClassList.size(); i++) {
                                    atasksList.add(new TaskClass(taskClassList.get(i).getCount()));
                                }
                            } catch (final Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<TaskClass>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
                try {
                    Thread.sleep(1000);

                } catch (Exception eo) {
                    eo.printStackTrace();
                }
            }while (atasksList == null);
            return atasksList;
        }
        @Override
        protected void onPostExecute(List<TaskClass> taskClassResponseEntity) {
            dismissProgressDialog();
//
            adapter = new CountClassAdapter(Dashboard.this, atasksList);
            taskListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


    private class TaskCompleted extends AsyncTask<String, Void, List<TaskClass>> {

        @Override
        protected void onPreExecute() {
            showLoadingDialog();

        }

        @Override
        protected List<TaskClass> doInBackground(String... strings) {
            do {
                Call<List<TaskClass>> completed = taskService.getAllCompleted();
                completed.enqueue(new Callback<List<TaskClass>>() {
                    @Override
                    public void onResponse(Call<List<TaskClass>> call, Response<List<TaskClass>> response) {
                        if (response.isSuccessful()) {
                            List<TaskClass> taskClassList = response.body();
                            try {
                                for (int i = 0; i < taskClassList.size(); i++) {
                                    ctasksList.add(new TaskClass(taskClassList.get(i).getCount()));
                                }
                            } catch (final Exception e) { e.printStackTrace(); }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<TaskClass>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });


                try {
                    Thread.sleep(1000);

                } catch (Exception eo) {
                    eo.printStackTrace();
                }
            }while (ctasksList == null);
            return ctasksList;
        }
        @Override
        protected void onPostExecute(List<TaskClass> taskClassResponseEntity) {
            dismissProgressDialog();
//
            adapter = new CountClassAdapter(Dashboard.this, ctasksList);
            cListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    private class TaskPending extends AsyncTask<String, Void, List<TaskClass>> {

        @Override
        protected void onPreExecute() {
            showLoadingDialog();

        }

        @Override
        protected List<TaskClass> doInBackground(String... strings) {
            do {
                Call<List<TaskClass>> pending = taskService.getAllPending();
                pending.enqueue(new Callback<List<TaskClass>>() {
                    @Override
                    public void onResponse(Call<List<TaskClass>> call, Response<List<TaskClass>> response) {
                        if (response.isSuccessful()) {
                            List<TaskClass> taskClassList = response.body();
                            try {
                                for (int i = 0; i < taskClassList.size(); i++) {
                                    ptasksList.add(new TaskClass(taskClassList.get(i).getCount()));
                                }
                            } catch (final Exception e) { e.printStackTrace(); }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<TaskClass>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                try {
                    Thread.sleep(1000);

                } catch (Exception eo) {
                    eo.printStackTrace();
                }
            }while (ctasksList == null);
            return ctasksList;
        }
        @Override
        protected void onPostExecute(List<TaskClass> taskClassResponseEntity) {
            dismissProgressDialog();
//
            adapter = new CountClassAdapter(Dashboard.this, ptasksList);
            pListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mListView = (ListView) findViewById(R.id.listViewProj);
        taskListView = (ListView) findViewById(R.id.listViewTasks);
        cListView = (ListView) findViewById(R.id.listViewTasks2) ;
        pListView = (ListView) findViewById(R.id.listViewTasks3) ;



        txtprojects = (TextView) findViewById(R.id.txtProjects);
        txttasks = (TextView) findViewById(R.id.txtTasks);

//        final String uri = "http://192.168.2.123:8083/rest/project/projects/show/active";
            new ProjCountTask().execute();
        final String uri1 = "http://192.168.2.123:8083/rest/project/task/tasks/active";
        new TaskActive().execute(uri1);
        final String uri2 = "http://192.168.2.123:8083/rest/project/task/tasks/completed";
        new TaskCompleted().execute(uri2);
        final String uri3 = "http://192.168.2.123:8083/rest/project/task/tasks/pending";
        new TaskPending().execute(uri3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_dashboard);
                setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_dashboard);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_dashboard);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void setSupportActionBar(Toolbar supportActionBar) {
        this.supportActionBar = supportActionBar;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_dashboard);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_dashboard:
//                Intent d = new Intent(Dashboard.this, Dashboard.class);
//                startActivity(d);
                break;

            case R.id.nav_project:
                Intent p = new Intent(Dashboard.this, Projects.class);
                startActivity(p);
                break;

            case R.id.nav_purchaseRequest:
                Intent f = new Intent(Dashboard.this, Forms.class);
                startActivity(f);
                break;

            case R.id.nav_team:
//                loader.show();
                Intent x = new Intent(Dashboard.this, AttendanceNav.class);
                startActivity(x);
                break;

//            case R.id.nav_files:
//                loader.show();
//                Intent fi = new Intent(Dashboard.this, Files.class);
//                startActivity(fi);
//                break;
//            case R.id.nav_reports:
//                loader.show();
//                Intent r = new Intent(Dashboard.this, Reports.class);
//                startActivity(r);
//                break;
            case R.id.nav_users:
//                loader.show();
                Intent u = new Intent(Dashboard.this, Users.class);
                startActivity(u);
                break;

//            case R.id.nav_settings:
//                loader.show();
//                Intent s = new Intent(Dashboard.this, Settings.class);
//                startActivity(s);
//                break;

            case R.id.nav_logout:
//                loader.show();
                Intent l = new Intent(Dashboard.this, LoginActivity.class);
                startActivity(l);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_dashboard);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
