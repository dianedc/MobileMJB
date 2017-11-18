package com.mjm.workflowkami.service_classes;

import com.mjm.workflowkami.model_classes.ProjectTeamClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by admin on 13 Nov 2017.
 */

public interface ProjectTeamService {

    @Headers("Content-Type: application/json")
    @GET("project/teams")
    Call<List<ProjectTeamClass>> getAllProjTeams();

    @GET("project/{projteamID}")
    Call<ProjectTeamClass> getProjectTeamById(@Path("projteamID") int projteamID);

    @POST("project/add")
    Call<ProjectTeamClass> addProjectTeam(@Body ProjectTeamClass projectTeam);

    @POST("project/update/{projteamID}")
    Call<ProjectTeamClass> editTeam(@Path("projteamID") int projteamID, @Body ProjectTeamClass team);
}
