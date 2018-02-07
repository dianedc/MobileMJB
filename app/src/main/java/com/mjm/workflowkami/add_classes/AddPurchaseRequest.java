package com.mjm.workflowkami.add_classes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.mjm.workflowkami.R;

import java.text.SimpleDateFormat;

public class AddPurchaseRequest extends AppCompatActivity
        {

    private EditText preq_daterequested;
    private SimpleDateFormat dateFormat;
    private Button savePReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        savePReq = (Button) findViewById(R.id.btnSavePReq);
//        preq_daterequested = (EditText) findViewById(R.id.preq_daterequested);
        dateFormat= new SimpleDateFormat("yyyy-dd-MM");
//        preq_daterequested.setText(dateFormat.format(new Date()));


//        btnAddPItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddPurchaseRequest.this);
//                View mView = getLayoutInflater().inflate(R.layout.dialog_preq_add, null);
//                EditText item = (EditText) mView.findViewById(R.id.txtPReqItem);
//                EditText quantity = (EditText) mView.findViewById(R.id.txtPReqQuantity);
//                EditText amount = (EditText) mView.findViewById(R.id.txtPReqAmount);
//                Button addItem = (Button) mView.findViewById(R.id.btnAddMore);
//
//                addItem.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//            }
//        });
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_purchase_request, menu);
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

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.nav_dashboard:
//                Intent d = new Intent(AddPurchaseRequest.this, Dashboard.class);
//                startActivity(d);
//                break;
////            case R.id.nav_tasks:
////                Intent t = new Intent(AddPurchaseRequest.this, Tasks.class);
////                startActivity(t);
////                break;
////            case R.id.nav_schedule:
////                Intent s = new Intent(AddPurchaseRequest.this, Schedule.class);
////                startActivity(s);
////                break;
//            case R.id.nav_project:
//                Intent p = new Intent(AddPurchaseRequest.this, Projects.class);
//                startActivity(p);
//                break;
////            case R.id.nav_purchaseRequest:
////                Intent f = new Intent(AddPurchaseRequest.this, Forms.class);
////                startActivity(f);
////                break;
////            case R.id.nav_purchaseOrder:
////                Intent e = new Intent(AddPurchaseRequest.this, PurchaseOrder.class);
////                startActivity(e);
////                break;
//            case R.id.nav_files:
//                Intent fi = new Intent(AddPurchaseRequest.this, Files.class);
//                startActivity(fi);
//                break;
//            case R.id.nav_reports:
//                Intent r = new Intent(AddPurchaseRequest.this, Reports.class);
//                startActivity(r);
//                break;
//            case R.id.nav_users:
//                Intent u = new Intent(AddPurchaseRequest.this, Users.class);
//                startActivity(u);
//                break;
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

    public void onClickCancel(View view) {
//        Intent cancel = new Intent(AddPurchaseRequest.this, Forms.class);
//        startActivity(cancel);
    }

    public void onClickSavePrequest(View view){



    }

    public void AddPurchaseRequestImpl() {

    }
}
