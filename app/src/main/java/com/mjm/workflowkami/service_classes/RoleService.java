package com.mjm.workflowkami.service_classes;

import com.mjm.workflowkami.model_classes.RoleClass;
import com.mjm.workflowkami.model_classes.WorkerClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by admin on 21 Nov 2017.
 */

public interface RoleService {

    @Headers("Content-Type: application/json")
    @GET("role/roles")
    Call<List<RoleClass>> getAllRoles();

    @GET("role/roles/{roleID}")
    Call<RoleClass> getRoleById(@Path("roleID") int roleID);

    @GET("role/roles/all/{roleID}")
    Call<List<Object>> getByRoles(@Path("roleID") int roleID);
}
