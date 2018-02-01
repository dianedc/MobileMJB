package com.mjm.workflowkami.model_classes;

import java.io.Serializable;

/**
 * Created by DC on 01/02/2018.
 */

public class TaskAssignedClass implements Serializable {

    private int taskassignedID;
    private ProjectClass projectID;
    private TaskClass taskID;
    private ProjectTeamClass assignedID;

    public TaskAssignedClass() {
    }

    public TaskAssignedClass(int taskassignedID, ProjectClass projectID, TaskClass taskID, ProjectTeamClass assignedID) {
        this.taskassignedID = taskassignedID;
        this.projectID = projectID;
        this.taskID = taskID;
        this.assignedID = assignedID;
    }

    public int getTaskassignedID() {
        return taskassignedID;
    }

    public void setTaskassignedID(int taskassignedID) {
        this.taskassignedID = taskassignedID;
    }

    public ProjectClass getProjectID() {
        return projectID;
    }

    public void setProjectID(ProjectClass projectID) {
        this.projectID = projectID;
    }

    public TaskClass getTaskID() {
        return taskID;
    }

    public void setTaskID(TaskClass taskID) {
        this.taskID = taskID;
    }

    public ProjectTeamClass getAssignedID() {
        return assignedID;
    }

    public void setAssignedID(ProjectTeamClass assignedID) {
        this.assignedID = assignedID;
    }

    @Override
    public String toString() {
        return "TaskAssignedClass{" +
                "taskassignedID=" + taskassignedID +
                ", projectID=" + projectID +
                ", taskID=" + taskID +
                ", assignedID=" + assignedID +
                '}';
    }
}
