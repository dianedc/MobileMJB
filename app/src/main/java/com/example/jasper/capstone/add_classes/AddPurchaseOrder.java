package com.example.jasper.capstone.add_classes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jasper.capstone.impl_classes.Forms;
import com.example.jasper.capstone.impl_classes.Dashboard;
import com.example.jasper.capstone.impl_classes.Files;
import com.example.jasper.capstone.impl_classes.Projects;
import com.example.jasper.capstone.impl_classes.PurchaseOrder;
import com.example.jasper.capstone.R;
import com.example.jasper.capstone.impl_classes.Reports;
import com.example.jasper.capstone.impl_classes.Schedule;
import com.example.jasper.capstone.impl_classes.Tasks;
import com.example.jasper.capstone.impl_classes.Users;

public class AddPurchaseOrder extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
        getMenuInflater().inflate(R.menu.add_purchase_order, menu);
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
        switch (id) {
            case R.id.nav_dashboard:
                Intent d = new Intent(AddPurchaseOrder.this, Dashboard.class);
                startActivity(d);
                break;
            case R.id.nav_tasks:
                Intent t = new Intent(AddPurchaseOrder.this, Tasks.class);
                startActivity(t);
                break;
            case R.id.nav_schedule:
                Intent s = new Intent(AddPurchaseOrder.this, Schedule.class);
                startActivity(s);
                break;
            case R.id.nav_project:
                Intent p = new Intent(AddPurchaseOrder.this, Projects.class);
                startActivity(p);
                break;
            case R.id.nav_purchaseRequest:
                Intent f = new Intent(AddPurchaseOrder.this, Forms.class);
                startActivity(f);
                break;
            case R.id.nav_purchaseOrder:
                Intent e = new Intent(AddPurchaseOrder.this, PurchaseOrder.class);
                startActivity(e);
                break;
            case R.id.nav_files:
                Intent fi = new Intent(AddPurchaseOrder.this, Files.class);
                startActivity(fi);
                break;
            case R.id.nav_reports:
                Intent r = new Intent(AddPurchaseOrder.this, Reports.class);
                startActivity(r);
                break;
            case R.id.nav_users:
                Intent u = new Intent(AddPurchaseOrder.this, Users.class);
                startActivity(u);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void onClickCancel(View view) {
        Intent cancel = new Intent(AddPurchaseOrder.this, PurchaseOrder.class);
        startActivity(cancel);
    }
}
