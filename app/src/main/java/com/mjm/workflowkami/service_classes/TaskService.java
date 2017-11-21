package com.mjm.workflowkami.service_classes;

/**
 * Created by Jasper on 13 Sep 2017.
 */

import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.TaskClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TaskService {

    @Headers("Content-Type: application/json")
    @GET("project/tasks")
    Call<List<TaskClass>> getAllTasks();

    @GET("project/{task_id}")
    Call<TaskClass> getTaskById(@Path("task_id") int task_id);

    @GET("project/{proj_id}/task")
    Call<ProjectClass> getProjectById(@Path("proj_id") int proj_id);

    @POST("task/add")
    Call<TaskClass> addTask(@Body TaskClass task);

    @POST("task/update/{task_id}")
    Call<TaskClass> editTask(@Path("task_id") int taskID, @Body TaskClass task);
}
