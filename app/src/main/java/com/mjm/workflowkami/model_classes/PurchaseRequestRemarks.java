package com.mjm.workflowkami.model_classes;

import java.io.Serializable;

/**
 * Created by DC on 29/01/2018.
 */

public class PurchaseRequestRemarks implements Serializable {

    private int remarksID;
    private PurchaseRequestClass prequestID;
    private UserClass userID;
    private String remarks;
    private String remarksdate;

    public PurchaseRequestRemarks() {
    }

    public int getRemarksID() {
        return remarksID;
    }

    public void setRemarksID(int remarksID) {
        this.remarksID = remarksID;
    }

    public PurchaseRequestClass getPrequestID() {
        return prequestID;
    }

    public void setPrequestID(PurchaseRequestClass prequestID) {
        this.prequestID = prequestID;
    }

    public UserClass getUserID() {
        return userID;
    }

    public void setUserID(UserClass userID) {
        this.userID = userID;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarksdate() {
        return remarksdate;
    }

    public void setRemarksdate(String remarksdate) {
        this.remarksdate = remarksdate;
    }

    public PurchaseRequestRemarks(int remarksID, PurchaseRequestClass prequestID, UserClass userID, String remarks, String remarksdate) {
        this.remarksID = remarksID;
        this.prequestID = prequestID;
        this.userID = userID;
        this.remarks = remarks;
        this.remarksdate = remarksdate;
    }

    @Override
    public String toString() {
        return "PurchaseRequestRemarks{" +
                "remarksID=" + remarksID +
                ", prequestID=" + prequestID +
                ", userID=" + userID +
                ", remarks='" + remarks + '\'' +
                ", remarksdate='" + remarksdate + '\'' +
                '}';
    }
}
