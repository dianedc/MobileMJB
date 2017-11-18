package com.mjm.workflowkami.model_classes;

import java.io.Serializable;

/**
 * Created by Jasper on 15 Sep 2017.
 */

public class PurchaseRequestClass implements Serializable{

    private int preqID;
    private ProjectClass projectID;
    private String preqdate;
    private UserClass prequestedby;
    private Boolean preqstatus;
    private UserClass preqprojman;
    private String preqpmdate;
    private Boolean isapprovedpm;
    private UserClass preqpurchofficer;
    private String preqpodate;
    private Boolean isapprovedpo;
    private UserClass preqofficeengr;
    private String preqoedate;
    private Boolean isapprovedoe;

    public PurchaseRequestClass() {

    }
    public PurchaseRequestClass(int preqID, ProjectClass projectID, String preqdate, UserClass prequestedby, Boolean preqstatus, UserClass preqprojman, String preqpmdate, Boolean isapprovedpm, UserClass preqpurchofficer, String preqpodate, Boolean isapprovedpo, UserClass preqofficeengr, String preqoedate, Boolean isapprovedoe) {
        this.preqID = preqID;
        this.projectID = projectID;
        this.preqdate = preqdate;
        this.prequestedby = prequestedby;
        this.preqstatus = preqstatus;
        this.preqprojman = preqprojman;
        this.preqpmdate = preqpmdate;
        this.isapprovedpm = isapprovedpm;
        this.preqpurchofficer = preqpurchofficer;
        this.preqpodate = preqpodate;
        this.isapprovedpo = isapprovedpo;
        this.preqofficeengr = preqofficeengr;
        this.preqoedate = preqoedate;
        this.isapprovedoe = isapprovedoe;
    }

    public int getPreqID() {
        return preqID;
    }

    public void setPreqID(int preqID) {
        this.preqID = preqID;
    }

    public ProjectClass getProjectID() {
        return projectID;
    }

    public void setProjectID(ProjectClass projectID) {
        this.projectID = projectID;
    }

    public String getPreqdate() {
        return preqdate;
    }

    public void setPreqdate(String preqdate) {
        this.preqdate = preqdate;
    }

    public UserClass getPrequestedby() {
        return prequestedby;
    }

    public void setPrequestedby(UserClass prequestedby) {
        this.prequestedby = prequestedby;
    }

    public Boolean getPreqstatus() {
        return preqstatus;
    }

    public void setPreqstatus(Boolean preqstatus) {
        this.preqstatus = preqstatus;
    }

    public UserClass getPreqprojman() {
        return preqprojman;
    }

    public void setPreqprojman(UserClass preqprojman) {
        this.preqprojman = preqprojman;
    }

    public String getPreqpmdate() {
        return preqpmdate;
    }

    public void setPreqpmdate(String preqpmdate) {
        this.preqpmdate = preqpmdate;
    }

    public Boolean getIsapprovedpm() {
        return isapprovedpm;
    }

    public void setIsapprovedpm(Boolean isapprovedpm) {
        this.isapprovedpm = isapprovedpm;
    }

    public UserClass getPreqpurchofficer() {
        return preqpurchofficer;
    }

    public void setPreqpurchofficer(UserClass preqpurchofficer) {
        this.preqpurchofficer = preqpurchofficer;
    }

    public String getPreqpodate() {
        return preqpodate;
    }

    public void setPreqpodate(String preqpodate) {
        this.preqpodate = preqpodate;
    }

    public Boolean getIsapprovedpo() {
        return isapprovedpo;
    }

    public void setIsapprovedpo(Boolean isapprovedpo) {
        this.isapprovedpo = isapprovedpo;
    }

    public UserClass getPreqofficeengr() {
        return preqofficeengr;
    }

    public void setPreqofficeengr(UserClass preqofficeengr) {
        this.preqofficeengr = preqofficeengr;
    }

    public String getPreqoedate() {
        return preqoedate;
    }

    public void setPreqoedate(String preqoedate) {
        this.preqoedate = preqoedate;
    }

    public Boolean getIsapprovedoe() {
        return isapprovedoe;
    }

    public void setIsapprovedoe(Boolean isapprovedoe) {
        this.isapprovedoe = isapprovedoe;
    }

    @Override
    public String toString() {
        return "PurchaseRequestClass{" +
                "preqID=" + preqID +
                ", projectID=" + projectID +
                ", preqdate='" + preqdate + '\'' +
                ", prequestedby=" + prequestedby +
                ", preqstatus=" + preqstatus +
                ", preqprojman=" + preqprojman +
                ", preqpmdate='" + preqpmdate + '\'' +
                ", isapprovedpm=" + isapprovedpm +
                ", preqpurchofficer=" + preqpurchofficer +
                ", preqpodate='" + preqpodate + '\'' +
                ", isapprovedpo=" + isapprovedpo +
                ", preqofficeengr=" + preqofficeengr +
                ", preqoedate='" + preqoedate + '\'' +
                ", isapprovedoe=" + isapprovedoe +
                '}';
    }
}
