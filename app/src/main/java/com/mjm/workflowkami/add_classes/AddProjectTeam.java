package com.mjm.workflowkami.add_classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.impl_classes.ProjectTeam;
import com.mjm.workflowkami.model_classes.ProjectTeamClass;
import com.mjm.workflowkami.model_classes.UserClass;
import com.mjm.workflowkami.service_classes.ProjectService;
import com.mjm.workflowkami.service_classes.ProjectTeamService;
import com.mjm.workflowkami.service_classes.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProjectTeam extends AppCompatActivity {

    private Button saveTeam;
    private EditText projteamID;
    private EditText projectID;
    private EditText userID;
    private EditText workersID;

    private List<String> array;
    private ProjectTeamClass team;
    private ProjectTeamService projectTeamService = API.getInstance().getProjectTeamService();
    private ProjectService projectService = API.getInstance().getProjectService();
    private UserService userService = API.getInstance().getUserService();
    private ServiceImpl serviceImpl = new ServiceImpl();
    private ProjectTeamClass teamIntent = new ProjectTeamClass();
    private UserClass userClass1 = new UserClass();
    private List<UserClass> userClass2 = new ArrayList<UserClass>();
    private ArrayList<String> listUs = new ArrayList<String>();


    Toast t;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        projteamID = (EditText) findViewById(R.id.projectTeam_id);
        projectID = (EditText) findViewById(R.id.project_id);
        userID = (EditText) findViewById(R.id.user_id);

        saveTeam = (Button) findViewById(R.id.btnSaveProjectTeam);

        serviceImpl.GetAllUserId();
        serviceImpl.GetAllProjects();

        Intent intent = getIntent();
        teamIntent = (ProjectTeamClass) intent.getSerializableExtra("teams");

        if (teamIntent != null) {
            projteamID.setText(String.valueOf(teamIntent.getProjteamID()));
            projectID.setText(teamIntent.getProjectsprojID().getProjID());
            userID.setText(teamIntent.getUserID().getUserID());

        }

        Call<List<UserClass>> getUsers = userService.getAllUsers();

        getUsers.enqueue(new Callback<List<UserClass>>() {
            @Override
            public void onResponse(Call<List<UserClass>> call, Response<List<UserClass>> response) {
                if (response.isSuccessful()) {
                    List<UserClass> userClassList = response.body();

                    try {
                        for (int i = 0; i < userClassList.size(); i++) {
                            listUs.add(String.valueOf(userClassList.get(i).getUserID()) + " " +
                                    userClassList.get(i).getLastname() +", "+
                                    userClassList.get(i).getFirstname());
                        }
                    } catch (final Exception e) { e.printStackTrace(); }
                }
            }
            @Override
            public void onFailure(Call<List<UserClass>> call, Throwable t) {
                t.printStackTrace();
            }
        });
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
        getMenuInflater().inflate(R.menu.add_project_team, menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                startActivity(new Intent(this, ProjectTeam.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickCancel(View v) {
        Intent cancel = new Intent(AddProjectTeam.this, ProjectTeam.class);
        startActivity(cancel);
    }

    public void AddTeam(ProjectTeamClass t) {
        Call<ProjectTeamClass> addTeam = projectTeamService.addProjectTeam(t);

        addTeam.enqueue(new Callback<ProjectTeamClass>() {
            @Override
            public void onResponse(Call<ProjectTeamClass> call, Response<ProjectTeamClass> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddProjectTeam.this, "Team has been successfully added!", Toast.LENGTH_SHORT).show();

                    Intent u = new Intent(AddProjectTeam.this, ProjectTeam.class);
                    startActivity(u);
                }
            }
            @Override
            public void onFailure(Call<ProjectTeamClass> call, Throwable t) {
                Toast.makeText(AddProjectTeam.this, "An error has been encountered while adding team", Toast.LENGTH_SHORT);
            }
        });
    }

    public void UpdateTeam(int id, ProjectTeamClass t) {
        Call<ProjectTeamClass> addTeam = projectTeamService.editTeam(id, t);

        addTeam.enqueue(new Callback<ProjectTeamClass>() {
            @Override
            public void onResponse(Call<ProjectTeamClass> call, Response<ProjectTeamClass> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddProjectTeam.this, "Team has been successfully edited!", Toast.LENGTH_SHORT).show();

                    Intent u = new Intent(AddProjectTeam.this, ProjectTeam.class);
                    startActivity(u);
                }
            }
            @Override
            public void onFailure(Call<ProjectTeamClass> call, Throwable t) {
                Toast.makeText(AddProjectTeam.this, "An error has been encountered while editing team", Toast.LENGTH_SHORT);
            }
        });
    }
}
