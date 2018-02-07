package com.mjm.workflowkami.model_classes;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jasper on 15 Sep 2017.
 */

public class PurchaseRequestItemClass implements Serializable{

    private int preqitemID;
    private PurchaseRequestClass prequestID;
    private int preqqty;
    private String prequnit;
    private String preqgendesc;
    private String preqdesc;
    private String preqjob;
    private Double prequnitprice;
    private BigDecimal preqlinetotal;

    public PurchaseRequestItemClass() {   }

    public PurchaseRequestItemClass(int preqitemID, PurchaseRequestClass prequestID, int preqqty, String prequnit, String preqgendesc, String preqdesc, String preqjob, Double prequnitprice, BigDecimal preqlinetotal) {
        this.preqitemID = preqitemID;
        this.prequestID = prequestID;
        this.preqqty = preqqty;
        this.prequnit = prequnit;
        this.preqgendesc = preqgendesc;
        this.preqdesc = preqdesc;
        this.preqjob = preqjob;
        this.prequnitprice = prequnitprice;
        this.preqlinetotal = preqlinetotal;
    }
    public PurchaseRequestItemClass(PurchaseRequestClass prequestID, int preqqty, String prequnit, String preqgendesc, String preqdesc, String preqjob, Double prequnitprice, BigDecimal preqlinetotal) {
        this.prequestID = prequestID;
        this.preqqty = preqqty;
        this.prequnit = prequnit;
        this.preqgendesc = preqgendesc;
        this.preqdesc = preqdesc;
        this.preqjob = preqjob;
        this.prequnitprice = prequnitprice;
        this.preqlinetotal = preqlinetotal;
    }

    public String getPreqgendesc() {
        return preqgendesc;
    }

    public void setPreqgendesc(String preqgendesc) {
        this.preqgendesc = preqgendesc;
    }

    public int getPreqItemID() {
        return preqitemID;
    }

    public void setPreqItemID(int preqItemID) {
        this.preqitemID = preqItemID;
    }

    public PurchaseRequestClass getPrequestID() {
        return prequestID;
    }

    public void setPrequestID(PurchaseRequestClass prequestID) {
        this.prequestID = prequestID;
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

    public Double getPrequnitprice() {
        return prequnitprice;
    }

    public void setPrequnitprice(Double prequnitprice) {
        this.prequnitprice = prequnitprice;
    }

    public BigDecimal getPreqlinetotal() {
        return preqlinetotal;
    }

    public void setPreqlinetotal(BigDecimal preqlinetotal) {
        this.preqlinetotal = preqlinetotal;
    }
}
