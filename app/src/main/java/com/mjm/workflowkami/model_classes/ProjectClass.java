package com.mjm.workflowkami.model_classes;

import java.io.Serializable;

/**
 * Created by Jasper on 13 Sep 2017.
 */

public class ProjectClass implements Serializable {

    private int projID;
    private String projname;
    private String projstartdate;
    private String projenddate;
    private String projstatus;
    private UserClass projmanager;
    private Double projprogress;

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public String getProjectname() {
        return projname;
    }

    public void setProjectname(String projectname) {
        this.projname = projectname;
    }

    public String getProjectstartdate() {
        return projstartdate;
    }

    public void setProjectstartdate(String projectstartdate) { this.projstartdate = projectstartdate; }

    public String getProjectenddate() {
        return projenddate;
    }

    public void setProjectenddate(String projectenddate) {
        this.projenddate = projectenddate;
    }

    public String getProjectstatus() {
        return projstatus;
    }

    public void setProjectstatus(String projectstatus) {
        this.projstatus = projectstatus;
    }

    public UserClass getProjectmanager() {
        return projmanager;
    }

    public void setProjectmanager(UserClass projectmanager) {
        this.projmanager = projectmanager;
    }

    public Double getProjectprogress() { return projprogress; }

    public void setProjectprogress(Double projectprogress) { this.projprogress = projectprogress; }

    public ProjectClass(String projname, String projstartdate, String projenddate, String projstatus, UserClass projmanager, Double projprogress) {
        this.projname = projname;
        this.projstartdate = projstartdate;
        this.projenddate = projenddate;
        this.projstatus = projstatus;
        this.projmanager = projmanager;
        this.projprogress = projprogress;
    }

    public ProjectClass(int projID, String projname, String projstartdate, String projenddate, String projstatus, UserClass projmanager, Double projprogress) {
        this.projID = projID;
        this.projname = projname;
        this.projstartdate = projstartdate;
        this.projenddate = projenddate;
        this.projstatus = projstatus;
        this.projmanager = projmanager;
        this.projprogress = projprogress;
    }

    public ProjectClass() {}

    @Override
    public String toString() {
        return "ProjectClass{" +
                "projectID='" + projID + '\'' +
                ", projname='" + projname + '\'' +
                ", projstartdate='" + projstartdate + '\'' +
                ", projenddate='" + projenddate + '\'' +
                ", projstatus='" + projstatus + '\'' +
                ", projmanager=" + projmanager +
                ", projprogress='" + projprogress + '\'' +
                '}';
    }

}
