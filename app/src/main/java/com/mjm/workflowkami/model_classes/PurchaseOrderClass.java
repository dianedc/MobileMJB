package com.mjm.workflowkami.model_classes;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by DC on 01/11/2017.
 */

public class PurchaseOrderClass implements Serializable {

    private int pordID;
    private PurchaseRequestClass prequestID;
    private String pordapproveddate;
    private String pordrequesteddate;
    private UserClass pordrequestedby;
    private UserClass pordprojman;
    private String pordpmdate;
    private UserClass pordpurchofficer;
    private String pordpodate;
    private UserClass pordofficeengr;
    private String pordoedate;
    private BigDecimal pordsubtotal;
    private double pordsalestax;
    private BigDecimal pordtotal;

    public PurchaseOrderClass() {
    }

    public PurchaseOrderClass(int pordID, PurchaseRequestClass prequestID, String pordapproveddate, String pordrequesteddate, UserClass pordrequestedby, UserClass pordprojman, String pordpmdate, UserClass pordpurchofficer, String pordpodate, UserClass pordofficeengr, String pordoedate, BigDecimal pordsubtotal, double pordsalestax, BigDecimal pordtotal) {
        this.pordID = pordID;
        this.prequestID = prequestID;
        this.pordapproveddate = pordapproveddate;
        this.pordrequesteddate = pordrequesteddate;
        this.pordrequestedby = pordrequestedby;
        this.pordprojman = pordprojman;
        this.pordpmdate = pordpmdate;
        this.pordpurchofficer = pordpurchofficer;
        this.pordpodate = pordpodate;
        this.pordofficeengr = pordofficeengr;
        this.pordoedate = pordoedate;
        this.pordsubtotal = pordsubtotal;
        this.pordsalestax = pordsalestax;
        this.pordtotal = pordtotal;
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

    public String getPordapproveddate() {
        return pordapproveddate;
    }

    public void setPordapproveddate(String pordapproveddate) { this.pordapproveddate = pordapproveddate;  }

    public String getPordrequesteddate() {
        return pordrequesteddate;
    }

    public void setPordrequesteddate(String pordrequesteddate) { this.pordrequesteddate = pordrequesteddate;  }

    public UserClass getPordrequestedby() {
        return pordrequestedby;
    }

    public void setPordrequestedby(UserClass pordrequestedby) { this.pordrequestedby = pordrequestedby; }

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

    public UserClass getPordpurchofficer() {
        return pordpurchofficer;
    }

    public void setPordpurchofficer(UserClass pordpurchofficer) { this.pordpurchofficer = pordpurchofficer; }

    public String getPordpodate() {
        return pordpodate;
    }

    public void setPordpodate(String pordpodate) {
        this.pordpodate = pordpodate;
    }

    public UserClass getPordofficeengr() {
        return pordofficeengr;
    }

    public void setPordofficeengr(UserClass pordofficeengr) { this.pordofficeengr = pordofficeengr; }

    public String getPordoedate() {
        return pordoedate;
    }

    public void setPordoedate(String pordoedate) {
        this.pordoedate = pordoedate;
    }

    public BigDecimal getPordsubtotal() {
        return pordsubtotal;
    }

    public void setPordsubtotal(BigDecimal pordsubtotal) {
        this.pordsubtotal = pordsubtotal;
    }

    public double getPordsalestax() {
        return pordsalestax;
    }

    public void setPordsalestax(double pordsalestax) {
        this.pordsalestax = pordsalestax;
    }

    public BigDecimal getPordtotal() {
        return pordtotal;
    }

    public void setPordtotal(BigDecimal pordtotal) {
        this.pordtotal = pordtotal;
    }

    @Override
    public String toString() {
        return "PurchaseOrderClass{" +
                "pordID=" + pordID +
                ", prequestID=" + prequestID +
                ", pordapproveddate='" + pordapproveddate + '\'' +
                ", pordrequesteddate='" + pordrequesteddate + '\'' +
                ", pordrequestedby=" + pordrequestedby +
                ", pordprojman=" + pordprojman +
                ", pordpmdate='" + pordpmdate + '\'' +
                ", pordpurchofficer=" + pordpurchofficer +
                ", pordpodate='" + pordpodate + '\'' +
                ", pordofficeengr=" + pordofficeengr +
                ", pordoedate='" + pordoedate + '\'' +
                ", pordsubtotal=" + pordsubtotal +
                ", pordsalestax=" + pordsalestax +
                ", pordtotal=" + pordtotal +
                '}';
    }
}
