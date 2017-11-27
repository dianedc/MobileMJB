package com.mjm.workflowkami.impl_classes;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.baoyz.widget.PullRefreshLayout;
import com.mjm.workflowkami.API;
import com.mjm.workflowkami.Fragments.ProjTeam;
import com.mjm.workflowkami.LoaderAsync;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.adapter_classes.TaskClassAdapter;
import com.mjm.workflowkami.add_classes.AddTask;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.TaskClass;
import com.mjm.workflowkami.service_classes.TaskService;

import java.util.List;
import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class Tasks extends LoaderAsync
        implements NavigationView.OnNavigationItemSelectedListener {

//    private String TAG = Tasks.class.getSimpleName();
    private ListView listofTasks;
    private ServiceImpl serviceImpl = new ServiceImpl();
    private ProjectClass projectIntent = new ProjectClass();
//    List<TaskClass> tasksList = new ArrayList<TaskClass>();
//    private TaskService taskService = API.getInstance().getTaskService();
    private SpotsDialog loader;
    private PullRefreshLayout layout;

    private class ProjectTask extends AsyncTask<String, Void, List<TaskClass>> {

        @Override
        protected void onPreExecute() {
            showLoadingDialog();
        }

        @Override
        protected List<TaskClass> doInBackground(String... strings) {
//            while (serviceImpl.projectsList != null) {
//                serviceImpl.GetAllProjects();
//                try  {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            Intent intent = getIntent();
            projectIntent = (ProjectClass) intent.getSerializableExtra("projects");
            do {
                if (projectIntent != null) {
                    serviceImpl.GetTaskByProjId(projectIntent.getProjID());
                }
                try  {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (serviceImpl.tasksList == null);
            return serviceImpl.tasksList;
        }
        @Override
        protected void onPostExecute(List<TaskClass> taskClassResponseEntity) {
            dismissProgressDialog();
            listofTasks.setAdapter(new TaskClassAdapter(Tasks.this, taskClassResponseEntity));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        loader = new SpotsDialog(Tasks.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        projectIntent = (ProjectClass) intent.getSerializableExtra("projects");

        listofTasks = (ListView) findViewById(R.id.lstTasks);
//        if (projectIntent != null) {
//            serviceImpl.GetTaskByProjId(projectIntent.getProjID());
//        }
//        listofTasks.setAdapter(new TaskClassAdapter(this, serviceImpl.tasksList));

        if (projectIntent != null) {
            final String uri = "http://servicemjm-env.ap-southeast-1.elasticbeanstalk.com/project/" + projectIntent.getProjID() + "/task";
            new ProjectTask().execute(uri);
        }
        layout = (PullRefreshLayout) findViewById(R.id.refreshTask);
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            Intent add = new Intent(Tasks.this, AddTask.class);
//            startActivity(add);
//
//            }
//        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_tasks);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.navigation_task:
                        Intent n = new Intent(Tasks.this, Tasks.class);
                        startActivity(n);
                        break;

//                    case R.id.navigation_team:
////                        loader.show();
//                        Intent te = new Intent(Tasks.this, Workers.class);
//                        startActivity(te);
//                        return true;

                    case R.id.navigation_pr:
                        Intent p = new Intent(Tasks.this, Forms.class);
                        startActivity(p);
                        break;

                    case R.id.navigation_po:
                        Intent po =  new Intent(Tasks.this, PurchaseOrder.class);
                        startActivity(po);
                        break;
                }
                return true;
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_tasks);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_tasks);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tasks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_dashboard:
                loader.show();
                Intent d = new Intent(Tasks.this, Dashboard.class);
                startActivity(d);
                break;
//            case R.id.nav_tasks:
//                Intent t = new Intent(Tasks.this, Tasks.class );
//                startActivity(t);
//                break;
//            case R.id.nav_schedule:
//                loader.show();
//                Intent s = new Intent(Tasks.this, Schedule.class);
//                startActivity(s);
//                break;
            case R.id.nav_project:
                loader.show();
                Intent p = new Intent(Tasks.this, Projects.class);
                startActivity(p);
                break;

            case R.id.nav_team:
//                loader.show();
                Intent x = new Intent(Tasks.this, AttendanceNav.class);
                startActivity(x);
                break;
//            case R.id.nav_purchaseRequest:
//                loader.show();
//                Intent f = new Intent(Tasks.this, Forms.class);
//                startActivity(f);
//                break;
//            case R.id.nav_purchaseOrder:
//                loader.show();
//                Intent e = new Intent(Tasks.this, PurchaseOrder.class);
//                startActivity(e);
//                break;
//            case R.id.nav_files:
//                loader.show();
//                Intent fi = new Intent(Tasks.this, Files.class);
//                startActivity(fi);
//                break;
//            case R.id.nav_reports:
//                loader.show();
//                Intent r = new Intent(Tasks.this, Reports.class);
//                startActivity(r);
//                break;
            case R.id.nav_users:
                loader.show();
                Intent u = new Intent(Tasks.this, Users.class);
                startActivity(u);
                break;

//            case R.id.nav_workers:
//                loader.show();
//                Intent x = new Intent(Tasks.this, Workers.class);
//                startActivity(x);
//                break;

//            case R.id.nav_settings:
//                loader.show();
//                Intent s = new Intent(Tasks.this, Settings.class);
//                startActivity(s);
//                break;

            case R.id.nav_logout:
                loader.show();
                Intent l = new Intent(Tasks.this, LoginActivity.class);
                startActivity(l);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_tasks);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public void ListViewImpl() {

//        listofTasks.setAdapter(new TaskClassAdapter(this, serviceImpl.tasksList));
//        listofTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                TaskClass taskClass = (TaskClass) serviceImpl.tasksList.get(position);
//
//                Intent i = new Intent(getApplicationContext(), AddTask.class);
//                i.putExtra("tasks", taskClass);
////                Toast.makeText(getApplicationContext(), taskClass.getTaskname(), Toast.LENGTH_LONG).show();
//                startActivity(i);
//
//            }
//        });
//    }

}
