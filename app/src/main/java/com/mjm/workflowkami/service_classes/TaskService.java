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

    @Headers("Content-Type: application/json")
    @GET("project/task/tasks/active")
    Call<List<TaskClass>> getAllActive();

    @GET("project/task/tasks/pending")
    Call<List<TaskClass>> getAllPending();

    @GET("project/task/tasks/completed")
    Call<List<TaskClass>> getAllCompleted();

    @GET("project/task/{task_id}")
    Call<TaskClass> getTaskById(@Path("task_id") int task_id);

    //GET TASKS UNDER CON PHASE
    @GET("{proj_id}/tasks/con")
    Call<List<TaskClass>> getTaskByCon(@Path("proj_id") int proj_id);

    //GET TASKS UNDER CON PHASE
    @GET("{proj_id}/tasks/precon")
    Call<List<TaskClass>> getTaskByPreCon(@Path("proj_id") int proj_id);

    //GET TASKS UNDER CON PHASE
    @GET("{proj_id}/tasks/postcon")
    Call<List<TaskClass>> getTaskByPostCon(@Path("proj_id") int proj_id);

    //FIND ALL TASKS BY PROJECT ID
    @GET("project/{proj_id}/task")
    Call<List<TaskClass>> getTaskByProjId(@Path("proj_id") int proj_id);

    //ADD TASK
    @POST("{proj_id}/task/add")
    Call<TaskClass> addTask(@Path("proj_id") int proj_id, @Body TaskClass task);

    //EDIT TASKn
    @POST("{proj_id}/task/update/{task_id}")
    Call<Void> editTask(@Path("proj_id") int proj_id, @Path("task_id") int taskID, @Body TaskClass task);

    //SET TASK TO COMPLETE
    @POST("project/task/complete/{task_id}")
    Call<Void> completeTask(@Path("task_id") int task_id);

    //SET TASK TO START
    @POST("project/task/start/{task_id}")
    Call<Void> startTask(@Path("task_id") int task_id);
}
