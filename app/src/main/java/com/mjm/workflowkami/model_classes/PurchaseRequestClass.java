package com.mjm.workflowkami.model_classes;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jasper on 15 Sep 2017.
 */

public class PurchaseRequestClass implements Serializable{

    private int preqID;
    private ProjectClass projectID;
    private String preqapproveddate;
    private String preqrequesteddate;
    private UserClass preqrequestedby;
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
    private BigDecimal preqsubtotal;
    private double preqsalestax;
    private BigDecimal preqtotal;

    public PurchaseRequestClass() {

    }

    public PurchaseRequestClass(int preqID, ProjectClass projectID, String preqapproveddate, String preqrequesteddate, UserClass preqrequestedby, Boolean preqstatus, UserClass preqprojman, String preqpmdate, Boolean isapprovedpm, UserClass preqpurchofficer, String preqpodate, Boolean isapprovedpo, UserClass preqofficeengr, String preqoedate, Boolean isapprovedoe, BigDecimal preqsubtotal, double preqsalestax, BigDecimal preqtotal) {
        this.preqID = preqID;
        this.projectID = projectID;
        this.preqapproveddate = preqapproveddate;
        this.preqrequesteddate = preqrequesteddate;
        this.preqrequestedby = preqrequestedby;
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
        if(preqsubtotal!=null)
        {this.preqsubtotal = preqsubtotal;}
        else{ this.preqsubtotal = new BigDecimal("0.0");};
        this.preqsalestax = preqsalestax;
        if(preqtotal!=null)
        {this.preqtotal = preqtotal;}
        else{ this.preqtotal = new BigDecimal("0.0");};
    }

    public PurchaseRequestClass(ProjectClass projectID, String preqapproveddate, String preqrequesteddate, UserClass preqrequestedby, UserClass preqprojman, String preqpmdate, UserClass preqpurchofficer, String preqpodate, UserClass preqofficeengr, String preqoedate, BigDecimal preqsubtotal, double preqsalestax, BigDecimal preqtotal) {
        this.projectID = projectID;
        this.preqapproveddate = preqapproveddate;
        this.preqrequesteddate = preqrequesteddate;
        this.preqrequestedby = preqrequestedby;
        this.preqprojman = preqprojman;
        this.preqpmdate = preqpmdate;
        this.preqpurchofficer = preqpurchofficer;
        this.preqpodate = preqpodate;
        this.preqofficeengr = preqofficeengr;
        this.preqoedate = preqoedate;
        if(preqsubtotal!=null)
        {this.preqsubtotal = preqsubtotal;}
        else{ this.preqsubtotal = new BigDecimal("0.0");};
        this.preqsalestax = preqsalestax;
        if(preqtotal!=null)
        {this.preqtotal = preqtotal;}
        else{ this.preqtotal = new BigDecimal("0.0");};
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

    public String getPreqapproveddate() {
        return preqapproveddate;
    }

    public void setPreqapproveddate(String preqapproveddate) { this.preqapproveddate = preqapproveddate; }

    public String getPreqrequesteddate() {
        return preqrequesteddate;
    }

    public void setPreqrequesteddate(String preqrequesteddate) { this.preqrequesteddate = preqrequesteddate; }

    public UserClass getPreqrequestedby() {
        return preqrequestedby;
    }

    public void setPreqrequestedby(UserClass preqrequestedby) { this.preqrequestedby = preqrequestedby; }

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

    public void setPreqpurchofficer(UserClass preqpurchofficer) { this.preqpurchofficer = preqpurchofficer; }

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

    public void setPreqofficeengr(UserClass preqofficeengr) { this.preqofficeengr = preqofficeengr; }

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

    public BigDecimal getPreqsubtotal() {
        return preqsubtotal;
    }

    public void setPreqsubtotal(BigDecimal preqsubtotal) {
        this.preqsubtotal = preqsubtotal;
    }

    public double getPreqsalestax() {
        return preqsalestax;
    }

    public void setPreqsalestax(double preqsalestax) {
        this.preqsalestax = preqsalestax;
    }

    public BigDecimal getPreqtotal() {
        return preqtotal;
    }

    public void setPreqtotal(BigDecimal preqtotal) {
        this.preqtotal = preqtotal;
    }


}
