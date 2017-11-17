package com.example.jasper.capstone.model_classes;

import java.io.Serializable;

/**
 * Created by DC on 01/11/2017.
 */

public class PurchaseOrderClass implements Serializable {

    private int pordID;
    private PurchaseRequestClass prequestID;
    private String porddate;
    private double pordsubtotal;
    private double pordsalestax;
    private double pordtotal;
    private UserClass pordcreatedby;
    private String pordstatus;
    private UserClass pordprojman;
    private String pordpmdate;
    private Boolean isapprovedpm;
    private UserClass pordpurchofficer;
    private String pordpodate;
    private Boolean isapprovedpo;
    private String pordshipping;
    private String pordshippingterms;
    private String pordshipdeldate;

    public PurchaseOrderClass(){

    }

    public PurchaseOrderClass(int pordID, PurchaseRequestClass prequestID, String porddate, double pordsubtotal, double pordsalestax, double pordtotal, UserClass pordcreatedby, String pordstatus, UserClass pordprojman, String pordpmdate, Boolean isapprovedpm, UserClass pordpurchofficer, String pordpodate, Boolean isapprovedpo, String pordshipping, String pordshippingterms, String pordshipdeldate) {
        this.pordID = pordID;
        this.prequestID = prequestID;
        this.porddate = porddate;
        this.pordsubtotal = pordsubtotal;
        this.pordsalestax = pordsalestax;
        this.pordtotal = pordtotal;
        this.pordcreatedby = pordcreatedby;
        this.pordstatus = pordstatus;
        this.pordprojman = pordprojman;
        this.pordpmdate = pordpmdate;
        this.isapprovedpm = isapprovedpm;
        this.pordpurchofficer = pordpurchofficer;
        this.pordpodate = pordpodate;
        this.isapprovedpo = isapprovedpo;
        this.pordshipping = pordshipping;
        this.pordshippingterms = pordshippingterms;
        this.pordshipdeldate = pordshipdeldate;
    }

    public int getPordID() {
        return pordID;
    }

    public void setPordID(int pordID) {
        this.pordID = pordID;
    }

    public PurchaseRequestClass getPrequestID() {
        return prequestID;
    }

    public void setPrequestID(PurchaseRequestClass prequestID) {
        this.prequestID = prequestID;
    }

    public String getPorddate() {
        return porddate;
    }

    public void setPorddate(String porddate) {
        this.porddate = porddate;
    }

    public double getPordsubtotal() {
        return pordsubtotal;
    }

    public void setPordsubtotal(double pordsubtotal) {
        this.pordsubtotal = pordsubtotal;
    }

    public double getPordsalestax() {
        return pordsalestax;
    }

    public void setPordsalestax(double pordsalestax) {
        this.pordsalestax = pordsalestax;
    }

    public double getPordtotal() {
        return pordtotal;
    }

    public void setPordtotal(double pordtotal) {
        this.pordtotal = pordtotal;
    }

    public UserClass getPordcreatedby() {
        return pordcreatedby;
    }

    public void setPordcreatedby(UserClass pordcreatedby) {
        this.pordcreatedby = pordcreatedby;
    }

    public String getPordstatus() {
        return pordstatus;
    }

    public void setPordstatus(String pordstatus) {
        this.pordstatus = pordstatus;
    }

    public UserClass getPordprojman() {
        return pordprojman;
    }

    public void setPordprojman(UserClass pordprojman) {
        this.pordprojman = pordprojman;
    }

    public String getPordpmdate() {
        return pordpmdate;
    }

    public void setPordpmdate(String pordpmdate) {
        this.pordpmdate = pordpmdate;
    }

    public Boolean getIsapprovedpm() {
        return isapprovedpm;
    }

    public void setIsapprovedpm(Boolean isapprovedpm) {
        this.isapprovedpm = isapprovedpm;
    }

    public UserClass getPordpurchofficer() {
        return pordpurchofficer;
    }

    public void setPordpurchofficer(UserClass pordpurchofficer) {
        this.pordpurchofficer = pordpurchofficer;
    }

    public String getPordpodate() {
        return pordpodate;
    }

    public void setPordpodate(String pordpodate) {
        this.pordpodate = pordpodate;
    }

    public Boolean getIsapprovedpo() {
        return isapprovedpo;
    }

    public void setIsapprovedpo(Boolean isapprovedpo) {
        this.isapprovedpo = isapprovedpo;
    }

    public String getPordshipping() {
        return pordshipping;
    }

    public void setPordshipping(String pordshipping) {
        this.pordshipping = pordshipping;
    }

    public String getPordshippingterms() {
        return pordshippingterms;
    }

    public void setPordshippingterms(String pordshippingterms) {
        this.pordshippingterms = pordshippingterms;
    }

    public String getPordshipdeldate() {
        return pordshipdeldate;
    }

    public void setPordshipdeldate(String pordshipdeldate) {
        this.pordshipdeldate = pordshipdeldate;
    }
}
