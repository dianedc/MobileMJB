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

    @GET("prequest/{proj_id}/request")
    Call<List<PurchaseRequestClass>> getAllPreqByProj(@Path("proj_id") int proj_id);

    @POST("changestatus/{id}")
    Call<PurchaseRequestClass> changeStatus(@Path("id") int preq_id);

    @GET("prequest/{prequest_id}")
    Call<PurchaseRequestClass> getPreqById(@Path("prequest_id") int preq_id);

    @POST("prequest/add")
    Call<PurchaseRequestClass> addPreq(@Body PurchaseRequestClass project);

    @POST("prequest/update/{preq_id}")
    Call<Void> editPreq(@Path("preq_id") int preq_id, @Body PurchaseRequestClass project);
}