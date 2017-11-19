package com.mjm.workflowkami.impl_classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.adapter_classes.ProjectClassAdapter;
import com.mjm.workflowkami.add_classes.AddProject;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.service_classes.ProjectService;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Projects extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String TAG = Projects.class.getSimpleName();
    private ListView listOfProjects;
    private SpotsDialog loader;
    private ProjectService projectService = API.getInstance().getProjectService();
    List<ProjectClass> projectsList = new ArrayList<ProjectClass>();
    private ServiceImpl serviceImpl = new ServiceImpl();
    private List<String> strings = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        String[] arr = {"prj1", "proj2"};

        loader = new SpotsDialog(Projects.this);

        listOfProjects = (ListView) findViewById(R.id.lstProjects);
//        serviceImpl.GetAllProjects();
        Call<List<ProjectClass>> getProjects = projectService.getAllProjects();

        getProjects.enqueue(new Callback<List<ProjectClass>>() {
            @Override
            public void onResponse(Call<List<ProjectClass>> call, Response<List<ProjectClass>> response) {
                if (response.isSuccessful()) {
                    List<ProjectClass> projectClassList = response.body();

//                    Toast.makeText(Projects.this, response.body().toString(), Toast.LENGTH_LONG).show();
//                    Log.d(TAG, response.toString());
                    try {
                        for (int i = 0; i < projectClassList.size(); i++) {
                            projectsList.add(new ProjectClass(projectClassList.get(i).getProjID(),
                                    projectClassList.get(i).getProjname(),
                                    projectClassList.get(i).getProjclient(),
                                    projectClassList.get(i).getProjdesc(),
                                    projectClassList.get(i).getProjtype(),
                                    projectClassList.get(i).getProjstartdate(),
                                    projectClassList.get(i).getProjenddate(),
                                    projectClassList.get(i).getProjdatecompleted(),
                                    projectClassList.get(i).getProjstatus(),
                                    projectClassList.get(i).getProjmanager(),
                                    projectClassList.get(i).getProjcontractbudget(),
                                    projectClassList.get(i).getProjtargetbudget(),
                                    projectClassList.get(i).getProjprogress(),
                                    projectClassList.get(i).getProjduration()));
//                            strings.add(projectClassList.get(i).getProjname());

                        }
                    } catch (final Exception e) { e.printStackTrace(); }
                }
            }
            @Override
            public void onFailure(Call<List<ProjectClass>> call, Throwable t) {
                Toast.makeText(Projects.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
        Toast.makeText(Projects.this, projectsList.toString(), Toast.LENGTH_LONG).show();
        listOfProjects.setAdapter(new ProjectClassAdapter(Projects.this, projectsList));
        loader.dismiss();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent add = new Intent(Projects.this, AddProject.class);
                    startActivity(add);
            }
        });
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorLightBlue));

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
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
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
                loader.show();
                Intent d = new Intent(Projects.this, Dashboard.class);
                startActivity(d);
                break;
            case R.id.nav_project:
                break;
            case R.id.nav_files:
                Intent fi = new Intent(Projects.this, Files.class);
                startActivity(fi);
                break;
            case R.id.nav_reports:
                Intent r = new Intent(Projects.this, Reports.class);
                startActivity(r);
                break;
            case R.id.nav_users:
                Intent u = new Intent(Projects.this, Users.class);
                startActivity(u);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
