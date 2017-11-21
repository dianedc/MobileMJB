package com.mjm.workflowkami.impl_classes;

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
import android.widget.ListView;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.adapter_classes.PurchaseRequestAdapter;
import com.mjm.workflowkami.add_classes.AddPRequest;
import com.mjm.workflowkami.model_classes.PurchaseRequestClass;
import com.mjm.workflowkami.service_classes.PurchaseRequestService;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class Forms extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private String TAG = Tasks.class.getSimpleName();
    private ListView listofPreq;
    private ServiceImpl serviceImpl = new ServiceImpl();
    private List<PurchaseRequestClass> preqList = new ArrayList<PurchaseRequestClass>();
    private PurchaseRequestService purchaseRequestService = API.getInstance().getPurchaseRequestService();
    private SpotsDialog loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listofPreq = (ListView) findViewById(R.id.lstPreqs);
        serviceImpl.GetAllPurchaseRequests();

        listofPreq.setAdapter(new PurchaseRequestAdapter(Forms.this, serviceImpl.prList));

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_pr);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.navigation_task:
                        Intent n = new Intent(Forms.this, Tasks.class);
                        startActivity(n);
                        break;

                    case R.id.navigation_team:
                        loader.show();
                        Intent te = new Intent(Forms.this, ProjectTeam.class);
                        startActivity(te);
                        return true;

                    case R.id.navigation_pr:
                        Intent p = new Intent(Forms.this, Forms.class);
                        startActivity(p);
                        break;

                    case R.id.navigation_po:
                        Intent po =  new Intent(Forms.this, PurchaseOrder.class);
                        startActivity(po);
                        break;

//                    case R.id.navigation_attendance:
//                        Toast.makeText(Forms.this, "Going to Attendance", Toast.LENGTH_LONG).show();
//                        break;
                }
                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(Forms.this, AddPRequest.class);
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
        getMenuInflater().inflate(R.menu.forms, menu);
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
                Intent d = new Intent(Forms.this, Dashboard.class);
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
                Intent p = new Intent(Forms.this, Projects.class);
                startActivity(p);
                break;
//            case R.id.nav_purchaseRequest:
////                Intent f = new Intent(Forms.this, Forms.class);
////                startActivity(f);
//                break;
//            case R.id.nav_purchaseOrder:
//                Intent e = new Intent(Forms.this, PurchaseOrder.class);
//                startActivity(e);
//                break;
            case R.id.nav_files:
                Intent fi = new Intent(Forms.this, Files.class);
                startActivity(fi);
                break;
            case R.id.nav_reports:
                Intent r = new Intent(Forms.this, Reports.class);
                startActivity(r);
                break;
            case R.id.nav_users:
                Intent u = new Intent(Forms.this, Users.class);
                startActivity(u);
                break;

            case R.id.nav_workers:
                loader.show();
                Intent x = new Intent(Forms.this, Workers.class);
                startActivity(x);
                break;

            case R.id.nav_settings:
                loader.show();
                Intent s = new Intent(Forms.this, Settings.class);
                startActivity(s);
                break;

            case R.id.nav_logout:
                loader.show();
                Intent l = new Intent(Forms.this, LoginActivity.class);
                startActivity(l);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void ListViewImpl() {

//        listofPreq.setAdapter(new PurchaseRequestAdapter(this, serviceImpl.purchaseRequestList));
//        listofPreq.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                PurchaseRequestClass purchaseRequestClass = (PurchaseRequestClass) serviceImpl.purchaseRequestList.get(position);
//
////                Toast.makeText(Forms.this,purchaseRequestClass.getProjectID().getProjectID(), Toast.LENGTH_SHORT).show();
////                Intent i = new Intent(getApplicationContext(), AddPurchaseRequest.class);
////                i.putExtra("preqs", purchaseRequestClass);
////                startActivity(i);
//
//            }
//        });
    }
}
