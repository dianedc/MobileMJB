package com.mjm.workflowkami.add_classes;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.mjm.workflowkami.impl_classes.PurchaseRequest;
import com.mjm.workflowkami.impl_classes.Users;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestClass;
import com.mjm.workflowkami.model_classes.UserClass;
import com.mjm.workflowkami.service_classes.PurchaseRequestService;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private TextView requestedby_preq_id, preqProjMan_id, preqOfficeEng_id, preqPOfficer_id,  preq_sub_total, preq_sales_tax, preq_total;
    private RadioGroup rdogrppm, rdogrppo, rdogrpoe, rdogrpstat;
    private RadioButton rdopm, rdostat, rdooe, rdopo, sstat, spm, soe, spo;
    private Button btnSavePReq;
    private ServiceImpl serviceImpl = new ServiceImpl();
    private PurchaseRequestClass preqIntent = new PurchaseRequestClass();
    private PurchaseRequestClass addPreq = new PurchaseRequestClass();
    private ProjectClass projs = new ProjectClass();
    private PurchaseRequestService preqService = API.getInstance().getPurchaseRequestService();
    private SpinnerDialog reqDialog, pmDialog, poDialog, oeDialog;
    private String selectedReqBy, selectedpm, selectedoe, selectedpo;
    private UserClass req, pm, po, oe;
    private Boolean isstat, ispm, ispo, isoe;
//    private BigDecimal subTotal, total;
    private DateFormat dateFormat;



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

        dateFormat = new SimpleDateFormat("yyyy-dd-MM");

        rdogrpstat = (RadioGroup) rootView.findViewById(R.id.rdogrpstat);//
        int selected0 = rdogrpstat.getCheckedRadioButtonId();
        rdostat = (RadioButton) rootView.findViewById(selected0);
        sstat = (RadioButton) rootView.findViewById(R.id.statt);

        sstat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preq_dateapproved.setText(dateFormat.format(new Date()));
            }
        });

        rdogrppm = (RadioGroup) rootView.findViewById(R.id.rdogrppm);
        int selected1 = rdogrppm.getCheckedRadioButtonId();
        rdopm = (RadioButton) rootView.findViewById(selected1);
        spm = (RadioButton) rootView.findViewById(R.id.isapprovedpmt);

        spm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projman_dateapproved.setText(dateFormat.format(new Date()));
            }
        });

        rdogrpoe = (RadioGroup)  rootView.findViewById(R.id.rdogrpoe);
        int selected2 = rdogrpoe.getCheckedRadioButtonId();
        rdooe = (RadioButton) rootView.findViewById(selected2);
        soe = (RadioButton) rootView.findViewById(R.id.isapprovedoet);

        soe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                officeengineer_dateapproved.setText(dateFormat.format(new Date()));
            }
        });

        rdogrppo = (RadioGroup)  rootView.findViewById(R.id.rdogrppo);
        int selected3 = rdogrppo.getCheckedRadioButtonId();
        rdopo = (RadioButton) rootView.findViewById(selected3);
        spo = (RadioButton) rootView.findViewById(R.id.isapprovedpot);

        spo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateauthorized_purchase.setText(dateFormat.format(new Date()));
            }
        });

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

        requestedby_preq_id = (TextView) rootView.findViewById(R.id.requestedby_preq_id);
        preqProjMan_id = (TextView) rootView.findViewById(R.id.preqProjMan_id);
        preqOfficeEng_id = (TextView) rootView.findViewById(R.id.preqOfficeEng_id);
        preqPOfficer_id = (TextView) rootView.findViewById(R.id.preqPOfficer_id);

        Intent intent = getActivity().getIntent();
        preqIntent = (PurchaseRequestClass) intent.getSerializableExtra("preqs");
        Intent projsintent = getActivity().getIntent();
        projs = (ProjectClass) projsintent.getSerializableExtra("projects");
        if (projs != null) {
            Toast.makeText(getActivity(), projs.toString(), Toast.LENGTH_LONG).show();
        }
        try {
            if (preqIntent != null ) {


                preqID.setText(String.valueOf(preqIntent.getPreqID()));
                preqProjID.setText(String.valueOf(preqIntent.getProjectID().getProjID()));
//                preqProjID.setText(String.valueOf(projs.getProjID()));
                preq_dateapproved.setText(preqIntent.getPreqapproveddate());
                requestedby_preq.setText(preqIntent.getPreqrequestedby().getLastname() + ", " + preqIntent.getPreqrequestedby().getFirstname());
                requestedby_preq_id.setText(String.valueOf(preqIntent.getPreqrequestedby().getUserID()));
                preq_daterequested.setText(preqIntent.getPreqrequesteddate());
                preqProjMan.setText(preqIntent.getPreqprojman().getLastname() +", "+ preqIntent.getPreqprojman().getFirstname());
                preqProjMan_id.setText(String.valueOf(preqIntent.getPreqprojman().getUserID()));
                projman_dateapproved.setText(preqIntent.getPreqpmdate());
                preqOfficeEng.setText(preqIntent.getPreqofficeengr().getLastname() + ", " + preqIntent.getPreqofficeengr().getFirstname());
                preqOfficeEng_id.setText(String.valueOf(preqIntent.getPreqofficeengr().getUserID()));
                dateauthorized_purchase.setText(preqIntent.getPreqpodate());
                officeengineer_dateapproved.setText(preqIntent.getPreqoedate());
                preqPOfficer.setText(preqIntent.getPreqpurchofficer().getLastname() + ", " + preqIntent.getPreqpurchofficer().getFirstname());
                preqPOfficer_id.setText(String.valueOf(preqIntent.getPreqpurchofficer().getUserID()));

                preq_sub_total.setText(String.valueOf(preqIntent.getPreqsubtotal()));
                preq_sales_tax.setText(String.valueOf(preqIntent.getPreqsalestax()));
                preq_total.setText(String.valueOf(preqIntent.getPreqtotal()));

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
                requestedby_preq.setText(s+ " "+
                        serviceImpl.usersList.get(i).getLastname() + ", " +
                        serviceImpl.usersList.get(i).getFirstname());
            }
        });


        pmDialog = new SpinnerDialog(getActivity(), serviceImpl.userIDList, "Select User");
        pmDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                selectedpm = s;
                preqProjMan.setText(s + " "+
                        serviceImpl.usersList.get(i).getLastname() + ", " +
                        serviceImpl.usersList.get(i).getFirstname());
            }
        });


        poDialog = new SpinnerDialog(getActivity(), serviceImpl.userIDList, "Select User");
        poDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                selectedpo = s;
                preqPOfficer.setText(s+ " "+
                        serviceImpl.usersList.get(i).getLastname() + ", " +
                        serviceImpl.usersList.get(i).getFirstname());
//                Toast.makeText(getActivity(), String.valueOf(selectedReqBy), Toast.LENGTH_SHORT).show();
            }
        });


        oeDialog = new SpinnerDialog(getActivity(), serviceImpl.userIDList, "Select User");
        oeDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                selectedoe = s;
                preqOfficeEng.setText(s+ " "+
                        serviceImpl.usersList.get(i).getLastname() + ", " +
                        serviceImpl.usersList.get(i).getFirstname());
            }
        });


        btnSavePReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spo.isChecked()) {
                    ispo = true;
                } else {
                    ispo = false;
                }
                if(soe.isChecked()) {
                    isoe = true;
                } else {
                    isoe = false;
                }
                if(spm.isChecked()) {
                    ispm = true;
                } else {
                    ispm = false;
                }
                if(sstat.isChecked()) {
                    isstat =true;
                } else {
                    isstat=false;
                }
                BigDecimal subTotal, total;
                Double salTax;
                String subtotals = preq_sub_total.getText().toString().trim();
                String totals =preq_total.getText().toString();
                String salesTax = preq_sales_tax.getText().toString();
//                Toast.makeText(getActivity(), subtotals + totals, Toast.LENGTH_LONG).show();

                if (subtotals == "") {
                    subTotal = new BigDecimal("0.0");
                } else {
                    subTotal= new BigDecimal(subtotals);
                }
                if (totals == "") {
                    total =BigDecimal.ZERO;
                } else {
                    total = new BigDecimal(totals);
                }
                if (total.equals(BigDecimal.ZERO)) {
                    total = new BigDecimal("0.0");
                }

                if (salesTax == "") {
                    salTax = new Double("0.0");
                } else {
                    salTax = new Double(salesTax);
                }
                for (UserClass u : serviceImpl.usersList) {
                    if (u.getUserID().equals(Integer.valueOf(requestedby_preq_id.getText().toString()))) {
                        req = u;
                    }
                }
                Toast.makeText(getActivity(), req.toString(), Toast.LENGTH_LONG).show();
                for (UserClass u : serviceImpl.usersList) {
                    if (u.getUserID().equals(Integer.valueOf(preqProjMan_id.getText().toString()))) {
                        pm = u;
                    }
                }

                Toast.makeText(getActivity(), pm.toString(), Toast.LENGTH_LONG).show();
                for (UserClass u : serviceImpl.usersList) {
                    if (u.getUserID().equals(Integer.valueOf(preqOfficeEng_id.getText().toString()))) {
                        oe = u;
                    }
                }
                Toast.makeText(getActivity(), oe.toString(), Toast.LENGTH_LONG).show();
                for (UserClass u : serviceImpl.usersList) {
                    if (u.getUserID().equals(Integer.valueOf(preqPOfficer_id.getText().toString()))) {
                        po = u;
                    }
                }

                if(!preqID.getText().toString().matches("")) {
                    addPreq = new PurchaseRequestClass(preqIntent.getPreqID(),
                            preqIntent.getProjectID(),
                            preq_dateapproved.getText().toString().trim(),
                            preq_daterequested.getText().toString().trim(),
                            req,
                            isstat,
                            pm,
                            projman_dateapproved.getText().toString().trim(),
                            ispm,
                            po,
                            dateauthorized_purchase.getText().toString().trim(),
                            ispo,
                            oe,
                            officeengineer_dateapproved.getText().toString().trim(),
                            isoe,
                            subTotal,
                            salTax,
                            total);
                    EditPReq(preqIntent.getPreqID(), addPreq);
                    Toast.makeText(getActivity(), "Request has been successfully edited!", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                } else {

                    Toast.makeText(getActivity(), po.toString(), Toast.LENGTH_LONG).show();
//                Toast.makeText(getActivity(), projs.toString(), Toast.LENGTH_LONG).show();
                    addPreq = new PurchaseRequestClass(projs,
                            preq_dateapproved.getText().toString().trim(),
                            preq_daterequested.getText().toString().trim(),
                            req,
                            isstat,
                            pm,
                            projman_dateapproved.getText().toString().trim(),
                            ispm,
                            po,
                            dateauthorized_purchase.getText().toString().trim(),
                            ispo,
                            oe,
                            officeengineer_dateapproved.getText().toString().trim(),
                            isoe,
                            subTotal,
                            salTax,
                            total);

                    SavePReq(addPreq);
                }
            }
        });
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

                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<PurchaseRequestClass> call, Throwable t) {
                Toast.makeText(getActivity(), "An error has been encountered while adding request", Toast.LENGTH_SHORT);
            }
        });
    }

    public void EditPReq(int id, PurchaseRequestClass pr) {

        Call<Void> addPreq = preqService.editPreq(id, pr);

        addPreq.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Request has been successfully edited!", Toast.LENGTH_SHORT).show();

                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), "An error has been encountered while editing request", Toast.LENGTH_SHORT);
            }
        });
    }

    public void onClickCancel(View v) {
        getActivity().finish();

    }
}
