package com.mjm.workflowkami.service_classes;

import com.mjm.workflowkami.model_classes.ProjectClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by admin on 26 Nov 2017.
 */

public interface AttendanceService {
    @Headers("Content-Type: application/json")

    @POST("project/{projteamID}/attendance")
    Call<List<ProjectClass>> getProjTeamId(@Path("projteamID") int projteamID);


}
