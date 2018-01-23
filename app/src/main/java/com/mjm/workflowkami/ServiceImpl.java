package com.mjm.workflowkami;


import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.mjm.workflowkami.impl_classes.PurchaseOrder;
import com.mjm.workflowkami.impl_classes.Workers;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.ProjectTeamClass;
import com.mjm.workflowkami.model_classes.PurchaseOrderClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestItemClass;
import com.mjm.workflowkami.model_classes.TaskClass;
import com.mjm.workflowkami.model_classes.UserClass;
import com.mjm.workflowkami.model_classes.WorkerClass;
import com.mjm.workflowkami.service_classes.ProjectService;
import com.mjm.workflowkami.service_classes.ProjectTeamService;
import com.mjm.workflowkami.service_classes.PurchaseOrderService;
import com.mjm.workflowkami.service_classes.PurchaseRequestItemService;
import com.mjm.workflowkami.service_classes.PurchaseRequestService;
import com.mjm.workflowkami.service_classes.TaskService;
import com.mjm.workflowkami.service_classes.UserService;
import com.mjm.workflowkami.service_classes.WorkerService;

import java.math.BigDecimal;
import java.math.MathContext;
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
    private ProjectTeamService projectTeamService = API.getInstance().getProjectTeamService();
    private WorkerService workerService = API.getInstance().getWorkerService();

    private List<PurchaseOrderClass> pordList = new ArrayList<PurchaseOrderClass>();
    private PurchaseOrderService purchaseOrderService = API.getInstance().getPurchaseOrderService();

    public List<UserClass> usersList;
    public ArrayList<String> userIDList = new ArrayList<String>();
    public List<TaskClass> tasksList = new ArrayList<TaskClass>();
    public List<TaskClass> completedTasks = new ArrayList<TaskClass>();
    public List<ProjectClass> projectsList = new ArrayList<ProjectClass>();
    public UserClass userClass = new UserClass();
    ;
    public List<PurchaseRequestClass> prList = new ArrayList<PurchaseRequestClass>();
    public List<PurchaseRequestItemClass> pItemList = new ArrayList<PurchaseRequestItemClass>();
    public List<ProjectTeamClass> pTeamList = new ArrayList<ProjectTeamClass>();
    public List<WorkerClass> workerList = new ArrayList<WorkerClass>();

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
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
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
                if (response.isSuccessful()) {
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
            public void onFailure(Call<UserClass> call, Throwable t) {
                t.printStackTrace();
            }
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
//                            +
//                                    " " + userClassList.get(i).getLastname() +
//                                    ", " + userClassList.get(i).getFirstname()
                        }
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<UserClass>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return userIDList;
    }

    public List<TaskClass> GetTaskByProjId(int projID) {

        Call<List<TaskClass>> getTasks = taskService.getTaskByProjId(projID);

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
                                    taskClassList.get(i).getTaskbudget(),
                                    taskClassList.get(i).getTaskstartdate(),
                                    taskClassList.get(i).getTaskenddate(),
                                    taskClassList.get(i).getTaskdatecompleted(),
                                    taskClassList.get(i).getTaskstatus(),
                                    taskClassList.get(i).getTaskduration()));
                        }
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TaskClass>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return tasksList;
    }

    public List<TaskClass> GetCompletedTasks() {
        completedTasks = new ArrayList<TaskClass>();

        Call<List<TaskClass>> completed = taskService.getAllCompleted();
        completed.enqueue(new Callback<List<TaskClass>>() {
            @Override
            public void onResponse(Call<List<TaskClass>> call, Response<List<TaskClass>> response) {
                List<TaskClass> listComp = response.body();

                try {
                    for (int i = 0; i < listComp.size(); i++) {
                        completedTasks.add(new TaskClass(String.valueOf(listComp.get(i).getTaskID())));
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<TaskClass>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return completedTasks;
    }

    public List<ProjectClass> GetAllProjects() {

        projectsList = new ArrayList<ProjectClass>();
        Call<List<ProjectClass>> getProjects = projectService.getAllProjects();

        getProjects.enqueue(new Callback<List<ProjectClass>>() {
            @Override
            public void onResponse(Call<List<ProjectClass>> call, Response<List<ProjectClass>> response) {
                if (response.isSuccessful()) {
                    List<ProjectClass> projectClassList = response.body();

                    try {
                        for (int i = 0; i < projectClassList.size(); i++) {
                            projectsList.add(new ProjectClass(projectClassList.get(i).getProjID(),
                                    projectClassList.get(i).getProjname(),
                                    projectClassList.get(i).getProjclient(),
                                    projectClassList.get(i).getProjdesc(),
                                    projectClassList.get(i).getProjtype(),
                                    projectClassList.get(i).getProjstartdate(),
                                    projectClassList.get(i).getProjenddate(),
                                    projectClassList.get(i).getProjdatecompleted(),
                                    projectClassList.get(i).getProjstatus(),
                                    projectClassList.get(i).getProjmanager(),
                                    projectClassList.get(i).getProjcontractbudget(),
                                    projectClassList.get(i).getProjtargetbudget(),
                                    projectClassList.get(i).getProjprogress(),
                                    projectClassList.get(i).getProjduration()));
                        }
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProjectClass>> call, Throwable t) {
                t.printStackTrace();
            }
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
                                preq.get(i).getPreqapproveddate(),
                                preq.get(i).getPreqrequesteddate(),
                                preq.get(i).getPreqrequestedby(),
                                preq.get(i).getPreqstatus(),
                                preq.get(i).getPreqprojman(),
                                preq.get(i).getPreqpmdate(),
                                preq.get(i).getIsapprovedpm(),
                                preq.get(i).getPreqpurchofficer(),
                                preq.get(i).getPreqpodate(),
                                preq.get(i).getIsapprovedpo(),
                                preq.get(i).getPreqofficeengr(),
                                preq.get(i).getPreqoedate(),
                                preq.get(i).getIsapprovedoe(),
                                preq.get(i).getPreqsubtotal(),
                                preq.get(i).getPreqsalestax(),
                                preq.get(i).getPreqtotal()));
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                }
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
                                iList.get(i).getPrequestID(),
                                iList.get(i).getPreqqty(),
                                iList.get(i).getPrequnit(),
                                iList.get(i).getPreqdesc(),
                                iList.get(i).getPreqjob(),
                                iList.get(i).getPrequnitprice(),
                                iList.get(i).getPreqlinetotal()));
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<PurchaseRequestItemClass>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return pItemList;
    }

    public List<ProjectTeamClass> GetAllTeams() {

        Call<List<ProjectTeamClass>> getTeams = projectTeamService.getAllProjTeams();

        getTeams.enqueue(new Callback<List<ProjectTeamClass>>() {
            @Override
            public void onResponse(Call<List<ProjectTeamClass>> call, Response<List<ProjectTeamClass>> response) {
                if (response.isSuccessful()) {
                    List<ProjectTeamClass> teamClassList = response.body();


                    Log.d(TAG, response.toString());

//                    try {
//                        for (int i = 0; i < teamClassList.size(); i++) {
//                            pTeamList.add(new ProjectTeamClass(teamClassList.get(i).getProjteamID(),
//                                    teamClassList.get(i).getProjectID(),
//                                    teamClassList.get(i).getUserID(),
//                                    teamClassList.get(i).getProjuserrole()));
//
//                        }
//                    } catch (final Exception e) { e.printStackTrace(); }
                }
            }

            @Override
            public void onFailure(Call<List<ProjectTeamClass>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return pTeamList;
    }

    public List<WorkerClass> GetAllWorkers() {

        Call<List<WorkerClass>> getWorkers = workerService.getAllWorkers();

        getWorkers.enqueue(new Callback<List<WorkerClass>>() {
            @Override
            public void onResponse(Call<List<WorkerClass>> call, Response<List<WorkerClass>> response) {
                if (response.isSuccessful()) {
                    List<WorkerClass> workerClassList = response.body();


                    Log.d(TAG, response.toString());

                    try {
                        for (int i = 0; i < workerClassList.size(); i++) {
                            workerList.add(new WorkerClass(workerClassList.get(i).getWorkersID(),
                                    workerClassList.get(i).getWorkersfirstname(),
                                    workerClassList.get(i).getWorkerslastname(),
                                    workerClassList.get(i).getWorkersrole()));
                        }
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<WorkerClass>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return workerList;
    }

    public List<ProjectTeamClass> GetProjTeamID(int projTeamID) {

        Call<List<ProjectTeamClass>> getProjTeams = projectTeamService.putTimeIn(projTeamID);

        getProjTeams.enqueue(new Callback<List<ProjectTeamClass>>() {
            @Override
            public void onResponse(Call<List<ProjectTeamClass>> call, Response<List<ProjectTeamClass>> response) {
                if (response.isSuccessful()) {
                    List<ProjectTeamClass> projectTeamClassList = response.body();
                    Log.d(TAG, response.toString());

//                    try {
//                        for (int i = 0; i < projectTeamClassList.size(); i++) {
//                            pTeamList.add(new ProjectTeamClass(projectTeamClassList.get(i).getProjteamID()));
//                        }
//                    } catch (final Exception e) { e.printStackTrace(); }
                }
            }

            @Override
            public void onFailure(Call<List<ProjectTeamClass>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return pTeamList;
    }

    public List<ProjectTeamClass> GetUsersTeamById(int projID) {
        pTeamList = new ArrayList<ProjectTeamClass>();
        Call<List<ProjectTeamClass>> getProjTeams = projectTeamService.getUsersTeamById(projID);

        getProjTeams.enqueue(new Callback<List<ProjectTeamClass>>() {
            @Override
            public void onResponse(Call<List<ProjectTeamClass>> call, Response<List<ProjectTeamClass>> response) {
                if (response.isSuccessful()) {
                    List<ProjectTeamClass> projectTeamClassList = response.body();
                    Log.d(TAG, response.toString());

                    try {
                        for (int i = 0; i < projectTeamClassList.size(); i++) {
                            pTeamList.add(new ProjectTeamClass(projectTeamClassList.get(i).getProjteamID(),
                                    projectTeamClassList.get(i).getProjectsprojID(),
                                    projectTeamClassList.get(i).getUserID(),
                                    projectTeamClassList.get(i).getWorkersworkersID()));
                        }
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProjectTeamClass>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return pTeamList;
    }

    public List<ProjectTeamClass> GetWorkersTeamById(int projID) {
        pTeamList = new ArrayList<ProjectTeamClass>();
        Call<List<ProjectTeamClass>> getProjTeams = projectTeamService.getWorkerTeamById(projID);

        getProjTeams.enqueue(new Callback<List<ProjectTeamClass>>() {
            @Override
            public void onResponse(Call<List<ProjectTeamClass>> call, Response<List<ProjectTeamClass>> response) {
                if (response.isSuccessful()) {
                    List<ProjectTeamClass> projectTeamClassList = response.body();
                    Log.d(TAG, response.toString());

                    try {
                        for (int i = 0; i < projectTeamClassList.size(); i++) {
                            pTeamList.add(new ProjectTeamClass(projectTeamClassList.get(i).getProjteamID(),
                                    projectTeamClassList.get(i).getProjectsprojID(),
                                    projectTeamClassList.get(i).getUserID(),
                                    projectTeamClassList.get(i).getWorkersworkersID()));
                        }
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProjectTeamClass>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return pTeamList;
    }

    public List<ProjectTeamClass> GetAllProjTeams() {
        pTeamList = new ArrayList<ProjectTeamClass>();
        Call<List<ProjectTeamClass>> getProjTeams = projectTeamService.getAllProjTeams();

        getProjTeams.enqueue(new Callback<List<ProjectTeamClass>>() {
            @Override
            public void onResponse(Call<List<ProjectTeamClass>> call, Response<List<ProjectTeamClass>> response) {
                if (response.isSuccessful()) {
                    List<ProjectTeamClass> projectTeamClassList = response.body();
                    Log.d(TAG, response.toString());

                    try {
                        for (int i = 0; i < projectTeamClassList.size(); i++) {
                            pTeamList.add(new ProjectTeamClass(projectTeamClassList.get(i).getProjteamID(),
                                    projectTeamClassList.get(i).getProjectsprojID(),
                                    projectTeamClassList.get(i).getUserID(),
                                    projectTeamClassList.get(i).getWorkersworkersID()));
                        }
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProjectTeamClass>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return pTeamList;
    }
}
