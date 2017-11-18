package com.mjm.workflowkami.impl_classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

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


public class Projects extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String TAG = Projects.class.getSimpleName();
    private ListView listOfProjects;
    private SpotsDialog loader;
    //private ArrayAdapter<String> adapter;
    private ProjectService projectService = API.getInstance().getProjectService();
    private ProjectClass project;
    //private Object[] projectClassList;
    List<ProjectClass> projectsList = new ArrayList<ProjectClass>();
    private ServiceImpl serviceImpl = new ServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loader = new SpotsDialog(Projects.this);

        listOfProjects = (ListView) findViewById(R.id.lstProjects);
        serviceImpl.GetAllProjects();
        listOfProjects.setAdapter(new ProjectClassAdapter(this, serviceImpl.projectsList));
        loader.dismiss();

//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId())
//                {
//                    case R.id.navigation_task:
//                        Intent n = new Intent(Projects.this, Tasks.class);
//                        startActivity(n);
//                        break;
//
//                    case R.id.navigation_team:
//                        Toast.makeText(Projects.this, "Going to teams", Toast.LENGTH_LONG).show();
//                        break;
//
//                    case R.id.navigation_pr:
//                        Intent p = new Intent(Projects.this, Forms.class);
//                        startActivity(p);
//                        break;
//
//                    case R.id.navigation_po:
//                        Intent po =  new Intent(Projects.this, PurchaseOrder.class);
//                        startActivity(po);
//                        break;
//                }
//                return true;
//            }
//        });
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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
                loader.show();
                Intent d = new Intent(Projects.this, Dashboard.class);
                startActivity(d);
                break;
//            case R.id.nav_tasks:
//                Intent t = new Intent(Projects.this, Tasks.class );
//                startActivity(t);
//                break;
//            case R.id.nav_schedule:
//                Intent s = new Intent(Projects.this, Schedule.class);
//                startActivity(s);
//                break;
            case R.id.nav_project:
//                Intent p = new Intent(Projects.this, Projects.class);
//                startActivity(p);
//                getAllProjects();
//                adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, projectClassList);
//                listOfProjects.setAdapter(adapter);
                break;
//            case R.id.nav_purchaseRequest:
//                Intent f = new Intent(Projects.this, Forms.class);
//                startActivity(f);
//                break;
//            case R.id.nav_purchaseOrder:
//                Intent e = new Intent(Projects.this, PurchaseOrder.class);
//                startActivity(e);
//                break;
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
