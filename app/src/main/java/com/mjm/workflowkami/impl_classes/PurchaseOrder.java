package com.mjm.workflowkami.impl_classes;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
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
import com.mjm.workflowkami.LoaderAsync;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.adapter_classes.PurchaseOrderAdapter;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.adapter_classes.PurchaseRequestAdapter;
import com.mjm.workflowkami.add_classes.AddPurchaseOrder;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.PurchaseOrderClass;
import com.mjm.workflowkami.service_classes.PurchaseOrderService;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PurchaseOrder extends LoaderAsync
        implements NavigationView.OnNavigationItemSelectedListener {

    private String TAG = Tasks.class.getSimpleName();
    private ListView listsofPord;
    private ServiceImpl serviceImpl = new ServiceImpl();
    List<PurchaseOrderClass> pordList = new ArrayList<PurchaseOrderClass>();
    private PurchaseOrderService purchaseOrderService = API.getInstance().getPurchaseOrderService();
    private SpotsDialog loader;
    private ProjectClass projectIntent = new ProjectClass();

    private class ProjectTask extends AsyncTask<String, Void, List<PurchaseOrderClass>> {

        @Override
        protected void onPreExecute() {
            showLoadingDialog();
        }

        @Override
        protected List<PurchaseOrderClass> doInBackground(String... strings) {
            do {
                serviceImpl.GetAllPurchaseOrder();
                try  {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (pordList == null);
            return pordList;
        }
        @Override
        protected void onPostExecute(List<PurchaseOrderClass> taskClassResponseEntity) {
            dismissProgressDialog();
            listsofPord.setAdapter(new PurchaseOrderAdapter(PurchaseOrder.this, taskClassResponseEntity));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listsofPord = (ListView) findViewById(R.id.lstPords);
        loader = new SpotsDialog(PurchaseOrder.this);
        new ProjectTask().execute();

//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_po);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId())
//                {
//                    case R.id.navigation_task:
//                        Intent n = new Intent(PurchaseOrder.this, Tasks.class);
//                        startActivity(n);
//                        break;
//
//                    case R.id.navigation_team:
//                        loader.show();
//                        Intent t = new Intent(PurchaseOrder.this, ProjectTeam.class);
//                        startActivity(t);
//                        return true;
//
//                    case R.id.navigation_pr:
//                        Intent p = new Intent(PurchaseOrder.this, Forms.class);
//                        startActivity(p);
//                        break;
//
//                    case R.id.navigation_po:
//                        Intent po =  new Intent(PurchaseOrder.this, PurchaseOrder.class);
//                        startActivity(po);
//                        break;
//                }
//                return true;
//            }
//        });
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
        getMenuInflater().inflate(R.menu.purchase_order, menu);
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
        switch (id) {
            case R.id.nav_dashboard:
                Intent d = new Intent(PurchaseOrder.this, Dashboard.class);
                startActivity(d);
                break;
//            case R.id.nav_tasks:
//                Intent t = new Intent(PurchaseOrder.this, Tasks.class);
//                startActivity(t);
//                break;
//            case R.id.nav_schedule:
//                Intent s = new Intent(PurchaseOrder.this, Schedule.class);
//                startActivity(s);
//                break;
            case R.id.nav_project:
                Intent p = new Intent(PurchaseOrder.this, Projects.class);
                startActivity(p);
                break;
            case R.id.nav_team:
//                loader.show();
                Intent x = new Intent(PurchaseOrder.this, AttendanceNav.class);
                startActivity(x);
                break;

//            case R.id.nav_purchaseRequest:
//                Intent f = new Intent(PurchaseOrder.this, Forms.class);
//                startActivity(f);
//                break;
//            case R.id.nav_purchaseOrder:
//                Intent e = new Intent(PurchaseOrder.this, PurchaseOrder.class);
//                startActivity(e);
//                break;
//            case R.id.nav_files:
//                Intent fi = new Intent(PurchaseOrder.this, Files.class);
//                startActivity(fi);
//                break;
//            case R.id.nav_reports:
//                Intent r = new Intent(PurchaseOrder.this, Reports.class);
//                startActivity(r);
//                break;
            case R.id.nav_users:
                Intent u = new Intent(PurchaseOrder.this, Users.class);
                startActivity(u);
                break;

//            case R.id.nav_workers:
//                loader.show();
//                Intent x = new Intent(PurchaseOrder.this, Workers.class);
//                startActivity(x);
//                break;

//            case R.id.nav_settings:
//                loader.show();
//                Intent s = new Intent(PurchaseOrder.this, Settings.class);
//                startActivity(s);
//                break;

            case R.id.nav_logout:
//                loader.show();
                Intent l = new Intent(PurchaseOrder.this, LoginActivity.class);
                startActivity(l);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
