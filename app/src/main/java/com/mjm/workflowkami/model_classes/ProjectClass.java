package com.mjm.workflowkami.model_classes;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jasper on 13 Sep 2017.
 */

public class ProjectClass implements Serializable {

    private int projID;
    private String projname;
    private String projclient;
    private String projdesc;
    private String projtype;
    private String projstartdate;
    private String projenddate;
    private String projdatecompleted;
    private String projstatus;
    private UserClass projmanager;
    private double projcontractbudget;
    private double projtargetbudget;
    private BigDecimal projprogress;
    private String projduration;
    private String Count;

    public ProjectClass() {}

    public ProjectClass(String Count) {
        this.Count = Count;
    }

    @Override
    public String toString() {
        return "TaskClass{" +
                "Count='" + Count +
                '}';
    }

    public ProjectClass(int projID, String projname, String projclient, String projdesc, String projtype, String projstartdate, String projenddate, String projdatecompleted, String projstatus, UserClass projmanager, double projcontractbudget, double projtargetbudget, BigDecimal projprogress, String projduration) {
        this.projID = projID;
        this.projname = projname;
        this.projclient = projclient;
        this.projdesc = projdesc;
        this.projtype = projtype;
        this.projstartdate = projstartdate;
        this.projenddate = projenddate;
        this.projdatecompleted = projdatecompleted;
        this.projstatus = projstatus;
        this.projmanager = projmanager;
        this.projcontractbudget = projcontractbudget;
        this.projtargetbudget = projtargetbudget;
        this.projprogress = projprogress;
        this.projduration = projduration;
    }

    public ProjectClass(String projname, String projclient, String projdesc, String projtype, String projstartdate, String projenddate, String projdatecompleted, String projstatus, UserClass projmanager, double projcontractbudget, double projtargetbudget, BigDecimal projprogress, String projduration) {
        this.projname = projname;
        this.projclient = projclient;
        this.projdesc = projdesc;
        this.projtype = projtype;
        this.projstartdate = projstartdate;
        this.projenddate = projenddate;
        this.projdatecompleted = projdatecompleted;
        this.projstatus = projstatus;
        this.projmanager = projmanager;
        this.projcontractbudget = projcontractbudget;
        this.projtargetbudget = projtargetbudget;
        this.projprogress = projprogress;
        this.projduration = projduration;
    }

    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public String getProjname() {
        return projname;
    }

    public void setProjname(String projname) {
        this.projname = projname;
    }

    public String getProjclient() {
        return projclient;
    }

    public void setProjclient(String projclient) {
        this.projclient = projclient;
    }

    public String getProjdesc() {
        return projdesc;
    }

    public void setProjdesc(String projdesc) {
        this.projdesc = projdesc;
    }

    public String getProjtype() {
        return projtype;
    }

    public void setProjtype(String projtype) {
        this.projtype = projtype;
    }

    public String getProjstartdate() {
        return projstartdate;
    }

    public void setProjstartdate(String projstartdate) {
        this.projstartdate = projstartdate;
    }

    public String getProjenddate() {
        return projenddate;
    }

    public void setProjenddate(String projenddate) {
        this.projenddate = projenddate;
    }

    public String getProjdatecompleted() {
        return projdatecompleted;
    }

    public void setProjdatecompleted(String projdatecompleted) { this.projdatecompleted = projdatecompleted; }

    public String getProjstatus() {
        return projstatus;
    }

    public void setProjstatus(String projstatus) {
        this.projstatus = projstatus;
    }

    public UserClass getProjmanager() {
        return projmanager;
    }

    public void setProjmanager(UserClass projmanager) {
        this.projmanager = projmanager;
    }

    public double getProjcontractbudget() {
        return projcontractbudget;
    }

    public void setProjcontractbudget(double projcontractbudget) { this.projcontractbudget = projcontractbudget;  }

    public double getProjtargetbudget() {
        return projtargetbudget;
    }

    public void setProjtargetbudget(double projtargetbudget) { this.projtargetbudget = projtargetbudget;  }

    public BigDecimal getProjprogress() {
        return projprogress;
    }

    public void setProjprogress(BigDecimal projprogress) {
        this.projprogress = projprogress;
    }

    public String getProjduration() {
        return projduration;
    }

    public void setProjduration(String projduration) {
        this.projduration = projduration;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }
}
