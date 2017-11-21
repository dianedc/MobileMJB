package com.mjm.workflowkami.add_classes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mjm.workflowkami.impl_classes.PurchaseOrder;
import com.mjm.workflowkami.R;

public class AddPurchaseOrder extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
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
        getMenuInflater().inflate(R.menu.add_purchase_order, menu);
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

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//        switch (id){
//            case R.id.nav_dashboard:
//                loader.show();
//                Intent d = new Intent(Tasks.this, Dashboard.class);
//                startActivity(d);
//                break;
////            case R.id.nav_tasks:
////                Intent t = new Intent(Tasks.this, Tasks.class );
////                startActivity(t);
////                break;
////            case R.id.nav_schedule:
////                loader.show();
////                Intent s = new Intent(Tasks.this, Schedule.class);
////                startActivity(s);
////                break;
//            case R.id.nav_project:
//                loader.show();
//                Intent p = new Intent(Tasks.this, Projects.class);
//                startActivity(p);
//                break;
////            case R.id.nav_purchaseRequest:
////                loader.show();
////                Intent f = new Intent(Tasks.this, Forms.class);
////                startActivity(f);
////                break;
////            case R.id.nav_purchaseOrder:
////                loader.show();
////                Intent e = new Intent(Tasks.this, PurchaseOrder.class);
////                startActivity(e);
////                break;
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
//            case R.id.nav_users:
//                loader.show();
//                Intent u = new Intent(Tasks.this, Users.class);
//                startActivity(u);
//                break;
//
//            case R.id.nav_workers:
//                loader.show();
//                Intent x = new Intent(Tasks.this, Workers.class);
//                startActivity(x);
//                break;
//
//            case R.id.nav_settings:
//                loader.show();
//                Intent s = new Intent(Tasks.this, Settings.class);
//                startActivity(s);
//                break;
//
//            case R.id.nav_logout:
//                loader.show();
//                Intent l = new Intent(Tasks.this, LoginActivity.class);
//                startActivity(l);
//                break;
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_tasks);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

    public void onClickCancel(View view) {
        Intent cancel = new Intent(AddPurchaseOrder.this, PurchaseOrder.class);
        startActivity(cancel);
    }
}
