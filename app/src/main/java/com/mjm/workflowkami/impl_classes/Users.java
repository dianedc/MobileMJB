package com.mjm.workflowkami.impl_classes;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.LoaderAsync;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.add_classes.AddUserr;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.adapter_classes.UserClassAdapter;
import com.mjm.workflowkami.model_classes.UserClass;
import com.mjm.workflowkami.service_classes.UserService;


import java.util.List;

import dmax.dialog.SpotsDialog;


public class Users extends LoaderAsync
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listOfUsers;
    private UserClass user;
    private ServiceImpl serviceImpl = new ServiceImpl();
    private UserService userService = API.getInstance().getUserService();
    private UserClass[] uc;
    private SpotsDialog loader;
    private TextView loggedInUser, loggedInEmail;
    private String email;
//    private UserClassAdapter adapter = new UserClassAdapter();

    public class UserTask extends AsyncTask<String, Void, List<UserClass>> {

        List<UserClass> cached;
        @Override
        protected void onPreExecute() {
            if (cached == null) {
                showLoadingDialog();
            } else {
                super.onPostExecute(cached);
            }
        }

        @Override
        protected List<UserClass> doInBackground(String... strings) {

            do {
                serviceImpl.GetAllUsers();
                try  {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (serviceImpl.usersList ==null);

            return serviceImpl.usersList;
        }

        @Override
        protected void onPostExecute(List<UserClass> userClassResponseEntity) {
            dismissProgressDialog();
            listOfUsers.setAdapter(new UserClassAdapter(Users.this, userClassResponseEntity));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users2);
        loader = new SpotsDialog(Users.this);
        loggedInUser = (TextView) findViewById(R.id.loggedInUsername);
        loggedInEmail = (TextView) findViewById(R.id.loggedInUseremail);
        listOfUsers = (ListView) findViewById(R.id.lstUsers);

        serviceImpl.GetAllUsers();
        listOfUsers.setAdapter(new UserClassAdapter(this, serviceImpl.usersList));

        new Users.UserTask().execute();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent add = new Intent(Users.this, AddUserr.class);
                startActivity(add);
            }
            });
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorLightBlue));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_users);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_users);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.users, menu);
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
//                loader.show();
                Intent d = new Intent(Users.this, Dashboard.class);
                startActivity(d);
                break;
//            case R.id.nav_tasks:
//                loader.show();
//                Intent t = new Intent(Users.this, Tasks.class);
//                startActivity(t);
//                break;
//            case R.id.nav_schedule:
//                loader.show();
//                Intent s = new Intent(Users.this, Schedule.class);
//                startActivity(s);
//                break;
            case R.id.nav_project:
//                loader.show();
                Intent p = new Intent(Users.this, Projects.class);
                startActivity(p);
                break;

            case R.id.nav_purchaseRequest:
                Intent f = new Intent(Users.this, Forms.class);
                startActivity(f);
                break;

            case R.id.nav_team:
//                loader.show();
                Intent x = new Intent(Users.this, AttendanceNav.class);
                startActivity(x);
                break;
//            case R.id.nav_purchaseRequest:
//                loader.show();
//                Intent f = new Intent(Users.this, Forms.class);
//                startActivity(f);
//                break;
//            case R.id.nav_purchaseOrder:
//                loader.show();
//                Intent e = new Intent(Users.this, PurchaseOrder.class);
//                startActivity(e);
//                break;
//            case R.id.nav_files:
////                loader.show();
//                Intent fi = new Intent(Users.this, Files.class);
//                startActivity(fi);
//                break;
//            case R.id.nav_reports:
//                loader.show();
//                Intent r = new Intent(Users.this, Reports.class);
//                startActivity(r);
//                break;
            case R.id.nav_users:
                Intent u = new Intent(Users.this, Users.class);
                startActivity(u);
                break;

//            case R.id.nav_workers:
////                loader.show();
//                Intent x = new Intent(Users.this, Workers.class);
//                startActivity(x);
//                break;

//            case R.id.nav_settings:
////                loader.show();
//                Intent s = new Intent(Users.this, Settings.class);
//                startActivity(s);
//                break;

            case R.id.nav_logout:
//                loader.show();
                Intent l = new Intent(Users.this, LoginActivity.class);
                startActivity(l);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_users);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
