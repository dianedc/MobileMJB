package com.mjm.workflowkami.model_classes;

/**
 * Created by admin on 21 Nov 2017.
 */
import java.io.Serializable;


public class WorkerClass implements Serializable{

    private Integer workersID ;
    private String workersfirstname;
    private String workerslastname;
    private String workersrole;

    public WorkerClass() {

    }

    public WorkerClass(Integer workersID, String workersfirstname, String workerslastname, String workersrole) {
        this.workersID = workersID;
        this.workersfirstname = workersfirstname;
        this.workerslastname = workerslastname;
        this.workersrole = workersrole;
    }

    public WorkerClass( String workersfirstname, String workerslastname, String workersrole) {
        this.workersfirstname = workersfirstname;
        this.workerslastname = workerslastname;
        this.workersrole = workersrole;
    }

    public Integer getWorkersID() {
        return workersID;
    }

    public void setWorkersID(Integer workersID) {
        this.workersID = workersID;
    }

    public String getWorkersfirstname() {
        return workersfirstname;
    }

    public void setWorkersfirstname(String workersfirstname) { this.workersfirstname = workersfirstname; }

    public String getWorkerslastname() {
        return workerslastname;
    }

    public void setWorkerslastname(String workerslastname) { this.workerslastname = workerslastname; }

    public String getWorkersrole() {
        return workersrole;
    }

    public void setWorkersrole(String workersrole) {
        this.workersrole = workersrole;
    }

    @Override
    public String toString() {
        return "WorkerClass{" +
                "workersID='" + workersID + '\'' +
                ", workersfirstname='" + workersfirstname + '\'' +
                ", workerslastname='" + workerslastname + '\'' +
                ", workersrole='" + workersrole + '\'' +
                '}';
    }

}
