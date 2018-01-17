package com.mjm.workflowkami.service_classes;

/**
 * Created by Jasper on 13 Sep 2017.
 */
import com.mjm.workflowkami.model_classes.ProjectClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProjectService {

    @Headers("Content-Type: application/json")
//    @FormUrlEncoded
    @GET("project/projects")
    Call<List<ProjectClass>> getAllProjects();

    @GET("project/{project_id}")
    Call<ProjectClass> getProjectById(@Path("project_id") int project_id);

    @POST("project/add")
    Call<ProjectClass> addProject(@Body ProjectClass project);

    @POST("project/update/{proj_id}")
    Call<Void> editProject(@Path("proj_id") int proj_id, @Body ProjectClass proj);

    @POST("project/{projID}/team/{projteamID}/attendance/timein")
    Call<Void> workerTimeIn(@Path("projID") int proj_id, @Path("projteamID") int projteamID);

    @POST("project/{projID}/team/{projteamID}/attendance/timeout")
    Call<Void> workerTimeOut(@Path("projID") int proj_id, @Path("projteamID") int projteamID);

    @GET("project/projects/active")
    Call<List<ProjectClass>> getAllActiveProjects();
}
