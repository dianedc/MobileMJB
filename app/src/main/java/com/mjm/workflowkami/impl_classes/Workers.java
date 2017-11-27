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

//        listofWorkers = (ListView) findViewById(R.id.lstWorkers);
//        serviceImpl.GetAllWorkers();
//        listofWorkers.setAdapter(new WorkerClassAdapter(this, serviceImpl.workerList));


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        Intent intent = getIntent();
        projectIntent = (ProjectClass) intent.getSerializableExtra("projects");
        if (projectIntent !=null) {
            Toast.makeText(Workers.this, String.valueOf(projectIntent.getProjID()), Toast.LENGTH_LONG).show();
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_worker);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId())
//                {
//                    case R.id.navigation_task:
//                        Intent n = new Intent(Workers.this, Tasks.class);
//                        startActivity(n);
//                        break;
//
////                    case R.id.navigation_team:
//////                        loader.show();
////                        Intent te = new Intent(Workers.this, Workers.class);
////                        startActivity(te);
////                        return true;
//
//                    case R.id.navigation_pr:
//                        Intent p = new Intent(Workers.this, Forms.class);
//                        startActivity(p);
//                        break;
//
//                    case R.id.navigation_po:
//                        Intent po =  new Intent(Workers.this, PurchaseOrder.class);
//                        startActivity(po);
//                        break;
//                }
//                return true;
//            }
//        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
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
        getMenuInflater().inflate(R.menu.workers, menu);
//        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == android.R.id.home) {
//            finish();
//            return true;
//        } else if (id == R.id.action_back_dtl_item) {
//            Intent add = new Intent(getApplicationContext(), Dashboard.class);
//            startActivity(add);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_dashboard:
                loader.show();
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
                loader.show();
                Intent p = new Intent(Workers.this, Projects.class);
                startActivity(p);
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
                loader.show();
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        public PlaceholderFragment() {
//        }
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_tabbed, container, false);
////            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
////            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
//            return rootView;
//        }
//    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {

                case 0:
                    ProjTeam projTeam = new ProjTeam();
                    return projTeam;
                case 1:
                    Worker worker = new Worker();
                    return worker;
                case 2:
                    Attendance attendance = new Attendance();
                    return attendance;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        public CharSequence getPageTitle (int position){
            switch (position){
                case 0:
                    return "Project Team";
                case 1:
                    return "Time In";
                case 2:
                    return "Time Out";

                default:
                    return null;
            }
        }
    }
}