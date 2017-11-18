package com.mjm.workflowkami.model_classes;

import java.io.Serializable;

/**
 * Created by Jasper on 15 Sep 2017.
 */

public class PurchaseRequestItemClass implements Serializable{

    private int preqItemID;
    private int preqID;
    private int preqqty;
    private String prequnit;
    private String preqdesc;
    private String preqjob;

    public PurchaseRequestItemClass() {

    }

    public PurchaseRequestItemClass(int preqItemID, int preqID, int preqqty, String prequnit, String preqdesc, String preqjob) {
        this.preqItemID = preqItemID;
        this.preqID = preqID;
        this.preqqty = preqqty;
        this.prequnit = prequnit;
        this.preqdesc = preqdesc;
        this.preqjob = preqjob;
    }
    public PurchaseRequestItemClass( int preqID, int preqqty, String prequnit, String preqdesc, String preqjob) {
        this.preqID = preqID;
        this.preqqty = preqqty;
        this.prequnit = prequnit;
        this.preqdesc = preqdesc;
        this.preqjob = preqjob;
    }

    public int getPreqItemID() {
        return preqItemID;
    }

    public void setPreqItemID(int preqItemID) {
        this.preqItemID = preqItemID;
    }

    public int getPreqID() {
        return preqID;
    }

    public void setPreqID(int preqID) {
        this.preqID = preqID;
    }

    public int getPreqqty() {
        return preqqty;
    }

    public void setPreqqty(int preqqty) {
        this.preqqty = preqqty;
    }

    public String getPrequnit() {
        return prequnit;
    }

    public void setPrequnit(String prequnit) {
        this.prequnit = prequnit;
    }

    public String getPreqdesc() {
        return preqdesc;
    }

    public void setPreqdesc(String preqdesc) {
        this.preqdesc = preqdesc;
    }

    public String getPreqjob() {
        return preqjob;
    }

    public void setPreqjob(String preqjob) {
        this.preqjob = preqjob;
    }
}
