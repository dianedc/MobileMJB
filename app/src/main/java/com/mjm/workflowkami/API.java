package com.mjm.workflowkami;
/**
 * Created by Jasper on 1 Sep 2017.
 */

import com.mjm.workflowkami.service_classes.AttendanceService;
import com.mjm.workflowkami.service_classes.ProjectService;
import com.mjm.workflowkami.service_classes.ProjectTeamService;
import com.mjm.workflowkami.service_classes.PurchaseOrderService;
import com.mjm.workflowkami.service_classes.PurchaseRequestItemService;
import com.mjm.workflowkami.service_classes.PurchaseRequestService;
import com.mjm.workflowkami.service_classes.TaskAssignedService;
import com.mjm.workflowkami.service_classes.TaskService;
import com.mjm.workflowkami.service_classes.UserService;
import com.mjm.workflowkami.service_classes.WorkerService;

import java.net.URLEncoder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class API {
//    public final String BASE_URL = "http://192.168.2.108:8081/rest/";
//    public final String BASE_URL = "http://192.168.2.123:8083/rest/";
//    public final String BASE_URL = "http://10.1.12.136:8083/rest/";
//    public final String BASE_URL = "http://192.168.0.22:8083/rest/";
//    public final String BASE_URL = "http://192.168.2.107:8083/rest/";
//    public final String BASE_URL = "http://192.168.250.26:8083/rest/";
//    public final String BASE_URL = "http://servicemjm-env.ap-southeast-1.elasticbeanstalk.com/";
//    public final String BASE_URL = "http://192.168.2.144:8080/";
    public final String BASE_URL = "http://172.20.10.5:8083/rest/";
    //    public final String BASE_URL = "http://10.1.15.97:8081/rest/";
//    public final String BASE_URL = "http://192.168.254.108:8083/rest/";
//    public final String BASE_URL = "http://192.168.2.144:8083/";
    private static API instance = null;

    private UserService userService;
    private TaskService taskService;
    private ProjectService projectService;
    private PurchaseRequestItemService purchaseRequestItemService;
    private PurchaseOrderService purchaseOrderService;
    private PurchaseRequestService purchaseRequestService;
    private ProjectTeamService projectTeamService;
    private WorkerService workerService;
    private AttendanceService attendanceService;
    private TaskAssignedService taskAssignedService;

    public static API getInstance() {
        if (instance == null) {
            instance = new API();
        }
        return instance;
    }

    private API() {
        buildRetrofit();
    }

    private void buildRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();
        this.userService = retrofit.create(UserService.class);
        this.taskService = retrofit.create(TaskService.class);
        this.projectService = retrofit.create(ProjectService.class);
        this.purchaseRequestService = retrofit.create(PurchaseRequestService.class);
        this.purchaseOrderService = retrofit.create(PurchaseOrderService.class);
        this.purchaseRequestItemService = retrofit.create(PurchaseRequestItemService.class);
        this.projectTeamService = retrofit.create(ProjectTeamService.class);
        this.workerService = retrofit.create(WorkerService.class);
        this.attendanceService = retrofit.create(AttendanceService.class);
        this.taskAssignedService = retrofit.create(TaskAssignedService.class);

    }

    public UserService getUserService() {
        return this.userService;
    }

    public TaskService getTaskService() {
        return this.taskService;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public PurchaseRequestService getPurchaseRequestService() {
        return purchaseRequestService;
    }

    public PurchaseOrderService getPurchaseOrderService() {
        return this.purchaseOrderService;
    }

    public PurchaseRequestItemService getPurchaseRequestItemService() {return this.purchaseRequestItemService; }

    public ProjectTeamService getProjectTeamService () { return this.projectTeamService; }

    public WorkerService getWorkerService() { return this.workerService; }

    public AttendanceService getAttendanceService() { return this.attendanceService; }

    public TaskAssignedService getTaskAssignedService() { return this.taskAssignedService; }
}