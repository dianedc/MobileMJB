package com.mjm.workflowkami.model_classes;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jasper on 13 Sep 2017.
 */

public class TaskClass implements Serializable {

    private Integer taskID;
    private ProjectClass projectID;
    private String taskname;
    private String taskdesc;
    private String taskphase;
    private String taskheader;
    private BigDecimal taskbudget;
    private String taskstartdate;
    private String taskenddate;
    private String taskdatestarted;
    private BigDecimal tasktotalspent;
    private String taskdatecompleted;
    private String taskstatus;
    private String taskduration;

    private String Count;

    public TaskClass() {

    }

    public TaskClass(String Count) {
        this.Count = Count;
    }

    @Override
    public String toString() {
        return "TaskClass{" +
                "Count='" + Count +
                '}';
    }

    //Edit
    public TaskClass(Integer taskID, ProjectClass projectID, String taskname, String taskdesc, String taskphase, String taskheader, BigDecimal taskbudget, String taskstartdate, String taskenddate, String taskdatestarted, BigDecimal tasktotalspent, String taskdatecompleted, String taskstatus, String taskduration) {
        this.taskID = taskID;
        this.projectID = projectID;
        this.taskname = taskname;
        this.taskdesc = taskdesc;
        this.taskphase = taskphase;
        this.taskheader = taskheader;
        this.taskbudget = taskbudget;
        this.taskstartdate = taskstartdate;
        this.taskenddate = taskenddate;
        this.taskdatestarted = taskdatestarted;
        this.tasktotalspent = tasktotalspent;
        this.taskdatecompleted = taskdatecompleted;
        this.taskstatus = taskstatus;
        this.taskduration = taskduration;
    }

    //Add
    public TaskClass(ProjectClass projectID, String taskname, String taskdesc, String taskphase, String taskheader, BigDecimal taskbudget, String taskstartdate, String taskenddate, String taskdatecompleted, String taskstatus, String taskduration) {
        this.taskID = taskID;
        this.projectID = projectID;
        this.taskname = taskname;
        this.taskdesc = taskdesc;
        this.taskphase = taskphase;
        this.taskheader = taskheader;
        this.taskbudget = taskbudget;
        this.taskstartdate = taskstartdate;
        this.taskenddate = taskenddate;
        this.taskdatecompleted = taskdatecompleted;
        this.taskstatus = taskstatus;
        this.taskduration = taskduration;
    }

    public String getTaskdatestarted() {
        return taskdatestarted;
    }

    public void setTaskdatestarted(String taskdatestarted) {
        this.taskdatestarted = taskdatestarted;
    }

    public BigDecimal getTasktotalspent() {
        return tasktotalspent;
    }

    public void setTasktotalspent(BigDecimal tasktotalspent) {
        this.tasktotalspent = tasktotalspent;
    }

    public BigDecimal getTaskbudget() {
        return taskbudget;
    }

    public void setTaskbudget(BigDecimal taskbudget) {
        this.taskbudget = taskbudget;
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public ProjectClass getProjectID() {
        return projectID;
    }

    public void setProjectID(ProjectClass projectID) {
        this.projectID = projectID;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getTaskdesc() {
        return taskdesc;
    }

    public void setTaskdesc(String taskdesc) {
        this.taskdesc = taskdesc;
    }

    public String getTaskphase() {
        return taskphase;
    }

    public void setTaskphase(String taskphase) {
        this.taskphase = taskphase;
    }

    public String getTaskheader() {
        return taskheader;
    }

    public void setTaskheader(String taskheader) {
        this.taskheader = taskheader;
    }

    public String getTaskstartdate() {
        return taskstartdate;
    }

    public void setTaskstartdate(String taskstartdate) {
        this.taskstartdate = taskstartdate;
    }

    public String getTaskenddate() {
        return taskenddate;
    }

    public void setTaskenddate(String taskenddate) {
        this.taskenddate = taskenddate;
    }

    public String getTaskdatecompleted() {
        return taskdatecompleted;
    }

    public void setTaskdatecompleted(String taskdatecompleted) { this.taskdatecompleted = taskdatecompleted; }

    public String getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(String taskstatus) {
        this.taskstatus = taskstatus;
    }

    public String getTaskduration() {
        return taskduration;
    }

    public void setTaskduration(String taskduration) {
        this.taskduration = taskduration;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }
}
