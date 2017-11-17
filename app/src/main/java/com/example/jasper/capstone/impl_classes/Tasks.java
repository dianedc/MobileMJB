package com.example.jasper.capstone.impl_classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jasper.capstone.API;
import com.example.jasper.capstone.ServiceImpl;
import com.example.jasper.capstone.add_classes.AddTask;
import com.example.jasper.capstone.R;
import com.example.jasper.capstone.adapter_classes.TaskClassAdapter;
import com.example.jasper.capstone.model_classes.TaskClass;
import com.example.jasper.capstone.service_classes.TaskService;

import java.util.List;
import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class Tasks extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String TAG = Tasks.class.getSimpleName();
    private ListView listofTasks;
    private ServiceImpl serviceImpl = new ServiceImpl();
    List<TaskClass> tasksList = new ArrayList<TaskClass>();
    private TaskService taskService = API.getInstance().getTaskService();
    private SpotsDialog loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        loader = new SpotsDialog(Tasks.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listofTasks = (ListView) findViewById(R.id.lstTasks);
        serviceImpl.GetAllTasks();
        listofTasks.setAdapter(new TaskClassAdapter(this, serviceImpl.tasksList));

        if (listofTasks != null) {
            loader.dismiss();
        }
//        ListViewImpl();

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

                    case R.id.navigation_team:
                        Toast.makeText(Tasks.this, "Going to teams", Toast.LENGTH_LONG).show();
                        break;

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

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
            case R.id.nav_tasks:
//                Intent t = new Intent(Tasks.this, Tasks.class );
//                startActivity(t);
                break;
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
            case R.id.nav_files:
                loader.show();
                Intent fi = new Intent(Tasks.this, Files.class);
                startActivity(fi);
                break;
            case R.id.nav_reports:
                loader.show();
                Intent r = new Intent(Tasks.this, Reports.class);
                startActivity(r);
                break;
            case R.id.nav_users:
                loader.show();
                Intent u = new Intent(Tasks.this, Users.class);
                startActivity(u);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_tasks);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void ListViewImpl() {

        listofTasks.setAdapter(new TaskClassAdapter(this, serviceImpl.tasksList));
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
    }

}
