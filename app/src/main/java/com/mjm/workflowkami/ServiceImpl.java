package com.mjm.workflowkami;


import android.util.Log;

import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestItemClass;
import com.mjm.workflowkami.model_classes.TaskClass;
import com.mjm.workflowkami.model_classes.UserClass;
import com.mjm.workflowkami.service_classes.ProjectService;
import com.mjm.workflowkami.service_classes.PurchaseRequestItemService;
import com.mjm.workflowkami.service_classes.PurchaseRequestService;
import com.mjm.workflowkami.service_classes.TaskService;
import com.mjm.workflowkami.service_classes.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DC on 24/09/2017.
 */

public class ServiceImpl {
    private String TAG = ServiceImpl.class.getSimpleName();

    private UserService userService = API.getInstance().getUserService();
    private TaskService taskService = API.getInstance().getTaskService();
    private ProjectService projectService = API.getInstance().getProjectService();
    private PurchaseRequestService purchaseRequestService = API.getInstance().getPurchaseRequestService();
    private PurchaseRequestItemService pItemService = API.getInstance().getPurchaseRequestItemService();
    public List<UserClass> usersList;
    public ArrayList<String> userIDList = new ArrayList<String>();
    public List<TaskClass> tasksList = new ArrayList<TaskClass>();
    public List<ProjectClass> projectsList = new ArrayList<ProjectClass>();
    public UserClass userClass = new UserClass();;
    public List<PurchaseRequestClass> prList = new ArrayList<PurchaseRequestClass>();
    public List<PurchaseRequestItemClass> pItemList = new ArrayList<PurchaseRequestItemClass>();

    public List<UserClass> GetAllUsers() {

        usersList = new ArrayList<UserClass>();
        Call<List<UserClass>> getUsers = userService.getAllUsers();

        getUsers.enqueue(new Callback<List<UserClass>>() {
            @Override
            public void onResponse(Call<List<UserClass>> call, Response<List<UserClass>> response) {
                if (response.isSuccessful()) {
                    List<UserClass> userClassList = response.body();
                    try {
                        for (int i = 0; i < userClassList.size(); i++) {
                            usersList.add(new UserClass(userClassList.get(i).getUserID(),
                                    userClassList.get(i).getFirstname(),
                                    userClassList.get(i).getLastname(),
                                    userClassList.get(i).getMiddlename(),
                                    userClassList.get(i).getEmail(),
                                    userClassList.get(i).getPassword(),
                                    userClassList.get(i).getUserstatus()));
                        }
                    } catch (final Exception e) { e.printStackTrace(); }
                }
            }
            @Override
            public void onFailure(Call<List<UserClass>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return usersList;
    }

    public UserClass FetchUserById(int id) {

        userClass = new UserClass();
        Call<UserClass> getUserID = userService.getUserById(id);
        getUserID.enqueue(new Callback<UserClass>() {
            @Override
            public void onResponse(Call<UserClass> call, Response<UserClass> response) {
                if(response.isSuccessful()) {
                    UserClass uc = response.body();
                    userClass = new UserClass(uc.getUserID(),
                            uc.getFirstname(),
                            uc.getLastname(),
                            uc.getMiddlename(),
                            uc.getEmail(),
                            uc.getPassword(),
                            uc.getUserstatus());
                }
            }
            @Override
            public void onFailure(Call<UserClass> call, Throwable t) { t.printStackTrace(); }
        });
        return userClass;
    }

    public ArrayList<String> GetAllUserId() {

        userIDList = new ArrayList<String>();
        Call<List<UserClass>> getUsers = userService.getAllUsers();

        getUsers.enqueue(new Callback<List<UserClass>>() {
            @Override
            public void onResponse(Call<List<UserClass>> call, Response<List<UserClass>> response) {
                if (response.isSuccessful()) {
                    List<UserClass> userClassList = response.body();

                    try {
                        for (int i = 0; i < userClassList.size(); i++) {
                            userIDList.add(String.valueOf(userClassList.get(i).getUserID()));
                        }
                    } catch (final Exception e) { e.printStackTrace(); }
                }
            }
            @Override
            public void onFailure(Call<List<UserClass>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return userIDList;
    }

    public List<TaskClass> GetAllTasks() {

        Call<List<TaskClass>> getTasks = taskService.getAllTasks();

        getTasks.enqueue(new Callback<List<TaskClass>>() {
            @Override
            public void onResponse(Call<List<TaskClass>> call, Response<List<TaskClass>> response) {
                if (response.isSuccessful()) {
                    List<TaskClass> taskClassList = response.body();


                    Log.d(TAG, response.toString());

                    try {
                        for (int i = 0; i < taskClassList.size(); i++) {
//                            UserClass uc1 = taskClassList.get(i).getTaskowner();
//                            UserClass uc2 = taskClassList.get(i).getTaskmanager();
                            tasksList.add(new TaskClass(taskClassList.get(i).getTaskID(),
                                    taskClassList.get(i).getProjectID(),
                                    taskClassList.get(i).getTaskname(),
                                    taskClassList.get(i).getTaskdesc(),
                                    taskClassList.get(i).getTaskphase(),
                                    taskClassList.get(i).getTaskheader(),
                                    taskClassList.get(i).getTaskstartdate(),
                                    taskClassList.get(i).getTaskenddate(),
                                    taskClassList.get(i).getTaskdatecompleted(),
                                    taskClassList.get(i).getTaskstatus(),
                                    taskClassList.get(i).getTaskduration()));
                        }
                    } catch (final Exception e) { e.printStackTrace(); }
                }
            }
            @Override
            public void onFailure(Call<List<TaskClass>> call, Throwable t) { t.printStackTrace(); }
        });

        return tasksList;
    }
    public List<ProjectClass> GetAllProjects() {

        Call<List<ProjectClass>> getProjects = projectService.getAllProjects();

        getProjects.enqueue(new Callback<List<ProjectClass>>() {
            @Override
            public void onResponse(Call<List<ProjectClass>> call, Response<List<ProjectClass>> response) {
                if (response.isSuccessful()) {
                    List<ProjectClass> projectClassList = response.body();

                    Log.d(TAG, response.toString());

                    try {
                        for (int i = 0; i < projectClassList.size(); i++) {
                            projectsList.add(new ProjectClass(projectClassList.get(i).getProjID(),
                                    projectClassList.get(i).getProjectname(),
                                    projectClassList.get(i).getProjectstartdate(),
                                    projectClassList.get(i).getProjectenddate(),
                                    projectClassList.get(i).getProjectstatus(),
                                    projectClassList.get(i).getProjectmanager(),
                                    projectClassList.get(i).getProjectprogress()));
                        }
                    } catch (final Exception e) { e.printStackTrace(); }
                }
            }
            @Override
            public void onFailure(Call<List<ProjectClass>> call, Throwable t) { t.printStackTrace(); }
        });

        return projectsList;
    }

    public List<PurchaseRequestClass> GetAllPurchaseRequests() {

        Call<List<PurchaseRequestClass>> getPurchaseRequests = purchaseRequestService.getAllPreq();

        getPurchaseRequests.enqueue(new Callback<List<PurchaseRequestClass>>() {
            @Override
            public void onResponse(Call<List<PurchaseRequestClass>> call, Response<List<PurchaseRequestClass>> response) {
                List<PurchaseRequestClass> preq = response.body();

                Log.d(TAG, response.toString());

                try {
                    for (int i = 0; i < preq.size(); i++) {
                        prList.add(new PurchaseRequestClass(preq.get(i).getPreqID(),
                                preq.get(i).getProjectID(),
                                preq.get(i).getPreqdate(),
                                preq.get(i).getPrequestedby(),
                                preq.get(i).getPreqstatus(),
                                preq.get(i).getPreqprojman(),
                                preq.get(i).getPreqpmdate(),
                                preq.get(i).getIsapprovedpm(),
                                preq.get(i).getPreqpurchofficer(),
                                preq.get(i).getPreqpodate(),
                                preq.get(i).getIsapprovedpo(),
                                preq.get(i).getPreqofficeengr(),
                                preq.get(i).getPreqoedate(),
                                preq.get(i).getIsapprovedoe()));
                    }
                } catch (final Exception e) { e.printStackTrace(); }
            }

            @Override
            public void onFailure(Call<List<PurchaseRequestClass>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return prList;
    }

    public List<PurchaseRequestItemClass> GetAllPReqItemList(int i) {

        pItemList = new ArrayList<PurchaseRequestItemClass>();
        Call<List<PurchaseRequestItemClass>> getitems = pItemService.getItemByPReqId(i);

        getitems.enqueue(new Callback<List<PurchaseRequestItemClass>>() {
            @Override
            public void onResponse(Call<List<PurchaseRequestItemClass>> call, Response<List<PurchaseRequestItemClass>> response) {
                List<PurchaseRequestItemClass> iList = response.body();

                try {
                    for (int i = 0; i < iList.size(); i++) {
                        pItemList.add(new PurchaseRequestItemClass(iList.get(i).getPreqItemID(),
                                iList.get(i).getPreqID(),
                                iList.get(i).getPreqqty(),
                                iList.get(i).getPrequnit(),
                                iList.get(i).getPreqdesc(),
                                iList.get(i).getPreqjob()));
                    }
                } catch (final Exception e) { e.printStackTrace(); }
            }

            @Override
            public void onFailure(Call<List<PurchaseRequestItemClass>> call, Throwable t) {

            }
        });

        return pItemList;
    }

}
