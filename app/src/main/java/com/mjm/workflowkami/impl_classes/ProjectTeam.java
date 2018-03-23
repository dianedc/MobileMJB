package com.mjm.workflowkami.impl_classes;

import android.app.ProgressDialog;
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

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.Fragments.ProjTeam;
import com.mjm.workflowkami.LoaderAsync;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.adapter_classes.ProjTeamClassAdapter;
import com.mjm.workflowkami.adapter_classes.ProjectTeamAdapter;
import com.mjm.workflowkami.add_classes.AddProjectTeam;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.ProjectTeamClass;
import com.mjm.workflowkami.service_classes.ProjectTeamService;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class ProjectTeam extends LoaderAsync
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listofTeam;
    private ServiceImpl serviceImpl = new ServiceImpl();
    private ProjectClass projectIntent = new ProjectClass();

    private class ProjTeamTask extends AsyncTask<String, Void, List<ProjectTeamClass>> {

        @Override
        protected void onPreExecute() {
            showLoadingDialog();
        }

        @Override
        protected List<ProjectTeamClass> doInBackground(String... strings) {
            Intent intent = getIntent();
            projectIntent = (ProjectClass) intent.getSerializableExtra("projects");

            do {

//                serviceImpl.GetAllProjTeams();
                if (projectIntent != null) {
                    serviceImpl.GetUsersTeamById(projectIntent.getProjID());
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
            dismissProgressDialog();
            ProjTeamClassAdapter adapter = new ProjTeamClassAdapter(ProjectTeam.this, workerClassResponseEntity);
//            setListAdapter(adapter);
            listofTeam.setAdapter(adapter);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        projectIntent = (ProjectClass) intent.getSerializableExtra("projects");

        listofTeam = (ListView) findViewById(R.id.lstTeams);
        if (projectIntent != null) {
            new ProjTeamTask().execute();
        }

//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_team);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId())
//                {
//                    case R.id.navigation_task:
//                        Intent n = new Intent(ProjectTeam.this, Tasks.class);
//                        startActivity(n);
//                        break;
//
//                    case R.id.navigation_team:
////                        loader.show();
//                        Intent te = new Intent(ProjectTeam.this, ProjectTeam.class);
//                        startActivity(te);
//                        return true;
//
//                    case R.id.navigation_pr:
//                        Intent p = new Intent(ProjectTeam.this, Forms.class);
//                        startActivity(p);
//                        break;
//
//                    case R.id.navigation_po:
//                        Intent po =  new Intent(ProjectTeam.this, PurchaseOrder.class);
//                        startActivity(po);
//                        break;
//                }
//                return true;
//            }
//        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(ProjectTeam.this, AddProjectTeam.class);
                startActivity(add);
            }
        });
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorLightBlue));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.project_team, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
        switch (id) {
            case R.id.nav_dashboard:
                Intent d = new Intent(ProjectTeam.this, Dashboard.class);
                startActivity(d);
                break;
////            case R.id.nav_tasks:
////                Intent t = new Intent(Forms.this, Tasks.class );
////                startActivity(t);
////                break;
//            case R.id.nav_schedule:
//                Intent s = new Intent(Forms.this, Schedule.class);
//                startActivity(s);
//                break;
            case R.id.nav_project:
                Intent p = new Intent(ProjectTeam.this, Projects.class);
                startActivity(p);
                break;

            case R.id.nav_purchaseRequest:
                Intent f = new Intent(ProjectTeam.this, Forms.class);
                startActivity(f);
                break;
            case R.id.nav_team:
//                loader.show();
                Intent x = new Intent(ProjectTeam.this, AttendanceNav.class);
                startActivity(x);
                break;
//            case R.id.nav:
//                loader.show();
////                Intent f = new Intent(Forms.this, Forms.class);
////                startActivity(f);
//                break;
////            case R.id.nav_purchaseOrder:
////                Intent e = new Intent(Forms.this, PurchaseOrder.class);
////                startActivity(e);
////                break;
//            case R.id.nav_files:
//                loader.show();
//                Intent fi = new Intent(ProjectTeam.this, Files.class);
//                startActivity(fi);
//                break;
//            case R.id.nav_reports:
//                loader.show();
//                Intent r = new Intent(ProjectTeam.this, Reports.class);
//                startActivity(r);
//                break;
            case R.id.nav_users:
                Intent u = new Intent(ProjectTeam.this, Users.class);
                startActivity(u);
                break;

//            case R.id.nav_workers:
//                loader.show();
//                Intent x = new Intent(ProjectTeam.this, Workers.class);
//                startActivity(x);
//                break;

//            case R.id.nav_settings:
//                loader.show();
//                Intent s = new Intent(ProjectTeam.this, Settings.class);
//                startActivity(s);
//                break;

            case R.id.nav_logout:
                Intent l = new Intent(ProjectTeam.this, LoginActivity.class);
                startActivity(l);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

