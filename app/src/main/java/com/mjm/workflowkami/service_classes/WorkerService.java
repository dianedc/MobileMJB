package com.mjm.workflowkami.service_classes;

import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.TaskClass;
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

public interface WorkerService {

    @Headers("Content-Type: application/json")
    @GET("worker/workers")
    Call<List<WorkerClass>> getAllWorkers();

    @GET("worker/{workersID}")
    Call<WorkerClass> getWorkerById(@Path("workersID") int workersID);

    @POST("worker/add")
    Call<WorkerClass> addWorker(@Body WorkerClass work);

    @POST("worker/update/{worker_id}")
    Call<Void> editWork(@Path("workersID") int workersID, @Body WorkerClass work);
}
