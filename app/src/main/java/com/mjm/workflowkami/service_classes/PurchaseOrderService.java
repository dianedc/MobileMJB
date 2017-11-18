package com.mjm.workflowkami.service_classes;

import com.mjm.workflowkami.model_classes.PurchaseOrderClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Jasper on 22 Oct 2017.
 */

public interface PurchaseOrderService {

    @Headers("Content-Type: application/json")
    @GET("porder/orders")
    Call<List<PurchaseOrderClass>> getAllPord();

    @GET("pord/{pord_id}")
    Call<PurchaseOrderClass> getTaskById(@Path("pord_id") int task_id);

    @POST("pord/add")
    Call<PurchaseOrderClass> addTask(@Body PurchaseOrderClass task);
}
