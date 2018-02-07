package com.mjm.workflowkami.impl_classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.Fragments.Attendance;
import com.mjm.workflowkami.Fragments.ProjTeam;
import com.mjm.workflowkami.Fragments.Worker;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.WorkerClass;
import com.mjm.workflowkami.service_classes.WorkerService;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class Workers extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String TAG = Tasks.class.getSimpleName();
    private ListView listofWorkers;
    private ServiceImpl serviceImpl = new ServiceImpl();
    List<WorkerClass> workerList = new ArrayList<WorkerClass>();
    private WorkerService workerService = API.getInstance().getWorkerService();

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private SpotsDialog loader;
    private ViewPager mViewPager;
    private ProjectClass projectIntent = new ProjectClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar();
        loader = new SpotsDialog(Workers.this);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container_workers);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_workers);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        Intent intent = getIntent();
        projectIntent = (ProjectClass) intent.getSerializableExtra("projects");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_workers);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_workers);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.workers, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_dashboard:
//                loader.show();
                Intent d = new Intent(Workers.this, Dashboard.class);
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
                Intent p = new Intent(Workers.this, Projects.class);
                startActivity(p);
                break;

            case R.id.nav_purchaseRequest:
                Intent f = new Intent(Workers.this, Forms.class);
                startActivity(f);
                break;
            case R.id.nav_team:
//                loader.show();
                Intent x = new Intent(Workers.this, AttendanceNav.class);
                startActivity(x);
                break;
//            case R.id.nav_forms:
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
//                loader.show();
//                Intent fi = new Intent(Workers.this, Files.class);
//                startActivity(fi);
//                break;
//            case R.id.nav_reports:
//                loader.show();
//                Intent r = new Intent(Workers.this, Reports.class);
//                startActivity(r);
//                break;
            case R.id.nav_users:
//                loader.show();
                Intent u = new Intent(Workers.this, Users.class);
                startActivity(u);
                break;

//            case R.id.nav_settings:
//                loader.show();
//                Intent s = new Intent(Workers.this, Settings.class);
//                startActivity(s);
//                break;

            case R.id.nav_logout:
                loader.show();
                Intent l = new Intent(Workers.this, LoginActivity.class);
                startActivity(l);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_workers);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {

//                case 0:
//                    ProjTeam projTeam = new ProjTeam();
//                    return projTeam;
                case 0:
                    Worker worker = new Worker();
                    return worker;
                case 1:
                    Attendance attendance = new Attendance();
                    return attendance;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        public CharSequence getPageTitle (int position){
            switch (position){
//                case 0:
//                    return "Project Team";
                case 0:
                    return "Time In";
                case 1:
                    return "Time Out";

                default:
                    return null;
            }
        }
    }
}