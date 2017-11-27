package com.mjm.workflowkami.model_classes;

import java.io.Serializable;

/**
 * Created by admin on 13 Nov 2017.
 */

public class ProjectTeamClass implements Serializable {

    private Integer projteamID;
    private ProjectClass projectsprojID;
    private UserClass userID;
    private WorkerClass workersworkersID;

    public ProjectTeamClass() {
    }

    public ProjectTeamClass(Integer projteamID, ProjectClass projectsprojID, UserClass userID, WorkerClass workersworkersID) {
        this.projteamID = projteamID;
        this.projectsprojID = projectsprojID;
        this.userID = userID;
        this.workersworkersID = workersworkersID;
    }

    public Integer getProjteamID() {
        return projteamID;
    }

    public void setProjteamID(Integer projteamID) {
        this.projteamID = projteamID;
    }

    public ProjectClass getProjectsprojID() {
        return projectsprojID;
    }

    public void setProjectsprojID(ProjectClass projectsprojID) {
        this.projectsprojID = projectsprojID;
    }

    public UserClass getUserID() {
        return userID;
    }

    public void setUserID(UserClass userID) {
        this.userID = userID;
    }

    public WorkerClass getWorkersworkersID() {
        return workersworkersID;
    }

    public void setWorkersworkersID(WorkerClass workersworkersID) {
        this.workersworkersID = workersworkersID;
    }

    @Override
    public String toString() {
        return "ProjectTeamClass{" +
                "projteamID=" + projteamID +
                ", projectsprojID=" + projectsprojID +
                ", userID=" + userID +
                ", workersworkersID=" + workersworkersID +
                '}';
    }
}
