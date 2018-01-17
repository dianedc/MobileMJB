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
import com.mjm.workflowkami.adapter_classes.AttendanceNavClassAdapter;
import com.mjm.workflowkami.adapter_classes.ProjectClassAdapter;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.service_classes.ProjectService;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;


public class AttendanceNav extends LoaderAsync
        implements NavigationView.OnNavigationItemSelectedListener {

    private PullRefreshLayout layout;
    private SwipeRefreshLayout refreshLayout;
    private String TAG = AttendanceNav.class.getSimpleName();
    private ListView listOfProjects;
    private SpotsDialog loader;
    private ProjectService projectService = API.getInstance().getProjectService();
    List<ProjectClass> pl;
    private ServiceImpl serviceImpl = new ServiceImpl();
    private List<String> strings = new ArrayList<String>();
    private ProjectClassAdapter adapter;
    private Toolbar supportActionBar;

    private class ProjectTask extends AsyncTask<String, Void, List<ProjectClass>> {

        @Override
        protected void onPreExecute() {
            showLoadingDialog();
        }

        @Override
        protected List<ProjectClass> doInBackground(String... strings) {
//            while (serviceImpl.projectsList != null) {
//                serviceImpl.GetAllProjects();
//                try  {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            do {
                serviceImpl.GetAllProjects();
                try  {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (serviceImpl.projectsList == null);
            return serviceImpl.projectsList;
        }
        @Override
        protected void onPostExecute(List<ProjectClass> projClassResponseEntity) {
            dismissProgressDialog();
            listOfProjects.setAdapter(new AttendanceNavClassAdapter(AttendanceNav.this, projClassResponseEntity));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarProj);
        setTitle("Projects");
        setSupportActionBar(toolbar);

        loader = new SpotsDialog(AttendanceNav.this);
        listOfProjects = (ListView) findViewById(R.id.lstAttendanceNav);

        //final String uri = "http://servicemjm-env.ap-southeast-1.elasticbeanstalk.com/project/projects";
        new ProjectTask().execute();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_projects);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_projects);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_back_proj) {
//            startActivity(new Intent(this, Users.class));
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_dashboard:
                Intent d = new Intent(AttendanceNav.this, Dashboard.class);
                startActivity(d);
                break;
            case R.id.nav_project:
                Intent xp = new Intent(AttendanceNav.this, Projects.class);
                startActivity(xp);
                break;

            case R.id.nav_team:
                Intent x = new Intent(AttendanceNav.this, AttendanceNav.class);
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
                Intent u = new Intent(AttendanceNav.this, Users.class);
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
                Intent l = new Intent(AttendanceNav.this, LoginActivity.class);
                startActivity(l);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setSupportActionBar(Toolbar supportActionBar) {
        this.supportActionBar = supportActionBar;
    }

}
