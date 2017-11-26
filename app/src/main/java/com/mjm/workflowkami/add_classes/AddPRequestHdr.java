package com.mjm.workflowkami.add_classes;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.adapter_classes.UserClassAdapter;
import com.mjm.workflowkami.impl_classes.Users;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestClass;
import com.mjm.workflowkami.model_classes.UserClass;
import com.mjm.workflowkami.service_classes.PurchaseRequestService;

import java.util.ArrayList;
import java.util.List;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ddc on 11/14/17.
 */

public class AddPRequestHdr extends Fragment {

//    private EditText  preqID,  projectID,  preqapproveddate,  preqrequesteddate,  preqrequestedby,  preqstatus,  preqprojman,  preqpmdate,  isapprovedpm,  preqpurchofficer,  preqpodate, isapprovedpo, preqofficeengr,  preqoedate,  isapprovedoe, preqsubtotal, preqsalestax, preqtotal;
    private EditText preqID, preqProjID, preq_daterequested, preq_dateapproved, preqProjMan, projman_dateapproved, preqOfficeEng, officeengineer_dateapproved, preqPOfficer,
            dateauthorized_purchase, requestedby_preq, preqStatus;
    private TextView preq_sub_total, preq_sales_tax, preq_total;
    private RadioGroup rdogrppm, rdogrppo, rdogrpoe, rdogrpstat;
    private RadioButton rdopm, rdostat, rdooe, rdopo;
    private Button btnSavePReq;
    private ServiceImpl serviceImpl = new ServiceImpl();
    private PurchaseRequestClass preqIntent = new PurchaseRequestClass();
    private PurchaseRequestClass addPreq = new PurchaseRequestClass();
    private ProjectClass projs = new ProjectClass();
    private PurchaseRequestService preqService = API.getInstance().getPurchaseRequestService();
    private SpinnerDialog reqDialog, pmDialog, poDialog, oeDialog;
    private String selectedReqBy;
    private UserClass req, pm, po, oe;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_prequest, container, false);
        serviceImpl.GetAllUserId();
        serviceImpl.GetAllUsers();
        req = new UserClass();
        pm = new UserClass();
        po = new UserClass();
        oe = new UserClass();
        rdogrpstat = (RadioGroup) rootView.findViewById(R.id.rdogrpstat);//
        int selected0 = rdogrpstat.getCheckedRadioButtonId();
        rdostat = (RadioButton) rootView.findViewById(selected0);
//        isapprovedpmt = (RadioButton) rootView.findViewById(R.id.isapprovedpmt);

        rdogrppm = (RadioGroup) rootView.findViewById(R.id.rdogrppm);
        int selected1 = rdogrppm.getCheckedRadioButtonId();
        rdopm = (RadioButton) rootView.findViewById(selected1);

        rdogrpoe = (RadioGroup)  rootView.findViewById(R.id.rdogrpoe);
        int selected2 = rdogrpoe.getCheckedRadioButtonId();
        rdooe = (RadioButton) rootView.findViewById(selected2);

        rdogrppo = (RadioGroup)  rootView.findViewById(R.id.rdogrppo);
        int selected3 = rdogrppo.getCheckedRadioButtonId();
        rdopo = (RadioButton) rootView.findViewById(selected3);

        //Spinner Dialog
        requestedby_preq = (EditText) rootView.findViewById(R.id.requestedby_preq);//
        requestedby_preq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reqDialog.showSpinerDialog();
            }
        });

        preqProjMan = (EditText) rootView.findViewById(R.id.preqProjMan);
        preqProjMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pmDialog.showSpinerDialog();
            }
        });

        preqOfficeEng = (EditText) rootView.findViewById(R.id.preqOfficeEng);
        preqOfficeEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oeDialog.showSpinerDialog();
            }
        });

        preqPOfficer = (EditText) rootView.findViewById(R.id.preqPOfficer);
        preqPOfficer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poDialog.showSpinerDialog();
            }
        });

        preqID = (EditText) rootView.findViewById(R.id.preq_id);
        preqProjID = (EditText) rootView.findViewById(R.id.preq_proj_id);
        preq_daterequested = (EditText) rootView.findViewById(R.id.preq_daterequested);
        preq_dateapproved = (EditText) rootView.findViewById(R.id.preq_dateapproved);
        projman_dateapproved = (EditText) rootView.findViewById(R.id.projman_dateapproved);
        officeengineer_dateapproved = (EditText) rootView.findViewById(R.id.officeengineer_dateapproved);
        dateauthorized_purchase = (EditText) rootView.findViewById(R.id.dateauthorized_purchase);
        btnSavePReq = (Button) rootView.findViewById(R.id.btnSavePReq);
        preq_sub_total = (TextView) rootView.findViewById(R.id.preq_sub_total);
        preq_sales_tax = (TextView) rootView.findViewById(R.id.preq_sales_tax);
        preq_total = (TextView) rootView.findViewById(R.id.preq_total);


        Intent intent = getActivity().getIntent();
        preqIntent = (PurchaseRequestClass) intent.getSerializableExtra("preqs");
        projs = (ProjectClass) intent.getSerializableExtra("projs");

        try {
            if (preqIntent != null ) {
                if (projs != null) {
                    Toast.makeText(getActivity(), projs.toString(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), projs.toString(), Toast.LENGTH_LONG).show();
                }

                preqID.setText(String.valueOf(preqIntent.getPreqID()));
                preqProjID.setText(String.valueOf(preqIntent.getProjectID().getProjID()));
//                preqProjID.setText(String.valueOf(projs.getProjID()));
                preq_dateapproved.setText(preqIntent.getPreqapproveddate());
                requestedby_preq.setText(preqIntent.getPreqrequestedby().getLastname() + ", " + preqIntent.getPreqrequestedby().getFirstname());
                preq_daterequested.setText(preqIntent.getPreqrequesteddate());
                preqProjMan.setText(preqIntent.getPreqprojman().getLastname() +", "+ preqIntent.getPreqprojman().getFirstname());
                projman_dateapproved.setText(preqIntent.getPreqpmdate());
                preqOfficeEng.setText(preqIntent.getPreqofficeengr().getLastname() + ", " + preqIntent.getPreqofficeengr().getFirstname());
                dateauthorized_purchase.setText(preqIntent.getPreqpodate());
                officeengineer_dateapproved.setText(preqIntent.getPreqoedate());
                preqPOfficer.setText(preqIntent.getPreqpurchofficer().getLastname() + ", " + preqIntent.getPreqpurchofficer().getFirstname());

                preq_sub_total.setText("Sub Total: " + String.valueOf(preqIntent.getPreqsubtotal()));
                preq_sales_tax.setText("Sales Tax: "+ String.valueOf(preqIntent.getPreqsalestax()));
                preq_total.setText("Total: "+ String.valueOf(preqIntent.getPreqtotal()));

                if (preqIntent.getPreqstatus() == true) {
                    rdogrpstat.check(R.id.statt);
                }
                if (preqIntent.getIsapprovedpo() == true) {
                    rdogrppo.check(R.id.isapprovedpot);
                }
                if (preqIntent.getIsapprovedoe() == true) {
                    rdogrpoe.check(R.id.isapprovedoet);
                }
                if (preqIntent.getIsapprovedpm() == true) {
                    rdogrppm.check(R.id.isapprovedpmt);
                }

                requestedby_preq.setEnabled(false);
                preq_daterequested.setEnabled(false);
                preqProjMan.setEnabled(false);
                preqPOfficer.setEnabled(false);
                preqOfficeEng.setEnabled(false);
                dateauthorized_purchase.setEnabled(false);
                officeengineer_dateapproved.setEnabled(false);
                projman_dateapproved.setEnabled(false);
            }
        } catch (Exception eo) {
            Toast.makeText(getActivity(), eo.toString(), Toast.LENGTH_LONG).show();
        }


        //Dialog
        reqDialog = new SpinnerDialog(getActivity(), serviceImpl.userIDList, "Select User");
        reqDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                selectedReqBy = s;


//                requestedby_preq.setText(s);

            }
        });

        pmDialog = new SpinnerDialog(getActivity(), serviceImpl.userIDList, "Select User");
        pmDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
//                selectedReqBy = s;
                preqProjMan.setText(s);
//                Toast.makeText(getActivity(), String.valueOf(selectedReqBy), Toast.LENGTH_SHORT).show();
            }
        });


        poDialog = new SpinnerDialog(getActivity(), serviceImpl.userIDList, "Select User");
        poDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
//                selectedReqBy = s;
                preqPOfficer.setText(s);
//                Toast.makeText(getActivity(), String.valueOf(selectedReqBy), Toast.LENGTH_SHORT).show();
            }
        });


        oeDialog = new SpinnerDialog(getActivity(), serviceImpl.userIDList, "Select User");
        oeDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
//                selectedReqBy = s;
                preqOfficeEng.setText(s);
            }
        });

    //        btnSavePReq.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                addPreq = new PurchaseRequestClass(preqIntent.getProjectID(),
    //                        preq_daterequested.getText().toString().trim(),
    //                        req,
    //                        stat.isChecked(),
    //                        pm,
    //                        projman_dateapproved.getText().toString().trim(),
    //                        isapprovedpm.isChecked(),
    //                        po,
    //                        dateauthorized_purchase.getText().toString().trim(),
    //                        isapprovedpo.isChecked(),
    //                        oe,
    //                        officeengineer_dateapproved.getText().toString().trim(),
    //                        isapprovedoet.isChecked());
    //
    ////                Toast.makeText(getActivity(), addPreq.toString(), Toast.LENGTH_LONG).show();
    ////                Toast.makeText(getActivity(), req.getLastname(), Toast.LENGTH_SHORT).show();
    //
    //            }
    //        });


        return rootView;
    }

    public void SavePReq(PurchaseRequestClass pr) {

        Call<PurchaseRequestClass> addPreq = preqService.addPreq(pr);

        addPreq.enqueue(new Callback<PurchaseRequestClass>() {
            @Override
            public void onResponse(Call<PurchaseRequestClass> call, Response<PurchaseRequestClass> response) {
                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Request has been successfully added!", Toast.LENGTH_SHORT).show();

                    Intent u = new Intent(getActivity(), AddPRequest.class);
                    startActivity(u);
                }
            }

            @Override
            public void onFailure(Call<PurchaseRequestClass> call, Throwable t) {
                Toast.makeText(getActivity(), "An error has been encountered while adding request", Toast.LENGTH_SHORT);
            }
        });
    }

//
//    public void UpdatePReq(int pid, int tid, TaskClass t) {
//        Call<Void> addTask = taskService.editTask(pid, tid, t);
//
//        addTask.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                Toast.makeText(AddTask.this, response.toString(), Toast.LENGTH_LONG).show();
//                if (response.isSuccessful()) {
//                    Toast.makeText(AddTask.this, "Task has been successfully edited!", Toast.LENGTH_SHORT).show();
//
//                    Intent u = new Intent(AddTask.this, Users.class);
//                    startActivity(u);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//
//                Toast.makeText(AddTask.this, "An error has been encountered while editing task", Toast.LENGTH_SHORT);
//            }
//        });
//    }
    public void onClickCancel(View v) {
        Intent cancel = new Intent(getActivity(), AddPRequest.class);
        startActivity(cancel);

    }
}
