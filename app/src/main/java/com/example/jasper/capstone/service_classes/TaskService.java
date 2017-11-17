package com.example.jasper.capstone.service_classes;

/**
 * Created by Jasper on 13 Sep 2017.
 */

import com.example.jasper.capstone.model_classes.TaskClass;
import com.example.jasper.capstone.model_classes.UserClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TaskService {

    @Headers("Content-Type: application/json")
    @GET("task/tasks")
    Call<List<TaskClass>> getAllTasks();

    @GET("task/{task_id}")
    Call<TaskClass> getTaskById(@Path("task_id") int task_id);

    @POST("task/add")
    Call<TaskClass> addTask(@Body TaskClass task);

    @POST("task/update/{task_id}")
    Call<TaskClass> editTask(@Path("task_id") int taskID, @Body TaskClass task);

}
