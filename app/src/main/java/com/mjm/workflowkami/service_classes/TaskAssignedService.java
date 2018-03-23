package com.mjm.workflowkami.service_classes;

/**
 * Created by Jasper on 13 Sep 2017.
 */
import com.mjm.workflowkami.impl_classes.TaskAssigned;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.TaskAssignedClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TaskAssignedService {

    //RETURNS WORKERS
    @Headers("Content-Type: application/json")
    @GET("project/{projectID}/task/{taskID}/assigned")
    Call<List<TaskAssignedClass>> getAllWorkersAssigned(@Path("projectID") int projectID, @Path("taskID") int taskID);

    @POST("project/{projectID}/task/{taskID}/assigned/add")
    Call<TaskAssignedClass> addTaskAssigned(@Path("projectID") int projectID, @Path("taskID") int taskID, @Body TaskAssignedClass assigned);

    @POST("project/task/assigned/update/{assignedID}")
    Call<Void> updateTaskAssigned(@Path("assignedID") int assignedID, @Body TaskAssignedClass assigned);

    @GET("project/assigned/{assignedID}")
    Call<TaskAssignedClass> getTaskAssignedByID(@Path("assignedID") int assignedID);

    @GET("project/assigned")
    Call<List<TaskAssignedClass>> getAllAssignedTasks();

    //RETURNS TASK ASSIGNED
    @GET("project/{projectID}/task/{taskID}/assigned/{assignedID}")
    Call<TaskAssignedClass> getTaskAssignedByProjectAndTask(@Path("projectID") int projectID, @Path("taskID") int taskID, @Path("assignedID") int assignedID);
}
