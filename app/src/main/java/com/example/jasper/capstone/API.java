package com.example.jasper.capstone;
/**
 * Created by Jasper on 1 Sep 2017.
 */

import com.example.jasper.capstone.impl_classes.PurchaseOrder;
import com.example.jasper.capstone.model_classes.PurchaseRequestItemClass;
import com.example.jasper.capstone.service_classes.ProjectService;
import com.example.jasper.capstone.service_classes.PurchaseOrderService;
import com.example.jasper.capstone.service_classes.PurchaseRequestItemService;
import com.example.jasper.capstone.service_classes.PurchaseRequestService;
import com.example.jasper.capstone.service_classes.TaskService;
import com.example.jasper.capstone.service_classes.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class API {
    public final String BASE_URL = "http://192.168.2.108:8081/rest/";
//    public final String BASE_URL = "http://192.168.2.144:8081/rest/";
//    public final String BASE_URL = "http://192.168.2.144:8080/";
//    public final String BASE_URL = "http://172.20.10.5:8081/rest/";
    //    public final String BASE_URL = "http://10.1.15.97:8081/rest/";
    private static API instance = null;

    private UserService userService;
    private TaskService taskService;
    private ProjectService projectService;
    private PurchaseRequestItemService purchaseRequestItemService;
    private PurchaseOrderService purchaseOrderService;
    private PurchaseRequestService purchaseRequestService;

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
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BASE_URL).build();
        this.userService = retrofit.create(UserService.class);
        this.taskService = retrofit.create(TaskService.class);
        this.projectService = retrofit.create(ProjectService.class);
        this.purchaseRequestService = retrofit.create(PurchaseRequestService.class);
        this.purchaseOrderService = retrofit.create(PurchaseOrderService.class);
        this.purchaseRequestItemService = retrofit.create(PurchaseRequestItemService.class);
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
}