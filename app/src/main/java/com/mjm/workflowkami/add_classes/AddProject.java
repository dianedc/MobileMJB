package com.mjm.workflowkami.add_classes;

import android.content.Intent;
import android.os.Bundle;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.UserClass;
import com.mjm.workflowkami.service_classes.ProjectService;
import com.mjm.workflowkami.impl_classes.Projects;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.service_classes.UserService;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddProject extends AppCompatActivity
        implements OnClickListener {



    private EditText projProgress, projName, projID, fromDate, toDate, projClient, projDesc, dateComp, projCBudget, projTBudget, projDuration, projStatus, projType;
    private TextView projMan;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private ProjectClass projectIntent;

//    private SpinnerDialog projManDialog;
//    private ProjectClass project;
//    private Button projManager, saveProject;
//    private String selectedProjMan;
//    private ServiceImpl serviceImpl = new ServiceImpl();
//    private UserClass userClass1 = new UserClass();
//    private List<UserClass> userClass2 = new ArrayList<UserClass>();
//    private ArrayList<String> listUs = new ArrayList<String>();
//    private int projid;
//    ArrayList<String> projclass = new ArrayList<String>();
//    Toast t;
//    Intent i;
//    private ProjectService projectService = API.getInstance().getProjectService();
//    private UserService userService = API.getInstance().getUserService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        serviceImpl.GetAllUserId();
//        serviceImpl.GetAllUsers();

//        saveProject = (Button) findViewById(R.id.btnSaveProject);
        projID = (EditText) findViewById(R.id.project_id);
        projName = (EditText) findViewById(R.id.project_name);
        projStatus = (EditText) findViewById(R.id.project_status);
        projClient = (EditText) findViewById(R.id.proj_client);
        projDesc = (EditText) findViewById(R.id.proj_desc);
        dateComp = (EditText) findViewById(R.id.proj_date_comp);
        projCBudget = (EditText) findViewById(R.id.proj_c_budget);
        projTBudget = (EditText) findViewById(R.id.proj_t_budget);
        projDuration = (EditText) findViewById(R.id.proj_duration);
        projType = (EditText) findViewById(R.id.proj_type);
        projProgress = (EditText) findViewById(R.id.project_progress);
        fromDate = (EditText) findViewById(R.id.project_start_date);
        toDate = (EditText) findViewById(R.id.project_end_date);
        projMan = (TextView) findViewById(R.id.viewProjMan);
        dateFormatter = new SimpleDateFormat("yyyy-dd-MM", Locale.US);


        projProgress.setEnabled(false);
        projName.setEnabled(false);
        projID.setEnabled(false);
        fromDate.setEnabled(false);
        toDate.setEnabled(false);
        projClient.setEnabled(false);
        projDesc.setEnabled(false);
        dateComp.setEnabled(false);
        projCBudget.setEnabled(false);
        projTBudget.setEnabled(false);
        projDuration.setEnabled(false);
        projStatus.setEnabled(false);
        projType.setEnabled(false);
//        projManager = (Button) findViewById(R.id.proj_manager);
//        projManager.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                projManDialog.showSpinerDialog();
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

        Intent intent = getIntent();
        projectIntent = (ProjectClass) intent.getSerializableExtra("projects");


        if (projectIntent != null) {
            projID.setText(String.valueOf(projectIntent.getProjID()));
            projName.setText(projectIntent.getProjname());
            projClient.setText(projectIntent.getProjclient());
            projDesc.setText(projectIntent.getProjdesc());
            dateComp.setText(projectIntent.getProjdatecompleted());

            fromDate.setText(projectIntent.getProjstartdate());
            toDate.setText(projectIntent.getProjenddate());

            projStatus.setText(projectIntent.getProjstatus());
            projType.setText(projectIntent.getProjtype());
//            for (int i = 0; i < projStatus.getCount(); i++) {
//                if (projStatus.getItemAtPosition(i).toString().equals(projectIntent.getProjstatus())) {
//                    projStatus.setSelection(i);
//                    break;
//                }
//            }

//            for (int i = 0; i < projType.getCount(); i++) {
//                if (projType.getItemAtPosition(i).toString().equals(projectIntent.getProjtype())) {
//                    projType.setSelection(i);
//                    break;
//                }
//            }
            projMan.setText(projectIntent.getProjmanager().getLastname() + ", " + projectIntent.getProjmanager().getFirstname());
            projProgress.setText(projectIntent.getProjprogress().toString());
            projCBudget.setText(String.valueOf(projectIntent.getProjcontractbudget()));
            projTBudget.setText(String.valueOf(projectIntent.getProjtargetbudget()));
            projDuration.setText(projectIntent.getProjduration());
        }
//        Call<List<UserClass>> getUsers = userService.getAllUsers();
//
//        getUsers.enqueue(new Callback<List<UserClass>>() {
//            @Override
//            public void onResponse(Call<List<UserClass>> call, Response<List<UserClass>> response) {
//                if (response.isSuccessful()) {
//                    List<UserClass> userClassList = response.body();
//
//                    try {
//                        for (int i = 0; i < userClassList.size(); i++) {
//                            listUs.add(String.valueOf(userClassList.get(i).getUserID()) + " " +
//                                    userClassList.get(i).getLastname() +", "+
//                                    userClassList.get(i).getFirstname());
//                        }
//                    } catch (final Exception e) { e.printStackTrace(); }
//                }
//            }
//            @Override
//            public void onFailure(Call<List<UserClass>> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//        projManDialog = new SpinnerDialog(AddProject.this, listUs, "Select Project Manager");
//        projManDialog.bindOnSpinerListener(new OnSpinerItemClick() {
//            @Override
//            public void onClick(String s, int i) {
//                userClass1 = serviceImpl.usersList.get(i);
//                viewProjMan.setText(s);
//            }
//        });

//        saveProject.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (!projID.getText().toString().matches("")) {
//                    //update
//                    Toast.makeText(AddProject.this, "inside update", Toast.LENGTH_SHORT).show();
//                    project = new ProjectClass(Integer.valueOf(projID.getText().toString()),
//                            projname.getText().toString().trim(),
//                            fromDate.getText().toString().trim(),
//                            toDate.getText().toString().trim(),
//                            projStatus.getSelectedItem().toString().trim(),
//                            userClass1,
//                            Double.parseDouble(projProgress.getText().toString()));
//
//                    Toast.makeText(AddProject.this, project.toString(), Toast.LENGTH_SHORT).show();
//                    UpdateProject(projectIntent.getProjID(), project);
//                } else {
//                    //add
//                    Toast.makeText(AddProject.this, "inside add", Toast.LENGTH_SHORT).show();
//                    project = new ProjectClass(projname.getText().toString().trim(),
//                            fromDate.getText().toString().trim(),
//                            toDate.getText().toString().trim(),
//                            projStatus.getSelectedItem().toString().trim(),
//                            userClass1,
//                            Double.parseDouble(projProgress.getText().toString()));
//                    AddProjects(project);
//                }
//            }
//        });

        findViewsById();
        setDateTimeField();

    }
    private void findViewsById() {
        fromDate = (EditText) findViewById(R.id.project_start_date);
        fromDate.setInputType(InputType.TYPE_NULL);
//      fromDate.requestFocus();

        toDate = (EditText) findViewById(R.id.project_end_date);
        toDate.setInputType(InputType.TYPE_NULL);

        projName = (EditText) findViewById(R.id.project_name);
        projName.setInputType(InputType.TYPE_NULL);
//        projname.requestFocus();

    }

    private void setDateTimeField() {
        fromDate.setOnClickListener(this);
        toDate.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View view) {
        if(view == fromDate) {
            fromDatePickerDialog.show();
        } else if(view == toDate) {
            toDatePickerDialog.show();
        }
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        }else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_project, menu);
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
        if (id == R.id.action_back_proj) {
            startActivity(new Intent(this, Projects.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item)  {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//        switch (id){
//            case R.id.nav_dashboard:
//                Intent d = new Intent(AddProject.this, Dashboard.class);
//                startActivity(d);
//                break;
////            case R.id.nav_tasks:
////                Intent t = new Intent(AddProject.this, Tasks.class );
////                startActivity(t);
////                break;
////            case R.id.nav_schedule:
////                Intent s = new Intent(AddProject.this, Schedule.class);
////                startActivity(s);
////                break;
//            case R.id.nav_project:
//                Intent p = new Intent(AddProject.this, Projects.class);
//                startActivity(p);
//                break;
////            case R.id.nav_purchaseRequest:
////                Intent f = new Intent(AddProject.this, Forms.class);
////                startActivity(f);
////                break;
////            case R.id.nav_purchaseOrder:
////                Intent e = new Intent(AddProject.this, PurchaseOrder.class);
////                startActivity(e);
////                break;
//            case R.id.nav_files:
//                Intent fi = new Intent(AddProject.this, Files.class);
//                startActivity(fi);
//                break;
////            case R.id.nav_reports:
////                Intent r = new Intent(AddProject.this, Reports.class);
////                startActivity(r);
////                break;
//            case R.id.nav_users:
//                Intent u = new Intent(AddProject.this, Users.class);
//                startActivity(u);
//                break;
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

    public void onClickCancel(View v) {
        Intent cancel = new Intent(AddProject.this, Projects.class);
        startActivity(cancel);
    }

//    private void AddProjects(ProjectClass p) {
//        Call<ProjectClass> addProject = projectService.addProject(p);
//
//        addProject.enqueue(new Callback<ProjectClass>() {
//            @Override
//            public void onResponse(Call<ProjectClass> call, Response<ProjectClass> response) {
//
//                if (response.isSuccessful()) {
//
//                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddProject.this);
//                    alertDialogBuilder.setMessage(response.toString());
//                    alertDialogBuilder.setCancelable(true);
//                    alertDialogBuilder.show();
//                    Toast.makeText(AddProject.this, response.toString(), Toast.LENGTH_LONG).show();
//                    Intent i = new Intent(AddProject.this, Projects.class);
//                    startActivity(i);
//                }
//            }
//            @Override
//            public void onFailure(Call<ProjectClass> call, Throwable t) {
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddProject.this);
//                alertDialogBuilder.setMessage(t.toString());
//                alertDialogBuilder.setCancelable(true);
//                alertDialogBuilder.show();
//                Toast.makeText(AddProject.this, t.toString(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    private void UpdateProject(int id, ProjectClass p) {
//        Call<Void> editProject = projectService.editProject(id, p);
//
//        editProject.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (response.isSuccessful()) {
//
//                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddProject.this);
//                    alertDialogBuilder.setMessage(response.toString());
//                    alertDialogBuilder.setCancelable(true);
//                    alertDialogBuilder.show();
//                    Toast.makeText(AddProject.this, response.toString(), Toast.LENGTH_LONG).show();
//                    Intent i = new Intent(AddProject.this, Projects.class);
//                    startActivity(i);
//                }
//            }
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddProject.this);
//                alertDialogBuilder.setMessage(t.toString());
//                alertDialogBuilder.setCancelable(true);
//                alertDialogBuilder.show();
//                Toast.makeText(AddProject.this, t.toString(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }
}
