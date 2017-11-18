package com.mjm.workflowkami.service_classes;

/**
 * Created by Jasper on 15 Sep 2017.
 */

import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PurchaseRequestService {

    @Headers("Content-Type: application/json")
    @GET("prequest/requests")
    Call<List<PurchaseRequestClass>> getAllPreq();

    @GET("prequest/{prequest_id}")
    Call<PurchaseRequestClass> getProjectById(@Path("prequest_id") int preq_id);

    @POST("prequest/add")
    Call<PurchaseRequestClass> addProject(@Body ProjectClass project);
}