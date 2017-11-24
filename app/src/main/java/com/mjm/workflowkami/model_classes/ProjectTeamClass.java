package com.mjm.workflowkami.model_classes;

import java.io.Serializable;

/**
 * Created by admin on 13 Nov 2017.
 */

public class ProjectTeamClass implements Serializable {

    private Integer projteamID;
    private ProjectClass projectID;
    private UserClass userID;
    private WorkerClass workersID;

    public ProjectTeamClass(Integer projteamID, ProjectClass projectID, UserClass userID, WorkerClass workersID ) {
        this.projteamID = projteamID;
        this.projectID = projectID;
        this.workersID = workersID;
        this.userID = userID;

    }

    public ProjectTeamClass() {

    }

//    public ProjectTeamClass(Integer projteamID, String s, String s1, String trim) {
//    }
//
//    public ProjectTeamClass(String s, String s1, String s2, String trim) {
//    }

    public Integer getProjteamID() {
        return null;
    }

    public void setProjteamID(int projteamID) {
        this.projteamID = projteamID;
    }

    public ProjectClass getProjectID() {
        return projectID;
    }

    public void setProjectID(ProjectClass projectID) {
        this.projectID = projectID;
    }

    public UserClass getUserID() {
        return userID;
    }

    public void setUserID(UserClass userID) {
        this.userID = userID;
    }

    public WorkerClass getWorkersID() { return workersID; }

    public void setWorkersID(WorkerClass workersID) { this.workersID = workersID; }



    @Override
    public String toString() {
        return "ProjectTeamClass{" +
                "projteamID='" + projteamID + '\'' +
                ", projectID='" + projectID + '\'' +
                ", userID='" + userID + '\'' +
                ", workersID='" +  +
                '}';
    }

}
