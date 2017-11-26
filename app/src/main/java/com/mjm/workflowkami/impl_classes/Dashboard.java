package com.mjm.workflowkami.impl_classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.mjm.workflowkami.Fragments.Worker;
import com.mjm.workflowkami.LoaderAsync;
import com.mjm.workflowkami.R;

import dmax.dialog.SpotsDialog;

public class Dashboard extends LoaderAsync
        implements NavigationView.OnNavigationItemSelectedListener{

//    static final String EXTRA_MAP = "map";
//    static final LauncherIcon[] ICONS = {
//            new LauncherIcon(R.drawable.ic_menu_project, "Projects", "ic_launcher.png"),
//            new LauncherIcon(R.drawable.ic_menu_task, "Tasks", "ic_launcher.png"),
//            new LauncherIcon(R.drawable.ic_menu_task, "Tasks", "ic_launcher.png"),
//            new LauncherIcon(R.drawable.ic_menu_task, "Tasks", "ic_launcher.png"),
//    };
    private SpotsDialog loader;

//    private class ProjectTask extends AsyncTask<String, Void, List<ProjectClass>> {
//
//        @Override
//        protected void onPreExecute() {
//            showLoadingDialog();
//        }
//
//        @Override
//        protected List<ProjectClass> doInBackground(String... strings) {
////            while (serviceImpl.projectsList != null) {
////                serviceImpl.GetAllProjects();
////                try  {
////                    Thread.sleep(5000);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////            }
//            do {
//                serviceImpl.GetAllProjects();
//                try  {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            } while (serviceImpl.projectsList == null);
//            return serviceImpl.projectsList;
//        }
//        @Override
//        protected void onPostExecute(List<ProjectClass> projClassResponseEntity) {
//            dismissProgressDialog();
//            listOfProjects.setAdapter(new ProjectClassAdapter(Projects.this, projClassResponseEntity));
//        }
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        loader = new SpotsDialog(Dashboard.this);
        Button myButton = (Button) findViewById(R.id.btnProjects);
//        myButton.setText("19");
//        loader.dismiss();


//        GridView gridview = (GridView) findViewById(R.id.dashboard_grid);
//        gridview.setAdapter(new ImageAdapter(this));
////        gridview.setOnItemClickListener(this);
//
////        gridview.setOnTouchListener(new OnTouchListener() {
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//            {
//                Intent i = new Intent(Dashboard.class, ProjectsDashboard.class);
//            }

//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return event.getAction() == MotionEvent.ACTION_MOVE;
//            }
//        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//        Intent intent = new Intent(this, MapActivity.class);
//        intent.putExtra(EXTRA_MAP, ICONS[position].map);
//        startActivity(intent);
//    }
//
//    static class LauncherIcon {
//        final String text;
//        final int imgId;
//        final String map;
//
//        public LauncherIcon(int imgId, String text, String map) {
//            super();
//            this.imgId = imgId;
//            this.text = text;
//            this.map = map;
//        }
//
//    }
//    static class ImageAdapter extends BaseAdapter {
//        private Context mContext;
//
//        public ImageAdapter(Context c) {
//            mContext = c;
//        }
//
//        @Override
//        public int getCount() {
//            return ICONS.length;
//        }
//
//        @Override
//        public LauncherIcon getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//        static class ViewHolder {
//            public ImageView icon;
//            public TextView text;
//        }
//
//        // Create a new ImageView for each item referenced by the Adapter
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View v = convertView;
//            ViewHolder holder;
//            if (v == null) {
//                LayoutInflater vi = (LayoutInflater) mContext.getSystemService(
//                        Context.LAYOUT_INFLATER_SERVICE);
//
//                v = vi.inflate(R.layout.dashboard_icon, null);
//                holder = new ViewHolder();
//                holder.text = (TextView) v.findViewById(R.id.dashboard_icon_text);
//                holder.icon = (ImageView) v.findViewById(R.id.dashboard_icon_img);
//                v.setTag(holder);
//            } else {
//                holder = (ViewHolder) v.getTag();
//            }
//
//            holder.icon.setImageResource(ICONS[position].imgId);
//            holder.text.setText(ICONS[position].text);
//
//            return v;
//        }
//    }

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
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//
////        switch (item.getItemId()){
//////        int id = item.getItemId();
//////        switch (id){
////            case R.id.menu_user:
//////                Intent d = new Intent(Dashboard.this, Settings.class);
//////                startActivity(d);
//////                return true;
////                startActivity(new Intent(this, Settings.class));
////                return true;
////
////            case R.id.menu_logout:
////                logoutUser();
////                return true;
////            default:
////                return super.onOptionsItemSelected(item);
////        }
//        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            return true;
////        }
//
////        return super.onOptionsItemSelected(item);
//    }

//    private void logoutUser() {
//        session.setLogin(false);

//        db.deleteUsers();

        // Launching the login activity
//        Intent intent = new Intent(Dashboard.this, LoginActivity.class);
//        startActivity(intent);
//        finish();
//    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_dashboard:
//                Intent d = new Intent(Dashboard.this, Dashboard.class);
//                startActivity(d);
                break;
//            case R.id.nav_tasks:
//                loader.show();
//                Intent t = new Intent(Dashboard.this, Tasks.class );
//                startActivity(t);
//                break;
//            case R.id.nav_schedule:
//                loader.show();
//                Intent s = new Intent(Dashboard.this, Schedule.class);
//                startActivity(s);
//                break;
            case R.id.nav_project:
//                loader.show();
                Intent p = new Intent(Dashboard.this, Projects.class);
                startActivity(p);
                break;
            case R.id.nav_purchaseRequest:
//                loader.show();
                Intent f = new Intent(Dashboard.this, Forms.class);
                startActivity(f);
                break;
            case R.id.nav_purchaseOrder:
//                loader.show();
                Intent e = new Intent(Dashboard.this, PurchaseOrder.class);
                startActivity(e);
                break;
            case R.id.nav_files:
//                loader.show();
                Intent fi = new Intent(Dashboard.this, Files.class);
                startActivity(fi);
                break;
            case R.id.nav_reports:
//                loader.show();
                Intent r = new Intent(Dashboard.this, Reports.class);
                startActivity(r);
                break;
            case R.id.nav_users:
//                loader.show();
                Intent u = new Intent(Dashboard.this, Users.class);
                startActivity(u);
                break;

            case R.id.nav_workers:
//                loader.show();
                Intent x = new Intent(Dashboard.this, Workers.class);
                startActivity(x);
                break;

            case R.id.nav_settings:
//                loader.show();
                Intent s = new Intent(Dashboard.this, Settings.class);
                startActivity(s);
                break;

            case R.id.nav_logout:
//                loader.show();
                Intent l = new Intent(Dashboard.this, LoginActivity.class);
                startActivity(l);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
